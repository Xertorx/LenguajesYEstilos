/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.entidades;

/**
 *
 * @author roy-j
 */

public class Camion {
    
   
    private String matricula;
    private String tipo;
    private String potencia;
    private String modelo;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Camion(String matricula, String tipo, String potencia, String modelo) {
        this.matricula = matricula;
        this.tipo = tipo;
        this.potencia = potencia;
        this.modelo = modelo;
    }

    public Camion() {

    }
 
    

   
    
}
