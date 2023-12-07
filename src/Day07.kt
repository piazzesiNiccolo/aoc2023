enum class CardType(val value: Int) {
    FIVE(6),
    FOUR(5),
    FULLHOUSE(4),
    TRIS(3),
    TWO_PAIR(2),
    ONE_PAIR(1),
    HIGH_CARD(0)

}
object LabelToValue {
    val mapping = mapOf(
            'A' to 14,
            'K' to 13,
            'Q' to 12,
            'J' to 1,
            'T' to 10,
            '9' to 9,
            '8' to 8,
            '7' to 7,
            '6' to 6,
            '5' to 5,
            '4' to 4,
            '3' to 3,
            '2' to 2
    )
}
data class Game(val hand: String, val bid: Int, val handType: CardType) : Comparable<Game> {
    override fun compareTo(other: Game): Int {

        val handT = this.handType.value - other.handType.value
        if (handT != 0) {
            return handT
        } else {
            for (i in this.hand.indices) {
                val compareCards = LabelToValue.mapping[this.hand[i]]!! - LabelToValue.mapping[other.hand[i]]!!
                if (compareCards != 0) return compareCards
            }
            return 0
        }
    }
}

sealed class HandTypeParser {
    abstract fun parseHandType(hand: String): CardType
}

object Part1Parser : HandTypeParser() {
    override fun parseHandType(hand: String): CardType {
        val uniqueCards = mutableSetOf<Char>()
        for (h in hand) {
            uniqueCards.add(h)
        }
        return when (uniqueCards.size) {
            5 -> CardType.HIGH_CARD
            4 -> CardType.ONE_PAIR
            3 -> {
                var same = 1
                val s = String(hand.toCharArray().sortedArray())
                for (i in 1..<s.length) {
                    if (s[i] == s[i - 1]) same++
                    else same = 1
                    if (same == 3) return CardType.TRIS
                }
                return CardType.TWO_PAIR
            }

            2 -> {
                var sameFirst = 1
                for (i in 1..<hand.length) {
                    if (hand[i] == hand[0]) sameFirst++
                }
                if (sameFirst == 1 || sameFirst == 4) return CardType.FOUR
                else return CardType.FULLHOUSE
            }

            1 -> CardType.FIVE
            else -> throw IllegalArgumentException("Invalid hand size")
        }
    }
}

object Part2Parser: HandTypeParser(){
    override fun parseHandType(hand: String): CardType {
        if (!hand.contains('J')) return Part1Parser.parseHandType(hand)
      /*  val js = Regex("J").findAll(hand).count()
        return when(js){
            5,4 -> CardType.FIVE
            3 -> CardType.FOUR
            2 -> {
                val uniq = hand.toSet().size
                if (uniq == 2) CardType.FIVE
                else
                    if (uniq == 3) CardType.FOUR
                    else CardType.TRIS


            }
            1 -> {
                var res = CardType.HIGH_CARD
                val  uniq = hand.toSet().size
                if (uniq == 5) res = CardType.ONE_PAIR
                else if(uniq == 2) CardType.FIVE
                else {

                }
                res

            }

            else -> throw  IllegalArgumentException("Invalid hand")
        }*/
        return  CardType.TWO_PAIR
    }
}
fun main() {


    fun solver(input: List<String>, handTypeParser: HandTypeParser): Long {
        val cardsAndBids = input.map {
            val (hand, bid) = it.split(" ")
            Game(hand, bid.toInt(), handTypeParser.parseHandType(hand))
        }

        return cardsAndBids.sorted().foldIndexed(0L) { i, sum, g ->
            val s = (i + 1) * (g.bid)
            sum + s
        }

    }

    fun part1(input: List<String>) : Long {
        return solver(input, Part1Parser)
    }

    fun part2(input: List<String>): Long {

        return solver(input,Part2Parser)

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 6440L)
    check(part2(testInput) == 0L)
    val input = readInput("Day07")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
