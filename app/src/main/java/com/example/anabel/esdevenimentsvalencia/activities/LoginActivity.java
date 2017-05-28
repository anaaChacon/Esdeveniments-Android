package com.example.anabel.esdevenimentsvalencia.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.anabel.esdevenimentsvalencia.R;
import com.example.anabel.esdevenimentsvalencia.Servidor.TareaRest;
import com.example.anabel.esdevenimentsvalencia.Servidor.WebService;
import com.example.anabel.esdevenimentsvalencia.fragments.DialogFragmentConnection;
import com.example.anabel.esdevenimentsvalencia.global.Constants;
import com.example.anabel.esdevenimentsvalencia.models.Usuarios;

import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, TareaRest.TareaRestListener{

    private Button botonLogin, botonRegistro;
    private EditText campoUsuario, campoPassword;
    public static ArrayList<Usuarios> loginUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_login);

        campoUsuario = (EditText)findViewById(R.id.campUsuari);
        campoPassword = (EditText)findViewById(R.id.campContrasenya);
        botonLogin = (Button)findViewById(R.id.botonEntrar);
        botonRegistro = (Button)findViewById(R.id.botonRegistrar);

        botonLogin.setOnClickListener(this);
        botonRegistro.setOnClickListener(this);

        DialogFragmentConnection.newInstance().show(getFragmentManager(), null);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.botonEntrar){

            if(campoUsuario.getText().toString().isEmpty() || campoPassword.getText().toString().isEmpty()){
                Toasty.warning(this, getString(R.string.emptyFileds), Toast.LENGTH_LONG, false).show();
            }
            else {
                // Se lanza la tarea
                TareaRest tarea = new TareaRest(this, WebService.CODIGO_CONSULTA_LOGIN_USUARIO, "GET", WebService.URL_LOGIN_USUARIO+campoUsuario.getText().toString(), null, this);
                tarea.execute();
            }
        }

        if(view.getId() == R.id.botonRegistrar){
            Intent i = new Intent(this, RegistreActivity.class);
            startActivity(i);
        }
    }


    @Override
    public void onTareaRestFinalizada(int codigoOperacion, int codigoRespuestaHttp, String respuestaJson) {

        if(codigoRespuestaHttp == 200 || respuestaJson != null && !respuestaJson.isEmpty()){

            if(codigoOperacion == 0){

                loginUsuario = WebService.procesarListaUsuarios(respuestaJson);

                if(loginUsuario != null){

                    //Comprobar si coinciden los datos
                    for(int i = 0; i < loginUsuario.size(); i++){

                        if(loginUsuario.get(i).getUsername().equals(campoUsuario.getText().toString()) && !loginUsuario.get(i).getPassword().equals(campoPassword.getText().toString())){
                            Toasty.info(this, getString(R.string.passwordIncorrect), Toast.LENGTH_LONG, true).show();
                        }

                        else if(loginUsuario.get(i).getUsername().equals(campoUsuario.getText().toString()) && loginUsuario.get(i).getPassword().equals(campoPassword.getText().toString())){
                            Intent intencion = new Intent(this, MainActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.USERNAME, campoUsuario.getText().toString());
                            intencion.putExtras(bundle);
                            startActivity(intencion);
                            this.finish();
                        }
                    }
                }
            }
        }else{
            Toasty.info(this, getString(R.string.userNotExist), Toast.LENGTH_LONG, true).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
