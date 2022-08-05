package com.example.MoRe

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.MoRe.dao.SessionManager
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.network.model.base.Resource
import com.example.MoRe.network.model.req.ReqPutUser
import com.example.MoRe.network.model.res.ResGetUser
import com.example.MoRe.network.model.res.getuser.User
import com.example.MoRe.network.model.res.putUser.ResPutUser
import com.example.MoRe.network.repository.Repository
import com.example.MoRe.ui.theme.BlueApp
import com.example.MoRe.ui.theme.MyApplicationTheme
import com.example.MoRe.ui.theme.Teal200
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Composable
fun CustomAppbar(name:String,
                 navController: NavController,
                 email : String?,
                 password : String?,
                 idPabrik : String?
                 ) {
    Column {
        TopAppBar(title = {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                ) {
                Text(
                    text = "$name",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(90.dp, 0.dp)
                )
            }
        },
            backgroundColor = BlueApp,

            navigationIcon = {
                IconButton(onClick = {
                        navController.navigate(MoReScreens.HomeScreen.name +"/${email}/${password}"){
                        popUpTo(MoReScreens.HomeScreen.name +"/${email}/${password}")
                        {
                            inclusive = true
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Arrow",
                        tint = White
                    )
                }
            })
    }
}

@Composable
fun CustomAppbar2(name:String,
                 navController: NavController,
                 email : String?,
                 password : String?,
                  idPabrik: String?
) {
    Column {
        TopAppBar(title = {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "$name",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(90.dp, 0.dp)
                )
            }
        },
            backgroundColor = BlueApp,

            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate(MoReScreens.PabrikScreen.name +"/{$idPabrik}") {
                        popUpTo(MoReScreens.PabrikScreen.name+"/{$idPabrik}")
                        {
                            inclusive = true
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Arrow",
                        tint = White
                    )
                }
            })
    }
}

@Composable
fun Scaffoldlayout(navController: NavController) {

//    // START API
//    var responsegetUser by remember {
//        mutableStateOf<ResGetUser?>(null)
//    }
//    var activeUser by remember {
//        mutableStateOf<User?>(User("", "", "", "", "", true, ""))
//    }
//
//    suspend fun getUser(){
//        val repository = Repository()
//        coroutineScope {
//            launch(Dispatchers.IO) {
//                try{
//                    val response =  repository.getUser()
//                    launch(Dispatchers.Main) {
//                        responsegetUser = Resource.Success(response).data?.body()
//                        activeUser = responsegetUser?.data?.user
//                        Log.d("Response Get Data User", responsegetUser.toString())
//                    }
//                } catch (e: Exception){
//                    Log.e("error get User on UserProfile.kt : ", e.message.toString())
//                }
//            }
//        }
//    }
//
//    LaunchedEffect(Unit){
//        getUser()
//    }
    // STOP API

    Scaffold(
        topBar = {
            CustomAppbar("Profil",
                navController = navController, email = null , password = null, idPabrik = null)
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 10.dp),
                contentAlignment = Alignment.TopCenter,

                ) {
                Surface(
                    color = Color.White,
                )
                {
                    ProfileImage(navController)
                }
            }
        })
}

@Composable
fun ProfileImage(navController: NavController) {
    val imageUri = rememberSaveable { mutableStateOf("") }
//    val imgProfil = activeUser.foto_profil // ini foto profilnya tolong di implemen
    val painter = rememberAsyncImagePainter(
        if (imageUri.value.isEmpty())
            R.drawable.ic_user
        else
            imageUri.value
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp),
            border = BorderStroke(2.dp, color = Color.Black)
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp),
//                    .clickable { launcher.launch("image/*") },
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        userData(navController)
    }
}

