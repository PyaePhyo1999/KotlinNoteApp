package com.example.firebase.db.daos

import androidx.room.*
import com.example.firebase.db.entities.NoteEntities


@Dao
interface NoteDao {
    @Query("SELECT * FROM noteList")
    fun getNote(): List<NoteEntities>

    @Insert
    fun insertNote(noteEntities: NoteEntities)

    @Delete
    fun deleteNote(noteEntities: NoteEntities)




}