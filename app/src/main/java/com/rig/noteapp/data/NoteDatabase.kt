package com.rig.noteapp.data

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import com.rig.noteapp.model.Note

@Database(entities=[
    Note::class
],version = 1, exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao():NoteDatabaseDao
}