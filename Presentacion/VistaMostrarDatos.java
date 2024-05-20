package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase representa la interfaz gráfica para mostrar diversos datos relacionados
 * con alfabetos, frecuencias y textos. Permite al usuario seleccionar qué información
 * desea consultar y visualiza el contenido correspondiente. Extiende la clase VistaBase
 * e implementa ActionListener para manejar eventos de botones.
 */

public class VistaMostrarDatos extends VistaBase {
    private CtrlDatosPresentacion ctrlDatosPresentacion;
    private JTextArea contenidoArea;
    
    /**
     * Constructor de la vista para mostrar datos.
     *
     * @param ctrlDatosPresentacion Controlador de presentación de datos.
     */
    public VistaMostrarDatos(CtrlDatosPresentacion ctrlDatosPresentacion) {
        super(ctrlDatosPresentacion.getCtrlPresentacion(), "Mostrar Datos");
        this.ctrlDatosPresentacion = ctrlDatosPresentacion;

        configurarInterfaz();
    }

    /**
 * Configura los botones de la interfaz para mostrar diferentes tipos de datos,
 * asignándoles las acciones correspondientes al hacer clic.
 *
 * @param alf Botón para mostrar el alfabeto.
 * @param fre Botón para mostrar las frecuencias.
 * @param text Botón para mostrar el texto.
 * @param todosAlf Botón para mostrar todos los alfabetos.
 * @param todasFre Botón para mostrar todas las frecuencias.
 * @param todosText Botón para mostrar todos los textos.
 * @param back Botón para volver a la vista de gestión de datos.
 */
    
    private void configurarBotones(JButton alf, JButton fre, JButton text, JButton todosAlf, JButton todasFre, JButton todosText, JButton back) {

        configurarBotonMostrarAlf(alf);
        configurarBotonMostrarFre(fre);
        configurarBotonMostrarTexto(text);
        configurarBotonMostrarTodasFre(todasFre);
        configurarBotonMostrarTodosAlf(todosAlf);
        configurarBotonMostrarTodosTextos(todosText);
        configurarBotonVolver(back);      
    }

