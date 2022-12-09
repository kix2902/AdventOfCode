package y2022.d9

import BasePuzzle
import kotlin.math.abs

object Day9 : BasePuzzle {

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(13, puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

        checkResponse(1, puzzle2(test))
        println("Puzzle2: " + puzzle2(input).toString())

        println()
    }

    private fun puzzle1(data: List<String>): Int {
        var head = Pair(0, 0)
        var tail = Pair(0, 0)
        val visited = mutableSetOf<Pair<Int, Int>>()

        data.forEach { line ->
            val parts = line.split(" ")

            val direction = parts[0].first()
            val repetitions = parts[1].toInt()
            repeat(repetitions) {
                when (direction) {
                    'U' -> head = head.copy(first = head.first + 1)
                    'D' -> head = head.copy(first = head.first - 1)
                    'R' -> head = head.copy(second = head.second + 1)
                    'L' -> head = head.copy(second = head.second - 1)
                }

                if (!isAround(head, tail)) {
                    when (direction) {
                        'U' -> tail = tail.copy(first = head.first - 1, second = head.second)
                        'D' -> tail = tail.copy(first = head.first + 1, second = head.second)
                        'R' -> tail = tail.copy(first = head.first, second = head.second - 1)
                        'L' -> tail = tail.copy(first = head.first, second = head.second + 1)
                    }
                }

                visited.add(tail)
            }
        }

        return visited.size
    }

    private fun puzzle2(data: List<String>): Int {
        val pairs = (1..10).map { Pair(0, 0) }.toMutableList()
        val visited = mutableSetOf<Pair<Int, Int>>()

        data.forEach { line ->
            val parts = line.split(" ")

            val direction = parts[0].first()
            val repetitions = parts[1].toInt()
            repeat(repetitions) {
                when (direction) {
                    'U' -> pairs[0] = pairs[0].copy(first = pairs[0].first + 1)
                    'D' -> pairs[0] = pairs[0].copy(first = pairs[0].first - 1)
                    'R' -> pairs[0] = pairs[0].copy(second = pairs[0].second + 1)
                    'L' -> pairs[0] = pairs[0].copy(second = pairs[0].second - 1)
                }

                (1 until pairs.size).forEach { index ->
                    if (!isAround(pairs[index - 1], pairs[index])) {
                        val dX = pairs[index - 1].first - pairs[index].first
                        val dY = pairs[index - 1].second - pairs[index].second
                        pairs[index] = Pair(
                            first = pairs[index].first + dX.coerceIn(-1, 1),
                            second = pairs[index].second + dY.coerceIn(-1, 1)
                        )
                    }
                }

                visited.add(pairs.last())
            }
        }

        return visited.size
    }

    private fun isAround(a: Pair<Int, Int>, b: Pair<Int, Int>) = (abs(a.first - b.first) <= 1) && (abs(a.second - b.second) <= 1)
}
