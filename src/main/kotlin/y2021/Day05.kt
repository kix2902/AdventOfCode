fun main() {
    fun MutableMap<Pair<Int, Int>, Int>.increase(key: Pair<Int, Int>) {
        val value = this.getOrDefault(key, 0)
        this[key] = value + 1
    }

    fun part1(input: List<String>): Int {
        val map = mutableMapOf<Pair<Int, Int>, Int>()
        input.forEach { line ->
            val orig = line.substringBefore(" ").split(",").map { it.toInt() }.let { Pair(it[0], it[1]) }
            val dest = line.substringAfterLast(" ").split(",").map { it.toInt() }.let { Pair(it[0], it[1]) }

            if (orig.first == dest.first) {
                val first = orig.first
                if (orig.second <= dest.second) {
                    (orig.second..dest.second).forEach { second ->
                        map.increase(Pair(first, second))
                    }

                } else {
                    (dest.second..orig.second).forEach { second ->
                        map.increase(Pair(first, second))
                    }
                }

            } else if (orig.second == dest.second) {
                val second = orig.second
                if (orig.first <= dest.first) {
                    (orig.first..dest.first).forEach { first ->
                        map.increase(Pair(first, second))
                    }

                } else {
                    (dest.first..orig.first).forEach { first ->
                        map.increase(Pair(first, second))
                    }
                }
            }
        }
        return map.filter { it.value >= 2 }.count()
    }

    fun part2(input: List<String>): Int {
        val map = mutableMapOf<Pair<Int, Int>, Int>()
        input.forEach { line ->
            val orig = line.substringBefore(" ").split(",").map { it.toInt() }.let { Pair(it[0], it[1]) }
            val dest = line.substringAfterLast(" ").split(",").map { it.toInt() }.let { Pair(it[0], it[1]) }

            val firstDirection = dest.first.compareTo(orig.first)
            val secondDirection = dest.second.compareTo(orig.second)

            var first = orig.first
            var second = orig.second
            do {
                map.increase(Pair(first, second))
                first += 1 * firstDirection
                second += 1 * secondDirection
            } while ((first != dest.first) || (second != dest.second))
            map.increase(Pair(first, second))
        }
        return map.filter { it.value >= 2 }.count()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
