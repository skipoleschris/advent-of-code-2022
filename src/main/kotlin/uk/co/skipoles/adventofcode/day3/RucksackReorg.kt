package uk.co.skipoles.adventofcode.day3

import java.io.File

enum class Item(val token: Char, val priority: Int) {
  a('a', 1),
  b('b', 2),
  c('c', 3),
  d('d', 4),
  e('e', 5),
  f('f', 6),
  g('g', 7),
  h('h', 8),
  i('i', 9),
  j('j', 10),
  k('k', 11),
  l('l', 12),
  m('m', 13),
  n('n', 14),
  o('o', 15),
  p('p', 16),
  q('q', 17),
  r('r', 18),
  s('s', 19),
  t('t', 20),
  u('u', 21),
  v('v', 22),
  w('w', 23),
  x('x', 24),
  y('y', 25),
  z('z', 26),
  A('A', 27),
  B('B', 28),
  C('C', 29),
  D('D', 30),
  E('E', 31),
  F('F', 32),
  G('G', 33),
  H('H', 34),
  I('I', 35),
  J('J', 36),
  K('K', 37),
  L('L', 38),
  M('M', 39),
  N('N', 40),
  O('O', 41),
  P('P', 42),
  Q('Q', 43),
  R('R', 44),
  S('S', 45),
  T('T', 46),
  U('U', 47),
  V('V', 48),
  W('W', 49),
  X('X', 50),
  Y('Y', 51),
  Z('Z', 52)
}

object RucksackReorg {

  fun sumIncorrectPriorityItems(compartments: Pair<List<Item>, List<Item>>): Int =
      findInBoth(compartments.first.toSet(), compartments.second.toSet()).sumOf { it.priority }

  private fun findInBoth(left: Set<Item>, right: Set<Item>): Set<Item> =
      left.filter { right.contains(it) }.toSet()

  fun parseInventory(items: String): List<Item> = items.map { it.toItem() }

  fun splitInventoryIntoCompartments(items: List<Item>): Pair<List<Item>, List<Item>> =
      Pair(items.take(items.size / 2), items.drop(items.size / 2))

  private fun Char.toItem(): Item =
      Item.values().find { it.token == this }
          ?: throw IllegalArgumentException("Unknown token: $this")

  fun sumIncorrectPriorityItemsFromInventory(filename: String) =
      File(filename).useLines {
        it.map(::parseInventory)
            .map(::splitInventoryIntoCompartments)
            .map(::sumIncorrectPriorityItems)
            .sum()
      }

  fun commonItemTypePriority(rucksacks: List<List<Item>>): Int =
      findItemCommonToAll(rucksacks).priority

  private fun findItemCommonToAll(rucksacks: List<List<Item>>): Item {
    val first = rucksacks.first()
    val rest = rucksacks.drop(1)
    return first.first { item -> rest.all { it.contains(item) } }
  }

  fun sumBadgePrioritiesFromInventory(filename: String) =
      File(filename).useLines {
        it.map(::parseInventory).chunked(3).map(::commonItemTypePriority).sum()
      }
}
