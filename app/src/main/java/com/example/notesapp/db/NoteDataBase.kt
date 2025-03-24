package com.example.notesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Design an appropriate database schema for a note-taking application
@Database(entities = [NoteModel::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun notDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDataBase? = null

        fun getDataBase(context: Context): NoteDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDataBase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                instance
            }

        }
    }
}
