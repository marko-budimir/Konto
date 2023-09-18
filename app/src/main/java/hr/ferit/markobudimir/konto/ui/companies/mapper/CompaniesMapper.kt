package hr.ferit.markobudimir.konto.ui.companies.mapper

import hr.ferit.markobudimir.konto.model.Company
import hr.ferit.markobudimir.konto.ui.companies.CompaniesViewState

interface CompaniesMapper {
    fun toCompaniesViewState(companies: List<Company>): CompaniesViewState
}
