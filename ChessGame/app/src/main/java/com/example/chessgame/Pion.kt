package com.example.chessgame

class Pion(position: Case, color: String): Piece(position, color) {

    override fun bouger(newPos:Case){
        if (color == "white"){
            // si se déplace en diagonale
            if (position.row + 1 == newPos.row && (position.col - 1 == newPos.col || position.col + 1 == newPos.col)){
                // appele manger si case occupée par autre couleur
                if (newPos.piece != null && newPos.piece!!.color != color) {
                    position = newPos
                    newPos.piece!!.mourir()
                }
            }
                // si se déplace tout droit vers une case vide
            else if (position.col == newPos.col && newPos.piece != null) {
                    // peut avancer de deux cases si se trouve sur la deuxième ligne sinon avance de 1 ligne
                    if ((position.row == 2 && newPos.row == 4)||(position.row + 1 == newPos.row)) position = newPos
                }
        }
        else {
            // si se déplace en diagonale
            if (position.row - 1 == newPos.row && (position.col - 1 == newPos.col || position.col + 1 == newPos.col)){
                // appele manger si case occupée par autre couleur
                if (newPos.piece != null && newPos.piece!!.color != color) {
                    position = newPos
                    newPos.piece!!.mourir()
                }
            }
            // si se déplace tout droit vers une case vide
            else if (position.col == newPos.col && newPos.piece != null) {
                // peut avancer de deux cases si se trouve sur la deuxième ligne sinon avance de 1 ligne
                if ((position.row == 7 && newPos.row == 5)||(position.row - 1 == newPos.row)) position = newPos
            }
        }
    }

    override fun mourir() {
        TODO("Not yet implemented")
    }


}