package uk.co.skipoles.adventofcode.day4

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CampCleanupTest {

  @Test
  fun `can tell if one range fully contains the other`() {
    CampCleanup.fullyContains(Pair(Range(1, 6), Range(3, 4))).shouldBeTrue()
    CampCleanup.fullyContains(Pair(Range(3, 4), Range(1, 6))).shouldBeTrue()
    CampCleanup.fullyContains(Pair(Range(1, 3), Range(3, 4))).shouldBeFalse()
  }

  @Test
  fun `can read assignments and report number of fully contained ranges`() {
    val result = CampCleanup.countAssignments("data/day4/test.txt", CampCleanup::fullyContains)
    result shouldBe 2
  }

  @Test
  fun `can return a result for the part 1 challenge`() {
    val result = CampCleanup.countAssignments("data/day4/input.txt", CampCleanup::fullyContains)
    result shouldBe 651
  }

  @Test
  fun `can tell if one range overlaps the other`() {
    CampCleanup.overlaps(Pair(Range(1, 6), Range(5, 7))).shouldBeTrue()
    CampCleanup.overlaps(Pair(Range(5, 7), Range(1, 6))).shouldBeTrue()
    CampCleanup.overlaps(Pair(Range(1, 3), Range(4, 6))).shouldBeFalse()
  }

  @Test
  fun `can read assignments and report number of overlapping ranges`() {
    val result = CampCleanup.countAssignments("data/day4/test.txt", CampCleanup::overlaps)
    result shouldBe 4
  }

  @Test
  fun `can return a result for the part 2 challenge`() {
    val result = CampCleanup.countAssignments("data/day4/input.txt", CampCleanup::overlaps)
    result shouldBe 956
  }
}
