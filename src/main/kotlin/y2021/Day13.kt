fun main() {
    fun part1(input: List<String>): Int {
        var points = mutableListOf<Pair<Int, Int>>()
        val folds = mutableListOf<Pair<Char, Int>>()
        input.forEach { line ->
            if (line.startsWith("fold along")) {
                val axis = line.substringBefore("=").last()
                val pos = line.substringAfter("=").toInt()
                folds.add(Pair(axis, pos))
            } else if (line.isNotEmpty()) {
                val point = line.split(",").map { it.toInt() }.let { Pair(it[0], it[1]) }
                points.add(point)
            }
        }

        val fold = folds.first()
        if (fold.first == 'x') {
            val pos = fold.second
            val (remain, toFold) = points.partition { it.first <= pos }
            points = remain.toMutableList()

            toFold.forEach { point ->
                val displace = (point.first - pos) * 2
                points.add(Pair(point.first - displace, point.second))
            }

        } else {
            val pos = fold.second
            val (remain, toFold) = points.partition { it.second <= pos }
            points = remain.toMutableList()

            toFold.forEach { point ->
                val displace = (point.second - pos) * 2
                points.add(Pair(point.first, point.second - displace))
            }
        }
        points = points.distinct().toMutableList()

        return points.size
    }

    fun part2(input: List<String>): Int {
        var points = mutableListOf<Pair<Int, Int>>()
        val folds = mutableListOf<Pair<Char, Int>>()
        input.forEach { line ->
            if (line.startsWith("fold along")) {
                val axis = line.substringBefore("=").last()
                val pos = line.substringAfter("=").toInt()
                folds.add(Pair(axis, pos))
            } else if (line.isNotEmpty()) {
                val point = line.split(",").map { it.toInt() }.let { Pair(it[0], it[1]) }
                points.add(point)
            }
        }

        folds.forEach { fold ->
            if (fold.first == 'x') {
                val pos = fold.second
                val (remain, toFold) = points.partition { it.first <= pos }
                points = remain.toMutableList()

                toFold.forEach { point ->
                    val displace = (point.first - pos) * 2
                    points.add(Pair(point.first - displace, point.second))
                }

            } else {
                val pos = fold.second
                val (remain, toFold) = points.partition { it.second <= pos }
                points = remain.toMutableList()

                toFold.forEach { point ->
                    val displace = (point.second - pos) * 2
                    points.add(Pair(point.first, point.second - displace))
                }
            }

            points = points.distinct().toMutableList()
        }

        return points.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day13_test")
    check(part1(testInput) == 17)
    check(part2(testInput) == 16)

    val input = readInput("Day13")
    println(part1(input))
    println(part2(input))  // Hand reviewed: EAHKRECP
}