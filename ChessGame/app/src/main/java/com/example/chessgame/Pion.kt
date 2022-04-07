package com.example.chessgame

class Pion(position: Pair<Int,Int>, color: String, moves: MutableList<Pair<Int,Int>>): Piece(position, color, moves) {

    override fun calculateMoves(){
        // se déplacer
        // peut avancer de deux cases si se trouve sur la deuxième ligne
        if (position.first == 2 ) { //&&case vide//
            possibleMoves.add(Pair(position.first + 2, position.second))
        }

    }

    override fun etreMange() {
        TODO("Not yet implemented")
    }
}