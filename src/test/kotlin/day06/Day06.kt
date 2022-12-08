package day06

import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class Day06 {

    @Test
    fun `can find the first packet marker`() {
        listOf(
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 7,
            "bvwbjplbgvbhsrlpgdmjqwftvncz" to 5,
            "nppdvjthqldpwncqszvftbrmjlhg" to 6,
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 10,
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 11,
        ).forAll { (input, expectedResult) ->
            findFirstPacketMarker(input) shouldBe expectedResult
        }
    }

    @Test
    fun `can find the first message marker`() {
        listOf(
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 19,
            "bvwbjplbgvbhsrlpgdmjqwftvncz" to 23,
            "nppdvjthqldpwncqszvftbrmjlhg" to 23,
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 29,
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 26,
        ).forAll { (input, expectedResult) ->
            findFirstMessageMarker(input) shouldBe expectedResult
        }
    }
}