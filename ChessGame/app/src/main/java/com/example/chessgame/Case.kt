package com.example.chessgame
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class Case(var col:Int, var row:Int,piece: Piece?, var x1:Float,var y1: Float,var x2:Float,var y2:Float, view: DrawingView) {
    var case = RectF(x1, y1, x2, y2)
    val paint = Paint()

    fun setRect() {
        case.set(x1,y1,x2,y2)
    }

    fun draw(canvas: Canvas) {
        if ((col+row)%2==0){
            paint.color = Color.LTGRAY
        }
        else{
            paint.color = Color.BLACK
        }
        canvas.drawRect(case, paint)
    }


}
