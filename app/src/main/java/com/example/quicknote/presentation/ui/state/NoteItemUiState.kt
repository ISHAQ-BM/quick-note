package com.example.quicknote.presentation.ui.state

import com.example.quicknote.core.Priority

data class NoteItemUiState (
    val id:Int,
    val title:String,
    val description:String,
    val createdAt:String,
    val priority:Priority
)