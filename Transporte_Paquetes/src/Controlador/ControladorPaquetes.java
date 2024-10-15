package Controlador;

import Modelo.DAO.CamioneroDAO;
import Modelo.DAO.CamionesDAO;
import Modelo.DAO.PaquetesDAO;
import Modelo.entidades.Camionero;
import Modelo.entidades.Paquete;
import Vista.Paneles.Paquetes;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roy-j
 */
public class ControladorPaquetes {

    private Paquetes vista;
    private Paquete paquete;
    private PaquetesDAO sqlPaquetes;
    private CamioneroDAO sqlCamionero;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorPaquetes(Paquetes paqvista) {
        this.vista = paqvista;
        this.sqlPaquetes = new PaquetesDAO();
        this.sqlCamionero = new CamioneroDAO();
    }

    private Paquete recogerDatosPaquete() {
        int codigo=0;
        String Cedula;
        int CedulaCam=0;
        
        String destinatario = vista.inputDestinatario.getText();
        String direccion = vista.inputDireccion.getText();
        String descripcion = vista.inputDescripcion.getText();
        
        String codigoTexto = vista.inputCodigo.getText();
 
        if (codigoTexto != null && !codigoTexto.trim().isEmpty()) {
            codigo = Integer.parseInt(codigoTexto);
        }
        Cedula = (String) vista.JcCamioneros.getSelectedItem();
        ArrayList<Camionero> listaCamioneros = sqlCamionero.obtenerTodos();

        for (Camionero camionero : listaCamioneros) {
            String NombreCamionero = camionero.getNombre();
            if (NombreCamionero.equals(Cedula)) {
                CedulaCam = camionero.getCedula();
            }
        }System.out.println("Valor de la cedula recogida"+CedulaCam);
      
        if(codigo != 0 && Cedula !=null ){
            System.out.println("Entro aqui");
            return new Paquete(codigo, destinatario, direccion, descripcion,CedulaCam);
        }else if (Cedula !=null){
             return new Paquete(destinatario, direccion, descripcion,CedulaCam);
        }
        if (codigo != 0) {
            return new Paquete(codigo, destinatario, direccion, descripcion);
        }
        return new Paquete(destinatario, direccion, descripcion);

    }

    public void crearCamionero() {
        paquete = recogerDatosPaquete();
        if (paquete.getDestinatario() != null || paquete.getDireccion() != null || paquete.getDescripcion() != null) {
            vista.mostrarMensaje(sqlPaquetes.insertarPaquete(paquete));
        } else {
            vista.mostrarMensaje("Por favor Ingresa todos los datos");
        }

    }

    public void listar(JTable tabla) {

        ArrayList<Paquete> listaPaquetes = sqlPaquetes.obtenerTodos();

        Object[] row = new Object[5];

        modelo.addColumn("paq_Codigo");
        modelo.addColumn("paq_Destinatario");
        modelo.addColumn("paq_DireccionDestinatario");
        modelo.addColumn("paq_Descripcion");
        modelo.addColumn("cam_Cedula");

        tabla.setModel(modelo);
        modelo.setRowCount(0); // Limpia todas las filas existentes
        for (Paquete paquete : listaPaquetes) {
            row[0] = paquete.getCodigo();
            row[1] = paquete.getDestinatario();
            row[2] = paquete.getDireccion();
            row[3] = paquete.getDescripcion();
            int cedula = paquete.getCamioneroCedula();
            if (cedula == 0) {
                String cd = "Sin Asignacion";
                row[4] = cd;
            } else {
                row[4] = paquete.getCamioneroCedula();
            }

            modelo.addRow(row); // Añadir fila al modelo de la tabla
        }
        tabla.setModel(modelo);
    }

    public void actualizarPaquete() {

        paquete = recogerDatosPaquete(); // Llamar al nuevo método
        vista.mostrarMensaje(sqlPaquetes.actualizarPaquete(paquete));

    }

    public void consultarPaquete(JTable tabla) {
        //Traigos los valores que se encuentren desde la vista
        if (!vista.inputDestinatario.getText().isEmpty() || !vista.inputDireccion.getText().isEmpty() || !vista.inputDescripcion.getText().isEmpty()) {

            paquete = recogerDatosPaquete();

            ArrayList<Paquete> listaPaquetes = sqlPaquetes.consultarPaquete(paquete);

            Object[] row = new Object[6];

            modelo.addColumn("paq_Codigo");
            modelo.addColumn("paq_Destinatario");
            modelo.addColumn("paq_DireccionDestinatario");
            modelo.addColumn("paq_Descripcion");
            modelo.addColumn("cam_Cedula");
            tabla.setModel(modelo);

            for (Paquete paquete : listaPaquetes) {
                row[0] = paquete.getCodigo();
                row[1] = paquete.getDestinatario();
                row[2] = paquete.getDireccion();
                row[3] = paquete.getDescripcion();
                int cedula = paquete.getCamioneroCedula();
                if (cedula == 0) {
                    String cd = "Sin Asignacion";
                    row[4] = cd;
                } else {
                    row[4] = paquete.getCamioneroCedula();
                }

                modelo.addRow(row); // Añadir fila al modelo de la tabla
            }
            tabla.setModel(modelo);
        } else {
            vista.mostrarMensaje("Ingrese informacion a consultar");
        }

    }

    public void eliminar() {
        paquete = recogerDatosPaquete(); // Llamar al nuevo método
        vista.mostrarMensaje(sqlPaquetes.eliminarPaquete(paquete));

    }

    public void consultarCambioneroNombre(JComboBox<String> comboBox) {
        ArrayList<Camionero> listaCamioneros = sqlCamionero.obtenerTodos();

        comboBox.removeAllItems();

        for (Camionero camionero : listaCamioneros) {

            comboBox.addItem(camionero.getNombre());
        }

    }

}
