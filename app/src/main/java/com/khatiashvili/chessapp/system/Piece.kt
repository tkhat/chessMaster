package com.khatiashvili.chessapp.system

import com.khatiashvili.chessapp.system.Board.board
import com.khatiashvili.chessapp.system.Knight.Companion.knightPossibleMoves

abstract class Piece {
    abstract val iAmWhite: Boolean
    abstract var coordinates: Coordinates

    abstract fun isValidMove(
        end: Coordinates,
        isValid: (Boolean) -> Unit
    )

    abstract fun defineMoves(
        update: (moves: MutableList<Coordinates>) -> Unit
    )

    protected fun getPieceFromYourPerspective(x: Int, y: Int): Piece? {
        return if (coordinates.x.firstAdd(x) in 0..7 && coordinates.y.firstAdd(y) in 0..7) {
            board[coordinates.x.firstAdd(x)][coordinates.y.firstAdd(y)].piece
        } else {
            null
        }
    }

    protected fun getPiece(pair: Pair<Int, Int>): Piece? {
        return board[pair.first][pair.second].piece
    }


}

class Pawn(
    override val iAmWhite: Boolean,
    override var coordinates: Coordinates
) : Piece() {
    private var checkEnPassant: SavedMoves? = null

    override fun isValidMove(
        end: Coordinates,
        isValid: (Boolean) -> Unit
    ) {

        defineMoves { definedCoors ->
            definedCoors.forEach { coor ->
                if (coor == end) {
                    this.coordinates = coor
                    isValid.invoke(true)
                    return@defineMoves
                }
            }
        }
        isValid.invoke(false)
    }

    override fun defineMoves(
        update: (moves: MutableList<Coordinates>) -> Unit
    ) {
        var generalMovies = mutableListOf<Coordinates>()
        if (this.iAmWhite) {
            if (coordinates.x == 1) {
                if (getPieceFromYourPerspective(1, 0) == null) {
                    generalMovies.add(
                        Coordinates(
                            coordinates.x.firstAdd(1),
                            coordinates.y
                        )
                    )
                    if (getPieceFromYourPerspective(2, 0) == null) {

                        generalMovies.add(
                            Coordinates(
                                coordinates.x.firstAdd(2),
                                coordinates.y
                            )
                        )
                    }
                }
            } else {
                if (getPieceFromYourPerspective(1, 0) == null) {
                    generalMovies.add(
                        Coordinates(
                            coordinates.x.firstAdd(1),
                            coordinates.y
                        )
                    )
                }
            }
            if (getPieceFromYourPerspective(1, -1)?.iAmWhite != this.iAmWhite
                && getPieceFromYourPerspective(1, -1) is Piece
            ) {
                generalMovies.add(
                    Coordinates(
                        coordinates.x.firstAdd(1),
                        coordinates.y.firstAdd(-1)
                    )
                )
            }
            if (getPieceFromYourPerspective(1, 1)?.iAmWhite != this.iAmWhite
                && getPieceFromYourPerspective(1, 1) is Piece
            ) {
                generalMovies.add(
                    Coordinates(
                        coordinates.x.firstAdd(1),
                        coordinates.y.firstAdd(1)
                    )
                )
            }
        } else {
            if (coordinates.x == 6) {
                if (getPieceFromYourPerspective(-1, 0) == null) {
                    generalMovies.add(
                        Coordinates(
                            coordinates.x.firstAdd(-1),
                            coordinates.y
                        )
                    )
                    if (getPieceFromYourPerspective(-2, 0) == null) {
                        generalMovies.add(
                            Coordinates(
                                coordinates.x.firstAdd(-2),
                                coordinates.y
                            )
                        )
                    }
                }
            } else {
                if (getPieceFromYourPerspective(-1, 0) == null) {
                    generalMovies.add(
                        Coordinates(
                            coordinates.x.firstAdd(-1),
                            coordinates.y
                        )
                    )
                }
            }
            if (getPieceFromYourPerspective(-1, -1)?.iAmWhite != this.iAmWhite
                && getPieceFromYourPerspective(-1, -1) is Piece
            ) {
                generalMovies.add(
                    Coordinates(
                        coordinates.x.firstAdd(-1),
                        coordinates.y.firstAdd(-1)
                    )
                )
            }
            if (getPieceFromYourPerspective(-1, 1)?.iAmWhite != this.iAmWhite
                && getPieceFromYourPerspective(-1, 1) is Piece
            ) {
                generalMovies.add(
                    Coordinates(
                        coordinates.x.firstAdd(-1),
                        coordinates.y.firstAdd(1)
                    )
                )
            }
        }
        update.invoke(
            generalMovies.filterMovesForKing(
                coordinates,
                iAmWhite
            )
        )
    }
    /***
     * Needs to Defines All moves that does not involves check for his king
     * So if kings already had a check this function must defines only that moves
     * which defence King from the Check
     * ***/

}


