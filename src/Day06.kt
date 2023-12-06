fun main() {
    val NUM = Regex("\\d+")
    fun part1(input: List<String>): Int {
        val times = NUM.findAll(input[0]).map { it.value.toInt() }.toList()
        val distances = NUM.findAll(input[1]).map { it.value.toInt() }.toList()
        val timesToDist = times.zip(distances)

        return timesToDist.fold(1) { mul, (time, distance) ->
            var waysToWin = 0
            for (i in 0..time) {
                if (i * (time - i) > distance) {
                    waysToWin++
                }

            }
            mul * waysToWin
        }


    }

    fun part2(input: List<String>): Long {
        val time = NUM.findAll(input[0]).map { it.value }.joinToString("").toLong()
        val distance = NUM.findAll(input[1]).map { it.value }.joinToString("").toLong()
        var waysToWin = 0L
        for (i in 0..time) {
            if (i * (time - i) > distance) {
                waysToWin++
            }

        }
        return waysToWin

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    part1(testInput).println()
    check(part1(testInput) == 288)
    check(part2(testInput) == 71503L)
    val input = readInput("Day06")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
