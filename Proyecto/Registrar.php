<?php
include 'BdConnection.php';
$retorno = array(
    'exito' => false
);
$post = empty($_POST) ? json_decode(file_get_contents('php://input'), true) : $_POST;
$nickname=$post['nickname'];
$password=$post['password'];
$rol=$post['rol'];
$insert="insert into usuarios values(null,'$nickname','$password','$rol',null)";
$resultado=mysqli_query($conexion,$insert);
if($resultado){
$retorno['exito']=true;
}
header('Content-type: application/json');
echo json_encode( $retorno);
?>