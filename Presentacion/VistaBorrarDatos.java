package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaBorrarDatos extends VistaBase {
    protected CtrlDatosPresentacion ctrlDatosPresentacion;
    //padre
    protected JButton borrarAlfButton;
    protected JButton borrarFreButton;
    protected JButton borrarTextoButton;
    //alfabeto
    protected JLabel alfabetoLabel;
    protected JLabel idAlfLabel;
    protected JTextField idalfabetoField;
    protected JTextArea alfabetos;
    //texto
    protected JLabel textoLabel;
    protected JLabel idTextoLabel; 
    protected JTextArea textos;
    protected JTextField idTextoField;
    //frecuencia
    protected JLabel frecuenciaLabel;
    protected JLabel idFreLabel;
    protected JTextField idFrecuenciaField;
    protected JTextArea frecuencias;

    protected JButton backButton;

        /**
     * Constructor de la clase VistaBorrarDatos.
     *
     * @param ctrlDatosPresentacion Controlador de presentación de datos.
     * @param titulo Título de la ventana.
     */
    public VistaBorrarDatos(CtrlDatosPresentacion ctrlDatosPresentacion, String titulo) {
        super(ctrlDatosPresentacion.getCtrlPresentacion(), titulo);
        this.ctrlDatosPresentacion = ctrlDatosPresentacion;
        
        configurarBotones();

        configurarInterfaz();
    }

    /**
     * Configura los botones y elementos visuales relacionados con el borrado de datos.
     * Inicializa los botones, etiquetas y campos de texto correspondientes al borrado de alfabeto,
     * frecuencia y texto, así como el botón de volver.
     */
    protected void configurarBotones() {
        borrarAlfButton = new JButton("Borrar Alfabeto");
        borrarFreButton = new JButton("Borrar Frecuencia");
        borrarTextoButton = new JButton("Borrar Texto");

        alfabetoLabel = new JLabel("Alfabetos");       
        idAlfLabel = new JLabel("id Alfabeto:");
        idalfabetoField = new JTextField();
        alfabetos = new JTextArea();
        alfabetos.setLineWrap(true);
        alfabetos.setWrapStyleWord(true);
        alfabetos.setEditable(false);

        textoLabel = new JLabel("Textos");
        idTextoLabel = new JLabel("id Texto:"); 
        idTextoField = new JTextField();
        textos = new JTextArea();
        textos.setLineWrap(true);
        textos.setWrapStyleWord(true);
        textos.setEditable(false);

        frecuenciaLabel = new JLabel("Listado frecuencias"); 
        idFreLabel = new JLabel("id Frecuencia:");  
        idFrecuenciaField = new JTextField();     
        frecuencias = new JTextArea();
        frecuencias.setLineWrap(true);
        frecuencias.setWrapStyleWord(true);   
        frecuencias.setEditable(false);

        backButton = new JButton("Volver");

        configurarBotonBorrarAlf();
        configurarBotonBorrarFre();
        configurarBotonBorrarTexto();
        configurarBotonVolver();
    }


        /**
     * Configura la interfaz de la vista para el borrado de datos.
     * Crea y organiza paneles para mostrar botones de borrado de alfabeto, frecuencia y texto.
     * Utiliza el componente `JPanel` para agrupar los botones y facilitar la organización visual.
     */
    @Override
    public void configurarInterfaz() {
        // Panel para agrupar botones y panel de vuelta
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente

        // Establecer el tamaño preferido inicial en función del tamaño de la ventana
        int initialWidthButton = 250;
        int initialHeightButton = 50;

        // Configuración de los botones de borrado y del botón de vuelta
        borrarAlfButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        borrarFreButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        borrarTextoButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        backButton.setPreferredSize(new Dimension(160, 35));

        // Añadir botones al panel de botones
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        buttonPanel.add(borrarAlfButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        buttonPanel.add(borrarFreButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        buttonPanel.add(borrarTextoButton, gbc);

        // Añadir botón de vuelta al panel de vuelta
        backButtonPanel.add(backButton, gbc);

        // Añadir los paneles al contenedor principal
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        frame.getContentPane().add(backButtonPanel, BorderLayout.SOUTH);

        // Centrar la ventana en la pantalla
        frame.pack();
        frame.setLocationRelativeTo(null);
    }


        /**
     * Configura la interfaz de la vista para el borrado de alfabeto.
     * Crea y organiza paneles para mostrar alfabetos, introducir el identificador del alfabeto a borrar y los botones de acción.
     * Utiliza el componente `JScrollPane` para permitir el desplazamiento de la lista de alfabetos.
     */
    protected void configurarInterfazHijos() {
        JPanel panel = new JPanel(new GridBagLayout());

        // Crear paneles para cada grupo de filas
        JPanel panelmostrar = new JPanel(new GridBagLayout());
        JPanel panelid = new JPanel(new GridBagLayout());
        JPanel panelBoton = new JPanel(new GridBagLayout());
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));

        int initialWidthArea = 450;
        int initialHeightArea = 350;

        // Scrollpane para mostrar la lista de alfabetos
        JScrollPane mostrarAlfabetos = new JScrollPane(alfabetos);
        mostrarAlfabetos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mostrarAlfabetos.setPreferredSize(new Dimension(initialWidthArea, initialHeightArea));

        // Utilizando GridBagLayout para mayor control sobre la disposición
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente

        int initialWidthButton = 220;
        int initialHeightButton = 50;

        // Configuración del botón de borrado de alfabeto
        borrarAlfButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        idalfabetoField.setPreferredSize(new Dimension(100, 35));
        alfabetoLabel.setPreferredSize(new Dimension(80, 35));
        idAlfLabel.setPreferredSize(new Dimension(100, 35));
        backButton.setPreferredSize(new Dimension(160, 35));

        // Posicionamiento de componentes en los paneles
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelmostrar.add(alfabetoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelmostrar.add(mostrarAlfabetos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelid.add(idAlfLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panelid.add(idalfabetoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        gbc.fill = GridBagConstraints.NONE;  // Cambiar fill a NONE
        panelBoton.add(borrarAlfButton, gbc);

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
     * Configura el botón para borrar alfabetos.
     * Al hacer clic en el botón, se muestra la lista de todos los alfabetos para su posible eliminación.
     * Posteriormente, se muestra la vista de borrado de alfabeto y se oculta la vista actual.
     */
    protected void configurarBotonBorrarAlf() {
        borrarAlfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarTodosAlfabetosBorrar();
                ctrlDatosPresentacion.mostrarVistaBorrarAlfabeto();
                ocultar();
            }
        });
    }

    /**
     * Configura el botón para borrar frecuencias.
     * Al hacer clic en el botón, se muestra la lista de todas las frecuencias para su posible eliminación.
     * Posteriormente, se muestra la vista de borrado de frecuencia y se oculta la vista actual.
     */
    protected void configurarBotonBorrarFre() {
        borrarFreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarTodasFrecuenciasBorrar();
                ctrlDatosPresentacion.mostrarVistaBorrarFrecuencia();
                ocultar();
            }
        });
    }

    /**
     * Configura el botón para borrar textos.
     * Al hacer clic en el botón, se muestra la lista de todos los textos para su posible eliminación.
     * Posteriormente, se muestra la vista de borrado de texto y se oculta la vista actual.
     */
    protected void configurarBotonBorrarTexto() {
        borrarTextoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarTodosTextosBorrar();
                ctrlDatosPresentacion.mostrarVistaBorrarTexto();
                ocultar();
            }
        });
    }

    /**
     * Configura el botón para volver.
     * Al hacer clic en el botón, se limpian los campos, se muestra la vista de gestión de datos y se oculta la vista actual.
     */
    protected void configurarBotonVolver() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
                ctrlDatosPresentacion.mostrarVistaGestionarDatos();
                ocultar();
            }
        });
    }

    /**
     * Establece el texto en el área de visualización de alfabetos.
     *
     * @param text Texto a establecer en el área de visualización de alfabetos.
     */
    protected void setTextAlf(String text) {
        alfabetos.setText(text);
    }

    /**
     * Establece el texto en el área de visualización de textos.
     *
     * @param text Texto a establecer en el área de visualización de textos.
     */
    protected void setTextTexto(String text) {
        textos.setText(text);
    }

    /**
     * Establece el texto en el área de visualización de frecuencias.
     *
     * @param text Texto a establecer en el área de visualización de frecuencias.
     */
    protected void setTextFre(String text) {
        frecuencias.setText(text);
    }

    /**
     * Limpia los campos de entrada y muestra las listas de alfabetos, textos y frecuencias para su posible eliminación.
     */
    protected void limpiarCampos() {
        idalfabetoField.setText("");
        idTextoField.setText("");
        idFrecuenciaField.setText("");
        ctrlDatosPresentacion.mostrarTodosAlfabetosBorrar();
        ctrlDatosPresentacion.mostrarTodosTextosBorrar();
        ctrlDatosPresentacion.mostrarTodasFrecuenciasBorrar();
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
