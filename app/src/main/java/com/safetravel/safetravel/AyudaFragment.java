package com.safetravel.safetravel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AyudaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AyudaFragment extends Fragment {
    private TextView correo;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AyudaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AyudaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AyudaFragment newInstance(String param1, String param2) {
        AyudaFragment fragment = new AyudaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        Button btnEnviar = view.findViewById(R.id.btnEnviar);
        EditText titulo = view.findViewById((R.id.txtTitulo));
        EditText comentarios = view.findViewById((R.id.txt_comentarios));
        EditText telefono = view.findViewById((R.id.edittxt_Telefono));



        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnEnviar.setOnClickListener(view -> Toast.makeText(getContext(), "Datos enviados de "+ correo.getText().toString(), Toast.LENGTH_SHORT).show());
                titulo.setText("");
                comentarios.setText("");
                telefono.setText("");
            }

        });




    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_ayuda, container, false);
        //Por medio de la instacia vista se accede al botón del fragment
        //tal como se realiza con el Activity
        //Instancias
        Button enviar = vista.findViewById(R.id.btnEnviar);
        correo = vista.findViewById(R.id.txtTitulo);
        //Se define el escucha para el botón
        enviar.setOnClickListener(view -> Toast.makeText(getContext(), "Datos enviados de "+ correo.getText().toString(), Toast.LENGTH_SHORT).show());
        return vista;

    }

}