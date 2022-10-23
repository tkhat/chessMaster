package com.khatiashvili.chessapp.system.board

import com.khatiashvili.chessapp.system.piece.Piece

data class Spot(
    val coordinates: Coordinates,
    var piece: Piece? = null,
    var state: ChessBoardState
) {
    fun isFree() = piece != null
    fun isMyTeamMate(imAmWhite: Boolean) = piece?.iAmWhite == imAmWhite
}