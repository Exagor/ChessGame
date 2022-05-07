package com.example.chessgame

class Pion(position: Case, color: String): Piece(position, color) {
    override var image = if (color == "white") R.drawable.w_pawn_png_512px else R.drawable.b_pawn_png_512px
    override fun bouger(newPos:Case, cases: MutableList<Case>): Boolean {
        // se déplacer tout droit
        if (position.col == newPos.col && newPos.piece == null ) {
            //part de sa position de déprt
            if (position.row == 2 && color == "black" &&(newPos.row == 3 || newPos.row == 4)) {
                position = newPos
                return true

            } else if (position.row == 7 && color == "white" &&(newPos.row == 6 || newPos.row == 5)) {
                position = newPos
                return true
            } //part d'une case quelconque
            else if((color == "black" && newPos.row  == position.row +1)|| (color == "white" && newPos.row ==  position.row -1)){
                position = newPos
                return true
            }
        }
        //se déplace en diagonale
        else if (newPos.piece!= null &&(position.col == newPos.col +1 || position.col == newPos.col -1) && newPos.piece!!.color != color &&
            ((color == "black" && newPos.row == position.row +1)|| (color == "white" && newPos.row +1 == position.row ))){
            position= newPos
            return true
            }
       return false
    }
    override var value = 1





}