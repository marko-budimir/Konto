package hr.ferit.markobudimir.konto.ui.login.di

import hr.ferit.markobudimir.konto.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel { LoginViewModel(authRepository = get()) }
}
