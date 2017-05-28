package com.example.anabel.esdevenimentsvalencia.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.anabel.esdevenimentsvalencia.R;
import com.example.anabel.esdevenimentsvalencia.global.Constants;

public class MainActivity extends AppCompatActivity implements ImageView.OnClickListener{

    private LinearLayout calendario, interesa, compte;
    private TextView titleEvents, titleInteresa, titleCompte;
    public static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
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

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//    }

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
                startActivity(i);
                break;
            default:
                break;
        }
    }



}
