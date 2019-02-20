package com.example.lpiem.pokecard.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.ViewModel.PokemonViewModel


class FragmentAllPokemonDetail : BaseFragment() {


    private lateinit var detailPokemonViewModel: PokemonViewModel

    lateinit var id : TextView
    lateinit var nom : TextView
    lateinit var type1 : TextView
    lateinit var type2 :TextView
    lateinit var image : ImageView

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

    fun setUpView(){

        detailPokemonViewModel = ViewModelProviders.of(activity!!).get(PokemonViewModel::class.java)

        //showloading
        detailPokemonViewModel.fetchOnePokemon(context!!).observe(this, Observer {
            it?.result?.id_pokemon
            //hideLoading
        })

        id.text = detailPokemonViewModel.pokemon?.id_pokemon
        nom.text = detailPokemonViewModel.pokemon?.nom
        type1.text = detailPokemonViewModel.pokemon?.nom_type_1
        type2.text = detailPokemonViewModel.pokemon?.nom_type_2
        Glide.with(image).load(detailPokemonViewModel.pokemon?.url_image).into(image)

    }


}
