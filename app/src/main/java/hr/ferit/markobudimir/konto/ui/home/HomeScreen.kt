package hr.ferit.markobudimir.konto.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hr.ferit.markobudimir.konto.R
import hr.ferit.markobudimir.konto.ui.component.ChangeScreenButton
import hr.ferit.markobudimir.konto.ui.component.ProfitsCard
import hr.ferit.markobudimir.konto.ui.theme.KontoTheme
import hr.ferit.markobudimir.konto.ui.theme.spacing

const val NEGATIVE_BALANCE_TAG = "-"
const val WIDTH_PERCENTAGE = 0.9f

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
                .fillMaxWidth(WIDTH_PERCENTAGE)
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

        ChangeScreenButton(
            text = stringResource(id = R.string.customer_obligations),
            widthPercentage = WIDTH_PERCENTAGE,
            onClick = onCustomerObligationsButtonClick
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

        ChangeScreenButton(
            text = stringResource(id = R.string.debt),
            widthPercentage = WIDTH_PERCENTAGE,
            onClick = onDebtButtonClick
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
