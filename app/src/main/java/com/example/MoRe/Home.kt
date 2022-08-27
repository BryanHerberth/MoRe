package com.example.MoRe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp





@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {},
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier,
        contentAlignment = Alignment.Center) {
        TextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            shape = RoundedCornerShape(30.dp),
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black , textAlign = TextAlign.Left),
            modifier = Modifier
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true
                },
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {

                }
                Icon(imageVector = Icons.Default.Search , contentDescription = "Search")
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent)

        )
        if(isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}



