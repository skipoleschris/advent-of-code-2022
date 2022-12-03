package uk.co.skipoles.adventofcode.day3

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MinimalRucksackReorgTest {

  @Test
  fun `should produce a result for part 1`() {
    val total = sumPriorityItemsFromInventory("data/day3/input.txt", ::acrossCompartments)
    total shouldBe 8153
  }

  @Test
  fun `should produce a result for part 2`() {
    val total = sumPriorityItemsFromInventory("data/day3/input.txt", ::acrossGroups)
    total shouldBe 2342
  }
}
