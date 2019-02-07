/*
package com.example.lpiem.pokecard.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.lpiem.pokecard.activity.MainActivity
import com.example.lpiem.pokecard.R
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class FragmentConnexion: Fragment() {
    lateinit var mainActivity: MainActivity
     var mCallbackManager: CallbackManager? = null
    lateinit var button: SignInButton
    lateinit var button_connexion : Button
    lateinit var fragmentprofile : FragmentProfile
    lateinit var fragmentAllPokemon: FragmentAllPokemon

    lateinit var mAuth: FirebaseAuth
    lateinit var mGoogleApiClient: GoogleApiClient
    lateinit var mAuthListener: FirebaseAuth.AuthStateListener


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
    */
/*

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_all_pokemon, container, false)
    mainActivity = context as MainActivity
    rvAllPokemon = view.findViewById(R.id.rv_pokemon_fragment)
    rvAllPokemon.layoutManager = LinearLasyoutManager(context,RecyclerView.VERTICAL, false)
    adapter = AllPokemonListeAdapter(listeAllPokemon)
    rvAllPokemon.adapter = adapter
    //adapter.notifyDataSetChanged()
    return view

}*//*


        val view = inflater.inflate(R.layout.connexion, container, false)
        mainActivity = context as MainActivity
        mAuth = FirebaseAuth.getInstance()
        mCallbackManager = CallbackManager.Factory.create()
        val loginButton = view.findViewById<LoginButton>(R.id.fb_login)
        loginButton.setReadPermissions(Arrays.asList("public_profile","email","user_birthday","user_friends"))
        button = view.findViewById(R.id.googleSignIn)
        button_connexion = view.findViewById(R.id.connexion)
        fragmentprofile = mainActivity.fragmentProfile
        button_connexion.setOnClickListener {
            (activity as MainActivity).openFragment(FragmentAllPokemon())

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
                            //here is the data that you want
                            Log.d("FBLOGIN_JSON_RES", `object`.toString())

                            if (`object`.has("id")) {
                                // handleSignInResultFacebook(`object`)
                                Log.d("CA PASSE", "name,email,id,picture.type(large)")
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

        loginButton.setOnClickListener{
            (activity as MainActivity).openFragment(FragmentAllPokemon())
        }


//        signInButton.setOnClickListener { signIn() }
      mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
          if (firebaseAuth.currentUser != null) {
              */
/*startActivity(Intent(this@Connexion, MainActivity::class.java))*//*

          }
      }


//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build()


//        mGoogleApiClient = GoogleApiClient.Builder(this)
//                .enableAutoManage(this) { Toast.makeText(this@Connexion, "Marche pas ", Toast.LENGTH_SHORT).show() }
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build()



    return view
    }





    private fun signIn() {
//        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
//        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

//    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
//        Log.d("auth", "firebaseAuthWithGoogle:" + acct.id!!)
//
//        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d("connex", "signInWithCredential:success")
//                        val user = mAuth.currentUser
//                        //updateUI(user);
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w("con", "signInWithCredential:failure", task.exception)
//                        Toast.makeText(this@Connexion, "Marche pas authentification-connect", Toast.LENGTH_SHORT).show()
//                        // updateUI(null);
//                    }
//
//                    // ...
//                }
//    }

    */
/*val photo= URL("https://graph.facebook.com/")*//*


     override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        mAuth.addAuthStateListener(mAuthListener)
        if (currentUser != null) {
            updateUI()
        }

    }

    private fun updateUI() {
//        Toast.makeText(this@Connexion, "You're logged in", Toast.LENGTH_SHORT).show()
//
//        //  val intent = Intent(this, MainActivity::class.java)
//        val intent = Intent(this, FragmentProfile::class.java)
//        intent.putExtra("photo","https://graph.facebook.com/2084209848298504/picture?type=large")
//        intent.putExtra("nom","Benzaied")
//        intent.putExtra("prenom","Sofiane")
//        intent.putExtra("mail","sofiane.benzaied@yahoo.fr")
//        startActivity(intent)
        */
/*finish()*//*


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        mCallbackManager!!.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                val account = task.getResult<ApiException>(ApiException::class.java!!)
//                firebaseAuthWithGoogle(account!!)
//            } catch (e: ApiException) {
//                // Google Sign In failed, update UI appropriately
//                Toast.makeText(this@Connexion, "Marche pas authentification", Toast.LENGTH_SHORT).show()
//                // ...
//            }

        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("test", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        // Sign in success, update UI with the signed-in user's information
//                        Log.d("erreur", "signInWithCredential:success")
//                        val user = mAuth.currentUser
//                        updateUI()
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Log.w("erreur", "signInWithCredential:failure", task.exception)
//                        Toast.makeText(this@Connexion, "Authentication failed.",
//                                Toast.LENGTH_SHORT).show()
//                        updateUI()
//                    }
//
//                    // ...
//                }
    }

    companion object {
        private val RC_SIGN_IN = 2
    }


}
*/
