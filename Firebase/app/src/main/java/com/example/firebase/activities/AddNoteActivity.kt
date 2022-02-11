package com.example.firebase.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import com.example.firebase.db.DatabaseProvider
import com.example.firebase.db.entities.NoteEntities
import com.example.firebase.R
import com.example.firebase.model.Model

class AddNoteActivity : BaseActivity() {


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AddNoteActivity::class.java)
        }
    }
    private val etTitle by lazy {
        findViewById<EditText>(R.id.etTitle)
    }
    private val etBrief by lazy {
        findViewById<EditText>(R.id.etBrief)
    }
    private val ivSave by lazy{
        findViewById<ImageView>(R.id.ivSave)
    }
    private val ivBackPress by lazy {
         findViewById<ImageView>(R.id.ivBackPress)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)


            ivBackPress.setOnClickListener {
                toNoteListActivity()
                finish()
            }


        ivSave.setOnClickListener {
            val title = etTitle.text.toString()
            val brief = etBrief.text.toString()
            Model.getInstance().addNote(title,brief,this)
            startActivity(Intent(this, NoteListActivity::class.java))
        }

    }

    override fun onStop() {
        super.onStop()
        finish()
    }

//    private fun addNote(title: String , brief : String) {
//
//        val title = etTitle.text.toString()
//        val brief = etBrief.text.toString()
//
//        val addData = NoteEntities(
//            id = 0,
//            title = title,
//            brief = brief
//        )
//        executor.execute{
//            DatabaseProvider.instance(this).noteDao().insertNote(addData)
//        }
//
//        startActivity(Intent(this, NoteListActivity::class.java))
//
//    }


}