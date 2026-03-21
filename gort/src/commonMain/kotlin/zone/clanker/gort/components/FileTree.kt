package zone.clanker.gort.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import zone.clanker.gort.icons.lucide.*
import zone.clanker.gort.theme.Gort

data class TreeNode(
    val label: String,
    val icon: String = "",
    val children: List<TreeNode> = emptyList(),
    val data: Any? = null,
) {
    val isLeaf: Boolean get() = children.isEmpty()
}

@Composable
fun FileTree(
    roots: List<TreeNode>,
    modifier: Modifier = Modifier,
    onNodeClick: ((TreeNode) -> Unit)? = null,
    initialExpandedDepth: Int = 1,
) {
    Column(modifier = modifier) {
        roots.forEach { node ->
            FileTreeNodeRow(
                node = node,
                depth = 0,
                onNodeClick = onNodeClick,
                initialExpandedDepth = initialExpandedDepth,
                parentExpanded = List(0) { false },
            )
        }
    }
}

@Composable
private fun FileTreeNodeRow(
    node: TreeNode,
    depth: Int,
    onNodeClick: ((TreeNode) -> Unit)?,
    initialExpandedDepth: Int,
    parentExpanded: List<Boolean>,
) {
    var expanded by remember { mutableStateOf(depth < initialExpandedDepth) }
    val colors = Gort.colors
    val spacing = Gort.spacing
    val haptic = LocalHapticFeedback.current

    // Animated chevron rotation
    val chevronRotation by animateFloatAsState(
        targetValue = if (expanded) 90f else 0f,
        animationSpec = spring(dampingRatio = 0.7f, stiffness = 600f),
    )

    // File type icon
    val (fileIcon, iconColor) = getFileIcon(node)

    val indent = (depth * 20).dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                // Draw connector lines
                val lineColor = colors.border.copy(alpha = 0.3f)
                val lineWidth = 1.dp.toPx()
                for (d in 0 until depth) {
                    val x = (d * 20 + 10).dp.toPx()
                    drawLine(lineColor, Offset(x, 0f), Offset(x, size.height), lineWidth)
                }
                if (depth > 0) {
                    val x = ((depth - 1) * 20 + 10).dp.toPx()
                    val midY = size.height / 2
                    drawLine(lineColor, Offset(x, midY), Offset(x + 10.dp.toPx(), midY), lineWidth)
                }
            }
            .clickable {
                if (!node.isLeaf) {
                    expanded = !expanded
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                }
                onNodeClick?.invoke(node)
            }
            .padding(start = indent, top = 2.dp, bottom = 2.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Expand/collapse chevron
        if (!node.isLeaf) {
            Image(
                imageVector = Lucide.ChevronRight,
                contentDescription = if (expanded) "Collapse" else "Expand",
                colorFilter = ColorFilter.tint(colors.onSurface.copy(alpha = 0.5f)),
                modifier = Modifier
                    .size(14.dp)
                    .graphicsLayer(rotationZ = chevronRotation),
            )
        } else {
            Spacer(Modifier.width(14.dp))
        }

        Spacer(Modifier.width(4.dp))

        // File/folder icon
        Image(
            imageVector = fileIcon,
            contentDescription = null,
            colorFilter = ColorFilter.tint(iconColor),
            modifier = Modifier.size(14.dp),
        )

        Spacer(Modifier.width(spacing.xs))

        // Label
        BasicText(
            text = node.label,
            style = Gort.typography.body.copy(color = colors.onSurface),
        )
    }

    // Children
    if (expanded && !node.isLeaf) {
        node.children.forEach { child ->
            FileTreeNodeRow(
                node = child,
                depth = depth + 1,
                onNodeClick = onNodeClick,
                initialExpandedDepth = initialExpandedDepth,
                parentExpanded = parentExpanded + expanded,
            )
        }
    }
}

@Composable
private fun getFileIcon(node: TreeNode): Pair<ImageVector, Color> {
    val colors = Gort.colors
    if (!node.isLeaf) {
        return Lucide.Folder to Color(0xFFFFBE0B) // gold folder
    }

    val ext = node.label.substringAfterLast('.', "").lowercase()
    return when (ext) {
        "kt", "kts" -> Lucide.FileCode to Color(0xFF7F52FF) // Kotlin purple
        "java" -> Lucide.FileCode to Color(0xFFE76F00) // Java orange
        "xml" -> Lucide.FileCode to Color(0xFF4CAF50) // green
        "json" -> Lucide.FileCode to Color(0xFFFFBE0B) // gold
        "gradle" -> Lucide.FileCode to Color(0xFF02303A) // gradle dark
        "md", "txt" -> Lucide.FileText to colors.onSurface.copy(alpha = 0.6f)
        "yaml", "yml" -> Lucide.FileCode to Color(0xFFCB171E)
        else -> Lucide.File to colors.onSurface.copy(alpha = 0.5f)
    }
}

/** Generic tree view — same as FileTree but semantically named for non-file data. */
@Composable
fun TreeView(
    roots: List<TreeNode>,
    modifier: Modifier = Modifier,
    onNodeClick: ((TreeNode) -> Unit)? = null,
    initialExpandedDepth: Int = 1,
) = FileTree(roots, modifier, onNodeClick, initialExpandedDepth)
