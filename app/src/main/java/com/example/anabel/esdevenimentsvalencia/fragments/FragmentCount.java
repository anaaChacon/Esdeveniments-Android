package com.example.anabel.esdevenimentsvalencia.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anabel.esdevenimentsvalencia.R;
import com.example.anabel.esdevenimentsvalencia.activities.LoginActivity;
import com.example.anabel.esdevenimentsvalencia.activities.MainActivity;
import com.example.anabel.esdevenimentsvalencia.models.Usuarios;
import com.example.anabel.esdevenimentsvalencia.Servidor.TareaRest;
import com.example.anabel.esdevenimentsvalencia.Servidor.WebService;
import com.google.gson.Gson;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * Created by Anabel on 07/05/2017.
 */

public class FragmentCount extends Fragment implements View.OnClickListener, TareaRest.TareaRestListener{

    private EditText campoUser, campoEmail, campoPassword;
    private Button botonGuardar;
    private TextView cerrarSesion;
    private Usuarios usuario;


    public static ArrayList<Usuarios> listaUsuario;

    public FragmentCount() {
    }

    public static FragmentCount newInstance() {
        Bundle args = new Bundle();
        FragmentCount fragment = new FragmentCount();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_cuenta, container, false);

        campoUser = (EditText)view.findViewById(R.id.etUsername);
        campoEmail = (EditText)view.findViewById(R.id.etEmail);
        campoPassword = (EditText)view.findViewById(R.id.etPassword);
        cerrarSesion = (TextView)view.findViewById(R.id.singOff);
        botonGuardar = (Button)view.findViewById(R.id.buttonSave);

        botonGuardar.setOnClickListener(this);
        cerrarSesion.setOnClickListener(this);


        // Se lanza la tarea
        TareaRest tarea = new TareaRest(getContext(), WebService.CONSULTAR_USUARIO, "GET", WebService.URL_CUENTA_USUARIO+ MainActivity.username, null, this);
        tarea.execute();


        return view;
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.buttonSave){

            usuario = new Usuarios();
            usuario.setUsername(campoUser.getText().toString());
            usuario.setEmail(campoEmail.getText().toString());
            usuario.setPassword(campoPassword.getText().toString());
            //Creamos un objeto GSON
            Gson gson = new Gson();

            //Convertimos un objeto valoracion en una cadena JSON
            String parametroJson = gson.toJson(usuario);
            // Se lanza la tarea
            TareaRest tarea = new TareaRest(getContext(), WebService.UPDATE_USER, "PUT", WebService.URL_UPDATE_USER+listaUsuario.get(0).getId_usuario(), parametroJson, this);
            tarea.execute();
        }
        if(view.getId() == R.id.singOff){
            getActivity().finish();
        }
    }

    @Override
    public void onTareaRestFinalizada(int codigoOperacion, int codigoRespuestaHttp, String respuestaJson) {
        if(codigoRespuestaHttp == 200 || respuestaJson != null && !respuestaJson.isEmpty()) {

            if (codigoOperacion == 3) {

                listaUsuario = WebService.procesarListaUsuarios(respuestaJson);

                if (listaUsuario != null) {

                    if (listaUsuario != null) {

                        campoUser.setText(listaUsuario.get(0).getUsername());
                        campoEmail.setText(listaUsuario.get(0).getEmail());
                    }
                }
            }
            if(codigoOperacion == 12){
                Toasty.info(getActivity(), getContext().getString(R.string.update), Toast.LENGTH_LONG, true).show();
            }
        }
    }
}
