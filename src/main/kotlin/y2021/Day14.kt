fun main() {
    fun <T> MutableMap<T, Long>.increase(key: T, value: Long) {
        val oldValue = this.getOrDefault(key, 0L)
        this[key] = oldValue + value
    }

    fun part1(input: List<String>): Int {
        var polymer = input.first().toCharArray().toList()
        val conversions = input.mapNotNull { line ->
            if (line.contains("->")) {
                line.substringBefore(" ") to line.substringAfterLast(" ")
            } else null
        }.toMap()

        repeat(10) {
            val temp = mutableListOf<Char>()
            polymer.windowed(2) { chars ->
                temp.add(chars[0])
                val selected = conversions.getOrDefault(chars.joinToString(""), null)
                if (selected != null) temp.add(selected[0])
            }
            temp.add(polymer.last())
            polymer = temp
        }

        val counts = polymer.groupingBy { it }.eachCount()
        return counts.maxOf { it.value } - counts.minOf { it.value }
    }

    fun part2(input: List<String>): Long {
        val last = input.first().last()

        var data = mutableMapOf<String, Long>()
        input.first().toCharArray().toList().windowed(2) { data.increase(it.joinToString(""), 1) }

        val changes = input.mapNotNull { line ->
            if (line.contains("->")) {
                line.substringBefore(" ") to line.substringAfterLast(" ")
            } else null
        }.toMap()

        repeat(40) {
            val temp = mutableMapOf<String, Long>()
            data.forEach { (key, count) ->
                val insert = changes[key]
                if (insert != null) {
                    val first = key[0] + insert
                    val second = insert + key[1]

                    temp.increase(first, count)
                    temp.increase(second, count)
                }
            }

            data = temp
        }

        val totals = mutableMapOf<Char, Long>()
        data.forEach { (key, count) ->
            totals.increase(key[0], count)
        }
        totals.increase(last, 1L)

        return totals.maxOf { it.value }-totals.minOf { it.value }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day14_test")
    check(part1(testInput) == 1588)
    check(part2(testInput) == 2188189693529)

    val input = readInput("Day14")
    println(part1(input))
    println(part2(input))
}