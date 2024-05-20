package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Vista encargada de la modificación de teclados, permitiendo al usuario intercambiar
 * las teclas de un teclado específico. Extiende la clase VistaBase e implementa la interfaz
 * ActionListener para manejar eventos de botones.
 */

public class VistaModificarTeclado extends VistaBase {
    private CtrlTecladosPresentacion ctrlTecladosPresentacion;
    private JTextArea teclados;
    private JTextField idTecladoField;
    private JTextField letra1Field;
    private JTextField letra2Field;

    /**
     * Constructor de la vista de modificación de teclados.
     *
     * @param ctrlTecladosPresentacion Controlador de presentación de teclados.
     */

    public VistaModificarTeclado(CtrlTecladosPresentacion ctrlTecladosPresentacion) {
        super(ctrlTecladosPresentacion.getCtrlPresentacion(), "Modificar Teclados");
        this.ctrlTecladosPresentacion = ctrlTecladosPresentacion;

        configurarInterfaz();
    }

    /**
     * Configura los botones de la interfaz, especificando sus acciones.
     *
     * @param modificarTecladoButton Botón para modificar el teclado.
     * @param backButton             Botón para volver a la vista de gestión de teclados.
     */
    public void configurarBotones(JButton modificarTecladoButton, JButton backButton) {
        configurarBotonModificar(modificarTecladoButton);
        configurarBotonVolver(backButton);
    }

    /**
     * Configura la interfaz específica para la vista de modificar teclado.
     */
    @Override
    public void configurarInterfaz() {
        JButton modificarTecladoButton = new JButton("Modificar Teclado");
        JLabel tecladoLabel = new JLabel("Teclados");
        JLabel idTecladoLabel = new JLabel("id Teclado:"); 
        teclados = new JTextArea();
        teclados.setLineWrap(true);
        teclados.setWrapStyleWord(true);
        teclados.setEditable(false);
        idTecladoField = new JTextField();
        JLabel letrasLabel = new JLabel("Teclas a intercambiar");
        JLabel letra1Label = new JLabel("Tecla 1");
        JLabel letra2Label = new JLabel("Tecla 2");
        letra1Field = new JTextField();
        letra2Field = new JTextField();
        JButton backButton = new JButton("Volver");

        configurarBotones(modificarTecladoButton, backButton);

        JPanel panel = new JPanel(new GridBagLayout());
    
        // Crear paneles para cada grupo de filas
        JPanel panelmostrar = new JPanel(new GridBagLayout());
        JPanel panelid = new JPanel(new GridBagLayout());
        JPanel panelLetras = new JPanel(new GridBagLayout());
        JPanel panelBoton = new JPanel(new GridBagLayout());
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));

        int initialWidthArea = 400; //ancho
        int initialHeightArea = 300; //alto
        //scrollpane teclados
        JScrollPane mostrarTeclados = new JScrollPane(teclados);
        mostrarTeclados.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mostrarTeclados.setPreferredSize(new Dimension(initialWidthArea, initialHeightArea));

        // Utilizando GridBagLayout para mayor control sobre la disposición
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente

        int initialWidthLabel = 200;
        int initialHeightLabel = 35;

        int initialWidthField = 150;
        int initialHeightField = 35;

        int initialWidthButton = 180;
        int initialHeightButton = 50;

        modificarTecladoButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        tecladoLabel.setPreferredSize(new Dimension(initialWidthLabel, initialHeightLabel));
        letrasLabel.setPreferredSize(new Dimension(initialWidthLabel, initialHeightLabel));
        idTecladoLabel.setPreferredSize(new Dimension(80, 35));
        idTecladoField.setPreferredSize(new Dimension(initialWidthField, initialHeightField));
        letra1Label.setPreferredSize(new Dimension(80, initialHeightLabel));
        letra2Label.setPreferredSize(new Dimension(80, initialHeightLabel));
        letra1Field.setPreferredSize(new Dimension(80, 35));
        letra2Field.setPreferredSize(new Dimension(80, 35));
        backButton.setPreferredSize(new Dimension(160, 35));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelmostrar.add(tecladoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelmostrar.add(mostrarTeclados, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelid.add(idTecladoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panelid.add(idTecladoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelLetras.add(letrasLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelLetras.add(letra1Label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panelLetras.add(letra1Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panelLetras.add(letra2Label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        panelLetras.add(letra2Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        gbc.fill = GridBagConstraints.NONE;  // Cambiar fill a NONE
        panelBoton.add(modificarTecladoButton, gbc);

        backButtonPanel.add(backButton, gbc);

        // Agregar paneles al panel principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(panelmostrar, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(panelid, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(panelLetras, gbc);

        gbc.gridx = 1;
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
     * Configura el botón para modificar el teclado, asignándole una acción al hacer clic.
     *
     * @param modificarTecladoButton Botón para modificar el teclado.
     */
    private void configurarBotonModificar(JButton modificarTecladoButton) {
        modificarTecladoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idTecladoField.getText();
                String x = letra1Field.getText();
                String y = letra2Field.getText();
                ctrlTecladosPresentacion.modificarTeclado(id, x, y);
                limpiarCampos();
            }
        });
    }

    /**
     * Configura el botón para volver a la vista de gestión de teclados, asignándole una acción al hacer clic.
     *
     * @param backButton Botón para volver.
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
     * Establece el texto en el área de texto para mostrar los teclados.
     *
     * @param text Texto a mostrar.
     */
    public void setText(String text) {
        teclados.setText(text);
    }

    /**
     * Limpia los campos de entrada y actualiza la lista de teclados mostrados.
     */
    public void limpiarCampos() {
        idTecladoField.setText("");
        letra1Field.setText("");
        letra2Field.setText("");
        ctrlTecladosPresentacion.mostrarTodosTecladosModifcar();
    }

    /**
     * Muestra la ventana de la vista.
     */
    @Override
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
