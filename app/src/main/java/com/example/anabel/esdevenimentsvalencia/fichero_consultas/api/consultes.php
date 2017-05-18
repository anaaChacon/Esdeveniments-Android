<?php


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Importamos el FrameWork Slim
	require 'Slim/Slim.php';
    \Slim\Slim::registerAutoloader();

//Creamos una nueva instancia de la clase Slim que luego configuraremos
   $app = new \Slim\Slim();

// Indicamos el tipo de contenido y condificacion que devolvemos desde el 
// framework Slim por defecto
	$app->contentType('text/html; charset=utf-8');

// Definimos conexion de la base de datos.
// Lo haremos utilizando PDO con el driver mysql.

//Configuracion conexion BD local
	define('BD_SERVIDOR', 'localhost');
	define('BD_NOMBRE', 'qxm773');
	define('BD_USUARIO', 'admin');
	define('BD_PASSWORD', 'Lesron1');


// Hacemos la conexion a la base de datos con PDO.
// Para activar las collations en UTF8 podemos hacerlo al crear la conexion por PDO
// o bien una vez hecha la conexion con $db->exec("set names utf8");
	$db = new PDO('mysql:host=' . BD_SERVIDOR . ';dbname=' . BD_NOMBRE . ';charset=utf8', BD_USUARIO, BD_PASSWORD);


