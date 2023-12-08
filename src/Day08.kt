fun main() {

    fun part1(input: String): Int {
        val (instr, path) = input.split(("\n\n"))
        instr.println()
        val nodes = path.split("\n").fold(ArrayList<List<String>>()) {l, p ->
            l.add(Regex("\\w+").findAll(p).map { it.value }.toList())
            l

        }
        var n = nodes.first { it[0] == "AAA" }
        var i = 0
        var steps = 0
        while (n[0] != "ZZZ") {
            if (instr[i] == 'L')
                n = nodes.first{it[0] == n[1]}
            else
                n = nodes.first{it[0] == n[2]}
            steps++
            i = (i+1)%instr.length
        }
        return steps
    }

    fun part2(input: String): Long {
        val (instr, path) = input.split(("\n\n"))
        instr.println()
        val nodes = path.split("\n").fold(ArrayList<List<String>>()) {l, p ->
            l.add(Regex("\\w+").findAll(p).map { it.value }.toList())
            l

        }
        return nodes.filter { it[0].last() == 'A' }.map { n ->
            var c = n
            var i = 0
            var steps = 0
            while (c[0].last() != 'Z') {
                if (instr[i] == 'L')
                    c = nodes.first{it[0] == c[1]}
                else
                    c = nodes.first{it[0] == c[2]}
                steps++
                i = (i+1)%instr.length
            }
            steps
        }.map (Int::toBigInteger ) .reduce { acc, steps -> acc * steps / acc.gcd(steps) }.toLong()

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputWhole("Day08_test")
    part2(testInput).println()
    val input = readInputWhole("Day08")
    println( "Part 2: ${part2(input)}")
}
