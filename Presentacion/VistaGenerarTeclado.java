package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Vista para la generación de teclados.
 */
public class VistaGenerarTeclado extends VistaBase {

    private CtrlTecladosPresentacion ctrlTecladosPresentacion;
    private JTextArea alfabetos;
    private JTextArea frecuencias;

    /**
     * Constructor de la clase VistaGenerarTeclado.
     *
     * @param ctrlTecladosPresentacion Controlador de presentación de teclados.
     */
    public VistaGenerarTeclado(CtrlTecladosPresentacion ctrlTecladosPresentacion) {
        super(ctrlTecladosPresentacion.getCtrlPresentacion(), "Generar Teclados");
        this.ctrlTecladosPresentacion = ctrlTecladosPresentacion;
        frame.setResizable(true);
        configurarInterfaz();
    }

    /**
     * Configura la interfaz de usuario para la generación de teclados.
     */
    @Override
    public void configurarInterfaz() {
        //teclado
        JButton generarTecladoButton = new JButton("Generar Teclado");
        JLabel idTecladoLabel = new JLabel("id teclado:");
        JTextField idTecladoField = new JTextField();
        JRadioButton qapButton = new JRadioButton("QAP");
        JRadioButton simButton = new JRadioButton("SA");
        JRadioButton bothButton = new JRadioButton("Los dos");
        ButtonGroup Group = new ButtonGroup();
        Group.add(qapButton);
        Group.add(simButton);
        Group.add(bothButton);
        JLabel algoritmoLabel = new JLabel("Algoritmo");
        JLabel esperLabel = new JLabel("<html>La generación puede tardar hasta varios minutos especialmente con 10 letras.</html>");
        esperLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Añadir un borde para visualización
        //alfabeto
        JButton añadirAlf = new JButton();
        JLabel alfabetoLabel = new JLabel("Alfabetos");       
        JLabel idAlfLabel = new JLabel("id Alfabeto:");
        JTextField idalfabetoField = new JTextField();
        alfabetos = new JTextArea();
        alfabetos.setLineWrap(true);
        alfabetos.setWrapStyleWord(true);
        alfabetos.setEditable(false);
        //frecuencia
        JButton añadirFre = new JButton();
        JLabel frecuenciaLabel = new JLabel("Listado frecuencias"); 
        JLabel idFreLabel = new JLabel("id Frecuencia:");  
        JTextField idFrecuenciaField = new JTextField();     
        frecuencias = new JTextArea();
        frecuencias.setLineWrap(true);
        frecuencias.setWrapStyleWord(true);   
        frecuencias.setEditable(false);

        ImageIcon plusIcon = new ImageIcon("icons/suma.jpg");
        Image plusImage = plusIcon.getImage().getScaledInstance(25, 20, Image.SCALE_SMOOTH);
        añadirAlf.setIcon(new ImageIcon(plusImage));
        añadirFre.setIcon(new ImageIcon(plusImage));

        JButton backButton = new JButton("Volver");

        configurarBotones(generarTecladoButton, añadirAlf, añadirFre, idTecladoField, idalfabetoField, idFrecuenciaField, Group, qapButton, simButton, bothButton, backButton);

        JPanel panel = new JPanel(new GridBagLayout());
    
        // Crear paneles para cada grupo de filas
        JPanel panelidTeclado = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        JPanel panelmostrarAlf = new JPanel(new GridBagLayout());
        JPanel panelmostrarFre = new JPanel(new GridBagLayout());
        JPanel panelidAlf = new JPanel(new GridBagLayout());
        JPanel panelidFre = new JPanel(new GridBagLayout());
        JPanel panelOpciones = new JPanel(new GridBagLayout());
        JPanel panelBoton = new JPanel(new GridBagLayout());
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));

        int initialWidthArea = 350;
        int initialHeightArea = 250;
        //scrollpane alfabetos
        JScrollPane mostrarAlfabetos = new JScrollPane(alfabetos);
        mostrarAlfabetos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mostrarAlfabetos.setPreferredSize(new Dimension(initialWidthArea, initialHeightArea));
        //scrollpane frecuencias
        JScrollPane mostrarFrecuencias = new JScrollPane(frecuencias);
        mostrarFrecuencias.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mostrarFrecuencias.setPreferredSize(new Dimension(initialWidthArea, initialHeightArea));

        // Utilizando GridBagLayout para mayor control sobre la disposición
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente

        int initialWidthField = 150;
        int initialHeightField = 35;

        int initialWidthButton = 220;
        int initialHeightButton = 50;

        generarTecladoButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        idTecladoLabel.setPreferredSize(new Dimension(80, 35));
        algoritmoLabel.setPreferredSize(new Dimension(80, 35));
        idTecladoField.setPreferredSize(new Dimension(initialWidthField, initialHeightField));
        idalfabetoField.setPreferredSize(new Dimension(initialWidthField, initialHeightField));
        alfabetoLabel.setPreferredSize(new Dimension(80, 35));
        idAlfLabel.setPreferredSize(new Dimension(100, 35));
        backButton.setPreferredSize(new Dimension(160, 35));
        frecuenciaLabel.setPreferredSize(new Dimension(150, 35));
        idFreLabel.setPreferredSize(new Dimension(120, 35));
        idFrecuenciaField.setPreferredSize(new Dimension(initialWidthField, initialHeightField));
        backButton.setPreferredSize(new Dimension(160, 35));
        añadirAlf.setPreferredSize(new Dimension(25, 20));
        añadirFre.setPreferredSize(new Dimension(25, 20));
        esperLabel.setPreferredSize(new Dimension(330, 50));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelmostrarAlf.add(alfabetoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelmostrarAlf.add(mostrarAlfabetos, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 0, 0, 60); 
        panelmostrarAlf.add(añadirAlf, gbc);
        // Restaura los insets a su valor predeterminado
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelmostrarFre.add(frecuenciaLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelmostrarFre.add(mostrarFrecuencias, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        panelmostrarFre.add(añadirFre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelidAlf.add(idAlfLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelidAlf.add(idalfabetoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelidFre.add(idFreLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelidFre.add(idFrecuenciaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelOpciones.add(algoritmoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelOpciones.add(qapButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panelOpciones.add(simButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        panelOpciones.add(bothButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        gbc.fill = GridBagConstraints.NONE;  // Cambiar fill a NONE
        panelBoton.add(generarTecladoButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelBoton.add(esperLabel, gbc);

        backButtonPanel.add(backButton, gbc);
        panelidTeclado.add(idTecladoLabel, gbc);
        panelidTeclado.add(idTecladoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(panelmostrarAlf, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(panelmostrarFre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(panelidAlf, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(panelidFre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(panelOpciones, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(panelBoton, gbc);

        // Añadir los paneles al contenedor principal
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(panelidTeclado, BorderLayout.NORTH); 
        frame.getContentPane().add(backButtonPanel, BorderLayout.SOUTH); 

        frame.pack();
        frame.setLocationRelativeTo(null);

        ocultar();
    }

    /**
     * Configura los botones de la interfaz.
     *
     * @param generarButton        Botón para generar teclados.
     * @param añadirAlf           Botón para añadir alfabetos.
     * @param añadirFre           Botón para añadir frecuencias.
     * @param idTecladoField      Campo de texto para el ID del teclado.
     * @param idalfabetoField     Campo de texto para el ID del alfabeto.
     * @param idFrecuenciaField   Campo de texto para el ID de la frecuencia.
     * @param group               Grupo de botones para seleccionar algoritmo.
     * @param qap                 Botón de algoritmo QAP.
     * @param sim                 Botón de algoritmo SA.
     * @param both                Botón de algoritmo ambos.
     * @param backButton          Botón para volver.
     */
    public void configurarBotones(JButton generarButton, JButton añadirAlf, JButton añadirFre, JTextField idTecladoField, JTextField idalfabetoField, JTextField idFrecuenciaField, ButtonGroup group, JRadioButton qap, JRadioButton sim, JRadioButton both, JButton backButton) {
        configurarBotonGenerar(generarButton, idTecladoField, idalfabetoField, idFrecuenciaField, group, qap, sim, both);
        configurarBotonAñadirAlf(añadirAlf, idTecladoField, idalfabetoField, idFrecuenciaField, group);
        configurarBotonAñadirFre(añadirFre, idTecladoField, idalfabetoField, idFrecuenciaField, group);
        configurarBotonVolver(backButton, idTecladoField, idalfabetoField, idFrecuenciaField, group);
    }

    /**
     * Configura el botón de generación de teclado.
     *
     * @param generarButton       Botón para generar teclados.
     * @param idTecladoField      Campo de texto para el ID del teclado.
     * @param idalfabetoField     Campo de texto para el ID del alfabeto.
     * @param idFrecuenciaField   Campo de texto para el ID de la frecuencia.
     * @param group               Grupo de botones para seleccionar algoritmo.
     * @param qap                 Botón de algoritmo QAP.
     * @param sim                 Botón de algoritmo SA.
     * @param both                Botón de algoritmo ambos.
     */
    private void configurarBotonGenerar(JButton generarButton, JTextField idTecladoField, JTextField idalfabetoField, JTextField idFrecuenciaField, ButtonGroup group, JRadioButton qap, JRadioButton sim, JRadioButton both) {
        generarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idTeclado = idTecladoField.getText();
                String idAlf = idalfabetoField.getText();
                String idFre = idFrecuenciaField.getText();
                String alg = null;
                if(qap.isSelected()) alg = "QAP";
                else if (sim.isSelected()) alg = "SA";
                else if(both.isSelected()) alg = "both";
                else throw new NullPointerException("Has de elegir un algoritmo");
                ctrlTecladosPresentacion.generarTeclado(idTeclado, alg, idAlf, idFre);
                limpiarCampos(idTecladoField, idalfabetoField, idFrecuenciaField, group);
            }
        });
    }

    /**
     * Configura el botón para añadir alfabetos.
     *
     * @param añadirAlf           Botón para añadir alfabetos.
     * @param idTecladoField      Campo de texto para el ID del teclado.
     * @param idalfabetoField     Campo de texto para el ID del alfabeto.
     * @param idFrecuenciaField   Campo de texto para el ID de la frecuencia.
     * @param group               Grupo de botones para seleccionar algoritmo.
     */
    private void configurarBotonAñadirAlf(JButton añadirAlf, JTextField idTecladoField, JTextField idalfabetoField, JTextField idFrecuenciaField, ButtonGroup group) {
        añadirAlf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos(idTecladoField, idalfabetoField, idFrecuenciaField, group);
                ctrlTecladosPresentacion.mostrarVistaAñadirAlfFre();
                ocultar();
            }
        });
    }

    /**
     * Configura el botón para añadir frecuencias.
     *
     * @param añadirFre           Botón para añadir frecuencias.
     * @param idTecladoField      Campo de texto para el ID del teclado.
     * @param idalfabetoField     Campo de texto para el ID del alfabeto.
     * @param idFrecuenciaField   Campo de texto para el ID de la frecuencia.
     * @param group               Grupo de botones para seleccionar algoritmo.
     */
    private void configurarBotonAñadirFre(JButton añadirFre, JTextField idTecladoField, JTextField idalfabetoField, JTextField idFrecuenciaField, ButtonGroup group) {
        añadirFre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos(idTecladoField, idalfabetoField, idFrecuenciaField, group);
                ctrlTecladosPresentacion.mostrarVistaAñadirAlfFre();
                ocultar();
            }
        });
    }

    /**
     * Configura el botón para volver.
     *
     * @param backButton          Botón para volver.
     * @param idTecladoField      Campo de texto para el ID del teclado.
     * @param idalfabetoField     Campo de texto para el ID del alfabeto.
     * @param idFrecuenciaField   Campo de texto para el ID de la frecuencia.
     * @param group               Grupo de botones para seleccionar algoritmo.
     */
    private void configurarBotonVolver(JButton backButton, JTextField idTecladoField, JTextField idalfabetoField, JTextField idFrecuenciaField, ButtonGroup group) {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos(idTecladoField, idalfabetoField, idFrecuenciaField, group);
                ctrlTecladosPresentacion.mostrarVistaInicial();
                ocultar();
            }
        });
    }

    /**
     * Limpia los campos de texto y la selección de botones.
     *
     * @param idTecladoField      Campo de texto para el ID del teclado.
     * @param idalfabetoField     Campo de texto para el ID del alfabeto.
     * @param idFrecuenciaField   Campo de texto para el ID de la frecuencia.
     * @param group               Grupo de botones para seleccionar algoritmo.
     */
    public void limpiarCampos(JTextField idTecladoField, JTextField idalfabetoField, JTextField idFrecuenciaField, ButtonGroup group) {
        idTecladoField.setText("");
        idFrecuenciaField.setText("");
        idalfabetoField.setText("");
        group.clearSelection();
        ctrlTecladosPresentacion.mostrarTodosAlfabetos();
        ctrlTecladosPresentacion.mostrarTodasFrecuencias();
    }

    /**
     * Establece el texto en el área de texto de alfabetos.
     *
     * @param text Texto a establecer.
     */
    public void setTextAlf(String text) {
        alfabetos.setText(text);
    }

    /**
     * Establece el texto en el área de texto de frecuencias.
     *
     * @param text Texto a establecer.
     */
    public void setTextFre(String text) {
        frecuencias.setText(text);
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
