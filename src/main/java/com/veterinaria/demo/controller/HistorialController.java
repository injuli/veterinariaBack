package com.veterinaria.demo.controller;

import com.veterinaria.demo.dto.Mensaje;
import com.veterinaria.demo.dto.HistorialDto;
import com.veterinaria.demo.entity.Historial;
import com.veterinaria.demo.service.HistorialService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historial")
@CrossOrigin(origins = "http://localhost:4200")
public class HistorialController {

    @Autowired
    HistorialService historialService;

    @GetMapping("/lista")
    public ResponseEntity<List<Historial>> list(){
        List<Historial> list = historialService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Historial> getById(@PathVariable("id") int id){
        if(!historialService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Historial historial = historialService.getOne(id).get();
        return new ResponseEntity(historial, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Historial> getByNombre(@PathVariable("nombre") String nombre){
        if(!historialService.existsByNombre(nombre))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Historial historial = historialService.getByNombre(nombre).get();
        return new ResponseEntity(historial, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody HistorialDto historialDto){
        if(StringUtils.isBlank(historialDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(historialDto.getCedula()==0 || historialDto.getCedula()<0 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if(historialService.existsByNombre(historialDto.getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Historial historial = new Historial(historialDto.getNombre(), historialDto.getApellido(), historialDto.getCedula(), historialDto.getTipo(), historialDto.getGenero());
        historialService.save(historial);
        return new ResponseEntity(new Mensaje("historial creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody HistorialDto historialDto){
        if(!historialService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(historialService.existsByNombre(historialDto.getNombre()) && historialService.getByNombre(historialDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(historialDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(historialDto.getCedula()==0 || historialDto.getCedula()<0 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);

        Historial historial = historialService.getOne(id).get();
        historial.setNombre(historialDto.getNombre());
        historial.setApellido(historialDto.getApellido());
        historial.setTipo(historialDto.getTipo());
        historial.setGenero(historialDto.getGenero());
        

        historialService.save(historial);
        return new ResponseEntity(new Mensaje("historial actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!historialService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        historialService.delete(id);
        return new ResponseEntity(new Mensaje("historial eliminado"), HttpStatus.OK);
    }


}
