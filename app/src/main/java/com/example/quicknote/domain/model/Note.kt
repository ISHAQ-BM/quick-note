package com.example.quicknote.domain.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quicknote.core.Priority
import com.example.quicknote.core.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val title: String,
    val description: String,
    val priority: Priority,
    @ColumnInfo(name = "created_at") val createdAt:String

    )