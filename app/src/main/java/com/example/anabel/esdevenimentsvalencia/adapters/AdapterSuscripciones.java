package com.example.anabel.esdevenimentsvalencia.adapters;

import android.content.Context;
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
import com.example.anabel.esdevenimentsvalencia.fragments.FragmentEventsCategories;
import com.example.anabel.esdevenimentsvalencia.fragments.FragmentListEvents;
import com.example.anabel.esdevenimentsvalencia.fragments.FragmentTeInteresa;
import com.example.anabel.esdevenimentsvalencia.models.Categorias;
import com.example.anabel.esdevenimentsvalencia.models.Eventos;
import com.example.anabel.esdevenimentsvalencia.models.Suscripciones;
import com.example.anabel.esdevenimentsvalencia.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Anabel on 07/05/2017.
 */

public class AdapterSuscripciones extends ArrayAdapter<Suscripciones>{

    private Context contexto;
    private ArrayList<Eventos>eventoses;
    private Suscripciones suscripciones;
    private Callback callback;

    private ImageView fotoEvento;
    private TextView nombreEvento, direccionEvento;
    private TextView fechaEvento;

    public AdapterSuscripciones(Context context, ArrayList<Suscripciones> resource) {
        super(context, 0, resource);
        this.contexto = context;

    }

    public View getView(final int position, View convertView, ViewGroup parent){

        if(convertView == null){
            convertView = LayoutInflater.from(contexto).inflate(R.layout.item_event, parent, false);
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

        suscripciones = getItem(position);

        fotoEvento = (ImageView)convertView.findViewById(R.id.imagenEvento);
        nombreEvento = (TextView)convertView.findViewById(R.id.eventoNombre);
        direccionEvento = (TextView)convertView.findViewById(R.id.eventoDireccion);
        fechaEvento = (TextView) convertView.findViewById(R.id.eventoFecha);

        Glide.with(contexto).load(FragmentTeInteresa.listadoEventos.get(position).getFoto_miniatura()).placeholder(R.drawable.login_grey).centerCrop().into(fotoEvento);
        nombreEvento.setText(FragmentTeInteresa.listadoEventos.get(position).getNombre());
        String date = Utils.parseDate(FragmentTeInteresa.listadoEventos.get(position).getFecha_fin() + " "+ FragmentTeInteresa.listadoEventos.get(position).getHora_fin());
        direccionEvento.setText("Fin: " + date);
        fechaEvento.setText(contexto.getString(R.string.cancelEvent));

        return convertView;
    }

    public interface Callback {
        void onItemClick(int position);
    }

}
