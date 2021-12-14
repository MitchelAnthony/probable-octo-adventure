import java.lang.IllegalStateException

fun main() {
    // Run against the sample input to verify the code works
    val testInput = readInput("day4_input_sample")
    val testOutput = calculateWinningBoardScore(testInput)
    check(testOutput[0] == 4512)
    check(testOutput[1] == 1924)

    // Run this against your personal input data to get the results printed to the standard output
    val input = readInput("day4_input")
    val output = calculateWinningBoardScore(input)
    println(output[0])
    println(output[1])
}

class Board(private val numbers: MutableList<Int>) {
    private var state = 0U

    init {
        check(numbers.size == 25) { "A Board needs 25 numbers to work." }
    }

    /**
     * Returns the index of the drawnNumbers that triggered the bingo
     */
    fun findWinningNumberIndex(drawnNumbers: List<Int>, winningBoardPatterns: UIntArray): Int {
        for (index in drawnNumbers.indices) {
            val numberIndex = numbers.indexOf(drawnNumbers[index])
            if (numberIndex > -1) {
                state = (1U shl numberIndex) or state
                numbers[numberIndex] = 0

                for (boardPattern in winningBoardPatterns) {
                    if (boardPattern and state == boardPattern) {
                        return index
                    }
                }


            }
        }

        return -1
    }

    fun getSumOfNumbers(): Int {
        return numbers.sum()
    }
}

/**
 * Returns an array with the score for the first and last winning boards
 */
fun calculateWinningBoardScore(input: List<String>): IntArray {
    if (input.isEmpty()) {
        throw IllegalStateException("The input list cannot be empty.")
    }

    val drawnNumbers = parseDrawnNumbers(input.first())
    val boards = parseBoards(input.subList(1, input.size))

    val winningBoardPatterns = uintArrayOf(
        0b00000_00000_00000_00000_11111U, // top row
        0b00000_00000_00000_11111_00000U,
        0b00000_00000_11111_00000_00000U,
        0b00000_11111_00000_00000_00000U,
        0b11111_00000_00000_00000_00000U, // bottom row

        0b00001_00001_00001_00001_00001U, // right column
        0b00010_00010_00010_00010_00010U,
        0b00100_00100_00100_00100_00100U,
        0b01000_01000_01000_01000_01000U,
        0b10000_10000_10000_10000_10000U, // left column
    )

    var bestBoard: Board? = null
    var worstBoard: Board? = null

    var winningNumberIndex = drawnNumbers.size
    var worstNumberIndex = -1
    for (board in boards) {
        val index = board.findWinningNumberIndex(drawnNumbers, winningBoardPatterns)
        if (index < winningNumberIndex) {
            bestBoard = board
            winningNumberIndex = index
        }

        if (index >= worstNumberIndex) {
            worstBoard = board
            worstNumberIndex = index
        }
    }

    return intArrayOf(
        drawnNumbers[winningNumberIndex] * (bestBoard?.getSumOfNumbers() ?: 0),
        drawnNumbers[worstNumberIndex] * (worstBoard?.getSumOfNumbers() ?: 0),
    )
}

fun parseDrawnNumbers(drawnNumbers: String): List<Int> {
    return drawnNumbers.split(",").map { it.toInt(10) }
}

fun parseBoards(boardsNumbers: List<String>): List<Board> {
    val boards = emptyList<Board>().toMutableList()
    for (i in 0..boardsNumbers.lastIndex step 6) {
        val numbers = emptyList<Int>().toMutableList()
        for (j in i + 1..i + 5) {
            numbers.addAll(boardsNumbers[j].trim().split(Regex(""" +""")).map { it.toInt(10) })
        }
        boards.add(Board(numbers))
    }

    return boards
}
