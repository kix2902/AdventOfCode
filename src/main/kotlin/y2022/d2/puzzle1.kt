package y2022.d2

import BasePuzzle

object Puzzle1 : BasePuzzle {

    private val rock = arrayOf('A', 'X')
    private val paper = arrayOf('B', 'Y')
    private val scissor = arrayOf('C', 'Z')

    @JvmStatic
    fun main(args: Array<String>) {
        val data = readInput()

        val sum = data.sumOf { line ->
            val data = line.toCharArray()
            val pair = data[0] to data[2]
            return@sumOf value(data[2]) + score(pair)
        }

        println(sum.toString())
    }

    private fun value(movement: Char) = when (movement) {
        in rock -> 1
        in paper -> 2
        in scissor -> 3
        else -> 0
    }

    private fun score(pair: Pair<Char, Char>) = when (pair.first) {
        in rock -> when (pair.second) {
            in rock -> 3
            in paper -> 6
            in scissor -> 0
            else -> 0
        }

        in paper -> when (pair.second) {
            in rock -> 0
            in paper -> 3
            in scissor -> 6
            else -> 0
        }

        in scissor -> when (pair.second) {
            in rock -> 6
            in paper -> 0
            in scissor -> 3
            else -> 0
        }

        else -> 0
    }
}
