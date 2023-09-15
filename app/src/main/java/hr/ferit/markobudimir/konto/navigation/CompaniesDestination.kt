package hr.ferit.markobudimir.konto.navigation

const val COMPANIES_ROUTE = "Companies"
const val COMPANY_ID_KEY = "companyId"
const val COMPANIES_ROUTE_WITH_PARAMS = "$COMPANIES_ROUTE/{$COMPANY_ID_KEY}"

object CompaniesDestination : KontoDestination(
    route = COMPANIES_ROUTE_WITH_PARAMS
) {
    fun createNavigationRoute(companyId: String): String = "$COMPANIES_ROUTE/$companyId"
}
