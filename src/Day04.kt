import kotlin.math.pow

fun main() {

    fun part1(input: List<String>): Int {
        return input.sumOf { it ->
            val cards = it.split(":")[1].split("|")
            val winningCards = cards[0].trim().split("\\s+".toRegex()).map { it.toInt() }
            val myCards = cards[1].trim().split("\\s+".toRegex()).map { it.toInt() }
            val t = winningCards.intersect(myCards)
            2.0.pow(t.size.toDouble() - 1).toInt()

        }

    }

    fun part2(input: List<String>): Int {
        val copies = (1..input.size).associateWith { 1 }.toMutableMap()
        input.forEachIndexed { i, line ->
            val cards = line.split(":")[1].split("|")
            val winningCards = cards[0].trim().split("\\s+".toRegex()).map { it.toInt() }
            val myCards = cards[1].trim().split("\\s+".toRegex()).map { it.toInt() }
            val t = myCards.intersect(winningCards.toSet()).size
            for (card in i + 2..i + t + 1) {
                copies[card] = copies[card]!! + copies[i + 1]!!
            }
        }
        return copies.values.sum()

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    println(part1(testInput))
    check(part1(testInput) == 13)
    val input = readInput("Day04")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
