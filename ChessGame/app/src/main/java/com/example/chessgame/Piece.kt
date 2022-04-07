package com.example.chessgame

abstract class Piece(var position: Pair <Int, Int>, val color: String, var possibleMoves: MutableList<Pair<Int, Int>>){
     fun bouger(newPos: Pair<Int,Int>) {
        if (newPos in possibleMoves) position = newPos
     }
    abstract fun etreMange()
    abstract fun calculateMoves()
}