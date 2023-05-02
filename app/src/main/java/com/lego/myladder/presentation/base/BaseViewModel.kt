package com.lego.myladder.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class BaseViewModel : ViewModel() {

    private val job = SupervisorJob()
    val viewModelScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}