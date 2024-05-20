package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que representa la interfaz de usuario para modificar datos, como alfabetos, frecuencias, textos y teclados.
 * Hereda de la clase VistaBase.
 */
public class VistaModificarDatos extends VistaBase {
    protected CtrlDatosPresentacion ctrlDatosPresentacion;   
    //alfabeto  
    protected JButton modAlfabetoButton;
    protected JLabel alfabetoLabel;
    protected JLabel letrasLabel;
    protected JLabel idAlfLabel;
    protected JTextField idalfabetoField;
    protected JTextField newAlfabetoField;
    protected JTextArea alfabetos;
    protected JRadioButton añadir;
    protected JRadioButton quitar;
    protected ButtonGroup Group;
    //frecuencia
    protected JButton modFrecuenciasButton;
    protected JLabel frecuenciaLabel;
    protected JLabel newFrecLabel;
    protected JLabel idFreLabel;
    protected JTextField idFrecuenciaField;
    protected JTextArea newfrecuenciaArea;
    protected JTextArea frecuencias;
    //texto
    protected JButton modTextoButton;
    protected JLabel textoLabel;
    protected JLabel newTextoLabel;
    protected JLabel idTextoLabel; 
    protected JTextArea newtextoArea;
    protected JTextArea textos;
    protected JTextField idTextoField;
    //teclado
    protected JButton tecladoButton;
    protected JLabel tecladoLabel;

    protected JButton backButton;

    /**
     * Constructor de la clase VistaModificarDatos.
     * @param ctrlDatosPresentacion Controlador de la presentación de datos.
     * @param titulo                Título de la ventana.
     */
    public VistaModificarDatos(CtrlDatosPresentacion ctrlDatosPresentacion, String titulo) {
        super(ctrlDatosPresentacion.getCtrlPresentacion(), titulo);
        this.ctrlDatosPresentacion = ctrlDatosPresentacion;

        configurarBotones();

        configurarInterfaz();      
    }

    /**
     * Configura los botones y otros elementos de la interfaz.
     */
    protected void configurarBotones() {
        modAlfabetoButton = new JButton("Modificar Alfabeto");   
        alfabetoLabel = new JLabel("Alfabetos"); 
        letrasLabel = new JLabel("Letras:");        
        idAlfLabel = new JLabel("id Alfabeto:");
        newAlfabetoField = new JTextField();
        idalfabetoField = new JTextField();
        alfabetos = new JTextArea();
        alfabetos.setLineWrap(true);
        alfabetos.setWrapStyleWord(true);
        alfabetos.setEditable(false);
        añadir = new JRadioButton("Añadir letras");
        quitar = new JRadioButton("Quitar letras");
        // Creamos un ButtonGroup y agregamos los JRadioButton
        Group = new ButtonGroup();
        Group.add(añadir);
        Group.add(quitar);
        
        modFrecuenciasButton = new JButton("Modificar Frecuencia"); 
        frecuenciaLabel = new JLabel("Listado frecuencias"); 
        newFrecLabel = new JLabel("Frecuencas modificadas:");
        idFreLabel = new JLabel("id Frecuencia:");  
        idFrecuenciaField = new JTextField();   
        newfrecuenciaArea = new JTextArea();
        newfrecuenciaArea.setLineWrap(true);  // Permitir el salto de línea automático
        newfrecuenciaArea.setWrapStyleWord(true);  
        frecuencias = new JTextArea();
        frecuencias.setLineWrap(true);
        frecuencias.setWrapStyleWord(true);   
        frecuencias.setEditable(false);

        modTextoButton = new JButton("Modificar Texto");
        textoLabel = new JLabel("Textos");
        newTextoLabel = new JLabel("Texto modificado:");
        idTextoLabel = new JLabel("id Texto:"); 
        idTextoField = new JTextField();
        newtextoArea = new JTextArea();
        newtextoArea.setLineWrap(true);
        newtextoArea.setWrapStyleWord(true);
        textos = new JTextArea();
        textos.setLineWrap(true);
        textos.setWrapStyleWord(true);
        textos.setEditable(false);

        tecladoButton = new JButton("Ir");
        tecladoLabel = new JLabel("Ir a generar el Teclado");

        backButton = new JButton("Volver");

        configurarBotonModificarAlf();
        configurarBotonModificarFre();
        configurarBotonModificarTexto();
        configurarBotonVolver();
    }

