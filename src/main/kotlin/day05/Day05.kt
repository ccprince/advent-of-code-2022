package day05

import java.io.File

typealias Cargo = List<String>

data class Move(val n: Int, val from: Int, val to: Int)

fun Cargo.moveCrates(n: Int, from: Int, to: Int, oneAtATime: Boolean = true): Cargo {
    val movedCrates = this[from - 1].takeLast(n).let { if (oneAtATime) it.reversed() else it }
    return this.mapIndexed { i, stack ->
        when (i) {
            from - 1 -> stack.dropLast(n)
            to - 1 -> stack + movedCrates
            else -> stack
        }
    }
}

fun Cargo.moveCrates(moves: Collection<Move>, oneAtATime: Boolean = true): Cargo =
    moves.fold(this) { acc, move -> acc.moveCrates(move.n, move.from, move.to, oneAtATime) }

fun Cargo.topCrates(): String = this.map { it.takeLast(1) }.joinToString("")

private val regex = Regex("""move (\d+) from (\d+) to (\d+)""")
fun parseLineToMove(l: String): Move {
    val (n, from, to) = regex.matchEntire(l)!!.destructured
    return Move(n.toInt(), from.toInt(), to.toInt())
}



fun main() {
    // Hand-parsing the initial state, because that's not the interesting part of this problem...
    val initialState = listOf(
        "DMSZRFWN", "WPQGS", "WRVQFNJC", "FZPCGDL", "TPS", "HDFWRL", "ZNDC", "WNRFVSJQ", "RMSGZWV"
    )
    val moves = File("data/day05.txt").readLines().dropWhile { it.isNotEmpty() }.drop(1)
        .map(::parseLineToMove)

    println("The top crates are: ${initialState.moveCrates(moves).topCrates()}")
    println("If you move multiple crates at a time, the top crates are ${initialState.moveCrates(moves, oneAtATime = false).topCrates()}")
}