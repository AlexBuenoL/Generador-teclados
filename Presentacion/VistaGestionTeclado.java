package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa la vista para gestionar teclados en la interfaz gráfica.
 */
public class VistaGestionTeclado extends VistaBase {
    private CtrlTecladosPresentacion ctrlTecladosPresentacion;

    /**
     * Constructor de la clase VistaGestionTeclado.
     * @param ctrlTecladosPresentacion Controlador de presentación de teclados.
     */
    public VistaGestionTeclado(CtrlTecladosPresentacion ctrlTecladosPresentacion) {
        super(ctrlTecladosPresentacion.getCtrlPresentacion(), "Gestion Teclados");
        this.ctrlTecladosPresentacion = ctrlTecladosPresentacion;

        configurarInterfaz();
    }

    /**
     * Configura los botones en la interfaz gráfica.
     * @param mostrarTecladosButton Botón para mostrar teclados.
     * @param modificarTecladoButton Botón para modificar teclados.
     * @param borrarTecladoButton Botón para borrar teclados.
     * @param backButton Botón para volver.
     */
    public void configurarBotones(JButton mostrarTecladosButton, JButton modificarTecladoButton, JButton borrarTecladoButton, JButton backButton) {
        configurarBotonMostrar(mostrarTecladosButton);
        configurarBotonModificar(modificarTecladoButton);
        configurarBotonBorrar(borrarTecladoButton);
        configurarBotonVolver(backButton);
    }

    @Override
    public void configurarInterfaz() {
        JButton mostrarTecladosButton = new JButton("Mostrar Teclados");
        JButton modificarTecladoButton = new JButton("Modificar Teclados");
        JButton borrarTecladoButton = new JButton("Borrar Teclados");
        JButton backButton = new JButton("Volver");

        configurarBotones(mostrarTecladosButton, modificarTecladoButton, borrarTecladoButton, backButton);

        // Panel para agrupar
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente

        // Establecer el tamaño preferido inicial en función del tamaño de la ventana
        int initialWidthButton = 250;
        int initialHeightButton = 50;

        mostrarTecladosButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        modificarTecladoButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        borrarTecladoButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        backButton.setPreferredSize(new Dimension(160, 35));

        // Generar Teclado button
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(mostrarTecladosButton, gbc);

        // gestionar Datos button
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(modificarTecladoButton, gbc);

        // gestionar Teclados button
        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(borrarTecladoButton, gbc);

        // Botón de salir en la esquina inferior derecha
        backPanel.add(backButton);

        // Añadir los paneles al contenedor principal
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        frame.getContentPane().add(backPanel, BorderLayout.SOUTH);

        // Centrar la ventana en la pantalla
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * Configura la acción del botón para mostrar teclados.
     * @param mostrarTecladosButton Botón para mostrar teclados.
     */
    private void configurarBotonMostrar(JButton mostrarTecladosButton) {
        mostrarTecladosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlTecladosPresentacion.mostrarVistaMostrarTeclado();
                ocultar();
            }
        });
    }

    /**
     * Configura la acción del botón para modificar teclados.
     * @param modificarTecladoButton Botón para modificar teclados.
     */
    private void configurarBotonModificar(JButton modificarTecladoButton) {
        modificarTecladoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlTecladosPresentacion.mostrarTodosTecladosModifcar();
                ctrlTecladosPresentacion.mostrarVistaModificarTeclado();
                ocultar();
            }
        });
    }

    /**
     * Configura la acción del botón para borrar teclados.
     * @param borrarTecladoButton Botón para borrar teclados.
     */
    private void configurarBotonBorrar(JButton borrarTecladoButton) {
        borrarTecladoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlTecladosPresentacion.mostrarTodosTecladosBorrar();
                ctrlTecladosPresentacion.mostrarVistaBorrarTeclado();
                ocultar();
            }
        });
    }

    /**
     * Configura la acción del botón para volver.
     * @param backButton Botón para volver.
     */
    private void configurarBotonVolver(JButton backButton) {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlTecladosPresentacion.mostrarVistaInicial();
                ocultar();
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
