package com.riwi.eventos.services;

import com.riwi.eventos.entities.Evento;
import com.riwi.eventos.repositories.EventoRepository;
import com.riwi.eventos.services.abstract_service.IEventoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EventoService implements IEventoService {

    /**
     *  Que todas las inyecciones de dependencias deberían venir de una interfaz
     *  LO PREGUNTAN EN ENTREVISTAS
     *  El service y el controller siempre deberían ser interfaces
     *  */

    /** final es para indicar que es una constante*/
    @Autowired /** Es para que Springboot entienda esa inyección de dependencias */
    private final EventoRepository objEventoRepository;

    @Override
    public Evento save(Evento objEvento) {

        /** Validar que la fecha del evento no sea en el pasado */
        /** Validar que la capacidad no sea negativa */
        if (((objEvento.getFecha().isAfter(LocalDate.now())) || (objEvento.getFecha().isEqual(LocalDate.now()))) && (objEvento.getCapacidad() > 0)) {
            return this.objEventoRepository.save(objEvento);
        } else {
            System.out.println("ERROR AL INSERTAR >>>> La fecha debe ser mayor a la fecha actual y la capacidad de asistentes no puede ser menor negativa");
            return null;
        }
    }

    @Override
    public List<Evento> getAll() {
        return this.objEventoRepository.findAll();
    }

    @Override
    public Evento getById(String id) {
        return this.objEventoRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(String id) {
        Evento evento = this.objEventoRepository.findById(id).orElseThrow();

        this.objEventoRepository.delete(evento);
    }

    @Override
    public Evento update(Evento objEvento) {
        /** Validar que la fecha del evento no sea en el pasado */
        /** Validar que la capacidad no sea negativa */
        if (((objEvento.getFecha().isAfter(LocalDate.now())) || (objEvento.getFecha().isEqual(LocalDate.now()))) && (objEvento.getCapacidad() > 0)) {
            return this.objEventoRepository.save(objEvento);
        } else {
            System.out.println("ERROR AL INSERTAR >>>> La fecha debe ser mayor a la fecha actual y la capacidad de asistentes no puede ser menor negativa");
            return null;
        }
    }

    @Override
    public Page<Evento> getAllPagination(int page, int size) {
        /** Validación de que la página no sea menor a 0 */
        if (page < 0) {
            page = 0;
        }

        /** Creación de la paginación */
        PageRequest objPage = PageRequest.of(page, size);

        return this.objEventoRepository.findAll(objPage);
    }

}
