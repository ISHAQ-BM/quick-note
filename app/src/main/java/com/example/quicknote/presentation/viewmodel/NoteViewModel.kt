package com.example.quicknote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknote.domain.model.Note
import com.example.quicknote.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
):ViewModel() {



    fun getAll()=repository.getAll()
    fun addNote(note:Note)=viewModelScope.launch {
        repository.addNote(note)
    }

    fun updateNote(note:Note)=viewModelScope.launch {
        repository.updateNote(note)
    }

    fun getNote(id:Int)=repository.getNote(id)

    fun searchNotes(query:String?)= repository.searchNotes(query)


}