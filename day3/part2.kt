package day3.part2

import java.io.File

fun main() {
  val input = readInput()
  val totalJoltage =
          input
                  .map { it.toCharArray().map { it.digitToInt() } }
                  .map { calculateJoltage(it, 11) }
                  .onEach { println(it) }
                  .sum()
  println(totalJoltage)
}

fun calculateJoltage(joltageValues: List<Int>, index: Int): Long {
  if (index == 0) return joltageValues.max().toLong()

  val maximumJoltage = joltageValues.dropLast(index).max()
  val indexOfMaximum = joltageValues.dropLast(index).indexOf(maximumJoltage)

  // println("Maximum Joltage ${maximumJoltage} of battery: ${joltageValues}")
  return maximumJoltage * Math.pow(10.toDouble(), index.toDouble()).toLong() +
          calculateJoltage(joltageValues.drop(indexOfMaximum + 1), index - 1)
}

fun readInput(): List<String> {
  val file = File("input.txt")
  val lines = file.readLines()
  return lines
}
