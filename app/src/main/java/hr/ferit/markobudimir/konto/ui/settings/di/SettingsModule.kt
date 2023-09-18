package hr.ferit.markobudimir.konto.ui.settings.di


import hr.ferit.markobudimir.konto.ui.settings.SettingsViewModel
import hr.ferit.markobudimir.konto.ui.settings.mapper.SettingsMapper
import hr.ferit.markobudimir.konto.ui.settings.mapper.SettingsMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingModule = module {
    viewModel { SettingsViewModel(get(), get(), get()) }
    single<SettingsMapper> { SettingsMapperImpl() }
}
