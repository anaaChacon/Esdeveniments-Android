package com.example.anabel.esdevenimentsvalencia.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.anabel.esdevenimentsvalencia.R;
import com.example.anabel.esdevenimentsvalencia.activities.LoginActivity;
import com.example.anabel.esdevenimentsvalencia.models.Usuarios;
import com.example.anabel.esdevenimentsvalencia.Servidor.TareaRest;
import com.example.anabel.esdevenimentsvalencia.Servidor.WebService;

/**
 * Created by Anabel on 07/05/2017.
 */

public class FragmentCount extends Fragment implements View.OnClickListener, TareaRest.TareaRestListener{

    private EditText campoUser, campoEmail, campoPassword;
    private Button botonGuardar;
    Usuarios usuario;
    public static Usuarios listaUsuario;

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
        botonGuardar = (Button)view.findViewById(R.id.buttonSave);

        botonGuardar.setOnClickListener(this);

        // Se lanza la tarea
        TareaRest tarea = new TareaRest(getContext(), WebService.CONSULTAR_USUARIO, "GET", WebService.URL_CUENTA_USUARIO+ LoginActivity.campoUsuario.getText().toString(), null, this);
        tarea.execute();


        return view;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onTareaRestFinalizada(int codigoOperacion, int codigoRespuestaHttp, String respuestaJson) {
        if(codigoRespuestaHttp == 200 || respuestaJson != null && !respuestaJson.isEmpty()) {

            if (codigoOperacion == 3) {

                listaUsuario = WebService.procesarListaUsuario(respuestaJson);

                if (listaUsuario != null) {

                    if (listaUsuario != null) {

                        campoUser.setText(listaUsuario.getUsername());
                        campoEmail.setText(listaUsuario.getEmail());
                    }
                }
            }
        }
    }
}
