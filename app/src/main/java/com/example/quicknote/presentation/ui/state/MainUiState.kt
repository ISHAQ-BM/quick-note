package com.example.quicknote.presentation.ui.state

data class MainUiState(
    val noteItems:List<NoteItemUiState> = emptyList()
)
