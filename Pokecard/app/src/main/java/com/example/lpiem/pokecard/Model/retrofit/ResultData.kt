package com.example.lpiem.pokecard.Model.retrofit

//add image
data class ResultData (val id: String, val nom : String, val image :String) {
    override fun toString(): String {
        return "ResultData(id='$id', image='$nom', url='$image')"
    }
}


