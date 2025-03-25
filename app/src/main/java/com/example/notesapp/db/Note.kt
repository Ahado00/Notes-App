package com.example.notesapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey


//Task1: Create Entity class with proper annotations for your notes data
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)//so Room will generate the id for each row
    val id: Int = 0,

    val title: String,

    val content: String
)
