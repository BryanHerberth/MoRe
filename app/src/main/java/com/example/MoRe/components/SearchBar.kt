package com.example.MoRe.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.MoRe.ViewModel.SearchViewModel
import com.example.MoRe.model.PabrikSearchState
import com.example.MoRe.network.model.res.DataPabrik
import com.example.MoRe.rememberFlowWithLifecycle
import com.example.MoRe.ui.theme.BlueApp
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.KProperty

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    navController: NavController,
    searchViewModel: SearchViewModel,
)
{
    val PabrikSearchState by rememberFlowWithLifecycle(searchViewModel.PabrikSearchState)
        .collectAsState(
        initial = PabrikSearchState.Empty
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = BlueApp
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text ,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    text = "Search Here...",
                    color = Color.White)
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    onClick = {

                    }
                ) {
                    Icon(imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()){
                            onTextChange("")
                        } else {
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    searchViewModel.onSearchTextChanged(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium )
            )
        )
    }
}



@Composable
fun listPabrik(navController: NavController,
               state: MutableState<TextFieldValue>,
               resPabrik: DataPabrik
){
    val list = resPabrik.nama_pabrik
    var filteredResult: ArrayList<String>
//    LazyColumn(modifier = Modifier.fillMaxWidth()) {
//        val searchedText = state.value.text
//        filteredResult = if (searchedText.isEmpty()) {
//            list.nam
//        } else {
//            val resultList = java.util.ArrayList<String>()
//            for (country in list) {
//                if (country.lowercase(Locale.getDefault())
//                        .contains(searchedText.lowercase(Locale.getDefault()))
//                ) {
//                    resultList.add(list)
//                }
//            }
//            resultList
//        }
//        items(filteredResult) { filteredCountry ->
//            CountryListItem(
//                countryText = filteredCountry,
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
//        }
//    }
}


//@Composable
//@Preview
//fun SearchbarPReview() {
//    SearchAppBar(text = "Random",
//        onTextChange = {}, onCloseClicked = { /*TODO*/ }, onSearchClicked ={} )
//}