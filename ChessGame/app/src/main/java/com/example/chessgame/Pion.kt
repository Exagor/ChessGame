package com.example.chessgame

class Pion(position: Case, color: String): Piece(position, color) {
    override var image = if (color == "white") R.drawable.pawn_white else R.drawable.pawn_black
    override fun bouger(newPos:Case, cases: MutableList<Case>): Boolean {
        // se déplacer tout droit
        if (position.col == newPos.col) {
            //part de sa position de déprt
            if (position.row == 1 && color == "black") {
                println("")
                return newPos.row == 2 || newPos.row == 3

            } else if (position.row == 6 && color == "white") {
                return newPos.row == 5 || newPos.row == 4
            } //part d'une case quelconque
            else return (color == "black" && newPos.row > position.row )|| (color == "white" && newPos.row < position.row )
        }
        //se déplace en diagonale
        else if ((position.col == newPos.col +1 || position.col ==newPos.col -1) && newPos.piece!!.color != color){
          return (color == "black" && newPos.row > position.row )|| (color == "white" && newPos.row < position.row )}
       return false
    }

    override fun mourir() {
        TODO("Not yet implemented")
    }



}