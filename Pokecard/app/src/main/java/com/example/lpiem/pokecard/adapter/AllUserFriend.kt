package com.example.lpiem.pokecard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lpiem.pokecard.data.model.UserListAmis
import com.example.lpiem.pokecard.data.model.UserOneAmi
import kotlinx.android.synthetic.main.item_friend.view.*
import com.example.lpiem.pokecard.R
import kotlinx.android.synthetic.main.item_pokemon.view.*

class AllUserFriend (private val clickListener: AllUserFriendsAdapterClickListener?) : RecyclerView.Adapter<AllUserFriend.ViewHolder>(){



    private var allfriends = emptyList<UserOneAmi>()



    override fun getItemCount(): Int {
        return allfriends.size
    }

    fun setData(allfriends: List<UserOneAmi>) {
        this.allfriends = allfriends
        notifyDataSetChanged()
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            clickListener?.onClick(position, allfriends[position])
        }
        holder.bind(allfriends[position])
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(private val v: View) : RecyclerView.ViewHolder(v){

        fun bind(ami: UserOneAmi) {

            //v.tv_id_item_pokemon.text = pokemon?.idAmis.toString()
            v.tv_friend.text = ami?.nom+" "+ami?.prenom
        //    Glide.with(v.iv_item_pokemon).load(pokemon?.image).into(v.iv_item_pokemon)


        }
    }


    interface AllUserFriendsAdapterClickListener {
        fun onClick(dataPosition: Int, pokemon: UserOneAmi)
    }
}

