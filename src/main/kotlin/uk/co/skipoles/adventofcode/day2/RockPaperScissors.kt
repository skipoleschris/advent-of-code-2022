package uk.co.skipoles.adventofcode.day2

import java.io.File

enum class Choice {
  Rock,
  Paper,
  Scissors
}

data class Strategy(val opponentChoice: Choice, val playerChoice: Choice)

object RockPaperScissors {

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
}

fun main() {
  println("Part 1: ${RockPaperScissors.calculateScoreForStrategyGuide("data/day2/input.txt")}")
}
