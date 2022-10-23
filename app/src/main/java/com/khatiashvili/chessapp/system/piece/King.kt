package com.khatiashvili.chessapp.system.piece

import com.khatiashvili.chessapp.system.board.Coordinates
import com.khatiashvili.chessapp.system.checkSize
import com.khatiashvili.chessapp.system.filterMovesForKing
import com.khatiashvili.chessapp.system.firstAdd

class King(override val iAmWhite: Boolean, override var coordinates: Coordinates) : Piece() {
    override fun isValidMove(
        end: Coordinates,
        isValid: (Boolean) -> Unit
    ) {
        defineMoves { definedCoors ->

            val validatedCoors = definedCoors.filterMovesForKing(
                startCoordinates = this.coordinates,
                isKingWhite = this.iAmWhite
            )

            validatedCoors.forEach { coor ->
                if (coor == end) {
                    this.coordinates = coor
                    isValid.invoke(true)
                    return@defineMoves
                }
            }
        }
        isValid.invoke(false)
    }

    override fun defineMoves(

        update: (moves: MutableList<Coordinates>) -> Unit
    ) {
        var generalMoves = mutableListOf<Coordinates>()
        kingMovies.forEach {
            val tuple = (coordinates.x.firstAdd(it.first) to coordinates.y.firstAdd(it.second))
            if (tuple.checkSize()) {
                val piece = getPiece(tuple)
                if (piece?.iAmWhite != this.iAmWhite) {
                    generalMoves.add(
                        Coordinates(
                            tuple.first,
                            tuple.second
                        )
                    )
                }
            }
        }
        update.invoke(
            generalMoves.filterMovesForKing(
                coordinates,
                iAmWhite
            )
        )

    }

    companion object {
        val kingMovies = mutableListOf(
            (-1 to -1),
            (-1 to 0),
            (-1 to 1),
            (0 to -1),
            (0 to 1),
            (1 to -1),
            (1 to 0),
            (1 to 1),
        )
    }

}