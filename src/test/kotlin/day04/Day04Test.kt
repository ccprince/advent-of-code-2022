package day04

import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class Day04Test {

    @Test
    fun `can determine if one pair fully contains another`() {
        listOf(
            Pair(2..4, 6..8) to false,
            Pair(2..3, 4..5) to false,
            Pair(5..7, 7..9) to false,
            Pair(2..8, 3..7) to true,
            Pair(6..6, 4..6) to true,
            Pair(2..6, 4..8) to false
        ).forAll { (input, expectedResult) ->
            hasFullOverlap(input.first, input.second) shouldBe expectedResult
        }
    }

    @Test
    fun `can determine if one pair at least partially contains another`() {
        listOf(
            Pair(2..4, 6..8) to false,
            Pair(2..3, 4..5) to false,
            Pair(5..7, 7..9) to true,
            Pair(2..8, 3..7) to true,
            Pair(6..6, 4..6) to true,
            Pair(2..6, 4..8) to true
        ).forAll { (input, expectedResult) ->
            hasSomeOverlap(input.first, input.second) shouldBe expectedResult
        }
    }
}

