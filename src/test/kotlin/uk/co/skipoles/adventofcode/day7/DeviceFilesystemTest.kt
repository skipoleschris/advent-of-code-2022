package uk.co.skipoles.adventofcode.day7

import io.kotest.matchers.maps.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeviceFilesystemTest {

  private val testTree =
      mapOf(
          listOf("/") to
              listOf(
                  DirectoryEntry("a", DirectoryEntryType.Directory),
                  DirectoryEntry("b.txt", DirectoryEntryType.File, 14848514L),
                  DirectoryEntry("c.dat", DirectoryEntryType.File, 8504156L),
                  DirectoryEntry("d", DirectoryEntryType.Directory)),
          listOf("/", "a") to
              listOf(
                  DirectoryEntry("e", DirectoryEntryType.Directory),
                  DirectoryEntry("f", DirectoryEntryType.File, 29116L),
                  DirectoryEntry("g", DirectoryEntryType.File, 2557L),
                  DirectoryEntry("h.lst", DirectoryEntryType.File, 62596L)),
          listOf("/", "a", "e") to listOf(DirectoryEntry("i", DirectoryEntryType.File, 584L)),
          listOf("/", "d") to
              listOf(
                  DirectoryEntry("j", DirectoryEntryType.File, 4060174L),
                  DirectoryEntry("d.log", DirectoryEntryType.File, 8033020L),
                  DirectoryEntry("d.ext", DirectoryEntryType.File, 5626152L),
                  DirectoryEntry("k", DirectoryEntryType.File, 7214296L)))

  private val testSizes =
      mapOf(
          listOf("/") to 48381165L,
          listOf("/", "a") to 94853L,
          listOf("/", "a", "e") to 584L,
          listOf("/", "d") to 24933642L)

  @Test
  fun `can parse terminal output and construct a filesystem tree`() {
    val tree = DeviceFilesystem.parseTerminalOutput("data/day7/test.txt")
    tree.shouldContainExactly(testTree)
  }

  @Test
  fun `can calculate the total size of each directory`() {
    val sizes = DeviceFilesystem.calculateSizesOfEachDirectory(testTree)
    sizes.shouldContainExactly(testSizes)
  }

  @Test
  fun `can find sum of directory sizes of at most 100000`() {
    val total = DeviceFilesystem.sumDirectoriesWithSizeAtMost(100000L, testSizes)
    total shouldBe 95437L
  }

  @Test
  fun `can calculate the answer for part 1`() {
    val total =
        DeviceFilesystem.sumDirectoriesWithSizeAtMost(
            100000L,
            DeviceFilesystem.calculateSizesOfEachDirectory(
                DeviceFilesystem.parseTerminalOutput("data/day7/input.txt")))
    total shouldBe 919137L
  }

  @Test
  fun `can find optimum directory size to delete`() {
    val size = DeviceFilesystem.optimumDeletionDirectorySize(70000000L, 30000000L, testSizes)
    size shouldBe 24933642L
  }

  @Test
  fun `can calculate the answer for part 2`() {
    val total =
        DeviceFilesystem.optimumDeletionDirectorySize(
            70000000L,
            30000000L,
            DeviceFilesystem.calculateSizesOfEachDirectory(
                DeviceFilesystem.parseTerminalOutput("data/day7/input.txt")))
    total shouldBe 2877389L
  }
}
