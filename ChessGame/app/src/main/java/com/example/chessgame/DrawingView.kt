package com.example.chessgame
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Canvas
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

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
    var roi_mort= ""
    val activity = context as FragmentActivity


    fun king_dead(perdant:String){
        roi_mort=perdant
        gameOver()
    }

    fun gameOver() {
        drawing = false
        if (roi_mort=="black") {
            //showGameOverDialog(R.string.win_blanc)
        }
        else if(roi_mort=="white") {
            //showGameOverDialog(R.string.win_noir)
        }
        gameOver = true
    }

    fun newGame() {
        /*board.resetBoard()
        piece.resetPiece()
        cimetiere.resetCimetiere()*/
        drawing = true
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
                val x = event.rawX- 30
                val y = event.rawY - 20
                checkCase(x, y)
            }
        }
    return true
    }

    fun checkCase(x:Float, y:Float){
        val cases = board.cases
        for (case in cases) {
            if (case.rectangle.contains(x,y)){
                // Mettre une condition pour si la case contient une pièce de couleur différente
                val col = case.col
                val row = case.row
                println("col: $col row: $row")
                if(onfocus == null && case.piece != null) {
                    onfocus = (row - 1)* 8 + col -1
                    board.selection(onfocus!!,true )
                    return
                }
                 if (onfocus != null ){
                     if (board.bouger(onfocus!!, (row - 1)* 8 + col -1)){
                        println("a bougé")
                     }
                     println("onfocus!=null")
                     board.selection(onfocus!!,false )
                     onfocus = null
                }
                else if (onfocus != null){
                     println("dans else")
                     board.selection(onfocus!!,false )
                     onfocus = null
                 }
                break

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
