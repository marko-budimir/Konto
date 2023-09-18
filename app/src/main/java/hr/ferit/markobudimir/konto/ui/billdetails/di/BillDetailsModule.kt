package hr.ferit.markobudimir.konto.ui.billdetails.di

import hr.ferit.markobudimir.konto.ui.billdetails.BillDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val billDetailsModule = module {
    viewModel { BillDetailsViewModel(billRepository = get()) }
}
