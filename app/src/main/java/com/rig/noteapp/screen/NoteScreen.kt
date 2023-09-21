package com.rig.noteapp.screen

import android.os.Build
import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rig.noteapp.R
import com.rig.noteapp.components.NoteBtn
import com.rig.noteapp.components.NoteRow
import com.rig.noteapp.components.NoteTextFields
import com.rig.noteapp.data.NoteDataSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes:List<com.rig.noteapp.model.Note>,
    onAddNote:(com.rig.noteapp.model.Note)->Unit,
    onRemoveNote:(com.rig.noteapp.model.Note)->Unit,

){
    var title by remember{
        mutableStateOf("")
    }
    var description by remember{
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column(modifier=Modifier.padding(6.dp)) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "icon")
        },
            colors = TopAppBarDefaults.mediumTopAppBarColors(Color(0xFFDADFE3))
        )
        //Content
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteTextFields(text = title, label = "Title", modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp
            ),
                onTextChange = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() })
                        title = it
                })
            NoteTextFields(text = description, label = "Add a note", modifier = Modifier.padding(
                top = 9.dp,
                bottom = 8.dp
            ), onTextChange = {
                if (it.all { char ->
                        char.isLetter() || char.isWhitespace()
                    }) description = it
            })
            NoteBtn(text = "Save", onclick = {
                if(title.isNotEmpty() && description.isNotEmpty()){
                    onAddNote(
                        com.rig.noteapp.model.Note(title = title, description=description)
                    )
                    title=""
                    description = ""

                    Toast.makeText(context,title+description,Toast.LENGTH_SHORT).show()
                }
            })

            }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn{
            items(notes){
                    note-> NoteRow(note = note, onNoteClick = {
                        onRemoveNote(note)
            })
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen(NoteDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {} )
}