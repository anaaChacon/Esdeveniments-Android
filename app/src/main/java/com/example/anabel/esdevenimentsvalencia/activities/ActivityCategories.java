package com.example.anabel.esdevenimentsvalencia.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.anabel.esdevenimentsvalencia.R;
import com.example.anabel.esdevenimentsvalencia.fragments.FragmentEventsCategories;
import com.example.anabel.esdevenimentsvalencia.fragments.FragmentCount;
import com.example.anabel.esdevenimentsvalencia.global.Constants;

public class ActivityCategories extends AppCompatActivity {

    private TextView etiquetaTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        etiquetaTitle = (TextView)findViewById(R.id.titlePrincipal);

        Intent i = getIntent();
        etiquetaTitle.setText(i.getStringExtra(MainActivity.TITLE));


        switch (i.getIntExtra(MainActivity.CODE, -1)){
            case Constants.CODE_CALENDAR:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, FragmentEventsCategories.newInstance()).commitNow();
                break;
            case Constants.CODE_COMPTE:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, FragmentCount.newInstance()).commitNow();
        }


    }
}
