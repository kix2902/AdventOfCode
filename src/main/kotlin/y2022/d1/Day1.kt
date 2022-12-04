package y2022.d1

import BasePuzzle
import java.math.BigInteger

object Day1 : BasePuzzle {

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(24000, puzzle1(test).toInt())
        println("Puzzle1: " + puzzle1(input).toString())

        checkResponse(45000, puzzle2(test).toInt())
        println("Puzzle2: " + puzzle2(input).toString())

        println()
    }

    private fun puzzle1(data: List<String>): BigInteger {
        var pos = 0
        val list = mutableListOf<BigInteger>()
        data.forEach { line ->
            if (line.isBlank()) {
                pos++
            } else {
                val num = BigInteger(line)
                val value = (list.getOrNull(pos) ?: BigInteger.ZERO) + num
                if (list.size == pos) {
                    list.add(value)
                } else {
                    list[pos] = value
                }
            }
        }
        return list.max()
    }

    private fun puzzle2(data: List<String>): BigInteger {
        var pos = 0
        val list = mutableListOf<BigInteger>()
        data.forEach { line ->
            if (line.isBlank()) {
                pos++
            } else {
                val num = BigInteger(line)
                val value = (list.getOrNull(pos) ?: BigInteger.ZERO) + num
                if (list.size == pos) {
                    list.add(value)
                } else {
                    list[pos] = value
                }
            }
        }
        return list.sortedDescending().take(3).sumOf { it }
    }
}
