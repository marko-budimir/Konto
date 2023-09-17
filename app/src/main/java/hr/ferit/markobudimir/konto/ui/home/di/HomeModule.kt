package hr.ferit.markobudimir.konto.ui.home.di

import hr.ferit.markobudimir.konto.ui.home.HomeViewModel
import hr.ferit.markobudimir.konto.ui.home.mapper.HomeMapper
import hr.ferit.markobudimir.konto.ui.home.mapper.HomeMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(userRepository = get(), homeMapper =  get()) }
    single<HomeMapper> { HomeMapperImpl() }
}
