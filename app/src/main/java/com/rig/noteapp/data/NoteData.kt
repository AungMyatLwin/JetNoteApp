package com.rig.noteapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.rig.noteapp.model.Note

class NoteDataSource{
    @RequiresApi(Build.VERSION_CODES.O)
    fun loadNotes():List<Note>{
        return listOf(
            Note(title = "Bonita", description = "Do you? or Do you not feel Bonita?"),
            Note(title="Compose", description = "Working on Android Compose"),
            Note(title="A movie day", description = "Watching a movie with Myself")
        )
    }
}