package uk.co.skipoles.adventofcode.day7

import java.io.File

data class Directory(
    var sizeOfFiles: Long = 0L,
    val children: MutableMap<String, Directory> = mutableMapOf()
)

fun Directory.totalSize(): Long = children.values.sumOf { it.totalSize() } + sizeOfFiles

fun sumDirectoriesWithSizeAtMost(atMost: Long, filename: String): Long =
    allSizes(parseTerminalOutput(filename)).filter { it <= atMost }.sum()

fun optimumDeletionDirectorySize(totalSpace: Long, neededSpace: Long, filename: String): Long {
  val root = parseTerminalOutput(filename)
  val minimumDelete = neededSpace - (totalSpace - root.totalSize())
  return allSizes(root).filter { it >= minimumDelete }.minOrNull() ?: throw IllegalStateException()
}

private fun allSizes(directory: Directory): List<Long> =
    directory.children.values.flatMap(::allSizes) + directory.totalSize()

private fun parseTerminalOutput(filename: String): Directory =
    File(filename).useLines { it.fold(emptyList(), ::parseLine) }.first()

private fun parseLine(currentPath: List<Directory>, line: String): List<Directory> =
    if (currentPath.isEmpty() && line == "$ cd /") {
      listOf(Directory())
    } else if (line != "$ cd .." && line.startsWith("$ cd ")) {
      currentPath + (currentPath.last().children[line.drop(5)]!!)
    } else if (line == "$ cd ..") {
      currentPath.dropLast(1)
    } else if (line == "$ ls") {
      // Do nothing
      currentPath
    } else if (line.startsWith("dir ")) {
      currentPath.last().children[line.drop(4)] = Directory()
      currentPath
    } else {
      val bits = line.split(" ")
      currentPath.last().sizeOfFiles += bits[0].toLong()
      currentPath
    }
