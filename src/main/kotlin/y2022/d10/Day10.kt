package y2022.d10

import BasePuzzle
import kotlin.math.abs

object Day10 : BasePuzzle {

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(13140, puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

        println("Puzzle2:")
        puzzle2(test)
        puzzle2(input)

        println()
    }

    private fun puzzle1(data: List<String>): Int {
        var cycle = 0
        var value = 1
        var sumSignal = 0
        var nextSumCycle = 20
        val maxSumCycle = 220
        data.forEach { line ->
            if (line.startsWith("noop")) {
                cycle++
                if (cycle == nextSumCycle) {
                    sumSignal += cycle * value
                    nextSumCycle += 40
                }

            } else if (line.startsWith("addx")) {
                val toAdd = line.substringAfter(" ").toInt()
                cycle++
                if (cycle == nextSumCycle) {
                    sumSignal += cycle * value
                    nextSumCycle += 40
                }

                cycle++
                if ((cycle == nextSumCycle) && (cycle <= maxSumCycle)) {
                    sumSignal += cycle * value
                    nextSumCycle += 40
                }

                value += toAdd
            }
        }

        return sumSignal
    }

    private fun puzzle2(data: List<String>) {
        var cycle = 0
        var value = 1
        val pixels = (0..6).associateWith { "" }.toMutableMap()
        var row = 0
        data.forEach { line ->
            if (line.startsWith("noop")) {
                pixels[row] += if (abs(cycle - value) <= 1) "#" else " "
                cycle++

                if ((cycle == 40)) {
                    cycle = 0
                    row++
                }

            } else if (line.startsWith("addx")) {
                val toAdd = line.substringAfter(" ").toInt()
                pixels[row] += if (abs(cycle - value) <= 1) "#" else " "
                cycle++

                if ((cycle == 40)) {
                    cycle = 0
                    row++
                }

                pixels[row] += if (abs(cycle - value) <= 1) "#" else " "
                cycle++

                if ((cycle == 40)) {
                    cycle = 0
                    row++
                }

                value += toAdd
            }
        }

        println(pixels.values.joinToString("\n"))
    }
}
