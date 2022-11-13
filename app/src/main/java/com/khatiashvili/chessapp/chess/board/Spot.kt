package com.khatiashvili.chessapp.chess.board

import com.khatiashvili.chessapp.chess.piece.Piece

data class Spot(
    val x: Int,
    val y: Int,
    var piece: Piece? = null,
    var state: ChessSpotState
)