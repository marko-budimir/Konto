package hr.ferit.markobudimir.konto.ui.login

data class LoginViewState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: String? = ""
)
