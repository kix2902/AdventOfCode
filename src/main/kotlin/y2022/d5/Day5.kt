package y2022.d5

import BasePuzzle

object Day5 : BasePuzzle {

    private val regexMoves = "move (\\d+) from (\\d+) to (\\d+)".toRegex()

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse("CMZ", puzzle1(test))
        println("Puzzle1: " + puzzle1(input))

        checkResponse("MCD", puzzle2(test))
        println("Puzzle2: " + puzzle2(input))

        println()
    }

    private fun puzzle1(data: List<String>): String {
        val indexCut = data.indexOfFirst { it.isEmpty() }
        val initList = data.subList(0, indexCut)
        val stacks = buildStacks(initList)

        val moves = data.subList(indexCut + 1, data.size)

        moves.forEach { line ->
            val match = regexMoves.find(line)
            if (match != null) {
                val (move, from, to) = match.destructured

                repeat(move.toInt()) {
                    val temp = stacks[from.toInt() - 1].removeLast()
                    stacks[to.toInt() - 1].addLast(temp)
                }
            }
        }

        return stacks.map { it.removeLastOrNull() }.joinToString("")
    }

    private fun puzzle2(data: List<String>): String {
        val indexCut = data.indexOfFirst { it.isEmpty() }
        val initList = data.subList(0, indexCut)
        val stacks = buildStacks(initList)

        val moves = data.subList(indexCut + 1, data.size)

        moves.forEach { line ->
            val match = regexMoves.find(line)
            if (match != null) {
                val (move, from, to) = match.destructured

                var temp = ""
                repeat(move.toInt()) {
                    temp = stacks[from.toInt() - 1].removeLast() + temp
                }
                temp.forEach { char ->
                    stacks[to.toInt() - 1].addLast(char)
                }
            }
        }

        return stacks.map { it.removeLastOrNull() }.joinToString("")
    }

    private fun buildStacks(data: List<String>): List<ArrayDeque<Char>> {
        val totalStacks = data.last().split(" ").count { it.isNotEmpty() }
        val stacks = (1..totalStacks).map { ArrayDeque<Char>() }

        data.dropLast(1).reversed().forEach { line ->
            var stack = 0
            var temp = line
            while (temp.isNotEmpty()) {
                val item = temp.take(4)
                val char = item[1]
                if (char != ' ') stacks[stack].addLast(char)
                temp = temp.drop(4)
                stack++
            }
        }

        return stacks
    }
}
