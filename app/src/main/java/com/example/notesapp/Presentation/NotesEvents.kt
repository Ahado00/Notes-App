package com.example.notesapp.Presentation

import com.example.notesapp.db.Note


//  create events/actions user
sealed interface NotesEvents {

    data class SaveNote(
        val title: String,
        val description: String
    ) : NotesEvents

    data class UpdateNote(val note: Note) : NotesEvents

    data class DeleteNote(val note: Note) : NotesEvents

}