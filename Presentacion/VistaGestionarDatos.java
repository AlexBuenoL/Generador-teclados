package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa la vista para gestionar datos en la interfaz gráfica.
 */
public class VistaGestionarDatos extends VistaBase {
    private CtrlDatosPresentacion ctrlDatosPresentacion; 

    /**
     * Constructor de la clase VistaGestionarDatos.
     * @param ctrlDatosPresentacion Controlador de presentación de datos.
     */
    public VistaGestionarDatos(CtrlDatosPresentacion ctrlDatosPresentacion) {
        super(ctrlDatosPresentacion.getCtrlPresentacion(), "Gestionar Datos");
        this.ctrlDatosPresentacion = ctrlDatosPresentacion;
        
        configurarInterfaz();
    }

    /**
     * Configura los botones en la interfaz gráfica.
     * @param addDato Botón para añadir datos.
     * @param modDato Botón para modificar datos.
     * @param mostrarDatos Botón para mostrar datos.
     * @param borrarDatos Botón para borrar datos.
     * @param back Botón para volver.
     */
    private void configurarBotones(JButton addDato, JButton modDato, JButton mostrarDatos, JButton borrarDatos, JButton back) {

        configurarBotonAñadirDatos(addDato);
        configurarBotonModificarDatos(modDato);
        configurarBotonMostrarDatos(mostrarDatos);
        configurarBotonBorrarDatos(borrarDatos);
        configurarBotonVolver(back);
    }

    @Override
    public void configurarInterfaz() {
        JButton addDatosButton = new JButton("Añadir Datos");
        JButton modificarDatosButton = new JButton("Modificar Datos");
        JButton mostrarDatosButton = new JButton("Mostrar Datos");
        JButton borrarDatosButton = new JButton("Borrar Datos");
        JButton backButton = new JButton("Volver");

        configurarBotones(addDatosButton, modificarDatosButton, mostrarDatosButton, borrarDatosButton, backButton);

        // Panel para agrupar
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        JPanel notaPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente

        // Establecer el tamaño preferido inicial en función del tamaño de la ventana
        int initialWidthButton = 250;
        int initialHeightButton = 50;

        addDatosButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        modificarDatosButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        mostrarDatosButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        borrarDatosButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        backButton.setPreferredSize(new Dimension(160, 35));


        // Añadir datos
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        buttonPanel.add(addDatosButton, gbc);

        // Modificar datos
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        buttonPanel.add(modificarDatosButton, gbc);

        // Mostrar Datos button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Vuelve a un ancho normal
        buttonPanel.add(mostrarDatosButton, gbc);

        //borra datos
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;  // Vuelve a un ancho normal
        buttonPanel.add(borrarDatosButton, gbc);

        // Botón de salir en la esquina inferior derecha
        backPanel.add(backButton);


        // Añadir los paneles al contenedor principal
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        frame.getContentPane().add(backPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(notaPanel, BorderLayout.NORTH);

        // Centrar la ventana en la pantalla
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * Configura la acción del botón para añadir datos.
     * @param addDatosButton Botón para añadir datos.
     */
    private void configurarBotonAñadirDatos(JButton addDatosButton) {
        addDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarVistaAñadirDatos();
            }
        });
    }

    /**
     * Configura la acción del botón para modificar datos.
     * @param modificarDatosButton Botón para modificar datos.
     */
    private void configurarBotonModificarDatos(JButton modificarDatosButton) {
        modificarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarVistaModificarDatos();
            }
        });

    }

    /**
     * Configura la acción del botón para mostrar datos.
     * @param mostrarDatosButton Botón para mostrar datos.
     */
    private void configurarBotonMostrarDatos(JButton mostrarDatosButton) {
        mostrarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarVistaMostrarDatos();
            }
        });
    }

    /**
     * Configura la acción del botón para borrar datos.
     * @param borrarDatosButton Botón para borrar datos.
     */
    private void configurarBotonBorrarDatos(JButton borrarDatosButton) {
        borrarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarVistaBorrarDatos();
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
                ctrlDatosPresentacion.mostrarVistaInicial();
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
