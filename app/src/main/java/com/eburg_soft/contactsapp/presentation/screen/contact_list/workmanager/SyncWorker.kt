package com.eburg_soft.contactsapp.presentation.screen.contact_list.workmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.core.app.NotificationCompat.Builder
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.eburg_soft.contactsapp.R
import org.jetbrains.annotations.NotNull

const val IS_FIRST_TIME = "isFirstTime"
const val LAST_SYNC_TIME = "lastSyncTime"
const val MINUTE: Long = 60000L

class SyncWorker(@NotNull context: Context, @NotNull workerParams: WorkerParameters) :
    Worker(context, workerParams) {


    override fun doWork(): Result {
        var result: Result = Result.failure()
        result = try {
            val isFirstTime = inputData.getBoolean(IS_FIRST_TIME, true)
            val lastSyncTime = inputData.getLong(LAST_SYNC_TIME, 0L)
            val currentTime = System.currentTimeMillis()
            val timeDifference = currentTime - lastSyncTime

            if ((!isFirstTime && (timeDifference > MINUTE)) || isFirstTime) {
                //                displayNotification("Title", (timeDifference).toString())
                Result.success()
            } else Result.failure()
        } catch (e: Exception) {
            Result.failure()
        }

        return result

//        if ((!isFirstTime && (timeDifference > MINUTE)) || isFirstTime) {
//            result = try {
//                displayNotification("Title", (timeDifference).toString())
//                val output = Data.Builder()
//                    .putLong(LAST_SYNC_TIME, currentTime)
//                    .build()
//
//                Result.success()
//            } catch (e: Exception) {
//                Result.failure()
//            }
//        }
//        return result
    }

    private fun displayNotification(title: String, task: String) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (VERSION.SDK_INT >= VERSION_CODES.O) {
            val channel =
                NotificationChannel("id", "name", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        val notification: Builder = Builder(applicationContext, "id")
            .setContentTitle(title)
            .setContentText(task)
            .setSmallIcon(R.mipmap.ic_launcher)

        notificationManager.notify(1, notification.build())
    }
}