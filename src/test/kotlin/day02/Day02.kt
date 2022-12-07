package day02

import day02.RPS.*
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class Day02 {

    @Test
    fun `can score a round given a suggested play`() {
        listOf(
            Pair(Rock, Paper) to 8,
            Pair(Paper, Rock) to 1,
            Pair(Scissors, Scissors) to 6
        ).forAll { (guide, expectedScore) ->
            val (him, me) = guide
            scoreRound(him, me) shouldBe expectedScore
        }
    }

    @Test
    fun `can score a round given a suggested outcome`() {
        listOf(
            "A Y" to 4,
            "B X" to 1,
            "C Z" to 7
        ).forAll { (input, expectedScore) ->
            val (him, me) = parseLineForSuggestedOutcome(input)
            scoreRound(him, me) shouldBe expectedScore
        }
    }
}