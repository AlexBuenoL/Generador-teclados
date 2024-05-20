package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa la interfaz de usuario para modificar frecuencias. Hereda de la clase VistaModificarDatos.
 */
public class VistaModificarFre extends VistaModificarDatos {

    /**
     * Constructor de la clase VistaModificarFre.
     * @param ctrlDatosPresentacion Controlador de la presentación de datos.
     */
    public VistaModificarFre(CtrlDatosPresentacion ctrlDatosPresentacion) {
        super(ctrlDatosPresentacion, "Modificar Frecuencia");
        this.ctrlDatosPresentacion = ctrlDatosPresentacion; 

        configurarInterfazHijos();
    }

    /**
     * Configura la interfaz principal.
     */
    @Override
    public void configurarInterfaz() {
        
    }

    /**
     * Configura la interfaz específica para la modificación de frecuencias.
     */
    @Override
    public void configurarInterfazHijos() {
        añadir.setText("Añadir frecuencias");
        quitar.setText("Quitar frecuencias");
        
        JPanel panel = new JPanel(new GridBagLayout());
    
        // Crear paneles para cada grupo de filas
        JPanel panelmostrar = new JPanel(new GridBagLayout());
        JPanel panelid = new JPanel(new GridBagLayout());
        JPanel panelOptions = new JPanel(new GridBagLayout());
        JPanel panelFre = new JPanel(new GridBagLayout());
        JPanel panelBoton = new JPanel(new GridBagLayout());
        JPanel panelInferior = new JPanel(new BorderLayout());  

        int initialWidthArea = 280; //ancho
        int initialHeightArea = 320; //alto
        //scrollpane frecuencias
        JScrollPane mostrarFrecuencias = new JScrollPane(frecuencias);
        mostrarFrecuencias.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mostrarFrecuencias.setPreferredSize(new Dimension(initialWidthArea, initialHeightArea));
        //scrollpane new frec
        JScrollPane newFrec = new JScrollPane(newfrecuenciaArea);
        newFrec.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        newFrec.setPreferredSize(new Dimension(initialWidthArea, initialHeightArea));

        // Utilizando GridBagLayout para mayor control sobre la disposición
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente

        //int initialWidthLabel = 150;
        int initialHeightLabel = 50;

        int initialWidthButton = 220;
        int initialHeightButton = 50;

        modFrecuenciasButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        frecuenciaLabel.setPreferredSize(new Dimension(150, 35));
        newFrecLabel.setPreferredSize(new Dimension(150, 35));
        idFreLabel.setPreferredSize(new Dimension(120, 35));
        idFrecuenciaField.setPreferredSize(new Dimension(100, 35));
        tecladoButton.setPreferredSize(new Dimension(160,35));
        tecladoLabel.setPreferredSize(new Dimension(165, initialHeightLabel));
        backButton.setPreferredSize(new Dimension(160, 35));

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
        panelOptions.add(añadir, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelOptions.add(quitar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelFre.add(newFrecLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panelFre.add(newFrec, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        gbc.fill = GridBagConstraints.NONE;  // Cambiar fill a NONE
        panelBoton.add(modFrecuenciasButton, gbc);

        // Configurar el panel de botones inferior
        JPanel panelBackButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        panelBackButton.add(backButton);

        JPanel panelTecladoButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        panelTecladoButton.add(tecladoLabel);
        panelTecladoButton.add(tecladoButton);

        panelInferior.add(panelBackButton, BorderLayout.WEST);
        panelInferior.add(panelTecladoButton, BorderLayout.EAST);

        // Agregar paneles al panel principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(panelmostrar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(panelid, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(panelOptions, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(panelFre, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(panelBoton, gbc);

        // Añadir los paneles al contenedor principal
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(panelInferior, BorderLayout.SOUTH); 

        frame.pack();
        frame.setLocationRelativeTo(null);

        ocultar();
    }

    /**
     * Configura la acción a realizar cuando se presiona el botón de modificar frecuencia.
     */
    @Override
    protected void configurarBotonModificarFre() {
        modFrecuenciasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idFrecuenciaField.getText();
                int opc=0;
                if(añadir.isSelected()) opc = 1;
                else if(quitar.isSelected()) opc = 2;
                else opc = 3;
                String newFrec = newfrecuenciaArea.getText();
                ctrlDatosPresentacion.modificarFrecuencia(id, newFrec, opc);
                limpiarCampos();
            }
        });
    }

    /**
     * Configura la acción a realizar cuando se presiona el botón de volver.
     */
    @Override
    protected void configurarBotonVolver() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
                ctrlDatosPresentacion.mostrarVistaModificarDatos();
                ocultar();
            }
        });
    }

    /**
     * Establece el texto en el área de texto de frecuencias.
     * @param text Texto a establecer en el área de texto de frecuencias.
     */
    public void setText(String text) {
        frecuencias.setText(text);
    }

    /**
     * Muestra la interfaz de la vista de modificación de frecuencias.
     */
    @Override   
    public void mostrar() {
        frame.setVisible(true);
    }

    /**
     * Oculta la interfaz de la vista de modificación de frecuencias.
     */
    @Override
    public void ocultar() {
        frame.setVisible(false);
    }
}
