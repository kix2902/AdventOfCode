fun main() {
    fun part1(input: List<String>): Int {
        val pos = arrayOf(input[0].substringAfterLast(" ").toInt(), input[1].substringAfterLast(" ").toInt())
        val points = arrayOf(0, 0)

        var turn = 0
        var rolls = 0
        var number = 0
        do {
            var totalNum = 0
            repeat(3) {
                rolls++
                number = (number + 1).mod(100)
                totalNum += if (number > 0) number else 100
            }

            pos[turn] = (pos[turn] + totalNum).mod(10)
            points[turn] += if (pos[turn] > 0) pos[turn] else 10

            turn = (turn + 1).mod(2)
        } while (points.all { it < 1000 })

        return points.minOf { it } * rolls
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day21_test")
    check(part1(testInput) == 739785)

    val input = readInput("Day21")
    println(part1(input))
}