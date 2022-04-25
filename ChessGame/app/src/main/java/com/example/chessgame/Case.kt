package com.example.chessgame
import android.content.Context
import android.graphics.*

class Case(var row:Int, var col:Int, var x1:Float,var y1: Float,var x2:Float,var y2:Float, val view: DrawingView, val context : Context) {
    var rectangle = RectF(x1, y1, x2, y2)
    val paint = Paint()
    var piece: Piece? = null

    private val whitepieces = listOf(
        R.drawable.bishop_white,
        R.drawable.queen_white,
        R.drawable.king_white,
        R.drawable.rook_white,
        R.drawable.knight_white,
        R.drawable.pawn_white,
    )
    private val blackpieces = listOf(
        R.drawable.bishop_black,
        R.drawable.queen_black,
        R.drawable.king_black,
        R.drawable.rook_black,
        R.drawable.knight_black,
        R.drawable.pawn_black,
    )
    fun setRect() {
        rectangle.set(x1,y1,x2,y2)
    }

    fun draw(canvas: Canvas) {
        if ((col+row)%2==0){
            paint.color = Color.LTGRAY
        }
        else{
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
