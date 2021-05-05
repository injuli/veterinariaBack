package com.veterinaria.demo.service;

import com.veterinaria.demo.entity.Historial;
import com.veterinaria.demo.repository.HistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HistorialService {

    @Autowired
    HistorialRepository historialRepository;

    public List<Historial> list(){
        return historialRepository.findAll();
    }

    public Optional<Historial> getOne(int id){
        return historialRepository.findById(id);
    }

    public Optional<Historial> getByNombre(String nombre){
        return historialRepository.findByNombre(nombre);
    }
    
    public Optional<Historial> getByCedula(int cedula){
        return historialRepository.findByCedula(cedula);
    }

    public void  save(Historial producto){
        historialRepository.save(producto);
    }

    public void delete(int id){
        historialRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return historialRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return historialRepository.existsByNombre(nombre);
    }
    
    public boolean existsByCedula(int cedula) {
    	return historialRepository.existsByCedula(cedula);
    	
    }
}
