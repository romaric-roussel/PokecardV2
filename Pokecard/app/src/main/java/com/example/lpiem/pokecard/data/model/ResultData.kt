package com.example.lpiem.pokecard.data.model

//add image
data class ResultData (val id: String, val name : String, val image :String) {
    override fun toString(): String {
        return "ResultData(id='$id', image='$name', url='$image')"
    }
}


