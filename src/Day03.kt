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
    fun findNumber(matrix: List<CharArray>, row: Int, col: Int): String {
        var number = ""

        var currentCol = col
        while(currentCol > 0 && matrix[row][currentCol -1].isDigit()) {
            currentCol--
        }

        while(currentCol < matrix[0].size && matrix[row][currentCol].isDigit()) {
            number += matrix[row][currentCol]
            currentCol++
        }
        return number
    }

    fun getAdjacentNumbers(matrix: List<CharArray>, row: Int, col: Int): List<Int> {

        val nums = mutableListOf<Int>()

        var i = -1
        while(i <= 1) {
            var j = -1
            while (j <= 1) {
                if(i == 0 && j == 0) {
                    j++
                }
                val newRow = row + i
                val newCol = col + j

                if(matrix[newRow][newCol].isDigit()) {
                    val number = findNumber(matrix, newRow, newCol)
                    nums.add(number.toInt())
                    var tempCol = newCol
                    while (matrix[newRow][tempCol].isDigit()){
                        tempCol++
                        if(tempCol >= matrix[0].size) {
                            tempCol--
                            break
                        }
                    }

                    j += tempCol - newCol
                }
                j++
            }
            i++
        }

        return nums
    }

    fun part2(lines: List<String>): Int {
        val schematic = lines.map { it.toCharArray() }

        var sum = 0
        for(i in schematic.indices) {
            for(j in schematic[0].indices) {
                if(schematic[i][j] == '*') {
                    val adjNums = getAdjacentNumbers(schematic, i, j)
                    if(adjNums.size > 1) {
                        sum += adjNums.reduce { acc, index -> acc * index }
                    }
                }
            }
        }
        return sum
    }

    val testInput = readInput("Day03_test")
    val input = readInput("Day03")
    part1(testInput).println()
    part1(input).println()
    part2(testInput).println()
    part2(input).println()
}