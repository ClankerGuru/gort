package zone.clanker.gort.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            TreeNodeRow(
                node = node,
                depth = 0,
                onNodeClick = onNodeClick,
                initialExpandedDepth = initialExpandedDepth,
            )
        }
    }
}

@Composable
private fun TreeNodeRow(
    node: TreeNode,
    depth: Int,
    onNodeClick: ((TreeNode) -> Unit)?,
    initialExpandedDepth: Int,
) {
    var expanded by remember { mutableStateOf(depth < initialExpandedDepth) }
    val colors = Gort.colors
    val spacing = Gort.spacing
    val indent = (depth * 20).dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (!node.isLeaf) expanded = !expanded
                onNodeClick?.invoke(node)
            }
            .padding(start = indent, top = 2.dp, bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Expand/collapse indicator
        if (!node.isLeaf) {
            BasicText(
                text = if (expanded) "▼" else "▶",
                style = Gort.typography.label.copy(color = colors.onSurface.copy(alpha = 0.5f)),
                modifier = Modifier.width(16.dp),
            )
        } else {
            Spacer(Modifier.width(16.dp))
        }

        // Icon
        if (node.icon.isNotEmpty()) {
            BasicText(
                text = node.icon,
                style = Gort.typography.body,
                modifier = Modifier.padding(end = spacing.xs),
            )
        }

        // Label
        BasicText(
            text = node.label,
            style = if (node.isLeaf) {
                Gort.typography.body.copy(color = colors.onSurface)
            } else {
                Gort.typography.body.copy(color = colors.onSurface)
            },
        )
    }

    // Children
    if (expanded && !node.isLeaf) {
        node.children.forEach { child ->
            TreeNodeRow(
                node = child,
                depth = depth + 1,
                onNodeClick = onNodeClick,
                initialExpandedDepth = initialExpandedDepth,
            )
        }
    }
}

/**
 * Generic tree view — same as FileTree but semantically named for non-file data.
 */
@Composable
fun TreeView(
    roots: List<TreeNode>,
    modifier: Modifier = Modifier,
    onNodeClick: ((TreeNode) -> Unit)? = null,
    initialExpandedDepth: Int = 1,
) = FileTree(roots, modifier, onNodeClick, initialExpandedDepth)
