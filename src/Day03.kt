fun main() {

    fun part1(input: List<String>): Int {
        val schematic: List<CharArray> = input.map { it.toCharArray() }
        val rows = schematic.size
        val cols = schematic[0].size
        val map = HashMap<Pair<Int,Int>, Int>()
        for (i in 0 until rows) {
            var number = ""
            for (j in 0 until cols) {
                val char = schematic[i][j]
                if (char.isDigit()) {
                    number += char
                    if (j == cols - 1) {
                        map[Pair(i,j)] = number.toString().toInt()
                    }
                } else {
                    if (number.isNotEmpty()) {
                        map[Pair(i,j - 1)] = number.toString().toInt()
                        number = ""
                    }
                }
            }
        }
        println(map.toString())
        var sum = 0
        outer@ for ((key, value) in map) {
            var rightmostCol = (key.second + 1).coerceIn(0, cols - 1)
            var leftmostCol = (key.second - (value.toString().length)).coerceIn(0, cols - 1)
            var upperRow = (key.first - 1).coerceIn(0, rows - 1)
            var underRow = (key.first + 1).coerceIn(0, rows - 1)
            for (i in upperRow..underRow) {
                for (j in leftmostCol..rightmostCol) {
                    val char = schematic[i][j]
                    if (!char.isDigit() && !char.equals('.')) {
                        sum += value
                        continue@outer
                    }
                }
            }
        }
        return sum
    }
    fun part2(input: List<String>): Int {
        val schematic: List<CharArray> = input.map { it.toCharArray() }
        val rows = schematic.size
        val cols = schematic[0].size
        val map = HashMap<Pair<Int,Int>, Int>()
        for (i in 0 until rows) {
            var number = ""
            for (j in 0 until cols) {
                val char = schematic[i][j]
                if (char.isDigit()) {
                    number += char
                    if (j == cols - 1) {
                        map[Pair(i,j)] = number.toString().toInt()
                    }
                } else {
                    if (number.isNotEmpty()) {
                        map[Pair(i,j - 1)] = number.toString().toInt()
                        number = ""
                    }
                }
            }
        }
        var sum = 0
        outer@ for ((key, value) in map) {
            var rightmostCol = (key.second + 1).coerceIn(0, cols - 1)
            var leftmostCol = (key.second - (value.toString().length)).coerceIn(0, cols - 1)
            var upperRow = (key.first - 1).coerceIn(0, rows - 1)
            var underRow = (key.first + 1).coerceIn(0, rows - 1)
            for (i in upperRow..underRow) {
                for (j in leftmostCol..rightmostCol) {
                    val char = schematic[i][j]
                    if (!char.isDigit() && !char.equals('.')) {
                        sum += value
                        continue@outer
                    }
                }
            }
        }
        return sum
    }
    val testInput = readInput("Day03opog")
    val input = readInput("Day03")
    // part1(testInput).println()
    // part1(input).println()
    part2(testInput).println()
    part2(input).println()
}