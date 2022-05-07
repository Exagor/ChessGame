package com.example.chessgame
import android.content.Context
import android.graphics.*

class Case(
    var row: Int,
    var col: Int,
    private var x1: Float,
    private var y1: Float,
    private var x2: Float,
    private var y2: Float,
    private val context: Context
) {
    var rectangle = RectF(x1, y1, x2, y2)
    private val paint = Paint()
    var piece: Piece? = null
    var focus = false
    var accessible = false

    fun setRect() {
        rectangle.set(x1,y1,x2,y2)
    }

    fun draw(canvas: Canvas) {
        when {
            accessible -> paint.color = Color.argb(255,192,108,100)

            focus -> paint.color= Color.argb(255,192,108,136)

            (col+row)%2==0 -> paint.color = Color.argb(255,158,216,219)

            else -> paint.color = Color.argb(255,4,123,159)

        }

        canvas.drawRect(rectangle, paint)
        if (piece != null) {
            val image = piece!!.image
            val bmp = BitmapFactory.decodeResource(context.resources, image)
            val rect = RectF(x1+8, y1+8, x2-8, y2-8)
            canvas.drawBitmap(bmp!!, null, rect, null)
        }

    }


}
