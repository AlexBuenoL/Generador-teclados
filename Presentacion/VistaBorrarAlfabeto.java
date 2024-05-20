package edu.upc.prop.clusterxx.Presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa la vista para borrar un alfabeto en la interfaz de usuario.
 * Extiende de VistaBorrarDatos y proporciona la interfaz gráfica para eliminar un alfabeto.
 */
public class VistaBorrarAlfabeto extends VistaBorrarDatos {

    /**
     * Constructor de la clase VistaBorrarAlfabeto.
     *
     * @param ctrlDatosPresentacion Controlador de datos de presentación asociado a la vista.
     */
    public VistaBorrarAlfabeto(CtrlDatosPresentacion ctrlDatosPresentacion) {
        super(ctrlDatosPresentacion, "Borrar Alfabeto");
        this.ctrlDatosPresentacion = ctrlDatosPresentacion;  

        configurarInterfazHijos();
    }

    /**
     * Configura la interfaz gráfica de la vista.
     * Este método debe ser implementado por las subclases.
     */
    @Override
    public void configurarInterfaz() {
        // Implementación específica para la configuración de la interfaz, si es necesario.
    }

    /**
     * Configura el botón de borrar alfabeto con su respectivo ActionListener.
     * Este método se llama desde la configuración de la interfaz hija.
     */
    @Override
    protected void configurarBotonBorrarAlf() {
        borrarAlfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idalfabetoField.getText();
                ctrlDatosPresentacion.borrarAlfabeto(id);
                limpiarCampos();
            }
        });
    }

    /**
     * Configura el botón de volver con su respectivo ActionListener.
     * Este método se llama desde la configuración de la interfaz hija.
     */
    @Override
    protected void configurarBotonVolver() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para volver a VistaUsuario
                limpiarCampos();
                ctrlDatosPresentacion.mostrarVistaBorrarDatos();
                ocultar();
            }
        });
    }

    /**
     * Muestra la vista.
     */
    @Override
    public void mostrar() {
        frame.setVisible(true);
    }

    /**
     * Oculta la vista.
     */
    @Override
    public void ocultar() {
        frame.setVisible(false);
    }
}
