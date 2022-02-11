package com.example.firebase.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.AppUtils
import com.example.firebase.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import java.util.concurrent.Executors

open class BaseActivity : AppCompatActivity() {

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    companion object {
        val executor = Executors.newSingleThreadExecutor()!!
    }

//    fun createRequest() {
//        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOption)
//
//    }

//    fun signIn() {
//        val intent = mGoogleSignInClient.signInIntent
//        startActivityForResult(
//            intent,
//            AppUtils.RC_SIGN_IN
//        )
//    }

    fun toNoteListActivity() {
        val intent = NoteListActivity.newIntent(applicationContext)
        startActivity(intent)
    }

    fun toLoginActivity() {
        val intent = LoginActivity.newIntent(applicationContext)
        startActivity(intent)
    }

    fun toNoteDetailActivity() {
        val intent = NoteDetailActivity.newIntent(applicationContext)
        startActivity(intent)
    }

    fun toLoginProfileActivity() {
        val intent = LoginProfileActivity.newIntent(this)
        startActivity(intent)
    }

    fun toAddNoteActivity() {
        val intent = AddNoteActivity.newIntent(applicationContext)
        startActivity(intent)
    }


}