var mysql = require('mysql');

var conexion = mysql.createConnection({
    host:  'localhost',
    database:  'jsMysql',
    user: 'root',
    password: ''
});

conexion.connect(function(error){
    if (error){
        throw error;
    }
    else {
        console.log('Conexion exitosa')
    }
});
conexion.end();