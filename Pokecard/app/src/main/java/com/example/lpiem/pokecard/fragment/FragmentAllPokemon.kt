package com.example.lpiem.pokecard.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.MainActivity
import com.example.lpiem.pokecard.Model.Pokemon
import com.example.lpiem.pokecard.Model.retrofit.AllResult
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.RecyclerTouchListener
import com.example.lpiem.pokecard.adapter.AllPokemonListeAdapter
import com.example.lpiem.pokecard.retrofit.GestionRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class FragmentAllPokemon : Fragment() {

    val api by lazy {
        GestionRetrofit.initRetrofit()
    }

    lateinit var mainActivity: MainActivity
    lateinit var fragmentAllPokemonDetail:FragmentAllPokemonDetail
    lateinit var rvAllPokemon: RecyclerView
    lateinit var adapter: AllPokemonListeAdapter
    var listeAllPokemon: ArrayList<Pokemon> = ArrayList()

    interface GetAllPokemonCallback {
        fun onGetPokemon(resultAllPokemon: AllResult?)
        fun onError()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_all_pokemon, container, false)
        mainActivity = context as MainActivity
        rvAllPokemon = view.findViewById(R.id.rv_pokemon_fragment)
        rvAllPokemon.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL, false)
        adapter = AllPokemonListeAdapter(listeAllPokemon)
        rvAllPokemon.adapter = adapter
        //adapter.notifyDataSetChanged()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

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
    private fun setUpRecyclerView() {


        getAllPokemon(object : GetAllPokemonCallback {
            override fun onGetPokemon(resultAllPokemon: AllResult?) {
                var size = resultAllPokemon?.result?.data!!.size
                for (i in 0..size - 1) {
                    //add resultAllPlants.result.data[i].image
                    var pokemon = Pokemon(resultAllPokemon.result.data[i].id, resultAllPokemon.result.data[i].nom, "", "", resultAllPokemon.result.data[i].url)
                    listeAllPokemon.add(pokemon)

                    //Log.d("POKEMON", allResult.result.data[i].toString())
                }

                adapter.notifyDataSetChanged()
            }

            override fun onError() {

            }
        })

        fragmentAllPokemonDetail = mainActivity.fragmentAllPokemonDetail
        rvAllPokemon.addOnItemTouchListener(RecyclerTouchListener(
                activity!!.applicationContext,
                rvAllPokemon,
                object : RecyclerTouchListener.ClickListener {
                    override fun onClick(view: View, position: Int) {
                        MainActivity().openFragment(fragmentAllPokemonDetail)
                    }

                    override fun onLongClick(view: View?, position: Int) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                }))
    }




    companion object {
        fun newInstance(): FragmentAllPokemon = FragmentAllPokemon()
    }

    override fun onResume() {
        //adapter.notifyDataSetChanged()
        super.onResume()
    }


}