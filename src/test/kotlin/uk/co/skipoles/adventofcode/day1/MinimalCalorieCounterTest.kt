package uk.co.skipoles.adventofcode.day1

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MinimalCalorieCounterTest {

  @Test
  fun `can pass the part 1 scenario`() {
    val totalCalories = highestTotalCaloriesHeldByTopElvesFromInventory("data/day1/input.txt")

    totalCalories shouldBe 69177L
  }

  @Test
  fun `can pass the part 2 scenario`() {
    val totalCalories = highestTotalCaloriesHeldByTopElvesFromInventory("data/day1/input.txt", 3)

    totalCalories shouldBe 207456L
  }
}
