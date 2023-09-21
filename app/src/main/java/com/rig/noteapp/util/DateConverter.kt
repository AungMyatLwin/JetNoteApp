package com.rig.noteapp.util

import androidx.room.TypeConverter
import java.sql.Timestamp
import java.util.Date

class DateConverter {
    @TypeConverter
    fun timeStampsFromDate(date:Date):Long{
        return date.time
    }

    @TypeConverter
    fun dateTimeStamp(timestamp: Long):Date?{
        return Date(timestamp)
    }
}