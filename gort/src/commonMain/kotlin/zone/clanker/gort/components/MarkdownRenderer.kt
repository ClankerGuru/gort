package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

/**
 * Full GFM Markdown renderer.
 *
 * Supports: h1-h6, bold, italic, bold+italic, strikethrough, inline code,
 * fenced code blocks, GFM tables, blockquotes (nested), ordered/unordered lists (nested),
 * checkbox lists, links, images (alt text placeholder), autolinks, horizontal rules,
 * and GitHub-style callouts.
 */
@Composable
fun MarkdownRenderer(
    markdown: String,
    modifier: Modifier = Modifier,
    onCopy: ((String) -> Unit)? = null,
) {
    val blocks = remember(markdown) { parseMdBlocks(markdown) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Gort.spacing.sm),
    ) {
        blocks.forEach { block ->
            RenderMdBlock(block, onCopy)
        }
    }
}

/** ByteArray overload — decodes UTF-8. */
@Composable
fun MarkdownRenderer(
    data: ByteArray,
    modifier: Modifier = Modifier,
    onCopy: ((String) -> Unit)? = null,
) {
    val text = remember(data) { data.decodeToString() }
    MarkdownRenderer(markdown = text, modifier = modifier, onCopy = onCopy)
}

// ============================================================
// Block rendering
// ============================================================

@Composable
private fun RenderMdBlock(block: MdBlock, onCopy: ((String) -> Unit)?) {
    val colors = Gort.colors
    val typography = Gort.typography
    val spacing = Gort.spacing

    when (block) {
        is MdBlock.Header -> {
            val style = when (block.level) {
                1 -> typography.displayLarge
                2 -> typography.displaySmall
                3 -> typography.headlineLarge
                4 -> typography.headlineMedium
                5 -> typography.headlineSmall
                else -> typography.titleLarge
            }
            MdInlineText(block.text, style.copy(color = colors.onSurface))
            if (block.level <= 2) Divider()
        }

        is MdBlock.Paragraph -> {
            MdInlineText(block.text, typography.body.copy(color = colors.onSurface))
        }

        is MdBlock.CodeBlock -> {
            Code(code = block.code, language = block.language, onCopy = onCopy)
        }

        is MdBlock.Quote -> {
            val borderColor = colors.primary
            val borderWidth = Gort.borders.heavy
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        drawLine(
                            color = borderColor,
                            start = Offset(0f, 0f),
                            end = Offset(0f, size.height),
                            strokeWidth = borderWidth.toPx(),
                        )
                    }
                    .padding(start = spacing.md),
                verticalArrangement = Arrangement.spacedBy(spacing.xs),
            ) {
                block.children.forEach { child -> RenderMdBlock(child, onCopy) }
            }
        }

        is MdBlock.Callout -> {
            val type = when (block.kind.uppercase()) {
                "NOTE" -> CalloutType.Info
                "TIP" -> CalloutType.Tip
                "WARNING" -> CalloutType.Warning
                "CAUTION", "DANGER" -> CalloutType.Danger
                "IMPORTANT" -> CalloutType.Info
                else -> CalloutType.Info
            }
            Callout(type = type, title = block.kind) {
                block.children.forEach { child -> RenderMdBlock(child, onCopy) }
            }
        }

        is MdBlock.UnorderedList -> {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                block.items.forEach { item ->
                    RenderMdListItem(item, onCopy, ordered = false, index = 0)
                }
            }
        }

        is MdBlock.OrderedList -> {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                block.items.forEachIndexed { idx, item ->
                    RenderMdListItem(item, onCopy, ordered = true, index = block.startNumber + idx)
                }
            }
        }

        is MdBlock.CheckboxList -> {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                block.items.forEach { (checked, text) ->
                    Row(modifier = Modifier.padding(start = spacing.md)) {
                        BasicText(
                            text = if (checked) "☑" else "☐",
                            style = typography.body.copy(
                                color = if (checked) colors.success else colors.onSurface.copy(alpha = 0.5f),
                            ),
                            modifier = Modifier.padding(end = spacing.sm),
                        )
                        MdInlineText(
                            text,
                            typography.body.copy(
                                color = colors.onSurface,
                                textDecoration = if (checked) TextDecoration.LineThrough else TextDecoration.None,
                            ),
                        )
                    }
                }
            }
        }

        is MdBlock.Table -> {
            Table(
                columns = block.headers.map { TableColumn(header = it, width = 140.dp) },
                rows = block.rows,
            )
        }

        is MdBlock.Image -> {
            BasicText(
                text = "\uD83D\uDDBC ${block.alt.ifEmpty { "Image" }}",
                style = typography.body.copy(
                    color = colors.onSurface.copy(alpha = 0.6f),
                    fontStyle = FontStyle.Italic,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.background, Gort.corners.small)
                    .padding(spacing.md),
            )
        }

        is MdBlock.HorizontalRule -> Divider()
    }
}

