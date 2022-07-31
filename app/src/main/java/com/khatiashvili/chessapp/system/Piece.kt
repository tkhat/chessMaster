package com.khatiashvili.chessapp.system

import android.opengl.ETC1.isValid
import com.khatiashvili.chessapp.system.Board.board
import com.khatiashvili.chessapp.system.Knight.Companion.knightPossibleMoves

abstract class Piece {
    abstract val iAmWhite: Boolean
    abstract var coordinates: Coordinates
    private lateinit var kingCoordinates: Coordinates

    abstract fun isValidMove(end: Coordinates, isValid: (Boolean) -> Unit)
    abstract fun defineMoves(update: (moves: List<Coordinates>) -> Unit)

    protected fun hasKingCheck(): Boolean {
        initKingCoordinates()
        return checkVerticalAndHorizontal() || checkKnight() || checkDiagonal() || checkPawns()
    }

    fun checkPawns(): Boolean {
        val result = false
        if (iAmWhite) {
            val leftPc = getPieceFromKingsPerspective(1, -1)
            val rightPc = getPieceFromKingsPerspective(1, 1)
            if (leftPc?.iAmWhite != this.iAmWhite && leftPc is Pawn ||
                rightPc?.iAmWhite != this.iAmWhite && rightPc is Pawn
            ) {
                return true
            }
        } else {
            val leftPc = getPieceFromKingsPerspective(-1, 1)
            val rightPc = getPieceFromKingsPerspective(-1, -1)
            if (leftPc?.iAmWhite != this.iAmWhite && leftPc is Pawn ||
                rightPc?.iAmWhite != this.iAmWhite && rightPc is Pawn
            ) {
                return true
            }
        }
        return result
    }

    private fun initKingCoordinates() {
        kingCoordinates = if (iAmWhite) Coordinates(0, 4) else Coordinates(7, 4)
    }

    private fun checkVerticalAndHorizontal(): Boolean {
        var result = false
        for (i in kingCoordinates.x - 1 downTo 0) {
            val piece = getPieceFromKingsPerspective(i, kingCoordinates.y)
            if (piece?.iAmWhite == this.iAmWhite) {
                break
            } else if (piece is Queen || piece is Rook) {
                return true
            }
        }
        for (i in kingCoordinates.x + 1..7) {
            val piece = getPieceFromKingsPerspective(i, kingCoordinates.y)
            if (piece?.iAmWhite == this.iAmWhite) {
                break
            } else if (piece is Queen || piece is Rook) {
                return true
            }
        }
        for (i in kingCoordinates.y - 1 downTo 0) {
            val piece = getPieceFromKingsPerspective(kingCoordinates.x, i)
            if (piece?.iAmWhite == this.iAmWhite) {
                break
            } else if (piece is Queen || piece is Rook) {
                return true
            }
        }
        for (i in kingCoordinates.y + 1..7) {
            val piece = getPieceFromKingsPerspective(kingCoordinates.x, i)
            if (piece?.iAmWhite == this.iAmWhite) {
                break
            } else if (piece is Queen || piece is Rook) {
                return true
            }
        }
        return result
    }


    private fun getPieceFromKingsPerspective(x: Int, y: Int): Piece? {
        /**
         * if x and y Coordinates is inside the size of the Array func return the element
         * otherwise it returns null
         * **/
        return if (kingCoordinates.x.firstAdd(x) in 0..7 && kingCoordinates.y.firstAdd(y) in 0..7) {
            board[kingCoordinates.x.firstAdd(x)][kingCoordinates.y.firstAdd(y)].piece
        } else {
            null
        }
    }

    protected fun getPiece(x: Int, y: Int): Piece? {
        return if (coordinates.x.firstAdd(x) in 0..7 && coordinates.y.firstAdd(y) in 0..7) {
            board[coordinates.x.firstAdd(x)][coordinates.y.firstAdd(y)].piece
        } else {
            null
        }
    }

