package com.example.notesapp.Presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.db.Note
import com.example.notesapp.db.NoteDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


class NotesVM(private val dao: NoteDao) : ViewModel() {

    //all actions are here

    private var notes = dao.getAllNotes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    private val _state = MutableStateFlow(NoteState())

    val state = combine(
        _state, notes
    ) { state, notes ->
        state.copy(notes = notes) // to hold any changes on notes/_state and update it on state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteState()) //


    //Events function
    fun onEvent(event: NotesEvents) {

        when (event) {
            is NotesEvents.SaveNote -> { // to save note
                val note = Note(
                    title = state.value.title.value, // set note title
                    content = state.value.content.value // set not content
                )

                // insert the note
                viewModelScope.launch {
                    dao.insert(note)
                }

                _state.update {
                    it.copy(
                        title = mutableStateOf(""),
                        content = mutableStateOf("")
                    )
                }
            }

            is NotesEvents.UpdateNote -> {//to update note
                viewModelScope.launch {
                    dao.update(event.note)
                }
            }

            is NotesEvents.DeleteNote -> {//to delete note
                //call Delete Dao function:
                viewModelScope.launch {
                    dao.delete(event.note)
                }
            }
        }


    }


}