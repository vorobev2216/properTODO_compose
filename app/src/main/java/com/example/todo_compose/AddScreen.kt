package com.example.todo_compose

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

@Composable
fun AddScreen(viewModel: NavViewModel) {

    var inputText by remember {
        mutableStateOf("")
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(value = inputText, onValueChange = {
            inputText = it
            viewModel.inputText.value = inputText
        })

        Button(onClick = {
            viewModel.navController.value?.popBackStack()
        }) {
            Log.d("RRR", viewModel.inputText.value.toString())
        }
    }
}