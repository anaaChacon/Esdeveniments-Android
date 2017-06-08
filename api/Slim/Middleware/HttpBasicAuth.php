<?php
 
class HttpBasicAuth extends \Slim\Middleware
{
    /**
     * @var string
     */
    //Mensaje para el usuario no autenticado
    protected $realm;
    //Gracias a esto guardaremos un enlace a la base de datos para hacer comprobaciones
    protected $db;
 
    /**
     * Constructor
     *
     * @param   string  $realm      El realm de la autenticación HTTP
     * @param   PDO     $db         Una referencia a la conexión a la base de datos
     */
    public function __construct($db,$realm = 'Area protegida')
    {
        $this->realm = $realm;
        $this->db = $db;
    }
 
    /**
     * Acceso denegado
     *
     */   
    public function deny_access() {
        $res = $this->app->response();
        $res->status(401);
        $res->header('WWW-Authenticate', sprintf('Basic realm="%s"', $this->realm));        
    }
 
    /**
     * Authenticate 
     *
     * @param   string  $username   The HTTP Authentication username
     * @param   string  $password   The HTTP Authentication password     
     *
     */
        
    public function authenticate($username, $password) {
        //Aquí comprobaríamos que todos los caracteres del user son alfanuméricos
        if (!ctype_alnum($username)) {
            return false;
        }
        //Comprobamos si el usuario y la contraseña están fijados
        if (isset($username) && isset($password)) {
            //Encriptamos la contraseña en caso de que se guarde encriptada en la base de datos 
            //$password = crypt($password);
            // Comprobamos en la base de datos que user y pass coinciden
            try {
                // Va a devolver un objeto JSON con los datos de los alumnos.
                // Preparamos la consulta a la tabla.
                $consulta = $this->db->prepare("SELECT COUNT(*) FROM usuarios "
                        . "WHERE user='$username' AND pass='$password'");
                // Ejecutamos la consulta
                $consulta->execute();
                //Solo en fase de desarrollo: Comprobar que la consulta devuelve algún resultado
                // Almacenamos los resultados en un array asociativo.
                $resultados = $consulta->fetchColumn();
                //Si todo ha ido bien ($resultados tiene un valor diferente a FALSE)
                //se devuelve una lista de alumnos en formato JSON
                if ($resultados == 1) {
                    return true;
                } else {
                    return false;
                }
            } catch (PDOException $e) {
                $app->response()->setStatus(404);
                //Devolvemos el mensaje si es un error (desarrollo)
                //Si no hay resultados getmessage() (resultado vacío) devolverá una cadena vacía
                echo $e->getMessage();
            }            
        }
        else {
            return false;
        }
            
    }
 
    /**
     * Call
     * Este método comprueba las cabecerás de autenticación HTTP. 
     * Si el user y el pass de la cabecera están en la base de datos continúa 
     * con el middleware o con la aplicación web (get, post, put, delete)
     * sino se enviará una respuesta de 401 Authentication Required.
     */
    public function call()
    {
        $req = $this->app->request();
        $authUser = $req->headers('PHP_AUTH_USER');
        $authPass = $req->headers('PHP_AUTH_PW');
         
        if ($this->authenticate($authUser, $authPass)) {
            $this->next->call();
        } else {
            $this->deny_access();
        }
    }
}
