package com.example.lpiem.pokecard.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecard.ViewDialog
import com.example.lpiem.pokecard.data.model.*
import com.example.lpiem.pokecard.data.repository.PokemonRepository
import com.example.lpiem.pokecard.retrofit.GestionRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel : ViewModel() {


    var state = MutableLiveData<AllPokemonState>()
    var selectedPokemon : ResultData? = null
    var selectedUserPokemon : UserPokemonResultData? = null
    var resultOnePokemonData = PokemonRepository.onePokemonLiveData



    init {
        PokemonRepository.state.observeForever {
            state.postValue(it)
        }
    }


    fun getAllPokemonLiveData(): MutableLiveData<List<ResultData>> {
        return PokemonRepository.fetchAllPokemon()

    }

    fun getOnePokemonLiveData(): MutableLiveData<ResultOnePokemonData> {
        return PokemonRepository.fetchOnePokemon(selectedPokemon)

    }

    fun getOnePokemonUserLiveData(): MutableLiveData<ResultOnePokemonData> {
        return PokemonRepository.fetchOneUserPokemon(selectedUserPokemon)

    }


    fun getAllUserPokemonLiveData(id:String): MutableLiveData<List<UserPokemonResultData>> {
        return PokemonRepository.fetchAllUserPokemon(id)

    }









}