    /**
     * Configura la interfaz principal.
     */
    @Override
    public void configurarInterfaz() {
        // Panel para agrupar
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente

        // Establecer el tamaño preferido inicial en función del tamaño de la ventana
        int initialWidthButton = 250;
        int initialHeightButton = 50;

        modAlfabetoButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        modFrecuenciasButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        modTextoButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        backButton.setPreferredSize(new Dimension(160, 35));

        // mod Alf button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        buttonPanel.add(modAlfabetoButton, gbc);

        // mod frec button
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        buttonPanel.add(modFrecuenciasButton, gbc);

        // mod texto button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        buttonPanel.add(modTextoButton, gbc);

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
     * Configura la interfaz para la modificación de datos específicos, como alfabetos, frecuencias, textos y teclados.
     */
    protected void configurarInterfazHijos() {
        JPanel panel = new JPanel(new GridBagLayout());
    
        // Crear paneles para cada grupo de filas
        JPanel panelmostrar = new JPanel(new GridBagLayout());
        JPanel panelidOptions = new JPanel(new GridBagLayout());
        JPanel panelalf = new JPanel(new GridBagLayout());
        JPanel panelBoton = new JPanel(new GridBagLayout());
        JPanel panelInferior = new JPanel(new BorderLayout());  

        int initialWidthArea = 450;
        int initialHeightArea = 300;
        //scrollpane alfabetos
        JScrollPane mostrarAlfabetos = new JScrollPane(alfabetos);
        mostrarAlfabetos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mostrarAlfabetos.setPreferredSize(new Dimension(initialWidthArea, initialHeightArea));

        // Utilizando GridBagLayout para mayor control sobre la disposición
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente

        //int initialWidthLabel = 150;
        int initialHeightLabel = 50;

        int initialWidthField = 350;
        int initialHeightField = 35;

        int initialWidthButton = 220;
        int initialHeightButton = 50;

        modAlfabetoButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        newAlfabetoField.setPreferredSize(new Dimension(initialWidthField, initialHeightField));
        idalfabetoField.setPreferredSize(new Dimension(35, 35));
        alfabetoLabel.setPreferredSize(new Dimension(80, 35));
        letrasLabel.setPreferredSize(new Dimension(80, 35));
        idAlfLabel.setPreferredSize(new Dimension(100, 35));
        tecladoButton.setPreferredSize(new Dimension(160,35));
        tecladoLabel.setPreferredSize(new Dimension(165, initialHeightLabel));
        backButton.setPreferredSize(new Dimension(160, 35));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelmostrar.add(alfabetoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelmostrar.add(mostrarAlfabetos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelidOptions.add(idAlfLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panelidOptions.add(idalfabetoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelidOptions.add(añadir, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panelidOptions.add(quitar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelalf.add(letrasLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panelalf.add(newAlfabetoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        gbc.fill = GridBagConstraints.NONE;  // Cambiar fill a NONE
        panelBoton.add(modAlfabetoButton, gbc);

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
        panel.add(panelidOptions, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(panelalf, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
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
     * Configura la acción a realizar cuando se presiona el botón de modificar texto.
     */
    protected void configurarBotonModificarTexto() {
        modTextoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarTodosTextosModificar();
                ctrlDatosPresentacion.mostrarVistaModificarTexto();
                ocultar();
            }
        });
    }

    /**
     * Configura la acción a realizar cuando se presiona el botón de modificar alfabeto.
     */
    protected void configurarBotonModificarAlf() {
        modAlfabetoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarTodosAlfabetosModificar();
                ctrlDatosPresentacion.mostrarVistaModificarAlf();
                ocultar();
            }
        });
    }

    /**
     * Configura la acción a realizar cuando se presiona el botón de modificar frecuencia.
     */
    protected void configurarBotonModificarFre() {
        modFrecuenciasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarTodasFrecuenciasModificar();
                ctrlDatosPresentacion.mostrarVistaModificarFre();
                ocultar();
            }
        });
    }

    /**
     * Configura la acción a realizar cuando se presiona el botón de volver.
     */
    protected void configurarBotonVolver() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para volver a VistaUsuario
                limpiarCampos();
                ctrlDatosPresentacion.mostrarVistaGestionarDatos();
                ocultar();
            }
        });
    }

    /**
     * Limpia los campos de entrada.
     */
    protected void limpiarCampos() {
        idalfabetoField.setText("");
        newAlfabetoField.setText("");
        idFrecuenciaField.setText("");
        newfrecuenciaArea.setText("");
        idTextoField.setText("");
        newtextoArea.setText("");
        ctrlDatosPresentacion.mostrarTodosAlfabetosModificar();
        ctrlDatosPresentacion.mostrarTodasFrecuenciasModificar();
        ctrlDatosPresentacion.mostrarTodosTextosModificar();
        Group.clearSelection();
    }

    /**
     * Muestra la interfaz de la vista de modificación de datos.
     */
    @Override
    public void mostrar() {
        frame.setVisible(true);
    }

    /**
     * Oculta la interfaz de la vista de modificación de datos.
     */
    @Override
    public void ocultar() {
        frame.setVisible(false);
    }
    
}
