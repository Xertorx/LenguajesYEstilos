/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.entidades;

import java.util.Date;

/**
 *
 * @author roy-j
 */

public class AsgnCamion {
    
    private int codigo;
    private int cedulaCamionero; // Clave foránea a Camionero
    private String matriculaCamion; // Clave foránea a Camion
    private String fechaInicio;
    private String fechaEntrega;
    private String observaciones;

    // Constructor
    public AsgnCamion(int codigo, int cedulaCamionero, String matriculaCamion, String fechaInicio, String fechaEntrega, String observaciones) {
        this.codigo = codigo;
        this.cedulaCamionero = cedulaCamionero;
        this.matriculaCamion = matriculaCamion;
        this.fechaInicio = fechaInicio;
        this.fechaEntrega = fechaEntrega;
        this.observaciones = observaciones;
    }
    public AsgnCamion(int cedulaCamionero, String matriculaCamion, String fechaInicio) {
        this.cedulaCamionero = cedulaCamionero;
        this.matriculaCamion = matriculaCamion;
        this.fechaInicio = fechaInicio;
       
    }
    public AsgnCamion() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    // Getters y Setters
    public int getCedulaCamionero() {
        return cedulaCamionero;
    }

    public void setCedulaCamionero(int cedulaCamionero) {
        this.cedulaCamionero = cedulaCamionero;
    }

    public String getMatriculaCamion() {
        return matriculaCamion;
    }

    public void setMatriculaCamion(String matriculaCamion) {
        this.matriculaCamion = matriculaCamion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
}
