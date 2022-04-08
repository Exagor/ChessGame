package com.example.chessgame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class DrawingView @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr), SurfaceHolder.Callback, Runnable {
    lateinit var canvas: Canvas
    val board = Board( 0f, 0f, 0f, 0f,this)
    val cimetiere1 =Cimetiere(0f, 0f, 0f, 0f, this)
    val cimetiere2 =Cimetiere(0f, 0f, 0f, 0f, this)
    val backgroundPaint = Paint()
    var screenWidth = 0f
    var screenHeight = 0f
    var drawing = false
    lateinit var thread: Thread

    private val imgpiece = setOf(
        R.drawable.bishop_black,
        R.drawable.bishop_white,
        R.drawable.king_black,
        R.drawable.king_white,
        R.drawable.queen_black,
        R.drawable.queen_white,
        R.drawable.rook_black,
        R.drawable.rook_white,
        R.drawable.knight_black,
        R.drawable.knight_white,
        R.drawable.pawn_black,
        R.drawable.pawn_white,
    )

    fun pause() {
        drawing = false
        thread.join()
    }
    fun resume(){
        drawing = true
        thread = Thread(this)
        thread.start()
    }
    override fun run() {
        while (drawing) {
            draw()
        }
    }
    override fun onSizeChanged(w:Int, h:Int, oldw:Int, oldh:Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        screenWidth = w.toFloat()
        screenHeight = h.toFloat()

        board.width = (w*13 / 13.3f)
        board.boardHauteur= (h*2 / 16f)
        board.boardDebut = (w / 80f)
        board.boardFin = ((h*40/46f))
        board.setRect()

        cimetiere1.cimetiereDebut = (w / 80f)
        cimetiere1.cimetiereHauteur = (h/198f)
        cimetiere1.cimetiereFin = (h*2 / 16f)
        cimetiere1.width = (w*13 / 13.3f)
        cimetiere1.setRect()

        cimetiere2.width = (w*13 / 13.3f)
        cimetiere2.cimetiereDebut = (w / 80f)
        cimetiere2.cimetiereHauteur= ((h*40/46f))
        cimetiere2.cimetiereFin = ((h*40/ 40.3f))
        cimetiere2.setRect()


    }


    fun draw() {
        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()
            canvas.drawRect(0f, 0f, canvas.width.toFloat(),
                canvas.height.toFloat(), backgroundPaint)
            //board.draw(canvas)
            cimetiere1.draw(canvas)
            cimetiere2.draw(canvas)

            var dx = (board.width)/8
            var dy = (board.boardFin-board.boardHauteur)/8
            var x_i = board.boardDebut
            var y_i = board.boardHauteur
            for (i in 1..8){
                for (j in 1..8){
                    val case = Case(i,j,null, x_i,y_i,x_i+dx,y_i+dy, this)
                    case.setRect()
                    case.draw(canvas)
                    x_i+=dx
                }
                y_i+=dy
                x_i=board.boardDebut
            }

            holder.unlockCanvasAndPost(canvas)
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int,
                                width: Int, height: Int) {}

    override fun surfaceCreated(holder: SurfaceHolder) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {}

}
