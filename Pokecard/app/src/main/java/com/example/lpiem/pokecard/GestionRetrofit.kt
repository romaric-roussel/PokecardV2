package com.example.lpiem.pokecard

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class GestionRetrofit {

    companion object {
        fun initRetrofit() : API {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl(C.url)
                    .build()

            return retrofit.create(API::class.java)
        }
    }
}