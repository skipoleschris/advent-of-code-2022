package uk.co.skipoles.adventofcode.day6

import java.io.File

object TuningTrouble {

  fun characterCountToFirstStartOfMarker(datastream: String, uniqueCharacters: Int): Int =
      datastream.indices
          .takeWhile { index ->
            (index + 1 < uniqueCharacters) ||
                (datastream.take(index + 1).takeLast(uniqueCharacters)).toSet().size !=
                    uniqueCharacters
          }
          .size + 1

  fun processDataStream(filename: String, uniqueCharacters: Int): Int =
      File(filename).useLines { characterCountToFirstStartOfMarker(it.first(), uniqueCharacters) }
}
