package day05

import io.kotest.matchers.shouldBe
import kotlin.test.Test

class Day05Test {

    private val initialState = listOf("ZN", "MCD", "P")

    @Test
    fun `can move a single crate to another stack`() {
        initialState.moveCrates(1, 2, 1) shouldBe listOf("ZND", "MC", "P")
    }

    @Test
    fun `can move multiple crates to another stack`() {
        initialState.moveCrates(2, 1, 3) shouldBe listOf("", "MCD", "PNZ")
    }

    @Test
    fun `can make several moves in succession`() {
        val moves = listOf(
            Move(1, 2, 1),
            Move(3, 1, 3),
            Move(2, 2, 1),
            Move(1, 1, 2)
        )
        initialState.moveCrates(moves) shouldBe listOf("C", "M", "PDNZ")
    }

    @Test
    fun `can parse moves and make them all`() {
        val moves = listOf(
            "move 1 from 2 to 1",
            "move 3 from 1 to 3",
            "move 2 from 2 to 1",
            "move 1 from 1 to 2",
        ).map(::parseLineToMove)
        initialState.moveCrates(moves) shouldBe listOf("C", "M", "PDNZ")
    }

    @Test
    fun `can pick the top crate from each stack`() {
        initialState.topCrates() shouldBe "NDP"
    }

    @Test
    fun `can move multiple crates to another stack all at once`() {
        initialState.moveCrates(2, 1,3, oneAtATime = false) shouldBe listOf("", "MCD", "PZN")
    }
}
