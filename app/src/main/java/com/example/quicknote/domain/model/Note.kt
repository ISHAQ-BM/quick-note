package com.example.quicknote.domain.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quicknote.core.TABLE_NAME
import java.sql.Timestamp

@Entity(tableName = TABLE_NAME)
data class Note (
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val title: String,
    val subTitle: String,
    val description: String,
    val priority: String,
    @ColumnInfo(name = "created_date") val createdDate:String

    )