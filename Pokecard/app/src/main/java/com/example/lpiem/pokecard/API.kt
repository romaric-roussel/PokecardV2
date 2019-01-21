package com.example.lpiem.pokecard

import com.example.lpiem.pokecard.Model.AllResult
import retrofit2.Call
import retrofit2.http.GET


interface API {

    @GET("pokemon")
    fun getListPokemon() : Call<AllResult>



}