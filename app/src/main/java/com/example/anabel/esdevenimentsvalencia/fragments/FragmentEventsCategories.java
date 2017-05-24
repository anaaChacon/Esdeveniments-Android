package com.example.anabel.esdevenimentsvalencia.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;
import com.example.anabel.esdevenimentsvalencia.R;
import com.example.anabel.esdevenimentsvalencia.Servidor.TareaRest;
import com.example.anabel.esdevenimentsvalencia.Servidor.WebService;
import com.example.anabel.esdevenimentsvalencia.activities.ListEventsActivity;
import com.example.anabel.esdevenimentsvalencia.adapters.AdapterCategoria;
import com.example.anabel.esdevenimentsvalencia.global.Constants;
import com.example.anabel.esdevenimentsvalencia.models.Categorias;
import java.util.ArrayList;
import java.util.List;
import es.dmoral.toasty.Toasty;

/**
 * Created by Anabel on 07/05/2017.
 */

public class FragmentEventsCategories extends Fragment implements TareaRest.TareaRestListener, AdapterCategoria.Callback{

    public static List<Categorias> listaCategorias;
    private AdapterCategoria adapterCategorias;
    private GridView gridCategories;
    public static String title;

    public FragmentEventsCategories() {
    }

    public static FragmentEventsCategories newInstance(String titleCategoria) {
        Bundle args = new Bundle();
        args.putString(Constants.TITLE, titleCategoria);
        title = titleCategoria;
        FragmentEventsCategories fragment = new FragmentEventsCategories();
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment_categories, container, false);

        gridCategories = (GridView)view.findViewById(R.id.gridCategories);

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
        TareaRest tarea = new TareaRest(getContext(), WebService.CONSULTA_CATEGORIA_ITEM, "GET", WebService.URL_CONSULTA_CATEGORIAS, null, this);
        tarea.execute();

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onTareaRestFinalizada(int codigoOperacion, int codigoRespuestaHttp, String respuestaJson) {

        if(codigoRespuestaHttp == 200 || respuestaJson != null && !respuestaJson.isEmpty()) {

            if (codigoOperacion == 2) {
                listaCategorias = WebService.procesarListaCategorias(respuestaJson);

                if (listaCategorias != null) {

                    if (listaCategorias != null && adapterCategorias == null) {

                        adapterCategorias = new AdapterCategoria(this, getActivity(), (ArrayList) listaCategorias);
                        gridCategories.setAdapter(adapterCategorias);

                    }
                }
            }
        }
    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(getActivity(), ListEventsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TITLE_ACTIVITY_LIST, listaCategorias.get(position).getNombre_categoria());
        bundle.putString(Constants.TITLE, title);
        bundle.putInt(Constants.ITEM_MUSIC, listaCategorias.get(position).getId_categoria());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
