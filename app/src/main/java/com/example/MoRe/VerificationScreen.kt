package com.example.MoRe

import android.hardware.lights.Light
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.network.model.base.Resource
import com.example.MoRe.network.model.req.ReqSendVerifikasi
import com.example.MoRe.network.model.req.ReqVerifikasi
import com.example.MoRe.network.model.res.ResVerifikasi
import com.example.MoRe.network.repository.Repository
import com.example.MoRe.ui.theme.BlueApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response


@Composable
fun moreVerifScreen(
    navController: NavController,
    email: String?
) {
    Log.d("Email verification Screen", email!!)

    // API START

    // API STOP

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(vertical = 30.dp)
        ) {
            Text(text = "Verifikasi",
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Kode Verifikasi telah dikirim ke email anda. \nSilahkan masukan untuk mengverifikasi email anda",
                style = MaterialTheme.typography.caption,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(30.dp))

            VerifForm(navController = navController, email)

        }
    }
}


@Composable
fun VerifForm(
    navController: NavController,
    email: String
) {
    val verificationPin = rememberSaveable {
        mutableStateOf("")
    }
    val passwordFocusRequest = FocusRequester.Default
    val context = LocalContext.current
    // START API
    var responseVerify by remember{
        mutableStateOf<Resource<Response<ResVerifikasi>?>?>(null)
    }
    suspend fun postSendVerifikasi(email: String)  {
        val repository = Repository()
        val req = ReqSendVerifikasi(email)
        coroutineScope {
            launch(Dispatchers.IO) {
                try {
                    val response = repository.postUserSendVerifikasi(req)
                    Log.d("responseSendVerifikasi : ", response.toString())
                } catch (e: Exception){
                    Log.e("Error SendVerifikasi : ", e.message.toString())
                }
            }
        }
    }

    suspend fun postVerify(email: String, kode: String) {
        val repository = Repository()
        val req = ReqVerifikasi(email, kode)
        coroutineScope {
            launch(Dispatchers.IO) {
                try {
                    val response = repository.postUserVerifikasi(req)
                    Log.d("responseVerifikasiEmail : ", response.toString())
                    launch(Dispatchers.Main) {
                        responseVerify = Resource.Success(response)
                    }
                } catch (e: Exception){
                    Log.e("Error Verifikasi : ", e.message.toString())
                }
            }
        }
    }

    // STOP API
    val composableScope = rememberCoroutineScope()

    val modifier = Modifier
        .height(600.dp)
        .width(400.dp)
        .background(MaterialTheme.colors.background)
        .verticalScroll(rememberScrollState())

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        verifField(verifState = verificationPin,
            enabled = true,
            onAction = KeyboardActions {
                passwordFocusRequest.requestFocus()
            })

        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.End

        ) {
            TextButton(onClick = {
                composableScope.launch {
                    postSendVerifikasi(email)
                }
            }) {
                Text(text = "Kirim ulang kode verifikasi",
                    style = MaterialTheme.typography.h6,
                    color = BlueApp,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedButton(
                onClick = {
                    composableScope.launch {
                        postVerify(email, verificationPin.value)
                        if(responseVerify?.data?.code() != 200){
                            Toast.makeText(context, "Kode yang anda masukan tidak valid", Toast.LENGTH_SHORT).show()
                        } else{
                            Toast.makeText(context, "Verifikasi Sukses", Toast.LENGTH_SHORT).show()
//                            navController.popBackStack()
                            navController.navigate(MoReScreens.LoginScreen.name)
                        }
                    }
//                    navController.popBackStack()
//                    navController.navigate(MoReScreens.LoginScreen.name)
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
                Text(text = "Verifikasi",
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(MoReScreens.SignUpScreen.name)
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
                    backgroundColor = Color.LightGray,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Batal",
                )
            }
        }

    }
}


@Composable
fun verifField(modifier: Modifier = Modifier,
               verifState: MutableState<String>,
               labelId: String = "Masukkan kode ",
               enabled: Boolean = true,
               imeAction: ImeAction = ImeAction.Done,
               onAction: KeyboardActions = KeyboardActions.Default) {
    InputField(
        modifier = modifier,
        valueState = verifState,
        labelId = labelId,
        enabled = enabled,
        keyboardType = KeyboardType.Number,
        onAction = onAction,
        imeAction = imeAction
    )
}

