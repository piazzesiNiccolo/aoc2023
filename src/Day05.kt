fun main() {
    val NUM = Regex("\\d+")
    fun List<String>.toListOfNumber(): List<List<Long>> {
        return this.map { NUM.findAll(it).map { it.value.toLong() }.toList() }
    }

    fun List<List<Long>>.updateSeeds1(seeds: MutableList<Long>) {
        val changed = BooleanArray(seeds.size)
        changed.fill(false)
        this.forEach { list ->
            val (dest, source, len) = list
            for (i in seeds.indices) {
                if (source <= seeds[i] && seeds[i] < (source + len) && !changed[i]) {
                    seeds[i] = seeds[i] + (dest - source)
                    changed[i] = true

                }
            }

        }

    }

    fun List<List<Long>>.updateSeeds2(seeds: MutableList<Pair<Long, Long>>) {

    }

    fun part1(input: String): Long {
        val parts = input.split("\n\n")
        val seeds = NUM.findAll(parts[0]).map { it.value }.map { it.toLong() }.toMutableList()

        for (i in 1..7) {
            parts[i].split(":\n")[1].split("\n").toListOfNumber().updateSeeds1(seeds)
        }
        return seeds.min()


    }

    fun part2(input: String): Long {
        val parts = input.split("\n\n")
        val seed = NUM.findAll(parts[0]).map { it.value }.map { it.toLong() }.toList()

        val seedRanges = mutableListOf<Pair<Long, Long>>()
        for (i in seed.indices step 2) {
            seedRanges.add(Pair(seed[i], seed[i] + seed[i + 1] - 1))
        }
        seedRanges.println()
        for (i in 1..7) {
            parts[i].split(":\n")[1].split("\n").toListOfNumber().updateSeeds2(seedRanges)
        }
        return seedRanges.minOf { it.first }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputWhole("Day05_test")
    check(part1(testInput) == 35L)
//    check(part2(testInput) == 46L)
    val input = readInputWhole("Day05")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
