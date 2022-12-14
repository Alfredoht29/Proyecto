<?php 
include 'BdConnection.php';
$post = empty($_POST) ? json_decode(file_get_contents('php://input'), true) : $_POST;
$id_usuario = $post['usuario'];
$modelo = $post['modelo'];
$marca = $post['marca'];
$imagen = $post['imagen'];
$insert="INSERT INTO tickets VALUES (null,'$id_usuario',null,'$modelo','$marca','$imagen',NOW())";
$resultado=mysqli_query($conexion,$insert);
if($resultado){
    $retorno['exito']   = true;
    $retorno['mensaje'] = 'Guardado correctamente';
}else{
    $retorno['mensaje'] = 'Error en BD';
}
header('Content-type: application/json');
echo json_encode($retorno);
?>