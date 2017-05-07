package com.example.anabel.esdevenimentsvalencia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button botonLogin, botonRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        botonLogin = (Button)findViewById(R.id.botonEntrar);
        botonRegistro = (Button)findViewById(R.id.botonRegistrar);

        botonLogin.setOnClickListener(this);
        botonRegistro.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.botonEntrar){

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        if(view.getId() == R.id.botonRegistrar){

            Intent i = new Intent(this, RegistreActivity.class);
            startActivity(i);
        }
    }
}
