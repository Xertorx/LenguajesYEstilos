/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.BD.cConexion;
import Modelo.entidades.Paquete;
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
public class PaquetesDAO {

    cConexion conexion = new cConexion();
    Connection conn = null;
    PreparedStatement pst;
    ResultSet rs;
    String mensaje = null;

    public String insertarPaquete(Paquete paquete) {
        String sql = "";
        if (paquete.getCamioneroCedula() > 0) {
            sql = "INSERT INTO Paquetes (paq_Destinatario, paq_DireccionDestinatario, paq_Descripcion, cam_Cedula) VALUES(?,?,?,?)";
        } else {
            sql = "INSERT INTO Paquetes (paq_Destinatario, paq_DireccionDestinatario, paq_Descripcion) VALUES(?,?,?)";
        }

        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            pst.setString(1, paquete.getDestinatario());
            pst.setString(2, paquete.getDireccion());
            pst.setString(3, paquete.getDescripcion());
            pst.setString(3, paquete.getDescripcion());
            if (paquete.getCamioneroCedula() > 0) {
                pst.setInt(4, paquete.getCamioneroCedula());
            }

            pst.execute();
            mensaje = "Paquete creado con éxito.";
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error, no se pudo crear el paquete, revisa la informacion digitada";
        }

        return mensaje;
    }

    public ArrayList<Paquete> obtenerTodos() {
        String sql = "SELECT * FROM Paquetes";
        ArrayList<Paquete> listaPaquetes = new ArrayList<>();

        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Paquete pq = new Paquete();
                pq.setCodigo(rs.getInt(1));
                pq.setDestinatario(rs.getString(2));
                pq.setDireccion(rs.getString(3));
                pq.setDescripcion(rs.getString(4));
                pq.setCamioneroCedula(rs.getInt(5));
                listaPaquetes.add(pq);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPaquetes;
    }

    public String actualizarPaquete(Paquete paquete) {
        String sql = "";
        System.out.println("Valort de la cedula dentro de actualizar "+paquete.getCamioneroCedula());
        if (paquete.getCamioneroCedula() > 0) {
            System.out.println("Entro Aqui 2");
            sql = "UPDATE Paquetes SET paq_Destinatario = ?, paq_DireccionDestinatario = ?, paq_Descripcion = ?, cam_Cedula = ? WHERE paq_Codigo = ?;";
        } else {
            sql = "UPDATE Paquetes SET paq_Destinatario = ?, paq_DireccionDestinatario = ?, paq_Descripcion = ? WHERE paq_Codigo = ?;";
        }
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            pst.setString(1, paquete.getDestinatario());
            pst.setString(2, paquete.getDireccion());
            pst.setString(3, paquete.getDescripcion());
            if (paquete.getCamioneroCedula() > 0) {
                pst.setInt(4, paquete.getCamioneroCedula());
                pst.setInt(5, paquete.getCodigo());
            }else{
                pst.setInt(4, paquete.getCodigo());
            }
            

            int filasActualizadas = pst.executeUpdate();
            // Verifica cuántas filas fueron actualizadas
            if (filasActualizadas > 0) {
                mensaje = "Paquetes actualizado con éxito.";
            } else {
                mensaje = "No se encontró el Paquete para actualizar.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Errorr, no se pudo actualizar el Paquete, revisa la informacion digitada";
        }
        return mensaje;
    }

    public ArrayList<Paquete> consultarPaquete(Paquete paquete) {
        ArrayList<Paquete> listaPaquetes = new ArrayList<>();
        String sql = "SELECT * FROM Paquetes WHERE "
                + "paq_Destinatario LIKE ? AND "
                + "paq_DireccionDestinatario LIKE ? AND "
                + "paq_Descripcion LIKE ?  ";
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);

            pst.setString(1, "%" + paquete.getDestinatario() + "%");
            pst.setString(2, "%" + paquete.getDireccion() + "%");
            pst.setString(3, "%" + paquete.getDescripcion() + "%");
            rs = pst.executeQuery();

            while (rs.next()) {
                Paquete pq = new Paquete();
                pq.setCodigo(rs.getInt(1));
                pq.setDestinatario(rs.getString(2));
                pq.setDireccion(rs.getString(3));
                pq.setDescripcion(rs.getString(4));
                pq.setCamioneroCedula(rs.getInt(5));
                listaPaquetes.add(pq);
            }
            mensaje = "Paquetes Econtrados.";
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Paquetes no Econtrados.";
        }

        return listaPaquetes;
    }

    public String eliminarPaquete(Paquete paquete) {
        String sql = "DELETE FROM Paquetes WHERE paq_Codigo = ?";
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, paquete.getCodigo());

            int filasActualizadas = pst.executeUpdate();
            // Verifica cuántas filas fueron actualizadas
            if (filasActualizadas > 0) {
                mensaje = "Paquete ELIMINADO con éxito.";
            } else {
                mensaje = "No se encontró el Paquete para Eliminar.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaquetesDAO.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "Error al intentar eliminar el Paquete " + ex;
        }
        return mensaje;
    }

}
