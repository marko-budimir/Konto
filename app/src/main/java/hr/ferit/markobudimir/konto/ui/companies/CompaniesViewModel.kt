package hr.ferit.markobudimir.konto.ui.companies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.ferit.markobudimir.konto.data.repository.UserRepository
import hr.ferit.markobudimir.konto.ui.companies.mapper.CompaniesMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CompaniesViewModel(
    private val userRepository: UserRepository,
    private val companiesMapper: CompaniesMapper
) : ViewModel() {
    private val _companiesViewState = MutableStateFlow(
        CompaniesViewState(
            companies = listOf()
        )
    )
    val companiesViewState = _companiesViewState.asStateFlow()

    fun getCompanies(path: String) {
        viewModelScope.launch {
            userRepository.getCustomerObligations(path).collect { companies ->
                Log.d("CompaniesViewModel", "getCompanies: $companies")
                _companiesViewState.value = companiesMapper.toCompaniesViewState(companies)
            }
        }
    }
}
