fun main() {
    // Run against the sample input to verify the code works
    val testInput = readInput("day3_input_sample")
//    check(calculatePowerConsumption(testInput, calculateBitFrequencies(testInput)) == 198)
    println(calculateLifeSupportRating(convertInputToInts(testInput)))
    check(calculateLifeSupportRating(convertInputToInts(testInput)) == 230)

    // Run this against your personal input data to get the results printed to the standard output
    val input = readInput("day3_input")
//    check(calculatePowerConsumption(input, calculateBitFrequencies(input)) == 2648450) // part 1
//    println(calculateLifeSupportRating(convertInputToInts(input))) // part 2
}

fun calculatePowerConsumption(input: List<String>, bitFrequencies: IntArray): Int {
    val gammaRate = bitFrequencies
        .map { if (it >= input.size / 2) 1 else 0 }
        .joinToString("")

    val epsilonRate = gammaRate
        .map { if (it == '1') '0' else '1' }
        .joinToString("")

    return gammaRate.toInt(2) * epsilonRate.toInt(2)
}

fun calculateBitFrequencies(input: List<String>): IntArray {
    if (input.isEmpty()) {
        return IntArray(0)
    }

    val bitFrequencies = IntArray(input[0].length);
    for (bits in input) {
        for (index in bits.indices) {
            if (bits[index] == '1') {
                bitFrequencies[index]++
            }
        }
    }

    return bitFrequencies
}

@JvmName("calculateBitFrequenciesListInt")
fun calculateBitFrequencies(input: List<Int>): IntArray {
    if (input.isEmpty()) {
        return IntArray(0)
    }

    return intArrayOf(1,2,3)
}

fun convertInputToInts(input: List<String>): List<Int> {
    return input.map { it.toInt(2) }
}

fun calculateLifeSupportRating(input: List<Int>): Int {
//    val oneMostCommon= bitFrequencies.map { it >= input.size / 2 }
//    val zeroMostCommon = bitFrequencies.map { it <= input.size / 2 }
return 0
//    return calculateOxygenGeneratorRating(input) * calculateCO2ScrubberRating(input)
}

// We need to recalculate bit frequencies after filtering...
//fun calculateOxygenGeneratorRating(input: List<Int>, oneMostCommon: List<Boolean>, filter: (Int) -> Boolean): Int {
//fun calculateOxygenGeneratorRating(input: List<Int>): Int {
//    var filteredInput = input
//    for (index in oneMostCommon.indices) {
//        if (filteredInput.size == 1) {
//            break
//        }
//
//        // TODO Calculate frequencies
//
//        val flag = 1 shl ((oneMostCommon.size - 1) - index)
//        // oneMostCommon: if true, 1 is most common. if false, 0 is most common
//        filteredInput = filteredInput.filter { (oneMostCommon[index] && it and flag > 0) || (!oneMostCommon[index] && it and flag == 0) }
//    }
//
//    return filteredInput.first()
//}

//fun calculateCO2ScrubberRating(input: List<Int>): Int {
//    var filteredInput = input
//    for (index in zeroMostCommon.indices) {
//        if (filteredInput.size == 1) {
//            break
//        }
//
//        val flag = 1 shl ((zeroMostCommon.size - 1) - index)
//        // zeroMostCommon: if true, 0 is most common. if false, 1 is most common
//        filteredInput = filteredInput.filter { (zeroMostCommon[index] && it and flag == 0) || (!zeroMostCommon[index] && it and flag > 0) }
//    }
//
//    return filteredInput.first()
//}
