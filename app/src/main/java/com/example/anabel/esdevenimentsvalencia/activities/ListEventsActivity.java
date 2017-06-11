package com.example.anabel.esdevenimentsvalencia.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anabel.esdevenimentsvalencia.R;
import com.example.anabel.esdevenimentsvalencia.fragments.FragmentListEvents;
import com.example.anabel.esdevenimentsvalencia.global.Constants;

public class ListEventsActivity extends AppCompatActivity {

    public static TextView titleCategory, titleEvent;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_events);

        titleCategory = (TextView)findViewById(R.id.titleCat);
        titleEvent = (TextView)findViewById(R.id.titleCatEvent);

        Intent i = getIntent();
        titleCategory.setText(i.getStringExtra(Constants.TITLE));
        titleEvent.setText(i.getStringExtra(Constants.TITLE_ACTIVITY_LIST));
        position = i.getIntExtra(Constants.ITEM_MUSIC,-1);


        getSupportFragmentManager().beginTransaction().replace(R.id.containerList, FragmentListEvents.newInstance(position)).commitNow();
    }
}
