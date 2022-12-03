package y2022.d1

import BasePuzzle
import java.math.BigInteger

object Puzzle1 : BasePuzzle {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = readInput()

        var pos = 0
        val list = mutableListOf<BigInteger>()
        data.forEach { line ->
            if (line.isBlank()) {
                pos++
            } else {
                val num = BigInteger(line)
                val value = (list.getOrNull(pos) ?: BigInteger.ZERO) + num
                if (list.size == pos) {
                    list.add(value)
                } else {
                    list[pos] = value
                }
            }
        }

        println(list.max())
    }
}
