package y2022.d14

import BasePuzzle

object Day14 : BasePuzzle {

    private val sandSource = Pair(500, 0)

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(24, puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

        checkResponse(93, puzzle2(test))
        println("Puzzle2: " + puzzle2(input).toString())

        println()
    }

    private fun puzzle1(data: List<String>): Int {
        val rocks = buildMap(data)
        val sands = mutableSetOf<Pair<Int, Int>>()

        val limitLeft = rocks.minOf { it.first }
        val limitRight = rocks.maxOf { it.first }
        val limitDown = rocks.maxOf { it.second }

        var offLimits = false
        while (!offLimits && (!sands.contains(sandSource))) {
            var sand = sandSource
            val map = rocks + sands
            var nxt = getNext(sand, map)
            while (!offLimits && (nxt != null) && !map.contains(nxt)) {
                sand = nxt
                nxt = getNext(sand, map)
                if ((nxt != null) && ((nxt.first < limitLeft) || (nxt.first > limitRight) || (nxt.second > limitDown))) {
                    offLimits = true
                }
            }
            sands += sand
        }
        return sands.size - 1
    }

    private fun puzzle2(data: List<String>): Int {
        val rocks = buildMap(data)
        val sands = mutableSetOf<Pair<Int, Int>>()

        val floor = rocks.maxOf { it.second } + 2

        while (!sands.contains(sandSource)) {
            var sand = sandSource
            val map = rocks + sands
            var nxt = getNext(sand, map, floor)
            while ((nxt != null) && !map.contains(nxt)) {
                sand = nxt
                nxt = getNext(sand, map, floor)
            }
            sands += sand
        }
        return sands.size
    }

    private fun buildMap(data: List<String>): Set<Pair<Int, Int>> {
        val rocks = mutableSetOf<Pair<Int, Int>>()
        data.map { line ->
            line.split(" -> ").map {
                val (x, y) = it.split(",").map { it.toInt() }
                Pair(x, y)
            }
        }.forEach { walls ->
            var src = walls[0]
            walls.drop(1).forEach { nxt ->
                if (src.first == nxt.first) {
                    rangeBetween(src.second, nxt.second).forEach {
                        rocks += Pair(src.first, it)
                    }
                } else {
                    rangeBetween(src.first, nxt.first).forEach {
                        rocks += Pair(it, src.second)
                    }
                }
                src = nxt
            }
        }
        return rocks
    }

    private fun getNext(sand: Pair<Int, Int>, map: Set<Pair<Int, Int>>, limitDown: Int = Int.MAX_VALUE): Pair<Int, Int>? {
        if (sand.second + 1 >= limitDown) return null
        val down = Pair(sand.first, sand.second + 1)
        val left = Pair(sand.first - 1, sand.second + 1)
        val right = Pair(sand.first + 1, sand.second + 1)
        if (!map.contains(down)) return down
        if (!map.contains(left)) return left
        if (!map.contains(right)) return right
        return null
    }

    private fun rangeBetween(a: Int, b: Int) = if (a > b) a downTo b else a..b
}
