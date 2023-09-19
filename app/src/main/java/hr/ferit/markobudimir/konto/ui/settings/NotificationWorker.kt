package hr.ferit.markobudimir.konto.ui.settings

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import hr.ferit.markobudimir.konto.CHANNEL_ID
import hr.ferit.markobudimir.konto.R

class NotificationWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result = try {
        showNotification()
        Log.d("NotificationWorker", "doWork: Notification sent")
        Result.success()
    } catch (e: Exception) {
        Log.d("NotificationWorker", "doWork: ${e.message}")
        Result.failure()
    }

    private fun showNotification() {
        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle("Konto")
            .setContentText("Podsjetnik za slanje računa")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        val notificationManager = NotificationManagerCompat.from(applicationContext)
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("NotificationWorker", "showNotification: No permission")
            throw Exception("No permission")
        }
        notificationManager.notify(1, notification)
    }
}
