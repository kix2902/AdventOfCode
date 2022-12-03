package y2022.d2

import BasePuzzle

object Puzzle2 : BasePuzzle {

    private val rock = 'A'
    private val paper = 'B'
    private val scissor = 'C'

    private val lose = 'X'
    private val draw = 'Y'
    private val win = 'Z'

    @JvmStatic
    fun main(args: Array<String>) {
        val data = readInput()

        val sum = data.sumOf { line ->
            val data = line.toCharArray()
            val pair = data[0] to data[2]
            return@sumOf value(movement(pair)) + score(data[2])
        }

        println(sum.toString())
    }

    private fun movement(pair: Pair<Char, Char>) = when (pair.second) {
        lose -> when (pair.first) {
            rock -> scissor
            paper -> rock
            scissor -> paper
            else -> pair.first
        }

        draw -> {
            pair.first
        }

        win -> when (pair.first) {
            rock -> paper
            paper -> scissor
            scissor -> rock
            else -> pair.first
        }

        else -> pair.first
    }

    private fun value(movement: Char) = when (movement) {
        rock -> 1
        paper -> 2
        scissor -> 3
        else -> 0
    }

    private fun score(result: Char) = when (result) {
        lose -> 0
        draw -> 3
        win -> 6
        else -> 0
    }
}
