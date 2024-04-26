package com.riwi.eventos.controllers;

import com.riwi.eventos.entities.Evento;
import com.riwi.eventos.services.EventoService;
import com.riwi.eventos.services.abstract_service.IEventoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/eventos")
@AllArgsConstructor
public class EventoController {
    @Autowired
    private final IEventoService objIEventoService;

    @GetMapping
    /** ResponseEntity lo utilizamos para responder con los status http */
    public ResponseEntity <List<Evento>> getAll(){
        //Repositorio servicio
        return ResponseEntity.ok(this.objIEventoService.getAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Evento> get(@PathVariable String id){
        return ResponseEntity.ok(this.objIEventoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Evento> insert(@RequestBody Evento objEvento){
        return ResponseEntity.ok(this.objIEventoService.save(objEvento));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Evento> update(
            /** Para obtener los datos actualizados de l evento */
            @RequestBody Evento objEvento,
            /** Obtener el id que viene en URL */
            @PathVariable String id
    ){
        objEvento.setId(id);
        return ResponseEntity.ok(this.objIEventoService.update(objEvento));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        this.objIEventoService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginate")
    public ResponseEntity <Page<Evento>> getWithPagination (
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size
    ){
        return ResponseEntity.ok(this.objIEventoService.getAllPagination(page - 1, size));
    }

}
