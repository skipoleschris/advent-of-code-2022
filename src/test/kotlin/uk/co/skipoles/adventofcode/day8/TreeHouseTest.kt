package uk.co.skipoles.adventofcode.day8

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TreeHouseTest {

  @Test
  fun `should count visible trees in test grid`() {
    TreeHouse.countVisibleTrees("data/day8/test.txt") shouldBe 21
  }

  @Test
  fun `should produce an answer for part 1`() {
    TreeHouse.countVisibleTrees("data/day8/input.txt") shouldBe 1681
  }

  @Test
  fun `should find the highest scenic score`() {
    TreeHouse.highestScenicScore("data/day8/test.txt") shouldBe 8
  }

  @Test
  fun `should produce an answer for part 2`() {
    TreeHouse.highestScenicScore("data/day8/input.txt") shouldBe 201684
  }
}
