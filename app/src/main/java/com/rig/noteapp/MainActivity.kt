package com.rig.noteapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rig.noteapp.data.NoteDataSource
import com.rig.noteapp.screen.NoteScreen
import com.rig.noteapp.screen.NoteViewModel
import com.rig.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val notes= remember{
                        mutableStateListOf<com.rig.noteapp.model.Note>()
                    }
//                    val viewModel:NoteViewModel by viewModels()
                    val viewModel = viewModel<NoteViewModel>()
                    NoteScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun NoteScreen(noteViewModel: NoteViewModel ){
    val notes = noteViewModel.noteList.collectAsState().value
    NoteScreen(notes=notes,
        onAddNote = {
            noteViewModel.addNotes(it)
        }, onRemoveNote = {
            noteViewModel.removeNote(it)
        })
}

@Preview()
@Composable
fun PreviewNotes(){
    NoteScreen(notes = NoteDataSource().loadNotes(), onAddNote = {}, onRemoveNote ={} )
}
