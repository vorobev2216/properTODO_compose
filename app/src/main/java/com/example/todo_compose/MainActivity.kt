package com.example.todo_compose

import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo_compose.ui.theme.ToDo_composeTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    private val NavViewModel by viewModels<NavViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toDoViewModel = ViewModelProvider(this)[ToDoViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            ToDo_composeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = ScreenA) {
                    composable<ScreenA> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()

                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Top
                            ) {
                                ToDoPage(toDoViewModel, NavViewModel)
                            }

                            val sheetState = rememberModalBottomSheetState()
                            var isSheetOpen by remember {
                                mutableStateOf(false)
                            }
                            if (isSheetOpen) {

                                ModalBottomSheet(
                                    onDismissRequest = { isSheetOpen = false },
                                    sheetState = sheetState, modifier = Modifier.wrapContentHeight().imePadding()
                                ) {
                                    BottomSheetContent()
                                }
                            }

                            IconButton(
                                onClick = { isSheetOpen = true },
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 50.dp)
                                    .clip(CircleShape)
                                    .size(65.dp)
                                    .background(color = MaterialTheme.colorScheme.primary)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_add_24),
                                    modifier = Modifier.size(30.dp),
                                    contentDescription = "Add"
                                )
                            }
                        }


                    }
                    composable<ScreenB> {
                        NavViewModel.setNavController(navController)
                        AddScreen(NavViewModel)
                    }
                }
            }
        }
    }


}

@Serializable
object ScreenA

@Serializable
object ScreenB

