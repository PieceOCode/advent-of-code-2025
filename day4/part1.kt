package day4.part1

import java.io.File

fun main() {
  val input = readInput()
  println(countAccessableRolls(input))
}

val adjacentCoordinates = listOf(-1 to 1, 0 to 1, 1 to 1, -1 to 0, 1 to 0, -1 to -1, 0 to -1, 1 to -1)

fun countAccessableRolls(grid: List<List<Char>>): Int {
  var accessibleRolls = 0
  val height = grid.size
  val width = grid[0].size
  for (x in 0..width-1) {
    for (y in 0..height-1) {
      if (grid[y][x] != '@') continue

      var adjacentRollsCount = 0;
      
      for ((dx, dy) in adjacentCoordinates) {
        if (x + dx < 0 || x + dx >= width || y + dy < 0 || y + dy >= height) {
          continue
        }

        if (grid[y + dy][x + dx] == '@') {
          adjacentRollsCount++
        }
      }
      
      if (adjacentRollsCount < 4) {
        accessibleRolls++
      }
    }
  }

  return accessibleRolls
}

fun readInput(): List<List<Char>> {
  val file = File("input.txt")
  val lines = file.readLines()
  return lines.map{ it.toList()}
}
