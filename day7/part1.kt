package day7.part1

import java.io.File

fun main() {
  val manifold = readInput()
  val beamSplits = countBeamSplits(manifold)

  manifold.forEach{println(it.joinToString(""))}

  println("Amount of lasers at the end: ${manifold.last().count{ it == '|'}}")
  println("The beam was splitted ${beamSplits} times.")
}

fun countBeamSplits(manifold : List<MutableList<Char>>): Int {
  var beamSplits = 0

  for (rowIndex in 1..manifold.size-1) {
    val row = manifold[rowIndex]
    val lasers = manifold[rowIndex-1]
    for (i in 0..row.size-1) {
      if (lasers[i] == 'S' || lasers[i] == '|') {
        if (row[i] == '^') {
          beamSplits++
          if (i>=1)
            row[i-1] = '|'
          if (i<row.size-1)
            row[i+1] = '|'
        } else {
          row[i] = '|'
        }
      }
    }
  }

  return beamSplits
}

fun readInput(): List<MutableList<Char>> {
  val file = File("input.txt")
  val lines = file.readLines().map{ it.toCharArray().toMutableList() }
  return lines
}
