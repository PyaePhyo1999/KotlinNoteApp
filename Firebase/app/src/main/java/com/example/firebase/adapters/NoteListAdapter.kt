package com.example.firebase.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.viewholders.NoteListItems
import com.example.firebase.db.entities.NoteEntities

import com.example.firebase.R
import com.example.firebase.delegates.OnTapNoteList

class NoteListAdapter(var mDelegate: OnTapNoteList) : RecyclerView.Adapter<NoteListItems>() {


    private var noteList = listOf<NoteEntities>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListItems {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_note_list, parent, false)
        val holder = NoteListItems(view)

        holder.ivDelete.setOnClickListener {
            val position = holder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val itemDel = noteList[position]
                mDelegate.onTapDelete(itemDel)
            }
        }
        return holder
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun setItem(note: List<NoteEntities>) {
        noteList = note
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NoteListItems, position: Int) {
        val item = noteList[position]
        holder.tvTitle.text = item.title
        holder.tvBrief.text = item.brief
        holder.itemView.setOnClickListener {
            mDelegate.onTapItem(item)
        }

    }


}