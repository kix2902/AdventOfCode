fun main() {
    fun part1(input: List<String>): Int {
        var total = 0
        input.forEachIndexed { i, num ->
            when {
                i == 0 -> total = 0
                num.toInt() > input[i - 1].toInt() -> total++
            }
        }
        return total
    }

    fun part2(input: List<String>): Int {
        var total = 0
        val nums = input.map { it.toInt() }
        for (index in 3 until nums.size) {
            val old = nums[index - 3] + nums[index - 2] + nums[index - 1]
            val new = nums[index - 2] + nums[index - 1] + nums[index]
            if (new > old) total++
        }
        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
