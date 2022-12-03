import java.io.File

interface BasePuzzle {
    fun readInput(suffix: String = ""): List<String> {
        val path = this::class.java.packageName.replace(".", "/")
        val file = "input$suffix.txt"
        return File("src/main/kotlin/$path", file).readLines()
    }
}
