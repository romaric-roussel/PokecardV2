package com.example.lpiem.pokecard.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.lpiem.pokecard.R

class FragmentProfile : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        val imageView = view.findViewById<ImageView>(R.id.iv_photo_fragment_account_informations)
        val nom = view.findViewById<TextView>(R.id.tv_name_fragment_account_informations)
        val prenom = view.findViewById<TextView>(R.id.tv_birthdate_fragment_account_informations)
        val mail = view.findViewById<TextView>(R.id.tv_mail_fragment_account_informations)
        var photo = "https://graph.facebook.com/2084209848298504/picture?type=large"
        var profilenom = "Benzaied"
        var profileprenom = "Sofiane"
        var profilemail = "sofiane.benzaied@yahoo.fr"


        nom.text = "Benzaied"
        prenom.text = "Sofiane"
        mail.text = "sofiane.benzaied@yahoo.fr"

        Glide.with(imageView).load(photo).into(imageView)

        return view

    }


}