package day2.part1

import java.io.File

data class Interval(val start: Long, val end: Long)

fun main() {
    val intervals = readInput()
    var result = intervals.asSequence()
      .flatMap { (it.start..it.end).asSequence() }
      .filter { isInvalidId(it) }
      .sum()

    println(result)
}

fun isInvalidId(number: Number) : Boolean {
  val numberAsString = number.toString()
  for (i in 1..numberAsString.length/2) {
    val substring = numberAsString.substring(0, i)
    var remaining = numberAsString;

    while (remaining.length >= i && remaining.startsWith(substring)) {
      remaining = remaining.substring(i)
    }

    if (remaining.isEmpty()) {
      // // println("Invalid id! ${numberAsString}")
      return true
    }
  }

  return false 
}

fun readInput() : List<Interval> {
    val inputFile = File("input.txt")
    val lines = inputFile.readLines()
    val numberPairs = lines.get(0).split(",")

    return numberPairs
        .map{ it.split("-") }
        .map{ Interval(it[0].toLong(), it[1].toLong()) }
}