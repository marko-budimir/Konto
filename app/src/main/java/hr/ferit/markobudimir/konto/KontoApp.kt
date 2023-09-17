package hr.ferit.markobudimir.konto

import android.app.Application
import hr.ferit.markobudimir.konto.data.di.dataModule
import hr.ferit.markobudimir.konto.data.di.firebaseAuthModule
import hr.ferit.markobudimir.konto.ui.login.di.loginModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KontoApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KontoApp)
            modules(
                dataModule,
                loginModule,
                firebaseAuthModule
            )
        }
    }
}
