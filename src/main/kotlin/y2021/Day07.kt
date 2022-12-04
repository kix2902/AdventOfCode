import kotlin.math.absoluteValue

fun main() {
    fun med(list: List<Int>) = list.sorted().let {
        if (it.size % 2 == 0)
            (it[it.size / 2] + it[(it.size - 1) / 2]) / 2
        else
            it[it.size / 2]
    }

    fun seriesSum(n: Int) = n * (n + 1) / 2

    fun part1(input: List<String>): Int {
        val nums = input[0].split(",").map { it.toInt() }
        val avg = med(nums)
        return nums.sumOf { (it - avg).absoluteValue }
    }

    fun part2(input: List<String>): Int {
        val nums = input[0].split(",").map { it.toInt() }
        val maxPos = nums.maxOrNull()!!
        val minPos = nums.minOrNull()!!
        return (minPos..maxPos).map { pos ->
            nums.sumOf { num -> seriesSum((num - pos).absoluteValue) }
        }.minOrNull()!!
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}