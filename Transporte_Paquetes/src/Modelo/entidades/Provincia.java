/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.entidades;

/**
 *
 * @author roy-j
 */
public class Provincia {

    private int codigo;
    private String nombre;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Provincia(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Provincia(String nombre) {
        this.nombre = nombre;
    }

    public Provincia() {

    }

}
