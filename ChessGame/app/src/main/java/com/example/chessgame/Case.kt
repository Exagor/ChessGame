package com.example.chessgame
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

class Case(var cornerdownl: Float,var cornerupl: Float, var cornerdownr: Float, var cornerupr: Float, val color:Int, val posx: Int, val posy:Int, val col : Int, val row: Int, var piece: Piece?) {
    var case = RectF(cornerdownl, cornerupl, cornerdownr, cornerupr)
    val paint = Paint()
    val position = arrayOf(posx,posy)

    fun draw(canvas: Canvas) {
        if (color==0){
            paint.color = Color.WHITE
        }
        else{
            paint.color = Color.BLACK
        }
        canvas.drawRect(case, paint)
    }


}
