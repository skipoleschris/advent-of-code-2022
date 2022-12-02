package uk.co.skipoles.adventofcode.day2

import java.io.File

enum class Choice {
  Rock,
  Paper,
  Scissors
}

enum class Action {
  Win,
  Lose,
  Draw
}

data class Strategy(val opponentChoice: Choice, val playerChoice: Choice)

data class Outcome(val opponentChoice: Choice, val playerAction: Action)

object RockPaperScissors {

  // Part 1 scoring implementation
  fun calculateScore(strategy: Strategy): Int = strategy.score() + strategy.playerChoice.score()

  private fun Strategy.score() =
      if (opponentChoice == playerChoice) 3 else if (playerChoice.beats(opponentChoice)) 6 else 0

  private fun Choice.beats(choice: Choice) =
      when (this) {
        Choice.Rock -> choice == Choice.Scissors
        Choice.Paper -> choice == Choice.Rock
        Choice.Scissors -> choice == Choice.Paper
      }

  private fun Choice.score() =
      when (this) {
        Choice.Rock -> 1
        Choice.Paper -> 2
        Choice.Scissors -> 3
      }

  // Part 2 scoring implementation
  fun calculateScore(outcome: Outcome): Int =
      calculateScore(Strategy(outcome.opponentChoice, outcome.playerChoice()))

  private fun Outcome.playerChoice() =
      when (this.playerAction) {
        Action.Win -> opponentChoice.losesTo()
        Action.Lose -> opponentChoice.winsOver()
        Action.Draw -> opponentChoice
      }

  private fun Choice.losesTo() =
      when (this) {
        Choice.Rock -> Choice.Paper
        Choice.Paper -> Choice.Scissors
        Choice.Scissors -> Choice.Rock
      }

  private fun Choice.winsOver() =
      when (this) {
        Choice.Rock -> Choice.Scissors
        Choice.Paper -> Choice.Rock
        Choice.Scissors -> Choice.Paper
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
}
