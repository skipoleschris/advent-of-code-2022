package uk.co.skipoles.adventofcode.day2

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MinimalRockPaperScissorsTest {

  @Test
  fun `can pass the part 2 scenario`() {
    val score = calculateScoreForStrategy("data/day2/input.txt")
    score shouldBe 14184
  }
}
