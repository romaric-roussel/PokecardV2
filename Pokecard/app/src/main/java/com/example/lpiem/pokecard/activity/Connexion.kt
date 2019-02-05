package com.example.lpiem.pokecard.activity



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lpiem.pokecard.R
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import java.util.*


class Connexion : AppCompatActivity() {

    private var mCallbackManager: CallbackManager? = null
    lateinit var button: SignInButton
    lateinit var button_connexion : Button
    lateinit var mAuth: FirebaseAuth
    lateinit var mAuthListener: FirebaseAuth.AuthStateListener
    lateinit var CompteNom : String
    lateinit var CompteMail : String
    lateinit var CompteImage : String


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.connexion)
        mAuth = FirebaseAuth.getInstance()
        mCallbackManager = CallbackManager.Factory.create()
        val loginButton = findViewById<LoginButton>(R.id.login_button)
        loginButton.setReadPermissions(Arrays.asList("public_profile","email","user_birthday","user_friends"))
        button = findViewById(R.id.sign_in_button)
        button_connexion = findViewById(R.id.connexion)
        button_connexion.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("photo","https://graph.facebook.com/2084209848298504/picture?type=large")
            intent.putExtra("nom","Benzaied")
            intent.putExtra("prenom","Sofiane")
            intent.putExtra("mail","sofiane.benzaied@yahoo.fr")
            startActivity(intent)

        }



        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser != null) {
                startActivity(Intent(this@Connexion, MainActivity::class.java))
            }
        }


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
                        //Recuperation des données JSON Facebook
                        Log.d("FBLOGIN_JSON_RES", `object`.toString())
                        CompteNom= response.jsonObject.getString("name")
                        CompteMail=response.jsonObject.getString("email")
                        CompteImage= "https://graph.facebook.com/"+response.jsonObject.getString("id")+"/picture?type=large"



                        if (`object`.has("id")) {
                            Log.d("CA PASSE","name,email,id,picture.type(large)")
                        } else {
                            Log.e("FBLOGIN_FAILD", `object`.toString())
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()

                    }
                }
                val parameters = Bundle()
                parameters.putString("fields", "name,email,id,picture.type(large)")
                request.parameters = parameters
                request.executeAsync()


            }

            override fun onCancel() {
                Log.d("Test", "facebook:onCancel")

            }

            override fun onError(error: FacebookException) {
                Log.d("Test", "facebook:onError", error)

            }


        })

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

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("photo",CompteImage)
        intent.putExtra("nom",CompteNom)
        intent.putExtra("mail",CompteMail)
        startActivity(intent)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        mCallbackManager!!.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            try {

            } catch (e: ApiException) {
                Toast.makeText(this@Connexion, "Marche pas authentification", Toast.LENGTH_SHORT).show()

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


                }
    }

    companion object {
        private val RC_SIGN_IN = 2
    }


}





