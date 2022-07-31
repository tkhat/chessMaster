package com.khatiashvili.chessapp.testmain

fun main() {

    val matrix8x8 = listOf(
        listOf("0-0", "0-1", "0-2", "0-3", "0-4", "0-5", "0-6", "0-7"),
        listOf("1-0", "1-1", "1-2", "1-3", "1-4", "1-5", "1-6", "1-7"),
        listOf("2-0", "2-1", "2-2", "2-3", "2-4", "2-5", "2-6", "2-7"),
        listOf("3-0", "3-1", "3-2", "3-3", "3-4", "3-5", "3-6", "3-7"),
        listOf("4-0", "4-1", "4-2", "4-3", "4-4", "4-5", "4-6", "4-7"),
        listOf("5-0", "5-1", "5-2", "5-3", "5-4", "5-5", "5-6", "5-7"),
        listOf("6-0", "6-1", "6-2", "6-3", "6-4", "6-5", "6-6", "6-7"),
        listOf("7-0", "7-1", "7-2", "7-3", "7-4", "7-5", "7-6", "7-7"),
    )

    defineDiagonal((3 to 3), matrix8x8)
}

private fun Pair<Int, Int>.checkSize(): Boolean {
    return this.first in 0..7 && this.second in 0..7
}

private fun defineDiagonal(
    pair: Pair<Int, Int>,
    matrix: List<List<String>>
) {
    for (i in 1..8) {
        val rightDown = ((pair.first + i) to (pair.second + i))
        val leftDown = ((pair.first - i) to (pair.second - i))
        val rightUp = ((pair.first - i) to (pair.second + i))
        val leftUp = ((pair.first + i) to (pair.second - i))
        if (rightDown.checkSize()) {
            println(matrix[rightDown.first][rightDown.second])
        }
        if (leftDown.checkSize()) {
            println(matrix[leftDown.first][leftDown.second])
        }
        if (rightUp.checkSize()) {
            println(matrix[rightUp.first][rightUp.second])
        }
        if (leftUp.checkSize()) {
            println(matrix[leftUp.first][leftUp.second])
        }
    }
}

private fun checkSize(
    pair: Pair<Int, Int>,
    secondPair: Pair<Int, Int>
): Boolean {
    val sizeC = firstAdd(pair, secondPair)
    return sizeC.first in 0..7 && sizeC.second in 0..7
}

fun hasPath(
    coordinates: Pair<Int, Int>,
    pair: Pair<Int, Int>,
    matrix: List<List<String>>
): Boolean? {
    val air = firstAdd(coordinates, pair)
    return if (air.first in 0..7 && air.second in 0..7) {
        true
    } else {
        null
    }
}

private fun checkVertical(x: Int) {
    for (i in x - 1 downTo 0) {
        println(i)
    }
    for (i in x + 1..7) {
        println(i)
    }
}

private fun firstAdd(pair: Pair<Int, Int>, pairTwo: Pair<Int, Int>): Pair<Int, Int> {
    val x = pair.first + pairTwo.first
    val y = pair.second + pairTwo.second
    return x to y
}