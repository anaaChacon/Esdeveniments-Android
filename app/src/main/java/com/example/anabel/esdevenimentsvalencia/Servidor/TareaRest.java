package com.example.anabel.esdevenimentsvalencia.Servidor;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.Toast;

import com.example.anabel.esdevenimentsvalencia.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by Anabel on 06/05/2017.
 */



//Tarea asíncrona que nos permite realizar la conexión de red en segundo plano para operaciones con alumnos
public class TareaRest extends AsyncTask <Void, String, String> {

    private Context contexto;
    private int codigoOperacion;
    private String operacionREST;
    private String urlRecurso;
    private String parametroJSON;
    private TareaRestListener actividadOyente;
    private String usuario;
    private String pass;
    private ProgressDialog progressDialog;
    private int codigoRespuestaHttp;

    //Este constructor nos permite configurar la tarea (abajo las explicaciones)
    public TareaRest(Context contexto, int codigoOperacion, String operacionREST, String urlRecurso, String parametroJSON, TareaRestListener actividadOyente) {
        this.contexto = contexto;
        //- Fijamos el requestcode para luego devolvérselo a la activity y que sepa para qué nos llamó
        this.codigoOperacion = codigoOperacion;
        // - Fijamos la operación sobre alumnos que se solicitará al servidor
        this.operacionREST = operacionREST;
        // - Fijamos el urlRecurso sobre el cuál se realizará la operación
        this.urlRecurso = urlRecurso;
        // - Fijamos el parámetro POST que usaremos en inserciones y modificaciones
        this.parametroJSON = parametroJSON;
        // - Suscribimos la actividad que nos llama a nuestro evento de finalización
        // con el código HTTP de resultado y, en su caso, el objeto JSON de respuesta
        this.actividadOyente = actividadOyente;
        //Instanciamos el progress dialog
        progressDialog = new ProgressDialog(contexto);
    }

    //Este constructor nos permite configurar la tarea CON AUTENTICACIÓN
    public TareaRest(Context contexto, int codigoOperacion, String operacionREST, String urlRecurso, String parametroJSON, TareaRestListener actividadOyente, String usuario, String pass) {
        this.contexto = contexto;
        //- Fijamos el requestcode para luego devolvérselo a la activity y que sepa para qué nos llamó
        this.codigoOperacion = codigoOperacion;
        // - Fijamos la operación sobre alumnos que se solicitará al servidor
        this.operacionREST = operacionREST;
        // - Fijamos el urlRecurso sobre el cuál se realizará la operación
        this.urlRecurso = urlRecurso;
        // - Fijamos el parámetro POST que usaremos en inserciones y modificaciones
        this.parametroJSON = parametroJSON;
        // - Suscribimos la actividad que nos llama a nuestro evento de finalización
        // con el código HTTP de resultado y, en su caso, el objeto JSON de respuesta
        this.actividadOyente = actividadOyente;
        // - Fijamos el usuario y la contraseña que nos permitirán crear una conexión autenticada
        this.usuario = usuario;
        this.pass = pass;
        //Instanciamos el progress dialog
        progressDialog = new ProgressDialog(contexto);
    }

    //Gracias a esta interface podremos comunicarle a la actividad que nos ha invocado:
    //- El código de la operación con la que nos ha llamado
    //- El código de respuesta HTTP devuelto por el servidor
    //- La respuesta JSON en caso de una consulta (sino será null)el resultado
    public interface TareaRestListener {
        void onTareaRestFinalizada(int codigoOperacion, int codigoRespuestaHttp, String respuestaJson);
    }

    protected void onPreExecute() {

        //Lanzamos el diálogo de comienzo de la comunicación
        progressDialog.setMessage(TareaRest.this.contexto.getString(R.string.loading));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    // Esto se ejecutará en un hilo secundario después de onPreExecute
    protected String doInBackground(Void... vacio) {

        /************ Construcción de una petición POST al servidor y obtención de la respuesta***********/
        BufferedReader bufferLectura = null;
        HttpURLConnection conexion = null;
        String cuerpoRecibido = null;

        try {

            // Creamos un objeto URL que contendrá la web donde realizar peticiones
            URL url = new URL(urlRecurso);

            //Creamos la conexión con el servidor web
            conexion = (HttpURLConnection) url.openConnection();
            //Definimos cuánto tiempo esperaremos respuesta del servidor
            conexion.setConnectTimeout(5000);
            //Establecemos el tipo de petición que realizaremos (GET, POST,PUT,DELETE)
            conexion.setRequestMethod(operacionREST);

            if (usuario != null && pass != null) {
                String cadenaAutBasica = usuario + ":" + pass;
                conexion.setRequestProperty("Authorization", "Basic " +
                        Base64.encodeToString(cadenaAutBasica.getBytes(), Base64.NO_WRAP));
            }

            // ...
            // ...
            if (operacionREST.equals("POST") || operacionREST.equals("PUT")) {
                //Fijamos el tipo de dato a incluir
                conexion.setRequestProperty("Content-Type", "application/json");
                //Activamos el método POST
                conexion.setDoOutput(true);
                //Creamos un flujo de salida de datos
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conexion.getOutputStream());
                //Escribimos los datos (valor de editText)
                outputStreamWriter.write(parametroJSON);
                //Limpiamos el flujo de salida de datos
                outputStreamWriter.flush();
                //Cerramos el flujo
                outputStreamWriter.close();
            }

            // Obtenemos la respuesta del servidor
            //Instanciamos el buffer de lectura a través del flujo de entrada de la conexión
            codigoRespuestaHttp = conexion.getResponseCode();
            bufferLectura = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            //Usamos un constructor de Strings
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            // Vamos leyendo línea a línea la respuesta del servidor hasta que no quedan líneas
            while ((line = bufferLectura.readLine()) != null) {
                // Añadimos las líneas al constructor de Strings
                stringBuilder.append(line + "\n");
            }
            // Añadimos las respuesta del servidor al String msgContenido
            cuerpoRecibido = stringBuilder.toString();
        } catch (Exception ex) {
            //Mostramos el error (servidor no existente, servidor no responde, etc.)
            publishProgress(ex.toString());
        } finally {
            //Finalizamos las conexiones y los flujos de datos
            conexion.disconnect();
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                } catch (Exception ex) {
                    //Mostramos el error
                    publishProgress("Error al cerrar el buffer de lectura: " + ex.toString());
                }
            }

            //Devolvemos el mensaje (respuesta o error) para que onPostExecute lo trate
            return cuerpoRecibido;
        }
    }

    //Esto se ejecuta en el hilo principal y cuando el hilo secundario ha acabado su tarea
    protected void onPostExecute(String cuerpoRecibido) {
        // Cerramos el diálogo de progreso
        progressDialog.dismiss();
        //Llamamos a la actividad que se nos ha suscrito para devolverle el código http de respuesta
        //y, en su caso, el objeto JSON de respuesta
        actividadOyente.onTareaRestFinalizada(codigoOperacion, codigoRespuestaHttp, cuerpoRecibido);
    }

    @Override
    protected void onProgressUpdate(String... error) {
        //Mostramos las incidencias de conexión con un Toast en la pantalla de la actividad que nos llamó
        Toast.makeText(contexto, error[0], Toast.LENGTH_LONG).show();
    }
}
