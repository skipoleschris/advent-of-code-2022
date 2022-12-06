package uk.co.skipoles.adventofcode.day6

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TuningTroubleTest {

  @Test
  fun `can find the number of characters up to the first start of packet marker`() {
    TuningTrouble.characterCountToFirstStartOfMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 4) shouldBe 7
    TuningTrouble.characterCountToFirstStartOfMarker("bvwbjplbgvbhsrlpgdmjqwftvncz", 4) shouldBe 5
    TuningTrouble.characterCountToFirstStartOfMarker("nppdvjthqldpwncqszvftbrmjlhg", 4) shouldBe 6
    TuningTrouble.characterCountToFirstStartOfMarker(
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 4) shouldBe 10
    TuningTrouble.characterCountToFirstStartOfMarker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 4) shouldBe
        11
  }

  @Test
  fun `can calculate the result for part 1`() {
    val result = TuningTrouble.processDataStream("data/day6/input.txt", 4)
    result shouldBe 1855
  }

  @Test
  fun `can find the number of characters up to the first start of message marker`() {
    TuningTrouble.characterCountToFirstStartOfMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 14) shouldBe
        19
    TuningTrouble.characterCountToFirstStartOfMarker("bvwbjplbgvbhsrlpgdmjqwftvncz", 14) shouldBe 23
    TuningTrouble.characterCountToFirstStartOfMarker("nppdvjthqldpwncqszvftbrmjlhg", 14) shouldBe 23
    TuningTrouble.characterCountToFirstStartOfMarker(
        "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 14) shouldBe 29
    TuningTrouble.characterCountToFirstStartOfMarker(
        "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 14) shouldBe 26
  }

  @Test
  fun `can calculate the result for part 2`() {
    val result = TuningTrouble.processDataStream("data/day6/input.txt", 14)
    result shouldBe 3256
  }
}
