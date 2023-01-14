package hr.ferit.markobudimir.konto.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hr.ferit.markobudimir.konto.ui.theme.spacing

@Immutable
data class CompanyItemViewState(
    val id: Int,
    val name: String,
    val value: String,
)

@Composable
fun CompanyItem(
    companyItemViewState: CompanyItemViewState,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.tertiary,
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.extraSmall)
        ) {
            Text(
                text = companyItemViewState.name,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
            Text(
                text = companyItemViewState.value,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview
@Composable
fun CompanyItemPreview() {
    Column {
        CompanyItem(
            companyItemViewState = CompanyItemViewState(1, "Company name", "10000€"),
            modifier = Modifier
                .padding(MaterialTheme.spacing.extraSmall)
                .fillMaxWidth()
        )
        CompanyItem(
            companyItemViewState = CompanyItemViewState(2, "Company name 2", "20000€"),
            modifier = Modifier
                .padding(MaterialTheme.spacing.extraSmall)
                .fillMaxWidth()
        )
    }
}
