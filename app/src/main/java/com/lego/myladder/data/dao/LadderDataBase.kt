package com.lego.myladder.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lego.myladder.data.models.IntTypeConverter
import com.lego.myladder.data.models.Ladder

@Database(entities = [Ladder::class], version = 1)
@TypeConverters(IntTypeConverter::class)
abstract class LadderDataBase : RoomDatabase() {
    abstract fun ladderDao(): LadderDao
}