    private fun checkDiagonal(): Boolean {
        var spaceA = true
        var spaceB = true
        var spaceC = true
        var spaceD = true
        for (i in 1..7) {

            if (spaceA) {
                val a = getPieceFromKingsPerspective(i.unaryMinus(), i.unaryMinus())
                if (a?.iAmWhite == this.iAmWhite) {
                    spaceA = false
                } else {
                    if (a is Queen || a is Bishop) {
                        return true
                    }
                }
            }
            if (spaceB) {
                val b = getPieceFromKingsPerspective(i, i)
                if (b?.iAmWhite == this.iAmWhite) {
                    spaceB = false
                } else {
                    if (b is Queen || b is Bishop) {
                        return true
                    }
                }
            }
            if (spaceC) {
                val c = getPieceFromKingsPerspective(i.unaryMinus(), i)
                if (c?.iAmWhite == this.iAmWhite) {
                    spaceC = false
                } else {
                    if (c is Queen || c is Bishop) {
                        return true
                    }
                }
            }
            if (spaceD) {
                val d = getPieceFromKingsPerspective(i, i.unaryMinus())
                if (d?.iAmWhite == this.iAmWhite) {
                    spaceD = false
                } else {
                    if (d is Queen || d is Bishop) {
                        return true
                    }
                }
            }
        }
        return false
    }


    private fun checkKnight(): Boolean {
        var hasCheck = false
        knightPossibleMoves.forEach {
            val element = getPieceFromKingsPerspective(it.first, it.second)
            if (element is Knight && element.iAmWhite != this.iAmWhite) {
                return true
            }
        }
        return hasCheck
    }

    protected fun setKingCoordinates(coordinates: Coordinates) {
        this.coordinates = coordinates
    }
}

