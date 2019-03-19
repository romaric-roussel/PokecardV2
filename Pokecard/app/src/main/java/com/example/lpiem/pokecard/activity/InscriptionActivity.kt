package com.example.lpiem.pokecard.activity

import android.content.Intent
import android.os.Bundle
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.data.model.UserResult
import com.example.lpiem.pokecard.retrofit.GestionRetrofit
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InscriptionActivity : BaseActivity() {
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
            if( mdp==confirmmdp){

            GestionRetrofit.initRetrofit().newUser(nom,prenom,mail,type,photo,mdp)
                    .enqueue(object : Callback<UserResult>{
                        override fun onFailure(call: Call<UserResult>, t: Throwable) {
                            toast("Mail adress already used")
                        }

                        override fun onResponse(call: Call<UserResult>, response: Response<UserResult>) {

                            if(response.isSuccessful){
                                startActivity(Intent(this@InscriptionActivity, MainActivity::class.java)) //To change body of created functions use File | Settings | File Templates.
                            }
                        }


                    })}


        }
    }
}