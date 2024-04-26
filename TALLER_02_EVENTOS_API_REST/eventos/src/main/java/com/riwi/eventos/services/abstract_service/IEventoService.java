package com.riwi.eventos.services.abstract_service;

import com.riwi.eventos.entities.Evento;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IEventoService {
    /** Utilizamos interfaz para ser utilizada como inyección de dependencias en controlador
     * mantiene integridad, desacoplamiento y principios SOLID */

    public Evento save(Evento objEvento);
    public List<Evento> getAll();
    public Evento getById(String id);
    public void delete(String id);
    public Evento update(Evento objEvento);

    /** Método para implementar paginación para el endpoint de obtener todos los eventos.  */
    public Page<Evento> getAllPagination(int page, int size);

    /** SpringbootApp.bind(IEventoService, EventoService)*/
}
