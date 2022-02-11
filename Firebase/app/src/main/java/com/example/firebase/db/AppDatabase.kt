 package com.example.firebase.db
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.firebase.db.daos.NoteDao
import com.example.firebase.db.entities.NoteEntities

@Database(entities = [NoteEntities::class],version =1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun noteDao(): NoteDao
}