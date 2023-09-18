package hr.ferit.markobudimir.konto.ui.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import hr.ferit.markobudimir.konto.R
import hr.ferit.markobudimir.konto.ui.theme.KontoTheme
import hr.ferit.markobudimir.konto.ui.theme.spacing

@Composable
fun LoginRoute(
    onNavigateToMain: () -> Unit,
    viewModel: LoginViewModel
) {
    val viewState: LoginViewState by viewModel.loginViewState.collectAsState()
    LoginScreen(
        viewState = viewState,
        onLoginButtonClick = { email, password -> viewModel.login(email, password) },
        resetLoginViewState = { viewModel.resetLoginViewState() },
        onNavigateToMain = onNavigateToMain,
    )
}

@Composable
fun LoginScreen(
    viewState: LoginViewState,
    onLoginButtonClick: (String, String) -> Unit,
    onNavigateToMain: () -> Unit,
    resetLoginViewState: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = MaterialTheme.spacing.extraExtraLarge)
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraExtraLarge))

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        InputField(
            placeholder = stringResource(id = R.string.email_placeholder),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { email = it }
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

        var passwordVisible by remember { mutableStateOf(false) }
        InputField(
            placeholder = stringResource(id = R.string.password_placeholder),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(id = R.drawable.ic_baseline_visibility_24)
                else painterResource(id = R.drawable.ic_baseline_visibility_off_24)

                val description = if (passwordVisible)
                    stringResource(id = R.string.password_icon_description_hide)
                else stringResource(id = R.string.password_icon_description_show)

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = image, description)
                }
            },
            onValueChange = { password = it }
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (viewState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen.login_button_height))
                        .width(dimensionResource(id = R.dimen.login_button_height))
                )
            }
        }

        Button(
            onClick = { onLoginButtonClick(email, password) },
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .height(dimensionResource(id = R.dimen.login_button_height)),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = MaterialTheme.shapes.large
        ) {
            Text(
                text = stringResource(id = R.string.login_button),
                style = MaterialTheme.typography.titleMedium
            )
        }

        if (viewState.isSuccess) {
            onNavigateToMain()
            resetLoginViewState()
        }
        val context = LocalContext.current
        LaunchedEffect(key1 = viewState.isError) {
            viewState.isError?.let { error ->
                if (error.isNotEmpty() && error.isNotBlank()) {
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit = {}
) {
    var value by remember { mutableStateOf("") }
    onValueChange(value)
    TextField(
        value = value,
        onValueChange = { value = it },
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
        },
        textStyle = MaterialTheme.typography.labelLarge,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        trailingIcon = trailingIcon,
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        singleLine = true,
        modifier = modifier,
    )
}

@Preview
@Composable
fun LoginScreenPreview() {
    KontoTheme {
        LoginScreen(
            viewState = LoginViewState(),
            onLoginButtonClick = { email, password -> },
            onNavigateToMain = {},
            resetLoginViewState = {}
        )
    }
}
