package hr.ferit.markobudimir.konto.ui.settings

data class SettingsUserDataViewState(
    val companyName: String,
    val address: String,
    val zipCode: String,
    val pin: String,
    val ownerName: String? = null,
)
