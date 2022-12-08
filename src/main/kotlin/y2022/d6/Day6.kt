package y2022.d6

import BasePuzzle

object Day6 : BasePuzzle {

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(7, puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

        checkResponse(19, puzzle2(test))
        println("Puzzle2: " + puzzle2(input).toString())

        println()
    }

    private fun puzzle1(data: List<String>): Int {
        val line = data.first()
        return findPacket(line, 4)
    }

    private fun puzzle2(data: List<String>): Int {
        val line = data.first()
        return findPacket(line, 14)
    }

    private fun findPacket(data: String, size: Int): Int {
        var position = 0
        var found = false
        data.windowed(size, 1, false) { chars ->
            if (found) return@windowed
            if (chars.count().toLong() == chars.chars().distinct().count()) {
                found = true
            } else {
                position++
            }
        }

        return position + size
    }
}
