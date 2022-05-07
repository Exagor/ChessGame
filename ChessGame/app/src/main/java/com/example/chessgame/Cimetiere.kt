package com.example.chessgame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class Cimetiere(var Pieces : MutableList<Piece>)
{
    var res = false
    fun ajouterPiece(piece: Piece){
        Pieces.add(piece)
    }
    fun reset(){
        Pieces.clear()
    }
    fun getScore(): MutableList<Int>{
        var scorew = 0
        var scoreb = 0
        for (piece in Pieces){
            when (piece.color){
                "white" -> scoreb += piece.value
                "black" -> scorew += piece.value
            }
        }
        return mutableListOf(scorew,scoreb)
    }
}
