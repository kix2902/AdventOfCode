package y2022.d20

import BasePuzzle

object Day20 : BasePuzzle {

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(3L, puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

        checkResponse(1623178306L, puzzle2(test))
        println("Puzzle2: " + puzzle2(input).toString())

        println()
    }

    private fun puzzle1(data: List<String>): Long {
        val original = data.mapIndexed { i, value -> i to value.toLong() }
        val mixed = original.toMutableList()

        original.forEach { value ->
            val index = mixed.indexOf(value)
            mixed.removeAt(index)

            val newIndex = (index + value.second).mod(mixed.size)
            mixed.add(newIndex, value)
        }

        val zeroIndex = mixed.indexOfFirst { it.second == 0L }
        val i1000 = mixed.valueAt(zeroIndex, 1000)
        val i2000 = mixed.valueAt(zeroIndex, 2000)
        val i3000 = mixed.valueAt(zeroIndex, 3000)

        return i1000 + i2000 + i3000
    }

    private fun puzzle2(data: List<String>): Long {
        val original = data.mapIndexed { i, value -> i to value.toLong() * 811589153L }
        val mixed = original.toMutableList()

        repeat(10) {
            original.forEach { value ->
                val index = mixed.indexOf(value)
                mixed.removeAt(index)

                val newIndex = (index + value.second).mod(mixed.size)
                mixed.add(newIndex, value)
            }
        }

        val zeroIndex = mixed.indexOfFirst { it.second == 0L }
        val i1000 = mixed.valueAt(zeroIndex, 1000)
        val i2000 = mixed.valueAt(zeroIndex, 2000)
        val i3000 = mixed.valueAt(zeroIndex, 3000)

        return i1000 + i2000 + i3000
    }

    private fun List<Pair<Int, Long>>.valueAt(zeroIndex: Int, shift: Int) = get((zeroIndex + shift) % size).second
}
