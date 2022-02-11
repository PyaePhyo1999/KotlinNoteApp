package com.example.firebase.db

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var database : AppDatabase? = null
    fun instance(context : Context) : AppDatabase {
        if (database ==null) {
            database = Room.databaseBuilder(context, AppDatabase::class.java, "note.db").build()
        }
        return database!!
    }
}