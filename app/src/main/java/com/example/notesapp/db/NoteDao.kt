package com.example.notesapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {//a Note data access object

    //Insert new notes into the database:
    @Insert
    suspend fun insert(note: Note)

    //Update existing note content
    @Update
    suspend fun update(note: Note)

    // @Upsert can do both functions in one annotation

    //Remove notes from the database
    @Delete
    suspend fun delete(note: Note)

    //Query all notes
    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<Note>>

    //Query individual notes by ID
    @Query("SELECT * FROM notes WHERE id = :noteID")
    suspend fun getNoteById(noteID: Int):Note?
}
