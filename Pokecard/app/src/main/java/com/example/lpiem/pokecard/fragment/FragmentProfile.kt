package com.example.lpiem.pokecard.fragment
import android.content.Context
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
import com.example.lpiem.pokecard.activity.OubliMdp
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.absoluteValue


class FragmentProfile : Fragment(),View.OnClickListener {

    var displayName : String? = null
    var mailAdress :String? = null
    var profilPicture :String?= null


    lateinit var userPicture: ImageView
    lateinit var userName: TextView
    lateinit var userMail: TextView
    lateinit var fbBtnLogOut: Button
    lateinit var bOubli: Button

    lateinit var mAuth: FirebaseAuth


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        userPicture = view.findViewById<ImageView>(R.id.iv_photo_fragment_user)
        userName = view.findViewById<TextView>(R.id.tv_name_fragment_user)
        userMail = view.findViewById<TextView>(R.id.tv_email_fragment_user)
        fbBtnLogOut = view.findViewById<Button>(R.id.fb_log_out_profil)
        bOubli = view.findViewById<Button>(R.id.b_oubli_mdp)

        mAuth = FirebaseAuth.getInstance()

        bOubli.setOnClickListener {
            activity?.let{
                val intent = Intent (it, OubliMdp::class.java)
                it.startActivity(intent)
            }
        }
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSharePref()

        userName.text = displayName
        userMail.text = mailAdress
        Glide.with(userPicture).load(profilPicture).into(userPicture)

        fbBtnLogOut.setOnClickListener(this)

    }

    private fun getSharePref() {
        val sharedPref = activity?.getSharedPreferences(resources.getString(R.string.sharePrefName), Context.MODE_PRIVATE) ?: return
        displayName = sharedPref.getString(resources.getString(R.string.keyDisplayName),"")
        mailAdress = sharedPref.getString(resources.getString(R.string.keyMailAdress),"")
        profilPicture = sharedPref.getString(resources.getString(R.string.keyProfilPicture),"")
    }

    override fun onClick(v: View) {
        val i = v.id
        if (i == R.id.fb_log_out_profil) {
            signOut()
            val intent = Intent(activity, Connexion::class.java)
            startActivity(intent)
        }
    }


    fun signOut() {
        mAuth.signOut()
        LoginManager.getInstance().logOut()
        removeSharePref()
        //Connexion().getUserProfilData(null)

    }

    private fun removeSharePref(){
        val sharedPref = activity?.getSharedPreferences(resources.getString(R.string.sharePrefName),Context.MODE_PRIVATE) ?:return
        sharedPref.edit().clear().commit()
    }
}