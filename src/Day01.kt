import java.lang.IllegalArgumentException
import java.util.ArrayList

object Consts {




}
fun main() {
    val NUMBERS = listOf(
            "zero",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
    )
    fun part1(input: List<String>): Int {
        return input.sumOf {
            val s = it.first(Char::isDigit)
            val s2 = it.last(Char::isDigit)
            (s.toString() + s2.toString()).toInt()
        }

    }

    fun part2(input: List<String>): Int {

        return input.sumOf {
           var digits = ArrayList<Int>()
           var n1 = 0
           it.forEachIndexed {index, c ->
               if (c.isDigit()) digits.add(c.minus('0'))
               NUMBERS.forEachIndexed{i,_ ->
                   if (it.substring(index).startsWith(NUMBERS.get(i))) digits.add(i)

               }
           }
           n1 =  digits.first()*10 + digits.last()
           digits.clear()
           n1
        }

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part2(testInput) == 281)
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
