package com.example.todo_compose

import android.content.res.Resources.Theme
import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import kotlinx.serialization.Serializable


@Composable
fun ToDoPage(toDoViewModel: ToDoViewModel, navViewModel: NavViewModel) {
    val todoList by toDoViewModel.todoList.observeAsState()
    var inputText by remember {
        mutableStateOf("")
    }
    Column() {
        Text(
            text = "Делай",
            modifier = Modifier
                .padding(start = 10.dp)
                .padding(top = 60.dp, bottom = 15.dp, start = 10.dp),
            fontFamily = FontFamily(Font(R.font.metropolissemibold)),
            fontSize = 35.sp
        )
        Column(
            modifier = Modifier.padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row() {
                OutlinedTextField(value = inputText, onValueChange = {
                    inputText = navViewModel.inputText.value.toString()
                })
                Button(onClick = {
                    toDoViewModel.addTodo(navViewModel.inputText.value.toString(), null)
                    inputText = ""
                }) {
                    Text(text = "Add")
                }
            }
            todoList?.let {
                LazyColumn(modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 5.dp),
                    content = {
                        itemsIndexed(it) { index: Int, item: ToDoCard ->
                            CardItem(item = item)
                        }
                    }
                )
            } ?: Text(text = "Нет дел!",modifier = Modifier.padding(top = 10.dp))
        }
    }
}


@Composable
fun CardItem(item: ToDoCard) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(100.dp)
            .padding(top = 6.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)


    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Column(
                Modifier.fillMaxWidth(0.75f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = item.title.toString(),
                    modifier = Modifier.padding(top = 10.dp, start = 10.dp)
                )

                Text(
                    text = item.description ?: "",
                    modifier = Modifier.padding(start = 10.dp),
                    color = Color.LightGray
                )


                HorizontalDivider(color = Color.LightGray, modifier = Modifier.padding(5.dp))
                Text(
                    text = item.time.toString(),
                    modifier = Modifier.padding(start = 10.dp, bottom = 5.dp),
                    color = Color.LightGray
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_delete_outline_24),
                    contentDescription = null
                )
            }


        }

    }

}

@Preview
@Composable
fun RoundAddButton() {

    IconButton(onClick = {  },
        modifier = Modifier

            .clip(CircleShape)
            .size(50.dp)
            .background(color = MaterialTheme.colorScheme.primary)) {
        Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = null)

    }
}