package com.example.lpiem.pokecard.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.ViewDialog
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
    private lateinit var userResultDataObserver2: Observer<UserInscriptionResult>
    private lateinit var viewDialog: ViewDialog
    private lateinit var stateObserver: Observer<AllPokemonState>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_sign_up)
        viewDialog= ViewDialog()
        b_signup_fragment.setOnClickListener {

            val nom = et_lastname_signup_fragment.text.toString().trim()
            val prenom = et_firstname_signup_fragment.text.toString().trim()
            val mail = et_email_signup_fragment.text.toString().trim()
            val type=0
            val photo = "photo".trim()
            val mdp =et_password_signup_fragment.text.toString().trim()
            val confirmmdp =et_confirm_password_signup_fragment.text.toString().trim()

            if (mdp==confirmmdp){

                userViewModel.newUser(nom,prenom,mail,type,photo,mdp,confirmmdp).observe(this, userResultDataObserver2)
                userViewModel.state.observe(this, stateObserver)


            }
            else{
                Toast.makeText(this,"Veuillez vérifiez vos champs",Toast.LENGTH_LONG).show()}
        }


        userViewModel = ViewModelProviders.of(this!!).get(UserViewModel::class.java)
        userResultDataObserver2= Observer {

            Log.d("itcode",it.code.toString())
            if (it.code==201)
            { Log.d("itcode2",it.code.toString())
                startActivity(Intent(this@InscriptionActivity, Connexion::class.java))
                finish()
            }
            else{Toast.makeText(this,"Mail déjà utilisé",Toast.LENGTH_LONG).show()}
    }
        stateObserver= Observer {

            handleState(it)
        }



}

    override fun onDestroy() {
        super.onDestroy()
        userViewModel.state.removeObserver(stateObserver)
    }

    private fun handleState(state: AllPokemonState?) {
        if(state?.isProgressLoadingShown == true){
            showLoadingProgress(this)
            return
        }
        if(state?.isProgressLoadingShown == false){
            hideLoadingProgress()
            return
        }
    }



    fun showLoadingProgress(context: Context){

        viewDialog.ViewDialog(context)
        viewDialog.showDialog()
    }

    fun hideLoadingProgress(){
        viewDialog.hideDialog()
    }


}