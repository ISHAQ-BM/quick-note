package com.example.quicknote.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.quicknote.data.database.NoteDao
import com.example.quicknote.domain.model.Note
import com.example.quicknote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
):NoteRepository{
    override fun getAll() =noteDao.getAll()

    override fun getNote(id: Int) =noteDao.getNote(id).asLiveData()

    override suspend fun deleteNote(note: Note) {
        noteDao.delete(note)
    }

    override suspend fun addNote(note: Note) {
        noteDao.insert(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.update(note)
    }

    override fun searchNotes(query: String?) = noteDao.searchNotes(query).asLiveData()
}