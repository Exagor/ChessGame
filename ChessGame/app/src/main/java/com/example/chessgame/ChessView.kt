package com.example.chessgame

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import kotlin.math.min


class ChessView @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr), SurfaceHolder.Callback, Runnable {
    private lateinit var canvas: Canvas
    lateinit var scorew: TextView
    lateinit var scoreb: TextView
    //création du board
    private var board = Board( 0f, 0f, 0f, 0f,this, context)
    private var screenWidth = 0f
    private var screenHeight = 0f
    private var onfocus : Int? = null // indice de la case sélectionnée
    private lateinit var thread: Thread
    private var gameOver = false
    private var roiMort=""
    private val activity = context as FragmentActivity
    private val backgroundPaint = Paint()
    init {
        backgroundPaint.color =Color.argb(255, 1,22,56)
    }

    fun kingDead(perdant:String){
        roiMort = perdant
        gameOver()
    }

    private fun gameOver() {
        if (roiMort=="black") {
            showGameOverDialog(R.string.win_blanc)
        }
        else if(roiMort=="white") {
            showGameOverDialog(R.string.win_noir)
        }
        gameOver = true
    }

    fun newGame() {
        board.clear()
        board.initialisation()
        board.cimetiere.reset()
        board.partie.resetTour()
        if (gameOver) {
            gameOver = false
            thread = Thread(this)
            thread.start()
        }
    }


    private fun showGameOverDialog(messageId: Int) {
        class GameResult: DialogFragment() {
            override fun onCreateDialog(bundle: Bundle?): Dialog {
                val builder = AlertDialog.Builder(activity)
                builder.setTitle(resources.getString(messageId))
                builder.setPositiveButton(R.string.reset_game
                ) { _, _ -> newGame() }
                return builder.create()
            }
        }

        activity.runOnUiThread {
            val ft = activity.supportFragmentManager.beginTransaction()
            val prev =
                activity.supportFragmentManager.findFragmentByTag("dialog")
            if (prev != null) {
                ft.remove(prev)
            }
            ft.addToBackStack(null)
            val gameResult = GameResult()
            gameResult.isCancelable = false
            gameResult.show(ft, "dialog")
        }
    }

    fun pause() {
        thread.join()
    }
    fun resume() {
        thread = Thread(this)
        thread.start()
    }

    override fun run() {
        draw()
    }

    override fun onSizeChanged(w:Int, h:Int, oldw:Int, oldh:Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        screenWidth = w.toFloat()
        screenHeight = h.toFloat()
        val side = min(screenHeight, screenWidth)*90/100
        board.left = (screenWidth-side)/2
        board.right= (screenWidth+side)/2
        board.top = (screenHeight-side)/2
        board.bottom = (screenHeight+side)/2
        board.initialisation()
    }


     private fun draw() {
        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()
            canvas.drawRect(0F,0F,canvas.width*1F,canvas.height*1F,backgroundPaint)
            board.draw(canvas)
            holder.unlockCanvasAndPost(canvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scorew.text = board.scorew.toString()
        scoreb.text = board.scoreb.toString()
        when (event!!.action){
            MotionEvent.ACTION_DOWN -> {
                val x = event.rawX
                val y = event.rawY - 100
                checkCase(x, y)
            }
        }
    return true
    }


    private fun checkCase(x:Float, y:Float){
        val cases = board.cases
        for (case in cases) {
            if (case.rectangle.contains(x,y)){
                    val col = case.col
                    val row = case.row

                    if ((board.checkTour() && case.getPiece()?.color == "white") or (!board.checkTour() && case.getPiece()?.color == "black")){
                        if (onfocus == null && case.getPiece() != null) {
                            onfocus = (row - 1) * 8 + col - 1
                            board.selection(onfocus!!, true)
                            draw()
                            return
                    }
                    }
                    if (onfocus != null) {
                        if (board.bouger(onfocus!!, (row - 1) * 8 + col - 1)) {
                            board.changeTour()
                        }
                        board.selection(onfocus!!, false)
                        onfocus = null
                    } else if (onfocus != null) {

                        board.selection(onfocus!!, false)
                        onfocus = null
                    }
                    draw()
                    break
            }
        }


    }
    fun getCimetiere(): MutableList<Piece> {
        return board.cimetiere.Pieces
    }
    fun getCimReset():Boolean{
        val res = board.cimetiere.res
        board.cimetiere.res = false
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

