package cl.desafio.latam.json.request;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import cl.desafio.latam.repository.PersonaMongo;

import java.util.List;

// No need implementation, just one interface, and you have CRUD, thanks Spring Data
public interface PersonaRepository extends MongoRepository<PersonaMongo, String> {

	PersonaMongo findFirstByNombre(String nombre);

	PersonaMongo findByNombreAndApellido(String nombre, String apellido);

    //Supports native JSON query string
    @Query("{nombre:'?0'}")
    PersonaMongo findCustomByDomain(String nombre);

    @Query("{nombre: { $regex: ?0 } })")
    List<PersonaMongo> findCustomByRegExDomain(String nombre);

}