package com.example.chessgame

import kotlin.math.abs

class Fou(position: Case, color: String): Piece(position, color) {
    override var image = if (color == "white") R.drawable.w_bishop_png_512px else R.drawable.b_bishop_png_512px
    override fun bouger(newPos: Case, cases: MutableList<Case>): Boolean {
        if (abs(newPos.row - position.row) == abs(newPos.col - position.col) && isDiagonalFree(position, newPos, cases)) {
            if (newPos.getPiece() == null || newPos.getPiece()!!.color != color) {
                position = newPos
                return true
            }
        }
        return false
    }
    override var value = 3

    }
