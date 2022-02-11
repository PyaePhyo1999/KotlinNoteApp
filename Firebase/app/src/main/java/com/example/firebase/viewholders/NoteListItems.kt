package com.example.firebase.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.db.entities.NoteEntities
import com.example.firebase.delegates.OnTapNoteList

import org.w3c.dom.Text

class NoteListItems(itemView: View) : RecyclerView.ViewHolder(itemView){
    val tvTitle : TextView =itemView.findViewById(R.id.tvNoteTitle)
    val tvBrief : TextView = itemView.findViewById(R.id.tvBrief)
    val ivDelete :ImageView = itemView.findViewById(R.id.ivDeleteItem)
}