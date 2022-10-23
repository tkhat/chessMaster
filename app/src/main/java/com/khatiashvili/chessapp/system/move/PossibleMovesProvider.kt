package com.khatiashvili.chessapp.system.move

import com.khatiashvili.chessapp.system.board.Board
import com.khatiashvili.chessapp.system.board.Spot

interface PossibleMovesProvider {

        fun calculateMove(board: Board, spot: Spot)
}
