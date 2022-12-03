package uk.co.skipoles.adventofcode.day3

import java.io.File

private fun findInAll(rucksacks: List<String>): Set<Char> =
    setOf(rucksacks.first().first { item -> rucksacks.drop(1).all { it.contains(item) } })

private fun asPrioritySum(items: Set<Char>): Int =
    items.sumOf { if (it < 'a') it.code - 38 else it.code - 96 }

fun acrossCompartments(lines: Sequence<String>) = lines.map { it.chunked(it.length / 2) }

fun acrossGroups(lines: Sequence<String>) = lines.chunked(3)

fun sumPriorityItemsFromInventory(
    filename: String,
    f: (Sequence<String>) -> Sequence<List<String>>
): Int = File(filename).useLines { f(it).map(::findInAll).map(::asPrioritySum).sum() }
