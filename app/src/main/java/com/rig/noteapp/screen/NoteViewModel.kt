package com.rig.noteapp.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.rig.noteapp.data.NoteDataSource
import com.rig.noteapp.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor():ViewModel() {
    var noteList = mutableStateListOf<Note>()
    init {
        noteList.addAll(NoteDataSource().loadNotes())
    }
    fun addNotes(note:Note){
        noteList.add(note)
    }

    fun removeNotes(note:Note){
        noteList.remove(note)
    }

    fun getAllNotes():List<Note>{
        return noteList
    }
}