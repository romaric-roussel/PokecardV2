package com.example.lpiem.pokecard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lpiem.pokecard.data.model.ResultData
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.data.model.UserPokemonResultData
import kotlinx.android.synthetic.main.item_pokemon.view.*
import kotlinx.android.synthetic.main.user_pokemon_custom_list.view.*

class AllPokemonUserListeAdapter(private val clickListener: AllUserPokemonListeAdapterClickListener?) : RecyclerView.Adapter<AllPokemonUserListeAdapter.ViewHolder>(){

    private var allUserpokemonList = emptyList<UserPokemonResultData>()



    override fun getItemCount(): Int {
        return allUserpokemonList.size
    }

    fun setData(allUserPokemonList: List<UserPokemonResultData>) {
        this.allUserpokemonList = allUserPokemonList
        notifyDataSetChanged()
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            clickListener?.onClick(position, allUserpokemonList[position])
        }
        holder.bind(allUserpokemonList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v){

        fun bind(pokemon: UserPokemonResultData) {

            v.tv_id_item_pokemon.text = pokemon?.id
            v.tv_name_item_pokemon.text = pokemon?.name
            Glide.with(v.iv_item_pokemon).load(pokemon?.image).into(v.iv_item_pokemon)


        }
    }

    //Listener pour interragir avec l'adapter
    interface AllUserPokemonListeAdapterClickListener {
        fun onClick(dataPosition: Int, pokemon: UserPokemonResultData)
    }
}