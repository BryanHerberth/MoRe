package com.example.MoRe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.ui.theme.BlueApp


@Composable
fun moreSignUpScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(vertical = 30.dp)
        ) {
            Text(text = "Daftar",
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(30.dp))
            registerForm(navController = navController)
        }
    }
}

@Composable
fun registerForm(navController: NavController) {
    val namaLengkap = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val noHp = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val confirmPassword = rememberSaveable { mutableStateOf("") }
    val confirmPass = rememberSaveable { mutableStateOf("") }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val passwordFocusRequest = FocusRequester.Default
    val modifier = Modifier
        .height(450.dp)
        .width(400.dp)
        .background(MaterialTheme.colors.background)
        .verticalScroll(rememberScrollState())

    Column(modifier , horizontalAlignment = Alignment.CenterHorizontally) {

        NamaInput(namaState = namaLengkap,
            enabled = true,
            onAction = KeyboardActions{
                passwordFocusRequest.requestFocus()
            })

        EmailInput(emailState = email,
            enabled = true,
            onAction = KeyboardActions{
                passwordFocusRequest.requestFocus()
            })

        noTelpon(noHpState = noHp,
            enabled = true,
            onAction = KeyboardActions{
                passwordFocusRequest.requestFocus()
            })

        PasswordInput(
            modifier = Modifier.focusRequester(passwordFocusRequest),
            passwordState = password,
            labelId = "Password",
            enabled = true,
            passwordVisibility = passwordVisibility
        )

        ConfirmpasswordInput(
            modifier = Modifier.focusRequester(passwordFocusRequest),
            passwordState = password,
            labelId = "Konfirmasi Password",
            enabled = true,
            passwordVisibility = passwordVisibility
        )


        Spacer(modifier = Modifier.height(10.dp))
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedButton(
                onClick = {
//                    navController.popBackStack()
                    navController.navigate(MoReScreens.VerificationScreen.name)

                },
                modifier = Modifier
                    .padding(4.dp)
                    .width(250.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 4.dp,
                    disabledElevation = 0.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = BlueApp,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Daftar",
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(200.dp))
    Column(verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Sudah Punya Akun?", color = Color.Gray)
            TextButton(onClick = {
                navController.popBackStack()
                navController.navigate(MoReScreens.LoginScreen.name)

            }) {
                Text(text = "Masuk", color = BlueApp,
                )
            }
        }
    }
}


@Composable
fun NamaInput(modifier: Modifier = Modifier,
              namaState: MutableState<String>,
              labelId: String = "Nama",
              enabled: Boolean = true,
              imeAction: ImeAction = ImeAction.Next,
              onAction: KeyboardActions = KeyboardActions.Default) {
    InputField(modifier = modifier,
        valueState = namaState,
        labelId = labelId,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction)
}

@Composable
fun noTelpon(modifier: Modifier = Modifier,
              noHpState: MutableState<String>,
              labelId: String = "No. Telepon",
              enabled: Boolean = true,
              imeAction: ImeAction = ImeAction.Next,
              onAction: KeyboardActions = KeyboardActions.Default) {
    InputField(modifier = modifier,
        valueState = noHpState,
        labelId = labelId,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction)
}

@Composable
fun ConfirmpasswordInput(
    modifier: Modifier,
    passwordState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    passwordVisibility: MutableState<Boolean>,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default,
) {

    val visualTransformation = if (passwordVisibility.value) VisualTransformation.None else
        PasswordVisualTransformation()
    OutlinedTextField(value = passwordState.value,
        onValueChange = {
            passwordState.value = it
        },
        label = { Text(text = labelId)},
        singleLine = true,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction),
        visualTransformation = visualTransformation,
        trailingIcon = {PasswordVisibility(passwordVisibility = passwordVisibility)},
        keyboardActions = onAction)

}