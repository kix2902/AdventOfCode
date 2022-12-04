fun main() {
    fun part1(input: List<String>): Int {
        val points = mutableMapOf<Triple<Int, Int, Int>, Boolean>()
        input.forEach { line ->
            var data = line
            val state = data.substringBefore(" ") == "on"
            data = data.substringAfter(" ")

            val x = data.substringAfter("=").substringBefore(",")
            data = data.substringAfter(",")

            val y = data.substringAfter("=").substringBefore(",")
            data = data.substringAfter(",")

            val z = data.substringAfter("=")

            val xRange = IntRange(x.substringBefore(".").toInt(), x.substringAfterLast(".").toInt())
            val yRange = IntRange(y.substringBefore(".").toInt(), y.substringAfterLast(".").toInt())
            val zRange = IntRange(z.substringBefore(".").toInt(), z.substringAfterLast(".").toInt())

            if ((xRange.first > 50) || (xRange.last < -50)) return@forEach
            if ((yRange.first > 50) || (yRange.last < -50)) return@forEach
            if ((zRange.first > 50) || (zRange.last < -50)) return@forEach

            xRange.forEach { a ->
                yRange.forEach { b ->
                    zRange.forEach { c ->
                        points[Triple(a, b, c)] = state
                    }
                }
            }
        }

        return points.filter { it.value }.count()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day22_test")
    check(part1(testInput) == 590784)

    val input = readInput("Day22")
    println(part1(input))
}