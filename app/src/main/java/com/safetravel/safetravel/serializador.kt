package com.safetravel.safetravel

import androidx.datastore.CorruptionException
import androidx.datastore.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

class serializador : Serializer<usuario> {
    override fun readFrom(input: InputStream): usuario {
        try {
            return usuario.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("No se pudo leer el proto", exception)
        }
    }

    override fun writeTo(t: usuario, output: OutputStream) {
        t.writeTo(output)
    }
}