@Composable
private fun RenderMdListItem(
    item: MdListItem,
    onCopy: ((String) -> Unit)?,
    ordered: Boolean,
    index: Int,
) {
    val spacing = Gort.spacing
    val typography = Gort.typography
    val colors = Gort.colors

    Column {
        Row(modifier = Modifier.padding(start = spacing.md)) {
            BasicText(
                text = if (ordered) "$index." else "•",
                style = typography.body.copy(color = colors.onSurface),
                modifier = Modifier.padding(end = spacing.sm).width(if (ordered) 24.dp else 12.dp),
            )
            MdInlineText(item.text, typography.body.copy(color = colors.onSurface))
        }
        if (item.children.isNotEmpty()) {
            Column(modifier = Modifier.padding(start = spacing.lg)) {
                item.children.forEach { child -> RenderMdBlock(child, onCopy) }
            }
        }
    }
}

// ============================================================
// Inline markdown text rendering
// ============================================================

@Composable
private fun MdInlineText(text: String, baseStyle: TextStyle) {
    val colors = Gort.colors
    val annotated = remember(text, colors.primary, colors.background) {
        buildMdAnnotatedString(text, colors.primary, colors.background)
    }
    BasicText(text = annotated, style = baseStyle)
}

private fun buildMdAnnotatedString(
    text: String,
    primaryColor: Color,
    bgColor: Color,
): AnnotatedString = buildAnnotatedString {
    var i = 0
    while (i < text.length) {
        when {
            // Bold+Italic ***text***
            text.startsWith("***", i) -> {
                val end = text.indexOf("***", i + 3)
                if (end > i) {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)) {
                        append(text.substring(i + 3, end))
                    }
                    i = end + 3
                } else { append(text[i]); i++ }
            }
            // Strikethrough ~~text~~
            text.startsWith("~~", i) -> {
                val end = text.indexOf("~~", i + 2)
                if (end > i) {
                    withStyle(SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                        append(text.substring(i + 2, end))
                    }
                    i = end + 2
                } else { append(text[i]); i++ }
            }
            // Bold **text**
            text.startsWith("**", i) -> {
                val end = text.indexOf("**", i + 2)
                if (end > i) {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(text.substring(i + 2, end))
                    }
                    i = end + 2
                } else { append(text[i]); i++ }
            }
            // Italic *text*
            text[i] == '*' && (i == 0 || text[i - 1] != '*') && (i + 1 < text.length && text[i + 1] != '*') -> {
                val end = text.indexOf('*', i + 1)
                if (end > i) {
                    withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                        append(text.substring(i + 1, end))
                    }
                    i = end + 1
                } else { append(text[i]); i++ }
            }
            // Inline code `text`
            text[i] == '`' -> {
                val end = text.indexOf('`', i + 1)
                if (end > i) {
                    withStyle(SpanStyle(fontFamily = FontFamily.Monospace, background = bgColor)) {
                        append(text.substring(i + 1, end))
                    }
                    i = end + 1
                } else { append(text[i]); i++ }
            }
            // Link [text](url)
            text[i] == '[' -> {
                val closeB = text.indexOf(']', i + 1)
                if (closeB > i && closeB + 1 < text.length && text[closeB + 1] == '(') {
                    val closeP = text.indexOf(')', closeB + 2)
                    if (closeP > closeB) {
                        withStyle(SpanStyle(color = primaryColor, textDecoration = TextDecoration.Underline)) {
                            append(text.substring(i + 1, closeB))
                        }
                        i = closeP + 1
                    } else { append(text[i]); i++ }
                } else { append(text[i]); i++ }
            }
            // Autolink
            text.startsWith("https://", i) || text.startsWith("http://", i) -> {
                val end = findUrlEnd(text, i)
                withStyle(SpanStyle(color = primaryColor, textDecoration = TextDecoration.Underline)) {
                    append(text.substring(i, end))
                }
                i = end
            }
            else -> { append(text[i]); i++ }
        }
    }
}

