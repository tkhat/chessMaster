package com.khatiashvili.chessapp.system.piece

import com.khatiashvili.chessapp.system.SavedMoves
import com.khatiashvili.chessapp.system.board.Coordinates
import com.khatiashvili.chessapp.system.filterMovesForKing
import com.khatiashvili.chessapp.system.firstAdd

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
