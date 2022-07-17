package com.khatiashvili.chessapp.system

class GamePlay(var notify: () -> Unit) {

    private var firstClick: Spot? = null
    private var isWhiteTurn: Boolean = true
    private var previousStates = mutableListOf<Coordinates>()
    private var moves = mutableListOf<Moves>()

     fun clickHappen(spot: Spot) {
        if (spot.piece?.iAmWhite == isWhiteTurn || spot.piece == null) {
            if (firstClick == null) {
                firstClick = spot
                updateStates()
            } else {
                validateMoves(spot)
            }

        } else  {
            return
        }
    }

    private fun validateMoves(spot: Spot) {
        when(firstClick?.piece) {
            is King -> {

            }
            else -> {
                firstClick?.piece?.isValidMove(spot.coordinates) { moveIsValid ->
                    if (moveIsValid) {
                        firstClick!!.state = ChessBoardState.LAST
                        spot.piece = firstClick?.piece
                        firstClick?.piece = null
                        firstClick = null
                        isWhiteTurn = !isWhiteTurn
                        notify.invoke()
                    } else {
                        if (spot.piece?.iAmWhite == true && isWhiteTurn) {
                            updateStates(spot)
                        }
                    }
                }
            }
        }
    }

    private fun updateStates(spot: Spot? = null) {
        if (spot != null) {
            spot.piece?.defineMoves {
                clearOldStates()
                changeStates(it)
            }
        } else {
            firstClick?.piece?.defineMoves {
                changeStates(it)
            }
        }
        notify.invoke()
    }
    private fun changeStates(list: List<Coordinates>) {
        if (moves.isNotEmpty()) {
            val moves = moves.last()
            moves.start.state = ChessBoardState.LAST
            moves.end.state = ChessBoardState.LAST
        }
        previousStates.clear()
        list.forEach { newCoordinates ->
            val indSpot = Board.board[newCoordinates.x][newCoordinates.y]
            indSpot.state = if (indSpot.piece == null) ChessBoardState.PATH else ChessBoardState.DANGER
            previousStates.add(newCoordinates)
        }
        notify.invoke()

    }
    private fun clearOldStates() {
        previousStates.forEach { prevCoordiantes ->
            Board.board[prevCoordiantes.x][prevCoordiantes.y].state = ChessBoardState.DEFAULT
        }
        previousStates.clear()
        notify.invoke()
    }
}

data class Moves(
    val start: Spot,
    val end: Spot
)