package uk.co.skipoles.adventofcode.day8

import java.io.File

object TreeHouse {

  private fun isAtEdge(row: Int, col: Int, lastRow: Int, lastCol: Int) =
      row == 0 || row == lastRow || col == 0 || col == lastCol

  private fun isVisibleFromEdge(height: Int, row: Int, col: Int, grid: List<List<Int>>) =
      isAtEdge(row, col, grid.lastIndex, grid.first().lastIndex) ||
          isTallerThanOthers(height, col, grid[row]) ||
          isTallerThanOthers(height, row, grid.map { it[col] })

  private fun isTallerThanOthers(height: Int, index: Int, others: List<Int>) =
      height > (others.take(index).maxOrNull() ?: throw IllegalStateException()) ||
          height > (others.drop(index + 1).maxOrNull() ?: throw IllegalStateException())

  private fun parseGrid(filename: String) =
      File(filename).readLines().map { line -> line.map { it.digitToInt() } }

  private fun <T> parseAndTransformGrid(
      filename: String,
      f: (Int, Int, Int, List<List<Int>>) -> T
  ): List<T> {
    val grid = parseGrid(filename)
    return grid.flatMapIndexed { rowIndex, row ->
      row.mapIndexed { colIndex, height -> f(height, rowIndex, colIndex, grid) }
    }
  }

  fun countVisibleTrees(filename: String): Int =
      parseAndTransformGrid(filename, ::isVisibleFromEdge).count { it }

  private fun scenicScore(height: Int, row: Int, col: Int, grid: List<List<Int>>): Int =
      calculateScoreEitherSide(height, col, grid[row]) *
          calculateScoreEitherSide(height, row, grid.map { it[col] })

  private fun trackNotBlocked(height: Int): (Pair<Int, Boolean>, Int) -> Pair<Int, Boolean> =
      fun(result: Pair<Int, Boolean>, treeHeight: Int) =
          if (result.second) result
          else if (treeHeight < height) Pair(result.first + 1, false)
          else Pair(result.first + 1, true)

  private fun calculateScoreEitherSide(height: Int, index: Int, others: List<Int>): Int {
    val left = others.take(index).reversed().fold(Pair(0, false), trackNotBlocked(height)).first
    val right = others.drop(index + 1).fold(Pair(0, false), trackNotBlocked(height)).first
    return left * right
  }

  fun highestScenicScore(filename: String): Int =
      parseAndTransformGrid(filename, ::scenicScore).maxOrNull()!!
}
