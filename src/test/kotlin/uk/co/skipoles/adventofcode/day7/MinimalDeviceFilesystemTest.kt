package uk.co.skipoles.adventofcode.day7

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MinimalDeviceFilesystemTest {

  @Test
  fun `can calculate the answer for part 1`() {
    val total = sumDirectoriesWithSizeAtMost(100000L, "data/day7/input.txt")
    total shouldBe 919137L
  }

  @Test
  fun `can calculate the answer for part 2`() {
    val total = optimumDeletionDirectorySize(70000000L, 30000000L, "data/day7/input.txt")
    total shouldBe 2877389L
  }
}
