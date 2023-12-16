package y2023.d15

import BasePuzzle

object Day15 : BasePuzzle {

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(1320, puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

//        checkResponse(0, puzzle2(test))
//        println("Puzzle2: " + puzzle2(input).toString())

        println()
    }

    private fun puzzle1(data: List<String>): Int {
        var acum = 0
        data[0].split(",").forEach { str ->
            var current = 0
            str.forEach { c ->
                current += c.code
                current *= 17
                current = current.rem(256)
            }
            acum += current
        }
        return acum
    }

    private fun puzzle2(data: List<String>): Int {
        return 0
    }
}
