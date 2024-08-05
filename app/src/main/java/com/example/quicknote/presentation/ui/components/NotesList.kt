package com.example.quicknote.presentation.ui.components

import android.provider.ContactsContract
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quicknote.core.Priority
import com.example.quicknote.domain.model.Note
import com.example.quicknote.presentation.ui.state.NoteItemUiState
import com.example.quicknote.presentation.ui.theme.typography

@Composable
fun NotesList(
    items:List<NoteItemUiState>,
    onItemClicked :(NoteItemUiState)->Unit
){
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns =GridCells.Adaptive(150.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)


    ) {
        items (items){item ->
            NoteItem(
                item = item,
                onItemClicked=onItemClicked
            )
        }
    }

}


@Composable
fun NoteItem(
    item:NoteItemUiState,
    onItemClicked :(NoteItemUiState)->Unit
){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClicked(item)
            }
    ){
        Column (
            modifier = Modifier.padding(12.dp)
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = item.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f).padding(end = 16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                Canvas(
                    modifier = Modifier.size(20.dp),
                    onDraw = {
                        drawCircle(
                            color = when(item.priority){
                                Priority.LOW -> Color(0xFF11D99B)
                                Priority.MEDIUM ->Color(0xFFD9D92F)
                                Priority.HIGH -> Color(0xFFFF5151)
                            },
                        )

                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.description,
                maxLines =2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 16.dp),
                style = MaterialTheme.typography.bodyMedium
                )
            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text =item.createdAt,
                style = MaterialTheme.typography.labelMedium)


        }
    }

}

@Preview(showBackground = true)
@Composable
fun NotePr(){
    NoteItem(
        NoteItemUiState(1,"hello","diow wifeheow hf hf hwfo ihwf oeqh fhfowqhfhf ewqohfeoqhf ihqwf ","5 min ago",Priority.HIGH),{})
}

@Preview(showBackground = true)
@Composable
fun NotePre(){
    NotesList(
        listOf<NoteItemUiState>(
            NoteItemUiState(1,"helle fef ew fo","diow wifeheow hf hf hwfo ihwf oeqh fhfowqhfhf ewqohfeoqhf ihqwf ","5 min ago",Priority.HIGH),
            NoteItemUiState(1,"hello","diow wifeheow hf hf hwfo ihwf oeqh fhfowqhfhf ewqohfeoqhf ihqwf ","5 min ago",Priority.HIGH)
        ),{})


}