package com.rig.noteapp.model

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID


@Entity(
    tableName = "notes_tbl",
)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:Int =0,
    @ColumnInfo(name="note_title")
    val title:String,
    @ColumnInfo(name="note_description")
    val description:String,
)
