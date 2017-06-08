package com.example.anabel.esdevenimentsvalencia.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anabel.esdevenimentsvalencia.R;
import com.example.anabel.esdevenimentsvalencia.global.Constants;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements ImageView.OnClickListener{

    private LinearLayout calendario, interesa, compte;
    private TextView titleEvents, titleInteresa, titleCompte;
    public static String username;
    public Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i = getIntent();
        username = i.getStringExtra(Constants.USERNAME);

        titleEvents = (TextView)findViewById(R.id.titleEvents);
        titleInteresa = (TextView)findViewById(R.id.titleInteresa);
        titleCompte = (TextView)findViewById(R.id.titleCompte);

        calendario = (LinearLayout)findViewById(R.id.layoutCalendari);
        interesa = (LinearLayout)findViewById(R.id.layoutInteresa);
        compte = (LinearLayout)findViewById(R.id.layoutCount);

        calendario.setOnClickListener(this);
        interesa.setOnClickListener(this);
        compte.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        Toasty.warning(this, "Si desea salir de la App, cierre sesión.", Toast.LENGTH_LONG, false).show();
    }

    @Override
    public void onClick(View view) {

        Intent i = new Intent(this, CategoriesActivity.class);
        Bundle bundle = new Bundle();

        switch(view.getId()){
            case R.id.layoutCalendari:
                bundle.putString(Constants.TITLE, titleEvents.getText().toString());
                bundle.putInt(Constants.CODE, 0);
                i.putExtras(bundle);
                startActivity(i);
                break;
            case R.id.layoutInteresa:
                bundle.putString(Constants.TITLE, titleInteresa.getText().toString());
                bundle.putInt(Constants.CODE, 2);
                i.putExtras(bundle);
                startActivity(i);
                break;
            case R.id.layoutCount:
                bundle.putString(Constants.TITLE, titleCompte.getText().toString());
                bundle.putInt(Constants.CODE, 3);
                i.putExtras(bundle);
                startActivityForResult(i, 1);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                this.finish();
            }
        }
    }
}
