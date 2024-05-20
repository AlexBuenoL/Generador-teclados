package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Vista para el registro de usuarios.
 */
public class VistaRegistro extends VistaUsuario {

    /**
     * Constructor de la clase VistaRegistro.
     *
     * @param ctrlUsuarioPresentacion Controlador de presentación de usuarios.
     */
    public VistaRegistro(CtrlUsuarioPresentacion ctrlUsuarioPresentacion) {
        super(ctrlUsuarioPresentacion, "Registro");
        this.ctrlUsuarioPresentacion = ctrlUsuarioPresentacion;

        // Configurar interfaz
        configurarInterfazComunHijos(registroButton);
    }

    /**
     * Configura la interfaz común para hijos.
     *
     * @param button Botón de registro.
     */
    @Override
    public void configurarInterfazComunHijos(JButton button) {
        super.configurarInterfazComunHijos(button);
        configurarInterfazEspecificaHijos(button);
    }

    /**
     * Configura la interfaz específica para hijos.
     *
     * @param button Botón de registro.
     */
    @Override
    public void configurarInterfazEspecificaHijos(JButton button) {
        // Label de confirmar contraseña
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(confirmPasswordLabel, gbc);

        // Field de confirmar contraseña
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(confirmPasswordField, gbc);

        // Botón de registro
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(button, gbc);
    }

    /**
     * Configura la interfaz de usuario.
     */
    @Override
    public void configurarInterfaz() {
        // Vacío, ya que se utiliza la configuración específica para hijos.
    }

    /**
     * Muestra la ventana.
     */
    @Override
    public void mostrar() {
        frame.setVisible(true);
    }

    /**
     * Configura el botón de registro.
     */
    @Override
    protected void configurarBotonRegistro() {
        registroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica de registro (puedes personalizar según tus necesidades)
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                char[] confirmPassword = confirmPasswordField.getPassword();

                ctrlUsuarioPresentacion.registro(username, password, confirmPassword);
            }
        });
    }
}
