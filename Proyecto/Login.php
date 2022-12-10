<?php
include 'BdConnection.php';
$retorno = array(
    'exito' => false,
    'token' => null
);
$post = empty($_POST) ? json_decode(file_get_contents('php://input'), true) : $_POST;
$token = bin2hex(random_bytes(16));
$usuario = $post['usuario'];
$password = $post['password'];
$login = "SELECT id FROM usuarios WHERE nickname ='$usuario' AND password ='$password'";
$query = mysqli_query($conexion, $login);
$id = mysqli_fetch_row($query);
$id = (int)$id[0];
$cant = mysqli_num_rows($query);
if ($cant>0) {
    $delete = "DELETE FROM logs WHERE id_usuario='$id'";
    $insert = "INSERT INTO logs VALUES (null,'$token','$id')";
    $delete = mysqli_query($conexion, $delete);
    $resultado = mysqli_query($conexion, $insert);
    $retorno['exito'] = true;
    $retorno['token'] = $token;
}
header('Content-type: application/json');
echo json_encode($retorno);
?>