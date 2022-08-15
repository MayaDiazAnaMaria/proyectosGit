package com.ConexionCognito.productConexionCognito.entidad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "persona")
@Getter
@Setter
@NoArgsConstructor

public class EntidadPersona {

    @Id
    private String id;
    private String usuario;
    private String clave;
    private String correoElectronico;
    private String telefono;
}
