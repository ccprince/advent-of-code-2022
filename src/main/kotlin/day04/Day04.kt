package day04

import java.io.File

fun main() {
    val pairs = File("data/day04.txt").readLines().map { line ->
        val numbers = line.split(',', '-').map { it.toInt() }
        Pair(numbers[0]..numbers[1], numbers[2]..numbers[3])
    }

    pairs.count { (a, b) -> hasFullOverlap(a, b) }.let { overlaps ->
        println("There are $overlaps pairs with full overlaps")
    }

    pairs.count { (a, b) -> hasSomeOverlap(a, b) }.let { overlaps ->
        println("There are $overlaps pairs with some overlap")
    }
}

fun hasFullOverlap(r1: IntRange, r2: IntRange): Boolean = r1.fullyContains(r2) || r2.fullyContains(r1)
fun IntRange.fullyContains(other: IntRange): Boolean = other.start in this && other.endInclusive in this

fun hasSomeOverlap(r1: IntRange, r2: IntRange): Boolean = r1.contains(r2) || r2.contains(r1)
fun IntRange.contains(other: IntRange): Boolean = other.start in this || other.endInclusive in this