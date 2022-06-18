package com.safetravel.safetravel

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class protoRepositorio(context: Context) {

    private val dataStore: DataStore<usuario> = context.createDataStore(
            "datos_usuario",
            serializer = serializador()
    )

    val readProto: Flow<usuario> = dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    Log.d("Error", exception.message.toString())
                    emit(usuario.getDefaultInstance())
                } else {
                    throw exception
                }

            }

    suspend fun updateValue(email: String, nombre: String, apellido: String, contra: String, edad: Int){
        dataStore.updateData { preference->
            preference.toBuilder().setEmail(email).setNombre(nombre).setApellido(apellido).setContra(contra).setEdad(edad).build()
        }
    }
}