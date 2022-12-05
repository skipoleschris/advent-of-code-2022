package uk.co.skipoles.adventofcode.day5

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SupplyStacksTest {

  @Test
  fun `can move a single crate between stacks`() {
    val stacks =
        SupplyStacks.move(MoveRequest(1, 1, 2), listOf(CrateStack.of('A'), CrateStack.of()))

    stacks.shouldContainExactly(listOf(CrateStack.of(), CrateStack.of('A')))
  }

  @Test
  fun `can move multiple crates between stacks`() {
    val stacks =
        SupplyStacks.move(
            MoveRequest(2, 1, 2), listOf(CrateStack.of('C', 'B', 'A'), CrateStack.of('D')))

    stacks.shouldContainExactly(listOf(CrateStack.of('C'), CrateStack.of('D', 'A', 'B')))
  }

  @Test
  fun `can return the top item on each stack`() {
    val items =
        SupplyStacks.topItems(
            listOf(CrateStack.of('C'), CrateStack.of('M'), CrateStack.of('P', 'D', 'N', 'Z')))
    items shouldBe "CMZ"
  }

  @Test
  fun `can parse a set of starting stack definitions`() {
    val stacks =
        SupplyStacks.parseStacks(listOf("    [D]    ", "[N] [C]    ", "[Z] [M] [P]", " 1   2   3 "))

    stacks.shouldContainExactly(
        listOf(CrateStack.of('Z', 'N'), CrateStack.of('M', 'C', 'D'), CrateStack.of('P')))
  }

  @Test
  fun `can parse a set of move requests`() {
    val requests =
        SupplyStacks.parseMoveRequests(
            listOf(
                "move 1 from 2 to 1",
                "move 3 from 1 to 3",
                "move 2 from 2 to 1",
                "move 1 from 1 to 2"))

    requests.shouldContainExactly(
        listOf(
            MoveRequest(1, 2, 1), MoveRequest(3, 1, 3), MoveRequest(2, 2, 1), MoveRequest(1, 1, 2)))
  }

  @Test
  fun `should run rearrangement and produce result of top containers`() {
    val result = SupplyStacks.runRearrangement("data/day5/test.txt", SupplyStacks::move)
    result shouldBe "CMZ"
  }

  @Test
  fun `should obtain a result for part 1`() {
    val result = SupplyStacks.runRearrangement("data/day5/input.txt", SupplyStacks::move)
    result shouldBe "FJSRQCFTN"
  }

  @Test
  fun `can move multiple crates between stacks in a single operation`() {
    val stacks =
        SupplyStacks.multiMove(
            MoveRequest(2, 1, 2), listOf(CrateStack.of('C', 'B', 'A'), CrateStack.of('D')))

    stacks.shouldContainExactly(listOf(CrateStack.of('C'), CrateStack.of('D', 'B', 'A')))
  }

  @Test
  fun `should run rearrangement and produce result of top containers with multi-move crane`() {
    val result = SupplyStacks.runRearrangement("data/day5/test.txt", SupplyStacks::multiMove)
    result shouldBe "MCD"
  }

  @Test
  fun `should obtain a result for part 2`() {
    val result = SupplyStacks.runRearrangement("data/day5/input.txt", SupplyStacks::multiMove)
    result shouldBe "CJVLJQPHS"
  }
}
