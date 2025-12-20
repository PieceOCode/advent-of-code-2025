package day6.part2

import java.io.File
import kotlin.collections.dropLast
import kotlin.jvm.internal.iterator

fun main() {
  val terms = readInput()
  val result = terms.map { calculateTerm(it) }.sum()
  println(result)
}

fun calculateTerm(term: Term): Long {
  val shouldMultiply = term.operand == '*'
  var current = if (shouldMultiply) 1L else 0L
  for (value in term.values) {
    if (shouldMultiply) {
      current *= value
    } else {
      current += value
    }
  }

  printTermWithResult(term, current)
  return current
}

fun readInput(): List<Term> {
  val file = File("input.txt")
  val lines = file.readLines()

  val iterators = lines.map { it.iterator() }

  var currentOperand = '+'
  var currentValues: MutableList<Long> = mutableListOf()
  val terms: MutableList<Term> = mutableListOf()

  while (iterators.all { it.hasNext() }) {
    val column = iterators.map { it.next() }

    if (column.last() != ' ') {
      currentOperand = column.last()
    }

    if (column.all { it == ' ' }) {
      terms.add(Term(currentValues.toList(), currentOperand))
      currentValues.clear()
    } else {
      val value = column.dropLast(1).joinToString("").trim().toLong()
      currentValues.add(value)
    }
  }

  terms.add(Term(currentValues.toList(), currentOperand))
  return terms
}

fun printTermWithResult(term: Term, result: Long) {
  term.values.dropLast(1).forEach { print("${it} ${term.operand} ") }
  print(term.values.last())
  println(" = ${result}")
}

data class Term(val values: List<Long>, val operand: Char)
