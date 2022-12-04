fun main() {
    fun part1(input: List<String>): Int {
        val maxColumns = input.first().length
        val maxRows = input.size

        var east = mutableListOf<Pair<Int, Int>>()
        var south = mutableListOf<Pair<Int, Int>>()
        input.forEachIndexed { row, line ->
            line.forEachIndexed { column, char ->
                if (char == '>') {
                    east.add(Pair(row, column))
                } else if (char == 'v') {
                    south.add(Pair(row, column))
                }
            }
        }

        var moves = 0
        var repeat = 0
        do {
            moves = 0
            val temp = mutableListOf<Pair<Int, Int>>()
            east.forEach {
                val nextColumn = (it.second + 1).mod(maxColumns)
                val nextPos = Pair(it.first, nextColumn)
                if ((!east.contains(nextPos)) && (!south.contains(nextPos))) {
                    temp.add(nextPos)
                    moves++
                } else {
                    temp.add(it)
                }
            }
            east = temp.toMutableList()

            temp.clear()
            south.forEach {
                val nextRow = (it.first + 1).mod(maxRows)
                val nextPos = Pair(nextRow, it.second)
                if ((!east.contains(nextPos)) && (!south.contains(nextPos))) {
                    temp.add(nextPos)
                    moves++
                } else {
                    temp.add(it)
                }
            }
            south = temp.toMutableList()

            repeat++
        } while (moves > 0)

        return repeat
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day25_test")
    check(part1(testInput) == 58)

    val input = readInput("Day25")
    println(part1(input))
}