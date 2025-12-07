package day1.part2

import java.io.File
import kotlin.text.toIntOrNull

fun main() {
  val inputFile = File("input.txt")

  inputFile.useLines() { println(countSecretPassword(it.toList()))}
}

fun countSecretPassword(lines : List<String>): Int{
  var password = 0
  var current = 50;
  var i = 0;

  for (line in lines) {
    val turnLeft = line.first() == 'L'
    var distance = line.drop(1).toInt()

    password += distance / 100
    distance = distance % 100
    
    var new = 0;
    if (turnLeft) {
        new = current - distance
        if (new <= 0 && current != 0) {
          password++
        }
    }
    else {
        new = current + distance
        if (new >= 100) {
          password++
        }
    }

    println("Current $current - turn $line - new $new - password $password")

    current = ((new % 100) + 100) % 100  

    // i++
    // if (i >= 100) {
    //   break
    // }
  }

  return password
}
