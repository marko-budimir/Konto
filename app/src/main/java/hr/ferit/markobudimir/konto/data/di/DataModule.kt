package hr.ferit.markobudimir.konto.data.di

import hr.ferit.markobudimir.konto.data.repository.AuthRepository
import hr.ferit.markobudimir.konto.data.repository.AuthRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<AuthRepository> { AuthRepositoryImpl(firebaseAuth = get()) }
}
