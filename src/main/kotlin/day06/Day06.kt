package day06

import java.io.File

fun main() {
    val signal = File("data/day06.txt").readLines()[0]

    findFirstPacketMarker(signal).let {
        println("The first packet marker is found at character $it")
    }
    findFirstMessageMarker(signal).let {
        println("The first message marker is found at character $it")
    }
}


fun findFirstPacketMarker(signal: String): Int = findMarker(signal, 4)
fun findFirstMessageMarker(signal: String): Int = findMarker(signal, 14)

fun findMarker(signal: String, chunkSize: Int): Int =
    signal.windowed(chunkSize).withIndex().first { (_, chunk) -> chunk.toSet().size == chunkSize }.index + chunkSize