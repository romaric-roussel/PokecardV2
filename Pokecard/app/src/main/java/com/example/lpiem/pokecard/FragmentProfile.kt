package com.example.lpiem.pokecard
import android.graphics.Bitmap
import android.net.Uri
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.example.lpiem.pokecard.R.id.iv_photo_fragment_account_informations
import kotlinx.android.synthetic.main.fragment_user.*

class FragmentProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_user)

        val imageView=findViewById<ImageView>(R.id.iv_photo_fragment_account_informations)
        val nom= findViewById<TextView>(R.id.tv_name_fragment_account_informations)
        val prenom= findViewById<TextView>(R.id.tv_birthdate_fragment_account_informations)
        val mail= findViewById<TextView>(R.id.tv_mail_fragment_account_informations)
        val URL=intent.getStringExtra("photo")

        nom.text=intent.getStringExtra("nom")
        prenom.text=intent.getStringExtra("prenom")
        mail.text=intent.getStringExtra("mail")

        Glide.with(this).load(URL).into(imageView)



    }



}