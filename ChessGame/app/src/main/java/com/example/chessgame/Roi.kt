package com.example.chessgame

class Roi(position: Case, color: String): Piece(position, color) {
    var valeur = 100
    override var image = if (color == "white") R.drawable.king_white else R.drawable.king_black
    override fun bouger(newPos: Case,cases : List<Case>): Boolean{
        var fait = false
        return fait
    }

    override fun mourir() {
        TODO("Not yet implemented")
    }


}
