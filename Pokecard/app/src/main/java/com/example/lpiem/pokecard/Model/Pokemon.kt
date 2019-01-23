package com.example.lpiem.pokecard.Model



data class Pokemon(val id_pokemon:String,val nom:String,val type_1:String,val type_2:String,val url:String) {

    override fun toString(): String {
        return "Pokemon(id_pokemon='$id_pokemon', nom='$nom', type_1='$type_1', type_2='$type_2', url='$url')"
    }
}

