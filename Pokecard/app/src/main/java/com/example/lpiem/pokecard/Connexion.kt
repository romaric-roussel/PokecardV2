package com.example.lpiem.pokecard

//3gm6wp3jThW8ssPN1P8ULbiBTrc=
//https://www.youtube.com/watch?v=mapLbSKNc6I

import android.content.Intent
import android.os.Bundle

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*

import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import org.json.JSONObject
import java.net.URL
import java.util.*


class Connexion : AppCompatActivity() {

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
        val loginButton = findViewById<LoginButton>(R.id.login_button)
        loginButton.setReadPermissions(Arrays.asList("public_profile","email","user_birthday","user_friends"))
        button = findViewById(R.id.sign_in_button)

        button.setOnClickListener { signIn() }
        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser != null) {
                startActivity(Intent(this@Connexion, MainActivity::class.java))
            }
        }


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()


        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this) { Toast.makeText(this@Connexion, "Marche pas ", Toast.LENGTH_SHORT).show() }
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()



        loginButton.setReadPermissions("email", "public_profile")

        loginButton.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d("Test", "facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult.accessToken)
                Log.d("FBLOGIN", loginResult.accessToken.token.toString())
                Log.d("FBLOGIN", loginResult.recentlyDeniedPermissions.toString())
                Log.d("FBLOGIN", loginResult.recentlyGrantedPermissions.toString())


                val request = GraphRequest.newMeRequest(loginResult.accessToken) { `object`, response ->
                    try {
                        //here is the data that you want
                        Log.d("FBLOGIN_JSON_RES", `object`.toString())

                        if (`object`.has("id")) {
                           // handleSignInResultFacebook(`object`)
                            Log.d("CA PASSE","name,email,id,picture.type(large)")
                        } else {
                            Log.e("FBLOGIN_FAILD", `object`.toString())
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()

                    }
                }
                //Sofiane Benzaied
                //sofiane.benzaied@yahoo.fr
                val parameters = Bundle()
                parameters.putString("fields", "name,email,id,picture.type(large)")
                request.parameters = parameters
                request.executeAsync()


            }

            override fun onCancel() {
                Log.d("Test", "facebook:onCancel")
                // ...
            }

            override fun onError(error: FacebookException) {
                Log.d("Test", "facebook:onError", error)
                // ...
            }


        })

    }



    private fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
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
                        Toast.makeText(this@Connexion, "Marche pas authentification-connect", Toast.LENGTH_SHORT).show()
                        // updateUI(null);
                    }

                    // ...
                }
    }

    val photo= URL("https://graph.facebook.com/")

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
        Toast.makeText(this@Connexion, "You're logged in", Toast.LENGTH_SHORT).show()

      //  val intent = Intent(this, MainActivity::class.java)
        val intent = Intent(this, FragmentProfile::class.java)
        var nom="Sofiane Benzaied"
        var email="sofiane.benzaied@yahoo.fr"
        var photo="https://graph.facebook.com/2084209848298504/picture?type=large"
        intent.putExtra("photo","https://graph.facebook.com/2084209848298504/picture?type=large")
        startActivity(intent)
        finish()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        mCallbackManager!!.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult<ApiException>(ApiException::class.java!!)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this@Connexion, "Marche pas authentification", Toast.LENGTH_SHORT).show()
                // ...
            }

        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("test", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("erreur", "signInWithCredential:success")
                        val user = mAuth.currentUser
                        updateUI()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("erreur", "signInWithCredential:failure", task.exception)
                        Toast.makeText(this@Connexion, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI()
                    }

                    // ...
                }
    }

    companion object {
        private val RC_SIGN_IN = 2
    }


}

// Initialize Facebook Login button




