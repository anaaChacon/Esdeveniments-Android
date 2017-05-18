package com.example.anabel.esdevenimentsvalencia.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anabel.esdevenimentsvalencia.R;
import com.example.anabel.esdevenimentsvalencia.global.Constants;

public class MainActivity extends AppCompatActivity implements ImageView.OnClickListener{

    ImageView calendario, llocsInteres, interesa, compte;
    TextView titleEvents, titleInteres, titleInteresa, titleCompte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleEvents = (TextView)findViewById(R.id.titleEvents);
        titleInteres = (TextView)findViewById(R.id.titleLlocs);
        titleInteresa = (TextView)findViewById(R.id.titleInteresa);
        titleCompte = (TextView)findViewById(R.id.titleCompte);

        calendario = (ImageView)findViewById(R.id.imageCalendar);
        llocsInteres = (ImageView)findViewById(R.id.imageLlocs);
        interesa = (ImageView)findViewById(R.id.imageInteresa);
        compte = (ImageView)findViewById(R.id.imageCompte);

        calendario.setOnClickListener(this);
        llocsInteres.setOnClickListener(this);
        interesa.setOnClickListener(this);
        compte.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {

        Intent i = new Intent(this, ActivityCategories.class);
        Bundle bundle = new Bundle();

        switch(view.getId()){
            case R.id.imageCalendar:
                bundle.putString(Constants.TITLE, titleEvents.getText().toString());
                bundle.putInt(Constants.CODE, 0);
                i.putExtras(bundle);
                startActivityForResult(i, MainActivity.RESULT_OK);
                break;
            case R.id.imageLlocs:
                bundle.putString(Constants.TITLE, titleInteres.getText().toString());
                bundle.putInt(Constants.CODE, 1);
                i.putExtras(bundle);
                startActivityForResult(i, MainActivity.RESULT_OK);
                break;
            case R.id.imageInteresa:
                bundle.putString(Constants.TITLE, titleInteresa.getText().toString());
                bundle.putInt(Constants.CODE, 2);
                i.putExtras(bundle);
                startActivityForResult(i, MainActivity.RESULT_OK);
                break;
            case R.id.imageCompte:
                bundle.putString(Constants.TITLE, titleCompte.getText().toString());
                bundle.putInt(Constants.CODE, 3);
                i.putExtras(bundle);
                startActivityForResult(i, MainActivity.RESULT_OK);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }
}
