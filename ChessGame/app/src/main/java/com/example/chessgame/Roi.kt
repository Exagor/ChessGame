package com.example.chessgame

import kotlin.math.abs

class Roi(position: Case, color: String): Piece(position, color), Roque {
    override var value = 0
    override var image = if (color == "white") R.drawable.w_king_png_512px else R.drawable.b_king_png_512px
    override fun bouger(newPos: Case,cases : MutableList<Case>): Boolean{
        // peut se déplacer
        if(newPos.getPiece() == null || newPos.getPiece()!!.color != color){
            val deltaCol = abs(position.col - newPos.col)
            val deltaRow = abs(position.row - newPos.row)
            if( deltaCol == 1 && deltaRow == 1 || deltaCol + deltaRow == 1){
                position = newPos
                return true
            }
        }
        else if (newPos.getPiece() != null && newPos.getPiece()!!.color == color){
            if(RoqueValide((position.row-1)*8 +position.col -1,(newPos.row-1)*8 +newPos.col -1, cases)){
                return true
            }

        }
        return false
    }

    override fun RoqueValide(caseRoi: Int, caseTour: Int, cases: MutableList<Case>): Boolean {
            if (cases[caseTour].getPiece() !is Tour) return false
            if (cases[caseTour].getPiece()!!.color != cases[caseRoi].getPiece()!!.color) return false
            val from = if(caseRoi< caseTour)  caseRoi else caseTour
            val to = if( from == caseRoi) caseTour else caseRoi
            for (i in (from+1)..(to-1)){
                if (cases[i].getPiece() != null){
                    return false
                }
            }
        if (cases[caseTour].col == 1) {
            position.col -=2
            return true
        }
        if (cases[caseTour].col == 8) {
            position.col +=2
            return true
        }
        return false
    }





}
