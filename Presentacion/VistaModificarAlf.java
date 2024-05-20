package edu.upc.prop.clusterxx.Presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa la interfaz de usuario para modificar alfabetos.
 * Hereda de la clase VistaModificarDatos.
 */
public class VistaModificarAlf extends VistaModificarDatos {

    /**
     * Constructor de la clase VistaModificarAlf.
     * @param ctrlDatosPresentacion Controlador de la presentación de datos.
     */
    public VistaModificarAlf(CtrlDatosPresentacion ctrlDatosPresentacion) {
        super(ctrlDatosPresentacion, "Modificar Alfabeto");
        this.ctrlDatosPresentacion = ctrlDatosPresentacion; 

        configurarInterfazHijos();
    }

    /**
     * Configura la interfaz de la vista de modificación de alfabetos.
     */
    @Override
    public void configurarInterfaz() {
        
    }

    /**
     * Configura la acción a realizar cuando se presiona el botón de modificar alfabeto.
     */
    @Override
    protected void configurarBotonModificarAlf() {
        modAlfabetoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idalfabetoField.getText();
                int opc=0;
                if(añadir.isSelected()) opc = 1;
                else if(quitar.isSelected()) opc = 2;
                else opc = 3;
                String newAlf = newAlfabetoField.getText();
                ctrlDatosPresentacion.modificarAlfabeto(id, newAlf, opc);
                limpiarCampos();
            }
        });
    }

    /**
     * Establece el texto de la interfaz para mostrar los alfabetos.
     *
     * @param text Texto a mostrar.
     */
    public void setText(String text) {
        alfabetos.setText(text);
    }

    /**
     * Configura la acción a realizar cuando se presiona el botón de volver.
     */
    @Override
    protected void configurarBotonVolver() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para volver a VistaUsuario
                limpiarCampos();
                ctrlDatosPresentacion.mostrarVistaModificarDatos();
                ocultar();
            }
        });
    }

    /**
     * Muestra la interfaz de la vista de modificación de alfabetos.
     */
    @Override
    public void mostrar() {
        frame.setVisible(true);
    }

    /**
     * Oculta la interfaz de la vista de modificación de alfabetos.
     */
    @Override
    public void ocultar() {
        frame.setVisible(false);
    }
    
}
