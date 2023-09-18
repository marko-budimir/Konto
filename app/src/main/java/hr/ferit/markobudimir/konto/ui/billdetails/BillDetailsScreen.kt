package hr.ferit.markobudimir.konto.ui.billdetails

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import hr.ferit.markobudimir.konto.R
import hr.ferit.markobudimir.konto.model.Bill
import hr.ferit.markobudimir.konto.ui.billdetails.visualtransformation.CurrencyAmountInputVisualTransformation
import hr.ferit.markobudimir.konto.ui.component.ChangeScreenButton
import hr.ferit.markobudimir.konto.ui.theme.KontoTheme
import hr.ferit.markobudimir.konto.ui.theme.spacing
import java.util.*

const val ZERO_CURRENCY_TAG = "0"

@Composable
fun BillDetailsRoute(viewModel: BillDetailsViewModel) {
    BillDetailsScreen(
        onSendButtonClick = { bill -> viewModel.sendBill(bill) },
        onHistoryButtonClicked = { /* TODO */ }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillDetailsScreen(
    onSendButtonClick: (Bill) -> Unit,
    onHistoryButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(all = MaterialTheme.spacing.medium)
    ) {
        Card(
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(
                defaultElevation = dimensionResource(id = R.dimen.profits_card_elevation)
            ),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.small)
            ) {
                Text(
                    text = stringResource(R.string.bill),
                    style = MaterialTheme.typography.headlineMedium
                )

                var billNumber by remember { mutableStateOf("") }
                TextField(
                    value = billNumber,
                    onValueChange = { billNumber = it },
                    label = {
                        Text(
                            text = stringResource(R.string.bill_number),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    },
                    textStyle = MaterialTheme.typography.labelLarge,
                    shape = MaterialTheme.shapes.medium,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        textColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    singleLine = true
                )
                var issueDate by remember { mutableStateOf("") }
                var dueDate by remember { mutableStateOf("") }
                var deliveryDate by remember { mutableStateOf("") }
                InputDate(
                    label = stringResource(R.string.issue_date),
                    onValueChange = { issueDate = it }
                )
                InputDate(
                    label = stringResource(R.string.due_date),
                    onValueChange = { dueDate = it }
                )
                InputDate(
                    label = stringResource(R.string.delivery_date),
                    onValueChange = { deliveryDate = it }
                )

                var amount by remember { mutableStateOf("") }
                TextField(
                    value = amount,
                    onValueChange = {
                        amount = if (it.startsWith(ZERO_CURRENCY_TAG)) {
                            ""
                        } else {
                            it
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(R.string.amount),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    },
                    textStyle = MaterialTheme.typography.labelLarge,
                    shape = MaterialTheme.shapes.medium,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        textColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    visualTransformation = CurrencyAmountInputVisualTransformation(
                        fixedCursorAtTheEnd = false
                    )
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

                val toast = Toast.makeText(
                    LocalContext.current,
                    stringResource(R.string.empty_fields),
                    Toast.LENGTH_SHORT
                )

                Button(
                    onClick = {
                        if (billNumber.isEmpty() || amount.isEmpty() || issueDate.isEmpty() ||
                            dueDate.isEmpty() || deliveryDate.isEmpty()
                        ) {
                            toast.show()
                            return@Button
                        }
                        onSendButtonClick(
                            Bill(
                                number = billNumber,
                                issueDate = issueDate,
                                deliveryDate = deliveryDate,
                                dueDate = dueDate,
                                amount = amount
                            )
                        )
                        billNumber = ""
                        amount = ""
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .height(dimensionResource(id = R.dimen.login_button_height)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    shape = MaterialTheme.shapes.large
                ) {
                    Text(
                        text = stringResource(R.string.send),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        ChangeScreenButton(
            text = stringResource(R.string.history),
            onClick = onHistoryButtonClicked
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDate(
    label: String,
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val day: Int
    val month: Int
    val year: Int

    val calendar = Calendar.getInstance()
    day = calendar.get(Calendar.DAY_OF_MONTH)
    month = calendar.get(Calendar.MONTH)
    year = calendar.get(Calendar.YEAR)

    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    onValueChange(date.value)
    val datePicker = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, y: Int, m: Int, d: Int ->
            date.value = "$d.${m + 1}.$y."
        }, year, month, day
    )

    TextField(
        value = date.value,
        onValueChange = { date.value = it },
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        },
        textStyle = MaterialTheme.typography.labelLarge,
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledTextColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        enabled = false,
        modifier = modifier.clickable { datePicker.show() }
    )
}

@Preview
@Composable
fun BillDetailsScreenPreview() {
    KontoTheme {
        BillDetailsScreen({}, {})
    }
}
