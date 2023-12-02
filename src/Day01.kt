fun main() {
    val NUMBERS = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    fun part1(input: List<String>): Int {
        return input.sumOf {
            val s = it.first(Char::isDigit)
            val s2 = it.last(Char::isDigit)
            (s.toString() + s2.toString()).toInt()
        }

    }

    fun part2(input: List<String>): Int {

        return input.sumOf {
            val digits = ArrayList<Int>()
            it.forEachIndexed { index, c ->
                if (c.isDigit()) digits.add(c.minus('0'))
                else {
                    NUMBERS.forEachIndexed { i, _ ->
                        if (it.substring(index).startsWith(NUMBERS.get(i))) digits.add(i)

                    }
                }
            }
            val n1: Int = digits.first() * 10 + digits.last()
            digits.clear()
            n1
        }

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 281)
    val input = readInput("Day01")
    println( "Part 1: ${part1(input)}")
    println( "Part 2: ${part2(input)}")
}
