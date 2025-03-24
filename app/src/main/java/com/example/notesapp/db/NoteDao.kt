package com.example.notesapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    //Insert new notes into the database:
    @Insert
    suspend fun insert(noteModel: NoteModel)

    //Update existing note content
    @Update
    suspend fun update(noteModel: NoteModel)

    //Remove notes from the database
    @Delete
    suspend fun delete(noteModel: NoteModel)

    //Query all notes
    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<NoteModel>

    //Query individual notes by ID
    @Query("SELECT * FROM notes WHERE id = noteID")
    suspend fun getNoteById(noteID: Int):NoteModel?
}
