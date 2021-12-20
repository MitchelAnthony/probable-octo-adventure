fun main() {
    // Run against the sample input to verify the code works
    val testInput = readInput("day6_input_sample").first()
    check(calculateNumberOfLanternFish(testInput, 18) == 26L)
    check(calculateNumberOfLanternFish(testInput) == 5934L)
//    check(calculateNumberOfLanternFish(testInput, 256) == 26984457539)
//    println(calculateNumberOfLanternFish(testInput, 256))

    // Run this against your personal input data to get the results printed to the standard output
    val input = readInput("day6_input").first()
    check(calculateNumberOfLanternFish(input) == 389726L)
//    println(calculateNumberOfLanternFish(input))
//    println(calculateNumberOfLanternFish(input, 256))
}

data class Lanternfish(var daysToSpawn: Int) {
    fun subtractDay() {
        daysToSpawn = if (daysToSpawn == 0) 6 else --daysToSpawn
    }
}

//data class LanternfishState(var currentTotal: Long, val currentState: List<Int>)

fun calculateNumberOfLanternFish(initialState: String, daysToSimulate: Int = 80): Long {
    val lanternfishStates = initialState.split(",").map { it.toInt() }
    var totalFish = 0L

    val stepSize = 100
    val ranges = mutableListOf<IntRange>()
    for (startOfRange in 0..daysToSimulate step stepSize) {
        ranges.add(if (startOfRange + stepSize > daysToSimulate) startOfRange..daysToSimulate else startOfRange..startOfRange + stepSize)
    }

    for (range in ranges) {
//        totalFish += calculateNumberOfLanternFishInBatch(lanternfishStates, stepSize)
    }

    return totalFish
}

fun calculateNumberOfLanternFishInBatch(lanternfishStates: List<Int>, daysToSimulate: Int = 80): Long {
    val uniqueLanternfishStates = lanternfishStates.toSortedSet()
    val uniqueLanternfishStatesResults = mutableMapOf<Int, Long>()

    for (uniqueState in uniqueLanternfishStates) {
        val lanternfish = mutableListOf(Lanternfish(uniqueState))

        for (day in 1..daysToSimulate) {
            val newFish = mutableListOf<Lanternfish>()
            for (fish in lanternfish) {
                if (fish.daysToSpawn == 0) {
                    // Spawn
                    newFish.add(Lanternfish(8))
                }

                fish.subtractDay()
            }

            lanternfish.addAll(newFish)
        }

        uniqueLanternfishStatesResults[uniqueState] = lanternfish.size.toLong()
    }

    var totalFish = 0L
    for (state in lanternfishStates) {
        if (uniqueLanternfishStatesResults.containsKey(state)) {
            totalFish += uniqueLanternfishStatesResults[state]!!
        }
    }

    return totalFish
}