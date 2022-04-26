package com.example.chessgame

class Roi(position: Case, color: String, id: Int): Piece(position, color, id) {
    var valeur = 100
    override var image = if (color == "white") R.drawable.king_white else R.drawable.king_black
    override fun bouger(newPos: Case): Boolean{
        var fait = false
        return fait
    }

    override fun mourir() {
        TODO("Not yet implemented")
    }


}