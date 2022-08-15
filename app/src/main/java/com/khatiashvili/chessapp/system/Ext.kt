package com.khatiashvili.chessapp.system

fun Int.firstAdd(add: Int): Int {
    return this + add
}

fun Pair<Int, Int>.checkSize(): Boolean {
    return this.first in 0..7 && this.second in 0..7
}

fun MutableList<Coordinates>.filterMovesForKing(
    kingCoordinates: Coordinates,
    startCoordinates: Coordinates,
    isKingWhite: Boolean
): MutableList<Coordinates> {
    var testingBoard = Board.getBoardForTesting()
    this.listIterator().run {

        if (hasNext()){
            val it = this.next()
            testingBoard[it.x][it.y].piece =
                testingBoard[startCoordinates.x][startCoordinates.y].piece
            testingBoard[startCoordinates.x][startCoordinates.y].piece = null

            if (HasKingCheckerUtil(
                    kingCoordinates,
                    testingBoard,
                    isKingWhite
                ).hasKingCheck()
            ) {
                this.remove()
            }
        }
        testingBoard = Board.getBoardForTesting()
    }
    return this
}