class King(override val iAmWhite: Boolean, override var coordinates: Coordinates) : Piece() {
    override fun isValidMove(
        end: Coordinates,
        isValid: (Boolean) -> Unit
    ) {
        defineMoves { definedCoors ->

            val validatedCoors = definedCoors.filterMovesForKing(
                startCoordinates = this.coordinates,
                isKingWhite = this.iAmWhite
            )

            validatedCoors.forEach { coor ->
                if (coor == end) {
                    this.coordinates = coor
                    isValid.invoke(true)
                    return@defineMoves
                }
            }
        }
        isValid.invoke(false)
    }

    override fun defineMoves(

        update: (moves: MutableList<Coordinates>) -> Unit
    ) {
        var generalMoves = mutableListOf<Coordinates>()
        kingMovies.forEach {
            val tuple = (coordinates.x.firstAdd(it.first) to coordinates.y.firstAdd(it.second))
            if (tuple.checkSize()) {
                val piece = getPiece(tuple)
                if (piece?.iAmWhite != this.iAmWhite) {
                    generalMoves.add(
                        Coordinates(
                            tuple.first,
                            tuple.second
                        )
                    )
                }
            }
        }
        update.invoke(
            generalMoves.filterMovesForKing(
                coordinates,
                iAmWhite
            )
        )

    }

    companion object {
        val kingMovies = mutableListOf(
            (-1 to -1),
            (-1 to 0),
            (-1 to 1),
            (0 to -1),
            (0 to 1),
            (1 to -1),
            (1 to 0),
            (1 to 1),
        )
    }

}

class Rook(
    override val iAmWhite: Boolean,
    override var coordinates: Coordinates,
) : Piece() {
    override fun isValidMove(
        end: Coordinates,
        isValid: (Boolean) -> Unit
    ) {
        defineMoves { definedCoors ->

            val validatedCoors = definedCoors.filterMovesForKing(
                startCoordinates = this.coordinates,
                isKingWhite = this.iAmWhite
            )

            validatedCoors.forEach { coor ->
                if (coor == end) {
                    this.coordinates = coor
                    isValid.invoke(true)
                    return@defineMoves
                }
            }
        }
        isValid.invoke(false)
    }

    override fun defineMoves(
        update: (moves: MutableList<Coordinates>) -> Unit
    ) {
        var generalMoves = mutableListOf<Coordinates>()
        for (i in coordinates.x.firstAdd(1)..7) {
            val pc = getPieceFromYourPerspective((i.firstAdd(coordinates.x.unaryMinus())), 0)
            if (pc == null) {
                generalMoves.add(Coordinates(i, coordinates.y))
            } else if (pc.iAmWhite == this.iAmWhite) {
                break
            } else {
                generalMoves.add(Coordinates(i, coordinates.y))
                break
            }
        }
        for (i in coordinates.x.firstAdd(-1) downTo 0) {
            val pc = getPieceFromYourPerspective(coordinates.x.firstAdd(-i).unaryMinus(), 0)
            if (pc == null) {
                generalMoves.add(Coordinates(i, coordinates.y))
            } else if (pc.iAmWhite == this.iAmWhite) {
                break
            } else {
                generalMoves.add(Coordinates(i, coordinates.y))
                break
            }
        }
        for (i in coordinates.y.firstAdd(-1) downTo 0) {
            val pc = getPieceFromYourPerspective(0, coordinates.y.firstAdd(-i).unaryMinus())
            if (pc == null) {
                generalMoves.add(Coordinates(coordinates.x, i))
            } else if (pc.iAmWhite == this.iAmWhite) {
                break
            } else {
                generalMoves.add(Coordinates(coordinates.x, i))
                break
            }
        }
        for (i in coordinates.y.firstAdd(1)..7) {
            val pc = getPieceFromYourPerspective(0, i.firstAdd(coordinates.y.unaryMinus()))
            if (pc == null) {
                generalMoves.add(Coordinates(coordinates.x, i))
            } else if (pc.iAmWhite == this.iAmWhite) {
                break
            } else {
                generalMoves.add(Coordinates(coordinates.x, i))
                break
            }
        }
        update.invoke(
            generalMoves.filterMovesForKing(
                coordinates,
                iAmWhite
            )
        )
    }

}

