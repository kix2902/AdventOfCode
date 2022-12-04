package y2022.d2

import BasePuzzle

object Day2 : BasePuzzle {

    private const val rock = 'A'
    private const val paper = 'B'
    private const val scissor = 'C'

    private const val rock2 = 'X'
    private const val paper2 = 'Y'
    private const val scissor2 = 'Z'

    private const val lose = 'X'
    private const val draw = 'Y'
    private const val win = 'Z'

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(15, puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

        checkResponse(12, puzzle2(test))
        println("Puzzle2: " + puzzle2(input).toString())

        println()
    }

    private fun puzzle1(data: List<String>): Int {
        return data.sumOf { line ->
            val chars = line.toCharArray()
            val pair = chars[0] to chars[2]
            return@sumOf value(chars[2]) + scorePair(pair)
        }
    }

    private fun puzzle2(data: List<String>): Int {
        return data.sumOf { line ->
            val chars = line.toCharArray()
            val pair = chars[0] to chars[2]
            return@sumOf value(nextMovement(pair)) + scoreResult(chars[2])
        }
    }

    private fun value(movement: Char) = when (movement) {
        rock, rock2 -> 1
        paper, paper2 -> 2
        scissor, scissor2 -> 3
        else -> 0
    }

    private fun scorePair(pair: Pair<Char, Char>) = when (pair) {
        Pair(rock, rock2),
        Pair(paper, paper2),
        Pair(scissor, scissor2) -> 3

        Pair(rock, scissor2),
        Pair(paper, rock2),
        Pair(scissor, paper2) -> 0

        Pair(rock, paper2),
        Pair(paper, scissor2),
        Pair(scissor, rock2) -> 6

        else -> 0
    }

    private fun scoreResult(result: Char) = when (result) {
        lose -> 0
        draw -> 3
        win -> 6
        else -> 0
    }

    private fun nextMovement(pair: Pair<Char, Char>) = when (pair) {
        Pair(rock, lose),
        Pair(scissor, draw),
        Pair(paper, win) -> scissor

        Pair(paper, lose),
        Pair(rock, draw),
        Pair(scissor, win) -> rock

        Pair(scissor, lose),
        Pair(paper, draw),
        Pair(rock, win) -> paper

        else -> pair.first
    }
}
