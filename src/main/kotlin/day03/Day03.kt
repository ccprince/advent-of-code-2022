package day03

import java.io.File

fun main() {
    val rucksacks = File("data/day03.txt").readLines()

    calculateTotalPriority(rucksacks).let {
        println("The total priority is $it")
    }

    calculateBadgePriority(rucksacks).let {
        println("The priority by badges is $it")
    }
}

fun findCommonItem(rucksack: String): Char {
    val compartmentOne = rucksack.take(rucksack.length / 2).toSet()
    val compartmentTwo = rucksack.drop(rucksack.length / 2).toSet()
    return compartmentOne.intersect(compartmentTwo).first()
}

fun calculateTotalPriority(rucksacks: Collection<String>): Int = rucksacks.sumOf { priority(findCommonItem(it)) }

fun priority(item: Char): Int = when (item) {
    in 'a'..'z' -> item - 'a' + 1
    in 'A'..'Z' -> item - 'A' + 27
    else -> 0 // Should never happen
}

fun findBadge(group: Collection<String>): Char {
    require(group.size == 3) { "Only works with a group of three elves" }
    return group.map { it.toSet() }.reduce { acc, rucksack -> acc.intersect(rucksack) }.first()
}

fun calculateBadgePriority(rucksacks: Collection<String>): Int = rucksacks.chunked(3).map(::findBadge).sumOf(::priority)