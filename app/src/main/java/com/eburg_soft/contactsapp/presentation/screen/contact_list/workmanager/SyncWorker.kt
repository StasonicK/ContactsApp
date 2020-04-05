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

class SyncWorker(@NotNull context: Context, @NotNull workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {

        return try {

            displayNotification("Title", "Message1")
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
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