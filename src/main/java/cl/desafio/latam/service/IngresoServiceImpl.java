package cl.desafio.latam.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.desafio.latam.json.request.PersonaRequest;
import cl.desafio.latam.json.response.PersonaResponse;
import cl.desafio.latam.json.request.PersonaRequestRepository;

@Service
public class IngresoServiceImpl implements IngresoService {
	@Autowired
    private PersonaRequestRepository repository;
	
	private static final Logger log = LoggerFactory.getLogger(IngresoServiceImpl.class);

	@Autowired
	private PoemaService poemaService;

	@SuppressWarnings("deprecation")
	@Override
	public PersonaResponse ingresoPersona(PersonaRequest personaReq) {
		log.info("[ INICIO - ingresoPersona ]");
			
		
		PersonaResponse persona = new PersonaResponse();
		String poema;
		
		repository.save(personaReq);

		persona.setNombre(personaReq.getNombre());
		persona.setApellido(personaReq.getApellido());
		
		Date fecha;
		try {
			fecha = new SimpleDateFormat("dd-MM-yyyy").parse(personaReq.getFecha());
		} catch (ParseException e) {
			fecha = null;
			e.printStackTrace();
		}
		
		persona.setFecha(fecha);

		Period periodo = Period.between(persona.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
		persona.setEdad(periodo.getYears());
		periodo = Period.between(persona.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusYears(periodo.plusYears(1).getYears()), LocalDate.now());
		
		log.info(persona.toString());
		
		if (fecha.getDate() == new Date().getDate()) {
			poema = poemaService.obtenerPoema();
			persona.setMensaje("FELICITACIONES ESTA DE CUMPLEAÑOS!!! \n \n \n " + poema);
		} else {
			String strMes = ((periodo.getMonths() * -1) == 1)?" mes y ":" meses y ";
			String strDia = ((periodo.getDays() * -1) == 1)?" día para su cumpleaños.":" días para su cumpleaños.";
			persona.setMensaje("NO ESTA DE CUMPLEAÑOS :c " + "faltan " + (periodo.getMonths() * -1)  + strMes + ( periodo.getDays() * -1 ) + strDia);
		}

		log.info("[ FIN - ingresoPersona ]");
		
		return persona;
	}

}