package com.offsyncstudios.actiontimetracker

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService

class ActionTimeTracker {

    private var startTime: Long = 0L
    private var endTime: Long = 0L

    fun startTimer() {
        startTime = System.currentTimeMillis()
    }

    fun stopTimer(context: Context, actionName: String) {
        endTime = System.currentTimeMillis()
        val executionTime = endTime - startTime
        println("$actionName Duration is: $executionTime")
        sendNotification(context, executionTime, actionName)
    }

    private fun sendNotification(context: Context, executionTime: Long, actionName: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = System.currentTimeMillis().toInt()

        val notificationBuilder = NotificationCompat.Builder(context, "ActionTimeTracker")
            .setSmallIcon(com.google.android.material.R.drawable.ic_clear_black_24)
            .setContentTitle("$actionName Duration")
            .setContentText("$executionTime milliseconds")
            .setAutoCancel(true)

        val channel = NotificationChannel(
            "ActionTimeTracker",
            "ActionTimeTracker",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    //Next update: to provide a method which takes a lambda as an argument,
    //executes the code within the lambda, and measures its execution time using System.currentTimeMillis()
    fun measureExecutionTime(actionName: String, block: () -> Unit) {
        val startTime = System.currentTimeMillis()
        block()
        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        println("$actionName Duration is: $duration")
    }
}