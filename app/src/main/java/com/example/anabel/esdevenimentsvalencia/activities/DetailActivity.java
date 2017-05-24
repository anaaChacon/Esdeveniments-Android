package com.example.anabel.esdevenimentsvalencia.activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anabel.esdevenimentsvalencia.R;
import com.example.anabel.esdevenimentsvalencia.Servidor.TareaRest;
import com.example.anabel.esdevenimentsvalencia.Servidor.WebService;
import com.example.anabel.esdevenimentsvalencia.adapters.Pager;
import com.example.anabel.esdevenimentsvalencia.fragments.FragmentArribar;
import com.example.anabel.esdevenimentsvalencia.fragments.FragmentInfo;
import com.example.anabel.esdevenimentsvalencia.fragments.FragmentListEvents;
import com.example.anabel.esdevenimentsvalencia.fragments.FragmentPrincipal;
import com.example.anabel.esdevenimentsvalencia.global.Constants;
import com.example.anabel.esdevenimentsvalencia.models.Suscripciones;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import es.dmoral.toasty.Toasty;

@RequiresApi(api = Build.VERSION_CODES.M)
public class DetailActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    private TabLayout tabLayout;
    private ViewPager frameTab;
    private TextView titlePrincipal;
    public static int idLugar, idCategoria, idEvento;
    public static String nombreLugar, direccionLugar, imagenLugar, informacion;

    private Suscripciones suscripcion;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();

        idCategoria = i.getIntExtra(Constants.ID_CATEGORY, -1);
        idLugar = i.getIntExtra(Constants.ID_LUGAR, -1);
        idEvento = i.getIntExtra(Constants.ID_EVENTO, -1);
        nombreLugar = i.getStringExtra(Constants.NOMBRE_LUGAR);
        direccionLugar = i.getStringExtra(Constants.DIRECCION);
        imagenLugar = i.getStringExtra(Constants.IMAGEN);
        informacion = i.getStringExtra(Constants.INFORMACION);

        frameTab = (ViewPager)findViewById(R.id.frameTab);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        titlePrincipal = (TextView)findViewById(R.id.titlePrincipal);

        titlePrincipal.setText(ListEventsActivity.titleCategory.getText().toString() + " - " +ListEventsActivity.titleEvent.getText().toString());

        tabLayout.addTab(tabLayout.newTab().setText(R.string.principal));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.arrive));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.infoLloc));

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());
        frameTab.setAdapter(adapter);
        frameTab.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        frameTab.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
