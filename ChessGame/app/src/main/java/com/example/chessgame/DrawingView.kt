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
    val board = Board( 0f, 0f, 0f, 0f,this)
    // création des cimetières
    val cimetiere1 =Cimetiere(0f, 0f, 0f, 0f, this, null)
    val cimetiere2 =Cimetiere(0f, 0f, 0f, 0f, this, null)
    val backgroundPaint = Paint()
    var screenWidth = 0f
    var screenHeight = 0f
    var drawing = true
    var onfocus : Case? = null


    var cases = mutableListOf<Case>()

    init{
        backgroundPaint.color = Color.CYAN
    }
    fun initialisation() { // crée les cases du board et les pièces
        if(cases.size < 64){
            var compteur = 0
            var dx = (board.width) / 8
            var dy = (board.boardFin - board.boardHauteur) / 8
            var x_i = board.boardDebut
            var y_i = board.boardHauteur
            for (i in 1..8) {
                for (j in 1..8) {
                    var piece: Piece?

                    // on ajoute crée pions blancs
                    if (i == 7) {
                        cases.add(Case(i, j, x_i, y_i, x_i + dx, y_i + dy, this, context))
                        piece = Pion(cases[compteur], "white")
                        cases[compteur].piece = piece

                    }
                    if (i == 2) {
                        cases.add(Case(i, j, x_i, y_i, x_i + dx, y_i + dy, this, context))
                        piece = Pion(cases[compteur], "black")
                        cases[compteur].piece = piece

                    } else cases.add(Case(i, j, x_i, y_i, x_i + dx, y_i + dy, this, context))
                    compteur += 1
                    x_i += dx
                }
                y_i += dy
                x_i = board.boardDebut
            }
        }
    }
    lateinit var thread: Thread
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
        initialisation()
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

            cimetiere1.draw(canvas)
            cimetiere2.draw(canvas)
            for (case in cases){
                case.setRect()
                case.draw(canvas)
            }

            holder.unlockCanvasAndPost(canvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action){
            MotionEvent.ACTION_DOWN -> {
                val x = event.rawX
                val y = event.rawY -75
                checkCase(x, y)
            }
        }
    return true
    }
    fun checkCase(x:Float, y:Float) {

        for (case in cases) {
            if (case.rectangle.contains(x,y)){
                if(onfocus == null && case.piece != null) {
                    onfocus = case
                    case.paint.color = Color.MAGENTA
                }

                 if (onfocus != null){
                    onfocus!!.piece!!.bouger(case)
                }
                break
            }
        }


    }
    override fun surfaceChanged(holder: SurfaceHolder, format: Int,
                                width: Int, height: Int) {}

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread = Thread(this)
        thread.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {}

}
