fun main() {
    // Run against the sample input to verify the code works
    val testInput = readInput("day1_input_sample")
    check(calculateAmountOfIncreases(testInput) == 7)
    check(calculateAmountOfIncreases(createWindows(testInput)) == 5)

    // Run this against your personal input data to get the results printed to the standard output
    val input = readInput("day1_input")
    println(calculateAmountOfIncreases(input)) // part 1
    println(calculateAmountOfIncreases(createWindows(input))) // part 2
}

fun calculateAmountOfIncreases(input: List<String>): Int {
    var increases = 0
    var previousMeasurement = -1

    for (measurement in input) {
        if (previousMeasurement == -1) {
            previousMeasurement = measurement.toInt()

            continue
        }

        if (measurement.toInt() > previousMeasurement) {
            increases++
        }

        previousMeasurement = measurement.toInt()
    }

    return increases
}

fun createWindows(input: List<String>): List<String> {
    val windows = emptyList<String>().toMutableList()
    for(index in 0..input.size) {
        if (index + 3 > input.size) {
            break
        }

        val window = input.subList(index, index + 3)
        windows += (window[0].toInt() + window[1].toInt() + window[2].toInt()).toString()
    }

    return windows
}