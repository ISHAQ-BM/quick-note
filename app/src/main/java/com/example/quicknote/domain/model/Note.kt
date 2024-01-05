package com.example.quicknote.domain.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quicknote.core.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Note (
    @PrimaryKey val id: Int,
    val title: String,
    val subTitle: String,
    val description: String

    )