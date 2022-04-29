package com.example.chessgame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import kotlin.math.abs


class Board(var boardHauteur: Float, var boardDebut: Float,  var width: Float, var boardFin: Float,var view: DrawingView, val context: Context) {
    var board = RectF(boardDebut, boardHauteur, boardDebut+width,boardFin)
    val cimetiere =Cimetiere(mutableListOf())
    var partie = Partie()

    fun setRect() {
        board.set(boardDebut, boardHauteur,
            boardDebut + width, boardFin)
    }
    var cases = mutableListOf<Case>()
    var paint = Paint()
    fun initialisation() { // crée les cases du board et les pièces
        if(cases.size < 64){
            var compteur = 0
            var dx = (width) / 8
            var dy = (boardFin - boardHauteur) / 8
            var x_i = boardDebut
            var y_i = boardHauteur
            for (i in 1..8) { //lignes
                for (j in 1..8) { //colonnes
                    var piece: Piece? = null
                    cases.add(Case(i, j, x_i, y_i, x_i + dx, y_i + dy, view, context))
                    // création pions blancs
                    if (i == 7) {
                        piece = Pion(cases[compteur], "white")
                    }
                    // création  pions noirs
                    else if (i == 2) {
                        piece = Pion(cases[compteur], "black")
                    }
                    // création tours blanches
                    else if (i ==8 && (j == 1 || j == 8) ){
                        piece = Tour(cases[compteur], "white")
                    }
                    //création tours noires
                    else if (i ==1 && (j == 1 || j == 8) ){
                        piece = Tour(cases[compteur], "black")
                    }
                    //création cavaliers blancs
                    else if (i ==8 && (j == 2 || j == 7) ){
                        piece = Cavalier(cases[compteur], "white")
                    }
                    //création cavaliers noirs
                    else if (i ==1 && (j == 2 || j == 7) ){
                        piece = Cavalier(cases[compteur], "black")
                    }
                    // création fous blancs
                    else if (i ==8 && (j == 3 || j == 6) ){
                        piece = Fou(cases[compteur], "white")
                    }
                    // création fous noirs
                    else if (i ==1 && (j == 3 || j == 6) ){
                        piece = Fou(cases[compteur], "black")
                    }
                    // création reine blanche
                    else if (i ==8 && j == 4  ){
                        piece = Reine(cases[compteur], "white")
                    }
                    // création reine noire
                    else if (i ==1 && j == 4  ){
                        piece = Reine(cases[compteur], "black")
                    }
                    // création roi blanc
                    else if (i ==8 && j == 5  ){
                        piece = Roi(cases[compteur], "white")
                    }
                    // création roi noir
                    else if (i ==1 && j == 5 ){
                        piece = Roi(cases[compteur], "black")
                    }
                    cases[compteur].piece = piece
                    compteur += 1
                    x_i += dx
                }
                y_i += dy
                x_i = boardDebut
            }
        }
    }

    fun draw(canvas: Canvas) {
        paint.color = Color.WHITE
        canvas.drawRect(board, paint)
        for (case in cases){
            case.setRect()
            case.draw(canvas)
        }
    }

    fun selection(caseRef:Int , colorier: Boolean){
        cases[caseRef].focus = colorier
    }
    fun bouger(from:Int, to:Int):Boolean{
        val moved = (cases[from].piece!!.bouger(cases[to], cases))
        if(cases[to].piece != null && moved){
            mourir(cases[to].piece)
            cases[from].piece!!.position = cases[to]
            cases[to].piece = cases[from].piece
            cases[from].piece = null}
        else if (moved){
            cases[from].piece!!.position = cases[to]
            cases[to].piece = cases[from].piece
            cases[from].piece = null
        }
        return moved
    }
    fun mourir(piece: Piece?){
        cimetiere.ajouterPiece(piece!!)
    }

    fun isDiagonalFree( from: Case,  to : Case): Boolean{
        if (abs(from.col - to.col) != abs(from.row - to.row)) return false // pas une diagonale
        val ecart = abs(from.col - to.col) - 1
        for (i in 1..ecart) {
            val nextCol = if (to.col > from.col) from.col + i else from.col - i
            val nextRow = if (to.row > from.row) from.row + i else from.row - i
            if (cases[(nextRow-1)*8 + nextCol].piece != null) {
                return false
            }
        }
        return true
    }
    fun isVerticalFree(from: Case, to: Case): Boolean{
        if (from.col != to.col) return false
        val ecart = abs(from.row - to.row) - 1
        if (ecart == 0 ) return true
        for (i in 1..ecart) {
            val nextRow = if (to.row > from.row) from.row + i else from.row - i
            if (cases[(nextRow-1)*8 + from.col].piece != null) {
                return false
            }
        }
        return true
    }
    fun isHorizontallyFree(from: Case, to: Case): Boolean {
        if (from.row != to.row) return false
        val ecart = abs(from.col - to.col) - 1
        if (ecart == 0 ) return true
        for (i in 1..ecart) {
            val nextCol = if (to.col > from.col) from.col + i else from.col - i
            if (cases[(from.row-1)*8 + nextCol].piece != null) {
                return false
            }
        }
        return true
    }

}
