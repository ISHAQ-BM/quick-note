package com.example.quicknote.data.source.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.quicknote.core.TABLE_NAME
import com.example.quicknote.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllNotes(): Flow<List<Note>>


    @Query("SELECT * FROM $TABLE_NAME WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' ")
    fun searchNotes(query:String?): Flow<List<Note>>

    @Upsert()
    suspend fun upsert(note:Note)

    @Delete
    suspend fun delete(note:Note)
}