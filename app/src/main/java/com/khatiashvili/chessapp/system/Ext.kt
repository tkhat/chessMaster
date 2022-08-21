package com.khatiashvili.chessapp.system

fun Int.firstAdd(add: Int): Int {
    return this + add
}

fun Pair<Int, Int>.checkSize(): Boolean {
    return this.first in 0..7 && this.second in 0..7
}

fun MutableList<Coordinates>.filterMovesForKing(
    startCoordinates: Coordinates,
    isKingWhite: Boolean
): MutableList<Coordinates> {
    var movesForDelete = mutableListOf<Coordinates>()
    var testingBoard = Board.getBoardForTesting()

    this.forEach {

        val obj = testingBoard[startCoordinates.x][startCoordinates.y].piece
        testingBoard[startCoordinates.x][startCoordinates.y].piece = null
        testingBoard[it.x][it.y].piece = obj

        if (HasKingCheckerUtil(testingBoard, isKingWhite).hasKingCheck()
        ) {
            movesForDelete.add(it)
        }
        testingBoard = Board.getBoardForTesting()
    }
    movesForDelete.forEach {
        remove(it)
    }
    return this
}
