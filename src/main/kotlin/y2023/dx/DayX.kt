package y2023.dx

import BasePuzzle

object DayX : BasePuzzle {

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(0, puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

        checkResponse(0, puzzle2(test))
        println("Puzzle2: " + puzzle2(input).toString())

        println()
    }

    private fun puzzle1(data: List<String>): Int {
        return 0
    }

    private fun puzzle2(data: List<String>): Int {
        return 0
    }
}
