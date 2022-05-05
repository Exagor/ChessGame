package com.example.chessgame

import kotlin.reflect.typeOf

interface Roque {

     abstract fun RoqueValide(caseRoi:Int, caseTour:Int, cases:MutableList<Case>): Boolean

}