class Knight(override val iAmWhite: Boolean, override var coordinates: Coordinates) : Piece() {
    override fun isValidMove(
        end: Coordinates,
        isValid: (Boolean) -> Unit
    ) {

        defineMoves { definedCoors ->
            val validatedCoors = definedCoors.filterMovesForKing(
                startCoordinates = this.coordinates,
                isKingWhite = this.iAmWhite
            )

            validatedCoors.forEach { coor ->
                if (coor == end) {
                    this.coordinates = coor
                    isValid.invoke(true)
                    return@defineMoves
                }
            }
        }
        isValid.invoke(false)
    }

    override fun defineMoves(
        update: (moves: MutableList<Coordinates>) -> Unit
    ) {
        val generalMoves = mutableListOf<Coordinates>()
        knightPossibleMoves.forEach {
            if (hasPath(it) == true) {
                generalMoves.add(
                    Coordinates(
                        coordinates.x.firstAdd(it.first),
                        coordinates.y.firstAdd(it.second)
                    )
                )
            }
        }
        update.invoke(
            generalMoves.filterMovesForKing(
                coordinates,
                iAmWhite
            )
        )
    }

    private fun hasPath(pair: Pair<Int, Int>): Boolean? {
        val air = (coordinates.x.firstAdd(pair.first) to coordinates.y.firstAdd(pair.second))
        return if (air.first in 0..7 && air.second in 0..7) {
            board[air.first][air.second].piece?.iAmWhite != iAmWhite
        } else {
            null
        }
    }

    companion object {
        val knightPossibleMoves = mutableListOf(
            (-2 to -1),
            (-2 to 1),
            (-1 to -2),
            (-1 to 2),
            (2 to -1),
            (2 to 1),
            (1 to -2),
            (1 to 2)
        )
    }

}

class Bishop(override val iAmWhite: Boolean, override var coordinates: Coordinates) : Piece() {

    override fun isValidMove(
        end: Coordinates,
        isValid: (Boolean) -> Unit
    ) {

        defineMoves { definedCoors ->
            val validatedCoors = definedCoors.filterMovesForKing(
                startCoordinates = this.coordinates,
                isKingWhite = this.iAmWhite
            )

            validatedCoors.forEach { coor ->
                if (coor == end) {
                    this.coordinates = coor
                    isValid.invoke(true)
                    return@defineMoves
                }
            }
        }
        isValid.invoke(false)
    }

