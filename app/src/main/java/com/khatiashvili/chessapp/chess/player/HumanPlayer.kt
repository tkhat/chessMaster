package com.khatiashvili.chessapp.chess.player

import com.khatiashvili.chessapp.chess.board.Board
import com.khatiashvili.chessapp.chess.board.Board.board
import com.khatiashvili.chessapp.chess.move.Move
import com.khatiashvili.chessapp.chess.piece.PColor

class HumanPlayer(
    val color: PColor,
    val moveExecutor: (move: Move) -> Unit,
) {
    private lateinit var myMoves: List<Move>

    fun getMoves(): List<Move> {
        return myMoves
    }

    fun makeMove(move: Move) {
        myMoves = calculateMoves()
        myMoves.forEach { legalMove ->
            if (legalMove == move) {
                move.executeMove()
                moveExecutor.invoke(move)
            }
        }
    }


    private fun calculateMoves(): List<Move> {
        TODO()
    }

}