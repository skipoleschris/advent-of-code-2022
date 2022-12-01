package uk.co.skipoles.adventofcode.day1

import java.io.File

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
}

object MinimalistCalorieCounter {

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
}

fun main() {
  println(CalorieCounter.highestTotalCaloriesHeldByTopElvesFromInventory("data/day1/input.txt", 1))
  println(CalorieCounter.highestTotalCaloriesHeldByTopElvesFromInventory("data/day1/input.txt", 3))
  println(
      MinimalistCalorieCounter.highestTotalCaloriesHeldByTopElvesFromInventory(
          "data/day1/input.txt", 1))
  println(
      MinimalistCalorieCounter.highestTotalCaloriesHeldByTopElvesFromInventory(
          "data/day1/input.txt", 3))
}
