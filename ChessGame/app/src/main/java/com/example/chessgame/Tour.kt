package com.example.chessgame

import kotlin.math.abs

class Tour(position: Case, color: String): Piece(position, color), Roque {
    override var image = if (color == "white") R.drawable.w_rook_png_512px else R.drawable.b_rook_png_512px
    override fun bouger(newPos: Case, cases: MutableList<Case>): Boolean {
        if(newPos.piece == null || newPos.piece!!.color != color ){
            if (abs(newPos.row - position.row) == 0 && isHorizontalFree(position, newPos, cases)) {
                if (newPos.piece == null||newPos.piece!!.color != color) {
                    position = newPos
                    return true
                }
            } else if (abs(newPos.col - position.col) == 0 && isVerticalFree(position, newPos, cases)) {
                if (newPos.piece == null || newPos.piece!!.color != color) {
                    position = newPos
                    return true
                }
            }
        }
        else{
            if(RoqueValide((newPos.row-1)*8 +newPos.col -1 ,(position.row-1)*8 +position.col-1, cases)){
            return true}
        }
        return false
    }
    override var value = 5
    override fun RoqueValide(caseRoi: Int, caseTour: Int, cases: MutableList<Case>): Boolean {
        if (cases[caseTour].col == 1) {
            position.col += 3
            return true
        }
        if (cases[caseTour].col == 8) {
            position.col -= 2
            return true
        }
         return false

    }

}

