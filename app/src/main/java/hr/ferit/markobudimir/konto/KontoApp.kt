package hr.ferit.markobudimir.konto

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import hr.ferit.markobudimir.konto.data.di.dataModule
import hr.ferit.markobudimir.konto.data.di.firebaseModule
import hr.ferit.markobudimir.konto.ui.billdetails.di.billDetailsModule
import hr.ferit.markobudimir.konto.ui.companies.di.companiesModule
import hr.ferit.markobudimir.konto.ui.home.di.homeModule
import hr.ferit.markobudimir.konto.ui.login.di.loginModule
import hr.ferit.markobudimir.konto.ui.settings.di.settingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

const val CHANNEL_ID = "KontoApp"
const val CHANNEL_NAME = "Konto"
const val CHANNEL_DESCRIPTION = "KontoApp notification channel"

class KontoApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = CHANNEL_DESCRIPTION
            }
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        startKoin {
            androidContext(this@KontoApp)
            modules(
                dataModule,
                loginModule,
                firebaseModule,
                homeModule,
                companiesModule,
                settingModule,
                billDetailsModule
            )
        }
    }
}
