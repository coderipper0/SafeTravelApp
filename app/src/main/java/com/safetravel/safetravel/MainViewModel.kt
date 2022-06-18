package com.safetravel.safetravel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository = protoRepositorio(application)

    val email = repository.readProto.asLiveData()

    fun updateValue(email: String, nombre: String, apellido: String, contra: String, edad: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateValue(email, nombre, apellido, contra, edad)
    }
}