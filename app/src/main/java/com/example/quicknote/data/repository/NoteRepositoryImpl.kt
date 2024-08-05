package com.example.quicknote.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.quicknote.data.source.local.database.NoteDao
import com.example.quicknote.domain.model.Note
import com.example.quicknote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
):NoteRepository{
    override fun getAllNotes() = noteDao.getAllNotes()

    override suspend fun deleteNote(note: Note) {
        noteDao.delete(note)
    }
    override suspend fun upsert(note: Note) {
        noteDao.upsert(note)
    }

    override fun searchNotes(query: String?) = noteDao.searchNotes(query)
}