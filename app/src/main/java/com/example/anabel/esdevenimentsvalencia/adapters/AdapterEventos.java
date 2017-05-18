package com.example.anabel.esdevenimentsvalencia.adapters;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anabel.esdevenimentsvalencia.R;
import com.example.anabel.esdevenimentsvalencia.fragments.FragmentListEvents;
import com.example.anabel.esdevenimentsvalencia.models.Eventos;
import com.example.anabel.esdevenimentsvalencia.models.Lugares;
import com.example.anabel.esdevenimentsvalencia.utils.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Anabel on 14/05/2017.
 */

public class AdapterEventos extends ArrayAdapter<Eventos> {

    private Context contexto;
    private ImageView fotoEvento;
    private TextView nombreEvento, direccionEvento;
    private TextView fechaEvento;
    private Eventos evento;
    private Lugares lugar;

    String dia;

    public AdapterEventos(Context context, ArrayList<Eventos> resource) {
        super(context, 0, resource);
        this.contexto = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public View getView(final int position, View convertView, ViewGroup parent) {
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

        evento = getItem(position);

        fotoEvento = (ImageView)convertView.findViewById(R.id.imagenEvento);
        nombreEvento = (TextView)convertView.findViewById(R.id.eventoNombre);
        direccionEvento = (TextView)convertView.findViewById(R.id.eventoDireccion);
        fechaEvento = (TextView) convertView.findViewById(R.id.eventoFecha);

        Glide.with(contexto).load(FragmentListEvents.listaEventos.get(position).getFoto_miniatura()).placeholder(R.drawable.login_grey).centerCrop().into(fotoEvento);
        nombreEvento.setText(evento.getNombre());
        if(FragmentListEvents.listaLugares!=null){
           direccionEvento.setText(FragmentListEvents.listaLugares.get(position).getNombreLugar());
        }else{
            direccionEvento.setText(contexto.getString(R.string.unknown_place));
        }
        //Formato para el dia y el mes en número
        String date = Utils.parseDate(evento.getFecha_inicio() + " "+ evento.getHora_inicio());
        fechaEvento.setText(date);

        return convertView;
    }
}
