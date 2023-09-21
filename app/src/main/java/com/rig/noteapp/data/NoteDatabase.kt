package com.rig.noteapp.data

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.rig.noteapp.model.Note
import com.rig.noteapp.util.DateConverter
import com.rig.noteapp.util.UUIDConverter

@Database(entities=[
    Note::class
],version = 1, exportSchema = false)
//@TypeConverters( UUIDConverter::class)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao():NoteDatabaseDao
}