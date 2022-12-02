package uk.co.skipoles.adventofcode.day2

import java.io.File

// The Rock / Paper / Scissors options and the rules about who wins and looses
enum class Choice {
  Rock,
  Paper,
  Scissors
}

fun Choice.losesTo() =
    when (this) {
      Choice.Rock -> Choice.Paper
      Choice.Paper -> Choice.Scissors
      Choice.Scissors -> Choice.Rock
    }

fun Choice.winsOver() =
    when (this) {
      Choice.Rock -> Choice.Scissors
      Choice.Paper -> Choice.Rock
      Choice.Scissors -> Choice.Paper
    }

// Strategy represents the interpretation of the strategy file for part 1
data class Strategy(val opponentChoice: Choice, val playerChoice: Choice)

enum class Action {
  Win,
  Lose,
  Draw
}

// Outcome represents the interpretation of the strategy file for part 2
data class Outcome(val opponentChoice: Choice, val playerAction: Action)

object RockPaperScissors {

  // Part 1 scoring implementation
  fun calculateScore(strategy: Strategy): Int = strategy.score() + strategy.playerChoice.score()

  private fun Strategy.score() =
      when (opponentChoice) {
        playerChoice -> 3
        playerChoice.winsOver() -> 6
        else -> 0
      }

  private fun Choice.score() =
      when (this) {
        Choice.Rock -> 1
        Choice.Paper -> 2
        Choice.Scissors -> 3
      }

  // Part 2 scoring implementation
  // Builds on part 1 by converting the outcome to a strategy and the reusing the part 1
  // scoring implementation. Probably wouldn't take this approach and would instead compress
  // them into a single calculation, but wanted to keep part 1 and part 2 both operational in
  // the solution.
  fun calculateScore(outcome: Outcome): Int =
      calculateScore(Strategy(outcome.opponentChoice, outcome.playerChoice()))

  private fun Outcome.playerChoice() =
      when (this.playerAction) {
        Action.Win -> opponentChoice.losesTo()
        Action.Lose -> opponentChoice.winsOver()
        Action.Draw -> opponentChoice
      }

  // Part 1 Version
  fun calculateScoreForStrategyGuide(filename: String): Int =
      File(filename).useLines { it.map(::lineToStrategy).map(::calculateScore).sum() }

  private fun lineToStrategy(line: String) = Strategy(line[0].toChoice(), line[2].toChoice())

  private fun Char.toChoice() =
      when (this) {
        'A' -> Choice.Rock
        'B' -> Choice.Paper
        'C' -> Choice.Scissors
        // The below are only relevant for part 1
        'X' -> Choice.Rock
        'Y' -> Choice.Paper
        'Z' -> Choice.Scissors
        else -> throw IllegalArgumentException("Unknown choice character: $this")
      }

  // Part 2 Version
  fun calculateScoreForStrategyGuide2(filename: String): Int =
      File(filename).useLines { it.map(::lineToOutcome).map(::calculateScore).sum() }

  private fun lineToOutcome(line: String) = Outcome(line[0].toChoice(), line[2].toAction())

  private fun Char.toAction() =
      when (this) {
        'X' -> Action.Lose
        'Y' -> Action.Draw
        'Z' -> Action.Win
        else -> throw IllegalArgumentException("Unknown action character: $this")
      }
}

fun main() {
  println("Part 1: ${RockPaperScissors.calculateScoreForStrategyGuide("data/day2/input.txt")}")
  println("Part 2: ${RockPaperScissors.calculateScoreForStrategyGuide2("data/day2/input.txt")}")

  // Absolute minimalist version
  val winMap = mapOf('A' to 2, 'B' to 3, 'C' to 1)
  val loseMap = mapOf('A' to 3, 'B' to 1, 'C' to 2)
  println(
      File("data/day2/input.txt").useLines { lines ->
        lines
            .map {
              when (it[2]) {
                'X' -> loseMap[it[0]]!!
                'Y' -> 3 + it[0].code - 64
                'Z' -> 6 + winMap[it[0]]!!
                else -> throw IllegalArgumentException("Invalid format")
              }
            }
            .sum()
      })
}
