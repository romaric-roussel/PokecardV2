package com.example.lpiem.pokecard.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lpiem.pokecard.data.model.AllPokemonState
import com.example.lpiem.pokecard.data.model.AllResult
import com.example.lpiem.pokecard.data.model.OneResult
import com.example.lpiem.pokecard.data.model.ResultData
import com.example.lpiem.pokecard.retrofit.GestionRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PokemonRepository {

    private var allPokemonLiveData = MutableLiveData<List<ResultData>>()
    private val apiPokemon = GestionRetrofit.initRetrofit()

    fun fetchAllPokemon() : MutableLiveData<List<ResultData>> {

        val call = apiPokemon.getListPokemon()
        //showLoadingProgress(context)
        AllPokemonState().isProgressLoadingShown = true

        //Asynchrone
        call.enqueue(object : Callback<AllResult> {
            override fun onFailure(call: Call<AllResult>, t: Throwable) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                AllPokemonState().isProgressLoadingShown = false
            }

            override fun onResponse(call: Call<AllResult>, response: Response<AllResult>) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                AllPokemonState().isProgressLoadingShown = false

                if (response.isSuccessful) {
                    allPokemonLiveData.postValue(response.body()?.result?.data)

                } else {

                }
            }
        })
        return allPokemonLiveData
    }

   /* fun fetchOnePokemon(context: Context) : LiveData<OneResult?> {

        //val call = apiPokemon.getOnePokemon(selectedPokemon!!.id)
        //showLoadingProgress(context)

        val oneResult = MutableLiveData<OneResult?>()

        //Asynchrone
        call.enqueue(object : Callback<OneResult> {
            override fun onFailure(call: Call<OneResult>, t: Throwable) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                //hideLoadingProgress()
                oneResult.postValue(null)

            }

            override fun onResponse(call: Call<OneResult>, response: Response<OneResult>) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                //hideLoadingProgress()

                if (response.isSuccessful) {
                    //pokemon = response.body()?.result
                    //pokemonLiveData = response.body()?.result
                    //pokemonLiveData.postValue(response.body()?.result)
                    //pokemonLiveData.value = response.body()?.result
                    oneResult.postValue(response.body())

                } else {
                    Log.d("Fail","PETIT FAIL MA GOL")
                    oneResult.postValue(null)
                }
            }
        })

        return oneResult
    }*/




}