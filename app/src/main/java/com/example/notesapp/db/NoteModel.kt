package com.example.notesapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey


//Task1: Create Entity class with proper annotations for your notes data
@Entity(tableName = "notes")
data class NoteModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String
)
