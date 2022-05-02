package com.example.chessgame
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

import android.graphics.Paint
import kotlin.math.min


class DrawingView @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr), SurfaceHolder.Callback, Runnable {
    lateinit var canvas: Canvas
    //création du board
    val board = Board( 0f, 0f, 0f, 0f,this, context)
    var screenWidth = 0f
    var screenHeight = 0f
    var drawing = false
    var onfocus : Int? = null // indice de la case sélectionnée
    lateinit var thread: Thread
    var gameOver = false
    var roi_mort=""
    val activity = context as FragmentActivity
    val backgroundPaint = Paint()
    init {
        backgroundPaint.color =Color.argb(255, 1,22,56)
    }
    fun king_dead(perdant:String){
        roi_mort=perdant
        gameOver()
    }

    fun gameOver() {
        drawing = false
        if (roi_mort=="black") {
            showGameOverDialog(R.string.win_blanc)
        }
        else if(roi_mort=="white") {
            showGameOverDialog(R.string.win_noir)
        }
        gameOver = true
    }

    private fun newGame() {
        board.initialisation()
        draw()
        if (gameOver) {
            gameOver = false
            thread = Thread(this)
            thread.start()
        }
    }

    fun showGameOverDialog(messageId: Int) {
        class GameResult: DialogFragment() {
            override fun onCreateDialog(bundle: Bundle?): Dialog {
                val builder = AlertDialog.Builder(activity)
                builder.setTitle(resources.getString(messageId))
                builder.setPositiveButton(R.string.reset_game,
                    DialogInterface.OnClickListener { _, _->newGame()}
                )
                return builder.create()
            }
        }

        activity.runOnUiThread(
            Runnable {
                val ft = activity.supportFragmentManager.beginTransaction()
                val prev =
                    activity.supportFragmentManager.findFragmentByTag("dialog")
                if (prev != null) {
                    ft.remove(prev)
                }
                ft.addToBackStack(null)
                val gameResult = GameResult()
                gameResult.isCancelable = false
                gameResult.show(ft,"dialog")
            }
        )
    }


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
        while (drawing) {

        }
    }

    override fun onSizeChanged(w:Int, h:Int, oldw:Int, oldh:Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        screenWidth = w.toFloat()
        screenHeight = h.toFloat()
        val s = min(screenHeight,screenWidth)*8/9
        board.right = (w+s)/2
        board.left = (w-s)/2
        board.top= (h- s)/2
        board.bottom = ((h+s)/2)
        board.setRect()
        draw()
        newGame()
    }

     fun draw() {
        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()
            canvas.drawRect(0F,0F,canvas.width*1F,canvas.height*1F,backgroundPaint)
            board.draw(canvas)
            holder.unlockCanvasAndPost(canvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action){
            MotionEvent.ACTION_DOWN -> {
                val x = event.rawX- 30
                val y = event.rawY -30
                checkCase(x, y)
            }
        }
    return true
    }

    fun checkCase(x:Float, y:Float){
        val cases = board.cases
        for (case in cases) {
            if (case.rectangle.contains(x,y)){
                    val col = case.col
                    val row = case.row
                    println("col: $col row: $row")
                    if ((board.checkTour() && case.piece?.color == "white") or (!board.checkTour() && case.piece?.color == "black")){
                        if (onfocus == null && case.piece != null) {
                            onfocus = (row - 1) * 8 + col - 1
                            board.selection(onfocus!!, true)
                    }
                    }
                    else if (onfocus != null) {
                        if (board.bouger(onfocus!!, (row - 1) * 8 + col - 1)) {
                            board.ChangeTour()
                        }
                        println("onfocus!=null")
                        board.selection(onfocus!!, false)
                        onfocus = null
                    } else if (onfocus != null) {
                        board.selection(onfocus!!, false)
                        onfocus = null
                    }
                    draw()
                    return
            }
        }


    }
    fun getCimetiere(): MutableList<Piece> {
        return board.cimetiere.Pieces
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int,
                                width: Int, height: Int) {}

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread = Thread(this)
        thread.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {}

}

