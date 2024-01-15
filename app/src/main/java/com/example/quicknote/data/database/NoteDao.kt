package com.example.quicknote.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.quicknote.core.TABLE_NAME
import com.example.quicknote.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): Flow<List<Note>>

    @Query("SELECT * from $TABLE_NAME WHERE id = :id")
    fun getNote(id: Int): Flow<Note>

    @Query("SELECT * FROM $TABLE_NAME WHERE title LIKE '%' || :query || '%' OR subTitle LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' ")
    fun searchNotes(query:String?): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note:Note)

    @Update
    suspend fun update(note:Note)

    @Delete
    suspend fun delete(note:Note)
}