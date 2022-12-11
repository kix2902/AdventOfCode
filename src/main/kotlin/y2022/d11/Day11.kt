package y2022.d11

import BasePuzzle

object Day11 : BasePuzzle {

    private data class Monkey(val stack: MutableList<Long>, val operation: String, val test: Long, val testTrue: Int, val testFalse: Int) {
        var inspected = 0L
    }

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(10605L, puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

        checkResponse(2713310158L, puzzle2(test))
        println("Puzzle2: " + puzzle2(input).toString())

        println()
    }

    private fun puzzle1(data: List<String>): Long {
        val monkeys = parseMonkeys(data)
        monkeys.iterateMonkeys(20)
        return monkeys.calculateResult()
    }

    private fun puzzle2(data: List<String>): Long {
        val monkeys = parseMonkeys(data)
        val calm = lcm(monkeys.map { it.test })
        monkeys.iterateMonkeys(10000, calm)
        return monkeys.calculateResult()
    }

    private fun parseMonkeys(data: List<String>): ArrayList<Monkey> {
        val monkeys = arrayListOf<Monkey>()

        var tempIndex: Int? = null
        var tempItems: List<Long>? = null
        var tempOperation: String? = null
        var tempTest: Long? = null
        var tempTestTrue: Int? = null
        var tempTestFalse: Int? = null
        data.forEach { line ->
            if (line.trim().startsWith("Monkey")) {
                tempIndex = line.substringAfter(" ").substringBefore(":").toInt()
            } else if (line.trim().startsWith("Starting items")) {
                tempItems = line.substringAfter(":").split(",").map { it.trim().toLong() }
            } else if (line.trim().startsWith("Operation")) {
                tempOperation = line.substringAfter("=").trim()
            } else if (line.trim().startsWith("Test")) {
                tempTest = line.substringAfter("by").trim().toLong()
            } else if (line.trim().startsWith("If true")) {
                tempTestTrue = line.substringAfter("monkey").trim().toInt()
            } else if (line.trim().startsWith("If false")) {
                tempTestFalse = line.substringAfter("monkey").trim().toInt()
            } else if (line.isEmpty()) {
                val monkey = Monkey(tempItems!!.toMutableList(), tempOperation!!, tempTest!!, tempTestTrue!!, tempTestFalse!!)
                monkeys.add(tempIndex!!, monkey)
            }
        }

        if (tempIndex != null) {
            val monkey = Monkey(tempItems!!.toMutableList(), tempOperation!!, tempTest!!, tempTestTrue!!, tempTestFalse!!)
            monkeys.add(tempIndex!!, monkey)
        }

        return monkeys
    }

    private fun MutableList<Monkey>.iterateMonkeys(repetitions: Int, calmValue: Long? = null) {
        repeat(repetitions) {
            forEach { monkey ->
                while (monkey.stack.isNotEmpty()) {
                    var item = monkey.stack.removeAt(0)
                    val opParts = monkey.operation.split(" ")
                    item = when (opParts[1]) {
                        "+" -> parseOp(item, opParts[0]) + parseOp(item, opParts[2])
                        "-" -> parseOp(item, opParts[0]) - parseOp(item, opParts[2])
                        "*" -> parseOp(item, opParts[0]) * parseOp(item, opParts[2])
                        "/" -> parseOp(item, opParts[0]) / parseOp(item, opParts[2])
                        else -> 0
                    }
                    item = if (calmValue == null) item / 3L else item % calmValue
                    val testResult = if ((item % monkey.test) == 0L) monkey.testTrue else monkey.testFalse
                    this[testResult].stack.add(item)
                    monkey.inspected++
                }
            }
        }
    }

    private fun parseOp(old: Long, text: String) = if (text == "old") old else text.toLong()

    private fun MutableList<Monkey>.calculateResult(): Long {
        return this.sortedByDescending { it.inspected }.take(2).fold(1L) { acc, monkey -> acc * monkey.inspected }
    }

    private fun lcm(numbers: List<Long>, idx: Int = 0): Long {
        if (idx == numbers.lastIndex) {
            return numbers[idx]
        }
        val a: Long = numbers[idx]
        val b: Long = lcm(numbers, idx + 1)
        return a * b / gcd(a, b)

    }

    private fun gcd(a: Long, b: Long): Long {
        return if (b == 0L) a else gcd(b, a % b)
    }
}
