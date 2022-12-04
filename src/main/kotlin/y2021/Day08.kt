fun main() {
    fun part1(input: List<String>): Int {
        val outputs = input.map { it.substringAfter("|").split(" ").filterNot { it.isEmpty() } }
        return outputs.map { output ->
            output.count { it.length in arrayOf(2, 3, 4, 7) }
        }.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)

    val input = readInput("Day08")
    println(part1(input))
}