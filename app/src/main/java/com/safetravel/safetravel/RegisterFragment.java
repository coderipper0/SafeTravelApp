package com.safetravel.safetravel;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    //Instancia de los componentes
    private EditText email, name, lastname, pass, confirmpass,  date;
    int capacidad = 10;
    User[] arreglo = new User[capacidad];
    int contador;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnRegistrar = view.findViewById(R.id.btnRegistrar);
        email = view.findViewById(R.id.edtEmailR);
        name = view.findViewById(R.id.edtNameR);
        lastname = view.findViewById(R.id.edtLasNameR);
        pass = view.findViewById(R.id.edtConfPassR);
        confirmpass = view.findViewById(R.id.edtConfPassR);

        for (int i=0; i<capacidad; i++){
            arreglo[i] = new User();
        }

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.fragments_login_container, new LoginFragment());
                ft.commit();
            }
        });
    }

    public void register(){
        if(contador < capacidad){
                    if(confirmpass.getText().toString().equals(pass.getText().toString())){
                        arreglo[contador].setEmail(email.getText().toString());
                        arreglo[contador].setName(name.getText().toString());
                        arreglo[contador].setLastname(lastname.getText().toString());
                        arreglo[contador].setPass(pass.getText().toString());
                        contador++;

                        Toast.makeText(getContext(), "Registrado", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(),"Contraseña diferente", Toast.LENGTH_SHORT).show();
                    }
        }else {
            Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
        }
    }
}