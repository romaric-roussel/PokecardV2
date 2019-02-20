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



class AllPokemonListeAdapter(private val clickListener: AllPokemonListeAdapterClickListener?) : RecyclerView.Adapter<AllPokemonListeAdapter.ViewHolder>(){

    private var allpokemonList = emptyList<Pokemon>()



    override fun getItemCount(): Int {
        return allpokemonList.size
    }

    fun setData(allpokemonList: List<Pokemon>) {
        this.allpokemonList = allpokemonList
        notifyDataSetChanged()
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            clickListener?.onClick(position, allpokemonList[position])
        }
        holder.bind(allpokemonList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v){

        fun bind(pokemon: Pokemon) {

            v.tv_id_item_pokemon.text = pokemon.id_pokemon
            v.tv_name_item_pokemon.text = pokemon.nom
            Glide.with(v.iv_item_pokemon).load(pokemon.url).into(v.iv_item_pokemon)


        }
    }

    //Listener pour interragir avec l'adapter
    interface AllPokemonListeAdapterClickListener {
        fun onClick(dataPosition: Int, pokemon: Pokemon)
    }
}