package com.example.chessgame

import kotlin.math.abs

class Reine(position: Case, color: String): Piece(position, color) {
    override var image = if (color == "white") R.drawable.w_queen_png_512px else R.drawable.b_queen_png_512px
    override fun bouger(newPos: Case, cases: MutableList<Case>): Boolean {
        if (abs(newPos.row - position.row) == abs(newPos.col - position.col) && isDiagonalFree(position, newPos, cases)) {
            if (newPos.piece == null||newPos.piece!!.color != color) {
                return true
            }
        }
        else if((abs(newPos.row - position.row) == 0 && isHorizontalFree(position,newPos,cases)) or (abs(newPos.col - position.col) == 0 && isVerticalFree(position,newPos,cases)) ){
            if (newPos.piece == null||newPos.piece!!.color != color) {
                return true
            }
        }
        return false
    }
    override var value = 9



}
