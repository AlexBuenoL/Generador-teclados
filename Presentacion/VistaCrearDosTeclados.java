package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaCrearDosTeclados extends VistaBase {
    /**
     * Controlador de presentación de teclados asociado a esta vista.
     */
    private CtrlTecladosPresentacion ctrlTecladosPresentacion;

    /**
     * Identificador del teclado.
     */
    private String idTeclado;

    /**
     * Representación bidimensional del diseño del primer teclado.
     */
    private char[][] teclado1;

    /**
     * Representación bidimensional del diseño del segundo teclado.
     */
    private char[][] teclado2;

    /**
     * Área de texto que muestra la representación visual del primer teclado.
     */
    private JTextArea teclado1Area;

    /**
     * Área de texto que muestra la representación visual del segundo teclado.
     */
    private JTextArea teclado2Area;

    /**
     * Campo de texto para la entrada de parámetros de optimización para el algoritmo QAP.
     */
    private JTextField optimQAPField;

    /**
     * Campo de texto para la entrada de parámetros de optimización para el algoritmo SA.
     */
    private JTextField optimSAField;

    /**
     * Botón de selección para el teclado asociado al problema QAP.
     */
    private JRadioButton teclQAP;

    /**
     * Botón de selección para el teclado asociado al problema SA.
     */
    private JRadioButton teclSA;

    /**
     * Grupo de botones para asegurar que solo se pueda seleccionar un tipo de teclado a la vez.
     */
    private ButtonGroup group;

        /**
     * Constructor de la vista para crear dos teclados.
     * @param ctrlTecladosPresentacion Controlador de presentación de teclados.
     */
    public VistaCrearDosTeclados(CtrlTecladosPresentacion ctrlTecladosPresentacion) {
        super(ctrlTecladosPresentacion.getCtrlPresentacion(), "Elegir entre Teclados");
        this.ctrlTecladosPresentacion = ctrlTecladosPresentacion;
        asignarValores(idTeclado, teclado1, teclado2);
        configurarInterfaz();
    }

    /**
     * Configura los botones de la interfaz.
     * @param guardar Botón para guardar.
     * @param backButton Botón de retroceso.
     * @param iniButton Botón de inicio.
     * @param info Botón de información.
     */
    public void configurarBotones(JButton guardar, JButton backButton, JButton iniButton, JButton info) {
        configurarBotonGuardar(guardar);
        configurarBotonInicio(iniButton);
        configurarBotonVolver(backButton);
        configurarBotonInfo(info);
    }


        /**
     * Configura la interfaz de la vista para crear dos teclados.
     */
    @Override
    public void configurarInterfaz() {
        JButton guardarTeclado = new JButton("Guardar Teclado");
        teclado1Area = new JTextArea();
        teclado1Area.setLineWrap(true);
        teclado1Area.setWrapStyleWord(true);
        teclado1Area.setEditable(false);
        teclado2Area = new JTextArea();
        teclado2Area.setLineWrap(true);
        teclado2Area.setWrapStyleWord(true);
        teclado2Area.setEditable(false);
        teclQAP = new JRadioButton("Teclado QAP");
        teclSA = new JRadioButton("Teclado SA");
        group = new ButtonGroup();
        group.add(teclQAP);
        group.add(teclSA);
        JLabel qapLabel = new JLabel("Teclado QAP");
        JLabel saLabel = new JLabel("Teclado SA");
        JButton backButton = new JButton("Volver");
        JButton iniButton = new JButton("Inicio");
        JLabel optimQAPLabel = new JLabel("optimalidad QAP:");
        JLabel optimSALabel = new JLabel("optimalidad SA:");
        optimQAPField = new JTextField();
        optimQAPField.setEditable(false);
        optimSAField = new JTextField();
        optimSAField.setEditable(false);
        JLabel info = new JLabel("INFO SOBRE OPTIMALIDAD:");
        JButton infoButton = new JButton(UIManager.getIcon("OptionPane.informationIcon"));
        infoButton.setToolTipText("Mostra info");

        configurarBotones(guardarTeclado, backButton, iniButton, infoButton);

        JPanel panel = new JPanel(new GridBagLayout());

        // Crear paneles para cada grupo de filas
        JPanel panelmostrar = new JPanel(new GridBagLayout());
        JPanel panelOpciones = new JPanel(new GridBagLayout());
        JPanel panelComparacion = new JPanel(new GridBagLayout());
        JPanel panelBoton = new JPanel(new GridBagLayout());
        JPanel panelInferior = new JPanel(new BorderLayout());
        JPanel notaPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));

        int initialWidthArea = 200; //ancho
        int initialHeightArea = 200; //alto
        //scrollpane teclado 1
        JScrollPane teclado1 = new JScrollPane(teclado1Area);
        teclado1.setPreferredSize(new Dimension(initialWidthArea, initialHeightArea));
        //scrollpane teclado 2
        JScrollPane teclado2 = new JScrollPane(teclado2Area);
        teclado2.setPreferredSize(new Dimension(initialWidthArea, initialHeightArea));

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

        guardarTeclado.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        backButton.setPreferredSize(new Dimension(160, 35));
        iniButton.setPreferredSize(new Dimension(160, 35));
        qapLabel.setPreferredSize(new Dimension(initialWidthLabel, initialHeightLabel));
        saLabel.setPreferredSize(new Dimension(initialWidthLabel, initialHeightLabel));
        optimQAPLabel.setPreferredSize(new Dimension(initialWidthLabel, initialHeightLabel));
        optimSALabel.setPreferredSize(new Dimension(initialWidthLabel, initialHeightLabel));
        optimQAPField.setPreferredSize(new Dimension(initialWidthField, initialHeightField));
        optimSAField.setPreferredSize(new Dimension(initialWidthField, initialHeightField));
        info.setPreferredSize(new Dimension(242, 35));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelmostrar.add(qapLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelmostrar.add(teclado1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelmostrar.add(saLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelmostrar.add(teclado2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelOpciones.add(teclQAP, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panelOpciones.add(teclSA, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelComparacion.add(optimQAPLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panelComparacion.add(optimQAPField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panelComparacion.add(optimSALabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panelComparacion.add(optimSAField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Se extiende a lo ancho
        gbc.fill = GridBagConstraints.NONE; // Cambiar fill a NONE
        panelBoton.add(guardarTeclado, gbc);

        // Configurar el panel de botones inferior
        JPanel panelBackButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        panelBackButton.add(backButton);

        JPanel panelInicioButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        panelInicioButton.add(iniButton);

        panelInferior.add(panelBackButton, BorderLayout.WEST);
        panelInferior.add(panelInicioButton, BorderLayout.EAST);

        notaPanel.add(info);
        notaPanel.add(infoButton);

        // Agregar paneles al panel principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(panelmostrar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(panelOpciones, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(panelComparacion, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(panelBoton, gbc);

        // Añadir los paneles al contenedor principal
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(panelInferior, BorderLayout.SOUTH);
        frame.getContentPane().add(notaPanel, BorderLayout.NORTH);

        frame.pack();
        frame.setLocationRelativeTo(null);

        ocultar();
    }

        /**
     * Configura el botón de guardar para realizar acciones específicas al hacer clic en él.
     *
     * @param guardar El botón de guardar que se va a configurar.
     */
    private void configurarBotonGuardar(JButton guardar) {
        guardar.addActionListener(new ActionListener() {
            /**
             * Se ejecuta cuando se hace clic en el botón de guardar.
             *
             * @param e Evento de acción que desencadena el clic en el botón.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(teclQAP.isSelected()) ctrlTecladosPresentacion.guardarTeclados(true, idTeclado, teclado1);
                else if(teclSA.isSelected()) ctrlTecladosPresentacion.guardarTeclados(true, idTeclado, teclado2);
                else ctrlTecladosPresentacion.guardarTeclados(false, null, null);
                limpiarCampos();
            }
        });
    }

    /**
     * Configura el botón de inicio para realizar acciones específicas al hacer clic en él.
     *
     * @param Inicio El botón de inicio que se va a configurar.
     */
    private void configurarBotonInicio(JButton Inicio) {
        Inicio.addActionListener(new ActionListener() {
            /**
             * Se ejecuta cuando se hace clic en el botón de inicio.
             *
             * @param e Evento de acción que desencadena el clic en el botón.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
                ctrlTecladosPresentacion.mostrarVistaInicial();
                ocultar();
            }
        });
    }

    /**
     * Configura el botón de información para realizar acciones específicas al hacer clic en él.
     *
     * @param info El botón de información que se va a configurar.
     */
    private void configurarBotonInfo(JButton info) {
        info.addActionListener(new ActionListener() {
            /**
             * Se ejecuta cuando se hace clic en el botón de información.
             *
             * @param e Evento de acción que desencadena el clic en el botón.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlTecladosPresentacion.mostrarInfo();
            }
        });
    }

    /**
     * Configura el botón de volver para realizar acciones específicas al hacer clic en él.
     *
     * @param back El botón de volver que se va a configurar.
     */
    private void configurarBotonVolver(JButton back) {
        back.addActionListener(new ActionListener() {
            /**
             * Se ejecuta cuando se hace clic en el botón de volver.
             *
             * @param e Evento de acción que desencadena el clic en el botón.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
                ctrlTecladosPresentacion.mostrarVistaGenerarTeclado();
                ocultar();
            }
        });
    }


        /**
     * Asigna los valores del teclado.
     * @param id Identificador del teclado.
     * @param teclado1 Matriz representando el teclado 1.
     * @param teclado2 Matriz representando el teclado 2.
     */
    public void asignarValores(String id, char[][] teclado1, char[][] teclado2) {
        this.idTeclado = id;
        this.teclado1 = teclado1;
        this.teclado2 = teclado2;
    }

    /**
     * Establece el texto para la representación del teclado 1.
     * @param text Texto a establecer en el área del teclado 1.
     */
    public void setTextTeclado1(String text) {
        teclado1Area.setText(text);
    }

    /**
     * Establece el texto para la representación del teclado 2.
     * @param text Texto a establecer en el área del teclado 2.
     */
    public void setTextTeclado2(String text) {
        teclado2Area.setText(text);
    }

    /**
     * Establece el texto para la optimización del problema QAP.
     * @param text Texto a establecer en el campo de optimización QAP.
     */
    public void setOptQAP(String text) {
        optimQAPField.setText(text);
    }

    /**
     * Establece el texto para la optimización del algoritmo SA.
     * @param text Texto a establecer en el campo de optimización SA.
     */
    public void setOptSA(String text) {
        optimSAField.setText(text);
    }

    /**
     * Limpia los campos de la interfaz.
     */
    public void limpiarCampos() {
        teclado1Area.setText("");
        teclado2Area.setText("");
        optimQAPField.setText("");
        optimSAField.setText("");
        group.clearSelection();
    }

    /**
     * Muestra la interfaz.
     */
    @Override
    public void mostrar() {
        frame.setVisible(true);
    }

    /**
     * Oculta la interfaz.
     */
    @Override
    public void ocultar() {
        frame.setVisible(false);
    }

    
}
