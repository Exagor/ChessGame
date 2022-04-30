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
            paint.color= Color.argb(255,192,108,136)
        }

        else if ((col+row)%2==0){
            paint.color = Color.argb(255,158,216,219)
        }
        else {
            paint.color = Color.argb(255,4,123,159)
        }

        canvas.drawRect(rectangle, paint)
        if (piece != null) {
            val image = piece!!.image
            val bmp = BitmapFactory.decodeResource(context.resources, image)
            val rect = RectF(x1+8, y1+10, x2-8, y2-10)
            canvas.drawBitmap(bmp!!, null, rect, null)
        }

    }


}
