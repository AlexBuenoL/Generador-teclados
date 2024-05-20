package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Vista para mostrar información relacionada con los teclados.
 */

public class VistaMostrarTeclado extends VistaBase {
    private CtrlTecladosPresentacion ctrlTecladosPresentacion;
    private JTextArea contenidoArea;

     /**
     * Constructor de la clase VistaMostrarTeclado.
     *
     * @param ctrlTecladosPresentacion Controlador de presentación de teclados.
     */
    public VistaMostrarTeclado(CtrlTecladosPresentacion ctrlTecladosPresentacion) {
        super(ctrlTecladosPresentacion.getCtrlPresentacion(), "Mostrar Teclados");
        this.ctrlTecladosPresentacion = ctrlTecladosPresentacion;
        
        configurarInterfaz();
    }

    /**
     * Configura los botones de la interfaz para realizar acciones específicas.
     *
     * @param mostrarTecladoButton Botón para mostrar un teclado.
     * @param mostrarTecladosButton Botón para mostrar todos los teclados.
     * @param backButton Botón para volver a la vista de gestión de teclado.
     */
    private void configurarBotones(JButton mostrarTecladoButton, JButton mostrarTecladosButton, JButton backButton) {
        configurarBotonMostrarTeclado(mostrarTecladoButton);
        configurarBotonMostrarTodosTeclados(mostrarTecladosButton);
        configurarBotonVolver(backButton);
    }

    /**
     * Configura la interfaz de la vista para mostrar teclados.
     */
    @Override
    public void configurarInterfaz() {
        JButton mostrarTecladoButton = new JButton("Mostrar Teclado");
        JButton mostrarTecladosButton = new JButton("Mostrar todos Teclados");
        JButton backButton = new JButton("Volver");
        contenidoArea = new JTextArea();
        contenidoArea.setLineWrap(true);
        contenidoArea.setWrapStyleWord(true);
        contenidoArea.setEditable(false);

        configurarBotones(mostrarTecladoButton, mostrarTecladosButton, backButton);
        
        JPanel panel = new JPanel(new GridBagLayout());

        // Crear paneles para cada grupo de filas
        JPanel panelBotones = new JPanel(new GridBagLayout());
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        JScrollPane panelContenido = new JScrollPane(contenidoArea);
        panelContenido.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Utilizando GridBagLayout para mayor control sobre la disposición
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente

        int initialWidthButton = 220;
        int initialHeightButton = 50;

        mostrarTecladoButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        mostrarTecladosButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        backButton.setPreferredSize(new Dimension(160, 35));

        //panel botones
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        panelBotones.add(mostrarTecladoButton, gbc);

        // Botón Mostrar Frecuencias
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        panelBotones.add(mostrarTecladosButton, gbc);

        // Agregar paneles al panel principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(10, 20, 0, 20);
        panel.add(panelBotones, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.insets = new Insets(10, 20, 0, 20);
        panel.add(panelContenido, gbc);

        backButtonPanel.add(backButton, gbc);

        // Añadir los paneles al contenedor principal
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(backButtonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * Configura el botón para mostrar un teclado.
     *
     * @param mostrarTecladoButton Botón para mostrar un teclado.
     */
    private void configurarBotonMostrarTeclado(JButton mostrarTecladoButton) {
        mostrarTecladoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlTecladosPresentacion.mostrarTeclado();
                
            }
        });
    }

     /**
     * Configura el botón para mostrar todos los teclados.
     *
     * @param mostrarTecladosButton Botón para mostrar todos los teclados.
     */
    private void configurarBotonMostrarTodosTeclados(JButton mostrarTecladosButton) {
        mostrarTecladosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlTecladosPresentacion.mostrarTodosTeclados();
                
            }
        });
    }

     /**
     * Configura el botón para volver a la vista de gestión de teclado.
     *
     * @param backButton Botón para volver a la vista de gestión de teclado.
     */
    private void configurarBotonVolver(JButton backButton) {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
                ctrlTecladosPresentacion.mostrarVistaGestionTeclado();
                ocultar();
            }
        });
    }

    /**
     * Establece el texto en el área de contenido.
     *
     * @param text Texto a establecer.
     */
    public void setText(String text) {
        contenidoArea.setText(text);
    }

    /**
     * Limpia los campos del área de contenido.
     */
    public void limpiarCampos() {
        contenidoArea.setText("");
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
