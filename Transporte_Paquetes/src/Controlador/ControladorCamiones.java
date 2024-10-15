package Controlador;

import Modelo.DAO.CamioneroDAO;
import Modelo.DAO.CamionesDAO;
import Modelo.entidades.Camion;
import Modelo.entidades.Camionero;
import Vista.Paneles.Camioneros;
import Vista.Paneles.Camiones;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roy-j
 */
public class ControladorCamiones {

    private Camiones Camvista;
    private Camion camion;
    private CamionesDAO sqlCamion;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorCamiones(Camiones camvista) {
        this.Camvista = camvista;
        this.sqlCamion = new CamionesDAO();
    }

    private Camion recogerDatosCamion() {
        
        String matricula = Camvista.inputMatricula.getText();
        String tipo = Camvista.inputTipo.getText();
        String potencia = Camvista.inputPotencia.getText();
        String modelo = Camvista.inputModelo.getText();


        return new Camion(matricula, tipo, potencia, modelo);
    }

    public void crear() {
        camion = recogerDatosCamion();
        if(camion.getMatricula() !=null  && camion.getTipo() !=null && camion.getPotencia()!=null && camion.getModelo() !=null ){
            Camvista.mostrarMensaje(sqlCamion.insertarCamion(camion));
        }else{
             Camvista.mostrarMensaje("Por favor Ingresa todos los datos");
        }
        
    }

    public void listar(JTable tabla) {

        ArrayList<Camion> listaCamiones = sqlCamion.obtenerTodos();

        Object[] row = new Object[6];

        modelo.addColumn("Matricula");
        modelo.addColumn("Tipo");
        modelo.addColumn("Potencia");
        modelo.addColumn("Modelo");
        tabla.setModel(modelo);

        for (Camion camion : listaCamiones) {
            row[0] = camion.getMatricula();
            row[1] = camion.getTipo();
            row[2] = camion.getPotencia();
            row[3] = camion.getModelo();
            
            modelo.addRow(row); // Añadir fila al modelo de la tabla
        }
        tabla.setModel(modelo);
    }

    public void actualizarCamion() {
        camion = recogerDatosCamion(); // Llamar al nuevo método
        Camvista.mostrarMensaje(sqlCamion.actualizarCamion(camion));
    }

    public void consultarCamion(JTable tabla) {
        //Traigos los valores que se encuentren desde la vista
        if (!Camvista.inputMatricula.getText().isEmpty() || !Camvista.inputTipo.getText().isEmpty() || !Camvista.inputModelo.getText().isEmpty()
                || !Camvista.inputPotencia.getText().isEmpty()) {

            camion = recogerDatosCamion();

            ArrayList<Camion> listaCamion = sqlCamion.consultarCamion(camion);

            Object[] row = new Object[4];

            modelo.addColumn("Matricula");
            modelo.addColumn("Tipo");
            modelo.addColumn("Potencia");
            modelo.addColumn("Modelo");
            tabla.setModel(modelo);

            for (Camion camion : listaCamion) {
                row[0] = camion.getMatricula();
                row[1] = camion.getTipo();
                row[2] = camion.getPotencia();
                row[3] = camion.getModelo();

                modelo.addRow(row); // Añadir fila al modelo de la tabla
            }
            tabla.setModel(modelo);
        } else {
            Camvista.mostrarMensaje("Ingrese informacion a consultar");
        }

    }

    public void eliminar() {
        camion = recogerDatosCamion(); // Llamar al nuevo método
        Camvista.mostrarMensaje(sqlCamion.eliminarCamion(camion));

    }
    
}
