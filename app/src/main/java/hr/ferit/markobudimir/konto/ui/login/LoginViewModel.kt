package hr.ferit.markobudimir.konto.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.ferit.markobudimir.konto.data.repository.AuthRepository
import hr.ferit.markobudimir.konto.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    val _loginViewState = MutableStateFlow(LoginViewState())
    val loginViewState = _loginViewState.asStateFlow()

    fun login(email: String, password: String) = viewModelScope.launch {
        authRepository.login(email, password).collect { result ->
            when (result) {
                is Resource.Success ->
                    _loginViewState.value = LoginViewState(isSuccess = true)

                is Resource.Loading ->
                    _loginViewState.value = LoginViewState(isLoading = true)

                is Resource.Error ->
                    _loginViewState.value = LoginViewState(isError = result.message)
            }
        }
    }

    fun resetLoginViewState() {
        _loginViewState.value = LoginViewState()
    }
}
