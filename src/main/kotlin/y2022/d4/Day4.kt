package y2022.d4

import BasePuzzle
import java.util.*

object Day4 : BasePuzzle {

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(2, puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

        checkResponse(4, puzzle2(test))
        println("Puzzle2: " + puzzle2(input).toString())

        println()
    }

    private fun puzzle1(data: List<String>): Int {
        return data.count { line ->
            val parts = line.split(",")
            val s1 = parts[0].split("-").let { it[0].toInt()..it[1].toInt() }.toList()
            val s2 = parts[1].split("-").let { it[0].toInt()..it[1].toInt() }.toList()

            ((s1.containsAll(s2)) || (s2.containsAll(s1)))
        }
    }

    private fun puzzle2(data: List<String>): Int {
        return data.count { line ->
            val parts = line.split(",")
            val s1 = parts[0].split("-").let { it[0].toInt()..it[1].toInt() }.toList()
            val s2 = parts[1].split("-").let { it[0].toInt()..it[1].toInt() }.toList()

            (!Collections.disjoint(s1, s2))
        }
    }
}
