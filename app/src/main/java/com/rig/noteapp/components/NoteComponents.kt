package com.rig.noteapp.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.rig.noteapp.model.Note
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NoteTextFields(
    modifier:Modifier,
    text:String,
    maxline:Int=1,
    label:String,
    onTextChange:(String)->Unit={},
    onImeAction: ()->Unit = {
    }
){
        val keyboardController = LocalSoftwareKeyboardController.current
    TextField(value = text, onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(Color.DarkGray),
        maxLines = maxline,
        label = { Text(text = label)},
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        },),
        modifier=modifier
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteRow(
    modifier:Modifier=Modifier,
    note:Note,
    onNoteClick:(Note)->Unit){
    Surface(modifier = Modifier
        .padding(4.dp)
        .clip(shape = RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
        .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        tonalElevation = 6.dp,
        shadowElevation = 6.dp
    ) {
        Column(modifier=Modifier.clickable {
            onNoteClick(note)
        }.padding(horizontal = 14.dp, vertical= 6.dp),
            horizontalAlignment = Alignment.Start) {
            Text(note.title, style = MaterialTheme.typography.labelSmall)
            Text(text = note.description, style = MaterialTheme.typography.labelMedium)
            Text(text = note.entryData.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                style = MaterialTheme.typography.labelSmall)
        }
    }
}
@Composable
fun NoteBtn(
    modifier:Modifier = Modifier,
    text:String,
    onclick:()->Unit,
    enable:Boolean=true,
){
//    Button(onClick = { onclick.invoke() })
    Button(onClick = onclick,
        shape = CircleShape,
        enabled = enable,
        modifier = modifier){
        Text(text=text)
    }
}