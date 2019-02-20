package com.example.lpiem.pokecard.ViewModel

import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecard.Model.retrofit.AllResult
import com.example.lpiem.pokecard.Model.retrofit.OneResult
import com.example.lpiem.pokecard.Model.retrofit.ResultData
import com.example.lpiem.pokecard.Model.retrofit.ResultOnePokemonData
import com.example.lpiem.pokecard.ViewDialog
import com.example.lpiem.pokecard.activity.MainActivity
import com.example.lpiem.pokecard.fragment.FragmentAllPokemon
import com.example.lpiem.pokecard.retrofit.GestionRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel : ViewModel() {

    var allPokemonLiveData = MutableLiveData<List<ResultData>>()

    //var pokemonLiveData = MutableLiveData<ResultOnePokemonData>()

    var selectedPokemon: ResultData? = null

    var pokemon: ResultOnePokemonData? = null

    val apiPokemon = GestionRetrofit.initRetrofit()

    var viewDialog =  ViewDialog()




    fun showLoadingProgress(context: Context){

        viewDialog.ViewDialog(context)
        viewDialog.showDialog()
    }

    fun hideLoadingProgress(){
        viewDialog.hideDialog()
    }



    fun fetchAllPokemon(context: Context) {

        val call = apiPokemon.getListPokemon()
        showLoadingProgress(context)

        //Asynchrone
        call.enqueue(object : Callback<AllResult> {
            override fun onFailure(call: Call<AllResult>, t: Throwable) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                hideLoadingProgress()

            }

            override fun onResponse(call: Call<AllResult>, response: Response<AllResult>) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                hideLoadingProgress()

                if (response.isSuccessful) {
                    allPokemonLiveData.postValue(response.body()?.result?.data)

                } else {
                    Log.d("Fail","PETIT FAIL MA GOL")
                }
            }
        })
    }

    fun fetchOnePokemon(context: Context) : LiveData<OneResult?> {

        val call = apiPokemon.getOnePokemon(selectedPokemon!!.id)
        showLoadingProgress(context)

        val oneResult = MutableLiveData<OneResult?>()

        //Asynchrone
        call.enqueue(object : Callback<OneResult> {
            override fun onFailure(call: Call<OneResult>, t: Throwable) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                hideLoadingProgress()
                oneResult.postValue(null)

            }

            override fun onResponse(call: Call<OneResult>, response: Response<OneResult>) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                hideLoadingProgress()

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
    }
}