private fun findUrlEnd(text: String, start: Int): Int {
    var i = start
    while (i < text.length && text[i] != ' ' && text[i] != '\n' && text[i] != ')') i++
    return i
}

// ============================================================
// Block parser
// ============================================================

private sealed class MdBlock {
    data class Header(val level: Int, val text: String) : MdBlock()
    data class Paragraph(val text: String) : MdBlock()
    data class CodeBlock(val code: String, val language: String?) : MdBlock()
    data class Quote(val children: List<MdBlock>) : MdBlock()
    data class Callout(val kind: String, val children: List<MdBlock>) : MdBlock()
    data class UnorderedList(val items: List<MdListItem>) : MdBlock()
    data class OrderedList(val items: List<MdListItem>, val startNumber: Int = 1) : MdBlock()
    data class CheckboxList(val items: List<Pair<Boolean, String>>) : MdBlock()
    data class Table(val headers: List<String>, val rows: List<List<String>>) : MdBlock()
    data class Image(val alt: String, val url: String) : MdBlock()
    data object HorizontalRule : MdBlock()
}

private data class MdListItem(
    val text: String,
    val children: List<MdBlock> = emptyList(),
)

private fun parseMdBlocks(md: String): List<MdBlock> {
    val blocks = mutableListOf<MdBlock>()
    val lines = md.lines()
    var i = 0

    while (i < lines.size) {
        val line = lines[i]
        when {
            // Fenced code block
            line.startsWith("```") -> {
                val lang = line.removePrefix("```").trim().ifEmpty { null }
                val codeLines = mutableListOf<String>()
                i++
                while (i < lines.size && !lines[i].startsWith("```")) {
                    codeLines.add(lines[i]); i++
                }
                blocks.add(MdBlock.CodeBlock(codeLines.joinToString("\n"), lang))
                if (i < lines.size) i++
            }

            // Image ![alt](url)
            line.trimStart().startsWith("![") -> {
                val altEnd = line.indexOf(']')
                val urlStart = if (altEnd >= 0) line.indexOf('(', altEnd) else -1
                val urlEnd = if (urlStart >= 0) line.indexOf(')', urlStart) else -1
                if (altEnd > 0 && urlStart > 0 && urlEnd > 0) {
                    blocks.add(MdBlock.Image(
                        alt = line.substring(line.indexOf("![") + 2, altEnd),
                        url = line.substring(urlStart + 1, urlEnd),
                    ))
                } else {
                    blocks.add(MdBlock.Paragraph(line))
                }
                i++
            }

            // Headers
            line.startsWith("######") -> { blocks.add(MdBlock.Header(6, line.removePrefix("######").trim())); i++ }
            line.startsWith("#####") && !line.startsWith("######") -> { blocks.add(MdBlock.Header(5, line.removePrefix("#####").trim())); i++ }
            line.startsWith("####") && !line.startsWith("#####") -> { blocks.add(MdBlock.Header(4, line.removePrefix("####").trim())); i++ }
            line.startsWith("###") && !line.startsWith("####") -> { blocks.add(MdBlock.Header(3, line.removePrefix("###").trim())); i++ }
            line.startsWith("##") && !line.startsWith("###") -> { blocks.add(MdBlock.Header(2, line.removePrefix("##").trim())); i++ }
            line.startsWith("# ") -> { blocks.add(MdBlock.Header(1, line.removePrefix("#").trim())); i++ }

            // GitHub callout: > [!TYPE]
            line.startsWith("> [!") -> {
                val kindEnd = line.indexOf(']', 4)
                if (kindEnd > 0) {
                    val kind = line.substring(4, kindEnd)
                    i++
                    val contentLines = mutableListOf<String>()
                    while (i < lines.size && (lines[i].startsWith("> ") || lines[i] == ">")) {
                        contentLines.add(lines[i].removePrefix("> ").removePrefix(">"))
                        i++
                    }
                    blocks.add(MdBlock.Callout(kind, parseMdBlocks(contentLines.joinToString("\n"))))
                } else {
                    i = parseMdQuote(lines, i, blocks)
                }
            }

            // Blockquote
            line.startsWith("> ") || line == ">" -> {
                i = parseMdQuote(lines, i, blocks)
            }

            // Checkbox list
            line.trimStart().let { it.startsWith("- [x]") || it.startsWith("- [ ]") || it.startsWith("- [X]") } -> {
                val items = mutableListOf<Pair<Boolean, String>>()
                while (i < lines.size) {
                    val l = lines[i].trimStart()
                    when {
                        l.startsWith("- [x]") || l.startsWith("- [X]") -> { items.add(true to l.drop(5).trim()); i++ }
                        l.startsWith("- [ ]") -> { items.add(false to l.drop(5).trim()); i++ }
                        else -> break
                    }
                }
                blocks.add(MdBlock.CheckboxList(items))
            }

            // Unordered list
            line.trimStart().let { it.startsWith("- ") || it.startsWith("* ") || it.startsWith("+ ") } -> {
                val items = mutableListOf<MdListItem>()
                while (i < lines.size) {
                    val l = lines[i].trimStart()
                    if (l.startsWith("- ") || l.startsWith("* ") || l.startsWith("+ ")) {
                        items.add(MdListItem(l.drop(2))); i++
                    } else break
                }
                blocks.add(MdBlock.UnorderedList(items))
            }

            // Ordered list
            line.trimStart().matches(Regex("^\\d+\\.\\s.*")) -> {
                val items = mutableListOf<MdListItem>()
                var startNum = 1; var first = true
                while (i < lines.size && lines[i].trimStart().matches(Regex("^\\d+\\.\\s.*"))) {
                    val l = lines[i].trimStart()
                    val dotIndex = l.indexOf('.')
                    if (first) { startNum = l.substring(0, dotIndex).toIntOrNull() ?: 1; first = false }
                    items.add(MdListItem(l.substring(dotIndex + 1).trim())); i++
                }
                blocks.add(MdBlock.OrderedList(items, startNum))
            }

            // Table
            line.contains('|') && i + 1 < lines.size && lines[i + 1].matches(Regex("^[\\s|:-]+$")) -> {
                val headers = line.split('|').map { it.trim() }.filter { it.isNotEmpty() }
                i += 2
                val rows = mutableListOf<List<String>>()
                while (i < lines.size && lines[i].contains('|')) {
                    rows.add(lines[i].split('|').map { it.trim() }.filter { it.isNotEmpty() }); i++
                }
                blocks.add(MdBlock.Table(headers, rows))
            }

            // Horizontal rule
            line.matches(Regex("^-{3,}$|^\\*{3,}$|^_{3,}$")) -> { blocks.add(MdBlock.HorizontalRule); i++ }

            // Blank
            line.isBlank() -> i++

            // Paragraph
            else -> {
                val paraLines = mutableListOf<String>()
                while (i < lines.size && lines[i].isNotBlank()
                    && !lines[i].startsWith("#")
                    && !lines[i].startsWith("```")
                    && !lines[i].startsWith("> ")
                    && !(lines[i].contains('|') && i + 1 < lines.size
                        && lines.getOrNull(i + 1)?.matches(Regex("^[\\s|:-]+$")) == true)
                    && !lines[i].matches(Regex("^-{3,}$|^\\*{3,}$|^_{3,}$"))
                ) {
                    paraLines.add(lines[i]); i++
                }
                blocks.add(MdBlock.Paragraph(paraLines.joinToString(" ")))
            }
        }
    }
    return blocks
}

private fun parseMdQuote(lines: List<String>, startIndex: Int, blocks: MutableList<MdBlock>): Int {
    var i = startIndex
    val contentLines = mutableListOf<String>()
    while (i < lines.size && (lines[i].startsWith("> ") || lines[i] == ">")) {
        contentLines.add(lines[i].removePrefix("> ").removePrefix(">"))
        i++
    }
    blocks.add(MdBlock.Quote(parseMdBlocks(contentLines.joinToString("\n"))))
    return i
}
