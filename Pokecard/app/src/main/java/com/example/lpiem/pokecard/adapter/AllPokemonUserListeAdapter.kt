package com.example.lpiem.pokecard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lpiem.pokecard.data.model.ResultData
import com.example.lpiem.pokecard.R
import kotlinx.android.synthetic.main.user_pokemon_custom_list.view.*

class AllPokemonUserListeAdapter(items : ArrayList<ResultData>) : RecyclerView.Adapter<AllPokemonUserListeAdapter.ViewHolder>(){

    var list = items
   // var context = ctx



    override fun getItemCount(): Int {
        return list.size
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name?.text = list[position].name
        holder.id.text = list[position].id
        Glide.with(holder.image).load(list[position].image).into(holder.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_pokemon_custom_list, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val id = v.tv_id_item_pokemon_user
        val name = v.tv_name_item_pokemon_user
        val image = v.iv_item_pokemon_user
    }

}