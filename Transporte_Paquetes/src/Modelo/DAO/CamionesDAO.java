/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.DAO;

import Modelo.BD.cConexion;
import Modelo.entidades.Camion;
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
public class CamionesDAO {

    cConexion conexion = new cConexion();
    Connection conn = null;
    PreparedStatement pst;
    ResultSet rs;
    String mensaje = null;

    public String insertarCamion(Camion camion) {
        if (consultarCamionCedula(camion) == true) {
            mensaje = "La Matricula digitada ya se encuentra registrada";
        } else {
            String sql = "INSERT INTO Camiones (cam_Matricula, cam_Tipo, cam_Potencia, cam_Modelo) VALUES(?,?,?,?)";

            try {
                conn = conexion.establecerConexion();
                pst = conn.prepareStatement(sql);
                pst.setString(1, camion.getMatricula());
                pst.setString(2, camion.getTipo());
                pst.setString(3, camion.getPotencia());
                pst.setString(4, camion.getModelo());
                pst.execute();
                mensaje = "Camion creado con éxito.";
            } catch (SQLException e) {
                e.printStackTrace();
                mensaje = "Erro, no se pudo crear el camionero, revisa la informacion digitada";
            }
        }
        return mensaje;
    }

    public ArrayList<Camion> obtenerTodos() {
        String sql = "SELECT * FROM Camiones";
        ArrayList<Camion> listaCamiones = new ArrayList<>();

        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Camion cam = new Camion();
                cam.setMatricula(rs.getString(1));
                cam.setTipo(rs.getString(2));
                cam.setPotencia(rs.getString(3));
                cam.setModelo(rs.getString(4));
                listaCamiones.add(cam);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCamiones;
    }

    public String actualizarCamion(Camion camion) {
        String sql = "UPDATE Camiones SET cam_Tipo = ?, cam_Potencia = ?, cam_Modelo = ? WHERE cam_Matricula = ?;";
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);

            pst.setString(1, camion.getTipo());
            pst.setString(2, camion.getPotencia());
            pst.setString(3, camion.getModelo());
            pst.setString(4, camion.getMatricula());

            int filasActualizadas = pst.executeUpdate();
            // Verifica cuántas filas fueron actualizadas
            if (filasActualizadas > 0) {
                mensaje = "Camionero actualizado con éxito.";
            } else {
                mensaje = "No se encontró el Camion para actualizar.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Error, no se pudo actualizar el Camion, revisa la informacion digitada";
        }
        return mensaje;
    }

    public boolean consultarCamionCedula(Camion camion) {
        String sql = "SELECT * FROM Camiones WHERE cam_Matricula = ?";
        boolean validacion = false;
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            pst.setString(1, camion.getMatricula());
            rs = pst.executeQuery();
            if (rs.next()) {
                // Si hay al menos un registro, significa que el camionero existe
                validacion = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CamionesDAO.class.getName()).log(Level.SEVERE, null, ex);
            validacion = false;
        }
        return validacion;
    }

    public ArrayList<Camion> consultarCamion(Camion camion) {
        ArrayList<Camion> listaCamioneros = new ArrayList<>();
        String sql = "SELECT * FROM Camiones WHERE "
                + "cam_Matricula LIKE ? AND "
                + "cam_Tipo LIKE ? AND "
                + "cam_Potencia LIKE ? AND "
                + "cam_Modelo LIKE ? ";
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);

            pst.setString(1, "%" + camion.getMatricula() + "%");
            pst.setString(2, "%" + camion.getTipo() + "%");
            pst.setString(3, "%" + camion.getPotencia() + "%");
            pst.setString(4, "%" + camion.getModelo() + "%");
            rs = pst.executeQuery();

            while (rs.next()) {
                Camion cam = new Camion();
                cam.setMatricula(rs.getString(1));
                cam.setTipo(rs.getString(2));
                cam.setPotencia(rs.getString(3));
                cam.setModelo(rs.getString(4));
                listaCamioneros.add(cam);
            }
            mensaje = "Camiones Econtrados.";
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Camiones no Econtrados.";
        }

        return listaCamioneros;
    }

    public String eliminarCamion(Camion camion) {
        String sql = "DELETE FROM Camiones WHERE cam_Matricula = ?";
        try {
            conn = conexion.establecerConexion();
            pst = conn.prepareStatement(sql);
            pst.setString(1, camion.getMatricula());

            int filasActualizadas = pst.executeUpdate();
            // Verifica cuántas filas fueron actualizadas
            if (filasActualizadas > 0) {
                mensaje = "Camion ELIMINADO con éxito.";
            } else {
                mensaje = "No se encontró el Camion para Eliminar.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(CamionesDAO.class.getName()).log(Level.SEVERE, null, ex);
            mensaje = "Error al intentar eliminar el Camion " + ex;
        }
        return mensaje;
    }


}
