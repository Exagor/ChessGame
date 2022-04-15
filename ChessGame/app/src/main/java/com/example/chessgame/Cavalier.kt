package com.example.chessgame

import kotlin.math.abs

class Cavalier(position: Case, color: String): Piece(position, color) {
    override fun bouger(newPos: Case): Boolean {
        var fait = false
        if (abs(newPos.col - position.col)== 2 && abs(newPos.row - position.row) == 1
            ||abs(newPos.col - position.col)== 1 && abs(newPos.row - position.row) == 2 ){
            if (newPos.piece == null) {
                position = newPos
                fait = true
            }
            else if (newPos.piece!!.color != color) {
                position = newPos
                fait = true
                newPos.piece!!.mourir()
            }
        }
        return fait
    }

    override fun mourir() {
        TODO("Not yet implemented")
    }


}