package com.ConexionCognito.productConexionCognito.Controlador;

import com.ConexionCognito.productConexionCognito.entidad.EntidadPersona;
import com.ConexionCognito.productConexionCognito.repositorio.RepositorioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class ControladorPersona {

    @Autowired
    private RepositorioPersona repositorioPersona;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EntidadPersona> traerTodasPersonas(){

        return repositorioPersona.findAll();
    }

    /*
    @GetMapping
    public ResponseEntity<List<EntidadPersona>> traerTodasPersonas(){
    List<EntidadPersona> entidadesPersona = repositorioPersona.findAll();
    ResponseEntity<List<EntidadPersona>> responseEntity = new ResponseEntity<>(entidadesPersona, HttpStatus.OK);
    return responseEntity;
    }
    */


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void crearPersona(@RequestBody EntidadPersona entidadPersona){
        repositorioPersona.save(entidadPersona);

    }
}
