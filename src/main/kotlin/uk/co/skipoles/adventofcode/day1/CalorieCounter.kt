package uk.co.skipoles.adventofcode.day1

import java.io.File

private data class MaxCaloriesTracker(
    val numberOfElves: Int,
    val topCaloriesCarried: List<Long> = emptyList(),
    val currentElfCaloriesCarried: Long = 0L
) {
  fun totalCalories() = topCaloriesCarried.sum()
}

private fun MaxCaloriesTracker.updateTopCalories(): MaxCaloriesTracker =
    if (topCaloriesCarried.size < numberOfElves)
        copy(
            topCaloriesCarried = topCaloriesCarried + currentElfCaloriesCarried,
            currentElfCaloriesCarried = 0L)
    else {
      val min = topCaloriesCarried.minOrNull()!!
      if (currentElfCaloriesCarried > min)
          copy(
              topCaloriesCarried = topCaloriesCarried - min + currentElfCaloriesCarried,
              currentElfCaloriesCarried = 0L)
      else copy(currentElfCaloriesCarried = 0L)
    }

private fun MaxCaloriesTracker.addToCurrentElf(calories: Long): MaxCaloriesTracker =
    copy(currentElfCaloriesCarried = currentElfCaloriesCarried + calories)

object CalorieCounter {

  fun highestTotalCaloriesHeldByTopElves(numberOfElves: Int, inventory: Sequence<String>): Long {
    val tracker =
        inventory.fold(MaxCaloriesTracker(numberOfElves)) { tracker, calories ->
          if (calories.isEmpty()) tracker.updateTopCalories()
          else tracker.addToCurrentElf(calories.toLong())
        }
    return tracker.updateTopCalories().totalCalories()
  }

  fun highestTotalCaloriesHeldByTopElvesFromInventory(
      filename: String,
      numberOfElves: Int = 1
  ): Long = File(filename).useLines { highestTotalCaloriesHeldByTopElves(numberOfElves, it) }
}

fun main() {
  println(CalorieCounter.highestTotalCaloriesHeldByTopElvesFromInventory("data/day1/input.txt", 3))
}
