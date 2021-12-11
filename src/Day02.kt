fun main() {
    // Run against the sample input to verify the code works
    val testInput = readInput("day2_input_sample")
    check(calculatePosition(testInput) == 150)
    check(calculatePositionWithAim(testInput) == 900)

    // Run this against your personal input data to get the results printed to the standard output
    val input = readInput("day2_input")
    println(calculatePosition(input)) // part 1
    println(calculatePositionWithAim(input)) // part 2
}

data class Position(var horizontal: Int, var vertical: Int, var aim: Int = 0)

fun calculatePosition(input: List<String>): Int {
    val position = Position(0, 0)

    for (instruction in input) {
        val (direction, amount) = instruction.split(" ")
        when (direction) {
            "forward" -> position.horizontal += amount.toInt()
            "up" -> position.vertical -= amount.toInt()
            "down" -> position.vertical += amount.toInt()
            else -> {
                continue
            }
        }
    }

    return position.horizontal * position.vertical
}

fun calculatePositionWithAim(input: List<String>): Int {
    val position = Position(0, 0)

    for (instruction in input) {
        val (direction, amount) = instruction.split(" ")
        when (direction) {
            "forward" -> {
                position.horizontal += amount.toInt()
                position.vertical += amount.toInt() * position.aim
            }
            "up" -> position.aim -= amount.toInt()
            "down" -> position.aim += amount.toInt()
            else -> {
                continue
            }
        }
    }

    return position.horizontal * position.vertical
}