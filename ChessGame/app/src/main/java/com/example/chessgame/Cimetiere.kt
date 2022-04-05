package com.example.chessgame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class Cimetiere(var cimetiereHauteur: Float, var cimetiereDebut: Float, var cimetiereFin: Float, var width: Float, var view: DrawingView)
{
    val r = RectF(cimetiereDebut, cimetiereHauteur, cimetiereDebut+width, cimetiereFin)
    val paint = Paint()

    fun setRect() {
        r.set(cimetiereDebut, cimetiereHauteur,
            cimetiereDebut + width, cimetiereFin)
    }

    fun draw(canvas: Canvas) {
        paint.color = Color.GRAY
        canvas.drawRect(r, paint)
    }

}
