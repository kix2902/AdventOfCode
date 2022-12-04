package y2022.d4

import BasePuzzle
import java.util.*

object Puzzle2 : BasePuzzle {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = readInput()

        var overlap = 0
        data.forEach { line ->
            val parts = line.split(",")
            val s1 = parts[0].split("-").let { it[0].toInt()..it[1].toInt() }.toList()
            val s2 = parts[1].split("-").let { it[0].toInt()..it[1].toInt() }.toList()

            if (!Collections.disjoint(s1, s2)) overlap++
        }

        println(overlap.toString())
    }
}
