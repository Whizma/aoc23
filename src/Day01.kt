

fun main() {
    fun part1(input: List<String>): Int {
        var sum: Int = 0
        var digit1: Char = 'a'
        var digit2: Char = 'a'
        for (line in input) {
            for (char in line) {
                if (char.isDigit()) {
                    digit1 = char
                    break;
                }
            }
            for (char in line.reversed()) {
                if (char.isDigit()) {
                    digit2 = char
                    break;
                }
            }
            sum += (digit1.toString() + digit2).toInt()
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum: Int = 0
        var digit1: Char = 'a'
        var digit2: Char = 'a'
        val conversionTable = mutableMapOf<String, String>()
        conversionTable["one"] = "1e"
        conversionTable["two"] = "2o"
        conversionTable["three"] = "3e"
        conversionTable["four"] = "4r"
        conversionTable["five"] = "5e"
        conversionTable["six"] = "6x"
        conversionTable["seven"] = "7n"
        conversionTable["eight"] = "8t"
        conversionTable["nine"] = "9e"

        for (line in input) {
            var line2 = line
            var current = 0
            while (current < line2.length) {
                val matchingKey = conversionTable.keys.find { line2.substring(current).startsWith(it) }
                if (matchingKey != null) {
                    line2 = line2.replaceFirst(matchingKey, conversionTable[matchingKey]!!)
                    current += conversionTable[matchingKey]!!.length
                } else {
                    current++
                }
            }
            for (char in line2) {
                if (char.isDigit()) {
                    digit1 = char
                    break;
                }
            }
            for (char in line2.reversed()) {
                if (char.isDigit()) {
                    digit2 = char
                    break;
                }
            }
            println(line)
            println(line2)
            println((digit1.toString() + digit2).toInt());
            sum += (digit1.toString() + digit2).toInt()
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    val input = readInput("Day01")
    part1(testInput).println()
    part1(input).println()
    part2(testInput).println()
    part2(input).println()
}
