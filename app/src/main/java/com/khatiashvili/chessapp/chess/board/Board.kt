package com.khatiashvili.chessapp.chess.board

import com.khatiashvili.chessapp.chess.piece.PColor
import com.khatiashvili.chessapp.chess.piece.Piece
import com.khatiashvili.chessapp.chess.piece.PieceType

object Board {

    var board = mutableListOf(
        mutableListOf(
            Spot(
                0, 0,
                piece = Piece(PColor.WHITE, PieceType.ROOK),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                0, 1,
                piece = Piece(PColor.WHITE, PieceType.KNIGHT),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                0, 2,
                piece = Piece(PColor.WHITE, PieceType.BISHOP),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                0, 3,
                piece = Piece(PColor.WHITE, PieceType.QUEEN),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                x = 0, y = 4,
                piece = Piece(PColor.WHITE, PieceType.KING),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                0, 5,
                piece = Piece(PColor.WHITE, PieceType.BISHOP),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                0, 6,
                piece = Piece(PColor.WHITE, PieceType.KNIGHT),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                0, 7,
                piece = Piece(PColor.WHITE, PieceType.ROOK),
                state = ChessSpotState.DEFAULT
            )
        ),
        mutableListOf(
            Spot(
                1, 0,
                piece = Piece(PColor.WHITE, PieceType.PAWN),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                1, 1,
                piece = Piece(PColor.WHITE, PieceType.PAWN),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                1, 2,
                piece = Piece(PColor.WHITE, PieceType.PAWN),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                1, 3,
                piece = Piece(PColor.WHITE, PieceType.PAWN),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                1, 4,
                piece = Piece(PColor.WHITE, PieceType.PAWN),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                1, 5,
                piece = Piece(PColor.WHITE, PieceType.PAWN),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                1, 6,
                piece = Piece(PColor.WHITE, PieceType.PAWN),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                1, 7,
                piece = Piece(PColor.WHITE, PieceType.PAWN),
                state = ChessSpotState.DEFAULT
            ),
        ),
        mutableListOf(
            Spot(
                2, 0,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                2, 1,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                2, 2,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                2, 3,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                2, 4,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                2, 5,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                2, 6,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                2, 7,
                state = ChessSpotState.DEFAULT
            )
        ),
        mutableListOf(
            Spot(
                3, 0,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                3, 1,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                3, 2,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                3, 3,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                3, 4,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                3, 5,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                3, 6,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                3, 7,
                state = ChessSpotState.DEFAULT
            )
        ),
        mutableListOf(
            Spot(
                4, 0,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                4, 1,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                4, 2,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                4, 3,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                4, 4,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                4, 5,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                4, 6,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                4, 7,
                state = ChessSpotState.DEFAULT
            )
        ),
        mutableListOf(
            Spot(
                5, 0,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                5, 1,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                5, 2,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                5, 3,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                5, 4,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                5, 5,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                5, 6,
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                5, 7,
                state = ChessSpotState.DEFAULT
            )
        ),
        mutableListOf(
            Spot(
                6, 0,
                piece = Piece(PColor.BLACK, PieceType.PAWN),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                6, 1,
                piece = Piece(PColor.BLACK, PieceType.PAWN),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                6, 2,
                piece = Piece(PColor.BLACK, PieceType.PAWN),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                6, 3,
                piece = Piece(PColor.BLACK, PieceType.PAWN),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                6, 4,
                piece = Piece(PColor.BLACK, PieceType.PAWN),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                6, 5,
                piece = Piece(PColor.BLACK, PieceType.PAWN),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                6, 6,
                piece = Piece(PColor.BLACK, PieceType.PAWN),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                6, 7,
                piece = Piece(PColor.BLACK, PieceType.PAWN),
                state = ChessSpotState.DEFAULT
            )
        ),
        mutableListOf(
            Spot(
                7, 0,
                piece = Piece(PColor.BLACK, PieceType.ROOK),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                7, 1,
                piece = Piece(PColor.BLACK, PieceType.KNIGHT),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                7, 2,
                piece = Piece(PColor.BLACK, PieceType.BISHOP),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                7, 3,
                piece = Piece(PColor.BLACK, PieceType.QUEEN),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                7, 4,
                piece = Piece(PColor.BLACK, PieceType.KING),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                7, 5,
                piece = Piece(PColor.BLACK, PieceType.BISHOP),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                7, 6,
                piece = Piece(PColor.BLACK, PieceType.KNIGHT),
                state = ChessSpotState.DEFAULT
            ),
            Spot(
                7, 7,
                piece = Piece(PColor.BLACK, PieceType.ROOK),
                state = ChessSpotState.DEFAULT
            )
        )
    )

    fun getBoardForTesting(): MutableList<MutableList<Spot>> {

        var testBoard: MutableList<MutableList<Spot>> =
            MutableList(8) { MutableList(8) { Spot(1, 1, null, ChessSpotState.DANGER) } }
        for (i in board.indices) {
            for (j in board.indices) {
                testBoard[i][j] = board[i][j].copy()
            }
        }
        return testBoard
    }

    fun getMyPieces(color: PColor): List<Spot> {
        val result = mutableListOf<Spot>()
        board.forEach { list ->
            list.forEach { spot ->
                if (spot.piece?.color == color) {
                    result.add(spot)
                }
            }
        }
        return result
    }
}