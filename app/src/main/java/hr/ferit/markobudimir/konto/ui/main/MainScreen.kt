package hr.ferit.markobudimir.konto.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hr.ferit.markobudimir.konto.mock.CompaniesMock
import hr.ferit.markobudimir.konto.ui.billdetails.BillDetailsScreen
import hr.ferit.markobudimir.konto.ui.companies.CompaniesScreen
import hr.ferit.markobudimir.konto.ui.companies.CompaniesViewState
import hr.ferit.markobudimir.konto.ui.home.HomeScreen
import hr.ferit.markobudimir.konto.ui.home.HomeUserDataViewState
import hr.ferit.markobudimir.konto.ui.login.LoginScreen
import hr.ferit.markobudimir.konto.navigation.COMPANY_ID_KEY
import hr.ferit.markobudimir.konto.navigation.CompaniesDestination
import hr.ferit.markobudimir.konto.navigation.LoginDestination
import hr.ferit.markobudimir.konto.navigation.NavigationItem
import hr.ferit.markobudimir.konto.ui.settings.SettingsScreen
import hr.ferit.markobudimir.konto.ui.settings.SettingsUserDataViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showBottomBar by remember {
        derivedStateOf {
            when (navBackStackEntry?.destination?.route) {
                CompaniesDestination.route -> false
                LoginDestination.route -> false
                else -> true
            }
        }
    }
    Scaffold(
        bottomBar = {
            if (showBottomBar)
                BottomNavigationBar(
                    destinations = listOf(
                        NavigationItem.HomeDestination,
                        NavigationItem.BillingDestination,
                        NavigationItem.SettingsDestination,
                    ),
                    onNavigateToDestination = { navigationItem ->
                        navController.navigate(navigationItem.route) {
                            popUpTo(NavigationItem.HomeDestination.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    currentDestination = navBackStackEntry?.destination
                )
        }
    ) { padding ->
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxWidth()
        ) {
            NavHost(
                navController = navController,
                startDestination = LoginDestination.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(LoginDestination.route) {
                    LoginScreen(onLoginButtonClick = {
                        navController.navigate(NavigationItem.HomeDestination.route) {
                            popUpTo(LoginDestination.route) {
                                saveState = true
                                inclusive = true
                            }
                        }
                    })
                }
                composable(NavigationItem.HomeDestination.route) {
                    HomeScreen(
                        viewState = HomeUserDataViewState(
                            "Vers-konto d.o.o",
                            "10.000,00€",
                            "5.000,00€",
                            "5.000,00€"
                        ),
                        onCustomerObligationsButtonClick = {
                            navController.navigate(CompaniesDestination.createNavigationRoute("Obveze kupaca"))
                        },
                        onDebtButtonClick = {
                            navController.navigate(CompaniesDestination.createNavigationRoute("Dug"))
                        },
                    )
                }
                composable(NavigationItem.BillingDestination.route) {
                    BillDetailsScreen({}, {})
                }
                composable(NavigationItem.SettingsDestination.route) {
                    SettingsScreen(
                        viewState = SettingsUserDataViewState(
                            companyName = "Vers-konto d.o.o.",
                            address = "Vukovarska ulica 123",
                            zipCode = "10000 Zagreb",
                            pin = "123456789",
                            ownerName = "Marko Budimir",
                        ),
                        onLogoutButtonClick = {
                            navController.navigate(LoginDestination.route) {
                                popUpTo(NavigationItem.HomeDestination.route) {
                                    saveState = true
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
                composable(
                    route = CompaniesDestination.route,
                    arguments = listOf(navArgument(COMPANY_ID_KEY) { type = NavType.StringType }),
                ) {
                    val companyTitle = it.arguments?.getString("companyId")
                    CompaniesScreen(
                        title = companyTitle ?: "",
                        viewState = CompaniesViewState(CompaniesMock.getCompanies())
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(
    destinations: List<NavigationItem>,
    onNavigateToDestination: (NavigationItem) -> Unit,
    currentDestination: NavDestination?,
) {
    NavigationBar {
        destinations.forEach { destination ->
            val selected = currentDestination?.route == destination.route
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (selected) {
                                destination.selectedIconId
                            } else {
                                destination.unselectedIconId
                            }
                        ),
                        contentDescription = destination.route,
                    )
                },
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.background,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                    indicatorColor = MaterialTheme.colorScheme.primary,
                ),
            )
        }
    }
}
