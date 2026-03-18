package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import zone.clanker.gort.theme.Gort

/**
 * Renders a subset of Markdown to Compose UI.
 * Supports: headers (h1-h3), bold, italic, inline code, code blocks,
 * unordered lists, blockquotes, horizontal rules, and paragraphs.
 */
@Composable
fun MarkdownRenderer(
    markdown: String,
    modifier: Modifier = Modifier,
) {
    val colors = Gort.colors
    val typography = Gort.typography
    val spacing = Gort.spacing
    val blocks = remember(markdown) { parseMarkdownBlocks(markdown) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        blocks.forEach { block ->
            when (block) {
                is MdBlock.Header -> {
                    val style = when (block.level) {
                        1 -> typography.display
                        2 -> typography.headline
                        else -> typography.title
                    }
                    BasicText(
                        text = block.text,
                        style = style.copy(color = colors.onSurface),
                    )
                }
                is MdBlock.Paragraph -> {
                    InlineMarkdownText(block.text)
                }
                is MdBlock.CodeBlock -> {
                    Code(
                        code = block.code,
                        language = block.language,
                    )
                }
                is MdBlock.Quote -> {
                    val borderColor = colors.primary
                    val borderWidth = Gort.borders.heavy
                    BasicText(
                        text = block.text,
                        style = typography.body.copy(
                            color = colors.onSurface.copy(alpha = 0.8f),
                            fontStyle = FontStyle.Italic,
                        ),
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
                    )
                }
                is MdBlock.ListItems -> {
                    block.items.forEach { item ->
                        Row(modifier = Modifier.padding(start = spacing.md)) {
                            BasicText(
                                text = "•",
                                style = typography.body.copy(color = colors.onSurface),
                                modifier = Modifier.padding(end = spacing.sm),
                            )
                            InlineMarkdownText(item)
                        }
                    }
                }
                is MdBlock.HorizontalRule -> {
                    Divider()
                }
            }
        }
    }
}

@Composable
private fun InlineMarkdownText(text: String) {
    val colors = Gort.colors
    val typography = Gort.typography

    val annotated = remember(text) {
        buildAnnotatedString {
            var i = 0
            while (i < text.length) {
                when {
                    // Bold **text**
                    text.startsWith("**", i) -> {
                        val end = text.indexOf("**", i + 2)
                        if (end > i) {
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(text.substring(i + 2, end))
                            }
                            i = end + 2
                        } else {
                            append(text[i])
                            i++
                        }
                    }
                    // Italic *text*
                    text[i] == '*' && (i == 0 || text[i - 1] != '*') -> {
                        val end = text.indexOf('*', i + 1)
                        if (end > i) {
                            withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                                append(text.substring(i + 1, end))
                            }
                            i = end + 1
                        } else {
                            append(text[i])
                            i++
                        }
                    }
                    // Inline code `text`
                    text[i] == '`' -> {
                        val end = text.indexOf('`', i + 1)
                        if (end > i) {
                            withStyle(SpanStyle(
                                fontFamily = typography.code.fontFamily,
                                background = colors.background,
                            )) {
                                append(text.substring(i + 1, end))
                            }
                            i = end + 1
                        } else {
                            append(text[i])
                            i++
                        }
                    }
                    else -> {
                        append(text[i])
                        i++
                    }
                }
            }
        }
    }

    BasicText(
        text = annotated,
        style = typography.body.copy(color = colors.onSurface),
    )
}

// Simple markdown block parser
private sealed class MdBlock {
    data class Header(val level: Int, val text: String) : MdBlock()
    data class Paragraph(val text: String) : MdBlock()
    data class CodeBlock(val code: String, val language: String?) : MdBlock()
    data class Quote(val text: String) : MdBlock()
    data class ListItems(val items: List<String>) : MdBlock()
    data object HorizontalRule : MdBlock()
}

private fun parseMarkdownBlocks(md: String): List<MdBlock> {
    val blocks = mutableListOf<MdBlock>()
    val lines = md.lines()
    var i = 0

    while (i < lines.size) {
        val line = lines[i]
        when {
            line.startsWith("```") -> {
                val lang = line.removePrefix("```").trim().ifEmpty { null }
                val codeLines = mutableListOf<String>()
                i++
                while (i < lines.size && !lines[i].startsWith("```")) {
                    codeLines.add(lines[i])
                    i++
                }
                blocks.add(MdBlock.CodeBlock(codeLines.joinToString("\n"), lang))
                i++ // skip closing ```
            }
            line.startsWith("###") -> {
                blocks.add(MdBlock.Header(3, line.removePrefix("###").trim()))
                i++
            }
            line.startsWith("##") -> {
                blocks.add(MdBlock.Header(2, line.removePrefix("##").trim()))
                i++
            }
            line.startsWith("#") -> {
                blocks.add(MdBlock.Header(1, line.removePrefix("#").trim()))
                i++
            }
            line.startsWith("> ") -> {
                val quoteLines = mutableListOf<String>()
                while (i < lines.size && lines[i].startsWith("> ")) {
                    quoteLines.add(lines[i].removePrefix("> "))
                    i++
                }
                blocks.add(MdBlock.Quote(quoteLines.joinToString("\n")))
            }
            line.startsWith("- ") || line.startsWith("* ") -> {
                val items = mutableListOf<String>()
                while (i < lines.size && (lines[i].startsWith("- ") || lines[i].startsWith("* "))) {
                    items.add(lines[i].drop(2))
                    i++
                }
                blocks.add(MdBlock.ListItems(items))
            }
            line.matches(Regex("^-{3,}$|^\\*{3,}$|^_{3,}$")) -> {
                blocks.add(MdBlock.HorizontalRule)
                i++
            }
            line.isBlank() -> {
                i++
            }
            else -> {
                val paraLines = mutableListOf<String>()
                while (i < lines.size && lines[i].isNotBlank() && !lines[i].startsWith("#") && !lines[i].startsWith("```")) {
                    paraLines.add(lines[i])
                    i++
                }
                blocks.add(MdBlock.Paragraph(paraLines.joinToString(" ")))
            }
        }
    }
    return blocks
}
