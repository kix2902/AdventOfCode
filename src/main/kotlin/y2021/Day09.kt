fun main() {
    fun toInt(c: Char?): Int? {
        if (c == null) return null
        return Character.getNumericValue(c)
    }

    fun part1(input: List<String>): Int {
        val lows = mutableListOf<Int>()
        input.forEachIndexed { row, line ->
            line.forEachIndexed { column, value ->
                val intValue = toInt(value)!!
                val up = toInt(input.getOrNull(row - 1)?.getOrNull(column)) ?: 10
                val down = toInt(input.getOrNull(row + 1)?.getOrNull(column)) ?: 10
                val left = toInt(input.getOrNull(row)?.getOrNull(column - 1)) ?: 10
                val right = toInt(input.getOrNull(row)?.getOrNull(column + 1)) ?: 10

                if ((minOf(intValue, up, down, left, right) == intValue)
                    && (intValue !in arrayOf(up, down, left, right))
                ) {
                    lows.add(intValue + 1)
                }
            }
        }

        return lows.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)

    val input = readInput("Day09")
    println(part1(input))
}