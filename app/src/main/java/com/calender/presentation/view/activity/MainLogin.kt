package com.calender.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.calender.presentation.R
import com.calender.presentation.base.BaseActivity
import com.calender.presentation.databinding.ActivityMainLoginBinding
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.startActivity

@AndroidEntryPoint
class MainLogin : BaseActivity<ActivityMainLoginBinding>(R.layout.activity_main_login) {
    private var googleSignInClient : GoogleSignInClient? = null
    private var GOOGLE_LOGIN_CODE = 9001
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(resources.getString(R.string.google_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        auth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        if(auth?.currentUser != null){
            startActivity<MainActivity>()
        }
        binding.googlebutton.setOnClickListener{
            googleLogin()
        }
        binding.AuthButton.setOnClickListener {
            if(auth?.currentUser != null){
                auth?.signOut()
            }
            auth?.signInAnonymously()
                ?.addOnCompleteListener(this){ task->
                    if(task.isSuccessful){
                        startActivity<MainActivity>()
                    }
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_LOGIN_CODE) {
            var result = data?.let { Auth.GoogleSignInApi.getSignInResultFromIntent(it) }
            if (result != null) {
                if (result.isSuccess) {
                    var account = result.signInAccount
                    firebaseAuthWithGoogle(account)
                }
            }
        }
    }

    fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        var credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener {
                    task ->
                if(task.isSuccessful) {
                    // 로그인 성공 시
                    startActivity<MainActivity>()
                } else {
                    // 로그인 실패 시
                    Toast.makeText(this,  task.exception?.message, Toast.LENGTH_LONG).show()
                }

            }
    }

    fun googleLogin() {
        var signInIntent = googleSignInClient?.signInIntent
        startActivityForResult(signInIntent, GOOGLE_LOGIN_CODE)
    }
}