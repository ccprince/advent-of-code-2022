package day02

import day02.RPS.*
import day02.RPSResult.*
import java.io.File

fun main() {
    val input = File("data/day02.txt").readLines()

    input.map(::parseLineForSuggestedPlay).let {
        val totalScore = it.sumOf { scoreRound(it.first, it.second) }
        println("The total score for suggested plays is $totalScore")
    }

    input.map(::parseLineForSuggestedOutcome).let {
        val totalScore = it.sumOf { scoreRound(it.first, it.second) }
        println("The total score for suggested outcomes is $totalScore")
    }
}

fun parseLineForSuggestedPlay(l: String): Pair<RPS, RPS> {
    val him = when(l[0]) {
        'A' -> Rock
        'B' -> Paper
        else -> Scissors
    }
    val me = when(l[2]) {
        'X' -> Rock
        'Y' -> Paper
        else -> Scissors
    }
    return him to me
}

fun parseLineForSuggestedOutcome(l: String): Pair<RPS, RPS> {
    val him = when(l[0]) {
        'A' -> Rock
        'B' -> Paper
        else -> Scissors
    }
    val me = when(l[2]) {
        'X' -> playToLoseTo(him)
        'Y' -> him
        else -> playToBeat(him)
    }
    return Pair(him, me)
}

fun scoreRound(he: RPS, me: RPS): Int {
    val choiceScore = when (me) {
        Rock -> 1
        Paper -> 2
        Scissors -> 3
    }
    val victoryScore = when (playRPS(he, me)) {
        AWins -> 0
        BWins -> 6
        Draw -> 3
    }
    return choiceScore + victoryScore
}

enum class RPS {
    Rock,
    Paper,
    Scissors;
}

enum class RPSResult {
    AWins,
    BWins,
    Draw
}

fun playRPS(a: RPS, b: RPS): RPSResult = if (a == b) Draw else when (a) {
    Rock -> if (b == Scissors) AWins else BWins
    Paper -> if (b == Rock) AWins else BWins
    Scissors -> if (b == Paper) AWins else BWins
}

fun playToBeat(p: RPS): RPS = when (p) {
    Rock -> Paper
    Paper -> Scissors
    Scissors -> Rock
}

fun playToLoseTo(p: RPS): RPS = when (p) {
    Rock -> Scissors
    Paper -> Rock
    Scissors -> Paper
}