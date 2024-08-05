package com.example.quicknote.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quicknote.domain.model.Note

@Database(entities = [Note::class], version = 2, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao
}