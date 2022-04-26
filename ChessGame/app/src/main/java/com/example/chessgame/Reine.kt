package com.example.chessgame

class Reine(position: Case, color: String, id: Int): Piece(position, color, id) {
    override var image = if (color == "white") R.drawable.queen_white else R.drawable.queen_black
    override fun bouger(newPos: Case): Boolean{
        var fait = false
        return fait
    }

    override fun mourir() {
        TODO("Not yet implemented")
    }


}