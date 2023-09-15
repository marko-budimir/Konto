package hr.ferit.markobudimir.konto.navigation

import hr.ferit.markobudimir.konto.R

const val HOME_ROUTE = "Home"
const val BILLING_ROUTE = "Billing"
const val SETTINGS_ROUTE = "Settings"

sealed class NavigationItem(
    override val route: String,
    val selectedIconId: Int,
    val unselectedIconId: Int,
) : KontoDestination(route) {
    object HomeDestination : NavigationItem(
        route = HOME_ROUTE,
        selectedIconId = R.drawable.ic_home_filled,
        unselectedIconId = R.drawable.ic_home_outlined,
    )

    object BillingDestination : NavigationItem(
        route = BILLING_ROUTE,
        selectedIconId = R.drawable.ic_billing_filled,
        unselectedIconId = R.drawable.ic_billing_outlined,
    )

    object SettingsDestination : NavigationItem(
        route = SETTINGS_ROUTE,
        selectedIconId = R.drawable.ic_settings_filled,
        unselectedIconId = R.drawable.ic_settings_outlined,
    )
}
