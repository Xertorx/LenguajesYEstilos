package Controlador;

import Modelo.DAO.CamioneroDAO;
import Modelo.DAO.PaquetesDAO;
import Modelo.DAO.ProvinciasDAO;
import Modelo.entidades.Provincia;
import Vista.Paneles.Provincias;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roy-j
 */
public class ControladorProvincias {

    private Provincias vista;
    private Provincia provincia;
    private PaquetesDAO sqlPaquetes;
    private CamioneroDAO sqlCamionero;
    private ProvinciasDAO sqlProvincias;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorProvincias(Provincias vista) {
        this.vista = vista;
        this.sqlPaquetes = new PaquetesDAO();
        this.sqlCamionero = new CamioneroDAO();
        this.sqlProvincias = new ProvinciasDAO();
    }

    private Provincia recogerDatosProvincia() {
        int codigo=0;
        String Cedula;
        int CedulaCam=0;
        
        String nombreProvincia = vista.inputProvincia.getText();
   
        String codigoTexto = vista.inputCodigo.getText();
 
        if (codigoTexto != null && !codigoTexto.trim().isEmpty()) {
            codigo = Integer.parseInt(codigoTexto);
        }

        if(codigo != 0){
            return new Provincia(codigo,nombreProvincia);
        }else{
             return new Provincia(nombreProvincia);
        }

    }

    public void crearProvincia() {
        provincia = recogerDatosProvincia();
        if (provincia.getNombre()!= null ) {
            vista.mostrarMensaje(sqlProvincias.insertarProvincia(provincia));
        } else {
            vista.mostrarMensaje("Por favor Ingresa todos los datos");
        }

    }

    public void listar(JTable tabla) {

        ArrayList<Provincia> listaProvincias = sqlProvincias.obtenerTodos();

        Object[] row = new Object[2];

        modelo.addColumn("prov_codigo");
        modelo.addColumn("prov_nombre");

        tabla.setModel(modelo);
        modelo.setRowCount(0); // Limpia todas las filas existentes
        for (Provincia provincia : listaProvincias) {
            row[0] = provincia.getCodigo();
            row[1] = provincia.getNombre();

            modelo.addRow(row); // Añadir fila al modelo de la tabla
        }
        tabla.setModel(modelo);
    }

    public void actualizarProvincia() {

        provincia = recogerDatosProvincia(); // Llamar al nuevo método
        vista.mostrarMensaje(sqlProvincias.actualizarProvincia(provincia));

    }

    public void consultarProvincia(JTable tabla) {
        //Traigos los valores que se encuentren desde la vista
        if (!vista.inputProvincia.getText().isEmpty()) {

            provincia = recogerDatosProvincia();

            ArrayList<Provincia> listaProvincia = sqlProvincias.consultarProvincia(provincia);

            Object[] row = new Object[2];

            modelo.addColumn("prov_codigo");
            modelo.addColumn("prov_nombre");
          
            tabla.setModel(modelo);

            for (Provincia provincia : listaProvincia) {
                row[0] = provincia.getCodigo();
                row[1] = provincia.getNombre();
                modelo.addRow(row); // Añadir fila al modelo de la tabla
            }
            tabla.setModel(modelo);
        } else {
            vista.mostrarMensaje("Ingrese informacion a consultar");
        }

    }

    public void eliminar() {
        provincia = recogerDatosProvincia(); // Llamar al nuevo método
        vista.mostrarMensaje(sqlProvincias.eliminarProvincia(provincia));

    }


}
