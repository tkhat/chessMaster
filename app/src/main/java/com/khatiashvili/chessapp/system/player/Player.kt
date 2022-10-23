package com.khatiashvili.chessapp.system.player

import com.khatiashvili.chessapp.system.board.Board
import com.khatiashvili.chessapp.system.board.Spot

abstract class Player(
    val board: Board,
    val myPieces: List<Spot>,
    val moveExecutor: MoveExecutor
) {

    fun interface MoveExecutor {
        fun execute(from: Spot, to: Spot)
    }
}