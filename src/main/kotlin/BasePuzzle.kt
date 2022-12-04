import java.io.File

interface BasePuzzle {

    fun readInput(): List<String> {
        val path = this::class.java.packageName.replace(".", "/")
        return File("src/main/kotlin/$path", "input.txt").readLines()
    }

    fun readTest(): List<String> {
        val path = this::class.java.packageName.replace(".", "/")
        return File("src/main/kotlin/$path", "test.txt").readLines()
    }

    fun checkResponse(expected: Any?, result: Any?) {
        if (expected != result) throw Exception("Response should be $expected, but was $result")
    }
}
