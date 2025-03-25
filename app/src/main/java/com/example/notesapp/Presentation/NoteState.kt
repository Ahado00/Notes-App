package com.example.notesapp.Presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.notesapp.db.Note

data class NoteState(

    // for notes list:
    val notes: List<Note> = emptyList(),

    // for the note's title
    val title: MutableState<String> = mutableStateOf(""),

    // for note's content
    val content: MutableState<String> = mutableStateOf("")
)