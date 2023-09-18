package hr.ferit.markobudimir.konto.ui.companies.mapper

import hr.ferit.markobudimir.konto.model.Company
import hr.ferit.markobudimir.konto.ui.companies.CompaniesViewState
import hr.ferit.markobudimir.konto.ui.component.CompanyItemViewState

class CompaniesMapperImpl : CompaniesMapper {
    override fun toCompaniesViewState(companies: List<Company>): CompaniesViewState {
        return CompaniesViewState(
            companies = companies.map {
                CompanyItemViewState(
                    id = it.hashCode(),
                    name = it.name,
                    value = it.debt,
                )
            }
        )
    }
}
