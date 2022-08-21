package com.khatiashvili.chessapp.system

class HasKingCheckerUtil(
    private val testBoard: MutableList<MutableList<Spot>>,
    private val iAmWhite: Boolean
) {

    private lateinit var kingCoordinates: Coordinates

    init {
        testBoard.forEach {
            it.forEach { spot ->
                if (spot.piece is King && spot.piece?.iAmWhite == iAmWhite) {
                    kingCoordinates = spot.coordinates
                }
            }
        }
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

    private fun checkPawns(): Boolean {
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

    private fun checkKnight(): Boolean {
        var hasCheck = false
        Knight.knightPossibleMoves.forEach {
            val element = getPieceFromKingsPerspective(it.first, it.second)
            if (element is Knight && element.iAmWhite != this.iAmWhite) {
                return true
            }
        }
        return hasCheck
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

    fun hasKingCheck(): Boolean {
        return checkVerticalAndHorizontal() || checkKnight() || checkDiagonal() || checkPawns()
    }

    private fun getPieceFromKingsPerspective(x: Int, y: Int): Piece? {
        /**
         * if x and y Coordinates is inside the size of the Array func return the element
         * otherwise it returns null
         * **/
        return if (kingCoordinates.x.firstAdd(x) in 0..7 && kingCoordinates.y.firstAdd(y) in 0..7) {
            testBoard[kingCoordinates.x.firstAdd(x)][kingCoordinates.y.firstAdd(y)].piece
        } else {
            null
        }
    }

}