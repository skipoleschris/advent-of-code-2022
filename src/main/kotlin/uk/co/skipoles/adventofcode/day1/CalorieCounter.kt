package uk.co.skipoles.adventofcode.day1

import java.io.File

private data class MaxCaloriesTracker(
    val numberOfElves: Int,
    val caloriesCarried: List<Long> = emptyList()
) {
  fun totalCalories() = caloriesCarried.sum()
}

private fun MaxCaloriesTracker.track(newTotal: Long): MaxCaloriesTracker =
    if (caloriesCarried.size < numberOfElves) copy(caloriesCarried = caloriesCarried + newTotal)
    else {
      val min = caloriesCarried.minOrNull()!!
      if (newTotal > min) copy(caloriesCarried = caloriesCarried - min + newTotal) else this
    }

object CalorieCounter {

  fun highestTotalCaloriesHeldByTopElves(numberOfElves: Int, inventory: Sequence<String>): Long {
    val (tracker, last) =
        inventory.fold(Pair(MaxCaloriesTracker(numberOfElves), emptyList<Long>())) {
            (tracker, currentElf),
            calories ->
          if (calories.isEmpty()) Pair(tracker.track(currentElf.sum()), emptyList())
          else Pair(tracker, currentElf + calories.toLong())
        }
    return tracker.track(last.sum()).totalCalories()
  }

  fun highestTotalCaloriesHeldByTopElvesFromInventory(
      filename: String,
      numberOfElves: Int = 1
  ): Long = File(filename).useLines { highestTotalCaloriesHeldByTopElves(numberOfElves, it) }
}

fun main() {
  println(CalorieCounter.highestTotalCaloriesHeldByTopElvesFromInventory("data/day1/input.txt", 3))
}
