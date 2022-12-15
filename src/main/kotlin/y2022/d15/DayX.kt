package y2022.d15

import BasePuzzle
import kotlin.math.abs
import kotlin.math.max

object Day15 : BasePuzzle {

    data class Sensor(
        val xSensor: Int,
        val ySensor: Int,
        val xBeacon: Int,
        val yBeacon: Int
    ) {
        fun distanceToBeacon() = (abs(xSensor - xBeacon) + abs(ySensor - yBeacon))
    }

    private val regex = "Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)".toRegex()

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(26, puzzle1(test, 10))
        println("Puzzle1: " + puzzle1(input, 2_000_000).toString())

        checkResponse(56_000_011L, puzzle2(test, 20))
        println("Puzzle2: " + puzzle2(input, 4_000_000).toString())

        println()
    }

    private fun puzzle1(data: List<String>, rowTest: Int): Int {
        val sensors = parseData(data)

        val checked = mutableSetOf<Int>()
        sensors.forEach { sensor ->
            val radius = sensor.distanceToBeacon()
            val distance = abs(rowTest - sensor.ySensor)
            val width = radius - distance
            checked.addAll(sensor.xSensor - width..sensor.xSensor + width)
        }
        val beaconsPos = sensors.filter { it.yBeacon == rowTest }.map { it.xBeacon }
        checked.removeAll(beaconsPos.toSet())

        return checked.size
    }

    private fun puzzle2(data: List<String>, maxSize: Int): Long {
        val sensors = parseData(data)

        val occupied = Array(maxSize + 1) { mutableListOf<IntRange>() }
        sensors.forEach { sensor ->
            val radius = sensor.distanceToBeacon()
            val start = (sensor.ySensor - radius).coerceAtLeast(0)
            val end = (sensor.ySensor + radius).coerceAtMost(maxSize)
            (start..end).forEach { row ->
                val distance = abs(row - sensor.ySensor)
                val width = radius - distance
                val xMin = (sensor.xSensor - width).coerceAtLeast(0)
                val xMax = (sensor.xSensor + width).coerceAtMost(maxSize)
                occupied[row].add(xMin..xMax)
            }
        }

        (0..maxSize).forEach { y ->
            val ranges = occupied[y].sortedBy { it.first }
            var i = 0
            while (i < ranges.size - 1) {
                var cur = ranges[i]
                i++
                var next = ranges[i]
                while (i < ranges.size - 1 && next.first < cur.last) {
                    cur = cur.first..max(cur.last, next.last)
                    i++
                    next = ranges[i]
                }
                if (cur.last < next.first) {
                    return ((cur.last + 1L) * 4_000_000) + y
                }
            }
        }

        return -1L
    }

    private fun parseData(data: List<String>) = data.map { line ->
        val (xSensor, ySensor, xBeacon, yBeacon) = regex.find(line)!!.groupValues.drop(1).map { it.toInt() }
        Sensor(xSensor, ySensor, xBeacon, yBeacon)
    }
}
