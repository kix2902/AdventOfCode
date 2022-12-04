package y2022.d3

import BasePuzzle

object Day3 : BasePuzzle {

    private const val aCode = 'a'.code
    private const val ACode = 'A'.code

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(157, puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

        checkResponse(70, puzzle2(test))
        println("Puzzle2: " + puzzle2(input).toString())

        println()
    }

    private fun puzzle1(data: List<String>): Int {
        return data.sumOf { line ->
            val packs = line.chunked(line.length / 2)
            val dup = packs[0].filter { it in packs[1] }.toCharArray().first()
            if (dup.isUpperCase()) {
                dup.code - ACode + 27
            } else {
                dup.code - aCode + 1
            }
        }
    }

    private fun puzzle2(data: List<String>): Int {
        return data.windowed(3, 3) { packs ->
            val dup = packs[0].filter { (it in packs[1]) && (it in packs[2]) }.toCharArray().first()
            if (dup.isUpperCase()) {
                dup.code - ACode + 27
            } else {
                dup.code - aCode + 1
            }
        }.sum()
    }
}
