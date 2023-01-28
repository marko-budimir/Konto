package hr.ferit.markobudimir.konto.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hr.ferit.markobudimir.konto.R
import hr.ferit.markobudimir.konto.ui.theme.KontoTheme
import hr.ferit.markobudimir.konto.ui.theme.spacing

@Composable
fun SettingsScreen(
    viewState: SettingsUserDataViewState,
    onLogoutButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(MaterialTheme.spacing.small)

    ) {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        Text(
            text = viewState.companyName,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(start = MaterialTheme.spacing.small),
            color = MaterialTheme.colorScheme.onBackground
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(
                defaultElevation = dimensionResource(id = R.dimen.settings_card_elevation)
            )
        ) {
            Column(modifier = Modifier.padding(MaterialTheme.spacing.small)) {
                Text(
                    text = viewState.address,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = viewState.zipCode,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = stringResource(R.string.pin) + viewState.pin,
                    style = MaterialTheme.typography.titleSmall
                )
                viewState.ownerName?.let {
                    Text(
                        text = stringResource(R.string.owner) + viewState.ownerName,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Text(
            text = stringResource(R.string.settings),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(start = MaterialTheme.spacing.small),
            color = MaterialTheme.colorScheme.onBackground
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(
                defaultElevation = dimensionResource(id = R.dimen.settings_card_elevation)
            )
        ) {
            Box(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.dark_mode),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                var darkMode by remember { mutableStateOf(false) }
                Switch(
                    checked = darkMode,
                    onCheckedChange = { darkMode = !darkMode },
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
            Divider(color = MaterialTheme.colorScheme.outline)
            Box(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.notifications),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                var darkMode by remember { mutableStateOf(false) }
                Switch(
                    checked = darkMode,
                    onCheckedChange = { darkMode = !darkMode },
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
            Box(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.day_of_month),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                var date by remember { mutableStateOf("1") }
                Text(
                    text = date,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
            Box(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.time),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                var time by remember { mutableStateOf("18:00") }
                Text(
                    text = time,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

        Button(
            onClick = onLogoutButtonClick,
            modifier = modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.obligation_debt_button_height)),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            ),
            shape = MaterialTheme.shapes.large
        ) {
            Text(
                text = stringResource(R.string.logout),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    KontoTheme {
        SettingsScreen(
            viewState = SettingsUserDataViewState(
                companyName = "Vers-konto d.o.o.",
                address = "Vukovarska ulica 123",
                zipCode = "10000 Zagreb",
                pin = "123456789",
                ownerName = "Marko Budimir",
            ),
            onLogoutButtonClick = {}
        )
    }
}
