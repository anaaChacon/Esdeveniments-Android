package com.example.anabel.esdevenimentsvalencia.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.anabel.esdevenimentsvalencia.R;
import com.example.anabel.esdevenimentsvalencia.Servidor.TareaRest;
import com.example.anabel.esdevenimentsvalencia.Servidor.WebService;
import com.example.anabel.esdevenimentsvalencia.activities.ListEventsActivity;
import com.example.anabel.esdevenimentsvalencia.activities.LoginActivity;
import com.example.anabel.esdevenimentsvalencia.global.Constants;
import com.example.anabel.esdevenimentsvalencia.models.Eventos;
import com.example.anabel.esdevenimentsvalencia.models.Lugares;
import com.example.anabel.esdevenimentsvalencia.models.Suscripciones;
import com.example.anabel.esdevenimentsvalencia.utils.Utils;
import com.google.gson.Gson;

import java.util.List;
import es.dmoral.toasty.Toasty;

/**
 * Created by Anabel on 21/05/2017.
 */

public class FragmentPrincipal extends Fragment implements TareaRest.TareaRestListener, View.OnClickListener{

    public static int id_categoria, id_lugar;
    public static List<Eventos> infoEventos;
    public static List<Lugares> listaLlocs;

    private ImageView imagePrincipal;
    private ImageButton imageSuscripcion;
    private TextView etiquetaNombreEvento, etiquetaNombreLugar, etiquetaFechaEvento, etiquetaDescripcionEvento, etiquetaDescripcionEvento2, etiquetaInfoEvento, etiquetaCategoria, more, less;
    private int infoLines;
    private Suscripciones suscripciones;
    private Suscripciones suscripcion;
    public static boolean click;

    public FragmentPrincipal(){}

    public static FragmentPrincipal newInstance(int idCategory, int idLugar) {
        
        Bundle args = new Bundle();
        args.putInt(Constants.ID_CATEGORY, idCategory);
        args.putInt(Constants.ID_LUGAR, idLugar);

        id_categoria = idCategory;
        id_lugar = idLugar;

        FragmentPrincipal fragment = new FragmentPrincipal();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment_principal, container, false);
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


        imagePrincipal = (ImageView)view.findViewById(R.id.imagePrincipal);
        etiquetaNombreEvento = (TextView)view.findViewById(R.id.nombreEvento);
        etiquetaNombreLugar = (TextView)view.findViewById(R.id.nombreLugar);
        etiquetaFechaEvento = (TextView)view.findViewById(R.id.fechaEvento);
        etiquetaDescripcionEvento = (TextView)view.findViewById(R.id.descripcionEvento);
        etiquetaDescripcionEvento2 = (TextView)view.findViewById(R.id.descripcionEvento2);
        etiquetaInfoEvento = (TextView)view.findViewById(R.id.infoEvento);
        etiquetaCategoria = (TextView)view.findViewById(R.id.categoriaEvento);
        imageSuscripcion = (ImageButton)view.findViewById(R.id.imageSuscripcion);
        more = (TextView)view.findViewById(R.id.detail_more);
        less = (TextView)view.findViewById(R.id.detail_less);

        more.setVisibility(View.VISIBLE);
        less.setVisibility(View.GONE);

        more.setOnClickListener(this);
        less.setOnClickListener(this);
        imageSuscripcion.setOnClickListener(this);

        if(click == true){
            imageSuscripcion.setBackground(getResources().getDrawable(R.drawable.star_ic_circle_yellow));
            imageSuscripcion.setEnabled(false);
        }else{
            imageSuscripcion.setBackground(getResources().getDrawable(R.drawable.star_ic_circle));
        }

        // Se lanza la tarea
        TareaRest tarea2 = new TareaRest(getContext(), WebService.CONSULTAR_LUGAR, "GET", WebService.URL_CONSULTA_LUGAR+id_lugar, null, this);
        tarea2.execute();

