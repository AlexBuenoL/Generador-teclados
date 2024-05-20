package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa la vista para borrar una frecuencia en la interfaz de usuario.
 * Extiende de VistaBorrarDatos y proporciona la interfaz gráfica para eliminar una frecuencia.
 */
public class VistaBorrarFrecuencia extends VistaBorrarDatos {

    /**
     * Constructor de la clase VistaBorrarFrecuencia.
     *
     * @param ctrlDatosPresentacion Controlador de datos de presentación asociado a la vista.
     */
    public VistaBorrarFrecuencia(CtrlDatosPresentacion ctrlDatosPresentacion) {
        super(ctrlDatosPresentacion, "Borrar Frecuencia");
        this.ctrlDatosPresentacion = ctrlDatosPresentacion;  
        configurarInterfazHijos();
    }

    /**
     * Configura la interfaz gráfica de la vista.
     * Este método debe ser implementado por las subclases.
     */
    @Override
    public void configurarInterfaz() {
        // Implementación específica para la configuración de la interfaz, si es necesario.
    }

    /**
     * Configura la interfaz gráfica específica para la vista hija.
     */
    @Override
    public void configurarInterfazHijos() {
        JPanel panel = new JPanel(new GridBagLayout());

        // Crear paneles para cada grupo de filas
        JPanel panelmostrar = new JPanel(new GridBagLayout());
        JPanel panelid = new JPanel(new GridBagLayout());
        JPanel panelBoton = new JPanel(new GridBagLayout());
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));

        // Configuración del JScrollPane para mostrar las frecuencias
        int initialWidthArea = 400;
        int initialHeightArea = 400;
        JScrollPane mostrarFrecuencias = new JScrollPane(frecuencias);
        mostrarFrecuencias.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mostrarFrecuencias.setPreferredSize(new Dimension(initialWidthArea, initialHeightArea));

        // Utilizando GridBagLayout para mayor control sobre la disposición
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente

        // Tamaño inicial de los botones
        int initialWidthButton = 220;
        int initialHeightButton = 50;

        borrarFreButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        frecuenciaLabel.setPreferredSize(new Dimension(150, 35));
        idFreLabel.setPreferredSize(new Dimension(120, 35));
        idFrecuenciaField.setPreferredSize(new Dimension(100, 35));
        backButton.setPreferredSize(new Dimension(160, 35));

        // Configuración de la disposición de los componentes en los paneles
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelmostrar.add(frecuenciaLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelmostrar.add(mostrarFrecuencias, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelid.add(idFreLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panelid.add(idFrecuenciaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        gbc.fill = GridBagConstraints.NONE;  // Cambiar fill a NONE
        panelBoton.add(borrarFreButton, gbc);

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
     * Configura el botón de borrar frecuencia con su respectivo ActionListener.
     * Este método se llama desde la configuración de la interfaz hija.
     */
    @Override
    protected void configurarBotonBorrarFre() {
        borrarFreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idFrecuenciaField.getText();
                ctrlDatosPresentacion.borrarFrecuencia(id);
                limpiarCampos();
            }
        });
    }

    /**
     * Configura el botón de volver con su respectivo ActionListener.
     * Este método se llama desde la configuración de la interfaz hija.
     */
    @Override
    protected void configurarBotonVolver() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para volver a VistaUsuario
                limpiarCampos();
                ctrlDatosPresentacion.mostrarVistaBorrarDatos();
                ocultar();
            }
        });
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
