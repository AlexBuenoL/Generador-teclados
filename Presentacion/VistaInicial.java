package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa la vista inicial en la interfaz gráfica.
 */
public class VistaInicial extends VistaBase {
    private CtrlDatosPresentacion ctrlDatosPresentacion;
    private CtrlTecladosPresentacion ctrlTecladosPresentacion;

    /**
     * Constructor de la clase VistaInicial.
     * @param ctrlPresentacion Controlador de presentación.
     * @param ctrlDatosPresentacion Controlador de presentación de datos.
     * @param ctrlTecladosPresentacion Controlador de presentación de teclados.
     */
    public VistaInicial(CtrlPresentacion ctrlPresentacion, CtrlDatosPresentacion ctrlDatosPresentacion, CtrlTecladosPresentacion ctrlTecladosPresentacion) {
        super(ctrlPresentacion, "Inicio");
        this.ctrlDatosPresentacion = ctrlDatosPresentacion;
        this.ctrlTecladosPresentacion = ctrlTecladosPresentacion;
        frame.setResizable(true);

        configurarInterfaz();
    }

    /**
     * Configura los botones en la interfaz gráfica.
     * @param generarTecladoButton Botón para generar teclado.
     * @param gestionarDatosButton Botón para gestionar datos.
     * @param gestionarTecladosButton Botón para gestionar teclados.
     * @param exitButton Botón para salir/cerrar sesión.
     */
    private void configurarBotones(JButton generarTecladoButton, JButton gestionarDatosButton, JButton gestionarTecladosButton, JButton exitButton) {
        configurarBotonGenerarTeclado(generarTecladoButton);
        configurarBotonGestionarTeclado(gestionarTecladosButton);
        configurarBotonGestionarDatos(gestionarDatosButton);
        configurarBotonSalir(exitButton);
    }

    @Override
    public void configurarInterfaz() {
        JButton gestionarDatosButton = new JButton("Gestionar Datos");
        JButton generarTecladoButton = new JButton("Generar Teclado");
        JButton gestionarTecladosButton = new JButton("Gestionar Teclados");
        JButton exitButton = new JButton("Cerrar Sesión");

        configurarBotones(generarTecladoButton, gestionarDatosButton, gestionarTecladosButton, exitButton);

        // Panel para agrupar
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente

        // Establecer el tamaño preferido inicial en función del tamaño de la ventana
        int initialWidthButton = 250;
        int initialHeightButton = 50;

        generarTecladoButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        gestionarDatosButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        gestionarTecladosButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        exitButton.setPreferredSize(new Dimension(160, 35));

        // Generar Teclado button
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(generarTecladoButton, gbc);

        // gestionar Datos button
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(gestionarDatosButton, gbc);

        // gestionar Teclados button
        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(gestionarTecladosButton, gbc);

        // Botón de salir en la esquina inferior derecha
        exitPanel.add(exitButton);

        // Añadir los paneles al contenedor principal
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        frame.getContentPane().add(exitPanel, BorderLayout.SOUTH);

        // Centrar la ventana en la pantalla
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * Configura la acción del botón para gestionar datos.
     * @param gestionarDatosButton Botón para gestionar datos.
     */
    private void configurarBotonGestionarDatos(JButton gestionarDatosButton) {
        gestionarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarVistaGestionarDatos();
            }
        });
    }

    /**
     * Configura la acción del botón para generar teclado.
     * @param generarTecladoButton Botón para generar teclado.
     */
    private void configurarBotonGenerarTeclado(JButton generarTecladoButton) {
        generarTecladoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlTecladosPresentacion.mostrarVistaGenerarTeclado();
            }
        });
    }

    /**
     * Configura la acción del botón para gestionar teclados.
     * @param gestionarTecladosButton Botón para gestionar teclados.
     */
    private void configurarBotonGestionarTeclado(JButton gestionarTecladosButton) {
        gestionarTecladosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlTecladosPresentacion.mostrarVistaGestionTeclado();
            }
        });
    }

    /**
     * Configura la acción del botón para salir/cerrar sesión.
     * @param exitButton Botón para salir/cerrar sesión.
     */
    private void configurarBotonSalir(JButton exitButton) {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.cerrarSesion();
            }
        });
    }

    @Override
    public void mostrar() {
        frame.setVisible(true);
    }

    @Override
    public void ocultar() {
        frame.setVisible(false);
    }
}
