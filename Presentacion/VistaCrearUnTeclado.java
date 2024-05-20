package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaCrearUnTeclado extends VistaBase {
    /**
     * Controlador de presentación de teclados asociado a esta vista.
     */
    private CtrlTecladosPresentacion ctrlTecladosPresentacion;

    /**
     * Identificador del teclado.
     */
    private String idTeclado;

    /**
     * Representación bidimensional del diseño del teclado.
     */
    private char[][] teclado;

    /**
     * Área de texto que muestra la representación visual del teclado.
     */
    private JTextArea tecladoArea;

    /**
     * Constructor de la clase VistaCrearUnTeclado.
     *
     * @param ctrlTecladosPresentacion El controlador de presentación de teclados asociado a esta vista.
     */
    public VistaCrearUnTeclado(CtrlTecladosPresentacion ctrlTecladosPresentacion) {
        super(ctrlTecladosPresentacion.getCtrlPresentacion(), "Guardar Teclado");
        this.ctrlTecladosPresentacion = ctrlTecladosPresentacion;
        asignarValores(idTeclado, teclado);
        configurarInterfaz();
    }

    /**
     * Configura los botones de la interfaz.
     *
     * @param guardar    Botón de guardar.
     * @param backButton Botón de retroceso.
     * @param iniButton  Botón de inicio.
     */
    public void configurarBotones(JButton guardar, JButton backButton, JButton iniButton) {
        configurarBotonGuardar(guardar);
        configurarBotonInicio(iniButton);
        configurarBotonVolver(backButton);
    }

    /**
     * Configura la interfaz gráfica de la vista para la operación de guardar teclado.
     * Se establecen los componentes visuales, como botones, áreas de texto y etiquetas,
     * así como su disposición en paneles utilizando GridBagLayout.
     * Además, se define el comportamiento de los botones al ser presionados.
     */
    @Override
    public void configurarInterfaz() {
        JButton guardarTeclado = new JButton("Guardar Teclado");
        tecladoArea = new JTextArea();
        tecladoArea.setLineWrap(true);
        tecladoArea.setWrapStyleWord(true);
        tecladoArea.setEditable(false);
        JLabel tecladoLabel = new JLabel("Teclado");
        JButton backButton = new JButton("Volver");
        JButton iniButton = new JButton("Inicio");

        configurarBotones(guardarTeclado, backButton, iniButton);

        JPanel panel = new JPanel(new GridBagLayout());
    
        // Crear paneles para cada grupo de filas
        JPanel panelmostrar = new JPanel(new GridBagLayout());
        JPanel panelBoton = new JPanel(new GridBagLayout());
        JPanel panelInferior = new JPanel(new BorderLayout());

        int initialWidthArea = 200; //ancho
        int initialHeightArea = 200; //alto
        //scrollpane teclado 1
        JScrollPane teclado = new JScrollPane(tecladoArea);
        teclado.setPreferredSize(new Dimension(initialWidthArea, initialHeightArea));

         // Utilizando GridBagLayout para mayor control sobre la disposición
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
         gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente
 
         int initialWidthLabel = 150;
         int initialHeightLabel = 50;
 
         int initialWidthButton = 180;
         int initialHeightButton = 50;

        guardarTeclado.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        backButton.setPreferredSize(new Dimension(160, 35));
        iniButton.setPreferredSize(new Dimension(160, 35));
        tecladoLabel.setPreferredSize(new Dimension(initialWidthLabel, initialHeightLabel));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelmostrar.add(tecladoLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelmostrar.add(teclado, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        gbc.fill = GridBagConstraints.NONE;  // Cambiar fill a NONE
        panelBoton.add(guardarTeclado, gbc);

        // Configurar el panel de botones inferior
        JPanel panelBackButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        panelBackButton.add(backButton);

        JPanel panelInicioButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        panelInicioButton.add(iniButton);

        panelInferior.add(panelBackButton, BorderLayout.WEST);
        panelInferior.add(panelInicioButton, BorderLayout.EAST);

        // Agregar paneles al panel principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(panelmostrar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
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
     * Configura el botón para guardar la configuración de los teclados.
     *
     * @param guardar Botón de guardar.
     */
    private void configurarBotonGuardar(JButton guardar) {
        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlTecladosPresentacion.guardarTeclados(true, idTeclado, teclado);
                limpiarCampos();                
            }
        });
    }

    /**
     * Configura el botón para ir a la vista inicial.
     *
     * @param inicio Botón de inicio.
     */
    private void configurarBotonInicio(JButton Inicio) {
        Inicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
                ctrlTecladosPresentacion.mostrarVistaInicial();
                ocultar();
            }
        });
    }

        /**
     * Configura el botón para volver a la vista de generar teclado.
     *
     * @param back Botón de volver.
     */
    private void configurarBotonVolver(JButton back) {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
                ctrlTecladosPresentacion.mostrarVistaGenerarTeclado();
                ocultar();
            }
        });
    }

        /**
     * Asigna los valores para la creación de un teclado.
     *
     * @param id     Identificador del teclado.
     * @param teclas Arreglo bidimensional que representa las teclas del teclado.
     */
    public void asignarValores(String id, char[][] teclas) {
        this.idTeclado = id;
        this.teclado = teclas;
    }

    /**
     * Establece el texto en el área de texto del teclado.
     *
     * @param text Texto a establecer en el área de texto del teclado.
     */
    public void setText(String text) {
        tecladoArea.setText(text);
    }

    /**
     * Limpia los campos del teclado, estableciendo el área de texto a vacío.
     */
    public void limpiarCampos() {
        tecladoArea.setText("");
    }

    /**
     * Muestra la interfaz gráfica asociada al teclado.
     */
    @Override
    public void mostrar() {
        frame.setVisible(true);
    }

    /**
     * Oculta la interfaz gráfica asociada al teclado.
     */
    @Override
    public void ocultar() {
        frame.setVisible(false);
    }

    
}
