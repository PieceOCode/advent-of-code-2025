package day6.part1

import java.io.File
import kotlin.jvm.internal.iterator

fun main() {
  val items = readInput()
  val result = calculate(items)
  println(result)
}

fun calculate(items: List<Sequence<String>>): Long {
  var total = 0L

  val iterators = items.map { it.iterator() }
  while (iterators.all { it.hasNext() }) {
    val items = iterators.map { it.next() }
    val multiply = items.last().equals("*")

    total += calculateTerm(items.dropLast(1).map { it.toLong() }, multiply)
  }

  return total
}

fun calculateTerm(values: List<Long>, shouldMultiply: Boolean): Long {
  var current = if (shouldMultiply) 1L else 0L
  for (term in values) {
    if (shouldMultiply) {
      current *= term.toLong()
    } else {
      current += term.toLong()
    }
  }
  // println(current)
  return current
}

fun readInput(): List<Sequence<String>> {
  val file = File("input.txt")
  val lines = file.readLines()

  val whitespacePattern = "\\s+".toRegex()
  return lines.map { it.splitToSequence(whitespacePattern) }
}
