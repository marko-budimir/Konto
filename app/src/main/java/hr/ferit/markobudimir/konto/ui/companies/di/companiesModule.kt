package hr.ferit.markobudimir.konto.ui.companies.di

import hr.ferit.markobudimir.konto.ui.companies.CompaniesViewModel
import hr.ferit.markobudimir.konto.ui.companies.mapper.CompaniesMapper
import hr.ferit.markobudimir.konto.ui.companies.mapper.CompaniesMapperImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val companiesModule = module {
    viewModel { CompaniesViewModel(userRepository = get(), companiesMapper = get()) }
    single<CompaniesMapper> { CompaniesMapperImpl() }
}
