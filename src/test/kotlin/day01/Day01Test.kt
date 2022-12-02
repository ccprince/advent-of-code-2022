package day01

import io.kotest.matchers.shouldBe
import mostCalories
import org.junit.jupiter.api.Test
import topThreeCalories

class Day01Test {
    private val input = listOf(
        1000, 2000, 3000, null, 4000, null, 5000, 6000, null, 7000, 8000, 9000, null, 10000
    )

    @Test
    fun `can find the highest calorie count`() {
        mostCalories(input) shouldBe 24000
    }

    @Test
    fun `can get the top three calorie counts`() {
        topThreeCalories(input) shouldBe 45000
    }
}