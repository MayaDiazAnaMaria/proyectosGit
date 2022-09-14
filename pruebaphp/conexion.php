<?php
function conectar(){
    
    $user="root";
    $password="";
    $server="localhost";
    $db="jsMysql";
    $con=mysqli_connect($server,$user,$password,$db) or die ("Error al conectar la base de datos".mysql_error());


    return $con;
}
?>