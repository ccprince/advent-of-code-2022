fun splitLinesOnBlank(lines: List<String>): List<List<String>> = lines.flatMapIndexed { index, x ->
    when {
        index == 0 || index == lines.lastIndex -> listOf(index)
        x.isEmpty() -> listOf(index - 1, index + 1)
        else -> emptyList()
    }
}.windowed(size = 2, step = 2) { (from, to) -> lines.slice(from..to) }

fun <T> splitListOnNull(list: List<T?>): List<List<T>> = list.flatMapIndexed { index, x ->
    when {
        index == 0 || index == list.lastIndex -> listOf(index)
        x == null -> listOf(index - 1, index + 1)
        else -> emptyList()
    }
}.windowed(size = 2, step = 2) { (from, to) -> list.slice(from..to) as List<T> }