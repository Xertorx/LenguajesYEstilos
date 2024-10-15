/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.BD.cConexion;
import Modelo.entidades.AsgnCamion;
import Modelo.entidades.Camion;
import Modelo.entidades.Camionero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author roy-j
 */
public class AsignarCamionesDAO {

    cConexion conexion = new cConexion();
    Connection conn = null;
    PreparedStatement pst;
    ResultSet rs;
    String mensaje = null;

    public String insertarCamionaCamionero(AsgnCamion asgnCamion) {

        String sql = "INSERT INTO AsignacionCamiones (Cedula_Camionero, Matricula_Camion, Asg_Fecha_inicio, Asg_Fecha_entrega, Asg_Observaciones) VALUES(?,?,?,?,?)";
   
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, asgnCamion.getCedulaCamionero());
            pst.setString(2, asgnCamion.getMatriculaCamion());
            pst.setString(3, asgnCamion.getFechaInicio());
            pst.setString(4, asgnCamion.getFechaEntrega());
            pst.setString(5, asgnCamion.getObservaciones());
            pst.execute();
            mensaje = "Camion Asignado con éxito.";
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error, no se pudo Asignar el camion al camionero, revisa la informacion seleccionada";
        }
        return mensaje;
    }

    public ArrayList<AsgnCamion> obtenerTodos() {
        String sql = "SELECT * FROM AsignacionCamiones";
        ArrayList<AsgnCamion> listaAsignaciones = new ArrayList<>();

        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                AsgnCamion asgnCamion = new AsgnCamion();
                asgnCamion.setCodigo(rs.getInt(1));
                asgnCamion.setCedulaCamionero(rs.getInt(2));
                asgnCamion.setMatriculaCamion(rs.getString(3));
                asgnCamion.setFechaInicio(rs.getString(4));
                asgnCamion.setFechaEntrega(rs.getString(5));
                asgnCamion.setObservaciones(rs.getString(6));
                listaAsignaciones.add(asgnCamion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAsignaciones;
    }

    public ArrayList<AsgnCamion> consultarAsingacion(AsgnCamion asgnCamion) {
        ArrayList<AsgnCamion> listaAsignacion= new ArrayList<>();
        String sql = "SELECT * FROM AsignacionCamiones WHERE "
                + "Cedula_Camionero LIKE ? AND "
                + "Matricula_Camion LIKE ? ";
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);

            pst.setString(1, "%" + asgnCamion.getCedulaCamionero() + "%");
            pst.setString(2, "%" + asgnCamion.getMatriculaCamion() + "%");

            rs = pst.executeQuery();

            while (rs.next()) {
                asgnCamion = new AsgnCamion();
                asgnCamion.setCodigo(rs.getInt(1));
                asgnCamion.setCedulaCamionero(rs.getInt(2));
                asgnCamion.setMatriculaCamion(rs.getString(3));
                asgnCamion.setFechaInicio(rs.getString(4));
                asgnCamion.setFechaEntrega(rs.getString(5));
                asgnCamion.setObservaciones(rs.getString(6));
                listaAsignacion.add(asgnCamion);
            }
            mensaje = "Asignaciones Econtradas.";
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Asignaciones no Econtradas.";
        }

        return listaAsignacion;
    }

    public String eliminarAsignacion(AsgnCamion asgnCamion) {
        String sql = "DELETE FROM AsignacionCamiones WHERE Asg_Codigo = ?";
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, asgnCamion.getCodigo());

            int filasActualizadas = pst.executeUpdate();
            // Verifica cuántas filas fueron actualizadas
            if (filasActualizadas > 0) {
                mensaje = "Asignacion ELIMINADA con éxito.";
            } else {
                mensaje = "No se encontró la Asignacion para Eliminar.";
            }
        } catch (SQLException ex) {
            mensaje = "Error al intentar eliminar La asignacion " + ex;
        }
        return mensaje;
    }
     public String actualizarAsignacion(AsgnCamion asgnCamion) {
        String sql = "UPDATE AsignacionCamiones SET Cedula_Camionero = ?, Matricula_Camion = ?, Asg_Fecha_entrega = ? ,Asg_Observaciones = ? WHERE Asg_Codigo = ?;";
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);

            pst.setInt(1, asgnCamion.getCedulaCamionero());
            pst.setString(2, asgnCamion.getMatriculaCamion());
            pst.setString(3, asgnCamion.getFechaEntrega());
            pst.setString(4, asgnCamion.getObservaciones());
            pst.setInt(5, asgnCamion.getCodigo());
        
            int filasActualizadas = pst.executeUpdate();
            // Verifica cuántas filas fueron actualizadas
            if (filasActualizadas > 0) {
                mensaje = "Asingacion actualizado con éxito.";
            } else {
                mensaje = "No se encontró la Asignacion para actualizar.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error, no se pudo actualizar la asignacion, revisa la informacion digitada";
        }
        return mensaje;
    }

}
