package com.example.chessgame

class Tour(position: Case, color: String): Piece(position, color) {
    override var image = if (color == "white") R.drawable.rook_white else R.drawable.rook_black
    override fun bouger(newPos: Case, cases : MutableList<Case>): Boolean{
        var fait = false
        return fait
    }

    override fun mourir() {
        TODO("Not yet implemented")
    }


}