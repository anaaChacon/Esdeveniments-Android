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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anabel.esdevenimentsvalencia.R;
import com.example.anabel.esdevenimentsvalencia.Servidor.TareaRest;
import com.example.anabel.esdevenimentsvalencia.Servidor.WebService;
import com.example.anabel.esdevenimentsvalencia.global.Constants;
import com.example.anabel.esdevenimentsvalencia.models.Lugares;

import org.w3c.dom.Text;

import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by Anabel on 21/05/2017.
 */

public class FragmentInfo extends Fragment implements TareaRest.TareaRestListener{

    private TextView lugarNombre, lugarCarrer, lugarDescripcion;
    private ImageView lugarImage;
    private List<Lugares> lloc;

    public static int id_evento;
    public static String nombreLugar,carrer,descripcionLugar,llocImage;

    public FragmentInfo(){}

    public static FragmentInfo newInstance(int idEvento, String nombre, String direccion, String descripcion, String imagen) {
        
        Bundle args = new Bundle();
        args.putInt(Constants.ID_EVENTO, idEvento);
        args.putString(Constants.NOMBRE_LUGAR, nombre);
        args.putString(Constants.DIRECCION, direccion);
        args.putString(Constants.INFORMACION, descripcion);
        args.putString(Constants.IMAGEN, imagen);

        id_evento = idEvento;
        nombreLugar = nombre;
        carrer = direccion;
        descripcionLugar = descripcion;
        llocImage = imagen;

        FragmentInfo fragment = new FragmentInfo();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_info, container, false);
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

        lugarNombre = (TextView)view.findViewById(R.id.lugarNombre);
        lugarCarrer = (TextView)view.findViewById(R.id.lugarCarrer);
        lugarDescripcion = (TextView)view.findViewById(R.id.lugarDescripcion);
        lugarImage = (ImageView)view.findViewById(R.id.lugarImage);

        lugarNombre.setText(nombreLugar);
        lugarCarrer.setText(carrer);
        lugarDescripcion.setText(descripcionLugar);
        Glide.with(this).load(llocImage).placeholder(R.drawable.login_grey).centerCrop().into(lugarImage);

        return view;
    }

    @Override
    public void onTareaRestFinalizada(int codigoOperacion, int codigoRespuestaHttp, String respuestaJson) {
        if(codigoRespuestaHttp == 200 || respuestaJson != null && !respuestaJson.isEmpty()) {
            if (codigoOperacion == 11) {
                lloc = WebService.procesarListaLugares(respuestaJson);


            }
        }
    }
}
