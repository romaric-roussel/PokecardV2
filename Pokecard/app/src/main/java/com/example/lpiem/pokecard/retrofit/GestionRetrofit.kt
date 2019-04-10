package com.example.lpiem.pokecard.retrofit

import com.example.lpiem.pokecard.retrofit.API.API
import com.example.lpiem.pokecard.retrofit.API.C
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import javax.xml.datatype.DatatypeConstants.SECONDS
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


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
                    .client(okHttpClient)
                    .build()

            return retrofit.create(API::class.java)
        }

        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
    }
}