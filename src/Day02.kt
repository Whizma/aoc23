// test if implementation meets criteria from the description, like:

fun main() {

    fun game(line: String): Int {
        var id = ""
        if (line[5].isDigit()) {
            id = line[5].toString()
            if (line[6].isDigit()) {
                id = id + line[6].toString()
                if (line[7].isDigit()) {
                    id = id + line[7].toString()
                }
            }
        }
        return id.toInt()
    }

    fun part1(input: List<String>): Int {
        val red = 12
        val green = 13
        val blue = 14
        var result = 0
        for (line in input) {
            val id = game(line)
            var color = ""
            var amount = 0
            var shouldAdd = true
            for (char in line) {
                if (char.equals(',') || char.equals(':') || char.equals(';')) {
                    amount = 0
                    color = ""
                }
                if (char.equals('G')) {
                    shouldAdd = true
                }
                if (char.isDigit()) {
                    amount = if (amount != 0) {
                        (amount.toString() + char).toInt()
                    } else {
                        char.digitToInt()
                    }
                    color = ""
                }
                if (char.isLetter()) {
                    color += char
                }
                if (color.equals("red") && amount > red) {
                    shouldAdd = false
                }
                else if (color.equals("blue") && amount > blue) {
                    shouldAdd = false
                }
                else if (color.equals("green") && amount > green) {
                    shouldAdd = false
                }
            }
            if (shouldAdd)
                result += id
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var sumRed = 1
        var sumGreen = 1
        var sumBlue = 1
        var power = -1
        for (line in input) {
            val id = game(line)
            var color = ""
            var amount = 0
            for (char in line) {
                if (char.equals(',') || char.equals(':') || char.equals(';')) {
                    amount = 0
                    color = ""
                }
                if (char.isDigit()) {
                    amount = if (amount != 0) {
                        (amount.toString() + char).toInt()
                    } else {
                        char.digitToInt()
                    }
                    color = ""
                }
                if (char.isLetter()) {
                    color += char
                }
                if (color.equals("red") && amount >= sumRed) {
                    sumRed = amount
                }
                else if (color.equals("blue") && amount >= sumBlue) {
                    sumBlue = amount
                }
                else if (color.equals("green") && amount >= sumGreen) {
                    sumGreen = amount
                }
                if (char.equals('G')) {
                    power += (sumRed * sumBlue * sumGreen)
                    sumRed = 1
                    sumBlue = 1
                    sumGreen = 1
                }
            }
        }
        power += (sumRed * sumBlue * sumGreen)
        return power
    }

    val testInput = readInput("Day02_test")
    val input = readInput("Day02")
    part1(testInput).println()
    part1(input).println()
    part2(testInput).println()
    part2(input).println()
}
