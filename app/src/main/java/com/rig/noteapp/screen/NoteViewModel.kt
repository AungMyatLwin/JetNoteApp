package com.rig.noteapp.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rig.noteapp.data.NoteDataSource
import com.rig.noteapp.model.Note
import com.rig.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
):ViewModel() {
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.getAllNote().distinctUntilChanged()
                .collect{
                    listOfNotes->
                    if(listOfNotes.isNullOrEmpty()){
                        Log.d("Empty", "FFFF :empty list ")
                    }
                    else{
                        _noteList.value=listOfNotes
                    }
                }
        }
    }
    suspend fun addNotes(note:Note) = viewModelScope.launch {
        noteRepository.addNote(note)
    }
    suspend fun updateNote(note:Note) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    suspend fun removeNote(note:Note) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

}