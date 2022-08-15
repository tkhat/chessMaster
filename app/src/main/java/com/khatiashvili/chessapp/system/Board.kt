package com.khatiashvili.chessapp.system

import android.content.SharedPreferences

object Board {

    var board = mutableListOf(
        mutableListOf(
            Spot(
                coordinates = Coordinates(0, 0),
                piece = Rook(true, Coordinates(0, 0)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(0, 1),
                piece = Knight(true, Coordinates(0, 1)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(0, 2),
                piece = Bishop(true, Coordinates(0, 2)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(0, 3),
                piece = Queen(true, Coordinates(0, 3)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(0, 4),
                piece = King(true, Coordinates(0, 4)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(0, 5),
                piece = Bishop(true, Coordinates(0, 5)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(0, 6),
                piece = Knight(true, Coordinates(0, 6)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(0, 7),
                piece = Rook(true, Coordinates(0, 7)),
                state = ChessBoardState.DEFAULT
            )
        ),
        mutableListOf(
            Spot(
                coordinates = Coordinates(1, 0),
                piece = Pawn(true, Coordinates(1, 0)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(1, 1),
                piece = Pawn(true, Coordinates(1, 1)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(1, 2),
                piece = Pawn(true, Coordinates(1, 2)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(1, 3),
                piece = Pawn(true, Coordinates(1, 3)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(1, 4),
                piece = Pawn(true, Coordinates(1, 4)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(1, 5),
                piece = Pawn(true, Coordinates(1, 5)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(1, 6),
                piece = Pawn(true, Coordinates(1, 6)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(1, 7),
                piece = Pawn(true, Coordinates(1, 7)),
                state = ChessBoardState.DEFAULT
            ),
        ),
        mutableListOf(
            Spot(
                coordinates = Coordinates(2, 0),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(2, 1),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(2, 2),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(2, 3),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(2, 4),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(2, 5),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(2, 6),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(2, 7),
                state = ChessBoardState.DEFAULT
            )
        ),
        mutableListOf(
            Spot(
                coordinates = Coordinates(3, 0),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(3, 1),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(3, 2),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(3, 3),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(3, 4),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(3, 5),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(3, 6),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(3, 7),
                state = ChessBoardState.DEFAULT
            )
        ),
        mutableListOf(
            Spot(
                coordinates = Coordinates(4, 0),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(4, 1),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(4, 2),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(4, 3),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(4, 4),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(4, 5),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(4, 6),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(4, 7),
                state = ChessBoardState.DEFAULT
            )
        ),
        mutableListOf(
            Spot(
                coordinates = Coordinates(5, 0),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(5, 1),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(5, 2),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(5, 3),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(5, 4),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(5, 5),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(5, 6),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(5, 7),
                state = ChessBoardState.DEFAULT
            )
        ),
        mutableListOf(
            Spot(
                coordinates = Coordinates(6, 0),
                piece = Pawn(false, Coordinates(6, 0)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(6, 1),
                piece = Pawn(false, Coordinates(6, 1)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(6, 2),
                piece = Pawn(false, Coordinates(6, 2)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(6, 3),
                piece = Pawn(false, Coordinates(6, 3)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(6, 4),
                piece = Pawn(false, Coordinates(6, 4)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(6, 5),
                piece = Pawn(false, Coordinates(6, 5)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(6, 6),
                piece = Pawn(false, Coordinates(6, 6)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(6, 7),
                piece = Pawn(false, Coordinates(6, 7)),
                state = ChessBoardState.DEFAULT
            )
        ),
        mutableListOf(
            Spot(
                coordinates = Coordinates(7, 0),
                piece = Rook(false, Coordinates(7, 0)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(7, 1),
                piece = Knight(false, Coordinates(7, 1)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(7, 2),
                piece = Bishop(false, Coordinates(7, 2)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(7, 3),
                piece = Queen(false, Coordinates(7, 3)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(7, 4),
                piece = King(false, Coordinates(7, 4)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(7, 5),
                piece = Bishop(false, Coordinates(7, 5)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(7, 6),
                piece = Knight(false, Coordinates(7, 6)),
                state = ChessBoardState.DEFAULT
            ),
            Spot(
                coordinates = Coordinates(7, 7),
                piece = Rook(false, Coordinates(7, 7)),
                state = ChessBoardState.DEFAULT
            )
        )
    )

    fun getBoardForTesting(): MutableList<MutableList<Spot>> {
        var testBoard = mutableListOf(
            mutableListOf(
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
            ), mutableListOf(
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
            ), mutableListOf(
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
            ), mutableListOf(
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
            ), mutableListOf(
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
            ), mutableListOf(
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
            ), mutableListOf(
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
            ), mutableListOf(
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
                Spot(Coordinates(0, 1), null, ChessBoardState.LAST),
            )
        )

        for (i in board.indices) {
            for (j in board.indices) {
                testBoard[i][j] = board[i][j].copy()
            }
        }
        return testBoard
    }
}