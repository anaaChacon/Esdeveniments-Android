package com.example.anabel.esdevenimentsvalencia.fragments;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.anabel.esdevenimentsvalencia.R;
import es.dmoral.toasty.Toasty;

import com.example.anabel.esdevenimentsvalencia.Servidor.TareaRest;
import com.example.anabel.esdevenimentsvalencia.Servidor.WebService;
import com.example.anabel.esdevenimentsvalencia.global.Constants;
import com.example.anabel.esdevenimentsvalencia.models.Lugares;

import java.util.List;
import java.util.Locale;

/**
 * Created by Anabel on 21/05/2017.
 */

public class FragmentArribar extends Fragment implements TareaRest.TareaRestListener{

    //Gestor de ubicación
    private Button mapa;
    private LocationManager locationManager;
    private Double latitud, longitud;
    private List<Lugares> place;

    private TextView nomLloc, carrer;

    public static int id_evento;
    public static String nombre, direccion;

    public FragmentArribar(){}

    public static FragmentArribar newInstance(int idEvento, String nombreLugar, String dirrecion) {
        
        Bundle args = new Bundle();
        args.putInt(Constants.ID_EVENTO, idEvento);
        args.putString(Constants.NOMBRE_LUGAR, nombreLugar);
        args.putString(Constants.DIRECCION, dirrecion);
        id_evento = idEvento;
        nombre = nombreLugar;
        direccion = dirrecion;
        FragmentArribar fragment = new FragmentArribar();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_arribar, container, false);
        //Se usa la clase ConnectivityManager para obtener las características actuales de la conexión.
        ConnectivityManager gestorConexion = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        //Esta información la guardamos en un elemento del tipo NetworkInfo con el método getActiveNetworkInfo().
        NetworkInfo infoRed = gestorConexion.getActiveNetworkInfo();
        //Comprobaremos que existe conexión de red
        if (infoRed != null && infoRed.isConnected()) {
        }
        else {
            // Mostrar errores
            Toasty.warning(getContext(), getContext().getString(R.string.connection), Toast.LENGTH_SHORT, true).show();
        }
        // Se lanza la tarea
        TareaRest tarea = new TareaRest(getContext(), WebService.LUGAR_INFO, "GET", WebService.URL_LUGAR_INFO+id_evento, null, this);
        tarea.execute();

        nomLloc = (TextView)view.findViewById(R.id.etiquetaNombreArribar);
        carrer = (TextView)view.findViewById(R.id.etiquetaDireccion);
        nomLloc.setText(nombre);
        carrer.setText(direccion);

        mapa = (Button)view.findViewById(R.id.mapa);
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Inicializar variables lat y long
                latitud = place.get(0).getCoor_latitud();
                longitud = place.get(0).getCoor_longitud();
                // Obtenemos una referencia al gestor de ubicación para poder usar la geolocalización
                locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
               // Armamos el Uri con el que llamaremos a la app de mapas
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitud, longitud);

               // Armamos un intent con esa Uri y la acción "ver"
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });



        return view;
    }


    @Override
    public void onTareaRestFinalizada(int codigoOperacion, int codigoRespuestaHttp, String respuestaJson) {
        if(codigoRespuestaHttp == 200 || respuestaJson != null && !respuestaJson.isEmpty()) {
            if (codigoOperacion == 11) {
                place = WebService.procesarListaLugares(respuestaJson);


            }
        }
    }
}
