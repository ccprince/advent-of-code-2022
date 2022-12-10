package day10

import java.io.File

fun main() {
    val program = File("data/day10.txt").readLines().map(::parseToInstruction).toTypedArray()

    println("The signal strength is ${signalStrength(program)}")
    renderSignal(program).let { screen ->
        println("The screen looks like:")
        screen.forEach(::println)
    }
}


data class Cpu(val program: Array<Instruction>, val x: Int = 1, val pointer: Int = 0, val inProcess: Boolean = false)

fun Cpu.tick(): Cpu {
    val currentInstruction = program[pointer]
    return when (currentInstruction) {
        is Noop -> copy(pointer = pointer + 1)
        is AddX -> if (inProcess) copy(
            pointer = pointer + 1,
            x = x + currentInstruction.dx,
            inProcess = false
        ) else copy(inProcess = true)
    }
}

sealed interface Instruction
object Noop : Instruction
data class AddX(val dx: Int) : Instruction

fun parseToInstruction(line: String): Instruction = if (line == "noop") Noop else AddX(line.drop(5).toInt())

//
// The function is based on the state _during_, not after, that tick is executed. So, the value of X during cycle
// 20 is actually the value after cycle 19.
//
fun signalStrength(program: Array<Instruction>): Int =
    (1..220).scan(0 to Cpu(program)) { (_, current), index -> index to current.tick() }
        .drop(19)
        .filter { (i, _) -> (i - 19) % 40 == 0 }
        .sumOf { (i, state) -> state.x * (i + 1) }

fun renderSignal(program: Array<Instruction>): List<String> {
    val states = (1..240).scan(Cpu(program)) { current, _ -> current.tick() }.map { it.x }
    val duringCycle = (listOf(1) + states).toTypedArray() // just to make the indices line up

    return (0..5).map { row ->
        (0..39).map { column ->
            val center = duringCycle[row * 40 + column + 1]
            if (column in (center-1)..(center+1)) '#' else '.'
        }.joinToString("")
    }
}