package day10

import io.kotest.matchers.shouldBe
import java.io.File
import kotlin.test.Test
import kotlin.test.fail

class Day10Test {

    val shortSample = listOf("noop", "addx 3", "addx -5").map(::parseToInstruction).toTypedArray()
    private val longSample = File("data/day10-test.txt").readLines().map(::parseToInstruction).toTypedArray()

    @Test
    fun `tick on a noop increases the instruction pointer`() {
        val initial = Cpu(shortSample)
        initial.tick() shouldBe Cpu(shortSample, x = 1, pointer = 1, inProcess = false)
    }

    @Test
    fun `tick on an addx only sets the in-process flag`() {
        val initial = Cpu(shortSample, pointer = 1)
        initial.tick() shouldBe Cpu(shortSample, x = 1, pointer = 1, inProcess = true)
    }

    @Test
    fun `tick on an in-process addx updates everything`() {
        val initial = Cpu(shortSample, pointer = 2, inProcess = true)
        initial.tick() shouldBe Cpu(shortSample, x = -4, pointer = 3, inProcess = false)
    }

    @Test
    fun `can run the short program`() {
        val initial = Cpu(shortSample)
        val result = (1..5).scan(initial) { current, _ -> current.tick() }.map { it.x }
        result shouldBe listOf(1, 1, 1, 4, 4, -1)
    }

    @Test
    fun `can calculate signal strength`() {
        signalStrength(longSample) shouldBe 13140
    }

    @Test
    fun `can calculate intermediate strengths`() {
        val intermediate = (1..221).scan(Cpu(longSample)) { current, _ -> current.tick() }.withIndex().drop(19)
            .filter { (i, _) -> (i - 19) % 40 == 0 }.map { (i, state) -> state.x * (i + 1) }
        intermediate shouldBe listOf(420, 1140, 1800, 2940, 2880, 3960)
    }

    @Test
    fun `can render the screen`() {
        renderSignal(longSample) shouldBe listOf(
            "##..##..##..##..##..##..##..##..##..##..",
            "###...###...###...###...###...###...###.",
            "####....####....####....####....####....",
            "#####.....#####.....#####.....#####.....",
            "######......######......######......####",
            "#######.......#######.......#######.....",
        )
    }
}
