package com.example.quicknote.di

import com.example.quicknote.data.repository.NoteRepositoryImpl
import com.example.quicknote.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNewsRepository(newsRepositoryImpl: NoteRepositoryImpl): NoteRepository

}