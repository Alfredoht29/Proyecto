<?php
include 'BdConnection.php';
$mensaje="error";
$post = empty($_POST) ? json_decode(file_get_contents('php://input'), true) : $_POST;
$nickname=$post['nickname'];
$password=$post['password'];
$rol=$post['rol'];
$insert="insert into usuarios values(null,'$nickname','$password','$rol',null)";
$resultado=mysqli_query($conexion,$insert);
?>