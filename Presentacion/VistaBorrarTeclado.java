package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaBorrarTeclado extends VistaBase {
    /**
     * Controlador de presentación de teclados asociado a esta vista.
     */
    private CtrlTecladosPresentacion ctrlTecladosPresentacion;

    /**
     * Área de texto que muestra información sobre los teclados existentes.
     */
    private JTextArea teclados;

    /**
     * Campo de texto para ingresar el identificador del teclado.
     */
    private JTextField idTecladoField;


    /**
 * Constructor de la vista para borrar teclados.
 *
 * @param ctrlTecladosPresentacion Controlador de presentación de teclados.
 */
    public VistaBorrarTeclado(CtrlTecladosPresentacion ctrlTecladosPresentacion) {
        super(ctrlTecladosPresentacion.getCtrlPresentacion(), "Borrar Teclados");
        this.ctrlTecladosPresentacion = ctrlTecladosPresentacion;

        configurarInterfaz();
    }

/**
 * Configura los botones de la interfaz.
 *
 * @param borrarTecladoButton Botón para borrar el teclado.
 * @param backButton Botón de retorno.
 */
    public void configurarBotones(JButton borrarTecladoButton, JButton backButton) {
        configurarBotonBorrar(borrarTecladoButton);
        configurarBotonVolver(backButton);
    }


    /**
     * Configura la interfaz de la vista para gestionar teclados.
     * Se definen los elementos visuales como botones, etiquetas y campos de texto.
     */
    @Override
    public void configurarInterfaz() {
        JButton borrarTecladoButton = new JButton("Borrar Teclado");
        JLabel tecladoLabel = new JLabel("Teclados");
        JLabel idTecladoLabel = new JLabel("id Teclado:"); 
        teclados = new JTextArea();
        teclados.setLineWrap(true);
        teclados.setWrapStyleWord(true);
        teclados.setEditable(false);
        idTecladoField = new JTextField();
        JButton backButton = new JButton("Volver");

        configurarBotones(borrarTecladoButton, backButton);

        JPanel panel = new JPanel(new GridBagLayout());
    
        // Crear paneles para cada grupo de filas
        JPanel panelmostrar = new JPanel(new GridBagLayout());
        JPanel panelid = new JPanel(new GridBagLayout());
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

        int initialWidthLabel = 150;
        int initialHeightLabel = 50;

        int initialWidthField = 150;
        int initialHeightField = 35;

        int initialWidthButton = 180;
        int initialHeightButton = 50;

        borrarTecladoButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        tecladoLabel.setPreferredSize(new Dimension(initialWidthLabel, initialHeightLabel));
        idTecladoLabel.setPreferredSize(new Dimension(80, 35));
        idTecladoField.setPreferredSize(new Dimension(initialWidthField, initialHeightField));
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
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        gbc.fill = GridBagConstraints.NONE;  // Cambiar fill a NONE
        panelBoton.add(borrarTecladoButton, gbc);

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
     * Configura el botón para borrar un teclado.
     *
     * @param borrarTecladoButton Botón para borrar el teclado.
     */
    private void configurarBotonBorrar(JButton borrarTecladoButton) {
        borrarTecladoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idTecladoField.getText();
                ctrlTecladosPresentacion.borrarTeclado(id);
                limpiarCampos();
            }
        });
    }

    /**
     * Configura el botón para volver a la vista de gestión de teclado.
     *
     * @param backButton Botón de retorno.
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
     * Establece el texto en el área de texto de los teclados.
     *
     * @param text Texto a establecer.
     */
    public void setText(String text) {
        teclados.setText(text);
    }

    /**
     * Limpia los campos de la vista de gestión de teclados.
     */
    public void limpiarCampos() {
        idTecladoField.setText("");
        ctrlTecladosPresentacion.mostrarTodosTecladosBorrar();
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
