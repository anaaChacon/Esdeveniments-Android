package com.example.anabel.esdevenimentsvalencia.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anabel.esdevenimentsvalencia.R;
import com.example.anabel.esdevenimentsvalencia.Servidor.TareaRest;
import com.example.anabel.esdevenimentsvalencia.Servidor.WebService;
import com.example.anabel.esdevenimentsvalencia.adapters.AdapterCategoria;
import com.example.anabel.esdevenimentsvalencia.adapters.AdapterEventos;
import com.example.anabel.esdevenimentsvalencia.global.Constants;
import com.example.anabel.esdevenimentsvalencia.models.Eventos;
import com.example.anabel.esdevenimentsvalencia.models.Lugares;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by Anabel on 14/05/2017.
 */

public class FragmentListEvents extends Fragment implements TareaRest.TareaRestListener {

    public static List<Eventos> listaEventos;
    public static List<Lugares> listaLugares;
    private AdapterEventos adapterEventos;
    private ListView listEventos;
    public static int pot;

    public FragmentListEvents() {
    }

    public static FragmentListEvents newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt(Constants.ITEM_MUSIC, position);
        pot = position;
        FragmentListEvents fragment = new FragmentListEvents();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment_recycler, container, false);

        listEventos = (ListView)view.findViewById(R.id.listaEventos);

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
        TareaRest tarea2 = new TareaRest(getContext(), WebService.CONSULTAR_LUGARES, "GET", WebService.URL_CONSULTA_LUGARES, null, this);
        tarea2.execute();

        // Se lanza la tarea
        TareaRest tarea = new TareaRest(getContext(), WebService.CONSULTAR_EVENTOS, "GET", WebService.URL_CONSULTA_EVENTOS+pot, null, this);
        tarea.execute();

        return view;
    }

    @Override
    public void onTareaRestFinalizada(int codigoOperacion, int codigoRespuestaHttp, String respuestaJson) {

        if(codigoRespuestaHttp == 200 || respuestaJson != null && !respuestaJson.isEmpty()) {
            if (codigoOperacion == 4) {
                listaEventos = WebService.procesarListaEventos(respuestaJson);

                if (listaEventos != null) {

                    if (listaEventos != null && adapterEventos == null) {

                        adapterEventos = new AdapterEventos(getContext(), (ArrayList) listaEventos);
                        listEventos.setAdapter(adapterEventos);
                    }
                }else{
                    Toast.makeText(getActivity(), getString(R.string.direction_not_found), Toast.LENGTH_LONG).show();
                }
            }
            if(codigoOperacion == 5){
                listaLugares = WebService.procesarListaLugares(respuestaJson);

                if (listaLugares == null) {
                    Toast.makeText(getActivity(), getString(R.string.direction_not_found), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
