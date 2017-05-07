package com.example.anabel.esdevenimentsvalencia.Servidor;

import com.example.anabel.esdevenimentsvalencia.models.Categorias;
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
    public static String URL_LOGIN_USUARIO = "http://84.123.121.34:8080/api/consultes.php/usuarios";
    public static final int CODIGO_CONSULTA_LOGIN_USUARIO = 0;

    /*REGISTRAR USUARIO*/
    public static String URL_INSERTAR_USUARIO = "http://84.123.121.34:8080/api/consultes.php/insertar-usuario";
    public static final int CODIGO_INSERTAR_USUARIO = 1;

    /*OBTENER CADA UNA DE LAS VALORACIONES*/
    public static String URL_CONSULTA_CATEGORIAS = "http://84.123.121.34:8080/api/consultes.php/categorias";
    public static final int CONSULTA_CATEGORIA_ITEM = 2;

    /*CONSULTAR VALIDAR LOGIN USER*/
    public static String URL_CUENTA_USUARIO = "http://84.123.121.34:8080/api/consultes.php/usuario-compte/";
    public static final int CONSULTAR_USUARIO = 3;

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

    /** MÉTODOS RELACIONADOS CON PELÍCULAS
    //Convierte un objeto JSON en una lista de valoraciones
    public static ArrayList<Valoracion> procesarListaValoraciones(String objetoJSON){
        Gson gson = new Gson();
        try {
            Type tipoLista = new TypeToken<ArrayList<Valoracion>>(){}.getType();
            ArrayList<Valoracion> valoraciones = gson.fromJson(objetoJSON,tipoLista);
            return valoraciones;
        } catch (Exception e){
            //Publico un Toast en la activity que nos llamó
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }**/



}

