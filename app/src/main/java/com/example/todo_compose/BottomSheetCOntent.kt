package com.example.todo_compose

import android.app.DatePickerDialog
import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BottomSheetContent() {
    var textFieldTitle by remember {
        mutableStateOf("")
    }

    var textFieldDesc by remember {
        mutableStateOf("")
    }

    val myTextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.metropolissemibold)),
        fontSize = 16.sp, // Adjust font size as needed
    )

    var showDDL by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            label = {
                Text(
                    text = "New Task",
                    fontFamily = FontFamily(Font(R.font.metropolissemibold)),
                    color = Color.White
                )
            },
            value = textFieldTitle,
            modifier = Modifier
                .imePadding()
                .clickable {
                    textFieldTitle = ""
                },
            textStyle = myTextStyle,
            onValueChange = { textFieldTitle = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Black,
                focusedIndicatorColor = Color.Black, // Цвет индикатора (линии) при фокусе
                unfocusedIndicatorColor = Color.Black,
                focusedTextColor = Color.White,
                cursorColor = Color.White,

                ),
            maxLines = 1
        )

        TextField(
            label = {
                Text(
                    text = "Description",
                    fontFamily = FontFamily(Font(R.font.metropolissemibold)),
                    color = Color.White
                )
            },
            value = textFieldDesc,
            modifier = Modifier
                .padding(15.dp)
                .imePadding()
                .clickable { textFieldDesc = "" },
            onValueChange = { textFieldDesc = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Black,
                focusedIndicatorColor = Color.Black, // Цвет индикатора (линии) при фокусе
                unfocusedIndicatorColor = Color.Black,// Цвет индикатора без фокуса
                cursorColor = Color.White,
                focusedTextColor = Color.White
            )

        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,


                ) {
                val myDatePickerDialog = DatePickerDialog(LocalContext.current)
                CardInAddScreen(text = "Today", icon = R.drawable.baseline_calendar_today_24) {
                    myDatePickerDialog.show()
                }
                CardInAddScreen(text = "Category", icon = R.drawable.baseline_category_24) {

                    showDDL = !showDDL
                }
                if (showDDL) {
                    DDL()
                }
            }
            RoundAddButton()
        }

    }
}


@Composable
fun CardInAddScreen(text: String, icon: Int, onCardClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(Color.DarkGray),
        modifier = Modifier
            .height(55.dp)
            .width(130.dp)
            .fillMaxWidth()
            .padding(start = 5.dp)
            .wrapContentWidth()
            .clickable { onCardClick() }


    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Icon(painter = painterResource(id = icon), contentDescription = null)
            Text(text = text, fontFamily = FontFamily(Font(R.font.metropolissemibold)))
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DDL() {
    var isExpand by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(expanded = isExpand, onExpandedChange = { isExpand = it }) {
        ExposedDropdownMenu(expanded = isExpand, onDismissRequest = { isExpand = false }) {
            DropdownMenuItem(
                text = { Text(text = "Important", color = Color(0xFF7E0D2E)) },
                onClick = { isExpand = false })
            DropdownMenuItem(
                text = { Text(text = "Need to do", color = Color(0xFFAD8405)) },
                onClick = { isExpand = false })
            DropdownMenuItem(
                text = { Text(text = "Can wait", color = Color(0xFF408B0A)) },
                onClick = { isExpand = false })

        }
    }
}

@Composable
fun ShowDDL() {
    // Вызываем вашу Composable функцию DDL()
    DDL()
}