class Pawn(
    override val iAmWhite: Boolean,
    override var coordinates: Coordinates
) : Piece() {
    private var checkEnPassant: SavedMoves? = null

    override fun isValidMove(end: Coordinates, isValid: (Boolean) -> Unit) {
        if (hasKingCheck()) {
            isValid(false)
            return
        }
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

    override fun defineMoves(update: (moves: List<Coordinates>) -> Unit) {
        var generalMovies = mutableListOf<Coordinates>()
        if (this.iAmWhite) {
            if (coordinates.x == 1) {
                if (getPiece(1, 0) == null) {
                    generalMovies.add(
                        Coordinates(
                            coordinates.x.firstAdd(1),
                            coordinates.y
                        )
                    )
                    if (getPiece(2, 0) == null) {

                        generalMovies.add(
                            Coordinates(
                                coordinates.x.firstAdd(2),
                                coordinates.y
                            )
                        )
                    }
                }
            } else {
                if (getPiece(1, 0) == null) {
                    generalMovies.add(
                        Coordinates(
                            coordinates.x.firstAdd(1),
                            coordinates.y
                        )
                    )
                }
            }
            if (getPiece(1, -1)?.iAmWhite != this.iAmWhite
                && getPiece(1, -1) is Piece
            ) {
                generalMovies.add(
                    Coordinates(
                        coordinates.x.firstAdd(1),
                        coordinates.y.firstAdd(-1)
                    )
                )
            }
            if (getPiece(1, 1)?.iAmWhite != this.iAmWhite
                && getPiece(1, 1) is Piece
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
                if (getPiece(-1, 0) == null) {
                    generalMovies.add(
                        Coordinates(
                            coordinates.x.firstAdd(-1),
                            coordinates.y
                        )
                    )
                    if (getPiece(-2, 0) == null) {
                        generalMovies.add(
                            Coordinates(
                                coordinates.x.firstAdd(-2),
                                coordinates.y
                            )
                        )
                    }
                }
            } else {
                if (getPiece(-1, 0) == null) {
                    generalMovies.add(
                        Coordinates(
                            coordinates.x.firstAdd(-1),
                            coordinates.y
                        )
                    )
                }
            }
            if (getPiece(-1, -1)?.iAmWhite != this.iAmWhite
                && getPiece(-1, -1) is Piece
            ) {
                generalMovies.add(
                    Coordinates(
                        coordinates.x.firstAdd(-1),
                        coordinates.y.firstAdd(-1)
                    )
                )
            }
            if (getPiece(-1, 1)?.iAmWhite != this.iAmWhite
                && getPiece(-1, 1) is Piece
            ) {
                generalMovies.add(
                    Coordinates(
                        coordinates.x.firstAdd(-1),
                        coordinates.y.firstAdd(1)
                    )
                )
            }
        }
        update.invoke(generalMovies)
    }
    /***
     * Needs to Defines All moves that does not involves check for his king
     * So if kings already had a check this function must defines only that moves
     * which defence King from the Check
     * ***/

}


class King(override val iAmWhite: Boolean, override var coordinates: Coordinates) : Piece() {
    override fun isValidMove(end: Coordinates, isValid: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun defineMoves(update: (moves: List<Coordinates>) -> Unit) {
        TODO("Not yet implemented")
    }

}

class Rook(override val iAmWhite: Boolean, override var coordinates: Coordinates) : Piece() {
    override fun isValidMove(end: Coordinates, isValid: (Boolean) -> Unit) {
        if (hasKingCheck()) {
            isValid(false)
            return
        }
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

    override fun defineMoves(update: (moves: List<Coordinates>) -> Unit) {
        var generalMoves = mutableListOf<Coordinates>()
        for (i in coordinates.x.firstAdd(1)..7) {
            val pc = getPiece((i.firstAdd(coordinates.x.unaryMinus())), 0)
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
            val pc = getPiece(coordinates.x.firstAdd(-i).unaryMinus(), 0)
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
            val pc = getPiece(0, coordinates.y.firstAdd(-i).unaryMinus())
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
            val pc = getPiece(0, i.firstAdd(coordinates.y.unaryMinus()))
            if (pc == null) {
                generalMoves.add(Coordinates(coordinates.x, i))
            } else if (pc.iAmWhite == this.iAmWhite) {
                break
            } else {
                generalMoves.add(Coordinates(coordinates.x, i))
                break
            }
        }
        update.invoke(generalMoves)
    }

}

class Knight(override val iAmWhite: Boolean, override var coordinates: Coordinates) : Piece() {
    override fun isValidMove(end: Coordinates, isValid: (Boolean) -> Unit) {
        if (hasKingCheck()) {
            isValid(false)
            return
        }
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

    override fun defineMoves(update: (moves: List<Coordinates>) -> Unit) {
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
        update.invoke(generalMoves)
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
    override fun isValidMove(end: Coordinates, isValid: (Boolean) -> Unit) {
        if (hasKingCheck()) {
            isValid(false)
            return
        }
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

    override fun defineMoves(update: (moves: List<Coordinates>) -> Unit) {
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
        update.invoke(generalMoves)
    }

}

class Queen(override val iAmWhite: Boolean, override var coordinates: Coordinates) : Piece() {
    override fun isValidMove(end: Coordinates, isValid: (Boolean) -> Unit) {
        if (hasKingCheck()) {
            isValid(false)
            return
        }
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

    override fun defineMoves(update: (moves: List<Coordinates>) -> Unit) {
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
            val pc = getPiece((i.firstAdd(coordinates.x.unaryMinus())), 0)
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
            val pc = getPiece(coordinates.x.firstAdd(-i).unaryMinus(), 0)
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
            val pc = getPiece(0, coordinates.y.firstAdd(-i).unaryMinus())
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
            val pc = getPiece(0, i.firstAdd(coordinates.y.unaryMinus()))
            if (pc == null) {
                generalMoves.add(Coordinates(coordinates.x, i))
            } else if (pc.iAmWhite == this.iAmWhite) {
                break
            } else {
                generalMoves.add(Coordinates(coordinates.x, i))
                break
            }
        }
        update.invoke(generalMoves)
    }


}

class Spot(
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
