package com.khatiashvili.chessapp.system

fun Int.firstAdd(add: Int): Int {
    return this + add
}

fun Pair<Int, Int>.checkSize(): Boolean {
    return this.first in 0..7 && this.second in 0..7
}