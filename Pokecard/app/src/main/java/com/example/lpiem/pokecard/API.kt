package com.example.lpiem.pokecard

import retrofit2.Call
import retrofit2.http.GET


interface API {

    @GET("pokemon")
    fun getListPokemon() : Call<AllResult>



}