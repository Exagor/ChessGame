package com.example.chessgame

import kotlin.math.abs

abstract class Piece(var position: Case, val color: String){
    abstract var image : Int
    abstract fun bouger(newPos: Case): Boolean
    abstract fun mourir()
    fun isDiagonalFree( from: Case,  to : Case, cases : List<Case>): Boolean{
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
    fun isVerticalFree(from: Case, to: Case, cases : List<Case>): Boolean{
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
    fun isHorizontallyFree(from: Case, to: Case,cases : List<Case>): Boolean {
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