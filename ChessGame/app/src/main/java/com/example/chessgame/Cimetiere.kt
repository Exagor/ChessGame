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
}
