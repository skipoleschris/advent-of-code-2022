package uk.co.skipoles.adventofcode.day7

import java.io.File

enum class DirectoryEntryType {
  File,
  Directory
}

data class DirectoryEntry(val name: String, val type: DirectoryEntryType, val size: Long? = null)

object DeviceFilesystem {

  fun calculateSizesOfEachDirectory(
      tree: Map<List<String>, List<DirectoryEntry>>
  ): Map<List<String>, Long> = tree.mapValues { sizeOfContents(it.key, tree) }

  private fun sizeOfContents(
      path: List<String>,
      tree: Map<List<String>, List<DirectoryEntry>>
  ): Long =
      tree[path]!!.sumOf {
        when (it.type) {
          DirectoryEntryType.File -> it.size!!
          DirectoryEntryType.Directory -> sizeOfContents(path + it.name, tree)
        }
      }

  fun sumDirectoriesWithSizeAtMost(atMost: Long, sizes: Map<List<String>, Long>): Long =
      sizes.values.filter { it <= atMost }.sum()

  fun optimumDeletionDirectorySize(
      totalSpace: Long,
      neededSpace: Long,
      sizes: Map<List<String>, Long>
  ): Long {
    val freeSpace = totalSpace - sizes[listOf("/")]!!
    val minimumDelete = neededSpace - freeSpace
    return sizes.values.filter { it >= minimumDelete }.minOrNull() ?: throw IllegalStateException()
  }

  fun parseTerminalOutput(filename: String): Map<List<String>, List<DirectoryEntry>> =
      File(filename).useLines { lines ->
        val state = ParseState()
        lines.forEach { parseLine(state, it) }
        state.unwind()
        state.result()
      }

  private class ParseState(
      val pwd: MutableList<String> = mutableListOf(),
      val contentsStack: ArrayDeque<MutableList<DirectoryEntry>> = ArrayDeque(),
      val directories: MutableMap<List<String>, List<DirectoryEntry>> = mutableMapOf()
  ) {
    fun enteringSubDirectory(name: String) {
      pwd.add(name)
      contentsStack.addFirst(mutableListOf())
    }

    fun leavingDirectory() {
      val contents = contentsStack.removeFirst()
      directories[pwd.toList()] = contents.toList()
      pwd.removeLast()
    }

    fun unwind() {
      while (pwd.isNotEmpty()) leavingDirectory()
    }

    fun addingFile(name: String, size: Long) {
      contentsStack.first().add(DirectoryEntry(name, DirectoryEntryType.File, size))
    }

    fun addingDirectory(name: String) {
      contentsStack.first().add(DirectoryEntry(name, DirectoryEntryType.Directory))
    }

    fun result(): Map<List<String>, List<DirectoryEntry>> = directories.toMap()
  }

  private fun parseLine(state: ParseState, line: String) {
    if (line != "$ cd .." && line.startsWith("$ cd ")) {
      state.enteringSubDirectory(line.drop(5))
    } else if (line == "$ cd ..") {
      state.leavingDirectory()
    } else if (line == "$ ls") {
      // Do nothing
    } else if (line.startsWith("dir ")) {
      state.addingDirectory(line.drop(4))
    } else {
      val bits = line.split(" ")
      state.addingFile(bits[1], bits[0].toLong())
    }
  }
}
