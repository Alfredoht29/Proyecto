<?php
include 'BdConnection.php';

$retorno = array(
    'exito' => false,
    'lista' => array()
);

$sql = "SELECT * FROM tickets ORDER BY fecha DESC";


$resultado = mysqli_query($conexion, $sql);

if($resultado){
    $retorno['exito'] = true;
    
    $tickets = array();
    $indice=0;
    while($fila = mysqli_fetch_assoc($resultado)){
        $tickets[$indice]["id"] = (int)$fila['id'];
        $tickets[$indice]["id_usuario"] = (int)$fila['id_usuario'];
        $tickets[$indice]["id_auditor"] = (int)$fila['id_auditor'];
        $tickets[$indice]["modelo"] = $fila['modelo'];
        $tickets[$indice]["marca"] = $fila['marca'];
        $tickets[$indice]["imagen"] = $fila['imagen'];
        $indice++;

    }
    
    $retorno['lista'] = $tickets;
}else{
    $retorno['mensaje'] = 'Error en BD';
}

header('Content-type: application/json');
echo json_encode($retorno);
?>