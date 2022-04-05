package com.example.chessgame

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF



class Board(var boardHauteur: Float, var boardDebut: Float, var boardFin: Float, var width: Float, var view: DrawingView) {
    var board = RectF(boardDebut, boardHauteur, boardDebut+width, boardFin)

    fun setRect() {
        board.set(boardDebut, boardHauteur,
            boardDebut + width, boardFin)
    }

    var paint = Paint()
    fun draw(canvas: Canvas) {
        paint.color = Color.BLUE
        canvas.drawRect(board, paint)
    }

}
