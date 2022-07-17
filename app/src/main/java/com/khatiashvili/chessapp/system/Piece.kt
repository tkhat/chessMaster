package com.khatiashvili.chessapp.system

abstract class Piece {
    abstract val iAmWhite: Boolean
    abstract var coordinates: Coordinates
    private var kingCoordinates: Coordinates = defineKingCoordinates()


    abstract fun isValidMove(end: Coordinates, isValid: (Boolean) -> Unit)
    abstract fun defineMoves(update: (moves: List<Coordinates>) -> Unit)

    private fun defineKingCoordinates(): Coordinates {
        return if (iAmWhite)
            Coordinates(1, 1)
        else Coordinates(0, 0)
    }
    protected fun setKingCoordinates(coordinates: Coordinates) {
        this.coordinates = coordinates
    }

   protected fun hasKingCheckIf(
        spotCoordinates: Coordinates
    ): List<Coordinates> {
        return emptyList()
    }
}

class Pawn(
    override val iAmWhite: Boolean,
    override var coordinates: Coordinates): Piece() {
    private var checkEnPassant: Moves? = null

    override fun isValidMove(end: Coordinates, isValid: (Boolean)-> Unit) {
        super.hasKingCheckIf(coordinates).forEach {
            if (end == it) {
                isValid.invoke(false)
                return
            }
        }
        defineMoves { definedCoors ->
            definedCoors.forEach { coor ->
                if (coor == end) {
                    isValid.invoke(true)
                    return@defineMoves
                }
            }
        }
        isValid.invoke(false)
    }

    override fun defineMoves(update: (moves: List<Coordinates>)-> Unit) {
        val moves = mutableListOf<Coordinates>()
        val board = Board.board
        if (iAmWhite) {
            if (coordinates.y != 7) {
                if (board[coordinates.x+1][coordinates.y+1].piece?.iAmWhite == false) {
                    moves.add(Coordinates(coordinates.x+1,coordinates.y+1))
                }
            }
            if (coordinates.y != 0) {
                if (board[coordinates.x+1][coordinates.y-1].piece?.iAmWhite == false) {
                    moves.add(Coordinates(coordinates.x+1,coordinates.y-1))
                }
            }
            if (board[coordinates.x+1][coordinates.y].piece == null) {
                moves.add(Coordinates(coordinates.x+1,coordinates.y))
                if (coordinates.x == 1) {
                    if (board[coordinates.x+2][coordinates.y].piece ==  null){
                        moves.add(Coordinates(coordinates.x+2,coordinates.y))
                    }
                }
            }

        } else {
            if (coordinates.y != 7) {
                if (board[coordinates.x-1][coordinates.y+1].piece?.iAmWhite == true) {
                    moves.add(Coordinates(coordinates.x-1,coordinates.y+1))
                }
            }
            if (coordinates.y != 0) {
                if (board[coordinates.x-1][coordinates.y-1].piece?.iAmWhite == true) {
                    moves.add(Coordinates(coordinates.x-1,coordinates.y-1))
                }
            }
            if (board[coordinates.x-1][coordinates.y].piece == null) {
                moves.add(Coordinates(coordinates.x-1,coordinates.y))
                if (coordinates.x == 6) {
                    if (board[coordinates.x-2][coordinates.y].piece ==  null){
                        moves.add(Coordinates(coordinates.x-2,coordinates.y))
                    }
                }
            }
        }
        update.invoke(moves)
    }

}
class King(override val iAmWhite: Boolean, override var coordinates: Coordinates): Piece() {
    override fun isValidMove(end: Coordinates, failedValidation: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun defineMoves(update: (moves: List<Coordinates>) -> Unit) {
        TODO("Not yet implemented")
    }

}
class Rook(override val iAmWhite: Boolean, override var coordinates: Coordinates): Piece(){
    override fun isValidMove(end: Coordinates, failedValidation: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun defineMoves(update: (moves: List<Coordinates>) -> Unit) {
        TODO("Not yet implemented")
    }

}
class Knight(override val iAmWhite: Boolean, override var coordinates: Coordinates): Piece(){
    override fun isValidMove(end: Coordinates, failedValidation: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun defineMoves(update: (moves: List<Coordinates>) -> Unit) {
        TODO("Not yet implemented")
    }

}
class Bishop(override val iAmWhite: Boolean, override var coordinates: Coordinates): Piece(){
    override fun isValidMove(end: Coordinates, failedValidation: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun defineMoves(update: (moves: List<Coordinates>) -> Unit) {
        TODO("Not yet implemented")
    }

}
class Queen(override val iAmWhite: Boolean, override var coordinates: Coordinates): Piece(){
    override fun isValidMove(end: Coordinates, failedValidation: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun defineMoves(update: (moves: List<Coordinates>) -> Unit) {
        TODO("Not yet implemented")
    }

}
class Spot(
    val coordinates: Coordinates,
    var piece: Piece? = null,
    var state: ChessBoardState
)

enum class ChessBoardState{
    DEFAULT,
    LEGAL_TOUCH,
    ILLEGAL_TOUCH,
    PATH,
    DANGER,
    LAST
}


data class Coordinates(val x: Int, val y: Int)

class Game(

)