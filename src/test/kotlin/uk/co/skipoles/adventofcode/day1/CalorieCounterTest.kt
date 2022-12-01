package uk.co.skipoles.adventofcode.day1

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalorieCounterTest {

  @Test
  fun `can return highest total calories for a single elf with one item`() {
    val highestCalories = CalorieCounter.highestTotalCaloriesHeldByOneElf(sequenceOf("1000"))

    highestCalories shouldBe 1000L
  }

  @Test
  fun `can return highest total calories for a single elf with multiple items`() {
    val highestCalories =
        CalorieCounter.highestTotalCaloriesHeldByOneElf(sequenceOf("1000", "2000", "3000"))

    highestCalories shouldBe 6000L
  }

  @Test
  fun `can return highest total calories for a multiple elves with multiple items`() {
    val highestCalories =
        CalorieCounter.highestTotalCaloriesHeldByOneElf(
            sequenceOf("1000", "2000", "3000", "", "5000", "", "4000", "6000"))

    highestCalories shouldBe 10000L
  }

  @Test
  fun `can obtain the inventory from a file and return highest total calories`() {
    val highestCalories =
        CalorieCounter.highestTotalCaloriesHeldByOneElfFromInventory("data/day1/test.txt")

    highestCalories shouldBe 10000L
  }
}
