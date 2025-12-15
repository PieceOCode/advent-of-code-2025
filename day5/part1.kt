package day5.part1

import java.io.File

fun main() {
  val lines = readInput()
  val freshIngredientRanges = readIngredientRanges(lines)
  val ingredients = readIngredients(lines)

  val freshIngredientCount = countFreshIngredients(freshIngredientRanges, ingredients)
  println(freshIngredientCount)
}

fun readInput(): List<String> {
  val file = File("input.txt")
  return file.readLines()
}

fun readIngredientRanges(input: List<String>): List<Pair<Long, Long>> {
  val indexOfEmptyLine = input.indexOfFirst{ it.length == 0 }
  println(indexOfEmptyLine)

  return input
    .take(indexOfEmptyLine)
    .map { it.split("-") }
    .map{ it[0].toLong() to it[1].toLong()}
}

fun readIngredients(input: List<String>): List<Long> {
  val indexOfEmptyLine = input.indexOfFirst{ it.length == 0 }

  return input
    .drop(indexOfEmptyLine + 1)
    .map{ it.toLong() }
}

fun countFreshIngredients(freshIngredientRanges: List<Pair<Long, Long>>, ingredients: List<Long>): Long {
  return ingredients 
    .map{ ingredient -> freshIngredientRanges.any { it.first <= ingredient && ingredient <= it.second }}
    .map{ if (it) 1L else 0L}
    .sum()
}