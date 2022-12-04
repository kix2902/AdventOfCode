package y2022.d3

import BasePuzzle

object Puzzle1 : BasePuzzle {

    private const val aCode = 'a'.code
    private const val ACode = 'A'.code

    @JvmStatic
    fun main(args: Array<String>) {
        val data = readInput()

        var sum = 0
        data.forEach { line ->
            val packs = line.chunked(line.length / 2)
            val dup = packs[0].filter { it in packs[1] }.toCharArray().first()
            sum += if (dup.isUpperCase()) {
                dup.code - ACode + 27
            } else {
                dup.code - aCode + 1
            }
        }

        println(sum.toString())
    }
}
