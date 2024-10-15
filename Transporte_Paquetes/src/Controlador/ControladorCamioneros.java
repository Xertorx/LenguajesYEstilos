package Controlador;

import Modelo.DAO.CamioneroDAO;
import Modelo.entidades.Camion;
import Modelo.entidades.Camionero;
import Vista.Paneles.Camioneros;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roy-j
 */
public class ControladorCamioneros {

    private Camioneros Camvista;
    private Camionero camionero;
    private CamioneroDAO sqlCamionero;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorCamioneros(Camioneros camvista) {
        this.Camvista = camvista;
        this.sqlCamionero = new CamioneroDAO();
    }

    private Camionero recogerDatosCamionero() {
        
        int cedula = 0;
        if (!Camvista.inputSalario.getText().isEmpty()) {
            cedula = Integer.parseInt(Camvista.inputCedula.getText());
        }
        String nombre = Camvista.inputNombre.getText();
        String telefono = Camvista.inputTelefono.getText();
        String direccion = Camvista.inputDireccion.getText();
        String salario = Camvista.inputSalario.getText();
        String poblacion = Camvista.inputPoblacion.getText();

        return new Camionero(cedula, nombre, telefono, direccion, salario, poblacion);
    }

    public void crearCamionero() {
        camionero = recogerDatosCamionero();
        if(camionero.getCedula() > 0  && camionero.getNombre()!=null && camionero.getTelefono()!=null && camionero.getDireccion() !=null && camionero.getSalario() !=null && camionero.getPoblacion() !=null){
            Camvista.mostrarMensaje(sqlCamionero.insertarCamionero(camionero));
        }else{
             Camvista.mostrarMensaje("Por favor Ingresa todos los datos");
        }
        
    }

    public void listar(JTable tabla) {

        ArrayList<Camionero> listaCamioneros = sqlCamionero.obtenerTodos();

        Object[] row = new Object[6];

        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("Direccion");
        modelo.addColumn("Salario");
        modelo.addColumn("Poblacion");
        tabla.setModel(modelo);

        for (Camionero camionero : listaCamioneros) {
            row[0] = camionero.getCedula();
            row[1] = camionero.getNombre();
            row[2] = camionero.getTelefono();
            row[3] = camionero.getDireccion();
            row[4] = camionero.getSalario();
            row[5] = camionero.getPoblacion();
            modelo.addRow(row); // Añadir fila al modelo de la tabla
        }
        tabla.setModel(modelo);
    }

    public void actualizarCamionero() {
        camionero = recogerDatosCamionero(); // Llamar al nuevo método
        Camvista.mostrarMensaje(sqlCamionero.actualizarCamionero(camionero));

    }

    public void consultarCamioneros(JTable tabla) {
        //Traigos los valores que se encuentren desde la vista
        if (!Camvista.inputCedula.getText().isEmpty() || !Camvista.inputNombre.getText().isEmpty() || !Camvista.inputTelefono.getText().isEmpty()
                || !Camvista.inputDireccion.getText().isEmpty() || !Camvista.inputSalario.getText().isEmpty() || !Camvista.inputPoblacion.getText().isEmpty()) {

            camionero = recogerDatosCamionero();

            ArrayList<Camionero> listaCamioneros = sqlCamionero.consultarCamionero(camionero);

            Object[] row = new Object[6];

            modelo.addColumn("Cedula");
            modelo.addColumn("Nombre");
            modelo.addColumn("Telefono");
            modelo.addColumn("Direccion");
            modelo.addColumn("Salario");
            modelo.addColumn("Poblacion");
            tabla.setModel(modelo);

            for (Camionero camionero : listaCamioneros) {
                row[0] = camionero.getCedula();
                row[1] = camionero.getNombre();
                row[2] = camionero.getTelefono();
                row[3] = camionero.getDireccion();
                row[4] = camionero.getSalario();
                row[5] = camionero.getPoblacion();
                modelo.addRow(row); // Añadir fila al modelo de la tabla
            }
            tabla.setModel(modelo);
        } else {
            Camvista.mostrarMensaje("Ingrese informacion a consultar");
        }

    }

    public void eliminar() {
        camionero = recogerDatosCamionero(); // Llamar al nuevo método
        Camvista.mostrarMensaje(sqlCamionero.eliminarCamionero(camionero));

    }
    
}
