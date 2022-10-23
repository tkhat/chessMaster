package com.khatiashvili.chessapp.system.piece

import com.khatiashvili.chessapp.system.board.Coordinates
import com.khatiashvili.chessapp.system.filterMovesForKing
import com.khatiashvili.chessapp.system.firstAdd

class Rook(
    override val iAmWhite: Boolean,
    override var coordinates: Coordinates,
) : Piece() {
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
        for (i in coordinates.x.firstAdd(1)..7) {
            val pc = getPieceFromYourPerspective((i.firstAdd(coordinates.x.unaryMinus())), 0)
            if (pc == null) {
                generalMoves.add(Coordinates(i, coordinates.y))
            } else if (pc.iAmWhite == this.iAmWhite) {
                break
            } else {
                generalMoves.add(Coordinates(i, coordinates.y))
                break
            }
        }
        for (i in coordinates.x.firstAdd(-1) downTo 0) {
            val pc = getPieceFromYourPerspective(coordinates.x.firstAdd(-i).unaryMinus(), 0)
            if (pc == null) {
                generalMoves.add(Coordinates(i, coordinates.y))
            } else if (pc.iAmWhite == this.iAmWhite) {
                break
            } else {
                generalMoves.add(Coordinates(i, coordinates.y))
                break
            }
        }
        for (i in coordinates.y.firstAdd(-1) downTo 0) {
            val pc = getPieceFromYourPerspective(0, coordinates.y.firstAdd(-i).unaryMinus())
            if (pc == null) {
                generalMoves.add(Coordinates(coordinates.x, i))
            } else if (pc.iAmWhite == this.iAmWhite) {
                break
            } else {
                generalMoves.add(Coordinates(coordinates.x, i))
                break
            }
        }
        for (i in coordinates.y.firstAdd(1)..7) {
            val pc = getPieceFromYourPerspective(0, i.firstAdd(coordinates.y.unaryMinus()))
            if (pc == null) {
                generalMoves.add(Coordinates(coordinates.x, i))
            } else if (pc.iAmWhite == this.iAmWhite) {
                break
            } else {
                generalMoves.add(Coordinates(coordinates.x, i))
                break
            }
        }
        update.invoke(
            generalMoves.filterMovesForKing(
                coordinates,
                iAmWhite
            )
        )
    }

}