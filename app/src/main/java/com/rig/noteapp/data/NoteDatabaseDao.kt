package com.rig.noteapp.data

import androidx.compose.runtime.MutableState
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rig.noteapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {
    @Query("Select * from notes_tbl")
    fun getNotes():Flow<List<Note>>

    @Query("Select * from notes_tbl where id=:id")
    fun getNoteById(id:String):Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note:Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(note:Note)

    @Query("Delete from notes_tbl")
    fun deleteAll()

    @Delete
    fun deleteNote(note:Note)
}