//Si se accede al recurso info mostramos informacion del servidor web
$app->get('/',function()use ($app){
    $app->response->setStatus(200);
    //En este caso mostramos una respuesta en texto plano
    echo "Servidor de anabel y benito funcionando.";
});
//Consulta para el listView nombre de categorias
	$app->get('/categorias', function () use ($app,$db){
        
        try{
            
            //Preparamos la consulta
            
                $consulta = $db->prepare("select id_categoria, nombre, foto_categoria from categorias");
                
            //Ejecutamos la consulta
                
                $consulta->execute();
                
            //Almacenamos los resultados de la consulta en un array
                
                $resultados = $consulta->fetchAll(PDO::FETCH_ASSOC);
                
            //Comprobamos si los resultados tienen un valor diferente a false
                
                if($resultados){
                    
                    //Establecemos el tipo de datos a enviar
                    
                        $app->response()->header('Content-Type', 'application/json');
                        
                    //Devolvemos el array como Json
                        
                        echo json_encode($resultados);
                    
                }
                
                else{
                    
                    throw new PDOException($consulta->errorInfo()[2]);
                
                }
        }
        
        catch (Exception $ex) {

            $app->response()->setStatus(404);
            echo $ex->getMessage();
            
        }
        
    });


 //get de clientes
    
    $app->get('/usuarios', function () use ($app,$db){
        
        try{
            
            //Preparamos la consulta
            
                $consulta = $db->prepare("select username,password from usuarios");
                
            //Ejecutamos la consulta
                
                $consulta->execute();
                
            //Almacenamos los resultados de la consulta en un array
                
                $resultados = $consulta->fetchAll(PDO::FETCH_ASSOC);
                
            //Comprobamos si los resultados tienen un valor diferente a false
                
                if($resultados){
                    
                    //Establecemos el tipo de datos a enviar
                    
                        $app->response()->header('Content-Type', 'application/json');
                        
                    //Devolvemos el array como Json
                        
                        echo json_encode($resultados);
                    
                }
                
                else{
                    
                    throw new PDOException($consulta->errorInfo()[2]);
                
                }
        }
        
        catch (Exception $ex) {

            $app->response()->setStatus(404);
            echo $e->getMessage();
            
        }
        
    });

    //ver los registros
    
    $app->get('/usuario-compte/:username', function ($username) use ($app,$db){
        
        try{
            
            //Preparamos la consulta
            //Cambiar
                $consulta = $db->prepare("select username,email from usuarios where username=$username");
                
            //Ejecutamos la consulta
                
                $consulta->execute();
                
            //Almacenamos los resultados de la consulta en un array
                
                $resultados = $consulta->fetchAll(PDO::FETCH_ASSOC);
                
            //Comprobamos si los resultados tienen un valor diferente a false
                
                if($resultados){
                    
                    //Establecemos el tipo de datos a enviar
                    
                        $app->response()->header('Content-Type', 'application/json');
                        
                    //Devolvemos el array como Json
                        
                        echo json_encode($resultados);
                    
                }
                
                else{
                    
                    throw new PDOException($consulta->errorInfo()[2]);
                
                }
        }
        
        catch (Exception $ex) {

            $app->response()->setStatus(404);
            echo $e->getMessage();
            
        }
        
    });

     //ver los registros
    $app->get('/evento/:categoria', function ($categoria) use ($app,$db){
        
        try{
            
            //Preparamos la consulta
            
                 $consulta = $db->prepare("select e.nombre,e.fecha_inicio,e.hora_inicio,e.foto_miniatura,l.nombreLugar 
											from eventos e, lugares l, categorias c
											where e.idLugar = l.id_lugar
                                            and e.idCategoria = c.id_categoria
											and c.id_categoria = $categoria");
                
            //Ejecutamos la consulta
                
                $consulta->execute();
                
            //Almacenamos los resultados de la consulta en un array
                
                $resultados = $consulta->fetchAll(PDO::FETCH_ASSOC);
                
            //Comprobamos si los resultados tienen un valor diferente a false
                
                if($resultados){
                    
                    //Establecemos el tipo de datos a enviar
                    
                        $app->response()->header('Content-Type', 'application/json');
                        
                    //Devolvemos el array como Json
                        
                        echo json_encode($resultados);
                    
                }
                
                else{
                    
                    throw new PDOException($consulta->errorInfo()[2]);
                
                }
        }
        
        catch (Exception $ex) {

            $app->response()->setStatus(404);
            echo $e->getMessage();
            
        }
        
    });

 //ver los registros
    $app->get('/lugares', function () use ($app,$db){
        
        try{
            
            //Preparamos la consulta
            
                 $consulta = $db->prepare("select id_lugar, nombreLugar from lugares");
                
            //Ejecutamos la consulta
                
                $consulta->execute();
                
            //Almacenamos los resultados de la consulta en un array
                
                $resultados = $consulta->fetchAll(PDO::FETCH_ASSOC);
                
            //Comprobamos si los resultados tienen un valor diferente a false
                
                if($resultados){
                    
                    //Establecemos el tipo de datos a enviar
                    
                        $app->response()->header('Content-Type', 'application/json');
                        
                    //Devolvemos el array como Json
                        
                        echo json_encode($resultados);
                    
                }
                
                else{
                    
                    throw new PDOException($consulta->errorInfo()[2]);
                
                }
        }
        
        catch (Exception $ex) {

            $app->response()->setStatus(404);
            echo $e->getMessage();
            
        }
        
    });


//Obtener todas las valoraciones    
    $app->get('/valoraciones/:id/:id_pelicula', function ($id, $id_pelicula) use ($app,$db){
        
        try{
            
            //Preparamos la consulta
            
                $consulta = $db->prepare("select valoracion_usuario from valoracion where id_usuario=$id and id_pelicula=$id_pelicula");
                
            //Ejecutamos la consulta
                
                $consulta->execute();
                
            //Almacenamos los resultados de la consulta en un array
                
                $resultados = $consulta->fetchAll(PDO::FETCH_ASSOC);
                
            //Comprobamos si los resultados tienen un valor diferente a false
                
                if($resultados){
                    
                    //Establecemos el tipo de datos a enviar
                    
                        $app->response()->header('Content-Type', 'application/json');
                        
                    //Devolvemos el array como Json
                        
                        echo json_encode($resultados);
                    
                }
                
                else{
                    
                    throw new PDOException($consulta->errorInfo()[2]);
                
                }
        }
        
        catch (Exception $ex) {

            $app->response()->setStatus(404);
            echo $e->getMessage();
            
        }
        
    });

    

//Insercion de un usuario
    $app->post('/insertar-usuario', function () use ($app,$db) {
      try {
        //Obtenemos el contenido de la peticion
        $cuerpoPeticion = $app->request()->getBody();
        $usuario = json_decode($cuerpoPeticion);
        //Obtengo todos los atributos del objeto usuarioa (menos la imagen que es opcional)
        
		$username=$usuario->username;
		$password=$usuario->password;
		$nombre=$usuario->nombre;
        $apellidos=$usuario->apellidos;
        $edad=$usuario->edad;
        $email=$usuario->email;
        
        // Preparamos la insercion SQL 
        $consulta = $db->prepare("Insert into usuarios(username,password,nombre,apellidos,edad,email)values('$username','$password','$nombre','$apellidos','$edad','$email');");
        //Realizamos la insercion
        $resultado = $consulta->execute();
		                  
            
                if ($resultado){

                    if ($consulta->rowCount()==1){
                        echo "Usruario aÃ±adido";
                    }
                    else {
                        //No exista el cliente
                            $app->response()->setStatus(404);
                            echo "Error en la insercion";
                    }

                }

                else {
                    //En caso de error Mysql
                        throw new PDOException($consulta->errorInfo()[2]);
                }
        
		} catch (PDOException $e) {
                  $app->response()->setStatus(404);
                  //Devolvemos el mensaje si es un error (desarrollo)
                  //Si no hay resultados getmessage()(resultado vacio) devolverÂ¡ una cadena vacia
                  echo $e->getMessage();
    }
});

//insertar una valoracion
$app->post('/insertar-valoracion',function()use($app,$db){
    try {
        //Obtenemos el contenido de la peticion
        $cuerpoPeticion = $app->request()->getBody();
        $valoracion = json_decode($cuerpoPeticion);
        //Obtengo todos los atributos del objeto cliente (menos la imagen que es opcional)
        
        $id_usuario=$valoracion->id_usuario;
        $id_pelicula=$valoracion->id_pelicula;
        $valoracion_usuario=$valoracion->valoracion_usuario;
        
        
        // Preparamos la insercion SQL
        $consulta = $db->prepare("Insert into valoracion(id_usuario,id_pelicula,valoracion_usuario)values('$id_usuario','$id_pelicula','$valoracion_usuario');");
        //Realizamos la insercion
        $resultado = $consulta->execute();
		
            
                if ($resultado){

                    if ($consulta->rowCount()==1){
                        echo "Valoracion anÃ±adida";
                    }
                    else {
                        //No exista el cliente
                            $app->response()->setStatus(404);
                            echo "Error en la insercion";
                    }

                }

                else {
                    //En caso de error Mysql
                        throw new PDOException($consulta->errorInfo()[2]);
                }
        
		} catch (PDOException $e) {
                  $app->response()->setStatus(404);
                  //Devolvemos el mensaje si es un error (desarrollo)
                  //Si no hay resultados getmessage()(resultado vacio) devolvera
                  echo $e->getMessage();
    }
});

     //Modifica valoracion
        
        $app->put('/valoracion/:id',function($id)use($app,$db){
            
            try{  
            
                //Obtenemos el contenido de la peticion

                    $cuerpoPeticion = $app->request()->getBody();
                    $valoracion = json_decode($cuerpoPeticion);

                //Obetenemos los atributos del objeto cliente
				
		    $valoracion_usuario=$valoracion ->valoracion_usuario;
                                       
                //Preparamos la consulta
                    
                    $consulta = $db->prepare("update valoracion set valoracion_usuario='$valoracion_usuario' where id_pelicula=$id");
                    
                //Ejecutamos la consulta
                    
                    $resultado = $consulta->execute();
            
                    if ($resultado){
                        
                        if ($consulta->rowCount()==1){
                             echo "Valoracion modificada";
                        }
                        else {
                            //No existía la valoracion
                             $app->response()->setStatus(404);
                              echo "No existe la valoracion especificada";
                        }

                    }
                    else {
                        //En caso de error Mysql
                        throw new PDOException($consulta->errorInfo()[2]);
                    }

            } catch (PDOException $e) {
                $app->response()->setStatus(404);
                //Si no hay resultados getmessage() devolverá una cadena vacía
                echo $e->getMessage();
            }    
             
        });

     //Borrado de valoracion
    
        $app->delete('/eliminar-valoracion/:id/:id_usuario',function($id, $id_usuario)use($app,$db){
            
            try{
                
                //Preparamos la consulta
                
                    $consulta = $db->prepare("delete from valoracion where id_pelicula=$id and id_usuario=$id_usuario");
                    
                //Ejecutamos la consulta
                
                    $resultado = $consulta->execute();
                    
                //Si ha eliminado la fila lo mostramos
                    
                if ($resultado){
                    if ($consulta->rowCount()==1){
                         echo "Valoracion borrada.";
                    }
                    else {
                        //No existe el cliente
                         $app->response()->setStatus(404);
                          echo "No se ha podido eliminar la valoracion.";
                    }
           
                }
                else {
                    //En caso de error Mysql
                    throw new PDOException($consulta->errorInfo()[2]);
                }
                
            } catch (Exception $ex) {

                $app->response()->setStatus(404);
                echo $e->getMessage();
                
            }
            
        });



$app->run();