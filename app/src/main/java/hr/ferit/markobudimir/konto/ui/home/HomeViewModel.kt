package hr.ferit.markobudimir.konto.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.ferit.markobudimir.konto.data.repository.UserRepository
import hr.ferit.markobudimir.konto.ui.home.mapper.HomeMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userRepository: UserRepository,
    private val homeMapper: HomeMapper
) : ViewModel() {
    private val _homeUserDataViewState = MutableStateFlow(
        HomeUserDataViewState(
            companyName = "",
            revenues = "",
            expenses = "",
            profit = ""
        )
    )
    val homeUserDataViewState = _homeUserDataViewState.asStateFlow()

    init {
        getUserData()
    }

    private fun getUserData() {
        viewModelScope.launch {
            userRepository.getUserData().collect { user ->
                user?.let {
                    _homeUserDataViewState.value = homeMapper.toHomeUserDataViewState(it)
                }
            }
        }
    }
}
