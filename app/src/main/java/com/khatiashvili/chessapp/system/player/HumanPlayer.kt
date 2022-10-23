package com.khatiashvili.chessapp.system.player

import com.khatiashvili.chessapp.system.board.Board
import com.khatiashvili.chessapp.system.board.Spot

class HumanPlayer(
    board: Board,
    myPieces: List<Spot>,
    moveExecutor: MoveExecutor
) : Player(board, myPieces, moveExecutor) {
    private var touchHappen: Spot? = null

    fun clickHappen(spot: Spot) {
        if (touchHappen != null) {

        }
    }

}