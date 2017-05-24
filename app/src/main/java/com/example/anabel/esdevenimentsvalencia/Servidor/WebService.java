package com.example.anabel.esdevenimentsvalencia.Servidor;

import com.example.anabel.esdevenimentsvalencia.fragments.DialogFragmentConnection;
import com.example.anabel.esdevenimentsvalencia.models.Categorias;
import com.example.anabel.esdevenimentsvalencia.models.Eventos;
import com.example.anabel.esdevenimentsvalencia.models.Lugares;
import com.example.anabel.esdevenimentsvalencia.models.Suscripciones;
import com.example.anabel.esdevenimentsvalencia.models.Usuarios;
import java.lang.reflect.Type;
import java.util.ArrayList;
import android.content.Context;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Anabel on 06/05/2017.
 */

public class WebService {

    private static Context context;

    public WebService(Context context) {
        this.context = context;
    }

    /** CONSTANTES RELACIONADAS CON PELÍCULAS **/
    public static String URL_LOGIN_USUARIO = "http://"+DialogFragmentConnection.dir+"/api/consultes.php/usuarios/";
    public static final int CODIGO_CONSULTA_LOGIN_USUARIO = 0;

    /*REGISTRAR USUARIO*/
    public static String URL_INSERTAR_USUARIO = "http://"+DialogFragmentConnection.dir+"/api/consultes.php/insertar-usuario";
    public static final int CODIGO_INSERTAR_USUARIO = 1;

    /*OBTENER CADA UNA DE LAS VALORACIONES*/
    public static String URL_CONSULTA_CATEGORIAS = "http://"+DialogFragmentConnection.dir+"/api/consultes.php/categorias";
    public static final int CONSULTA_CATEGORIA_ITEM = 2;

    /*CONSULTAR VALIDAR LOGIN USER*/
    public static String URL_CUENTA_USUARIO = "http://"+DialogFragmentConnection.dir+"/api/consultes.php/usuario-compte/";
    public static final int CONSULTAR_USUARIO = 3;

    /*CONSULTAR VALIDAR LOGIN USER*/
    public static String URL_CONSULTA_EVENTOS = "http://"+DialogFragmentConnection.dir+"/api/consultes.php/evento/";
    public static final int CONSULTAR_EVENTOS = 4;

    /*CONSULTAR VALIDAR LOGIN USER*/
    public static String URL_CONSULTA_LUGARES = "http://"+DialogFragmentConnection.dir+"/api/consultes.php/lugares";
    public static final int CONSULTAR_LUGARES = 5;

    /*CONSULTAR VALIDAR LOGIN USER*/
    public static String URL_CONSULTA_EVENTO_DETAIL = "http://"+DialogFragmentConnection.dir+"/api/consultes.php/evento-principal/";
    public static final int CONSULTAR_EVENTO_DETAIL = 6;

    /*CONSULTAR VALIDAR LOGIN USER*/
    public static String URL_CONSULTA_LUGAR = "http://"+DialogFragmentConnection.dir+"/api/consultes.php/lugar/";
    public static final int CONSULTAR_LUGAR = 7;

    /*CONSULTAR VALIDAR LOGIN USER*/
    public static String URL_INSERTAR_SUSCRIPCION = "http://"+DialogFragmentConnection.dir+"/api/consultes.php/insertar-suscripcion/";
    public static final int INSERTAR_SUSCRIPCION = 8;

    /*CONSULTAR VALIDAR LOGIN USER*/
    public static String URL_CONSULTAR_SUSCRIPCION = "http://"+DialogFragmentConnection.dir+"/api/consultes.php/suscripciones/";
    public static final int CONSULTAR_SUSCRIPCION = 9;

    /*CONSULTAR VALIDAR LOGIN USER*/
    public static String URL_ELIMINAR_SUSCRIPCION = "http://"+DialogFragmentConnection.dir+"/api/consultes.php/eliminar-suscripcion/";
    public static final int ELIMINAR_SUSCRIPCION = 10;

