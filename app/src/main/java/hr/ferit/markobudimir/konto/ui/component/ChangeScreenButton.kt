package hr.ferit.markobudimir.konto.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import hr.ferit.markobudimir.konto.R
import hr.ferit.markobudimir.konto.ui.theme.KontoTheme

@Composable
fun ChangeScreenButton(
    text: String,
    modifier: Modifier = Modifier,
    widthPercentage: Float = 1f,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(widthPercentage)
            .height(dimensionResource(id = R.dimen.obligation_debt_button_height)),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Text(text = text, style = MaterialTheme.typography.titleMedium)
    }
}

@Preview
@Composable
fun ChangeScreenButtonPreview() {
    KontoTheme {
        ChangeScreenButton(text = "Preview", onClick = {})
    }
}
