package com.example.quicknote.domain.repository

import androidx.lifecycle.LiveData
import com.example.quicknote.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotes(): Flow<List<Note>>
    suspend fun deleteNote(note:Note)
    suspend fun upsert(note:Note)



    fun searchNotes(query:String?):Flow<List<Note>>


}