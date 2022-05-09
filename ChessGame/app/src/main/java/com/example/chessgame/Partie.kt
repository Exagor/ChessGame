package com.example.chessgame

class Partie {
    var tour: Boolean = true // true-> tour blancs , false -> tour aux noirs

    fun changeTour(){
        tour = !tour
    }
    fun resetTour(){
        tour = true
    }
}