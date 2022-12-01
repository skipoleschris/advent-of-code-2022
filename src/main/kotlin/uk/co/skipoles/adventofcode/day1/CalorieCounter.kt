package uk.co.skipoles.adventofcode.day1

import java.io.File

object CalorieCounter {

  fun highestTotalCaloriesHeldByOneElf(inventory: Sequence<String>): Long {
    val (max, last) =
        inventory.fold(Pair(0L, emptyList<Long>())) { (max, currentElf), calories ->
          if (calories.isEmpty()) Pair(maxOf(max, currentElf.sum()), emptyList())
          else Pair(max, currentElf + calories.toLong())
        }
    return maxOf(max, last.sum())
  }

  fun highestTotalCaloriesHeldByOneElfFromInventory(filename: String): Long =
      File(filename).useLines(block = ::highestTotalCaloriesHeldByOneElf)
}

fun main() {
  println(CalorieCounter.highestTotalCaloriesHeldByOneElfFromInventory("data/day1/input.txt"))
}
