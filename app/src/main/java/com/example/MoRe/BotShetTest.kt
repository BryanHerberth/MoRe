package com.example.MoRe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.MoRe.components.CardNotif
import com.example.MoRe.model.DaftarMesinNotif
import com.example.MoRe.model.getDataMesin
import com.example.MoRe.ui.theme.MyApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class BotShetTest : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
//                BottomSheetSample()
                ModelBottomSheet()
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun MainScreenView( scope : CoroutineScope  ,
                     bottomSheetScaffoldState: BottomSheetScaffoldState
                     )
{
    Box( modifier = Modifier.fillMaxWidth()) {
        Button(
            modifier = Modifier
                .padding(20.dp)
                .align(alignment = Alignment.TopCenter),
            onClick = {
                scope.launch {
                    if (bottomSheetScaffoldState.bottomSheetState.isCollapsed)
                    {
                        bottomSheetScaffoldState.bottomSheetState.expand()
                    } else {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                }
            }) {
            Text(text = "Click to Show ")
        }
    }
}



@ExperimentalMaterialApi
@Composable
fun BottomSheetSample() {

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,

        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color(0xAA3fa7cc))
            ) {
                Text(
                    text = "Hello from bottom sheet",
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        },
        sheetPeekHeight = 40.dp
    ) {
        MainScreenView(scope, bottomSheetScaffoldState)
    }
}

@Preview
@Composable
@ExperimentalMaterialApi
fun ModelBottomSheet(
    listMesin : List<DaftarMesinNotif> = getDataMesin()) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue =ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            Text(text = "Test",
            style = MaterialTheme.typography.h3)
            LazyColumn{
                items(items = listMesin)
                {
                    CardNotif(mesin = it)
                }
            }
        }
    ) {
        ModalBottomSheetMainscreenView(scope, modalBottomSheetState)
    }
}


@ExperimentalMaterialApi
@Composable
fun ModalBottomSheetMainscreenView(
    scope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState
) {
    Box(
        Modifier
            .fillMaxWidth()
    ){
        Button(
            modifier = Modifier
                .padding(20.dp)
                .align(alignment = Alignment.TopCenter),
            onClick = {
                scope.launch{
                    modalBottomSheetState.show()
                }
            }
        ) {
            Text(
                text = "Click to show Model Bottom Sheet"
            )
        }
    }
}

