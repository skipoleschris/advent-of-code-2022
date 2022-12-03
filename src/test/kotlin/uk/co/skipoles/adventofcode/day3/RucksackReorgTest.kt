package uk.co.skipoles.adventofcode.day3

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RucksackReorgTest {

  @Test
  fun `an empty rucksack should have zero of priority items`() {
    val total = RucksackReorg.sumIncorrectPriorityItems(Pair(listOf(), listOf()))
    total shouldBe 0
  }

  @Test
  fun `a rucksack with no incorrectly placed items should have zero of priority items`() {
    val total =
        RucksackReorg.sumIncorrectPriorityItems(
            Pair(listOf(Item.a, Item.X, Item.v), listOf(Item.B, Item.x, Item.q)))
    total shouldBe 0
  }

  @Test
  fun `a rucksack with one incorrectly placed items should return the priority of that item`() {
    val total =
        RucksackReorg.sumIncorrectPriorityItems(
            Pair(listOf(Item.a, Item.X, Item.v), listOf(Item.B, Item.x, Item.X)))
    total shouldBe 50
  }

  @Test
  fun `a string representation of rucksack contents should be convertable into compartments of items`() {
    val (left, right) =
        RucksackReorg.splitInventoryIntoCompartments(
            RucksackReorg.parseInventory("vJrwpWtwJgWrhcsFMMfFFhFp"))
    left.shouldContainExactly(
        Item.v,
        Item.J,
        Item.r,
        Item.w,
        Item.p,
        Item.W,
        Item.t,
        Item.w,
        Item.J,
        Item.g,
        Item.W,
        Item.r)
    right.shouldContainExactly(
        Item.h,
        Item.c,
        Item.s,
        Item.F,
        Item.M,
        Item.M,
        Item.f,
        Item.F,
        Item.F,
        Item.h,
        Item.F,
        Item.p)
  }

  @Test
  fun `multiple rucksacks can be processed from an inventory`() {
    val total = RucksackReorg.sumIncorrectPriorityItemsFromInventory("data/day3/test.txt")
    total shouldBe 157
  }

  @Test
  fun `should produce a result for part 1`() {
    val total = RucksackReorg.sumIncorrectPriorityItemsFromInventory("data/day3/input.txt")
    total shouldBe 8153
  }

  @Test
  fun `an item type common to three bags can be found and its priority identified`() {
    val total =
        RucksackReorg.commonItemTypePriority(
            listOf(
                RucksackReorg.parseInventory("vJrwpWtwJgWrhcsFMMfFFhFp"),
                RucksackReorg.parseInventory("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"),
                RucksackReorg.parseInventory("PmmdzqPrVvPwwTWBwg"),
            ))
    total shouldBe 18
  }

  @Test
  fun `multiple groups of rucksacks can be processed from an inventory`() {
    val total = RucksackReorg.sumBadgePrioritiesFromInventory("data/day3/test.txt")
    total shouldBe 70
  }

  @Test
  fun `should produce a result for part 2`() {
    val total = RucksackReorg.sumBadgePrioritiesFromInventory("data/day3/input.txt")
    total shouldBe 2342
  }
}
