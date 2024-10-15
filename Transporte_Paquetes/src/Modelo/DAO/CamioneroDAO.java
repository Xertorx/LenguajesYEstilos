/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.BD.cConexion;
import Modelo.entidades.Camionero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author roy-j
 */
public class CamioneroDAO {

    cConexion conexion = new cConexion();
    Connection conn = null;
    PreparedStatement pst;
    ResultSet rs;
    String mensaje = null;

    public String insertarCamionero(Camionero camionero) {
        System.out.println();
        if (consultarCamioneroCedula(camionero) == true) {
            mensaje = "La cedula digitada ya se encuentra registrada";
        } else {
            String sql = "INSERT INTO Camioneros (cami_Cedula, cami_Nombre, cami_Telefono, cami_Direccion, cami_Salario, cami_Poblacion) VALUES(?,?,?,?,?,?)";

            try {
                conn = conexion.establecerConexion();
                pst = conn.prepareStatement(sql);
                pst.setInt(1, camionero.getCedula());
                pst.setString(2, camionero.getNombre());
                pst.setString(3, camionero.getTelefono());
                pst.setString(4, camionero.getDireccion());
                pst.setString(5, camionero.getSalario());
                pst.setString(6, camionero.getPoblacion());
                pst.execute();
                mensaje = "Camionero creado con éxito.";
            } catch (SQLException e) {
                e.printStackTrace();
                mensaje = "Erro, no se pudo crear el camionero, revisa la informacion digitada";
            }
        }
        return mensaje;
    }

    public ArrayList<Camionero> obtenerTodos() {
        String sql = "SELECT * FROM Camioneros";
        ArrayList<Camionero> listaCamioneros = new ArrayList<>();

        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Camionero cam = new Camionero();
                cam.setCedula(rs.getInt(1));
                cam.setNombre(rs.getString(2));
                cam.setTelefono(rs.getString(3));
                cam.setDireccion(rs.getString(4));
                cam.setSalario(rs.getString(5));
                cam.setPoblacion(rs.getString(6));
                listaCamioneros.add(cam);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCamioneros;
    }

    public String actualizarCamionero(Camionero camionero) {
        String sql = "UPDATE Camioneros SET cami_Nombre = ?, cami_Telefono = ?, cami_Direccion = ?, cami_Salario = ?,cami_Poblacion = ? WHERE cami_Cedula = ?;";
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);

            pst.setString(1, camionero.getNombre());
            pst.setString(2, camionero.getTelefono());
            pst.setString(3, camionero.getDireccion());
            pst.setString(4, camionero.getSalario());
            pst.setString(5, camionero.getPoblacion());
            pst.setInt(6, camionero.getCedula());
        
            int filasActualizadas = pst.executeUpdate();
            // Verifica cuántas filas fueron actualizadas
            if (filasActualizadas > 0) {
                mensaje = "Camionero actualizado con éxito.";
            } else {
                mensaje = "No se encontró el camionero para actualizar.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error, no se pudo actualizar el camionero, revisa la informacion digitada";
        }
        return mensaje;
    }

    public boolean consultarCamioneroCedula(Camionero camionero) {
        String sql = "SELECT * FROM Camioneros WHERE cami_Cedula = ?";
        boolean validacion = false;
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, camionero.getCedula());
            rs = pst.executeQuery();
            if (rs.next()) {
                // Si hay al menos un registro, significa que el camionero existe
                validacion = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CamioneroDAO.class.getName()).log(Level.SEVERE, null, ex);
            validacion = false;
        }
        return validacion;
    }
    

    public ArrayList<Camionero> consultarCamionero(Camionero camionero) {
        ArrayList<Camionero> listaCamioneros = new ArrayList<>();
        String sql = "SELECT * FROM Camioneros WHERE "
                + "cami_Cedula LIKE ? AND "
                + "cami_Nombre LIKE ? AND "
                + "cami_Telefono LIKE ? AND "
                + "cami_Direccion LIKE ? AND "
                + "cami_Salario LIKE ? AND "
                + "cami_Poblacion LIKE ?";
        System.out.println(camionero.getCedula());
        System.out.println(camionero.getNombre());
        System.out.println(camionero.getTelefono());
        System.out.println(camionero.getDireccion());
        System.out.println(camionero.getSalario());
        System.out.println(camionero.getPoblacion());
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);

            pst.setString(1, "%" + camionero.getCedula() + "%");
            pst.setString(2, "%" + camionero.getNombre() + "%");
            pst.setString(3, "%" + camionero.getTelefono() + "%");
            pst.setString(4, "%" + camionero.getDireccion() + "%");
            pst.setString(5, "%" + camionero.getSalario() + "%");
            pst.setString(6, "%" + camionero.getPoblacion() + "%");
            rs = pst.executeQuery();

            while (rs.next()) {
                Camionero cam = new Camionero();
                cam.setCedula(rs.getInt(1));
                cam.setNombre(rs.getString(2));
                cam.setTelefono(rs.getString(3));
                cam.setDireccion(rs.getString(4));
                cam.setSalario(rs.getString(5));
                cam.setPoblacion(rs.getString(6));
                System.out.println(cam);
                listaCamioneros.add(cam);
            }
            mensaje = "Camioneros Econtrados.";
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Camioneros no Econtrados.";
        }

        return listaCamioneros;
    }
    public String eliminarCamionero(Camionero camionero) {
        String sql = "DELETE FROM Camioneros WHERE cami_Cedula = ?";
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, camionero.getCedula());

            int filasActualizadas = pst.executeUpdate();
            // Verifica cuántas filas fueron actualizadas
            if (filasActualizadas > 0) {
                mensaje = "Camionero ELIMINADO con éxito.";
            } else {
                mensaje = "No se encontró el camionero para Eliminar.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(CamioneroDAO.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "Error al intentar eliminar el usuario" + ex;
        }
        return mensaje;
    }

}
