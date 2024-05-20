package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaBorrarTexto extends VistaBorrarDatos {

        /**
     * Crea una instancia de la vista para borrar textos.
     * @param ctrlDatosPresentacion Controlador de presentación de datos.
     */
    public VistaBorrarTexto(CtrlDatosPresentacion ctrlDatosPresentacion) {
        super(ctrlDatosPresentacion, "Borrar Texto");
        this.ctrlDatosPresentacion = ctrlDatosPresentacion;  

        // Configura la interfaz específica para la operación de borrar textos
        configurarInterfazHijos();
    }

    /**
     * Configura la interfaz principal.
     * (No se implementa en esta vista específica, ya que se utiliza la configuración
     * específica de la operación de borrar textos).
     */
    @Override
    public void configurarInterfaz() {
        // El cuerpo del método está vacío ya que se utiliza la configuración específica
        // de la operación de borrar textos en el método configurarInterfazHijos().
    }


    /**
     * Configura la interfaz para la operación de borrar textos.
     */
    @Override
    public void configurarInterfazHijos() {
        JPanel panel = new JPanel(new GridBagLayout());
    
        // Crear paneles para cada grupo de filas
        JPanel panelmostrar = new JPanel(new GridBagLayout());
        JPanel panelid = new JPanel(new GridBagLayout());
        JPanel panelBoton = new JPanel(new GridBagLayout());
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));

        int initialWidthArea = 950; //ancho
        int initialHeightArea = 400; //alto
        //scrollpane textos
        JScrollPane mostrarTextos = new JScrollPane(textos);
        mostrarTextos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mostrarTextos.setPreferredSize(new Dimension(initialWidthArea, initialHeightArea));

        // Utilizando GridBagLayout para mayor control sobre la disposición
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente

        int initialWidthButton = 180;
        int initialHeightButton = 50;

        borrarTextoButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        textoLabel.setPreferredSize(new Dimension(150, 35));
        idTextoLabel.setPreferredSize(new Dimension(80, 35));
        idTextoField.setPreferredSize(new Dimension(100, 35));
        backButton.setPreferredSize(new Dimension(160, 35));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelmostrar.add(textoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelmostrar.add(mostrarTextos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelid.add(idTextoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panelid.add(idTextoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        gbc.fill = GridBagConstraints.NONE;  // Cambiar fill a NONE
        panelBoton.add(borrarTextoButton, gbc);

        backButtonPanel.add(backButton, gbc);

        // Agregar paneles al panel principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(panelmostrar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(panelid, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(panelBoton, gbc);

        // Añadir los paneles al contenedor principal
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(backButtonPanel, BorderLayout.SOUTH); 

        frame.pack();
        frame.setLocationRelativeTo(null);

        ocultar();
    }

    /**
 * Configura el botón para borrar texto.
 */
    @Override
    protected void configurarBotonBorrarTexto() {
        borrarTextoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idTextoField.getText();
                ctrlDatosPresentacion.borrarTexto(id);
                limpiarCampos();
            }
        });
    }

/**
 * Configura el botón para volver.
 */
    @Override
    protected void configurarBotonVolver() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
                ctrlDatosPresentacion.mostrarVistaBorrarDatos();
                ocultar();
            }
        });
    }

/**
 * Muestra la ventana.
 */
    @Override
    public void mostrar() {
        frame.setVisible(true);
    }

/**
 * Oculta la ventana.
 */
    @Override
    public void ocultar() {
        frame.setVisible(false);
    }

    
}
