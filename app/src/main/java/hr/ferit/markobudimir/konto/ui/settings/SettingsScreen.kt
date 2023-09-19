package hr.ferit.markobudimir.konto.ui.settings

import android.app.TimePickerDialog
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hr.ferit.markobudimir.konto.R
import hr.ferit.markobudimir.konto.ui.theme.KontoTheme
import hr.ferit.markobudimir.konto.ui.theme.spacing
import java.util.Calendar

@Composable
fun SettingsRoute(
    viewModel: SettingsViewModel,
    onNavigateToLogin: () -> Unit
) {
    val viewState: SettingsUserDataViewState by viewModel.settingsUserDataViewState.collectAsState()
    SettingsScreen(
        viewState = viewState,
        onLogoutButtonClick = { viewModel.logout(); onNavigateToLogin() },
        addNotification = { day, hour, minute ->
            viewModel.scheduleNotification(
                day,
                hour,
                minute
            )
        },
        removeNotification = { viewModel.cancelNotification() }
    )
}

@Composable
fun SettingsScreen(
    viewState: SettingsUserDataViewState,
    onLogoutButtonClick: () -> Unit,
    addNotification: (Int, Int, Int) -> Unit,
    removeNotification: () -> Unit,
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
            var time by remember { mutableStateOf("00:00") }
            var isNotificationActive by remember { mutableStateOf(false) }
            var hasNotificationPermission by remember { mutableStateOf(false) }
            val toast = Toast.makeText(
                LocalContext.current,
                "Permission denied, you must allow notification permission",
                Toast.LENGTH_SHORT
            )
            val premissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission(),
                onResult = { isGranted ->
                    hasNotificationPermission = isGranted
                    if (!isGranted) {
                        toast.show()
                    }
                }
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                premissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            } else
                hasNotificationPermission = true
            var expanded by remember { mutableStateOf(false) }
            var selectedNumber by remember { mutableStateOf(1) }
            var numberText by remember(selectedNumber) { mutableStateOf(selectedNumber.toString()) }
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
                Switch(
                    checked = isNotificationActive,
                    onCheckedChange = { isChecked ->
                        isNotificationActive = isChecked
                        if (isChecked) {
                            val day = numberText.toInt()
                            val hour = time.split(":")[0].toInt()
                            val minute = time.split(":")[1].toInt()
                            addNotification(day, hour, minute)
                        } else {
                            removeNotification()
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterEnd),
                    enabled = hasNotificationPermission
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
                Text(
                    text = numberText,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable { if (!isNotificationActive) expanded = true }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    for (i in 1..28) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = i.toString(),
                                    style = MaterialTheme.typography.labelLarge,
                                )
                            },
                            onClick = {
                                selectedNumber = i
                                numberText = i.toString()
                                expanded = false
                            },
                            colors = MenuDefaults.itemColors(
                                textColor = MaterialTheme.colorScheme.onBackground
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

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

                InputTime(
                    isNotificationActive = isNotificationActive,
                    onValueChange = { time = it },
                    Modifier.align(Alignment.CenterEnd)
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

@Composable
fun InputTime(
    isNotificationActive: Boolean,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val hour: Int
    val minute: Int

    val calendar = Calendar.getInstance()
    hour = calendar.get(Calendar.HOUR_OF_DAY)
    minute = calendar.get(Calendar.MINUTE)

    val time = remember { mutableStateOf("00:00") }
    onValueChange(time.value)

    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
        { _, h: Int, m: Int ->
            time.value = "$h:$m"
        }, hour, minute, true
    )

    Text(
        text = time.value,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier.clickable { if (!isNotificationActive) timePickerDialog.show() }
    )
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
            onLogoutButtonClick = {},
            addNotification = { _, _, _ -> },
            removeNotification = {}
        )
    }
}
