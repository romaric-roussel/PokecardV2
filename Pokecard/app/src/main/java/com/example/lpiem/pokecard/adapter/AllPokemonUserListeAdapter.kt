package com.example.lpiem.pokecard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lpiem.pokecard.R
import kotlinx.android.synthetic.main.item_pokemon.view.*
import kotlinx.android.synthetic.main.user_pokemon_custom_list.view.*

class AllPokemonUserListeAdapter(items : ArrayList<String>) : RecyclerView.Adapter<AllPokemonUserListeAdapter.ViewHolder>(){

    var list = items
   // var context = ctx



    override fun getItemCount(): Int {
        return list.size
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name?.text = list[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_pokemon_custom_list, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val name = v.tv_title_item_pokemon_user
    }
}