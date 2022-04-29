package com.example.chessgame

import kotlin.math.abs

class Tour(position: Case, color: String): Piece(position, color) {
    override var image = if (color == "white") R.drawable.rook_white else R.drawable.rook_black
    override fun bouger(newPos: Case, cases : List<Case>): Boolean{
         if (abs(newPos.row - position.row) == 0 && isHorizontalFree(position,newPos,cases)){
             if (newPos.piece == null) {
                 return true
             }
             else if (newPos.piece!!.color != color) {
                 newPos.piece!!.mourir()
             }
         }
        else if (abs(newPos.col - position.col) == 0 && isVerticalFree(position,newPos,cases)){
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

