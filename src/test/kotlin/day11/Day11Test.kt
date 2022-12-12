package day11

import io.kotest.matchers.shouldBe
import kotlin.test.Test

class Day11Test {

    private val sampleMonkeys = listOf(
        Monkey(listOf(79, 98), { it * 19 }, 23, 2, 3),
        Monkey(listOf(54, 65, 75, 74), { it + 6 }, 19, 2, 0),
        Monkey(listOf(79, 60, 97), { it * it }, 13, 1, 3),
        Monkey(listOf(74), { it + 3 }, 17, 0, 1)
    )

    @Test
    fun `can correctly play one round`() {
        val result = playRound(sampleMonkeys)

        result.map { it.items } shouldBe listOf(
            listOf(20, 23, 27, 26),
            listOf(2080, 25, 167, 207, 401, 1046),
            listOf(),
            listOf()
        )
        result.map { it.itemsExamined } shouldBe listOf(2, 4, 3, 5)
    }

    @Test
    fun `can correctly play 20 rounds`() {
        val result = (1..20).fold(sampleMonkeys) { monkeys, _ -> playRound(monkeys) }

        result.map { it.items } shouldBe listOf(
            listOf(10, 12, 14, 26, 34),
            listOf(245, 93, 53, 199, 115),
            listOf(),
            listOf()
        )
        result.map { it.itemsExamined } shouldBe listOf(101, 95, 7, 105)
    }

    @Test
    fun `can calculate monkey business`() {
        monkeyBusiness(sampleMonkeys, 1) shouldBe 20
        monkeyBusiness(sampleMonkeys) shouldBe 10605
    }
}

