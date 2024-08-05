package com.example.quicknote.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknote.core.Priority
import com.example.quicknote.domain.model.Note
import com.example.quicknote.domain.repository.NoteRepository
import com.example.quicknote.presentation.ui.state.MainUiState
import com.example.quicknote.presentation.ui.state.NoteItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
):ViewModel() {


    private val _uiState = MutableStateFlow(MainUiState())
    val uiState :StateFlow<MainUiState> = _uiState

    init {
        viewModelScope.launch {
            getAll()
        }
    }


   fun getAll(){
        viewModelScope.launch {
            repository.getAllNotes().collect{ notes ->
                _uiState.update {
                    it.copy(
                        noteItems = notes.map { note ->
                            NoteItemUiState(
                                note.id,
                                note.title,
                                note.description,
                                note.createdAt,
                                note.priority
                            )
                        }
                    )
                }
            }


        }
    }
    fun deleteNote(note: Note)=viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun upsertNote(note:NoteItemUiState)=viewModelScope.launch {
        repository.upsert(Note(
            id = note.id,
            title = note.title,
            description = note.description,
            priority = note.priority,
            createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))


        ))
    }


    fun searchNotes(query:String?){
        viewModelScope.launch {
            repository.searchNotes(query).collect{ notes ->
                _uiState.update {
                    it.copy(
                        noteItems = notes.map { note ->
                            NoteItemUiState(
                                note.id,
                                note.title,
                                note.description,
                                note.createdAt,
                                note.priority
                            )
                        }
                    )
                }
            }
        }

    }



}