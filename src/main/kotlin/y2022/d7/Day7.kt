package y2022.d7

import BasePuzzle

object Day7 : BasePuzzle {

    private class Node(val name: String, var size: Long) {
        var parent: Node? = null
        val children: MutableList<Node> = mutableListOf()

        fun forEachDepthFirst(visit: (Node) -> Unit) {
            visit(this)
            children.forEach {
                it.forEachDepthFirst(visit)
            }
        }
    }

    fun solve() {
        println(this::class.simpleName)

        val test = readTest()
        val input = readInput()

        checkResponse(95437L, puzzle1(test))
        println("Puzzle1: " + puzzle1(input).toString())

        checkResponse(24933642L, puzzle2(test))
        println("Puzzle2: " + puzzle2(input).toString())

        println()
    }

    private fun puzzle1(data: List<String>): Long {
        val root = buildTree(data)

        var sum = 0L
        root.forEachDepthFirst { node ->
            if ((node.children.isNotEmpty()) && (node.size < 100000)) {
                sum += node.size
            }
        }

        return sum
    }

    private fun puzzle2(data: List<String>): Long {
        val root = buildTree(data)

        val total = 70_000_000L
        val required = 30_000_000L

        val currentEmpty = total - root.size
        val needed = required - currentEmpty

        var selected = Long.MAX_VALUE
        var diff = Long.MAX_VALUE
        root.forEachDepthFirst { node ->
            if ((node.children.isNotEmpty()) && (node.size >= needed)) {
                val temp = needed - node.size
                if ((temp < 0) && (selected > node.size)) {
                    selected = node.size
                }
            }
        }

        return selected
    }

    private fun buildTree(data: List<String>): Node {
        val root = Node("/", 0)

        var currentDir = root
        data.forEach { line ->
            when {
                line.startsWith("\$ cd") -> {
                    val dirName = line.substringAfter("\$ cd").trim()
                    if (dirName == "/") {
                        currentDir = root
                    } else if (dirName == "..") {
                        val size = currentDir.children.sumOf { it.size }
                        currentDir.size = size
                        currentDir = currentDir.parent!!
                    } else {
                        currentDir = currentDir.children.first { it.name == dirName }
                    }
                }

                line.startsWith("\$ ls") -> {
                    // Do nothing
                }

                line.startsWith("dir") -> {
                    val dirName = line.substringAfter("dir").trim()
                    val dir = Node(dirName, 0)
                    dir.parent = currentDir
                    currentDir.children.add(dir)
                }

                else -> {
                    val parts = line.split(" ")
                    val file = Node(parts[1], parts[0].toLong())
                    file.parent = currentDir
                    currentDir.children.add(file)
                }
            }
        }

        var hasParent = true
        do {
            val size = currentDir.children.sumOf { it.size }
            currentDir.size = size
            hasParent = currentDir.parent != null
            if (hasParent) currentDir = currentDir.parent!!
        } while (hasParent)


        return root
    }
}
