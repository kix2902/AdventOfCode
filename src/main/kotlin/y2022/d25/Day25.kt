package y2022.d25

import BasePuzzle
import kotlin.math.pow

object Day25 : BasePuzzle {

    private val base = 5

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse("2=-1=0", puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

        println()
    }

    private fun puzzle1(data: List<String>): String {
        val sum = data.map { snafuToDec(it) }.sum()
        return decToSnafu(sum)
    }

    private fun snafuToDec(value: String) = value.reversed().mapIndexed { index, char ->
        base.toDouble().pow(index.toDouble()).toLong() * charToValue(char)
    }.sum()

    private fun charToValue(char: Char) = "=-012".indexOf(char) - 2

    private fun decToSnafu(value: Long): String {
        if (value == 0L) return "0"
        var num = value
        val result = StringBuilder()
        while (num > 0L) {
            val temp = num % base
            if (temp > 2) {
                result.append(if (temp == 3L) "=" else "-")
                num += base
            } else {
                result.append(temp.toString())
            }
            num /= base
        }
        return result.reversed().toString()
    }
}
