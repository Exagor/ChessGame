package com.example.chessgame

class Pion(position: Case, color: String, id: Int): Piece(position, color, id) {
    override var image = if (color == "white") R.drawable.pawn_white else R.drawable.pawn_black
    override fun bouger(newPos:Case): Boolean {
        var fait = false
        if (color == "white"){
            // si se déplace en diagonale
            if (position.row + 1 == newPos.row && (position.col - 1 == newPos.col || position.col + 1 == newPos.col)){
                // appele manger si rectangle occupée par autre couleur
                if (newPos.piece != null && newPos.piece!!.color != color) {
                    position = newPos
                    fait = true
                    newPos.piece!!.mourir()
                }
            }
                // si se déplace tout droit vers une rectangle vide
            else if (position.col == newPos.col && newPos.piece != null) {
                    // peut avancer de deux cases si se trouve sur la deuxième ligne sinon avance de 1 ligne
                    if ((position.row == 2 && newPos.row == 4)||(position.row + 1 == newPos.row)) {
                        position = newPos
                        fait = true
                    }
                }
        }
        else {
            // si se déplace en diagonale
            if (position.row - 1 == newPos.row && (position.col - 1 == newPos.col || position.col + 1 == newPos.col)){
                // appele manger si rectangle occupée par autre couleur
                if (newPos.piece != null && newPos.piece!!.color != color) {
                    position = newPos
                    fait = true
                    newPos.piece!!.mourir()
                }
            }
            // si se déplace tout droit vers une rectangle vide
            else if (position.col == newPos.col && newPos.piece != null) {
                // peut avancer de deux cases si se trouve sur la deuxième ligne sinon avance de 1 ligne
                if ((position.row == 7 && newPos.row == 5)||(position.row - 1 == newPos.row)) {
                    position = newPos
                    fait = true
                }
            }
        }
        if (fait) position.focus = false
        return fait
    }

    override fun mourir() {
        TODO("Not yet implemented")
    }


}