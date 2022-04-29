package com.example.chessgame

import kotlin.math.abs

class Tour(position: Case, color: String): Piece(position, color) {
    override var image = if (color == "white") R.drawable.rook_white else R.drawable.rook_black
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

