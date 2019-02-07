package com.example.lpiem.pokecard.activity



import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.lpiem.pokecard.R
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.connexion.*


class Connexion : BaseActivity() {


    private lateinit var mCallbackManager: CallbackManager
    lateinit var signInButton: SignInButton
    lateinit var connexionButton : Button
    lateinit var mAuth: FirebaseAuth
    //lateinit var mAuthListener: FirebaseAuth.AuthStateListener
    lateinit var displayName : String
    lateinit var mailAdress : String
    lateinit var profilPicture : String


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.connexion)

        mAuth = FirebaseAuth.getInstance()
        mCallbackManager = CallbackManager.Factory.create()
        val loginButton = findViewById<LoginButton>(R.id.fb_login)
        loginButton.setReadPermissions("email", "public_profile")
        loginButton.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d(TAG, "facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d(TAG, "facebook:onCancel")
                // [START_EXCLUDE]
                getUserProfilData(null)
                // [END_EXCLUDE]
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, "facebook:onError", error)
                // [START_EXCLUDE]
                getUserProfilData(null)
                // [END_EXCLUDE]
            }
        })



        signInButton = findViewById(R.id.googleSignIn)
        connexionButton = findViewById(R.id.connexion)
       /* connexionButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("photo", "https://graph.facebook.com/2084209848298504/picture?type=large")
            intent.putExtra("nom", "Benzaied")
            intent.putExtra("prenom", "Sofiane")
            intent.putExtra("mail", "sofiane.benzaied@yahoo.fr")
            startActivity(intent)

        }*/



        /*mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser != null) {
                startActivity(Intent(this@Connexion, MainActivity::class.java))
            }
        }*/


    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        //mAuth.addAuthStateListener(mAuthListener)
        getUserProfilData(currentUser)
        if(currentUser != null)
            startActivity(Intent(this@Connexion, MainActivity::class.java))

    }
     fun getUserProfilData(user: FirebaseUser?) {
        hideProgressDialog()
        if (user != null) {

            displayName = user.displayName.toString()
            mailAdress = user.email.toString()
            profilPicture = user.photoUrl.toString()+"?type=large"
            saveUserDataInSharePref()
           // Log.d(TAG,"test " + displayName + mailAdress + profilPicture)
            fb_login.visibility = View.GONE
        } else {

            fb_login.visibility = View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data)

    }

    private fun saveUserDataInSharePref(){
        val sharedPref = this?.getSharedPreferences(
                getString(R.string.sharePrefName), Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()){
            putString(getString(R.string.keyDisplayName), displayName)
            putString(getString(R.string.keyMailAdress), mailAdress)
            putString(getString(R.string.keyProfilPicture), profilPicture)
            commit()
        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("test", "handleFacebookAccessToken:$token")
        showProgressDialog()
        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("erreur", "signInWithCredential:success")
                        val user = mAuth.currentUser
                        getUserProfilData(user)
                        startActivity(Intent(this@Connexion, MainActivity::class.java))
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("erreur", "signInWithCredential:failure", task.exception)
                        Toast.makeText(this@Connexion, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        getUserProfilData(null)
                    }

                    hideProgressDialog()
                }
    }

    companion object {
        //private val RC_SIGN_IN = 2
        private const val TAG = "FacebookLogin"

    }



}





