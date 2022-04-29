package com.example.chessgame

import kotlin.math.abs

class Fou(position: Case, color: String): Piece(position, color) {
    override var image = if (color == "white") R.drawable.bishop_white else R.drawable.bishop_black
    override fun bouger(newPos: Case, cases: List<Case>): Boolean {
        if (abs(newPos.row - position.row) == abs(newPos.col - position.col) && isDiagonalFree(position, newPos, cases)) {
            if (newPos.piece == null) {
                return true
            }
            else if (newPos.piece!!.color != color) {
                newPos.piece!!.mourir()
            }

        }
        return false
    }
    override fun mourir() {
        TODO("Not yet implemented")
    }
    }
