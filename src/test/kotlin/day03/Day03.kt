package day03

import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class Day03 {

    private val sampleData = listOf(
        "vJrwpWtwJgWrhcsFMMfFFhFp",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
        "PmmdzqPrVvPwwTWBwg",
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
        "ttgJtRGJQctTZtZT",
        "CrZsJsPPZsGzwwsLwLmpwMDw",
    )

    @Test
    fun `can find the common item in the two compartments`() {
        sampleData.zip(listOf('p', 'L', 'P', 'v', 't', 's')).forAll { (rucksack, expectedResult) ->
            findCommonItem(rucksack) shouldBe expectedResult
        }
    }

    @Test
    fun `can calculate the total priority based on the common item in a rucksack`() {
        calculateTotalPriority(sampleData) shouldBe 157
    }

    @Test
    fun `can find the badge for a group of three elves`() {
        listOf(
            sampleData.take(3) to 'r',
            sampleData.drop(3) to 'Z'
        ).forAll { (group, expectedResult) ->
            findBadge(group) shouldBe expectedResult
        }
    }

    @Test
    fun `can calculate the priority based on the badges`() {
        calculateBadgePriority(sampleData) shouldBe 70
    }
}