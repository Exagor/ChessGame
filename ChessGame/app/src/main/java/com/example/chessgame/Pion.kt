package com.example.chessgame

class Pion(position: Case, color: String): Piece(position, color) {
    override var image = if (color == "white") R.drawable.pawn_white else R.drawable.pawn_black
    override fun bouger(newPos:Case, cases: MutableList<Case>): Boolean {
        // se déplacer tout droit
        if (position.col == newPos.col && newPos.piece == null ) {
            //part de sa position de déprt
            if (position.row == 2 && color == "black") {
                println("position.row == 1 && color == \"black\"")
                return newPos.row == 3 || newPos.row == 4

            } else if (position.row == 7 && color == "white") {
                println("position.row == 7 && color == \"white\"")
                return newPos.row == 6 || newPos.row == 5
            } //part d'une case quelconque
            else return (color == "black" && newPos.row  == position.row +1)|| (color == "white" && newPos.row ==  position.row -1 )
        }
        //se déplace en diagonale
        else if ((position.col == newPos.col +1 || position.col ==newPos.col -1) && newPos.piece!!.color != color){
          return (color == "black" && newPos.row > position.row )|| (color == "white" && newPos.row < position.row )}
       return false
    }





}