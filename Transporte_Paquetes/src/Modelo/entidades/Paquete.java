/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.entidades;

/**
 *
 * @author roy-j
 */

public class Paquete {
    
   
    private int codigo;
    private String destinatario;
    private String direccion;
    private String descripcion;
    private int camioneroCedula;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCamioneroCedula() {
        return camioneroCedula;
    }

    public void setCamioneroCedula(int camioneroCedula) {
        this.camioneroCedula = camioneroCedula;
    }

    public Paquete( String destinatario, String direccion, String descripcion, int camioneroCedula) {

        this.destinatario = destinatario;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.camioneroCedula = camioneroCedula;
    }
     public Paquete(int codigo, String destinatario, String direccion, String descripcion, int camioneroCedula) {
        this.codigo=codigo;
        this.destinatario = destinatario;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.camioneroCedula = camioneroCedula;
    }
    public Paquete( int codigo,String destinatario, String direccion, String descripcion ) {
        this.codigo=codigo;
        this.destinatario = destinatario;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.camioneroCedula = camioneroCedula;
    }
  
    public Paquete(String destinatario, String direccion, String descripcion) {
        this.destinatario = destinatario;
        this.direccion = direccion;
        this.descripcion = descripcion;
    }

    public Paquete() {
    }

    
}
