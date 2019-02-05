package com.example.lpiem.pokecard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lpiem.pokecard.Model.Pokemon
import com.example.lpiem.pokecard.R
import kotlinx.android.synthetic.main.item_pokemon.view.*
import android.R.attr.name
import android.widget.AdapterView.OnItemClickListener



class AllPokemonListeAdapter(items : ArrayList<Pokemon>) : RecyclerView.Adapter<AllPokemonListeAdapter.ViewHolder>(){

    var list = items



    override fun getItemCount(): Int {
        return list.size
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name?.text = list[position].nom
        holder.id.text = list[position].id_pokemon
        Glide.with(holder.image).load(list[position].url).into(holder.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val id = v.tv_id_item_pokemon
        val name = v.tv_name_item_pokemon
        val image = v.iv_item_pokemon





    }
}