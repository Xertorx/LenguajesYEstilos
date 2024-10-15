/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.entidades;


/**
 *
 * @author roy-j
 */

public class Camionero {
    
  
   
    private int cedula;
    private String nombre;
    private String telefono;
    private String direccion;
    private String salario;
    private String poblacion;

    public Camionero() {
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public Camionero(int cedula, String nombre, String telefono, String direccion, String salario, String poblacion) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.salario = salario;
        this.poblacion = poblacion;
    }
    

   
    
}