@Composable
fun userData(navController: NavController) {
    var activeUser = User("", "", "", "", "", true, "")
    activeUser = SessionManager.getUserData()!!
    var text by remember {
        mutableStateOf(activeUser?.nama_pengguna)
    }
    var text2 by remember {
        mutableStateOf(activeUser?.nama_pengguna)
    }
    var email by remember {
        mutableStateOf(activeUser?.email)
    }
    var phonenum by remember {
        mutableStateOf(activeUser?.no_telepon)
    }
    var phonenum2 by remember {
        mutableStateOf(activeUser?.no_telepon)
    }
    val openDialog:MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    val opennameDialog:MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    // START API
    var responseEditUser by remember {
        mutableStateOf<ResPutUser?>(null)
    }

    suspend fun putUser(nama: String, noTelpn: String){
        val repository = Repository()
        coroutineScope {
            launch(Dispatchers.IO) {
                try {
                    val response = repository.putUser(ReqPutUser(nama, "", noTelpn))
                    launch(Dispatchers.Main) {
                        responseEditUser = Resource.Success(response).data?.body()
                    }
                } catch (e: Exception){
                    Log.e("Error Edit User on UserProfule.kt : ", e.message.toString())
                }
            }
        }
    }
    val composableScope = rememberCoroutineScope()
    // STOP API

    Column {
        OutlinedTextField(value = text, onValueChange = { newText ->
            text = newText
        },
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = {
                    opennameDialog.value = true
                }) {
                    Icon(imageVector = Icons.Filled.Create, contentDescription = "Edit",
                    )
                    if (opennameDialog.value) {

                        AlertDialog(
                            onDismissRequest = {
                                opennameDialog.value = false
                                text2 = text
                            },
                            title = {
                                Text(text = "Ubah Nama", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                            },
                            text = {
                                Column() {
                                    Text("Nama",
                                        fontSize = 20.sp,
                                        style = MaterialTheme.typography.body2)

                                    OutlinedTextField(value = text2, onValueChange = { newText2 ->
                                        text2 = newText2
                                    },
                                        shape = RoundedCornerShape(10.dp),
                                        singleLine = true,)
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = {
                                    text2 = text
                                    opennameDialog.value = false
                                }) {
                                    Text(
                                        text = "Batal",
                                        color = Color.Red,
                                        fontSize = 15.sp,
                                    )
                                }
                            },
                            confirmButton = {
                                TextButton(onClick = {
                                    opennameDialog.value = false
                                    composableScope.launch {
                                        putUser(text2, phonenum)
                                    }
                                    text = text2
                                }) {
                                    Text(
                                        text = "Ubah nama",
                                        color = BlueApp,
                                        fontSize = 15.sp,
                                    )
                                }
                            }
                        )
                    }
                }
            })
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = email, onValueChange = { newMail ->
                email = newMail
            },
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            readOnly = true
        )
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = phonenum, onValueChange = { newNum ->
                phonenum = newNum
            },
            shape = RoundedCornerShape(10.dp),
            readOnly = true,
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    openDialog.value = true
                }) {
                    Icon(imageVector = Icons.Filled.Create, contentDescription = "Edit")
                    if (openDialog.value) {

                        AlertDialog(
                            onDismissRequest = {
                                openDialog.value = false

                                phonenum2 = phonenum
                            },
                            title = {
                                Text(text = "Ubah Nomor", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                            },
                            text = {
                                Column() {
                                    Text("Nomor Handphone",
                                        fontSize = 20.sp,
                                        style = MaterialTheme.typography.body2)

                                    OutlinedTextField(value = phonenum2, onValueChange = { newNum ->
                                        phonenum2 = newNum
                                    },
                                        shape = RoundedCornerShape(10.dp),
                                        singleLine = true,
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Phone,
                                        ))
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = {
                                    phonenum2 = phonenum
                                    openDialog.value = false
                                }) {
                                    Text(
                                        text = "Batal",
                                        color = Color.Red,
                                        fontSize = 15.sp,
                                    )
                                }
                            },
                            confirmButton = {
                                TextButton(onClick = {
                                    openDialog.value = false
                                    composableScope.launch {
                                        putUser(text, phonenum2)
                                    }
                                    phonenum = phonenum2
                                }) {
                                    Text(
                                        text = "Ubah Nomor",
                                        color = BlueApp,
                                        fontSize = 15.sp,
                                    )
                                }
                            }
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
            )
        )
    }
    Spacer(modifier = Modifier.height(200.dp))
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                SessionManager.logOut()
                navController.navigate(MoReScreens.LoginScreen.name)
            },
            modifier = Modifier.size(260.dp, 40.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red,
            )
        )
        {
            Text(
                text = "Keluar",
                style = MaterialTheme.typography.button,
                color = Color.White
            )
        }
    }
}




