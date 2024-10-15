/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.BD.cConexion;
import Modelo.entidades.AsgnCamion;
import Modelo.entidades.Camion;
import Modelo.entidades.Camionero;
import Modelo.entidades.DetallesLlegada;
import Modelo.entidades.Paquete;
import Modelo.entidades.Provincia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author roy-j
 */
public class DetallesLlegadaDAO {

    cConexion conexion = new cConexion();
    Connection conn = null;
    PreparedStatement pst;
    ResultSet rs;
    String mensaje = null;

    public String reportarLLegadaPaquete(DetallesLlegada dtllegada) {

        String sql = "INSERT INTO DetallesLlegada (paq_codigo, prov_Cod, det_Fecha_entrega, det_Observaciones) VALUES(?,?,?,?)";
   
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, dtllegada.getPaqCodigo());
            pst.setInt(2, dtllegada.getProvCod());
            pst.setString(3, dtllegada.getFechaEntrega());
            pst.setString(4, dtllegada.getObservaciones());
            pst.execute();
            mensaje = "Reporte creado con éxito.";
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error, no se pudo crear el reporte, revisa la informacion seleccionada";
        }
        return mensaje;
    }

    public ArrayList<DetallesLlegada> obtenerTodos() {
        String sql = "SELECT * FROM DetallesLlegada";
        ArrayList<DetallesLlegada> listaReporte = new ArrayList<>();

        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                DetallesLlegada dtllegada = new DetallesLlegada();
                dtllegada.setCodigo(rs.getInt(1));
                dtllegada.setPaqCodigo(rs.getInt(2));
                dtllegada.setProvCod(rs.getInt(3));
                dtllegada.setFechaEntrega(rs.getString(4));
                dtllegada.setObservaciones(rs.getString(5));
                listaReporte.add(dtllegada);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaReporte;
    }

    public ArrayList<DetallesLlegada> consultarReporte(DetallesLlegada dtllegada) {
        ArrayList<DetallesLlegada> listaAsignacion= new ArrayList<>();
        String sql = "SELECT * FROM DetallesLlegada WHERE "
                + "paq_codigo LIKE ? AND "
                + "prov_Cod LIKE ? ";
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);

            pst.setString(1, "%" + dtllegada.getPaqCodigo() + "%");
            pst.setString(2, "%" + dtllegada.getProvCod() + "%");
            System.out.println(dtllegada.getPaqCodigo());
            System.out.println(dtllegada.getProvCod());
            rs = pst.executeQuery();

            while (rs.next()) {
                dtllegada = new DetallesLlegada();
                dtllegada.setCodigo(rs.getInt(1));
                dtllegada.setPaqCodigo(rs.getInt(2));
                dtllegada.setProvCod(rs.getInt(3));
                dtllegada.setFechaEntrega(rs.getString(4));
                dtllegada.setObservaciones(rs.getString(5));
                listaAsignacion.add(dtllegada);
            }
            mensaje = "Detalles Econtrados.";
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Detalles no Econtrados.";
        }

        return listaAsignacion;
    }

    public String eliminarReporte(DetallesLlegada dtllegada) {
        String sql = "DELETE FROM DetallesLlegada WHERE det_codigo = ?";
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, dtllegada.getCodigo());

            int filasActualizadas = pst.executeUpdate();
            // Verifica cuántas filas fueron actualizadas
            if (filasActualizadas > 0) {
                mensaje = "Detalles ELIMINADO con éxito.";
            } else {
                mensaje = "No se encontró el detalle para Eliminar.";
            }
        } catch (SQLException ex) {
            mensaje = "Error al intentar eliminar el Detalle " + ex;
        }
        return mensaje;
    }
     public String actualizarReporte(DetallesLlegada dtllegada) {
  
        String sql = "UPDATE DetallesLlegada SET paq_codigo = ?, prov_Cod = ?, det_Fecha_entrega = ? ,det_Observaciones = ? WHERE det_codigo = ?;";
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);

            pst.setInt(1, dtllegada.getPaqCodigo());
            pst.setInt(2, dtllegada.getProvCod());
            pst.setString(3, dtllegada.getFechaEntrega());
            pst.setString(4, dtllegada.getObservaciones());
            pst.setInt(5, dtllegada.getCodigo());
        
            int filasActualizadas = pst.executeUpdate();
            // Verifica cuántas filas fueron actualizadas
            if (filasActualizadas > 0) {
                mensaje = "DetallesLLegada actualizado con éxito.";
            } else {
                mensaje = "No se encontró la DetallesLLegada para actualizar.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error, no se pudo actualizar la DetallesLLegada, revisa la informacion digitada";
        }
        return mensaje;
    }

}
