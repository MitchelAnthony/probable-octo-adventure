fun main() {
    // Run against the sample input to verify the code works
    val testInput = readInput("day5_input_sample")
    check(parseInput(testInput) == 5)
    check(parseInput(testInput, true) == 12)

    // Run this against your personal input data to get the results printed to the standard output
    val input = readInput("day5_input")
    println(parseInput(input))
    println(parseInput(input, true))
}

data class Point(val x: Int, val y: Int)

fun parseInput(input: List<String>, includeDiagonalLines: Boolean = false): Int {
    val grid = HashMap<Point, Int>()
    for (lineDescription in input) {
        val points = parseLine(lineDescription, includeDiagonalLines)
        for (point in points) {
            if (grid.containsKey(point)) {
                val count = grid.getOrDefault(point, 0) + 1
                grid[point] = count
            } else {
                grid[point] = 1
            }
        }
    }

    var overlapCount = 0
    for ((_, count) in grid) {
        if (count > 1) {
            overlapCount++
        }
    }

    return overlapCount
}

fun parseLine(lineDescription: String, includeDiagonalLines: Boolean): List<Point> {
    val points = lineDescription.split(" -> ").map { parsePoint(it) }.toMutableList()

    val first = points.first()
    val second = points.last()
    if (first.x == second.x) {
        // Only Y has changed
        val range = if (first.y < second.y) first.y + 1 until second.y else second.y + 1 until first.y
        for (y in range) {
            points.add(Point(first.x, y))
        }
    } else if (first.y == second.y) {
        // Only X has changed
        val range = if (first.x < second.x) first.x + 1 until second.x else second.x + 1 until first.x
        for (x in range) {
            points.add(Point(x, first.y))
        }
    } else if (includeDiagonalLines) {
        points.clear()
        var x = first.x
        var y = first.y
        points.add(Point(x, y))
        while (x != second.x && y != second.y) {
            if (first.x < second.x) ++x else --x
            if (first.y < second.y) ++y else --y
            points.add(Point(x, y))
        }
    } else {
        points.clear()
    }

    return points
}

fun parsePoint(pointDescription: String): Point {
    val pointXY = pointDescription.split(",").map { it.toInt() }

    return Point(pointXY[0], pointXY[1])
}
