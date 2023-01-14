package hr.ferit.markobudimir.konto.ui.companies


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import hr.ferit.markobudimir.konto.mock.CompaniesMock
import hr.ferit.markobudimir.konto.ui.component.CompaniesViewState
import hr.ferit.markobudimir.konto.ui.component.CompanyItem
import hr.ferit.markobudimir.konto.ui.theme.KontoTheme
import hr.ferit.markobudimir.konto.ui.theme.spacing

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CompaniesScreen(
    title: String,
    viewState: CompaniesViewState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(MaterialTheme.spacing.small),
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        stickyHeader {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(vertical = MaterialTheme.spacing.small),
                textAlign = TextAlign.Center
            )
        }
        items(
            items = viewState.companies,
            key = { company -> company.id }
        ) { company ->
            CompanyItem(
                companyItemViewState = company,
                modifier = Modifier.padding(top = MaterialTheme.spacing.small)
            )
        }
    }
}

@Preview
@Composable
fun CompaniesScreenPreview() {
    KontoTheme {
        CompaniesScreen(
            title = "Obveze kupaca",
            viewState = CompaniesViewState(CompaniesMock.getCompanies())
        )
    }
}
