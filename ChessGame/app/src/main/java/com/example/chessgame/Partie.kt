package com.example.chessgame

class Partie {


    var enCours: Boolean = true
    var tour: Boolean = true // true-> tour blancs , false -> tour aux noirs
    var pieceChoisie: Piece? = null

    fun changeTour(){
        if(tour) tour = false
        else tour = true
    }
    fun terminer(){
        enCours = false
    }
    fun selectionPiece(piece: Piece){
        pieceChoisie = piece
    }

}