        // Se lanza la tarea
        TareaRest tarea = new TareaRest(getContext(), WebService.CONSULTAR_EVENTO_DETAIL, "GET", WebService.URL_CONSULTA_EVENTO_DETAIL+id_categoria+"/"+id_lugar, null, this);
        tarea.execute();

        return view;
    }

    @Override
    public void onTareaRestFinalizada(int codigoOperacion, int codigoRespuestaHttp, String respuestaJson) {

        if(codigoRespuestaHttp == 200 || respuestaJson != null && !respuestaJson.isEmpty()) {
            if (codigoOperacion == 6) {
                infoEventos = WebService.procesarListaEventos(respuestaJson);
                if(infoEventos != null){
                    Glide.with(getActivity()).load(infoEventos.get(0).getFoto_principal()).placeholder(R.drawable.login_grey).centerCrop().into(imagePrincipal);
                    etiquetaNombreEvento.setText(infoEventos.get(0).getNombre());
                    etiquetaNombreLugar.setText(listaLlocs.get(0).getNombreLugar());
                    //Formato para el dia y el mes en número
                    String date = Utils.parseDate(infoEventos.get(0).getFecha_inicio() + " "+ infoEventos.get(0).getHora_inicio());
                    etiquetaFechaEvento.setText(date);
                    etiquetaDescripcionEvento.setText(infoEventos.get(0).getDescripcion());
                    etiquetaInfoEvento.setText(infoEventos.get(0).getInfo_secundaria());
                    etiquetaCategoria.setText(getString(R.string.category) + " " + ListEventsActivity.titleEvent.getText().toString());


                    etiquetaDescripcionEvento.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            etiquetaDescripcionEvento2.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                            float lineHeight = etiquetaDescripcionEvento.getLineHeight();
                            infoLines = (int) (etiquetaDescripcionEvento.getHeight() / lineHeight);

                            if (infoLines <= 3) {
                                more.setVisibility(View.GONE);
                                less.setVisibility(View.GONE);
                            }

                            if(infoLines > 3){
                                more.setVisibility(View.VISIBLE);
                                less.setVisibility(View.GONE);
                            }

                            etiquetaDescripcionEvento.setMaxLines(3);
                        }
                    });
                }
            }
            if(codigoOperacion == 7){
                listaLlocs = WebService.procesarListaLugares(respuestaJson);

                if (listaLlocs == null) {
                    Toast.makeText(getActivity(), getString(R.string.direction_not_found), Toast.LENGTH_LONG).show();
                }
            }
            if (codigoOperacion == 8) {
                Toasty.info(getActivity(), getString(R.string.suscription), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.detail_more){
            etiquetaDescripcionEvento.setEllipsize(null);
            etiquetaDescripcionEvento.setMaxLines(infoLines);
            more.setVisibility(View.GONE);
            less.setVisibility(View.VISIBLE);
        }

        if(view.getId() == R.id.detail_less){
            etiquetaDescripcionEvento.setMaxLines(3);
            etiquetaDescripcionEvento.setEllipsize(TextUtils.TruncateAt.END);
            more.setVisibility(View.VISIBLE);
            less.setVisibility(View.GONE);
        }
        if(view.getId() == R.id.imageSuscripcion){
            imageSuscripcion.setBackground(getResources().getDrawable(R.drawable.star_ic_circle_yellow));

            suscripciones = new Suscripciones();
            suscripciones.setIdEvento(infoEventos.get(0).getId_evento());
            suscripciones.setIdUsuario(LoginActivity.loginUsuario.get(0).getId_usuario());

            //Guardamos la valoración
            this.suscripcion = suscripciones;

            //Creamos un objeto GSON
            Gson gson = new Gson();

            //Convertimos un objeto valoracion en una cadena JSON
            String parametroJson = gson.toJson(suscripciones);

            TareaRest tarea = new TareaRest(getActivity(), WebService.INSERTAR_SUSCRIPCION,"POST",WebService.URL_INSERTAR_SUSCRIPCION,parametroJson,this);
            tarea.execute();

            click = true;
            imageSuscripcion.setEnabled(false);
        }
    }




}
