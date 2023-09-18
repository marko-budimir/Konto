package hr.ferit.markobudimir.konto.model

data class Bill(
    val number: String = "",
    val issueDate: String = "",
    val dueDate: String = "",
    val deliveryDate: String = "",
    val amount: String = "",
)
