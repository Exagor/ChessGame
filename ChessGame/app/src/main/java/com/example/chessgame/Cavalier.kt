package com.example.chessgame

import kotlin.math.abs

class Cavalier(position: Case, color: String): Piece(position, color) {
    override fun bouger(newPos: Case){
        if (abs(newPos.col - position.col)== 2 && abs(newPos.row - position.row) == 1
            ||abs(newPos.col - position.col)== 1 && abs(newPos.row - position.row) == 2 ){
            if (newPos.piece == null)
                position = newPos
            else if (newPos.piece!!.color != color)
                position = newPos
                newPos.piece!!.mourir()
        }
    }

    override fun mourir() {
        TODO("Not yet implemented")
    }


}