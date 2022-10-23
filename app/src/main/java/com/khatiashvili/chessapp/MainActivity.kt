package com.khatiashvili.chessapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.khatiashvili.chessapp.databinding.ActivityMainBinding
import com.khatiashvili.chessapp.system.GamePlay
import com.khatiashvili.chessapp.system.board.Board
import com.khatiashvili.chessapp.system.board.Spot

class MainActivity : AppCompatActivity() {

    private lateinit var binidng: ActivityMainBinding
    private lateinit var boardAdapter: BoardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binidng = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(binidng.root)

        boardAdapter = BoardAdapter(
            GamePlay {
                boardAdapter.notifyDataSetChanged()
            }
        )
        binidng.RecyclierViewBoard.apply {
            adapter = boardAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 8)
        }
        boardAdapter.data = initData()
        binidng.resButton.setOnClickListener {

        }
    }

    fun initData(): MutableList<Spot> {
        var local = mutableListOf<Spot>()
        for (i in 0..7) {
            for (j in 0..7) {
                local.add(Board.board[i][j])
            }
        }
        return local
    }

}