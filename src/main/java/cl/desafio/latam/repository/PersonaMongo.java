package cl.desafio.latam.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Persona")
public class PersonaMongo {

    @Id
    private long id;

    @Indexed(unique = true)
    private String nombre;

    private String apellido;
    
    public String getNombre() {
    	return nombre;
    }
    
    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }
    
    public String getApellido() {
    	return apellido;
    }
    
    public void setApellido(String apellido) {
    	this.apellido = apellido;
    }

    //getters and setters 
}