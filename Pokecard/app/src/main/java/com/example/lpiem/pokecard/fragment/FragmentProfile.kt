package com.example.lpiem.pokecard.fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.lpiem.pokecard.activity.MainActivity

import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.activity.Connexion
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.connexion.*
import kotlinx.android.synthetic.main.fragment_user.*

private lateinit var auth: FirebaseAuth

class FragmentProfile : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        val imageView = view.findViewById<ImageView>(R.id.iv_photo_fragment_account_informations)
        val nom = view.findViewById<TextView>(R.id.tv_name_fragment_account_informations)
        val prenom = view.findViewById<TextView>(R.id.tv_birthdate_fragment_account_informations)
        nom.text = (activity as MainActivity).CompteNom
        prenom.text = (activity as MainActivity).CompteMail
        val boutonFacebook=view.findViewById<Button>(R.id.login_button)
        Glide.with(imageView).load((activity as MainActivity).CompteImage).into(imageView)
        FirebaseAuth.getInstance().signOut()
        boutonFacebook.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity, Connexion::class.java)
            startActivity(intent)}

        return view

    }


}