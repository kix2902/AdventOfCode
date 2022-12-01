import java.io.File

interface BasePuzzle {
    fun readFile(num: Int): List<String> {
        val path = this::class.java.packageName.replace(".", "/")
        val file = "input$num.txt"
        return File("src/main/kotlin/$path", file).readLines()
    }
}
