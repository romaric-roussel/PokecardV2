package com.example.lpiem.pokecard.View

import android.content.Intent
import android.os.Bundle

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lpiem.pokecard.Accueil
import com.example.lpiem.pokecard.retrofit.API.C
import com.example.lpiem.pokecard.R
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class GoogleView : AppCompatActivity(){

    private var mCallbackManager: CallbackManager? = null
    lateinit var button: SignInButton
    lateinit var mAuth: FirebaseAuth
    lateinit var mGoogleApiClient: GoogleApiClient
    lateinit var mAuthListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.connexion)

        mAuth = FirebaseAuth.getInstance()
        mCallbackManager = CallbackManager.Factory.create()
        button = findViewById(R.id.sign_in_button)
        button.setOnClickListener { signIn() }
        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser != null) {
                startActivity(Intent(this, Accueil::class.java))
            }
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()


        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this) { Toast.makeText(this, "Marche pas ", Toast.LENGTH_SHORT).show() }
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
    }

    private fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, C.RC_SIGN_IN)
    }


    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d("auth", "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("connex", "signInWithCredential:success")
                        val user = mAuth.currentUser
                        //updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("con", "signInWithCredential:failure", task.exception)
                        Toast.makeText(this, "Marche pas authentification-connect", Toast.LENGTH_SHORT).show()
                        // updateUI(null);
                    }

                    // ...
                }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        mAuth.addAuthStateListener(mAuthListener)
        if (currentUser != null) {
            updateUI()
        }

    }

    private fun updateUI() {
        Toast.makeText(this, "You're logged in", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, Accueil::class.java)
        startActivity(intent)
        finish()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        mCallbackManager!!.onActivityResult(requestCode, resultCode, data)

        if (requestCode == C.RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult<ApiException>(ApiException::class.java!!)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, "Marche pas authentification", Toast.LENGTH_SHORT).show()
                // ...
            }

        }
    }


}