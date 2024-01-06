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


    fun addNote(note:Note)=viewModelScope.launch {
        repository.addNote(note)
    }

    fun getNote(id:Int)=repository.getNote(id)
}