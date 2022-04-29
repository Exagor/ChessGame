package com.example.chessgame

import kotlin.math.abs

class Cavalier(position: Case, color: String): Piece(position, color) {
    override var image = if (color == "white") R.drawable.knight_white else R.drawable.knight_black

    override fun bouger(newPos: Case, cases: MutableList<Case>): Boolean {
        var fait = false
        if (abs(newPos.col - position.col)== 2 && abs(newPos.row - position.row) == 1
            ||abs(newPos.col - position.col)== 1 && abs(newPos.row - position.row) == 2 ){
            if (newPos.piece == null ||newPos.piece!!.color != color) {
                return  true
            }
        }
        return false
    }




}