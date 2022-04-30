package com.example.chessgame

import kotlin.math.abs

class Tour(position: Case, color: String): Piece(position, color) {
    override var image = if (color == "white") R.drawable.w_rook_png_512px else R.drawable.b_rook_png_512px
    override fun bouger(newPos: Case, cases: MutableList<Case>): Boolean {
        if (abs(newPos.row - position.row) == 0 && isHorizontalFree(position, newPos, cases)) {
            println("horizontal")
            if (newPos.piece == null||newPos.piece!!.color != color) {
                return true
            }
        } else if (abs(newPos.col - position.col) == 0 && isVerticalFree(position, newPos, cases)) {
            println("vertical")
            if (newPos.piece == null || newPos.piece!!.color != color) {
                return true
            }
        }
        return false
    }


}

