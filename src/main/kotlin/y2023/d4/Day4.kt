package y2023.d4

import BasePuzzle
import kotlin.math.pow

object Day4 : BasePuzzle {

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(13, puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

        checkResponse(30, puzzle2(test))
        println("Puzzle2: " + puzzle2(input).toString())

        println()
    }

    private fun puzzle1(data: List<String>): Int {
        val matches = mutableListOf<Int>()
        data.forEach { line ->
            val winning = line.substringAfter(":").substringBefore("|").trim().split("\\s+".toRegex()).map { it.toInt() }
            val numbers = line.substringAfter("|").trim().split("\\s+".toRegex()).map { it.toInt() }
            matches.add(numbers.count { it in winning })
        }
        matches.removeIf { it == 0 }
        var acc = 0
        matches.forEach { num ->
            acc += 2.0.pow((num - 1).toDouble()).toInt()
        }
        return acc
    }

    private fun puzzle2(data: List<String>): Int {
        val card = mutableMapOf<Int, Int>() // Id - Copies
        data.forEach { line ->
            val id = line.substringAfter(" ").substringBefore(":").trim().toInt()
            var currentCopies = card[id] ?: 0
            currentCopies += 1
            card[id] = currentCopies

            val winning = line.substringAfter(":").substringBefore("|").trim().split("\\s+".toRegex()).map { it.toInt() }
            val numbers = line.substringAfter("|").trim().split("\\s+".toRegex()).map { it.toInt() }
            val winners = numbers.count { it in winning }

            if (winners > 0) {
                (id + 1..id + winners).forEach { copy ->
                    val current = card[copy] ?: 0
                    card[copy] = current + currentCopies
                }
            }
        }
        return card.entries.sumOf { it.value }
    }
}
