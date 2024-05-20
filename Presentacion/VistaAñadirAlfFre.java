package edu.upc.prop.clusterxx.Presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa la vista para añadir alfabetos y frecuencias en la interfaz gráfica de usuario.
 * Extiende de la clase abstracta VistaAñadirDatos.
 */
public class VistaAñadirAlfFre extends VistaAñadirDatos {

    /**
     * Constructor de la clase que recibe una instancia del controlador de datos de presentación.
     *
     * @param ctrlDatosPresentacion Instancia del controlador de datos de presentación.
     */
    public VistaAñadirAlfFre(CtrlDatosPresentacion ctrlDatosPresentacion) {
        super(ctrlDatosPresentacion, "Añadir Alfabeto y Frecuencia");
        this.ctrlDatosPresentacion = ctrlDatosPresentacion;

        configurarInterfazHijos();
    }

    /**
     * Configura la interfaz específica para la vista de añadir alfabetos y frecuencias.
     * Este método debe ser implementado por las clases hijas.
     */
    @Override
    public void configurarInterfaz() {
        // Implementación específica para la configuración de la interfaz de esta vista
    }

    /**
     * Configura la acción del botón de información.
     */
    @Override
    protected void configurarBotonInfo() {
        // Configurar acción del botón de información
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarInfoAñadir();
            }
        });
    }

    /**
     * Configura la acción del botón de volver.
     */
    @Override
    protected void configurarBotonVolver() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para volver a VistaUsuario
                limpiarCampos();
                ctrlDatosPresentacion.mostrarVistaAñadirDatos();
                ocultar();
            }
        });
    }

    /**
     * Muestra la ventana de la vista.
     */
    @Override
    public void mostrar() {
        frame.setVisible(true);
    }

    /**
     * Oculta la ventana de la vista.
     */
    @Override
    public void ocultar() {
        frame.setVisible(false);
    }
}

