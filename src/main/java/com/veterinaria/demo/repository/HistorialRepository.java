package com.veterinaria.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.veterinaria.demo.entity.Historial;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Integer> {
	Optional<Historial> findByNombre (String nombre);
	boolean existsByNombre(String nombre);
	Optional<Historial> findByCedula (int cedula);
	boolean existsByCedula(int cedula);
	
	

}
