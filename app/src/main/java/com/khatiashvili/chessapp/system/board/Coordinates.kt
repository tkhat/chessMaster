package com.khatiashvili.chessapp.system.board

data class Coordinates(val x: Int, val y: Int)

infix fun Coordinates.toAddCoordinates(pair: Pair<Int, Int>): Coordinates {
    return Coordinates((this.x + pair.first), (this.y + pair.second))
}

infix fun Coordinates.getSpot(board: List<List<Spot>>): Spot {
    return board[this.x][this.y]
}
fun Coordinates.isInsideBound():Boolean {
    return this.x in 0..7 && this.y in 0..7
}