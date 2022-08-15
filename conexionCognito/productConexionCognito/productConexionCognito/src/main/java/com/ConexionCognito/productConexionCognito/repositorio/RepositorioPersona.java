package com.ConexionCognito.productConexionCognito.repositorio;

import com.ConexionCognito.productConexionCognito.entidad.EntidadPersona;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioPersona extends MongoRepository<EntidadPersona, String> {
}
