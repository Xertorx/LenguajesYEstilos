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

public class DetallesLlegada {
    
    private int codigo;    
    private int paqCodigo;
    private int provCod; // Clave foránea a Camionero
    private String fechaEntrega; // Clave foránea a Camion
    private String Observaciones;

    public int getPaqCodigo() {
        return paqCodigo;
    }

    public void setPaqCodigo(int paqCodigo) {
        this.paqCodigo = paqCodigo;
    }

    public int getProvCod() {
        return provCod;
    }

    public void setProvCod(int provCod) {
        this.provCod = provCod;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public DetallesLlegada(int codigo, int paqCodigo, int provCod, String fechaEntrega, String Observaciones) {
        this.codigo = codigo;
        this.paqCodigo = paqCodigo;
        this.provCod = provCod;
        this.fechaEntrega = fechaEntrega;
        this.Observaciones = Observaciones;
    }

    public DetallesLlegada(int paqCodigo, int provCod, String fechaEntrega, String Observaciones) {
        this.paqCodigo = paqCodigo;
        this.provCod = provCod;
        this.fechaEntrega = fechaEntrega;
        this.Observaciones = Observaciones;
    }

    public DetallesLlegada() {
    }

    
}
