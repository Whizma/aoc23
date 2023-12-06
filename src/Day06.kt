
fun main() {

    fun part1(input: List<String>): Int {
        val times = input[0].split("\\s+".toRegex())
            .filter { it.isNotEmpty() && it.matches("-?\\d+".toRegex()) }
            .map { it.toInt() }
        val distances = input[1].split("\\s+".toRegex())
            .filter { it.isNotEmpty() && it.matches("-?\\d+".toRegex()) }
            .map { it.toInt() }
        var res = 1
        for (time in times.withIndex()) {
            val record = distances[time.index]
            var recordsBeaten = 0
            for (i in 0..time.value) {
                var speed = i
                var timeToMove = time.value - speed
                var coveredDistance = speed * timeToMove
                if (coveredDistance > record) {
                    recordsBeaten++
                }
            }
            res = res * recordsBeaten
        }
        return res
    }

    fun part2(input: List<String>): Int {
        val time = input[0].filter { it.isDigit() }.toLong()
        val distance = input[1].filter {  it.isDigit() }.toLong()
        var res = 1
        val record = distance
        var recordsBeaten = 0
        for (i in 0..time) {
            var speed = i
            var timeToMove = time - speed
            var coveredDistance = speed * timeToMove
            if (coveredDistance > record) {
                recordsBeaten++
            }
        }
        res = res * recordsBeaten
        return res
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    val input = readInput("Day06")
    part1(testInput).println()
    part1(input).println()
    part2(testInput).println()
    part2(input).println()
}

