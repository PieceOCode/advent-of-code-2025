package day3.part1

import java.io.File

fun main() {
  val input = readInput()
  val totalJoltage = input.map { calculateJoltage(it) }.sum()
  println(totalJoltage)
}

fun calculateJoltage(batterieJoltage: String): Int {
  val joltageValues = batterieJoltage.toCharArray().map { it.digitToInt() }

  val maximumJoltage = joltageValues.max()
  val indexOfMaximum = joltageValues.indexOf(maximumJoltage)

  if (indexOfMaximum >= joltageValues.size - 1) {
    val secondMaximum = joltageValues.dropLast(1).max()
    println("Maximum of ${batterieJoltage} is ${secondMaximum}${maximumJoltage}")
    return secondMaximum * 10 + maximumJoltage
  } else {
    val nextMaximum = joltageValues.drop(indexOfMaximum + 1).max()
    println("Maximum of ${batterieJoltage} is ${maximumJoltage}${nextMaximum}")
    return maximumJoltage * 10 + nextMaximum
  }
}

fun readInput(): List<String> {
  val file = File("input.txt")
  val lines = file.readLines()
  return lines
}
