package com.example.firebase.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.example.firebase.R
import com.example.firebase.model.Model
import com.google.android.gms.auth.api.signin.GoogleSignIn

class LoginProfileActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(
                context,
                LoginProfileActivity::class.java
            )
        }
    }

    private val tvUserMail by lazy { findViewById<TextView>(R.id.tvMail) }
    private val tvName by lazy { findViewById<TextView>(R.id.tvName) }
    private val ivProfile by lazy { findViewById<ImageView>(R.id.ivProfile) }
    private val ivBack by lazy { findViewById<ImageView>(R.id.ivBackPress)}
    private val btnLogout by lazy { findViewById<CardView>(R.id.btnLogout)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_profile)

        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            tvUserMail.text = account.email
            tvName.text = account.displayName
            val photo = Glide.with(this).load(account.photoUrl).into(ivProfile)
            photo.view
            btnLogout.setOnClickListener {
                logout()
            }
        }

        ivBack.setOnClickListener {
            toNoteListActivity()
            finish()
        }

    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    private fun logout() {
        Model.getInstance().signOut()
        toLoginActivity()

    }




}