package uk.co.skipoles.adventofcode.day5

import java.io.File

data class MoveRequest(val amount: Int, val fromStack: Int, val toStack: Int)

data class CrateStack(val items: List<Char>) {
  companion object {
    fun of(vararg chars: Char) = CrateStack(chars.asList())
  }
}

fun CrateStack.moveTopItem(to: CrateStack): Pair<CrateStack, CrateStack> =
    Pair(CrateStack(items.dropLast(1)), CrateStack(to.items + items.last()))

fun CrateStack.addItemToTop(item: Char): CrateStack = CrateStack(items + item)

fun CrateStack.moveTopItems(count: Int, to: CrateStack): Pair<CrateStack, CrateStack> =
    Pair(CrateStack(items.dropLast(count)), CrateStack(to.items + items.takeLast(count)))

object SupplyStacks {

  private fun <T> replaceElement(list: List<T>, index: Int, element: T): List<T> =
      list.take(index) + element + list.drop(index + 1)

  fun move(
      request: MoveRequest,
      stacks: List<CrateStack>,
  ): List<CrateStack> =
      moveItems(request, stacks) {
        (1..request.amount).fold(it) { res, _ -> res.first.moveTopItem(res.second) }
      }

  fun multiMove(
      request: MoveRequest,
      stacks: List<CrateStack>,
  ): List<CrateStack> =
      moveItems(request, stacks) { (from, to) -> from.moveTopItems(request.amount, to) }

  private fun moveItems(
      request: MoveRequest,
      stacks: List<CrateStack>,
      f: (Pair<CrateStack, CrateStack>) -> Pair<CrateStack, CrateStack>
  ): List<CrateStack> {
    val from = stacks[request.fromStack - 1]
    val to = stacks[request.toStack - 1]
    val (newFrom, newTo) = f(Pair(from, to))
    return replaceElement(
        replaceElement(stacks, request.fromStack - 1, newFrom), request.toStack - 1, newTo)
  }

  fun topItems(stacks: List<CrateStack>): String =
      stacks.fold("") { res, stack -> if (stack.items.isEmpty()) res else res + stack.items.last() }

  private fun parseLine(line: String, stacks: List<CrateStack>): List<CrateStack> =
      (stacks.indices).fold(stacks) { res, index ->
        val item = line[index * 4 + 1]
        if (item != ' ') replaceElement(res, index, res[index].addItemToTop(item)) else res
      }

  fun parseStacks(lines: List<String>): List<CrateStack> {
    val numOfStacks = (lines.last().length / 4) + 1
    val emptyStacks = List(numOfStacks) { CrateStack.of() }
    return lines.dropLast(1).reversed().fold(emptyStacks) { stacks, line ->
      parseLine(line, stacks)
    }
  }

  fun parseMoveRequests(lines: List<String>): List<MoveRequest> {
    val regex = """move (\d+) from (\d+) to (\d+)""".toRegex()
    return lines
        .map { line ->
          val values = regex.matchEntire(line)!!.groupValues.drop(1).map { it.toInt() }
          MoveRequest(values[0], values[1], values[2])
        }
        .toList()
  }

  fun runRearrangement(
      filename: String,
      f: (MoveRequest, List<CrateStack>) -> List<CrateStack>
  ): String =
      File(filename).useLines { seq ->
        val lines = seq.toList()
        val stacks = parseStacks(lines.takeWhile { it.isNotEmpty() }.toList())
        val requests = parseMoveRequests(lines.dropWhile { it.isNotEmpty() }.drop(1))
        return topItems(requests.fold(stacks) { res, req -> f(req, res) })
      }
}