    /*CONSULTAR VALIDAR LOGIN USER*/
    public static String URL_LUGAR_INFO = "http://"+DialogFragmentConnection.dir+"/api/consultes.php/info-lugar/";
    public static final int LUGAR_INFO = 11;

    /*CONSULTAR VALIDAR LOGIN USER*/
    public static String URL_UPDATE_USER = "http://"+DialogFragmentConnection.dir+"/api/consultes.php/modificar-usuario/";
    public static final int UPDATE_USER = 12;


    /** MÉTODOS RELACIONADOS CON PELÍCULAS**/
    //Convierte un objeto JSON en una lista de películas
    public static ArrayList<Categorias> procesarListaCategorias(String objetoJSON){
        Gson gson = new Gson();
        try {
            Type tipoLista = new TypeToken<ArrayList<Categorias>>(){}.getType();
            ArrayList<Categorias> categorias = gson.fromJson(objetoJSON,tipoLista);
            return categorias;
        } catch (Exception e){
            //Publico un Toast en la activity que nos llamó
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    /** MÉTODOS RELACIONADOS CON PELÍCULAS**/
    //Convierte un objeto JSON en una lista de películas
    public static ArrayList<Lugares> procesarListaLugares(String objetoJSON){
        Gson gson = new Gson();
        try {
            Type tipoLista = new TypeToken<ArrayList<Lugares>>(){}.getType();
            ArrayList<Lugares> lugares = gson.fromJson(objetoJSON,tipoLista);
            return lugares;
        } catch (Exception e){
            //Publico un Toast en la activity que nos llamó
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    //Convierte un objeto JSON en unam película
    public Categorias procesarCategoria (String objetoJSON){
        Gson gson = new Gson();
        try {
            Categorias categoria = gson.fromJson(objetoJSON,Categorias.class);
            return categoria;
        } catch (Exception e){
            //Publico un Toast en la activity que nos llamó
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    /** MÉTODOS RELACIONADOS CON PELÍCULAS **/
    //Convierte un objeto JSON en una lista de películas
    public static ArrayList<Usuarios> procesarListaUsuarios(String objetoJSON){
        Gson gson = new Gson();
        try {
            Type tipoLista = new TypeToken<ArrayList<Usuarios>>(){}.getType();
            ArrayList<Usuarios> clientes = gson.fromJson(objetoJSON,tipoLista);
            return clientes;
        } catch (Exception e){
            //Publico un Toast en la activity que nos llamó
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    //Convierte un objeto JSON en una lista de películas
    public static Usuarios procesarListaUsuario(String objetoJSON){
        Gson gson = new Gson();
        try {
            Type tipoLista = new TypeToken<Usuarios>(){}.getType();
            Usuarios usuario = gson.fromJson(objetoJSON,tipoLista);
            return usuario;
        } catch (Exception e){
            //Publico un Toast en la activity que nos llamó
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    // MÉTODOS RELACIONADOS CON PELÍCULAS
    //Convierte un objeto JSON en una lista de valoraciones
    public static ArrayList<Eventos> procesarListaEventos(String objetoJSON){
        Gson gson = new Gson();
        try {
            Type tipoLista = new TypeToken<ArrayList<Eventos>>(){}.getType();
            ArrayList<Eventos> eventos = gson.fromJson(objetoJSON,tipoLista);
            return eventos;
        } catch (Exception e){
            //Publico un Toast en la activity que nos llamó
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    // MÉTODOS RELACIONADOS CON PELÍCULAS
    //Convierte un objeto JSON en una lista de valoraciones
    public static ArrayList<Suscripciones> procesarListaESuscripciones(String objetoJSON){
        Gson gson = new Gson();
        try {
            Type tipoLista = new TypeToken<ArrayList<Suscripciones>>(){}.getType();
            ArrayList<Suscripciones> suscripciones = gson.fromJson(objetoJSON,tipoLista);
            return suscripciones;
        } catch (Exception e){
            //Publico un Toast en la activity que nos llamó
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }


}

