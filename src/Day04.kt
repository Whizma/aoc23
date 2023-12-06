fun main() {

    fun part1(input: List<String>): Int {
        var totalPoints = 0

        for (line in input) {
            var groups = line.split("|", ":")
            val regex = Regex("\\d+")
            val match = regex.find(line)
            val cardId = match?.value?.toIntOrNull()

            var winners = groups[1].split(" ")
                .map { it.toIntOrNull() }
                .filterNotNull()
            var numbers = groups[2].split(" ")
                .map { it.toIntOrNull() }
                .filterNotNull()

            var matches = 0
            for (number in numbers) {
                if (winners.contains(number)) {
                    matches++
                }
            }
            fun calculateValue(number: Int): Number {
                return when (number) {
                    0 -> 0
                    1 -> 1
                    else -> Math.pow(2.0, (number - 1).toDouble())
                }
            }
            totalPoints += calculateValue(matches).toInt()
        }
        return totalPoints
    }

    fun countMatches(cardLine: String): Int {
        val (_, cards) = cardLine.split(": ")
        val (win, mine) = cards.split("|").map { group ->
            group.trim().split("\\s+".toRegex()).map { num ->
                num.toInt()
            }
        }
        val winSet = win.toSet()
        return mine.count { it in winSet }
    }

    fun part2(input: List<String>): Int {
        val cardCopies = IntArray(input.size) { 1 }
        for ((index, line) in input.withIndex()) {
            val matches = countMatches(line)
            if (matches > 0) {
                for (i in 1..matches) {
                    if (index + i >= cardCopies.size) break
                    cardCopies[index + i] += cardCopies[index]
                }
            }
        }
        return cardCopies.sum()
    }

    val testInput = readInput("Day04_test")
    val input = readInput("Day04")
    part1(testInput).println()
    part1(input).println()
    part2(testInput).println()
    part2(input).println()
}