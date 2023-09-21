package com.rig.noteapp.repository

import com.rig.noteapp.data.NoteDatabaseDao
import com.rig.noteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository@Inject constructor(
    private  val dao:NoteDatabaseDao) {
    suspend fun addNote(note:Note) = dao.insert(note)

    suspend fun updateNote(note:Note) = dao.update(note)
    suspend fun deleteNote(note: Note) = dao.deleteNote(note)
    suspend fun deleteAllNote()= dao.deleteAll()
    suspend fun getAllNote():Flow<List<Note>> =
        dao.getNotes().flowOn(Dispatchers.IO).conflate()
}