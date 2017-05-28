package com.example.anabel.esdevenimentsvalencia.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anabel.esdevenimentsvalencia.R;
import com.example.anabel.esdevenimentsvalencia.Servidor.TareaRest;
import com.example.anabel.esdevenimentsvalencia.Servidor.WebService;
import com.example.anabel.esdevenimentsvalencia.models.Usuarios;
import com.google.gson.Gson;

import es.dmoral.toasty.Toasty;

public class RegistreActivity extends AppCompatActivity implements View.OnClickListener, TareaRest.TareaRestListener {

    private Button registro;
    private EditText campoNombre, campoApellidos, campoEdad, campoEmail, campoUsuari, campoContrasenya;
    private Usuarios usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

        campoNombre = (EditText)findViewById(R.id.campoNombre);
        campoApellidos = (EditText)findViewById(R.id.campoApellidos);
        campoEdad = (EditText)findViewById(R.id.campoEdad);
        campoEmail = (EditText)findViewById(R.id.campoEmail);
        campoUsuari = (EditText)findViewById(R.id.campoUsuari);
        campoContrasenya = (EditText)findViewById(R.id.campoContrasenya);

        registro = (Button)findViewById(R.id.botonRegistro);
        registro.setOnClickListener(this);

        //Se usa la clase ConnectivityManager para obtener las características actuales de la conexión.
        ConnectivityManager gestorConexion = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        //Esta información la guardamos en un elemento del tipo NetworkInfo con el método getActiveNetworkInfo().
        NetworkInfo infoRed = gestorConexion.getActiveNetworkInfo();
        //Comprobaremos que existe conexión de red
        if (infoRed != null && infoRed.isConnected()) {
        }
        else {
            // Mostrar errores
            //Toast.makeText(this,"No hay conexión de red.",Toast.LENGTH_SHORT).show();
            Toasty.info(this, getString(R.string.connection), Toast.LENGTH_SHORT, true).show();
        }
    }


    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.botonRegistro){
            //Obtenemos los campos introducidos y los igualamos a las variables


            //Comprobar si los campos están vacíos o no
            if(!campoNombre.getText().toString().isEmpty() || !campoApellidos.getText().toString().isEmpty() ||
                    !campoEmail.getText().toString().isEmpty() || !campoEdad.getText().toString().isEmpty() ||
                    !campoUsuari.getText().toString().isEmpty() || !campoContrasenya.getText().toString().isEmpty()){

                //Instancio el objeto compra
                usuario = new Usuarios();

                usuario.setNombre(campoNombre.getText().toString());
                usuario.setApellidos(campoApellidos.getText().toString());
                usuario.setEmail(campoEmail.getText().toString());
                usuario.setEdad(Integer.parseInt(campoEdad.getText().toString()));
                usuario.setUsername(campoUsuari.getText().toString());
                usuario.setPassword(campoContrasenya.getText().toString());

                //Creamos un objeto GSON
                Gson gson = new Gson();
                //Convertimos un objeto cliente en una cadena JSON
                String parametroJson = gson.toJson(usuario);

                TareaRest tarea = new TareaRest(this, WebService.CODIGO_INSERTAR_USUARIO,"POST", WebService.URL_INSERTAR_USUARIO, parametroJson, this);
                tarea.execute();
            }
            else{
                Toasty.warning(this, getString(R.string.emptyFileds), Toast.LENGTH_SHORT, true).show();
            }
        }
    }

    @Override
    public void onTareaRestFinalizada(int codigoOperacion, int codigoRespuestaHttp, String respuestaJson) {

        if(codigoRespuestaHttp == 200 || respuestaJson != null && !respuestaJson.isEmpty()) {

            if (codigoOperacion == 1) {

                Toasty.success(this, getString(R.string.successRegistre), Toast.LENGTH_SHORT, true).show();

                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);

                this.finish();

            } else {
                Toasty.error(this, getString(R.string.notRegistre), Toast.LENGTH_SHORT, true).show();
            }
        }

        }
}
