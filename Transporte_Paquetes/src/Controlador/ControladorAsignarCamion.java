package Controlador;

import Modelo.DAO.AsignarCamionesDAO;
import Modelo.DAO.CamioneroDAO;
import Modelo.DAO.CamionesDAO;
import Modelo.entidades.AsgnCamion;
import Modelo.entidades.Camion;

import Modelo.entidades.Camionero;
import Modelo.entidades.Paquete;
import Vista.Paneles.AsignarCamion;
import Vista.Paneles.Camioneros;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roy-j
 */
public class ControladorAsignarCamion {

    private AsignarCamion vista;
    private Camionero camionero;
    private Camion camion;
    private AsgnCamion asgncamion;
    private CamioneroDAO sqlCamionero;
    private CamionesDAO sqlCamiones;
    private AsignarCamionesDAO sqlASGN;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorAsignarCamion(AsignarCamion asgnvista) {
        this.vista = asgnvista;
        this.sqlCamionero = new CamioneroDAO();
        this.sqlCamiones = new CamionesDAO();
        this.sqlASGN = new AsignarCamionesDAO();
    }

    private AsgnCamion recogerDatosAsignacion() {
        String Cedula;
        int codigo = 0;
        int CedulaCam = 0;

        if (!vista.inputCodigo.getText().isEmpty()) {
            codigo = Integer.parseInt(vista.inputCodigo.getText());
        }
        Cedula = (String) vista.jCamioneros.getSelectedItem();
        ArrayList<Camionero> listaCamioneros = sqlCamionero.obtenerTodos();

        for (Camionero camionero : listaCamioneros) {
            String NombreCamionero = camionero.getNombre();
            if (NombreCamionero.equals(Cedula)) {
                CedulaCam = camionero.getCedula();
            }
        }

        String matricula = (String) vista.jCamiones.getSelectedItem();
        String Observaciones = vista.Observaciones.getText();
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formato);
        if (codigo != 0 && Cedula != null) {
            System.out.println("Entro aqui");
            return new AsgnCamion(codigo,CedulaCam, matricula, fechaFormateada,fechaFormateada,Observaciones);
        } else {
           return new AsgnCamion(CedulaCam, matricula, fechaFormateada);
        }
    
    }

    public void crearAsignacionCamionaCamionero() {
        asgncamion = recogerDatosAsignacion();
        if (asgncamion.getCedulaCamionero() != 0 && asgncamion.getMatriculaCamion() != null && asgncamion.getFechaInicio() != null) {
            vista.mostrarMensaje(sqlASGN.insertarCamionaCamionero(asgncamion));
        } else {
            vista.mostrarMensaje("Por favor Ingresa todos los datos");
        }

    }

    public void consultarCambioneroNombre(JComboBox<String> comboBox) {
        ArrayList<Camionero> listaCamioneros = sqlCamionero.obtenerTodos();

        comboBox.removeAllItems();

        for (Camionero camionero : listaCamioneros) {

            comboBox.addItem(camionero.getNombre());
        }

    }

    public void consultarCamionMatricula(JComboBox<String> comboBox) {
        ArrayList<Camion> listaCamiones = sqlCamiones.obtenerTodos();

        comboBox.removeAllItems();

        for (Camion camion : listaCamiones) {

            comboBox.addItem(camion.getMatricula());
        }

    }

    public void listar(JTable tabla) {

        ArrayList<AsgnCamion> listaAsignaciones = sqlASGN.obtenerTodos();

        Object[] row = new Object[6];

        modelo.addColumn("Asg_Codigo");
        modelo.addColumn("Cedula_Camionero");
        modelo.addColumn("Matricula_Camion");
        modelo.addColumn("Fecha_Inicio");
        modelo.addColumn("Fecha_Entrega");
        modelo.addColumn("Observaciones");
        tabla.setModel(modelo);

        for (AsgnCamion asgncamion : listaAsignaciones) {
            row[0] = asgncamion.getCodigo();
            row[1] = asgncamion.getCedulaCamionero();
            row[2] = asgncamion.getMatriculaCamion();
            row[3] = asgncamion.getFechaInicio();
            row[4] = asgncamion.getFechaEntrega();
            row[5] = asgncamion.getObservaciones();

            modelo.addRow(row); // Añadir fila al modelo de la tabla
        }
        tabla.setModel(modelo);
    }

    public void consultarCamioneros(JTable tabla) {
        //Traigos los valores que se encuentren desde la vista
        if (vista.jCamioneros.getSelectedObjects().toString() != null || vista.jCamiones.getSelectedObjects().toString() != null) {

            asgncamion = recogerDatosAsignacion();

            ArrayList<AsgnCamion> listaAsignaciones = sqlASGN.consultarAsingacion(asgncamion);

            Object[] row = new Object[6];

            modelo.addColumn("Asg_Codigo");
            modelo.addColumn("Cedula_Camionero");
            modelo.addColumn("Matricula_Camion");
            modelo.addColumn("Fecha_Inicio");
            modelo.addColumn("Fecha_Entrega");
            modelo.addColumn("Observaciones");
            tabla.setModel(modelo);

            for (AsgnCamion asgncamion : listaAsignaciones) {
                row[0] = asgncamion.getCodigo();
                row[1] = asgncamion.getCedulaCamionero();
                row[2] = asgncamion.getMatriculaCamion();
                row[3] = asgncamion.getFechaInicio();
                row[4] = asgncamion.getFechaEntrega();
                row[5] = asgncamion.getObservaciones();

                modelo.addRow(row); // Añadir fila al modelo de la tabla
            }
            tabla.setModel(modelo);
        } else {
            vista.mostrarMensaje("Seleccione informacion a consultar");
        }

    }

    public void actualizarAsignacion() {
        asgncamion = recogerDatosAsignacion();
        vista.mostrarMensaje(sqlASGN.actualizarAsignacion(asgncamion));

    }

    public void eliminar() {
        asgncamion = recogerDatosAsignacion(); // Llamar al nuevo método
        vista.mostrarMensaje(sqlASGN.eliminarAsignacion(asgncamion));

    }
}
