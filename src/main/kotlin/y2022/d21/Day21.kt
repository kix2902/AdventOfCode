package y2022.d21

import BasePuzzle

object Day21 : BasePuzzle {

    private enum class Operation {
        SUM,
        MINUS,
        MULTIPLY,
        DIVIDE
    }

    private sealed class Monkey(open val name: String) {
        class Wait(override val name: String, val op1: String, val op2: String, val op: Operation) : Monkey(name)
        class Number(override val name: String, var number: Long) : Monkey(name)
    }

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(152L, puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

//        checkResponse(301L, puzzle2(test))
//        println("Puzzle2: " + puzzle2(input).toString())

        println()
    }

    private fun puzzle1(data: List<String>): Long {
        val monkeys = parseData(data)

        val root = monkeys.firstOrNull { it.name == "root" }
        val humnIndex = monkeys.indexOfFirst { it.name == "humn" }
        val humn = monkeys[humnIndex] as Monkey.Number
        return findValue(monkeys, root)
    }

    private fun puzzle2(data: List<String>): Long {
        val monkeys = parseData(data)

        // cannnot resolve it
        return 0L
    }

    private fun parseData(data: List<String>) = data.map { line ->
        val name = line.substringBefore(":")
        val job = line.substringAfter(": ")
        if (job.contains(" ")) {
            val parts = job.split(" ")
            val op = when (parts[1]) {
                "+" -> Operation.SUM
                "-" -> Operation.MINUS
                "*" -> Operation.MULTIPLY
                "/" -> Operation.DIVIDE
                else -> null
            }
            Monkey.Wait(name, parts[0], parts[2], op!!)
        } else {
            Monkey.Number(name, job.toLong())
        }
    }

    private fun findValue(monkeys: List<Monkey>, monkey: Monkey?): Long = when (monkey) {
        null -> 0L
        is Monkey.Number -> monkey.number
        is Monkey.Wait -> {
            val op1 = monkeys.firstOrNull { it.name == monkey.op1 }
            val op2 = monkeys.firstOrNull { it.name == monkey.op2 }
            when (monkey.op) {
                Operation.SUM -> findValue(monkeys, op1) + findValue(monkeys, op2)
                Operation.MINUS -> findValue(monkeys, op1) - findValue(monkeys, op2)
                Operation.MULTIPLY -> findValue(monkeys, op1) * findValue(monkeys, op2)
                Operation.DIVIDE -> findValue(monkeys, op1) / findValue(monkeys, op2)
            }
        }
    }
}
