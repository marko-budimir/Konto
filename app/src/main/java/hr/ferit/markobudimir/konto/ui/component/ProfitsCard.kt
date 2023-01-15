package hr.ferit.markobudimir.konto.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import hr.ferit.markobudimir.konto.R
import hr.ferit.markobudimir.konto.ui.theme.KontoTheme
import hr.ferit.markobudimir.konto.ui.theme.spacing

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

@Preview
@Composable
fun ProfitsCardPreview() {
    KontoTheme {
        ProfitsCard(
            title = "Profit",
            value = "1000",
            color = Color.Green
        )
    }
}
