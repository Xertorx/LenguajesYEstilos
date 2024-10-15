package Controlador;

import Modelo.DAO.DetallesLlegadaDAO;
import Modelo.DAO.PaquetesDAO;
import Modelo.DAO.ProvinciasDAO;
import Modelo.entidades.DetallesLlegada;
import Modelo.entidades.Paquete;
import Modelo.entidades.Provincia;
import Vista.Paneles.LlegadaPaquete;
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
public class ControladorDetallesLlegada {

    private Paquete paquete;
    private Provincia provincia;
    private DetallesLlegada detllegada;
    private LlegadaPaquete vista;
    private DetallesLlegadaDAO sqlDET;
    private PaquetesDAO sqlPaquete;
    private ProvinciasDAO sqlProvincias;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorDetallesLlegada(LlegadaPaquete vista) {
        this.vista = vista;
        this.sqlDET = new DetallesLlegadaDAO();
        this.sqlPaquete = new PaquetesDAO();
        this.sqlProvincias = new ProvinciasDAO();
    }

    private DetallesLlegada recogerDetalles() {
        String Paquete;
        String Provincia;
        int cdp = 0;
        int cdProv = 0;
        int codigo = 0;
        System.out.println(codigo);
        if (!vista.Codigo.getText().isEmpty()) {
            codigo = Integer.parseInt(vista.Codigo.getText()); //Traer la informacion del codigo si tiene algo
        }
        System.out.println(codigo);
        Paquete = (String) vista.JPaquete.getSelectedItem(); //Traer la informacion del Checkbox
        cdp = Integer.parseInt(Paquete); //Pasar la informaciona int
        Provincia = (String) vista.JProvincia.getSelectedItem(); //Traer la informacion del Checkbox

        ArrayList<Provincia> listaProvincias = sqlProvincias.obtenerTodos();//Crear un array para guardar lo obtenido de la funcionDAO

        for (Provincia provincia : listaProvincias) { //Encontrar similitud con la informacion recogida y el SQL mediante DAO
            String nombreProvincia = provincia.getNombre();
            if (nombreProvincia.equals(Provincia)) {
                cdProv = provincia.getCodigo();
            }
        }
        String Observaciones = vista.Observaciones.getText(); //Obetener las observaciones
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formato);

        if (codigo != 0) {
            return new DetallesLlegada(codigo, cdp, cdProv, fechaFormateada, Observaciones);
            
        } else {
            return new DetallesLlegada(cdp, cdProv, fechaFormateada, Observaciones);
        }
    }

    public void crearReporteLlegada() {
        detllegada = recogerDetalles();
        if (detllegada.getPaqCodigo() != 0 && detllegada.getProvCod() != 0 && detllegada.getObservaciones() != null) {
            vista.mostrarMensaje(sqlDET.reportarLLegadaPaquete(detllegada));
        } else {
            vista.mostrarMensaje("Por favor Ingresa todos los datos");
        }

    }

    public void consultarPaquete(JComboBox<String> comboBox) {
        ArrayList<Paquete> listaPaquetes = sqlPaquete.obtenerTodos();

        comboBox.removeAllItems();

        for (Paquete paquete : listaPaquetes) {

            comboBox.addItem(String.valueOf(paquete.getCodigo()));
        }

    }

    public void consultarProvincia(JComboBox<String> comboBox) {
        ArrayList<Provincia> listaProvincia = sqlProvincias.obtenerTodos();

        comboBox.removeAllItems();

        for (Provincia provincia : listaProvincia) {

            comboBox.addItem(provincia.getNombre());
        }

    }

    public void listar(JTable tabla) {

        ArrayList<DetallesLlegada> listaReporte = sqlDET.obtenerTodos();

        Object[] row = new Object[5];

        modelo.addColumn("det_codigo");
        modelo.addColumn("paq_codigo");
        modelo.addColumn("prov_codigo");
        modelo.addColumn("det_Fecha_entrega");
        modelo.addColumn("det_Observaciones");
        tabla.setModel(modelo);

        for (DetallesLlegada detllegada : listaReporte) {
            row[0] = detllegada.getCodigo();
            row[1] = detllegada.getPaqCodigo();
            row[2] = detllegada.getProvCod();
            row[3] = detllegada.getFechaEntrega();
            row[4] = detllegada.getObservaciones();

            modelo.addRow(row); // Añadir fila al modelo de la tabla
        }
        tabla.setModel(modelo);
    }

    public void consultarReporte(JTable tabla) {
        //Traigos los valores que se encuentren desde la vista
        if (vista.JPaquete.getSelectedObjects().toString() != null || vista.JProvincia.getSelectedObjects().toString() != null) {

            detllegada = recogerDetalles();

            ArrayList<DetallesLlegada> listaLlegada = sqlDET.consultarReporte(detllegada);

            Object[] row = new Object[5];

            modelo.addColumn("det_codigo");
            modelo.addColumn("paq_codigo");
            modelo.addColumn("prov_codigo");
            modelo.addColumn("det_Fecha_entrega");
            modelo.addColumn("det_Observaciones");
            tabla.setModel(modelo);

            for (DetallesLlegada detllegada : listaLlegada) {
                row[0] = detllegada.getCodigo();
                row[1] = detllegada.getPaqCodigo();
                row[2] = detllegada.getProvCod();
                row[3] = detllegada.getFechaEntrega();
                row[4] = detllegada.getObservaciones();

                modelo.addRow(row); // Añadir fila al modelo de la tabla
            }
            tabla.setModel(modelo);
        } else {
            vista.mostrarMensaje("Seleccione informacion a consultar");
        }

    }

    public void actualizarReporte() {
        detllegada = recogerDetalles();
        vista.mostrarMensaje(sqlDET.actualizarReporte(detllegada));

    }

    public void eliminar() {
       detllegada = recogerDetalles(); // Llamar al nuevo método
        vista.mostrarMensaje(sqlDET.eliminarReporte(detllegada));

    }
}
