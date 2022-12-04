import java.lang.Math.pow

fun main() {
    fun part1(input: List<String>): Int {
        var result = ""
        for (index in input.first().indices) {
            var total0 = 0
            var total1 = 0
            input.forEach {
                when (it[index]) {
                    '0' -> total0++
                    '1' -> total1++
                }
            }
            if (total1 > total0) result += "1" else result += "0"
        }
        val num = Integer.parseInt(result, 2)
        val inverse = pow(2.0, input.first().length.toDouble()) - 1 - num
        return (num * inverse).toInt()
    }

    fun part2(input: List<String>): Int {
        var oxygen = input.toList()
        var co2 = input.toList()

        var index = 0
        while ((oxygen.size > 1) || (co2.size > 1)) {
            if (oxygen.size > 1) {
                val oxygenMapCount = oxygen.map { it[index] }.groupingBy { it }.eachCount()
                val maxValue = oxygenMapCount.values.maxOrNull()
                val maxEntries = oxygenMapCount.filterValues { it == maxValue }
                val selected = if (maxEntries.size > 1) '1' else maxEntries.keys.first()
                oxygen = oxygen.filter { it[index] == selected }
            }
            if (co2.size > 1) {
                val co2MapCount = co2.map { it[index] }.groupingBy { it }.eachCount()
                val minValue = co2MapCount.values.minOrNull()
                val minEntries = co2MapCount.filterValues { it == minValue }
                val selected = if (minEntries.size > 1) '0' else minEntries.keys.first()
                co2 = co2.filter { it[index] == selected }
            }
            index++
        }

        val oxygenNum = Integer.parseInt(oxygen.first(), 2)
        val co2Num = Integer.parseInt(co2.first(), 2)
        return oxygenNum * co2Num
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
