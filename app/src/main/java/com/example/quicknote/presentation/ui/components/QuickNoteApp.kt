package com.example.quicknote.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quicknote.QuickNoteApp
import com.example.quicknote.R
import com.example.quicknote.presentation.ui.state.NoteItemUiState
import com.example.quicknote.presentation.ui.theme.QuickNoteTheme
import com.example.quicknote.presentation.viewmodel.NoteViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickNoteApp(
    viewModel: NoteViewModel = hiltViewModel()
){
    QuickNoteTheme {
        val sheetState = rememberModalBottomSheetState()
        var showBottomSheet by remember { mutableStateOf(false) }
        val uiState by viewModel.uiState.collectAsState()
        var selectedItem: NoteItemUiState? by remember {
            mutableStateOf(null)
        }

        var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
        var isSearchActive by remember { mutableStateOf(false) }

        if (!isSearchActive)
            viewModel.getAll()

        Scaffold (
            topBar = {

                TopAppBar(
                    title = {
                        if (isSearchActive) {
                            BasicTextField(
                                value = searchQuery,
                                onValueChange = {
                                    searchQuery = it
                                            viewModel.searchNotes(it.text)
                                                },
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                decorationBox = { innerTextField ->
                                    if (searchQuery.text.isEmpty()) {
                                        Text(
                                            text = "Search...",
                                        )
                                    }
                                    innerTextField()
                                }
                            )
                        } else {
                            Text("QuickNote")
                        }
                    },
                    navigationIcon = {
                        if (isSearchActive) {
                            IconButton(onClick = { isSearchActive = false }) {
                                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                            }
                        }
                    },
                    actions = {
                        if (isSearchActive) {
                            IconButton(onClick = { viewModel.searchNotes(searchQuery.text)}) {
                                Icon(Icons.Default.Search, contentDescription = "Search")
                            }
                        } else {
                            IconButton(onClick = { isSearchActive = true }) {
                                Icon(Icons.Default.Search, contentDescription = "Search")
                            }
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { showBottomSheet = true }) {
                    Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "Add note")
                }
            }
        ){innerPadding ->
            MainScreen(
                modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                notes = uiState.noteItems,
                onItemClicked = {
                    noteItem -> selectedItem=noteItem
                    showBottomSheet=true
                }
            )
            if (showBottomSheet)
                NoteBottomSheet(
                    onDismiss = {
                        showBottomSheet = false
                                selectedItem=null
                                },
                    sheetState = sheetState,
                    note = selectedItem,
                    onSave = {note -> viewModel.upsertNote(note)}
                )
        }

    }
}