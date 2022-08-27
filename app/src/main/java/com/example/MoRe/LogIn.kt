package com.example.MoRe

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.MoRe.dao.SessionManager
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.network.model.base.Resource
import com.example.MoRe.network.model.req.ReqLogin
import com.example.MoRe.network.model.res.login.ResLogin
import com.example.MoRe.network.repository.Repository
import com.example.MoRe.ui.theme.BlueApp
import kotlinx.coroutines.*

import retrofit2.Response

@Composable
fun moreLogInScreen(navController: NavHostController) {


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(vertical = 30.dp)
        ) {
            Text(text = "Masuk",
                style = MaterialTheme.typography.h3,
                fontWeight = Bold
            )

            Spacer(modifier = Modifier.height(30.dp))
            loginForm(navController)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun loginForm(
    navController: NavHostController
) {
    val email = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val default ="test"
    val defaultpass ="pass"
    val context = LocalContext.current



    // START API -----------------------------------------------------------------------------------
    var responseLogin by remember {
        mutableStateOf<Resource<Response<ResLogin>?>?>(null)
    }

    suspend fun postLogin (email: String, password: String){
        val repository = Repository()
        coroutineScope {
            launch(Dispatchers.IO) {
                try {
                    val response = repository.postLogin(ReqLogin(email, password))
                    if (response.code() != 201){

                    } else {
                        responseLogin = Resource.Success(response)
                        (responseLogin as Resource.Success<Response<ResLogin>?>).data?.body()?.data?.let {
                            SessionManager.saveAccessToken(
                                it.accessToken)
                        }
                        Log.d("Token Manager", SessionManager.accessToken)
//                        localDataSource.setAuthToken((responseLogin as Resource.Success<Response<ResLogin>?>).data?.body()?.data?.accessToken)
                    }
                } catch (e: Exception){
                    Log.e("Error", e.message.toString())
                }
            }
        }
    }

    // STOP API ------------------------------------------------------------------------------------
    val composableScope = rememberCoroutineScope()
    val modifier = Modifier
        .height(250.dp)
        .width(400.dp)
        .background(MaterialTheme.colors.background)
        .verticalScroll(rememberScrollState())
    
    Column(modifier , horizontalAlignment = Alignment.CenterHorizontally) {
        EmailInput(emailState = email,
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
        Spacer(modifier = Modifier.height(10.dp))
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedButton(
                onClick = {
                    composableScope.launch {
                        postLogin(email.value, password.value)
                        if(responseLogin?.data?.code() != 201){
                            Toast.makeText(context, "loginGagal", Toast.LENGTH_SHORT).show()
                        } else{
                            Toast.makeText(context, "login Sukses", Toast.LENGTH_LONG).show()
                            navController.navigate(MoReScreens.HomeScreen.name +"/${email.value}/${password.value}" )
                        }
                    }


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
                Text(text = "Masuk",
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(300.dp))
    Column(verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

    }
    Row(verticalAlignment = Alignment.CenterVertically,
        ) {
        Text(text = "Belum Punya Akun?", color = Color.Gray)
        TextButton(onClick = {
            navController.popBackStack()
            navController.navigate(MoReScreens.SignUpScreen.name)

        }) {
            Text(text = "Daftar", color = BlueApp,
                )
        }
    }

}

@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    labelId: String = "Email",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(modifier = modifier,
        valueState = emailState,
        labelId = labelId,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction)
}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground
        ),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction
    )
}



@Composable
fun PasswordInput(
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

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible}) {
        Icon(imageVector = Icons.Filled.Check, contentDescription = "Show password")
    }

}


