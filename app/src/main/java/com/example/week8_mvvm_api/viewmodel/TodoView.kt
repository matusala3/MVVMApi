package com.example.week8_mvvm_api.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week8_mvvm_api.model.Todo
import com.example.week8_mvvm_api.model.TodosApi
import kotlinx.coroutines.launch

class TodoView: ViewModel() {
    val todos = mutableStateListOf<Todo>()
       // private set
    init {
       getTodoList()
    }

    private fun getTodoList(){
        viewModelScope.launch {
            var todosApi: TodosApi? = null
            try{
                todosApi = TodosApi!!.getInstance()
                todos.clear()
                todos.addAll(todosApi.getTodos())
            } catch (e:Exception){
                Log.d("TODOVIEWMODEL", e.message.toString())
            }
        }
    }
}