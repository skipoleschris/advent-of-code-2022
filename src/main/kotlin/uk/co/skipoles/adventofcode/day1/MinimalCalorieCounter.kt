package uk.co.skipoles.adventofcode.day1

import java.io.File

fun highestTotalCaloriesHeldByTopElvesFromInventory(
    filename: String,
    numberOfElves: Int = 1
): Long =
    File(filename).useLines { lines ->
      lines
          .fold(Pair(emptyList<Long>(), 0L)) { (calories, currentElf), item ->
            if (item.isEmpty()) Pair(calories + currentElf, 0L)
            else Pair(calories, currentElf + item.toLong())
          }
          .let { it.first + it.second }
          .sortedDescending()
          .take(numberOfElves)
          .sum()
    }

fun main() {
  println(highestTotalCaloriesHeldByTopElvesFromInventory("data/day1/input.txt", 1))
  println(highestTotalCaloriesHeldByTopElvesFromInventory("data/day1/input.txt", 3))
}
