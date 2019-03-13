package com.example.lpiem.pokecard.retrofit.API

import com.example.lpiem.pokecard.data.model.AllResult
import com.example.lpiem.pokecard.data.model.OneResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface API {

    @GET("pokemon")
    fun getListPokemon() : Call<AllResult>

    @GET("pokemon/{id}")
    fun getOnePokemon(@Path("id") id: String) : Call<OneResult>



}