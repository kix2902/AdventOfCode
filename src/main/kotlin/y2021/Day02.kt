fun main() {
    fun part1(input: List<String>): Int {
        var pos = 0
        var depth = 0

        input.forEach {
            var parts = it.split(" ")
            when (parts[0]) {
                "forward" -> pos += parts[1].toInt()
                "down" -> depth += parts[1].toInt()
                "up" -> depth -= parts[1].toInt()
            }
        }

        return pos * depth
    }

    fun part2(input: List<String>): Int {
        var pos = 0
        var depth = 0
        var aim = 0

        input.forEach {
            var parts = it.split(" ")
            when (parts[0]) {
                "down" -> aim += parts[1].toInt()
                "up" -> aim -= parts[1].toInt()
                "forward" -> {
                    val value = parts[1].toInt()
                    pos += value
                    depth += aim * value
                }
            }
        }

        return pos * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
