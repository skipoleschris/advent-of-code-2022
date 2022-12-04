package uk.co.skipoles.adventofcode.day4

import java.io.File

data class Range(val start: Int, val end: Int)

fun Range.contains(other: Range): Boolean = start <= other.start && end >= other.end

fun Range.overlaps(other: Range): Boolean =
    contains(other) ||
        (start >= other.start && start <= other.end) ||
        (end >= other.start && end <= other.end)

object CampCleanup {

  fun fullyContains(ranges: Pair<Range, Range>): Boolean =
      ranges.first.contains(ranges.second) || ranges.second.contains(ranges.first)

  fun overlaps(ranges: Pair<Range, Range>): Boolean = ranges.first.overlaps(ranges.second)

  private fun toRanges(line: String): Pair<Range, Range> {
    val bits = line.split("""[\-,]""".toRegex()).map { it.toInt() }
    return Pair(Range(bits[0], bits[1]), Range(bits[2], bits[3]))
  }

  fun countAssignments(filename: String, f: (Pair<Range, Range>) -> Boolean): Int =
      File(filename).useLines { it.map(::toRanges).filter(f).count() }
}
