package y2022.d4

import BasePuzzle

object Puzzle1 : BasePuzzle {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = readInput()

        var contained = 0
        data.forEach { line ->
            val parts = line.split(",")
            val s1 = parts[0].split("-").let { it[0].toInt()..it[1].toInt() }.toList()
            val s2 = parts[1].split("-").let { it[0].toInt()..it[1].toInt() }.toList()

            if (s1.containsAll(s2)) {
                contained++
            } else if (s2.containsAll(s1)) {
                contained++
            }
        }

        println(contained.toString())
    }
}
