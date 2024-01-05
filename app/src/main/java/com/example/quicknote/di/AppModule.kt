package com.example.quicknote.di

import android.content.Context
import androidx.room.Room
import com.example.quicknote.core.DATABASE_NAME
import com.example.quicknote.data.database.NoteDao
import com.example.quicknote.data.database.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDao(notesDatabase: NotesDatabase): NoteDao {
        return notesDatabase.noteDao()
    }

    @Provides
    @Singleton
    fun provideNotesDatabase(@ApplicationContext context: Context): NotesDatabase {
        return Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

}