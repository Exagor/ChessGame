package com.example.chessgame
import android.app.Application
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import java.util.*

class DrawingView @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr), SurfaceHolder.Callback, Runnable {
    lateinit var canvas: Canvas
    //création du board
    val board = Board( 0f, 0f, 0f, 0f,this, context)
    // création des cimetières (finalement on les crée autre part)
    //val cimetiere1 =Cimetiere(0f, 0f, 0f, 0f, this, null)
    //val cimetiere2 =Cimetiere(0f, 0f, 0f, 0f, this, null)
    var screenWidth = 0f
    var screenHeight = 0f
    var drawing = false
    var onfocus : Case? = null
    lateinit var thread: Thread
    lateinit var cases : MutableList<Case>



    fun pause() {
        drawing = false
        thread.join()
    }
    fun resume() {
        drawing = true
        thread = Thread(this)
        thread.start()

    }

    override fun run() {
        cases = board.cases
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
/*
        cimetiere1.cimetiereDebut = (w / 80f)
        cimetiere1.cimetiereHauteur = (h/198f)
        cimetiere1.cimetiereFin = (h*2 / 16f)
        cimetiere1.width = (w*13 / 13.3f)
        cimetiere1.setRect()

        cimetiere2.width = (w*13 / 13.3f)
        cimetiere2.cimetiereDebut = (w / 80f)
        cimetiere2.cimetiereHauteur= ((h*40/46f))
        cimetiere2.cimetiereFin = ((h*40/ 40.3f))
        cimetiere2.setRect()*/
        board.initialisation()
        cases = board.cases

    }

     fun draw() {
        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()
            board.draw(canvas)
            holder.unlockCanvasAndPost(canvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action){
            MotionEvent.ACTION_DOWN -> {
                val x = event.rawX
                val y = event.rawY
                checkCase(x, y)
                println("$x $y")
            }
        }
    return true
    }

    fun checkCase(x:Float, y:Float){
        for (case in cases) {
            if (case.rectangle.contains(x,y)){
                val col = case.col
                val row = case.row
                println("col: $col " + "row: $row")
                if(onfocus == null && case.piece != null) {
                    onfocus = case
                    case.focus = true
                }
                 if (onfocus != null){
                    onfocus!!.piece!!.bouger(case)
                }
                else case.paint.color = Color.MAGENTA
                break
            }
        }


    }
    fun getCimetiere():MutableList<Piece>{
        var res = board.cimetiere.Pieces
        return res
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int,
                                width: Int, height: Int) {}

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread = Thread(this)
        thread.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {}

}
