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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anabel.esdevenimentsvalencia.R;
import com.example.anabel.esdevenimentsvalencia.Servidor.TareaRest;
import com.example.anabel.esdevenimentsvalencia.Servidor.WebService;
import com.example.anabel.esdevenimentsvalencia.activities.LoginActivity;
import com.example.anabel.esdevenimentsvalencia.adapters.AdapterEventos;
import com.example.anabel.esdevenimentsvalencia.adapters.AdapterSuscripciones;
import com.example.anabel.esdevenimentsvalencia.models.Eventos;
import com.example.anabel.esdevenimentsvalencia.models.Suscripciones;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by Anabel on 23/05/2017.
 */

public class FragmentTeInteresa extends Fragment implements TareaRest.TareaRestListener, AdapterView.OnItemClickListener{

    private ListView listaInteresa;
    private List<Suscripciones> listaSuscripciones;
    public static List<Eventos>listadoEventos;
    private AdapterSuscripciones adapterSuscripciones;

    public FragmentTeInteresa() {
    }

    public static FragmentTeInteresa newInstance() {

        Bundle args = new Bundle();

        FragmentTeInteresa fragment = new FragmentTeInteresa();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment_teinteresa, container, false);

        listaInteresa = (ListView)view.findViewById(R.id.listaTeinteresa);
        listaInteresa.setOnItemClickListener(this);

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
        TareaRest tarea = new TareaRest(getContext(), WebService.CONSULTAR_SUSCRIPCION, "GET", WebService.URL_CONSULTAR_SUSCRIPCION+LoginActivity.loginUsuario.get(0).getId_usuario(), null, this);
        tarea.execute();

        return view;
    }

    @Override
    public void onTareaRestFinalizada(int codigoOperacion, int codigoRespuestaHttp, String respuestaJson) {
        if(codigoRespuestaHttp == 200 || respuestaJson != null && !respuestaJson.isEmpty()) {
            if (codigoOperacion == 9) {
                listaSuscripciones = WebService.procesarListaESuscripciones(respuestaJson);
                listadoEventos = WebService.procesarListaEventos(respuestaJson);
                if (listaSuscripciones != null) {

                    if (listaSuscripciones != null && adapterSuscripciones == null) {

                        adapterSuscripciones = new AdapterSuscripciones(getContext(), (ArrayList) listaSuscripciones);
                        listaInteresa.setAdapter(adapterSuscripciones);
                        adapterSuscripciones.notifyDataSetChanged();
                    }
                }
            }
            if(codigoOperacion == 10){
                Toasty.info(getContext(), "Se ha eliminado correctamente", Toast.LENGTH_SHORT, true).show();
            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       //Ejecutar una tarea para eliminar
        TareaRest tarea = new TareaRest(getActivity(), WebService.ELIMINAR_SUSCRIPCION,"DELETE",WebService.URL_ELIMINAR_SUSCRIPCION+listaSuscripciones.get(i).getIdUsuario()+"/"+listaSuscripciones.get(i).getIdEvento(),null,this);
        tarea.execute();

        //Eliminar el listView y los datos de la base de datos
        listaSuscripciones.remove(i);
        adapterSuscripciones.notifyDataSetChanged();
    }
}
