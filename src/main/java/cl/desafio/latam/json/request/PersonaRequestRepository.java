package cl.desafio.latam.json.request;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

// No need implementation, just one interface, and you have CRUD, thanks Spring Data
public interface PersonaRequestRepository extends MongoRepository<PersonaRequest, Long> {

	PersonaRequest findFirstByNombre(String nombre);

	PersonaRequest findByNombreAndApellido(String nombre, String apellido);

    //Supports native JSON query string
    @Query("{nombre:'?0'}")
    PersonaRequest findCustomByDomain(String nombre);

    @Query("{nombre: { $regex: ?0 } })")
    List<PersonaRequest> findCustomByRegExDomain(String nombre);

}