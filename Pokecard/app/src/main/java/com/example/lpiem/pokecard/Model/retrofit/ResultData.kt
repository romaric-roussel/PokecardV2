package com.example.lpiem.pokecard.Model.retrofit

data class ResultData (val id: String, val nom : String, val url :String) {
    override fun toString(): String {
        return "$id  $nom"
    }
}


