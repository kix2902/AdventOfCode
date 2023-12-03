package y2023.d2

import BasePuzzle
import kotlin.math.max

object Day2 : BasePuzzle {

    private data class Bucket(
        var blue: Int = 0,
        var red: Int = 0,
        var green: Int = 0,
    )

    private const val MAX_RED = 12
    private const val MAX_BLUE = 14
    private const val MAX_GREEN = 13

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(8, puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

        checkResponse(2286, puzzle2(test))
        println("Puzzle2: " + puzzle2(input).toString())

        println()
    }

    private fun puzzle1(data: List<String>): Int {
        val games = mutableMapOf<Int, Bucket>()
        data.forEach { line ->
            val id = line.substringAfter(" ").substringBefore(":").toInt()
            val groups = line.substringAfter(":").split(";")
            val bucket = Bucket()
            groups.forEach { group ->
                bucket.max(group)
            }
            games[id] = bucket
        }
        var sum = 0
        games.forEach { (id, bucket) ->
            if ((bucket.red <= MAX_RED) &&
                (bucket.blue <= MAX_BLUE) &&
                (bucket.green <= MAX_GREEN)
            ) {
                sum += id
            }
        }
        return sum
    }

    private fun puzzle2(data: List<String>): Int {
        val games = mutableMapOf<Int, Bucket>()
        data.forEach { line ->
            val id = line.substringAfter(" ").substringBefore(":").toInt()
            val groups = line.substringAfter(":").split(";")
            val bucket = Bucket()
            groups.forEach { group ->
                bucket.max(group)
            }
            games[id] = bucket
        }
        var sum = 0
        games.forEach { (_, bucket) ->
            sum += bucket.red * bucket.blue * bucket.green
        }
        return sum
    }

    private fun Bucket.max(group: String) {
        val items = group.trim().split(",")
        items.forEach { item ->
            val data = item.trim().split(" ")
            when (data[1]) {
                "red" -> this.red = max(red, data[0].toInt())
                "blue" -> this.blue = max(blue, data[0].toInt())
                "green" -> this.green = max(green, data[0].toInt())
            }
        }
    }
}
