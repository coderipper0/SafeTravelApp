package com.safetravel.safetravel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ayuda extends AppCompatActivity {
    private TextView ayuda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
        View vista = findViewById(R.id.txtProblema);
        //Instancias
        Button enviar = vista.findViewById(R.id.btnEnviar);
        ayuda = vista.findViewById(R.id.txtProblema);
        //Se define el escucha para el botón
        enviar.setOnClickListener(view -> Toast.makeText(getApplicationContext(), "Datos enviados de "+ ayuda.getText().toString(), Toast.LENGTH_SHORT).show());

    }
}