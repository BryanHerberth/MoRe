package com.example.MoRe

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.MoRe.components.CardPabrik
import com.example.MoRe.model.DaftarPabrik
import com.example.MoRe.model.getPabrik
import com.example.MoRe.ui.theme.BlueApp
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun MainScreen(navController: NavController) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column {
        SearchView(textState)
        PabrikList(navController = navController, state = textState)
    }
}


@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
//                modifier = Modifier
//                    .padding(15.dp)
//                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
//                        modifier = Modifier
//                            .padding(15.dp)
//                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = BlueApp,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    SearchView(textState)
}

@Composable
fun PabrikList(navController: NavController,
                state: MutableState<TextFieldValue>,
                ) {
    val countries =  getPabrik()
    var filteredCountries: ArrayList<DaftarPabrik>
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        val searchedText = state.value.text
        filteredCountries = if (searchedText.isEmpty()) {
            countries
        } else {
            val resultList = ArrayList<DaftarPabrik>()
            for (namaPabrik in countries) {
                if (namaPabrik.namaPabrik.lowercase(Locale.getDefault())
                        .contains(searchedText.lowercase(Locale.getDefault()))
                ) {
                    resultList.add(namaPabrik)
                }
            }
            resultList
        }
        items(filteredCountries) { filteredCountry ->
//            CountryListItem(
//                countryText = filteredCountry.namaPabrik,
//                onItemClick = { selectedCountry ->
//                    navController.navigate("details/$selectedCountry") {
//                        // Pop up to the start destination of the graph to
//                        // avoid building up a large stack of destinations
//                        // on the back stack as users select items
//                        popUpTo("main") {
//                            saveState = true
//                        }
//                        // Avoid multiple copies of the same destination when
//                        // reselecting the same item
//                        launchSingleTop = true
//                        // Restore state when reselecting a previously selected item
//                        restoreState = true
//                    }
//                }
//            )
            CardPabrik(navController = navController, resPabrik =  null )
        }
    }
}



@Composable
fun CountryListItem(countryText: String, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = {
                onItemClick(countryText)
            })
            .background(Color.White)
            .fillMaxWidth()
            .padding(8.dp, 16.dp)
    ) {
        Text(text = countryText, fontSize = 18.sp, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun CountryListItemPreview(pabrik : DaftarPabrik = getPabrik()[0]) {
    CountryListItem(countryText = pabrik.namaPabrik, onItemClick = { })
}

