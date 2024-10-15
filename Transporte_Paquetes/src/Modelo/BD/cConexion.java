package Modelo.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class cConexion {

    Connection con;
    String cadena = "jdbc:sqlite:src/Recursos/BD/Transporte_Paquetes.db";

   public Connection establecerConexion() {
        try {
            Class.forName("org.sqlite.JDBC");
            // ABRIR CONEXIÓN
            con = DriverManager.getConnection(cadena);

        } catch (Exception e) {

        }
        return con;
    }
}
