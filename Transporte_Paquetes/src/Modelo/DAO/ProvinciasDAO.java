/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.BD.cConexion;
import Modelo.entidades.Paquete;
import Modelo.entidades.Provincia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roy-j
 */
public class ProvinciasDAO {

    cConexion conexion = new cConexion();
    Connection conn = null;
    PreparedStatement pst;
    ResultSet rs;
    String mensaje = null;

    public String insertarProvincia(Provincia provincia) {
        String sql = "";
       
        sql = "INSERT INTO Provincias (prov_nombre) VALUES(?)";
        
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            pst.setString(1, provincia.getNombre());
            pst.execute();
            mensaje = "Provincia creado con éxito.";
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error, no se pudo crear la provincia, revisa la informacion digitada";
        }

        return mensaje;
    }

    public ArrayList<Provincia> obtenerTodos() {
        String sql = "SELECT * FROM Provincias";
        ArrayList<Provincia> listaProvincia = new ArrayList<>();

        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Provincia provincia = new Provincia();
                provincia.setCodigo(rs.getInt(1));
                provincia.setNombre(rs.getString(2));
                listaProvincia.add(provincia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProvincia;
    }

    public String actualizarProvincia(Provincia provincia) {
        String sql = "";
        
        sql = "UPDATE Provincias SET prov_nombre = ? WHERE prov_codigo = ?;";

        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            pst.setString(1, provincia.getNombre());
            pst.setInt(2, provincia.getCodigo());
            
            int filasActualizadas = pst.executeUpdate();
            // Verifica cuántas filas fueron actualizadas
            if (filasActualizadas > 0) {
                mensaje = "Provincia actualizado con éxito.";
            } else {
                mensaje = "No se encontró la Provincia para actualizar.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Errorr, no se pudo actualizar la Provincia, revisa la informacion digitada";
        }
        return mensaje;
    }

    public ArrayList<Provincia> consultarProvincia(Provincia provincia) {
        ArrayList<Provincia> listaProvincia = new ArrayList<>();
        String sql = "SELECT * FROM Provincias WHERE "
                + "prov_nombre LIKE ?";
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);

            pst.setString(1, "%" + provincia.getNombre() + "%");
            rs = pst.executeQuery();

            while (rs.next()) {
                provincia = new Provincia();
                provincia.setCodigo(rs.getInt(1));
                provincia.setNombre(rs.getString(2));
                listaProvincia.add(provincia);
            }
            mensaje = "Paquetes Econtrados.";
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Paquetes no Econtrados.";
        }

        return listaProvincia;
    }

    public String eliminarProvincia(Provincia provincia) {
        String sql = "DELETE FROM Provincias WHERE prov_codigo = ?";
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, provincia.getCodigo());

            int filasActualizadas = pst.executeUpdate();
            // Verifica cuántas filas fueron actualizadas
            if (filasActualizadas > 0) {
                mensaje = "Provincia ELIMINADO con éxito.";
            } else {
                mensaje = "No se encontró la Provincia para Eliminar.";
            }
        } catch (SQLException ex) {
            mensaje = "Error al intentar eliminar la provincia " + ex;
        }
        return mensaje;
    }

}
