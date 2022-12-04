import java.math.BigInteger
import java.util.*

fun main() {
    val open = arrayOf('(', '[', '{', '<')
    val close = arrayOf(')', ']', '}', '>')
    val relationOpenClose = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
    val relationCloseOpen = mapOf(')' to '(', ']' to '[', '}' to '{', '>' to '<')

    fun Char.isOpen(): Boolean {
        return open.contains(this)
    }

    fun Char.isClose(): Boolean {
        return close.contains(this)
    }

    fun part1(input: List<String>): Int {
        val illegal = mutableListOf<Char>()
        input.forEach { line ->
            val opened = ArrayDeque<Char>()
            line.forEach lineFor@{
                when {
                    it.isOpen() -> opened.push(it)
                    it.isClose() -> {
                        if (opened.pop() != relationCloseOpen[it]) {
                            illegal.add(it)
                            return@lineFor
                        }
                    }
                }
            }
        }

        return illegal.map {
            when (it) {
                ')' -> 3
                ']' -> 57
                '}' -> 1197
                '>' -> 25137
                else -> 0
            }
        }.sum()
    }

    fun part2(input: List<String>): String {
        val incomplete = mutableListOf<List<Char>>()
        input.forEach { line ->
            val opened = ArrayDeque<Char>()
            var illegal = false
            line.forEach lineFor@{
                when {
                    it.isOpen() -> opened.push(it)
                    it.isClose() -> {
                        if (opened.pop() != relationCloseOpen[it]) {
                            illegal = true
                            return@lineFor
                        }
                    }
                }
            }
            if (!illegal) incomplete.add(opened.map { relationOpenClose[it]!! }.toList())
        }

        val points = mutableListOf<BigInteger>()
        incomplete.forEach { line ->
            var total = 0.toBigInteger()
            line.forEach {
                total *= 5.toBigInteger()
                when (it) {
                    ')' -> total += 1.toBigInteger()
                    ']' -> total += 2.toBigInteger()
                    '}' -> total += 3.toBigInteger()
                    '>' -> total += 4.toBigInteger()
                }
            }
            points.add(total)
        }

        return points.sorted()[points.size / 2].toString()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 26397)
    check(part2(testInput) == "288957")

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}