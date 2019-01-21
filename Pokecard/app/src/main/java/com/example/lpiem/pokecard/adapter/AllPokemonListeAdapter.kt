package com.example.lpiem.pokecard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_pokemon.view.*

class ListAdapter(items : List<String>,ctx: Context) : RecyclerView.Adapter<ListAdapter.ViewHolder>(){

    var list = items
    var context = ctx

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.name?.text = list.get(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pokemon,parent,false))
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val name = v.tv_title_item_pokemon
    }
}