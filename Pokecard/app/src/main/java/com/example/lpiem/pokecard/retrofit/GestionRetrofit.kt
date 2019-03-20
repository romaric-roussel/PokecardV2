package com.example.lpiem.pokecard.retrofit

import com.example.lpiem.pokecard.retrofit.API.API
import com.example.lpiem.pokecard.retrofit.API.C
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class GestionRetrofit {

    companion object {
        fun initRetrofit() : API {

            val gson =  GsonBuilder()
                    .setLenient()
                    .create()

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(
                            GsonConverterFactory.create(gson))
                    .baseUrl(C.url)
                    .build()

            return retrofit.create(API::class.java)
        }
    }
}