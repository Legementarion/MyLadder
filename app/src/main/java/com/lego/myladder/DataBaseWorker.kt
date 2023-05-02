package com.lego.myladder

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.lego.myladder.data.dao.LadderDataBase
import com.lego.myladder.data.models.Ladder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val database = LadderDataBase.getInstance(applicationContext)
            database.ladderDao().insertList(Ladder.populateData())
            Result.success()
        } catch (ex: Exception) {
            Result.failure()
        }
    }
}