     /**
     * Configura la interfaz de la vista para mostrar datos, estableciendo la disposición
     * de los componentes gráficos, botones y áreas de texto.
     */
    @Override
    public void configurarInterfaz() {
        JButton btnMostrarAlfabeto = new JButton("Mostrar Alfabeto");
        JButton btnMostrarFrecuencias = new JButton("Mostrar Frecuencia");
        JButton btnMostrarTexto = new JButton("Mostrar Texto");
        JButton btnMostrarTodosAlfabetos = new JButton("Mostrar todos Alfabetos");
        JButton btnMostrarTodasFrecuencias = new JButton("Mostrar todas Frecuencias");
        JButton btnMostrarTodosTextos = new JButton("Mostrar todos Textos");
        contenidoArea = new JTextArea();
        contenidoArea.setLineWrap(true);
        contenidoArea.setWrapStyleWord(true);
        contenidoArea.setEditable(false);
        JLabel labelEncimaBotones = new JLabel("Qué desea consultar?");
        JButton backButton = new JButton("Volver");

        configurarBotones(btnMostrarAlfabeto, btnMostrarFrecuencias, btnMostrarTexto, btnMostrarTodosAlfabetos, btnMostrarTodasFrecuencias, btnMostrarTodosTextos, backButton);

        JPanel panel = new JPanel(new GridBagLayout());

        // Crear paneles para cada grupo de filas
        JPanel panelBotones = new JPanel(new GridBagLayout());
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JScrollPane panelContenido = new JScrollPane(contenidoArea);
        panelContenido.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Utilizando GridBagLayout para mayor control sobre la disposición
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente

        int initialWidthButton = 230;
        int initialHeightButton = 50;

        btnMostrarAlfabeto.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        btnMostrarFrecuencias.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        btnMostrarTexto.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        btnMostrarTodasFrecuencias.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        btnMostrarTodosAlfabetos.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        btnMostrarTodosTextos.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        labelEncimaBotones.setPreferredSize(new Dimension(250, 35));
        backButton.setPreferredSize(new Dimension(160, 35));

        //panel botones
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        panelBotones.add(btnMostrarAlfabeto, gbc);

        // Botón Mostrar Frecuencias
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        panelBotones.add(btnMostrarFrecuencias, gbc);

        // Botón Mostrar Texto
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        panelBotones.add(btnMostrarTexto, gbc);

        // Botón Mostrar Todos Los Alfabetos
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        panelBotones.add(btnMostrarTodosAlfabetos, gbc);

        // Botón Mostrar Todas Las Frecuencias
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        panelBotones.add(btnMostrarTodasFrecuencias, gbc);

        // Botón Mostrar Todos Los Textos
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        panelBotones.add(btnMostrarTodosTextos, gbc);

        // Agregar paneles al panel principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.0;
        panel.add(panelBotones, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.insets = new Insets(10, 20, 0, 20);
        panel.add(panelContenido, gbc);

        labelPanel.add(labelEncimaBotones, gbc);
        backButtonPanel.add(backButton, gbc);

        // Añadir los paneles al contenedor principal
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(labelPanel, BorderLayout.NORTH);
        frame.getContentPane().add(backButtonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }    

     /**
     * Configura el botón para mostrar el alfabeto, asignándole una acción al hacer clic.
     *
     * @param btnMostrarAlfabeto Botón para mostrar el alfabeto.
     */
    private void configurarBotonMostrarAlf(JButton btnMostrarAlfabeto) {
        btnMostrarAlfabeto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarAlfabeto();
            }
        });
    }

     /**
     * Configura el botón para mostrar las frecuencias, asignándole una acción al hacer clic.
     *
     * @param btnMostrarFrecuencias Botón para mostrar las frecuencias.
     */
    private void configurarBotonMostrarFre(JButton btnMostrarFrecuencias) {
        btnMostrarFrecuencias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarFrecuencias();
            }
        });
    }

     /**
     * Configura el botón para mostrar el texto, asignándole una acción al hacer clic.
     *
     * @param btnMostrarTexto Botón para mostrar el texto.
     */
    private void configurarBotonMostrarTexto(JButton btnMostrarTexto) {
        btnMostrarTexto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarTexto();
            }
        });
    }

    /**
     * Configura el botón para mostrar todos los alfabetos, asignándole una acción al hacer clic.
     *
     * @param btnMostrarTodosAlfabetos Botón para mostrar todos los alfabetos.
     */
    private void configurarBotonMostrarTodosAlf(JButton btnMostrarTodosAlfabetos) {
        btnMostrarTodosAlfabetos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarTodosAlfabetos();
            }
        });
    }

    /**
     * Configura el botón para mostrar todas las frecuencias, asignándole una acción al hacer clic.
     *
     * @param btnMostrarTodasFrecuencias Botón para mostrar todas las frecuencias.
     */
    private void configurarBotonMostrarTodasFre(JButton btnMostrarTodasFrecuencias) {
        btnMostrarTodasFrecuencias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarTodasFrecuencias();
            }
        });
    }

    /**
     * Configura el botón para mostrar todos los textos, asignándole una acción al hacer clic.
     *
     * @param btnMostrarTodosTextos Botón para mostrar todos los textos.
     */
    private void configurarBotonMostrarTodosTextos(JButton btnMostrarTodosTextos) {
        btnMostrarTodosTextos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarTodosTextos();
            }
        });
    }

    /**
     * Configura el botón para volver a la vista de gestión de datos, asignándole una acción al hacer clic.
     *
     * @param backButton Botón para volver a la vista de gestión de datos.
     */
    private void configurarBotonVolver(JButton backButton) {
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
     * Establece el texto en el área de contenido de la vista.
     *
     * @param text Texto a mostrar en el área de contenido.
     */
    public void setText(String text) {
        contenidoArea.setText(text);
    }

    /**
     * Limpia el contenido del área de contenido.
     */
    public void limpiarCampos() {
        contenidoArea.setText("");
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
    public void ocultar() {
        frame.setVisible(false);
    }
}
