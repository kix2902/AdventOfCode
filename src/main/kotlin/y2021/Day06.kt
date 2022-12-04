fun main() {
    fun part1(input: List<String>): Int {
        var fishes = input[0].split(",").map { it.toInt() }.toMutableList()

        repeat(80) {
            var add = 0
            fishes = fishes.map {
                if (it == 0) {
                    add++
                    7
                } else {
                    it
                }
            }.toMutableList()
            fishes = fishes.map { it - 1 }.toMutableList()
            fishes.addAll(List(add) { 8 })
        }

        return fishes.size
    }

    fun part2(input: List<String>): Long {
        val fishes = input[0].split(",").map { it.toInt() }
        val fishesMap = fishes.groupingBy { it }.eachCount().mapValues { it.value.toLong() }.toMutableMap()

        repeat(256) {
            var temp: Long = 0
            (0..9).forEach { timer ->
                temp = fishesMap.getOrDefault(timer, 0)
                fishesMap[timer] = 0
                fishesMap[timer - 1] = temp
            }
            if (fishesMap.getOrDefault(-1, 0) > 0) {
                val dead = fishesMap.getOrDefault(-1, 0)
                fishesMap[6] = fishesMap.getOrDefault(6, 0) + dead
                fishesMap[8] = dead
            }
        }

        fishesMap[-1] = 0
        return fishesMap.values.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26984457539)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}