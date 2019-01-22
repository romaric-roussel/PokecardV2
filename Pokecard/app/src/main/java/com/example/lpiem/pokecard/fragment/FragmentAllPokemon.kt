package com.example.lpiem.pokecard.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.MainActivity
import com.example.lpiem.pokecard.Model.AllResult
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.adapter.AllPokemonListeAdapter
import com.example.lpiem.pokecard.retrofit.GestionRetrofit
import com.facebook.all.All
import kotlinx.android.synthetic.main.fragment_all_pokemon.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList


class FragmentAllPokemon : Fragment() {

    val api by lazy {
        GestionRetrofit.initRetrofit()
    }
    lateinit var rvAllPokemon: RecyclerView
    lateinit var adapter: AllPokemonListeAdapter
    var listeAllPokemon: ArrayList<String> = ArrayList()

    interface GetAllPokemonCallback {
        fun onGetPokemon(resultAllPlants: AllResult?)
        fun onError()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_all_pokemon, container, false)
        rvAllPokemon = view.findViewById(R.id.rv_pokemon_fragment)
        rvAllPokemon.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL, false)
        adapter = AllPokemonListeAdapter(listeAllPokemon)
        rvAllPokemon.adapter = adapter
        //adapter.notifyDataSetChanged()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setUpRecyclerView(view)
        super.onViewCreated(view, savedInstanceState)

    }



    private fun getAllPokemon(getAllPokemonCallback: GetAllPokemonCallback) {


        api.getListPokemon().enqueue(object : Callback<AllResult> {
            override fun onResponse(call: Call<AllResult>, response: Response<AllResult>) {
                if (response.isSuccessful) {
                    getAllPokemonCallback.onGetPokemon(response.body())
                } else {
                    getAllPokemonCallback.onError()
                }


            }

            override fun onFailure(call: Call<AllResult>, t: Throwable) {
                error("KO")
            }
        })
    }
    private fun setUpRecyclerView(view: View)
    {


        getAllPokemon(object : GetAllPokemonCallback {
            override fun onGetPokemon(resultAllPlants: AllResult?) {
                var size = resultAllPlants?.result?.data!!.size
                for (i in 0..size - 1) {
                    listeAllPokemon.add(resultAllPlants.result.data[i].toString())

                    //Log.d("POKEMON", allResult.result.data[i].toString())
                }

                adapter.notifyDataSetChanged()
            }

            override fun onError() {

            }
        })
    }


    companion object {
        fun newInstance(): FragmentAllPokemon = FragmentAllPokemon()
    }

    override fun onResume() {
        //adapter.notifyDataSetChanged()
        super.onResume()
    }
}