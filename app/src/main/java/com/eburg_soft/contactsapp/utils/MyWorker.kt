package com.eburg_soft.contactsapp.utils

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.jetbrains.annotations.NotNull

class TimeWorker(@NotNull context: Context, @NotNull workerParams: WorkerParameters) : Worker(context, workerParams) {

    val MINUTE: Long = 60000

    private var lastSyncTime: Int = 0

    override fun doWork(): Result {

        return try {
            var currentTime = System.currentTimeMillis()
            var timeDifference = currentTime - lastSyncTime
    //            var outputData =
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}