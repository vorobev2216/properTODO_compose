package com.example.todo_compose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class NavViewModel() : ViewModel() {
    private var _navController = MutableLiveData<NavController>()
    val navController = _navController

    fun setNavController(navController: NavController) {
        _navController.value = navController
    }

    private var _inputText = MutableLiveData<String>()
    val inputText = _inputText

    private fun getInputText(input: String){
        _inputText.value = input
    }

}