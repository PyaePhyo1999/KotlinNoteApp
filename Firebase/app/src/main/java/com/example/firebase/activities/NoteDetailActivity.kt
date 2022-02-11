package com.example.firebase.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.firebase.R

class NoteDetailActivity : BaseActivity(){

    companion object{
        fun newIntent(context : Context) : Intent{
            return Intent(context,NoteDetailActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)
    }
}