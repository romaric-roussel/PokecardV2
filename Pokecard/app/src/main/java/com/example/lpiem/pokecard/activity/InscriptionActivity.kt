package com.example.lpiem.pokecard.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.ViewModel.UserViewModel
import com.example.lpiem.pokecard.data.model.*
import com.example.lpiem.pokecard.retrofit.GestionRetrofit
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InscriptionActivity : BaseActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var userResultDataObserver: Observer<UserInscriptionResult>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_sign_up)

        b_signup_fragment.setOnClickListener {

            val nom = et_lastname_signup_fragment.text.toString().trim()
            val prenom = et_firstname_signup_fragment.text.toString().trim()
            val mail = et_email_signup_fragment.text.toString().trim()
            val type=0
            val photo = "photo".trim()
            val mdp =et_password_signup_fragment.text.toString().trim()
            val confirmmdp =et_confirm_password_signup_fragment.text.toString().trim()

            if (mdp==confirmmdp){

                userViewModel.newUser(nom,prenom,mail,type,photo,mdp,confirmmdp).observe(this, userResultDataObserver)

            }
        }


        userViewModel = ViewModelProviders.of(this!!).get(UserViewModel::class.java)
        userResultDataObserver= Observer {
            startActivity(Intent(this@InscriptionActivity, Connexion::class.java))
        }
    }
}