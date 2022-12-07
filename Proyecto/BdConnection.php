<?php

$host = 'localhost';
$user = 'root';
$password = '';
$database = 'luxuryauth';

$conexion = mysqli_connect($host, $user, $password, $database);

if(!$conexion){
    exit( mysqli_connect_error() );
}

mysqli_set_charset($conexion, 'utf8');

?>