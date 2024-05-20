package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa la interfaz de usuario para el proceso de inicio de sesión.
 * Hereda de la clase VistaUsuario.
 */
public class VistaLogIn extends VistaUsuario {

    /**
     * Constructor de la clase VistaLogIn.
     * @param ctrlUsuarioPresentacion Controlador de la presentación de usuario.
     */
    public VistaLogIn(CtrlUsuarioPresentacion ctrlUsuarioPresentacion) {
        super(ctrlUsuarioPresentacion, "Log In");
        this.ctrlUsuarioPresentacion = ctrlUsuarioPresentacion;

        // Configura la interfaz de login
        configurarInterfazComunHijos(loginButton);
    }

    /**
     * Configura la interfaz común de la vista de inicio de sesión.
     *
     * @param button Botón asociado a la interfaz.
     */
    @Override
    protected void configurarInterfazComunHijos(JButton button) {
        super.configurarInterfazComunHijos(button);
        configurarInterfazEspecificaHijos(button);
    }

    /**
     * Configura la interfaz específica de la vista de inicio de sesión.
     *
     * @param button Botón asociado a la interfaz.
     */
    @Override
    public void configurarInterfazEspecificaHijos(JButton button) {
        //boton
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(button, gbc);

        // Añadir espacio vertical entre botones
        gbc.insets = new Insets(40, 0, 0, 0);  // Añade 10 píxeles de margen arriba del botón newAccButton

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(newAccLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(newAccButton, gbc);

        // Restaura los insets a su valor predeterminado
        gbc.insets = new Insets(5, 5, 5, 5);
    }

    /**
     * Configura la interfaz de la vista de inicio de sesión.
     */
    @Override
    public void configurarInterfaz() {
        // Dejo vacío el método del padre
    }

    /**
     * Muestra la interfaz de la vista de inicio de sesión.
     */
    @Override
    public void mostrar() {
        frame.setVisible(true);
    }
    
    /**
     * Configura la acción a realizar cuando se presiona el botón de inicio de sesión.
     */
    @Override
    protected void configurarBotonLogin() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica de login
                String username = usernameField.getText();
                char[] password = passwordField.getPassword(); //char[] para mayor seguridad

                ctrlUsuarioPresentacion.login(username, password);
            }
        });
    }
}
