package com.khatiashvili.chessapp.system.move

import com.khatiashvili.chessapp.system.board.*

class KnightPossibleMovesProvider : PossibleMovesProvider {

    override fun calculateMove(board: Board, spot: Spot) {
        val generalMoves = mutableListOf<Coordinates>()
        knightPossibleMoves.forEach { pair ->
            val coordinatesSum = spot.coordinates toAddCoordinates pair
            if (coordinatesSum.isInsideBound()) {
                val directionSpot = board.getSpot(coordinatesSum)
                if (directionSpot.isFree() || !directionSpot.isMyTeamMate(spot.piece!!.iAmWhite)) {
                    generalMoves.add(coordinatesSum)
                }
            }

        }
    }

    companion object {
        val knightPossibleMoves = mutableListOf(
            (-2 to -1),
            (-2 to 1),
            (-1 to -2),
            (-1 to 2),
            (2 to -1),
            (2 to 1),
            (1 to -2),
            (1 to 2)
        )
    }
}
