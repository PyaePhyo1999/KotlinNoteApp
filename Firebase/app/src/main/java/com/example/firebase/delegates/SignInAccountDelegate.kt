package com.example.firebase.delegates

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface SignInAccountDelegate{
    fun onSuccessSignIn(account : GoogleSignInAccount)
    fun onFailureSignIn()
}
