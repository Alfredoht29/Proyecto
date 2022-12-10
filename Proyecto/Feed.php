<?php
include 'BdConnection.php';
$retorno = array(
    'exito' => false,
    'usuario' => "",
    'rol' => 0
);
$post = empty($_POST) ? json_decode(file_get_contents('php://input'), true) : $_POST;
$token = $post['token'];
$query = "SELECT  logs.token,logs.id_usuario,usuarios.nickname,usuarios.rol FROM logs INNER JOIN usuarios on logs.id_usuario=usuarios.id WHERE token='$token'";
$resultado = mysqli_query($conexion, $query);
$resultadoarr= mysqli_fetch_row($resultado);
if ($resultado) {
    $retorno['exito'] = true;
    $retorno['usuario']=$resultadoarr[2];
    $retorno['rol'] = $resultadoarr[3];
}
header('Content-type: application/json');
echo json_encode($retorno);
?>