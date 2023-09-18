package hr.ferit.markobudimir.konto

import android.app.Application
import hr.ferit.markobudimir.konto.data.di.dataModule
import hr.ferit.markobudimir.konto.data.di.firebaseModule
import hr.ferit.markobudimir.konto.ui.companies.di.companiesModule
import hr.ferit.markobudimir.konto.ui.home.di.homeModule
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
                firebaseModule,
                homeModule,
                companiesModule
            )
        }
    }
}
