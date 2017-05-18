package com.example.anabel.esdevenimentsvalencia.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anabel.esdevenimentsvalencia.R;
import com.example.anabel.esdevenimentsvalencia.activities.ActivityListEvents;
import com.example.anabel.esdevenimentsvalencia.fragments.FragmentEventsCategories;
import com.example.anabel.esdevenimentsvalencia.models.Categorias;

import java.util.ArrayList;

/**
 * Created by Anabel on 07/05/2017.
 */

public class AdapterCategoria extends ArrayAdapter<Categorias>{

    private Context contexto;
    private ImageView imageSub;
    private TextView titleCategory;
    private RelativeLayout itemCategory;
    private Categorias categoria;
    private Callback callback;

    public AdapterCategoria(Callback callback, Context context,  ArrayList<Categorias> resource) {
        super(context, 0, resource);
        this.callback = callback;
        this.contexto = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent){

        if(convertView == null){
            convertView = LayoutInflater.from(contexto).inflate(R.layout.item_categoria, parent, false);
        }

        //Se usa la clase ConnectivityManager para obtener las características actuales de la conexión.
        ConnectivityManager gestorConexion = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        //Esta información la guardamos en un elemento del tipo NetworkInfo con el método getActiveNetworkInfo().
        NetworkInfo infoRed = gestorConexion.getActiveNetworkInfo();
        //Comprobaremos que existe conexión de red
        if (infoRed != null && infoRed.isConnected()) {
        }
        else {
            // Mostrar errores
            Toast.makeText(contexto,contexto.getString(R.string.connection),Toast.LENGTH_SHORT).show();
        }

        categoria = getItem(position);

        imageSub = (ImageView)convertView.findViewById(R.id.imageSub);
        titleCategory = (TextView)convertView.findViewById(R.id.titleCategories);
        itemCategory = (RelativeLayout)convertView.findViewById(R.id.itemCategory);

        itemCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onItemClick(position);
            }
        });

        Glide.with(contexto).load(FragmentEventsCategories.listaCategorias.get(position).getFoto_categoria()).placeholder(R.drawable.login_grey).centerCrop().into(imageSub);
        titleCategory.setText(categoria.getNombre());

        return convertView;
    }

    public interface Callback {
        void onItemClick(int position);
    }

}
