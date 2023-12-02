import java.lang.Integer.max

fun main() {


    fun part1(input: List<String>): Int {
        val redLimit = 12
        val blueLimit = 14
        val greenLimit = 13
        return input.sumOf {
            val parts = it.split(":")
            val id = parts[0].split(" ")[1].toInt()
            val subgames = parts[1].split(";")
            val pred = subgames.all {
                val g = it.split(",")
                g.all {
                    val p = it.trim().split(" ")
                    val value = p[0].toInt()
                    when (p[1]) {
                        "green" -> value <= greenLimit
                        "blue" -> value <= blueLimit
                        "red" -> value <= redLimit
                        else -> false
                    }

                }
            }
            if (pred) id else 0
        }
    }

    fun part2(input: List<String>): Int {

        return input.sumOf {
            val mins = mutableMapOf(
                    "green" to 0,
                    "blue" to 0,
                    "red" to 0
            )
            val parts = it.split(":")
            parts[0].split(" ")[1].toInt()
            val subgames = parts[1].split(";")
            subgames.forEach {
                val g = it.split(",")
                g.forEach {
                    val p = it.trim().split(" ")
                    val value = p[0].toInt()
                    mins[p[1]] = max(mins[p[1]]!!, value)
                }
            }
            mins["green"]?.times(mins["red"]!!)?.times(mins["blue"]!!)!!
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    val input = readInput("Day02")
    part1(testInput)
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)
    println( "Part 1: ${part1(input)}")
    println( "Part 2: ${part2(input)}")
}
