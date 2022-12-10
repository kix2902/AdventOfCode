package y2022.d10

import BasePuzzle

object Day10 : BasePuzzle {

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(13140, puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

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
}
