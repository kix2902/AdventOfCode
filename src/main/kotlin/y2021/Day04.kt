class Card(data: List<List<Int>>) {
    private var numbers: List<List<Pair<Int, Boolean>>>
    private var lastNumber = -1
    var win = false

    init {
        numbers = data.map { row ->
            row.map {
                Pair(it, false)
            }
        }
    }

    fun mark(number: Int) {
        if (!win) {
            numbers = numbers.map { row ->
                row.map {
                    if (it.first == number) Pair(number, true) else it
                }
            }
            lastNumber = number

            win = rowWin() || columnWin()
        }
    }

    fun result() = if (win) numbers.flatten().filter { !it.second }.sumOf { it.first } * lastNumber else 0

    private fun rowWin() = (0..4).any { row -> numbers[row].all { it.second } }
    private fun columnWin() = (0..4).any { column -> numbers.all { it[column].second } }
}

fun main() {
    fun part1(input: List<String>): Int {
        val numbers = mutableListOf<Int>()
        val cards = mutableListOf<Card>()

        val temp = mutableListOf<List<Int>>()
        input.forEachIndexed { index, line ->
            if (index == 0) {
                numbers.addAll(line.split(",").map { it.toInt() })
            } else {
                if (line.isNotEmpty()) {
                    val row = line.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
                    temp.add(row)

                } else if (temp.isNotEmpty()) {
                    val card = Card(temp)
                    cards.add(card)
                    temp.clear()
                }
            }
        }
        val card = Card(temp)
        cards.add(card)

        numbers.forEach { number ->
            if (cards.any { it.win }) return@forEach

            cards.forEach {
                it.mark(number)
            }
        }

        return cards.first { it.win }.result()
    }

    fun part2(input: List<String>): Int {
        val numbers = mutableListOf<Int>()
        val cards = mutableListOf<Card>()

        val temp = mutableListOf<List<Int>>()
        input.forEachIndexed { index, line ->
            if (index == 0) {
                numbers.addAll(line.split(",").map { it.toInt() })
            } else {
                if (line.isNotEmpty()) {
                    val row = line.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
                    temp.add(row)

                } else if (temp.isNotEmpty()) {
                    val card = Card(temp)
                    cards.add(card)
                    temp.clear()
                }
            }
        }
        val card = Card(temp)
        cards.add(card)

        val won = mutableListOf<Int>()
        numbers.forEach { number ->
            if (won.size == cards.size) return@forEach

            cards.forEach {
                it.mark(number)
            }

            cards.forEachIndexed { index, card ->
                if ((card.win) && (!won.contains(index))) won.add(index)
            }
        }

        return cards[won.last()].result()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
