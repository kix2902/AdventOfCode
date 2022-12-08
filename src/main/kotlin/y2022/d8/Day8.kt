package y2022.d8

import BasePuzzle

object Day8 : BasePuzzle {

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(21, puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

        checkResponse(8, puzzle2(test))
        println("Puzzle2: " + puzzle2(input).toString())

        println()
    }

    private fun puzzle1(data: List<String>): Int {
        val border = ((data.size - 1) * 2) + ((data.first().length - 1) * 2)

        var internal = 0
        data.forEachIndexed rows@{ row, line ->
            if ((row == 0) || (row == data.size - 1)) return@rows
            line.forEachIndexed columns@{ col, char ->
                if ((col == 0) || (col == line.length - 1)) return@columns

                val visible = toEdge(data, row, col).any { view ->
                    view.all { char > it }
                }

                if (visible) internal++
            }
        }

        return internal + border
    }

    private fun puzzle2(data: List<String>): Int {
        var total = 0
        data.forEachIndexed rows@{ row, line ->
            if ((row == 0) || (row == data.size - 1)) return@rows
            line.forEachIndexed columns@{ col, char ->
                if ((col == 0) || (col == line.length - 1)) return@columns

                val temp = toEdge(data, row, col).fold(1) { acc, view ->
                    val distance = view.indexOfFirst { char <= it }
                    acc * (if (distance < 0) view.size else distance + 1)
                }

                if (temp > total) total = temp
            }
        }

        return total
    }

    private fun toEdge(data: List<String>, row: Int, col: Int) = listOf(
        (0 until row).map { data[it][col] }.reversed(),
        (row + 1 until data.size).map { data[it][col] },
        (0 until col).map { data[row][it] }.reversed(),
        (col + 1 until data.first().length).map { data[row][it] }
    )
}
