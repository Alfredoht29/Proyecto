<?php
include 'BdConnection.php';
$post = empty($_POST) ? json_decode(file_get_contents('php://input'), true) : $_POST;
$token= bin2hex(random_bytes(16));
$usuario=$post['usuario'];
$password=$post['password'];
$login="SELECT id FROM usuarios WHERE nickname ='$usuario' AND password ='$password'";
$query=mysqli_query($conexion,$login);
$q=mysqli_fetch_assoc($query);
echo $q['id']
/*$insert="INSERT INTO logs VALUES ('$token','$usuario')";
$resultado=mysqli_query($conexion,$insert);
header('Content-type: application/json');
echo json_encode( $token);*/
?>