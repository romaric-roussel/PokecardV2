package com.example.lpiem.pokecard.retrofit.API

import com.example.lpiem.pokecard.Model.retrofit.AllResult
import retrofit2.Call
import retrofit2.http.GET


interface API {

    @GET("pokemon")
    fun getListPokemon() : Call<AllResult>



}