package hr.ferit.markobudimir.konto.data.di

import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val firebaseAuthModule = module {
    single { FirebaseAuth.getInstance() }
}
