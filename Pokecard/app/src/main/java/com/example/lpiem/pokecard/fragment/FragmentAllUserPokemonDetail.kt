package com.example.lpiem.pokecard.fragment

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
import com.example.lpiem.pokecard.data.model.ResultOnePokemonData


class FragmentAllUserPokemonDetail : BaseFragment() {


    private lateinit var detailPokemonViewModel: PokemonViewModel
    lateinit var onePokemonObserver : Observer<ResultOnePokemonData>

    lateinit var id : TextView
    lateinit var nbExemplaire : TextView
    lateinit var nom : TextView
    lateinit var type1 : TextView
    lateinit var type2 :TextView
    lateinit var image : ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_pokemon_detail, container, false)
        //adapter.notifyDataSetChanged()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        id = view.findViewById(R.id.tv_id_user_pokemon)
        nbExemplaire = view.findViewById(R.id.tv_nb_exemplaire)
        nom = view.findViewById(R.id.tv_nom_all_pokemon_user_detail)
        type1 = view.findViewById(R.id.tv_type1_all_pokemon_user_detail)
        type2 = view.findViewById(R.id.tv_type2_all_pokemon_user_detail)
        image = view.findViewById(R.id.iv_all_pokemon_user_detail)


        detailPokemonViewModel = ViewModelProviders.of(activity!!).get(PokemonViewModel::class.java)
        onePokemonObserver = Observer {result ->
            setUpView(result)
        }
        detailPokemonViewModel.getOnePokemonUserLiveData().observe(this, onePokemonObserver)


        super.onViewCreated(view, savedInstanceState)

    }

    fun setUpView(result : ResultOnePokemonData){



        id.text = result.id_pokemon
        nom.text = result.nom
        type1.text = result.nom_type_1
        type2.text = result.nom_type_2
        Glide.with(image).load(result.url_image).into(image)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        detailPokemonViewModel.resultOnePokemonData.removeObserver(onePokemonObserver)
    }
}
