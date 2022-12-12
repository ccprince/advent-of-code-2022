package day11

fun main() {
    val monkeys = listOf(
        Monkey(listOf(52, 78, 79, 63, 51, 94), { it * 13 }, 5, 1, 6),
        Monkey(listOf(77, 94, 70, 83, 53), { it + 3 }, 7, 5, 3),
        Monkey(listOf(98, 50, 76), { it * it }, 13, 0, 6),
        Monkey(listOf(92, 91, 61, 75, 99, 63, 84, 69), { it + 5 }, 11, 5, 7),
        Monkey(listOf(51, 53, 83, 52), { it + 7 }, 3, 2, 0),
        Monkey(listOf(76, 76), { it + 4 }, 2, 4, 7),
        Monkey(listOf(75, 59, 93, 69, 76, 96, 65), { it * 19 }, 17, 1, 3),
        Monkey(listOf(89), { it + 2 }, 19, 2, 4),
    )

    println("The amount of monkey business is ${monkeyBusiness(monkeys)}")
}

data class Monkey(
    val items: List<Int>,
    val operation: (Int) -> Int,
    val testDivisor: Int,
    val ifTrue: Int,
    val ifFalse: Int,
    val itemsExamined: Int = 0
)

fun playRound(monkeys: List<Monkey>): List<Monkey> {
    val itemsLists = monkeys.map { it.items.toMutableList() }
    val counts = monkeys.map { it.itemsExamined }.toMutableList()

    monkeys.forEachIndexed() { i, monkey ->
        itemsLists[i].forEach { item ->
            val newWorry = monkey.operation(item) / 3
            val throwTo = if (newWorry % monkey.testDivisor == 0) monkey.ifTrue else monkey.ifFalse
            itemsLists[throwTo].add(newWorry)
            counts[i] += 1
        }
        itemsLists[i].clear()
    }

    return monkeys.mapIndexed { i, m ->
        monkeys[i].copy(
            items = itemsLists[i], itemsExamined = counts[i]
        )
    }
}

fun monkeyBusiness(monkeys: List<Monkey>, rounds: Int = 20): Int =
    (1..rounds).fold(monkeys) { m, _ -> playRound(m) }.map { it.itemsExamined }.sorted().reversed()
        .let { counts -> counts[0] * counts[1] }