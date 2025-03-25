package com.example.notesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// ** Design an appropriate database schema for a note-taking application **
@Database(entities = [Note::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {

//  create Dao Variable

    abstract val dao: NoteDao

}
