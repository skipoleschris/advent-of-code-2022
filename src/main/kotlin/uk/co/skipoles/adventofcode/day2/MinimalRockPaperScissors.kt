package uk.co.skipoles.adventofcode.day2

import java.io.File

private val winMap = mapOf('A' to 2, 'B' to 3, 'C' to 1)
private val loseMap = mapOf('A' to 3, 'B' to 1, 'C' to 2)

fun calculateScoreForStrategy(filename: String): Int =
    File(filename).useLines { lines ->
      lines
          .map {
            when (it[2]) {
              'X' -> loseMap[it[0]]!!
              'Y' -> 3 + it[0].code - 64
              'Z' -> 6 + winMap[it[0]]!!
              else -> throw IllegalArgumentException("Invalid format")
            }
          }
          .sum()
    }

fun main() {
  println("Part 2: ${calculateScoreForStrategy("data/day2/input.txt")}")
}
