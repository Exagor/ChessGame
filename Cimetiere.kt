package com.example.jeu_echec

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class Cimetiere(var cimetiereHauteur: Float, var cimetiereDebut: Float, var cimetiereFin: Float, var width: Float, var view: CanonView)
{
    val r = RectF(cimetiereDebut, cimetiereHauteur, cimetiereFin, cimetiereHauteur+width)
    val paint = Paint()

    fun draw(canvas: Canvas) {
        paint.color = Color.GRAY
        canvas.drawRect(r, paint)
    }

}
