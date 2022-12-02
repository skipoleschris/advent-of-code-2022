package uk.co.skipoles.adventofcode.day2

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RockPaperScissorsTest {

  // Tests for part 1
  @Test
  fun `can calculate the score for a single Win round`() {
    val score = RockPaperScissors.calculateScore(Strategy(Choice.Rock, Choice.Paper))
    score shouldBe 8
  }

  @Test
  fun `can calculate the score for a single Loss round`() {
    val score = RockPaperScissors.calculateScore(Strategy(Choice.Paper, Choice.Rock))
    score shouldBe 1
  }

  @Test
  fun `can calculate the score for a single Draw round`() {
    val score = RockPaperScissors.calculateScore(Strategy(Choice.Scissors, Choice.Scissors))
    score shouldBe 6
  }

  @Test
  fun `can calculate the score from a strategy guide file`() {
    val score = RockPaperScissors.calculateScoreForStrategyGuide("data/day2/test.txt")
    score shouldBe 15
  }

  // New tests introduced by part 2
  @Test
  fun `can calculate the score for a required Draw round`() {
    val score = RockPaperScissors.calculateScore(Outcome(Choice.Rock, Action.Draw))
    score shouldBe 4
  }

  @Test
  fun `can calculate the score for a required Lose round`() {
    val score = RockPaperScissors.calculateScore(Outcome(Choice.Paper, Action.Lose))
    score shouldBe 1
  }

  @Test
  fun `can calculate the score for a required Win round`() {
    val score = RockPaperScissors.calculateScore(Outcome(Choice.Scissors, Action.Win))
    score shouldBe 7
  }

  @Test
  fun `can calculate the part 2 score from a strategy guide file`() {
    val score = RockPaperScissors.calculateScoreForStrategyGuide2("data/day2/test.txt")
    score shouldBe 12
  }

  @Test
  fun `can pass the part 1 scenario`() {
    val score = RockPaperScissors.calculateScoreForStrategyGuide("data/day2/input.txt")
    score shouldBe 13675
  }

  @Test
  fun `can pass the part 2 scenario`() {
    val score = RockPaperScissors.calculateScoreForStrategyGuide2("data/day2/input.txt")
    score shouldBe 14184
  }
}
