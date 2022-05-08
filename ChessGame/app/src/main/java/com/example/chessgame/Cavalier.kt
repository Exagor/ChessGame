package com.example.chessgame

import kotlin.math.abs

class Cavalier(position: Case, color: String): Piece(position, color) {
    override var image = if (color == "white") R.drawable.w_knight_png_512px else R.drawable.b_knight_png_512px

    override fun bouger(newPos: Case, cases: MutableList<Case>): Boolean {
        if (abs(newPos.col - position.col)== 2 && abs(newPos.row - position.row) == 1
            ||abs(newPos.col - position.col)== 1 && abs(newPos.row - position.row) == 2 ){
            if (newPos.getPiece() == null ||newPos.getPiece()!!.color != color) {
                position= newPos
                return  true
            }
        }
        return false
    }
    override var value = 3




}