    override fun defineMoves(
        update: (moves: MutableList<Coordinates>) -> Unit
    ) {
        var generalMoves = mutableListOf<Coordinates>()
        var boolRightUp = true
        var boolLeftUp = true
        var boolRightDown = true
        var boolLeftDown = true
        for (i in 1..8) {
            val rightDown = ((coordinates.x + i) to (coordinates.y + i))
            val leftDown = ((coordinates.x - i) to (coordinates.y - i))
            val rightUp = ((coordinates.x - i) to (coordinates.y + i))
            val leftUp = ((coordinates.x + i) to (coordinates.y - i))

            if (rightDown.checkSize() && boolRightDown) {
                val piece = board[rightDown.first][rightDown.second]
                when {
                    piece.piece?.iAmWhite == this.iAmWhite -> {
                        boolRightDown = false
                    }
                    piece.piece is Piece -> {
                        generalMoves.add(Coordinates(rightDown.first, rightDown.second))
                        boolRightDown = false
                    }
                    else -> {
                        generalMoves.add(Coordinates(rightDown.first, rightDown.second))
                    }
                }
            }
            if (leftDown.checkSize() && boolLeftDown) {
                val piece = board[leftDown.first][leftDown.second]
                when {
                    piece.piece?.iAmWhite == this.iAmWhite -> {
                        boolLeftDown = false
                    }
                    piece.piece is Piece -> {
                        generalMoves.add(Coordinates(leftDown.first, leftDown.second))
                        boolLeftDown = false
                    }
                    else -> {
                        generalMoves.add(Coordinates(leftDown.first, leftDown.second))
                    }
                }
            }
            if (rightUp.checkSize() && boolRightUp) {
                val piece = board[rightUp.first][rightUp.second]
                when {
                    piece.piece?.iAmWhite == this.iAmWhite -> {
                        boolRightUp = false
                    }
                    piece.piece is Piece -> {
                        generalMoves.add(
                            Coordinates(
                                rightUp.first,
                                rightUp.second
                            )
                        )
                        boolRightUp = false
                    }
                    else -> {
                        generalMoves.add(
                            Coordinates(
                                rightUp.first,
                                rightUp.second
                            )
                        )
                    }
                }
            }
            if (leftUp.checkSize() && boolLeftUp) {
                val piece = board[leftUp.first][leftUp.second]
                when {
                    piece.piece?.iAmWhite == this.iAmWhite -> {
                        boolLeftUp = false
                    }
                    piece.piece is Piece -> {
                        generalMoves.add(
                            Coordinates(
                                leftUp.first,
                                leftUp.second
                            )
                        )
                        boolLeftUp = false
                    }
                    else -> {
                        generalMoves.add(
                            Coordinates(
                                leftUp.first,
                                leftUp.second
                            )
                        )
                    }
                }
            }
        }
        update.invoke(
            generalMoves.filterMovesForKing(
                coordinates,
                iAmWhite
            )
        )
    }

}

class Queen(override val iAmWhite: Boolean, override var coordinates: Coordinates) : Piece() {
    override fun isValidMove(
        end: Coordinates,
        isValid: (Boolean) -> Unit
    ) {
        defineMoves { definedCoors ->
            val validatedCoors = definedCoors.filterMovesForKing(
                startCoordinates = this.coordinates,
                isKingWhite = this.iAmWhite
            )

            validatedCoors.forEach { coor ->
                if (coor == end) {
                    this.coordinates = coor
                    isValid.invoke(true)
                    return@defineMoves
                }
            }
        }
        isValid.invoke(false)
    }

