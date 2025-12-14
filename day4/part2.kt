package day3.part1

import java.io.File

fun main() {
  val input = readInput()

  var accessibleRollsTotal = 0
  var accessibleRolls = 0
  do {
    accessibleRolls = markAccessibleRolls(input)
    // println("Current Grid:")
    // printGrid(input)
    removeMarkedRolls(input)
    accessibleRollsTotal += accessibleRolls
  } while (accessibleRolls > 0)
  println(accessibleRollsTotal)
}

val adjacentCoordinates = listOf(-1 to 1, 0 to 1, 1 to 1, -1 to 0, 1 to 0, -1 to -1, 0 to -1, 1 to -1)

fun markAccessibleRolls(grid: Array<CharArray>): Int {
  var accessibleRolls = 0
  val height = grid.size
  val width = grid[0].size
  for (x in 0..width-1) {
    for (y in 0..height-1) {
      if (grid[y][x] == '.') continue

      var adjacentRollsCount = 0;
      
      for ((dx, dy) in adjacentCoordinates) {
        if (x + dx < 0 || x + dx >= width || y + dy < 0 || y + dy >= height) {
          continue
        }

        if (grid[y + dy][x + dx] == '@' || grid[y + dy][x + dx] == 'x') {
          adjacentRollsCount++
        }
      }
      
      if (adjacentRollsCount < 4) {
        accessibleRolls++
        grid[y][x] = 'x';
      }
    }
  }

  return accessibleRolls
}

fun removeMarkedRolls(grid: Array<CharArray>) {
  for (row in grid) {
    for (i in 0..row.size-1) {
      if (row[i] == 'x') row[i] = '.'
    }
  }
}

fun printGrid(grid: Array<CharArray>) {
  for (row in grid) {
    for (item in row) {
      print(item)
    }
    println("")
  }
}

fun readInput(): Array<CharArray> {
  val file = File("input.txt")
  val lines = file.readLines()
  return lines.map{ it.toCharArray()}.toTypedArray()
}
