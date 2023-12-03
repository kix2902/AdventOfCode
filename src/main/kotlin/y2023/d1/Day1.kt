package y2023.d1

import BasePuzzle

object Day1 : BasePuzzle {

    private val REGEX_NUMBERS = "(zero|one|two|three|four|five|six|seven|eight|nine|0|1|2|3|4|5|6|7|8|9)".toRegex()
    private val REGEX_NUMBERS_R = ("(" + "zero|one|two|three|four|five|six|seven|eight|nine|0|1|2|3|4|5|6|7|8|9".reversed() + ")").toRegex()

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

//        checkResponse(142, puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

        checkResponse(281, puzzle2(test))
        println("Puzzle2: " + puzzle2(input).toString())

        println()
    }

    private fun puzzle1(data: List<String>): Int {
        var sum = 0
        data.forEach { line ->
            val c1 = line.first { it.isDigit() }
            val c2 = line.last { it.isDigit() }
            sum += "$c1$c2".toInt()
        }
        return sum
    }

    private fun puzzle2(data: List<String>): Int {
        var sum = 0
        data.forEach { line ->
            val c1 = REGEX_NUMBERS.findAll(line.lowercase()).first()
            val c2 = REGEX_NUMBERS_R.findAll(line.reversed().lowercase()).first()
            sum += "${c1.groupValues[0].toNumber()}${c2.groupValues[0].reversed().toNumber()}".toInt()
        }
        return sum
    }

    private fun String.toNumber() = when (this) {
        "0", "zero" -> '0'
        "1", "one" -> '1'
        "2", "two" -> '2'
        "3", "three" -> '3'
        "4", "four" -> '4'
        "5", "five" -> '5'
        "6", "six" -> '6'
        "7", "seven" -> '7'
        "8", "eight" -> '8'
        "9", "nine" -> '9'
        else -> '0'
    }
}
