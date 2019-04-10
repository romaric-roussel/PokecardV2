package com.example.lpiem.pokecard.activity



import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.retrofit.API.C.RC_SIGN_IN
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.jetbrains.anko.toast
import com.example.lpiem.pokecard.ViewModel.UserViewModel
import com.example.lpiem.pokecard.data.model.UserAllResult
import com.example.lpiem.pokecard.data.model.UserInscriptionResult
import com.example.lpiem.pokecard.data.model.UserResult
import com.example.lpiem.pokecard.data.model.UserResultData
import com.facebook.*
import kotlinx.android.synthetic.main.connexion.*


class Connexion : BaseActivity() {

    lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mCallbackManager: CallbackManager
    lateinit var signInButton: SignInButton
    lateinit var connexionButton : Button
    lateinit var mAuth: FirebaseAuth
    lateinit var gso: GoogleSignInOptions
     var displayName : String? = ""
     var mailAdress : String? = ""
     var profilPicture : String? = ""
     var displayId : String? = ""
     var status : String? = ""
     var coderesponse : Int=0

    private lateinit var userViewModel: UserViewModel
    private lateinit var userResultDataObserver: Observer<UserResultData>
    private lateinit var userResultDataObserver2: Observer<UserInscriptionResult>



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.connexion)
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mAuth = FirebaseAuth.getInstance()
        mCallbackManager = CallbackManager.Factory.create()
      //  val loginButton = findViewById<LoginButton>(R.id.fb_login)
        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        fb_login.setReadPermissions("email", "public_profile")
        fb_login.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {




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

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        signInButton.setOnClickListener {
            view: View? -> signInGoogle()
        }

        userViewModel = ViewModelProviders.of(this!!).get(UserViewModel::class.java)
        userResultDataObserver = Observer {

            status=it.status
            coderesponse=it.code
            if (coderesponse != 404){
                displayName=it.nom+" "+it.prenom
                mailAdress=it.mail
                profilPicture=it.photo
                displayId=it.id
                saveUserDataInSharePref()
                toast(it.status)
                startActivity(Intent(this@Connexion, MainActivity::class.java))
            }else {
                toast(it.status)

            }


        }
        Connexion.observer = userResultDataObserver


        connexionButton.setOnClickListener{
            val email = Identifiant.text.toString().trim()
            val mdp = password.text.toString().trim()
            userViewModel.getUser(email,mdp).observe(this, userResultDataObserver)

        }

        b_oubli_mdp.setOnClickListener { startActivity(Intent(this@Connexion, OubliMdp::class.java)) }
        b_inscription.setOnClickListener { startActivity(Intent(this@Connexion, InscriptionActivity::class.java)) }

    }

    private fun signInGoogle () {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }




        private fun handleResult (completedTask: Task<GoogleSignInAccount>) {
            try {
                val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)!!
                updateUI (account)
            } catch (e: ApiException) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            }
        }

        private fun updateUI (account: GoogleSignInAccount) {

           displayName =  account.displayName.toString()
           mailAdress =   account.email.toString()
           profilPicture =account.photoUrl.toString()
           displayId= account.id.toString()
            saveUserDataInSharePref()


            startActivity(Intent(this@Connexion, MainActivity::class.java))

}



    public override fun onStart() {
        super.onStart()

        val currentUser = mAuth.currentUser

        getUserProfilData(currentUser)
        if(currentUser != null)
            startActivity(Intent(this@Connexion, MainActivity::class.java))

    }

     fun getUserProfilData(user: FirebaseUser?) {
        hideProgressDialog()
        if (user != null) {

            displayName =   user.displayName.toString()
            mailAdress =        user.email.toString()
            profilPicture = user.photoUrl.toString()+"?type=large"
            saveUserDataInSharePref()
            fb_login.visibility = View.GONE
        } else {

            fb_login.visibility = View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }else {
         //   Toast.makeText(this, "Problem in execution order :(", Toast.LENGTH_LONG).show()
        }

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
            putString(getString(R.string.keyId),displayId)

            commit()
        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("test", "handleFacebookAccessToken:$token")
        val credential = FacebookAuthProvider.getCredential(token.token)
        showProgressDialog()
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("erreur", "signInWithCredential:success")
                        val user = mAuth.currentUser
                        getUserProfilData(user)

                        val nom = user!!.displayName.toString().trim()
                        val prenom ="".trim()
                        val mail = user!!.email.toString().trim()
                        val type=0
                        val photo = user.photoUrl.toString()+"?type=large".trim()
                        val mdp ="".trim()
                        val confirmmdp ="".trim()
                        if (mdp==confirmmdp){

                            userViewModel.newUser(nom,prenom,mail,type,photo,mdp,confirmmdp).observe(this, userResultDataObserver2)

                        }

                        userViewModel = ViewModelProviders.of(this!!).get(UserViewModel::class.java)
                        userResultDataObserver2= Observer {


                            if (it!=null)
                            {}
                            else{toast("Mail déjà utilisé")}
                        }


                        startActivity(Intent(this@Connexion, MainActivity::class.java))
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("erreur", "signInWithCredential:failure", task.exception)
                        Toast.makeText(this@Connexion, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        getUserProfilData(null)

                       Log.d("task error" ,task.exception.toString())
                    }

                    hideProgressDialog()
                }
    }


    companion object {
        //private val RC_SIGN_IN = 2
        private const val TAG = "FacebookLogin"
        lateinit var observer: Observer<UserResultData>
        fun getOberver(obs:Observer<UserResultData>){
            observer = obs
        }

    }


}





