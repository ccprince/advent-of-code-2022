import java.io.File

fun main() {
    val lines = File("data/day01.txt").readLines().map { it.toIntOrNull() }

    println("The elf with the most calories has ${mostCalories(lines)}")
    println("The top three elves have ${topThreeCalories(lines)} in total")
}

fun mostCalories(lines: List<Int?>): Int = splitListOnNull(lines).let { elves ->
    elves.maxOf { it.sum() }
}

fun topThreeCalories(lines: List<Int?>): Int = splitListOnNull(lines).map { it.sum() }.sortedDescending().take(3).sum()
