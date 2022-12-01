import java.io.File

interface BasePuzzle {
    val year: Int
    val day: Int
    val file: String

    fun readFile() = File("src/main/kotlin/y$year/d$day", file).readLines()
}
