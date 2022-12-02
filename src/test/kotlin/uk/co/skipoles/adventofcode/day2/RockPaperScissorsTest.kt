package uk.co.skipoles.adventofcode.day2

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RockPaperScissorsTest {

  @Test
  fun `can calculate the strategy guide score for a single Win round`() {
    val score = RockPaperScissors.calculateScore(Strategy(Choice.Rock, Choice.Paper))
    score shouldBe 8
  }

  @Test
  fun `can calculate the strategy guide score for a single Loss round`() {
    val score = RockPaperScissors.calculateScore(Strategy(Choice.Paper, Choice.Rock))
    score shouldBe 1
  }

  @Test
  fun `can calculate the strategy guide score for a single Draw round`() {
    val score = RockPaperScissors.calculateScore(Strategy(Choice.Scissors, Choice.Scissors))
    score shouldBe 6
  }

  @Test
  fun `can calculate the score from a strategy guide file`() {
    val score = RockPaperScissors.calculateScoreForStrategyGuide("data/day2/test.txt")
    score shouldBe 15
  }
}
