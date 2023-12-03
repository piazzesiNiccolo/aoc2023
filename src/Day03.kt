data class Num(val n: Int, val y: Int, val x: Pair<Int,Int>){
    fun adjacent(x:Int, y: Int) : Boolean {
        return (this.x.first - 1 <= x && x <= this.x.second + 1) &&
                (this.y-1 <= y && y <= this.y + 1)
    }
}

fun neighbors(x: Int, y: Int, maxX: Int, maxY: Int): List<Pair<Int, Int>> {
    val res = ArrayList<Pair<Int, Int>>()
    for (v in listOf(x - 1, x, x + 1)) {
        for (nv in listOf(y - 1, y, y + 1)) {
            if (v == x && nv == y) continue
            if (v >= 0 && v < maxX && nv >= 0 && nv < maxY) {
                res.add(Pair(v, nv))
            }
        }

    }
    return res
}

fun main() {

    fun part1(input: List<String>): Int {
        return input.foldIndexed(0) { i, sum, l ->
            val line = l.trim()
            val partNumbers = ArrayList<Int>()
            var j = 0
            while (j < line.length) {
                var isPart = false
                val s = StringBuilder()
                while (j < line.length && line[j].isDigit()) {
                    if(!isPart) {
                        val ns = neighbors(i, j, input.size, line.length)
                        isPart = ns.any { input[it.first][it.second] != '.' && !input[it.first][it.second].isDigit() }
                    }
                    s.append(line[j])
                    j++
                }
                if (isPart) partNumbers.add(s.toString().toInt())
                j++
            }
            sum + partNumbers.sum()
        }

    }

    fun part2(input: List<String>): Int {
        val NUM = "\\d+".toRegex()
        val stars = ArrayList<Pair<Int,Int>>()
        input.forEachIndexed {i,line ->
            line.forEachIndexed { j, c ->
                if (c == '*') stars.add(Pair(j,i))
            }
        }
        val nums = ArrayList<Num>()
        input.forEachIndexed {y,line ->
            for (match in NUM.findAll(line)){
                nums.add(Num(match.groupValues[0].toInt(),y, Pair(match.range.first,match.range.last)))
            }

        }
        return stars.sumOf {s ->
            val adj = nums.filter { it.adjacent(s.first,s.second) }
            if (adj.size == 2) adj[0].n * adj[1].n  else 0
        }

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)
    println( part2(testInput))
    check(part2(testInput) == 467835)
    val input = readInput("Day03")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
