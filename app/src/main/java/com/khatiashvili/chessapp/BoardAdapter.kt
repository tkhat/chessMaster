package com.khatiashvili.chessapp

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khatiashvili.chessapp.databinding.SpotItemBinding
import com.khatiashvili.chessapp.system.GamePlay
import com.khatiashvili.chessapp.system.board.ChessBoardState
import com.khatiashvili.chessapp.system.board.Spot
import com.khatiashvili.chessapp.system.piece.*

class BoardAdapter(val gamePlay: GamePlay) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    var data = mutableListOf<Spot>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardAdapter.ViewHolder {
        return ViewHolder(
            SpotItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BoardAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(private val binding: SpotItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(spot: Spot) {
            spot.piece?.let { drawPiece(it, binding) }
            drawSpot(spot, binding)
            drawState(spot.state,binding)
            binding.root.setOnClickListener {
                gamePlay.touchHappen(spot)
            }
        }

    }


    private fun drawSpot(spot: Spot, binding: SpotItemBinding) {
        if ((spot.coordinates.x + 1) % 2 == 0) {
            if ((spot.coordinates.y + 1) % 2 == 0) {
                binding.root.setBackgroundResource(R.drawable.black_spot)
            } else {
                binding.root.setBackgroundResource(R.drawable.white_spot)
            }
        } else {
            if ((spot.coordinates.y + 1) % 2 == 0) {
                binding.root.setBackgroundResource(R.drawable.white_spot)
            } else {
                binding.root.setBackgroundResource(R.drawable.black_spot)
            }
        }
    }


    private fun drawPiece(piece: Piece, binding: SpotItemBinding) {
        binding.chessPieceImgView.visibility = View.VISIBLE
        val isWhite = piece.iAmWhite
        when (piece) {
            is Rook -> {
                binding.chessPieceImgView.setImageResource(if (isWhite) R.drawable.white_rook else R.drawable.black_rook)
            }
            is Knight -> {
                binding.chessPieceImgView.setImageResource(if (isWhite) R.drawable.white_knight else R.drawable.black_knight)
            }
            is Bishop -> {
                binding.chessPieceImgView.setImageResource(if (isWhite) R.drawable.white_bishop else R.drawable.black_bishop)
            }
            is Queen -> {
                binding.chessPieceImgView.setImageResource(if (isWhite) R.drawable.white_queen else R.drawable.black_queen)
            }
            is King -> {
                binding.chessPieceImgView.setImageResource(if (isWhite) R.drawable.white_king else R.drawable.black_king)
            }
            is Pawn -> {
                binding.chessPieceImgView.setImageResource(if (isWhite) R.drawable.pawn_white else R.drawable.pawn_black)
            }
        }
    }

    private fun drawState(state: ChessBoardState, binding: SpotItemBinding) {
        binding.directionDrawable.visibility = View.VISIBLE
        when(state) {
            ChessBoardState.LAST -> {
                binding.directionDrawable.setImageResource(R.drawable.last_move_color)
                fixSize(binding.directionDrawable,true)
            }
            ChessBoardState.DANGER -> {
                binding.directionDrawable.setImageResource(R.drawable.capture_aim)
                fixSize(binding.directionDrawable,true)
            }
            ChessBoardState.PATH -> {
                binding.directionDrawable.setImageResource(R.drawable.free_direction)
                fixSize(binding.directionDrawable,false)
            }
            ChessBoardState.ILLEGAL_TOUCH -> {
                binding.directionDrawable.setImageResource(R.drawable.ilegal_touch_color)
                fixSize(binding.directionDrawable,true)
            }
            ChessBoardState.LEGAL_TOUCH -> {
                binding.directionDrawable.setImageResource(R.drawable.legal_touch_color)
                fixSize(binding.directionDrawable,true)
            }
            ChessBoardState.DEFAULT -> {
                binding.directionDrawable.visibility = View.GONE
            }
        }
    }

    fun fixSize(v: View, doExpand: Boolean) {
        val normalSize = 35.dp
        val expandSize = 45.dp
        val layoutParams: ViewGroup.LayoutParams = v.layoutParams

        if (doExpand) {
            layoutParams.height = expandSize
            layoutParams.width = expandSize
            v.layoutParams = layoutParams
        } else {
            layoutParams.height = normalSize
            layoutParams.width = normalSize
            v.layoutParams = layoutParams
        }
    }

}

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()