package com.khatiashvili.chessapp.system.piece

import com.khatiashvili.chessapp.system.board.Board.board

abstract class Piece {
     abstract val iAmWhite: Boolean
     abstract val pieceType: PieceType

    abstract fun isValidMove(
        end: Coordinates,
        isValid: (Boolean) -> Unit
    )

    abstract fun defineMoves(
        update: (moves: MutableList<Coordinates>) -> Unit
    )

    protected fun getPieceFromYourPerspective(x: Int, y: Int): Piece? {
        return if (coordinates.x.firstAdd(x) in 0..7 && coordinates.y.firstAdd(y) in 0..7) {
            board[coordinates.x.firstAdd(x)][coordinates.y.firstAdd(y)].piece
        } else {
            null
        }
    }

    protected fun getPiece(pair: Pair<Int, Int>): Piece? {
        return board[pair.first][pair.second].piece
    }
}