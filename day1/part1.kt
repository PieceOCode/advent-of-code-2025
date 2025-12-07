package day1.part1

import java.io.File
import kotlin.text.toIntOrNull

fun main() {
  val inputFile = File("input.txt")

  inputFile.useLines() { println(countSecretPassword(it.toList()))}
}

fun countSecretPassword(lines : List<String>): Int{
  var password = 0
  var current = 50;

  for (line in lines) {
    val turnLeft = line.first() == 'L'
    val distance = line.drop(1).toInt()
    current = turn(current, turnLeft, distance)

    if (current == 0) {
      password++
    }  
  }

  return password
}

fun turn (current: Int, left: Boolean, distance: Int):Int {
  val new = if (left) current - distance else current + distance
  return new % 100  
}