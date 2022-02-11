package com.example.firebase.model

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.firebase.AppUtils
import com.example.firebase.activities.BaseActivity
import com.example.firebase.db.DatabaseProvider
import com.example.firebase.db.entities.NoteEntities
import com.example.firebase.delegates.SignInAccountDelegate
import com.example.firebase.model.vos.UserVO
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Model() {

    private var mFirebaseAuth: FirebaseAuth? = null
    private var mFirebaseUser: FirebaseUser? = null
    private  var databaseReference: DatabaseReference
    private var userVO: UserVO? = null

    init {
        mFirebaseAuth = FirebaseAuth.getInstance()
        mFirebaseUser = mFirebaseAuth!!.currentUser
        databaseReference = FirebaseDatabase.getInstance().reference.child(AppUtils.USER)

    }

    companion object {

        fun getInstance(): Model {
            return Model()

        }
    }

    fun authenticateUserWithGoogleAccount(
        account: GoogleSignInAccount,
        delegate: SignInAccountDelegate
    ) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        mFirebaseAuth!!.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                delegate.onSuccessSignIn(account)
            }
        }

    }

    fun isUserLogin(): Boolean {
        return mFirebaseUser != null
    }

    fun signOut() {
        mFirebaseAuth?.signOut()
    }

    fun addNewUser(id:String,name:String,email:String) {

       userVO = UserVO(id, name, email)
      databaseReference.push().setValue(userVO)

    }

     fun addNote(title: String , brief : String,context: Context) {

        val addData = NoteEntities(
            id = 0,
            title = title,
            brief = brief
        )
        BaseActivity.executor.execute{
            DatabaseProvider.instance(context).noteDao().insertNote(addData)
        }

    }



}