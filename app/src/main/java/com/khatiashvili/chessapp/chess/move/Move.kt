package com.khatiashvili.chessapp.chess.move

import com.khatiashvili.chessapp.chess.board.Board.board
import com.khatiashvili.chessapp.chess.board.Spot
import com.khatiashvili.chessapp.chess.piece.Piece
import com.khatiashvili.chessapp.chess.piece.PieceType


sealed class Move {
    abstract fun executeMove()
    data class CommonMove(
        val from: Spot,
        val to: Spot
    ) : Move() {
        override fun executeMove() {
            board[to.x][to.y].piece = board[from.x][from.y].piece
            board[from.x][from.y].piece = null
        }
    }

    data class PromotionMove(
        val pieceType: PieceType,
        val from: Spot,
        val to: Spot
    ) : Move() {
        override fun executeMove() {
            board[to.x][to.y].piece = Piece(from.piece!!.color, pieceType)
            board[from.x][from.y].piece = null
        }
    }

    data class CastleMove(
        val from: Spot,
        val to: Spot
    ) : Move() {
        override fun executeMove() {
            TODO("Not yet implemented")
        }
    }

    data class EnPassant(
        val from: Spot,
        val toSpot: Spot
    ) : Move() {
        override fun executeMove() {
            TODO("Not yet implemented")
        }
    }
}



