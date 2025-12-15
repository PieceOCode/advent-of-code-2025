package day5.part2

import java.io.File

fun main() {
  val lines = readInput()
  val freshIngredientRanges = readIngredientRanges(lines)
  val mergedFreshIngredientRanges = mergeIngredientRanges(freshIngredientRanges)
  println(mergedFreshIngredientRanges)

  val freshIngredientCount = countFreshIngredients(mergedFreshIngredientRanges)
  println(freshIngredientCount)
}

fun readInput(): List<String> {
  val file = File("input.txt")
  return file.readLines()
}

fun readIngredientRanges(input: List<String>): List<Pair<Long, Long>> {
  val indexOfEmptyLine = input.indexOfFirst{ it.length == 0 }

  return input
    .take(indexOfEmptyLine)
    .map { it.split("-") }
    .map{ it[0].toLong() to it[1].toLong()}
}

// Merging is suboptimal because it always loops through all elements. 
// Better solution would be to sort the ranges by their start and then loop through them once
// Only adjacent ranges (or a group of adjacent ranges) can overlap.
fun mergeIngredientRanges(freshIngredientRanges: List<Pair<Long,Long>>): List<Pair<Long, Long>> {
  var mutableRanges = freshIngredientRanges.toMutableList()
  var wasMerged = true

  while(wasMerged) {
    wasMerged = false
    println("Starting again with ${mutableRanges}")
    for (i in 0..mutableRanges.size-1) {
      val indexOfIntersectingRange = mutableRanges.drop(i+1).indexOfFirst {!(mutableRanges[i].second < it.first || mutableRanges[i].first > it.second)}
      if (indexOfIntersectingRange >= 0) {
        val secondRange = mutableRanges[i+1+indexOfIntersectingRange]
        val firstRange = mutableRanges[i]

        val min = minOf(firstRange.first, secondRange.first)
        val max = maxOf(firstRange.second, secondRange.second)
        mutableRanges[i+1+indexOfIntersectingRange] = min to max
        mutableRanges.removeAt(i)
        println("${firstRange} at ${i} and ${secondRange} at ${i+1+indexOfIntersectingRange} were merged to ${min to max}")
        wasMerged = true
        break
      } else {
        val firstRange = mutableRanges[i]
        println("${firstRange} is not intersecting")
      }
    }
  }

  return mutableRanges.toList()
  
}

fun countFreshIngredients(freshIngredientRanges: List<Pair<Long, Long>>): Long {
  return freshIngredientRanges
    .map{ it.second - it.first + 1}
    .sum()
}