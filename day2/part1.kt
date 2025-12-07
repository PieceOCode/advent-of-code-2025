package day2.part1

import java.io.File

fun main() {
    val intervals = readInput()
    var result = 0L

    for (interval in intervals) {
        for (number:Long in interval.first..interval.second) {
            val numberAsString = number.toString()
            if (numberAsString.length % 2 != 0) {
                continue
            }

            if (numberAsString.substring(0, numberAsString.length/2) == numberAsString.substring(numberAsString.length/2)) {
                result += number
            }
        }
        println(result)
    }
}

fun readInput() : List<Pair<Long, Long>> {
    val inputFile = File("input.txt")
    val lines = inputFile.readLines()
    val numberPairs = lines.get(0).split(",")

    return numberPairs
        .map{ it.split("-") }
        .map{ it[0].toLong() to it[1].toLong() }
}