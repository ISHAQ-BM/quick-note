package com.example.quicknote.presentation.ui.components

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quicknote.R
import com.example.quicknote.core.Priority
import com.example.quicknote.presentation.ui.state.NoteItemUiState
import com.example.quicknote.presentation.ui.theme.QuickNoteTheme


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteBottomSheet(
    onDismiss: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
    note:NoteItemUiState?=null,
    onSave:(NoteItemUiState)->Unit
){

    var title by remember { mutableStateOf(note?.title ?: "") }
    var notes by remember { mutableStateOf(note?.description ?: "") }
    var selectedPriority by remember {mutableStateOf(note?.priority ?: Priority.LOW) }

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(stringResource(id = R.string.title)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(id = R.string.priority))
                Spacer(modifier = Modifier.width(12.dp))

                Priority(
                    isChecked = selectedPriority == Priority.LOW
                    ,{
                        selectedPriority=Priority.LOW
                    },
                    Priority.LOW
                )
                Spacer(modifier = Modifier.width(24.dp))

                Priority(
                    isChecked = selectedPriority == Priority.MEDIUM
                    ,{
                        selectedPriority=Priority.MEDIUM
                    },
                    Priority.MEDIUM

                )
                Spacer(modifier = Modifier.width(24.dp))

                Priority(
                    isChecked = selectedPriority == Priority.HIGH
                    ,{
                        selectedPriority=Priority.HIGH
                    },
                    Priority.HIGH

                )
            }


            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text(stringResource(id = R.string.notes))  },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 300.dp)

            )

            Button(
                onClick = {
                    onSave(
                        NoteItemUiState(
                            id = 0,
                            title=title,
                            description = notes,
                            priority = selectedPriority,
                            createdAt = "")
                    )
                    onDismiss()
                          },
                modifier = Modifier.fillMaxWidth(),
                enabled = title != note?.title || notes != note?.description || selectedPriority != note?.priority
            ) {
                Text(text = stringResource(R.string.save))
            }
        }
    }


    }

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun hh(){
    QuickNoteTheme {
        var title by remember { mutableStateOf("") }
        var notes by remember { mutableStateOf("") }
        var selectedPriority by remember { mutableStateOf(Priority.LOW) }

        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(stringResource(id = R.string.title)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(id = R.string.priority))
                Spacer(modifier = Modifier.width(12.dp))

                Priority(
                    isChecked = selectedPriority == Priority.LOW
                    ,{
                        selectedPriority=Priority.LOW
                    },
                    Priority.LOW
                )
                Spacer(modifier = Modifier.width(24.dp))

                Priority(
                    isChecked = selectedPriority == Priority.MEDIUM
                    ,{
                        selectedPriority=Priority.MEDIUM
                    },
                    Priority.MEDIUM

                )
                Spacer(modifier = Modifier.width(24.dp))

                Priority(
                    isChecked = selectedPriority == Priority.HIGH
                    ,{
                        selectedPriority=Priority.HIGH
                    },
                    Priority.HIGH

                )
                }


            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text(stringResource(id = R.string.notes))  },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 300.dp)

            )

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.save))
            }
        }
    }
}

@Composable
fun Priority(

    isChecked:Boolean,
    onClick :()->Unit,
    priority: Priority
){
    Box(
        contentAlignment = Alignment.Center,
    ){
        Canvas(

            onDraw = {
                drawCircle(
                    color = when(priority){
                        Priority.LOW -> Color(0xFF11D99B)
                        Priority.MEDIUM ->Color(0xFFD9D92F)
                        Priority.HIGH -> Color(0xFFFF5151)
                    },
                )

            },
            modifier = Modifier.size(32.dp).clickable {
                onClick()
            }
        )
        if (isChecked)
            Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "contentDescription",
                tint = Color.Black
        )
    }
}

