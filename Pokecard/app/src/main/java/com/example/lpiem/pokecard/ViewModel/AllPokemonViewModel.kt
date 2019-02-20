package com.example.lpiem.pokecard.ViewModel

import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lpiem.pokecard.Model.Pokemon
import com.example.lpiem.pokecard.Model.retrofit.AllResult
import com.example.lpiem.pokecard.Model.retrofit.ResultData
import com.example.lpiem.pokecard.ViewDialog
import com.example.lpiem.pokecard.activity.MainActivity
import com.example.lpiem.pokecard.fragment.FragmentAllPokemon
import com.example.lpiem.pokecard.retrofit.GestionRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllPokemonViewModel : ViewModel() {

    var allPokemonLiveData = MutableLiveData<List<ResultData>>()

    var selectedPokemon: ResultData? = null

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
}