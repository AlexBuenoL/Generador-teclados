package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;

/**
 * Clase base abstracta para las vistas en la aplicación. Proporciona funcionalidades comunes
 * y la estructura básica para las vistas.
 */
public abstract class VistaBase {
    /**
     * Controlador de presentación asociado a la vista.
     */
    protected CtrlPresentacion ctrlPresentacion;

    /**
     * Marco de la interfaz de usuario.
     */
    protected JFrame frame;

    /**
     * Ancho inicial de la ventana.
     */
    protected int initialWidthWindow;

    /**
     * Altura inicial de la ventana.
     */
    protected int initialHeightWindow;

    /**
     * Constructor de la clase VistaBase.
     *
     * @param ctrlPresentacion Controlador de presentación asociado a la vista.
     * @param titulo Título de la ventana.
     */
    public VistaBase(CtrlPresentacion ctrlPresentacion, String titulo) {
        this.ctrlPresentacion = ctrlPresentacion;
        frame = new JFrame(titulo);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialWidthWindow = 1100;
        initialHeightWindow = 700;
        frame.setPreferredSize(new Dimension(initialWidthWindow, initialHeightWindow));
    }

    /**
     * Configura la interfaz de usuario de la vista.
     */
    public abstract void configurarInterfaz();

    /**
     * Muestra la vista.
     */
    public abstract void mostrar();

    /**
     * Oculta la vista.
     */
    public abstract void ocultar();

    /**
     * Muestra un mensaje de error en un cuadro de diálogo.
     *
     * @param mensaje Mensaje de error.
     */
    protected void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje informativo en un cuadro de diálogo.
     *
     * @param mensaje Mensaje informativo.
     */
    protected void mostrarMensajeInform(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}
