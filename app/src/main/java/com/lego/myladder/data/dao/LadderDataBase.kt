package com.lego.myladder.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.lego.myladder.DatabaseWorker
import com.lego.myladder.data.models.IntTypeConverter
import com.lego.myladder.data.models.Ladder
import com.lego.myladder.domain.DATA_BASE_NAME

@Database(entities = [Ladder::class], version = 1)
@TypeConverters(IntTypeConverter::class)
abstract class LadderDataBase : RoomDatabase() {

    abstract fun ladderDao(): LadderDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: LadderDataBase? = null

        fun getInstance(context: Context): LadderDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): LadderDataBase {
            return Room.databaseBuilder(context, LadderDataBase::class.java, DATA_BASE_NAME)
                .addCallback(
                    object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<DatabaseWorker>()
                                .build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }

}