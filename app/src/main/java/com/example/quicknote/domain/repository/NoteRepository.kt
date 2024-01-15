package com.example.quicknote.domain.repository

import androidx.lifecycle.LiveData
import com.example.quicknote.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAll(): Flow<List<Note>>
    fun getNote(id:Int):LiveData<Note>
    suspend fun deleteNote(note:Note)
    suspend fun addNote(note:Note)
    suspend fun updateNote(note:Note)

    fun searchNotes(query:String?):LiveData<List<Note>>


}