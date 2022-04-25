package com.example.chessgame
import android.content.Context
import android.graphics.*
import android.widget.Button

class Case(var row:Int, var col:Int, var x1:Float,var y1: Float,var x2:Float,var y2:Float, val view: DrawingView, val context : Context) {
    var rectangle = RectF(x1, y1, x2, y2)
    val paint = Paint()
    var piece: Piece? = null
    var focus = false


    fun setRect() {
        rectangle.set(x1,y1,x2,y2)
    }

    fun draw(canvas: Canvas) {
        if (focus){
            paint.color= Color.MAGENTA
        }

        else if ((col+row)%2==0){
            paint.color = Color.LTGRAY
        }
        else {
            paint.color = Color.BLACK
        }

        canvas.drawRect(rectangle, paint)
        if (piece != null) {
            val image = piece!!.image
            val bmp = BitmapFactory.decodeResource(context.resources, image)
            canvas.drawBitmap(bmp!!, null, rectangle, null)
        }

    }


}
