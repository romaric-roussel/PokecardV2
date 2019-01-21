package com.example.lpiem.pokecard.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lpiem.pokecard.R

class FragmentAllUserPokemon : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_user_pokemon, container, false)

    companion object {
        fun newInstance(): FragmentAllUserPokemon = FragmentAllUserPokemon()
    }
}