package com.example.lpiem.pokecard.data.model

data class UserExchangePokemon(val id_list: Int,val id_pokemon_utilisateur_1: Int,
                               val image_pokemon_utilisateur_1: String,
                               val name_pokemon_utilisateur_1: String,
                               val id_pokemon_utilisateur_2: Int,
                               val image_pokemon_utilisateur_2: String,
                               val name_pokemon_utilisateur_2: String){


    override fun toString(): String {
        return "UserOneAmi(val id_list='$id_list'," +
                "id_pokemon_utilisateur_1='$id_pokemon_utilisateur_1'," +
                " image_pokemon_utilisateur_1='$image_pokemon_utilisateur_1'," +
                " name_pokemon_utilisateur_1='$name_pokemon_utilisateur_1'," +
                "id_pokemon_utilisateur_2='$id_pokemon_utilisateur_2', " +
                "image_pokemon_utilisateur_2='$image_pokemon_utilisateur_2'," +
                "name_pokemon_utilisateur_2='$name_pokemon_utilisateur_2')"
    }


}
