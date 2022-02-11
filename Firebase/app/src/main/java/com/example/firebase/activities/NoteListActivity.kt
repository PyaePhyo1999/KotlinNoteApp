package com.example.firebase.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.adapters.NoteListAdapter
import com.example.firebase.db.DatabaseProvider
import com.example.firebase.db.entities.NoteEntities
import com.example.firebase.delegates.OnTapNoteList
import com.example.firebase.model.Model
import com.google.android.material.floatingactionbutton.FloatingActionButton


class NoteListActivity : BaseActivity(), OnTapNoteList {


    private lateinit var mNoteListAdapter: NoteListAdapter
    private val rvNoteList by lazy { findViewById<RecyclerView>(R.id.rvNoteList) }
    private val fbAddNote by lazy { findViewById<FloatingActionButton>(R.id.fbAddNote) }
    private val ivProfile by lazy { findViewById<ImageView>(R.id.ivProfileActivity) }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, NoteListActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        try {

            mNoteListAdapter = NoteListAdapter(this)
            val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            rvNoteList.layoutManager = linearLayoutManager
            rvNoteList.adapter = mNoteListAdapter
            executeDb()

            fbAddNote.setOnClickListener {
                toAddNoteActivity()
            }
            ivProfile.setOnClickListener {
               toLoginProfileActivity()
            }


        } catch (ex: Exception) {
            Toast.makeText(applicationContext, ex.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    private fun executeDb() {
        executor.execute {
            val database = DatabaseProvider.instance(this)!!
            val dao = database.noteDao()
            val getData = dao.getNote()
            runOnUiThread {
                mNoteListAdapter.setItem(getData)
            }
        }
    }

    override fun onTapDelete(item: NoteEntities) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Delete Item")
            .setMessage("Are you sure to delete Items")
            .setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
            .setPositiveButton("Yes") { dialog, _ ->
                executor.execute {
                    DatabaseProvider.instance(this).noteDao().deleteNote(item)
                    executeDb()
                }

                dialog.dismiss()
            }.create()
        dialog.show()
    }


    override fun onTapItem(item: NoteEntities) {
       toNoteDetailActivity()
    }


}