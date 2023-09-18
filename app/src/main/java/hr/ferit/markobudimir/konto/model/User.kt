package hr.ferit.markobudimir.konto.model

data class User(
    val name: String,
    val revenues: String = "",
    val expenses: String = "",
    val profit: String = "",
    val owner: String = "",
    val address: String = "",
    val zipCode: String = "",
    val pin: String = "",
)
