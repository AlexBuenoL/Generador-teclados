package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase representa la interfaz gráfica para la modificación de textos,
 * permitiendo al usuario editar un texto existente o añadir uno nuevo.
 * Extiende la clase VistaModificarDatos y proporciona funcionalidades específicas
 * para la gestión de textos. Implementa la interfaz ActionListener para manejar
 * eventos de botones.
 */

public class VistaModificarTexto extends VistaModificarDatos {

    /**
     * Constructor de la vista de modificación de textos.
     *
     * @param ctrlDatosPresentacion Controlador de presentación de datos.
     */
    public VistaModificarTexto(CtrlDatosPresentacion ctrlDatosPresentacion) {
        super(ctrlDatosPresentacion, "Modificar Texto");
        this.ctrlDatosPresentacion = ctrlDatosPresentacion; 
        
        configurarInterfazHijos();  
    }

    /**
     * Configura la interfaz de la vista de modificación de textos,
     * estableciendo la disposición de los componentes gráficos.
     */
    @Override
    public void configurarInterfaz() {
        
    }

    /**
     * Configura la interfaz de la vista de modificación de textos
     * de los hijos de modificar texto
     */
    @Override
    public void configurarInterfazHijos() {
        JPanel panel = new JPanel(new GridBagLayout());
    
        // Crear paneles para cada grupo de filas
        JPanel panelmostrar = new JPanel(new GridBagLayout());
        JPanel panelid = new JPanel(new GridBagLayout());
        JPanel panelText = new JPanel(new GridBagLayout());
        JPanel panelBoton = new JPanel(new GridBagLayout());
        JPanel panelInferior = new JPanel(new BorderLayout());  

        int initialWidthArea = 400; //ancho
        int initialHeightArea = 300; //alto
        //scrollpane textos
        JScrollPane mostrarTextos = new JScrollPane(textos);
        mostrarTextos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mostrarTextos.setPreferredSize(new Dimension(initialWidthArea, initialHeightArea));
        //scrollpane new texto
        JScrollPane newText = new JScrollPane(newtextoArea);
        newText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        newText.setPreferredSize(new Dimension(initialWidthArea, initialHeightArea));

        // Utilizando GridBagLayout para mayor control sobre la disposición
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente

        int initialWidthLabel = 150;
        int initialHeightLabel = 50;

        int initialWidthButton = 150;
        int initialHeightButton = 50;

        modTextoButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        textoLabel.setPreferredSize(new Dimension(150, 35));
        newTextoLabel.setPreferredSize(new Dimension(150, 35));
        idTextoLabel.setPreferredSize(new Dimension(80, 35));
        idTextoField.setPreferredSize(new Dimension(100, 35));
        tecladoButton.setPreferredSize(new Dimension(160,35));
        tecladoLabel.setPreferredSize(new Dimension(165, initialHeightLabel));
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
        panelText.add(newTextoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelText.add(newText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        gbc.fill = GridBagConstraints.NONE;  // Cambiar fill a NONE
        panelBoton.add(modTextoButton, gbc);

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

        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(panelText, gbc);

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
     * Configura el botón para modificar un texto existente o agregar uno nuevo,
     * asignándole una acción al hacer clic.
     */
    @Override
    protected void configurarBotonModificarTexto() {
        modTextoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idTextoField.getText();
                String newText = newtextoArea.getText();
                ctrlDatosPresentacion.modificarTexto(id, newText);
                limpiarCampos();
            }
        });
    }

    /**
     * Configura el botón para volver a la vista de modificación de datos,
     * asignándole una acción al hacer clic.
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
     * Establece el texto en el área de texto para mostrar los textos.
     *
     * @param text Texto a mostrar.
     */
    public void setText(String text) {
        textos.setText(text);
    }

    /**
     * Muestra la ventana de la vista.
     */
    public void mostrar() {
        frame.setVisible(true);
    }

     /**
     * Oculta la ventana de la vista.
     */
    @Override
    public void ocultar() {
        frame.setVisible(false);
    }
    
}
