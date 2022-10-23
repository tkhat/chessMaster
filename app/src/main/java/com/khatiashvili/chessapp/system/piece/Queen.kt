package com.khatiashvili.chessapp.system.piece

import com.khatiashvili.chessapp.system.board.Board.board
import com.khatiashvili.chessapp.system.board.Coordinates
import com.khatiashvili.chessapp.system.checkSize
import com.khatiashvili.chessapp.system.filterMovesForKing
import com.khatiashvili.chessapp.system.firstAdd

class Queen(override val iAmWhite: Boolean, override var coordinates: Coordinates) : Piece() {
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
        var boolRightUp = true
        var boolLeftUp = true
        var boolRightDown = true
        var boolLeftDown = true
        for (i in 1..8) {
            val rightDown = ((coordinates.x + i) to (coordinates.y + i))
            val leftDown = ((coordinates.x - i) to (coordinates.y - i))
            val rightUp = ((coordinates.x - i) to (coordinates.y + i))
            val leftUp = ((coordinates.x + i) to (coordinates.y - i))

            if (rightDown.checkSize() && boolRightDown) {
                val piece = board[rightDown.first][rightDown.second]
                when {
                    piece.piece?.iAmWhite == this.iAmWhite -> {
                        boolRightDown = false
                    }
                    piece.piece is Piece -> {
                        generalMoves.add(Coordinates(rightDown.first, rightDown.second))
                        boolRightDown = false
                    }
                    else -> {
                        generalMoves.add(Coordinates(rightDown.first, rightDown.second))
                    }
                }
            }
            if (leftDown.checkSize() && boolLeftDown) {
                val piece = board[leftDown.first][leftDown.second]
                when {
                    piece.piece?.iAmWhite == this.iAmWhite -> {
                        boolLeftDown = false
                    }
                    piece.piece is Piece -> {
                        generalMoves.add(Coordinates(leftDown.first, leftDown.second))
                        boolLeftDown = false
                    }
                    else -> {
                        generalMoves.add(Coordinates(leftDown.first, leftDown.second))
                    }
                }
            }
            if (rightUp.checkSize() && boolRightUp) {
                val piece = board[rightUp.first][rightUp.second]
                when {
                    piece.piece?.iAmWhite == this.iAmWhite -> {
                        boolRightUp = false
                    }
                    piece.piece is Piece -> {
                        generalMoves.add(
                            Coordinates(
                                rightUp.first,
                                rightUp.second
                            )
                        )
                        boolRightUp = false
                    }
                    else -> {
                        generalMoves.add(
                            Coordinates(
                                rightUp.first,
                                rightUp.second
                            )
                        )
                    }
                }
            }
            if (leftUp.checkSize() && boolLeftUp) {
                val piece = board[leftUp.first][leftUp.second]
                when {
                    piece.piece?.iAmWhite == this.iAmWhite -> {
                        boolLeftUp = false
                    }
                    piece.piece is Piece -> {
                        generalMoves.add(
                            Coordinates(
                                leftUp.first,
                                leftUp.second
                            )
                        )
                        boolLeftUp = false
                    }
                    else -> {
                        generalMoves.add(
                            Coordinates(
                                leftUp.first,
                                leftUp.second
                            )
                        )
                    }
                }
            }
        }
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