package com.example.chessgame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class Cimetiere(/*var cimetiereHauteur: Float, var cimetiereDebut: Float, var cimetiereFin: Float, var right: Float, var view: DrawingView, */var Pieces : MutableList<Piece>)
{
   /* val r = RectF(cimetiereDebut, cimetiereHauteur, cimetiereDebut+right, cimetiereFin)
    val paint = Paint()

    fun setRect() {
        r.set(cimetiereDebut, cimetiereHauteur,
            cimetiereDebut + right, cimetiereFin)
    }

    fun draw(canvas: Canvas) {
        paint.color = Color.GRAY
        canvas.drawRect(r, paint)


    }*/
    fun ajouterPiece(piece: Piece){
        //if (Pieces == null)
            Pieces = mutableListOf(piece)
        Pieces.add(piece)
    }
}
