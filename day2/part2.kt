package day2.part1

import java.io.File

fun main() {
    val intervals = readInput()
    var result = 0L

    for (interval in intervals) {
        for (number:Long in interval.first..interval.second) {
            if (isInvalidId(number)) {
                result += number
            }
        }
        println(result)
    }
}

fun isInvalidId(number: Number) : Boolean {
  val numberAsString = number.toString()
  // println(number)
  for (i in 1..numberAsString.length/2) {
    val substring = numberAsString.substring(0, i)
    var remainingString = numberAsString;

    while (remainingString.length > 0) {
      // println("${i}: ${substring} of ${remainingString}")

      if (substring.length <= remainingString.length && substring == remainingString.substring(0, i)) {
        remainingString = remainingString.substring(i)
      } else {
        break
      }
    }

    if (remainingString.length == 0) {
      println("Invalid id! ${numberAsString}")
      return true
    }
  }

  return false 
}

fun readInput() : List<Pair<Long, Long>> {
    val inputFile = File("input.txt")
    val lines = inputFile.readLines()
    val numberPairs = lines.get(0).split(",")

    return numberPairs
        .map{ it.split("-") }
        .map{ it[0].toLong() to it[1].toLong() }
}