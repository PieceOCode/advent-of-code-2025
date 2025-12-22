package day7.part1

import java.io.File

fun main() {
    val manifold = readInput()
    simulateAlternativeTimelines(manifold)
    printManifold(manifold)

    val timeLines = manifold.last().sum()
    println("There are ${timeLines} different time lines.")
}

fun simulateAlternativeTimelines(manifold: List<MutableList<Long>>) {
    for (rowIndex in 1..manifold.size - 1) {
        val row = manifold[rowIndex]
        val lasers = manifold[rowIndex - 1]
        for (i in 0..row.size - 1) {
            if (lasers[i] >= 1L) {
                if (row[i] == -1L) {
                    if (i >= 1) row[i - 1] += lasers[i]
                    if (i < row.size - 1) row[i + 1] += lasers[i]
                } else {
                    row[i] += lasers[i]
                }
            }
        }
    }
}

fun readInput(): List<MutableList<Long>> {
    val file = File("input.txt")
    var lines = file.readLines().map { it.toCharArray() }
    var linesWithNumbers = lines.map { it.map{ getCharacterValue(it) }.toMutableList() }
    return linesWithNumbers
}

fun getCharacterValue(char: Char) : Long {
    if (char == '^') return -1L;
    else if (char == 'S') return 1L;
    else return 0L;
}

fun printManifold(manifold: List<MutableList<Long>>) {
    val paddedManifold = manifold.map{ it.map{ if (it.toString().length == 1) " " + it.toString() else it.toString()  }}
    val manifoldWithSplitters = paddedManifold.map{ it.map{ if (it == "-1") " ^" else it}}
    manifoldWithSplitters.forEach{ println(it.joinToString(",")) }
}