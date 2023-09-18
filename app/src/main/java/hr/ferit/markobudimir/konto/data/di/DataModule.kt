package hr.ferit.markobudimir.konto.data.di

import hr.ferit.markobudimir.konto.data.repository.AuthRepository
import hr.ferit.markobudimir.konto.data.repository.AuthRepositoryImpl
import hr.ferit.markobudimir.konto.data.repository.BillRepository
import hr.ferit.markobudimir.konto.data.repository.BillRepositoryImpl
import hr.ferit.markobudimir.konto.data.repository.UserRepository
import hr.ferit.markobudimir.konto.data.repository.UserRepositoryImpl
import org.koin.dsl.module


val dataModule = module {
    single<AuthRepository> { AuthRepositoryImpl(firebaseAuth = get()) }
    single<UserRepository> {
        UserRepositoryImpl(
            firebaseAuth = get(),
            databaseReference = get(),
        )
    }
    single<BillRepository> {
        BillRepositoryImpl(
            firebaseAuth = get(),
            databaseReference = get(),
        )
    }
}
