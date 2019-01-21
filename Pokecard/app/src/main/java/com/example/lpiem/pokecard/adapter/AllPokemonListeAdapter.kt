package com.example.lpiem.pokecard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.R
import kotlinx.android.synthetic.main.item_pokemon.view.*

class AllPokemonListeAdapter(items : List<String>) : RecyclerView.Adapter<AllPokemonListeAdapter.ViewHolder>(){




    var list = items

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.name?.text = list.get(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllPokemonListeAdapter.ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false))
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val name = v.tv_title_item_pokemon
    }
}