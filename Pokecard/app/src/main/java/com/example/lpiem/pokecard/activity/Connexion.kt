package com.example.lpiem.pokecard.activity



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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


class Connexion : BaseActivity() , View.OnClickListener{


    private lateinit var mCallbackManager: CallbackManager
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

        buttonFacebookSignout.setOnClickListener(this)

        mAuth = FirebaseAuth.getInstance()
        mCallbackManager = CallbackManager.Factory.create()
        val loginButton = findViewById<LoginButton>(R.id.login_button)
        loginButton.setReadPermissions("email", "public_profile")
        loginButton.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d(TAG, "facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d(TAG, "facebook:onCancel")
                // [START_EXCLUDE]
                updateUI(null)
                // [END_EXCLUDE]
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, "facebook:onError", error)
                // [START_EXCLUDE]
                updateUI(null)
                // [END_EXCLUDE]
            }
        })



        button = findViewById(R.id.sign_in_button)
        button_connexion = findViewById(R.id.connexion)
        button_connexion.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra("photo", "https://graph.facebook.com/2084209848298504/picture?type=large")
            intent.putExtra("nom", "Benzaied")
            intent.putExtra("prenom", "Sofiane")
            intent.putExtra("mail", "sofiane.benzaied@yahoo.fr")
            startActivity(intent)

        }



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
        updateUI(currentUser)

    }
    private fun updateUI(user: FirebaseUser?) {
        hideProgressDialog()
        if (user != null) {
           Log.d(TAG,"test " + user.toString())

            login_button.visibility = View.GONE
            buttonFacebookSignout.visibility = View.VISIBLE
        } else {


            login_button.visibility = View.VISIBLE
            buttonFacebookSignout.visibility = View.GONE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data)

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
                        updateUI(user)
                        startActivity(Intent(this@Connexion, MainActivity::class.java))
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("erreur", "signInWithCredential:failure", task.exception)
                        Toast.makeText(this@Connexion, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }

                    hideProgressDialog()
                }
    }

    companion object {
        private val RC_SIGN_IN = 2
        private const val TAG = "FacebookLogin"
    }
    override fun onClick(v: View) {
        val i = v.id
        if (i == R.id.buttonFacebookSignout) {
            signOut()
        }
    }
    fun signOut() {
        mAuth.signOut()
        LoginManager.getInstance().logOut()

        updateUI(null)
    }


}





