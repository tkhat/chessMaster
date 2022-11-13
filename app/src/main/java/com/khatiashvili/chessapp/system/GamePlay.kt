package com.khatiashvili.chessapp.system

import com.khatiashvili.chessapp.chess.board.Board

class GamePlay(var notify: () -> Unit) {

    private var touchPiece: Spot? = null
    private var isWhiteTurn: Boolean = true
    private var previousStates = mutableListOf<Coordinates>()
    private var moves = mutableListOf<SavedMoves>()

    fun touchHappen(spot: Spot) {
        when {
            touchPiece != null -> {
                validateMove(spot)
            }
            spot.piece?.iAmWhite == isWhiteTurn -> {
                updateStates(spot)
            }
            else -> {
                clearState()
            }
        }
    }

    private fun clearState() {
        previousStates.forEach {
            Board.board[it.x][it.y].state = ChessSpotState.DEFAULT
        }
        previousStates.clear()
        notify.invoke()
    }

    private fun updateStates(touchPiece: Spot) {
        this.touchPiece = touchPiece
        touchPiece.piece?.defineMoves(
        ) { piece ->

            clearState()
            if (piece.isNotEmpty()) {
                this.touchPiece?.let { touchSpot ->
                    previousStates.add(
                        Coordinates(
                            touchSpot.coordinates.x,
                            touchSpot.coordinates.y
                        )
                    )
                    Board.board[touchSpot.coordinates.x][touchSpot.coordinates.y].state =
                        ChessSpotState.LEGAL_TOUCH
                }
                piece.forEach {
                    Board.board[it.x][it.y].state = dangerOrDirection(it.x, it.y)
                    previousStates.add(it)
                }
            } else {
                this.touchPiece?.let {
                    previousStates.add(Coordinates(it.coordinates.x, it.coordinates.y))
                    Board.board[it.coordinates.x][it.coordinates.y].state =
                        ChessSpotState.ILLEGAL_TOUCH
                }
            }
        }
        notify.invoke()
    }

    private fun dangerOrDirection(x: Int, y: Int): ChessSpotState {
        return when (Board.board[x][y].piece) {
            is Piece -> {
                ChessSpotState.DANGER
            }
            else -> {
                ChessSpotState.PATH
            }
        }
    }

    private fun validateMove(spot: Spot) {
        touchPiece?.piece?.isValidMove(
            spot.coordinates
        ) {
            if (it) {
                makeMove(touchPiece!!, spot)
            } else if (spot.piece?.iAmWhite == isWhiteTurn) {
                updateStates(spot)
            } else {
                clearState()
            }
        }
    }

    private fun makeMove(touchPiece: Spot, spot: Spot) {
        moves.add(SavedMoves(touchPiece.piece!!, spot.coordinates))
        Board.board[spot.coordinates.x][spot.coordinates.y].piece = touchPiece.piece
        Board.board[spot.coordinates.x][spot.coordinates.y].piece?.coordinates =
            Coordinates(spot.coordinates.x, spot.coordinates.y)
        Board.board[touchPiece.coordinates.x][touchPiece.coordinates.y].piece = null
        clearAndAddPreviousMovesStates()
        this.touchPiece = null
        clearState()
        isWhiteTurn = !isWhiteTurn
    }

    private fun clearAndAddPreviousMovesStates() {
        moves.forEach {
            Board.board[it.start.coordinates.x][it.start.coordinates.y].state =
                ChessSpotState.DEFAULT
            Board.board[it.end.x][it.end.y].state = ChessSpotState.DEFAULT
        }
        if (moves.isNotEmpty()) {
            Board.board[moves.last().start.coordinates.x][moves.last().start.coordinates.y].state =
                ChessSpotState.LAST
            Board.board[moves.last().end.x][moves.last().end.y].state = ChessSpotState.LAST
        }
        notify.invoke()
    }

}

data class SavedMoves(
    val start: Piece,
    val end: Coordinates
)