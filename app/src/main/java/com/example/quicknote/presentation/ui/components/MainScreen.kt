package com.example.quicknote.presentation.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quicknote.presentation.ui.state.NoteItemUiState
import com.example.quicknote.presentation.viewmodel.NoteViewModel


@Composable
fun MainScreen(
    notes:List<NoteItemUiState>,
    modifier: Modifier=Modifier,
    onItemClicked:(NoteItemUiState)->Unit
){
            Box (
                modifier = modifier.padding(horizontal = 16.dp)

            ){
                NotesList(items =notes, onItemClicked = onItemClicked )
            }


}