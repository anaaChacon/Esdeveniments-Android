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
            
                $consulta = $db->prepare("select id_categoria, nombre_categoria, foto_categoria from categorias");
                
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
    
    $app->get('/usuarios/:user', function ($user) use ($app,$db){
        
        try{
            
            //Preparamos la consulta
            
                $consulta = $db->prepare("select id_usuario, username,password from usuarios where username = '$user'");
                
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
                $consulta = $db->prepare("select id_usuario,username,email from usuarios where username='$username'");
                
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
           
                 $consulta = $db->prepare("select e.id_evento,e.nombre,e.fecha_inicio,e.hora_inicio,e.foto_miniatura,l.nombreLugar,l.direccion,l.informacion,l.imagen,l.coor_latitud, l.coor_longitud,e.idLugar,e.idCategoria 
											from eventos e, lugares l, categorias c
											where e.idLugar = l.id_lugar
                                            and e.idCategoria = c.id_categoria
											and c.id_categoria = $categoria
                                            and CONCAT(e.fecha_fin, ' ', e.hora_fin) > sysdate()
		    	                            ORDER BY CONCAT(e.fecha_fin, ' ', e.hora_fin)");
                
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
            
                 $consulta = $db->prepare("select id_lugar, nombreLugar, direccion, informacion, imagen from lugares");
                
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
    $app->get('/lugar/:id', function ($id) use ($app,$db){
        
        try{
            
            //Preparamos la consulta
            
                 $consulta = $db->prepare("select id_lugar, nombreLugar, direccion, informacion, imagen from lugares where id_lugar = $id");
                
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
    $app->get('/evento-principal/:categoria/:lugar', function ($categoria, $lugar) use ($app,$db){
        
        try{
            
            //Preparamos la consulta
            
                $consulta = $db->prepare("select e.id_evento,e.foto_principal, e.nombre, l.nombreLugar, e.fecha_inicio, e.hora_inicio, e.descripcion, e.info_secundaria, c.nombre_categoria, c.id_categoria, l.id_lugar  
										from eventos e, lugares l, categorias c
										where e.idCategoria = c.id_categoria
										and c.id_categoria = $categoria
										and e.idLugar = l.id_lugar
										and l.id_lugar = $lugar");
                
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
    $app->get('/suscripciones/:id', function ($id) use ($app,$db){
        
        try{
            
            //Preparamos la consulta
            
                $consulta = $db->prepare("SELECT s.idUsuario, s.idEvento, e.nombre, e.foto_miniatura, e.fecha_fin, e.hora_fin
										from suscripciones s, eventos e, usuarios u
										where s.idUsuario = u.id_usuario
										and u.id_usuario = $id
										and s.idEvento = e.id_evento");
                
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
    $app->get('/usuario/:user', function ($user) use ($app,$db){
        
        try{
            
            //Preparamos la consulta
            
                $consulta = $db->prepare("select * from usuarios where username = '$user'");
                
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
                    
                    echo null;
                
                }
        }
        
        catch (Exception $ex) {

            $app->response()->setStatus(404);
            echo $e->getMessage();
            
        }
        
    });
	
	//Obtener todas las valoraciones    
    $app->get('/info-lugar/:id', function ($id) use ($app,$db){
        
        try{
            
            //Preparamos la consulta
            
                $consulta = $db->prepare("select l.coor_latitud, l.coor_longitud, l.nombreLugar, e.idLugar, e.id_evento
										from lugares l, eventos e
										where l.id_lugar = e.idLugar
										and e.id_evento = $id");
                
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
  $app->post('/insertar-suscripcion/', function()use($app,$db) {
      try {
        //Obtenemos el contenido de la peticion
        $cuerpoPeticion = $app->request()->getBody();
        $suscripcion = json_decode($cuerpoPeticion);
        //Obtengo todos los atributos del objeto usuarioa (menos la imagen que es opcional)
		$id_usuario = $suscripcion->idUsuario;
		$id_evento = $suscripcion->idEvento;
		
        // Preparamos la insercion SQL 
        $consulta = $db->prepare("INSERT INTO suscripciones(idUsuario,idEvento)VALUES('$id_usuario','$id_evento');");
        //Realizamos la insercion
        $resultado = $consulta->execute();
		                  
            
                if ($resultado){

                    if ($consulta->rowCount()==1){
                        echo "Suscripcion aÃ±adido";
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

 $app->post('/insertar-usuario', function()use($app,$db) {
      try {
        //Obtenemos el contenido de la peticion
        $cuerpoPeticion = $app->request()->getBody();
        $usuario = json_decode($cuerpoPeticion);
        //Obtengo todos los atributos del objeto usuarioa (menos la imagen que es opcional)
		$user = $usuario->username;
		$pass = $usuario->password;
		$name = $usuario->nombre;
		$subrname = $usuario->apellidos;
		$age = $usuario->edad;
		$mail = $usuario->email;
		
        // Preparamos la insercion SQL 
        $consulta = $db->prepare("INSERT INTO usuarios(username,password,nombre,apellidos,edad,email)VALUES('$user','$pass','$name','$subrname','$age','$mail');");
        //Realizamos la insercion
        $resultado = $consulta->execute();
		                  
            
                if ($resultado){

                    if ($consulta->rowCount()==1){
                        echo "Usuario aÃ±adido";
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


     //Modifica valoracion
        
        $app->put('/modificar-usuario/:id',function($id)use($app,$db){
            
            try{  
            
                //Obtenemos el contenido de la peticion

                    $cuerpoPeticion = $app->request()->getBody();
                    $usuario = json_decode($cuerpoPeticion);

                //Obetenemos los atributos del objeto cliente
				
		            $user=$usuario->username;
					$pass=$usuario->password;
					$mail=$usuario->email;
                                       
                //Preparamos la consulta
                    
                    $consulta = $db->prepare("update usuarios set username='$user', password='$pass', email='$mail' where id_usuario=$id");
                    
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
    
        $app->delete('/eliminar-suscripcion/:id_usuario/:id_evento',function($id_usuario, $id_evento)use($app,$db){
            
            try{
                
                //Preparamos la consulta
                
                    $consulta = $db->prepare("delete from suscripciones where idUsuario=$id_usuario and idEvento=$id_evento");
                    
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