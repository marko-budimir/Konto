package hr.ferit.markobudimir.konto.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.ferit.markobudimir.konto.data.repository.AuthRepository
import hr.ferit.markobudimir.konto.data.repository.UserRepository
import hr.ferit.markobudimir.konto.ui.settings.mapper.SettingsMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val settingsMapper: SettingsMapper
) : ViewModel() {
    private val _settingsUserDataViewState = MutableStateFlow(
        SettingsUserDataViewState(
            companyName = "",
            address = "",
            zipCode = "",
            pin = "",
        )
    )
    val settingsUserDataViewState = _settingsUserDataViewState.asStateFlow()

    init {
        getUserData()
    }

    private fun getUserData() {
        viewModelScope.launch {
            userRepository.getUserData().collect { user ->
                user?.let {
                    _settingsUserDataViewState.value =
                        settingsMapper.toSettingsUserDataViewState(it)
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}
