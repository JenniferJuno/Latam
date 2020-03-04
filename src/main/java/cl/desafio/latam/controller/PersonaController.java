package cl.desafio.latam.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.desafio.latam.json.request.PersonaRequest;
import cl.desafio.latam.json.response.PersonaResponse;
import cl.desafio.latam.service.IngresoService;

@RestController
public class PersonaController {
	
	private static final Logger log = LoggerFactory.getLogger(PersonaController.class);
	
	@Autowired
	private IngresoService ingServ;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping( value = "desafioLatam/ingresoPersona", method = RequestMethod.POST )
	public @ResponseBody PersonaResponse ingresoPersona( @RequestBody PersonaRequest personaRequest) {
		log.info("[ ingresoPersona - INICIO ]");
		log.info("[ ingresoPersona ] " + personaRequest.toString());

		PersonaResponse personaResponse = ingServ.ingresoPersona(personaRequest);
		
		log.info("[ ingresoPersona - FIN ]");
		return personaResponse;
	}

}
