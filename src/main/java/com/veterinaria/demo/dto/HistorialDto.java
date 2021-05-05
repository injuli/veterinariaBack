package com.veterinaria.demo.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class HistorialDto {

    @NotBlank
    private String nombre;
    @Min(0)
    private int cedula;
    private String apellido;
    private String tipo;
    private String genero;

    public HistorialDto() {
    }

    public HistorialDto(@NotBlank String nombre, @Min(0) int cedula, String apellido, String tipo, String genero) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.apellido = apellido;
        this.tipo = tipo;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
    
    
    
}