    override fun defineMoves(
        update: (moves: MutableList<Coordinates>) -> Unit
    ) {
        var generalMoves = mutableListOf<Coordinates>()
        var boolRightUp = true
        var boolLeftUp = true
        var boolRightDown = true
        var boolLeftDown = true
        for (i in 1..8) {
            val rightDown = ((coordinates.x + i) to (coordinates.y + i))
            val leftDown = ((coordinates.x - i) to (coordinates.y - i))
            val rightUp = ((coordinates.x - i) to (coordinates.y + i))
            val leftUp = ((coordinates.x + i) to (coordinates.y - i))

            if (rightDown.checkSize() && boolRightDown) {
                val piece = board[rightDown.first][rightDown.second]
                when {
                    piece.piece?.iAmWhite == this.iAmWhite -> {
                        boolRightDown = false
                    }
                    piece.piece is Piece -> {
                        generalMoves.add(Coordinates(rightDown.first, rightDown.second))
                        boolRightDown = false
                    }
                    else -> {
                        generalMoves.add(Coordinates(rightDown.first, rightDown.second))
                    }
                }
            }
            if (leftDown.checkSize() && boolLeftDown) {
                val piece = board[leftDown.first][leftDown.second]
                when {
                    piece.piece?.iAmWhite == this.iAmWhite -> {
                        boolLeftDown = false
                    }
                    piece.piece is Piece -> {
                        generalMoves.add(Coordinates(leftDown.first, leftDown.second))
                        boolLeftDown = false
                    }
                    else -> {
                        generalMoves.add(Coordinates(leftDown.first, leftDown.second))
                    }
                }
            }
            if (rightUp.checkSize() && boolRightUp) {
                val piece = board[rightUp.first][rightUp.second]
                when {
                    piece.piece?.iAmWhite == this.iAmWhite -> {
                        boolRightUp = false
                    }
                    piece.piece is Piece -> {
                        generalMoves.add(
                            Coordinates(
                                rightUp.first,
                                rightUp.second
                            )
                        )
                        boolRightUp = false
                    }
                    else -> {
                        generalMoves.add(
                            Coordinates(
                                rightUp.first,
                                rightUp.second
                            )
                        )
                    }
                }
            }
            if (leftUp.checkSize() && boolLeftUp) {
                val piece = board[leftUp.first][leftUp.second]
                when {
                    piece.piece?.iAmWhite == this.iAmWhite -> {
                        boolLeftUp = false
                    }
                    piece.piece is Piece -> {
                        generalMoves.add(
                            Coordinates(
                                leftUp.first,
                                leftUp.second
                            )
                        )
                        boolLeftUp = false
                    }
                    else -> {
                        generalMoves.add(
                            Coordinates(
                                leftUp.first,
                                leftUp.second
                            )
                        )
                    }
                }
            }
        }
        for (i in coordinates.x.firstAdd(1)..7) {
            val pc = getPieceFromYourPerspective((i.firstAdd(coordinates.x.unaryMinus())), 0)
            if (pc == null) {
                generalMoves.add(Coordinates(i, coordinates.y))
            } else if (pc.iAmWhite == this.iAmWhite) {
                break
            } else {
                generalMoves.add(Coordinates(i, coordinates.y))
                break
            }
        }
        for (i in coordinates.x.firstAdd(-1) downTo 0) {
            val pc = getPieceFromYourPerspective(coordinates.x.firstAdd(-i).unaryMinus(), 0)
            if (pc == null) {
                generalMoves.add(Coordinates(i, coordinates.y))
            } else if (pc.iAmWhite == this.iAmWhite) {
                break
            } else {
                generalMoves.add(Coordinates(i, coordinates.y))
                break
            }
        }
        for (i in coordinates.y.firstAdd(-1) downTo 0) {
            val pc = getPieceFromYourPerspective(0, coordinates.y.firstAdd(-i).unaryMinus())
            if (pc == null) {
                generalMoves.add(Coordinates(coordinates.x, i))
            } else if (pc.iAmWhite == this.iAmWhite) {
                break
            } else {
                generalMoves.add(Coordinates(coordinates.x, i))
                break
            }
        }
        for (i in coordinates.y.firstAdd(1)..7) {
            val pc = getPieceFromYourPerspective(0, i.firstAdd(coordinates.y.unaryMinus()))
            if (pc == null) {
                generalMoves.add(Coordinates(coordinates.x, i))
            } else if (pc.iAmWhite == this.iAmWhite) {
                break
            } else {
                generalMoves.add(Coordinates(coordinates.x, i))
                break
            }
        }
        update.invoke(
            generalMoves.filterMovesForKing(
                coordinates,
                iAmWhite
            )
        )
    }


}

data class Spot(
    val coordinates: Coordinates,
    var piece: Piece? = null,
    var state: ChessBoardState
)

enum class ChessBoardState {
    DEFAULT,
    LEGAL_TOUCH,
    ILLEGAL_TOUCH,
    PATH,
    DANGER,
    LAST
}


data class Coordinates(val x: Int, val y: Int)
