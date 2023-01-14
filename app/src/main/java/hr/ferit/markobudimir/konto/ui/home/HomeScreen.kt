package hr.ferit.markobudimir.konto.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hr.ferit.markobudimir.konto.R
import hr.ferit.markobudimir.konto.ui.theme.KontoTheme
import hr.ferit.markobudimir.konto.ui.theme.spacing

const val NEGATIVE_BALANCE_TAG = "-"
const val WIDTH_FACTOR = 0.9f

@Composable
fun HomeScreen(
    viewState: HomeUserDataViewState,
    onCustomerObligationsButtonClick: () -> Unit,
    onDebtButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = MaterialTheme.spacing.medium)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            modifier = modifier
                .fillMaxWidth(WIDTH_FACTOR)
                .padding(top = MaterialTheme.spacing.medium)
        ) {
            Text(
                text = viewState.companyName,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
            ProfitsCard(
                title = stringResource(id = R.string.revenues),
                value = viewState.revenues,
                color = colorResource(id = R.color.revenues)
            )
            ProfitsCard(
                title = stringResource(id = R.string.expenses),
                value = viewState.expenses,
                color = colorResource(id = R.color.expenses)
            )

            val profitColor = if (viewState.profit.contains(NEGATIVE_BALANCE_TAG))
                colorResource(id = R.color.expenses)
            else colorResource(id = R.color.revenues)

            ProfitsCard(
                title = stringResource(id = R.string.profit),
                value = viewState.profit,
                color = profitColor
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

        ObligationAndDebtButton(
            text = stringResource(id = R.string.customer_obligations),
            onClick = onCustomerObligationsButtonClick
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

        ObligationAndDebtButton(
            text = stringResource(id = R.string.debt),
            onClick = onDebtButtonClick
        )
    }
}

@Composable
fun ProfitsCard(
    title: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.profits_card_elevation)
        ),
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = MaterialTheme.spacing.small)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = color
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
fun ObligationAndDebtButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(WIDTH_FACTOR)
            .height(dimensionResource(id = R.dimen.obligation_debt_button_height)),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    KontoTheme {
        HomeScreen(
            viewState = HomeUserDataViewState(
                "Vers-konto d.o.o",
                "10.000,00€",
                "5.000,00€",
                "5.000,00€"
            ),
            onCustomerObligationsButtonClick = {},
            onDebtButtonClick = {},
        )
    }
}
