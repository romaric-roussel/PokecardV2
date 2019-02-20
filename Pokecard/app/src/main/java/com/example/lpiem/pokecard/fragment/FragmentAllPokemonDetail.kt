package com.example.lpiem.pokecard.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.lpiem.pokecard.Model.Pokemon
import com.example.lpiem.pokecard.Model.retrofit.OneResult
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.retrofit.GestionRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentAllPokemonDetail : BaseFragment() {

    val api by lazy {
        GestionRetrofit.initRetrofit()
    }
    var pokemonId = ""
    var pokemon = Pokemon("","","","","")
    lateinit var id : TextView
    lateinit var nom : TextView
    lateinit var type1 : TextView
    lateinit var type2 :TextView
    lateinit var image : ImageView
    interface GetOnePokemonCallback {
        fun onGetPokemon(oneResult: OneResult?)
        fun onError()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_all_pokemon_detail, container, false)
        //adapter.notifyDataSetChanged()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        id = view.findViewById(R.id.tv_id_all_pokemon_detail)
        nom = view.findViewById(R.id.tv_nom_all_pokemon_detail)
        type1 = view.findViewById(R.id.tv_type1_all_pokemon_detail)
        type2 = view.findViewById(R.id.tv_type2_all_pokemon_detail)
        image = view.findViewById(R.id.iv_all_pokemon_detail)
        setUpView()


        super.onViewCreated(view, savedInstanceState)

    }



    private fun getOnePokemon(getOnePokemonCallback: GetOnePokemonCallback) {


        api.getOnePokemon(pokemonId).enqueue(object : Callback<OneResult> {
            override fun onResponse(call: Call<OneResult>, response: Response<OneResult>) {
                if (response.isSuccessful) {
                    getOnePokemonCallback.onGetPokemon(response.body())
                } else {
                    getOnePokemonCallback.onError()
                }


            }

            override fun onFailure(call: Call<OneResult>, t: Throwable) {
                error("KO")
            }
        })
    }
    private fun setUpView()
    {
        getOnePokemon(object : GetOnePokemonCallback{
            override fun onError() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onGetPokemon(oneResult: OneResult?) {
                 pokemon = Pokemon(oneResult?.result?.id_pokemon.toString(),oneResult?.result?.nom.toString(),oneResult?.result?.nom_type_1.toString(),oneResult?.result?.nom_type_2.toString(),oneResult?.result?.url_image.toString())
                id.text = pokemon.id_pokemon
                nom.text = pokemon.nom
                type1.text = pokemon.type_1
                type2.text = pokemon.type_2
                Glide.with(image).load(pokemon.url).into(image)

                Log.d("POKEMON", "kek " + pokemon.toString())
            }
        })






    }




}
