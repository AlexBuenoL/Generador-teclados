package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Clase que representa la interfaz gráfica para añadir datos en la aplicación.
 * Extiende de la clase VistaBase.
 */
public class VistaAñadirDatos extends VistaBase {
    protected CtrlDatosPresentacion ctrlDatosPresentacion;
    //padre
    protected JButton addAlfabetoFrecButton;
    protected JButton addTextoButton;
    protected JButton importarAlfButton;
    protected JButton importarFreButton;
    protected JButton importarTextoButton;
    protected JLabel importarAlfLabel;
    protected JLabel importarFreLabel;
    protected JLabel importarTextoLabel;
    protected ImageIcon carpetaIcon;
    protected Image carpetaImage;
    //alfabeto  
    protected JButton addAlfabetoButton;
    protected JLabel alfabetoLabel;
    protected JLabel idAlfLabel;
    protected JTextField idalfabetoField;
    protected JTextField alfabetoField;
    //frecuencia
    protected JButton addFrecuenciasButton;
    protected JLabel frecuenciaLabel;
    protected JLabel idFreLabel;
    protected JTextField idFrecuenciaField;
    protected JTextArea frecuenciaArea;
    //texto
    protected JLabel textoLabel;
    protected JLabel idTextoLabel; 
    protected JTextArea textoArea;
    protected JTextField idTextoField;
    //teclado
    protected JButton tecladoButton;
    protected JLabel tecladoLabel;

    protected JLabel info;
    protected JButton infoButton;
    
    protected JButton backButton;
   
    /**
     * Constructor de la clase VistaAñadirDatos.
     * @param ctrlDatosPresentacion Controlador de la capa de presentación de datos.
     * @param titulo Título de la vista.
     */
    public VistaAñadirDatos(CtrlDatosPresentacion ctrlDatosPresentacion, String titulo) {
        super(ctrlDatosPresentacion.getCtrlPresentacion(), titulo);
        this.ctrlDatosPresentacion = ctrlDatosPresentacion;

        configurarBotones();

        configurarInterfaz();
    }

    /**
     * Configura los botones de la interfaz.
     */
    protected void configurarBotones() {
        addAlfabetoFrecButton = new JButton("Añadir Alfabeto y Frecuencia");
        addTextoButton = new JButton("Añadir Texto");
        importarAlfButton = new JButton();
        importarFreButton = new JButton();
        importarTextoButton = new JButton();
        importarAlfLabel = new JLabel("Importar Alfabeto");
        importarFreLabel = new JLabel("Importar Frecuencia");
        importarTextoLabel = new JLabel("Importar Texto");

        addAlfabetoButton = new JButton("Añadir Alfabeto");   
        alfabetoLabel = new JLabel("Alfabeto:");        
        idAlfLabel = new JLabel("id Alfabeto:");
        alfabetoField = new JTextField();
        idalfabetoField = new JTextField();
        
        addFrecuenciasButton = new JButton("Añadir Frecuencias"); 
        frecuenciaLabel = new JLabel("Listado frecuencias:"); 
        idFreLabel = new JLabel("id Frecuencia:");  
        idFrecuenciaField = new JTextField();   
        frecuenciaArea = new JTextArea();
        frecuenciaArea.setLineWrap(true);  // Permitir el salto de línea automático
        frecuenciaArea.setWrapStyleWord(true);     

        addTextoButton = new JButton("Añadir Texto");
        textoLabel = new JLabel("Texto:");
        idTextoLabel = new JLabel("id Texto:"); 
        idTextoField = new JTextField();
        textoArea = new JTextArea();
        textoArea.setLineWrap(true);
        textoArea.setWrapStyleWord(true);

        tecladoButton = new JButton("Ir");
        tecladoLabel = new JLabel("Ir a generar el Teclado");

        info = new JLabel("INFO SOBRE CÓMO AÑADIR DATOS:");
        infoButton = new JButton(UIManager.getIcon("OptionPane.informationIcon"));
        infoButton.setToolTipText("Mostra info");

        backButton = new JButton("Volver");

        configurarImagenes();

        configurarBotonAñadirAlfFre();
        configurarBotonAñadirAlf();
        configurarBotonAñadirFre();
        configurarBotonTeclado();
        configurarBotonAñadirTexto();
        configurarBotonImportarAlf();
        configurarBotonImportarFre();
        configurarBotonImportarTexto();
        configurarBotonInfo();
        configurarBotonVolver();
    }

    /**
     * Configura las imágenes utilizadas en la interfaz.
     */
    protected void configurarImagenes() {
        carpetaIcon = new ImageIcon("icons/carpeta.png");
        carpetaImage = carpetaIcon.getImage().getScaledInstance(35, 30, Image.SCALE_SMOOTH);
        importarAlfButton.setIcon(new ImageIcon(carpetaImage));
        importarFreButton.setIcon(new ImageIcon(carpetaImage));
        importarTextoButton.setIcon(new ImageIcon(carpetaImage));

        // Establecer la posición del texto vertical y horizontalmente
        infoButton.setVerticalTextPosition(SwingConstants.CENTER);
        infoButton.setHorizontalTextPosition(SwingConstants.CENTER);
        // Establecer el borde del botón a cero
        infoButton.setBorder(BorderFactory.createEmptyBorder());
        // Asegurar que el contenido del botón se alinee en el centro
        infoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoButton.setPreferredSize(new Dimension(
            infoButton.getIcon().getIconWidth(),
            infoButton.getIcon().getIconHeight()));
    }

    /**
     * Configura la interfaz principal de la aplicación.
     * Crea y organiza los componentes en paneles y establece su disposición.
     */
    @Override
    public void configurarInterfaz() {
        // Panel para agrupar
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        JPanel notaPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente

        // Establecer el tamaño preferido inicial en función del tamaño de la ventana
        int initialWidthButton = 250;
        int initialHeightButton = 50;

        addAlfabetoFrecButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        addTextoButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        info.setPreferredSize(new Dimension(242, 35));
        backButton.setPreferredSize(new Dimension(160, 35));

        // Añadir Alf Fre button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        buttonPanel.add(addAlfabetoFrecButton, gbc);

        // Añadir texto button
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        buttonPanel.add(addTextoButton, gbc);

        backButtonPanel.add(backButton, gbc);

        notaPanel.add(info);
        notaPanel.add(infoButton);

        // Añadir los paneles al contenedor principal
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        frame.getContentPane().add(backButtonPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(notaPanel, BorderLayout.NORTH);

        // Centrar la ventana en la pantalla
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * Configura la interfaz de los elementos secundarios de la aplicación.
     * Crea paneles para grupos de filas y organiza los componentes en ellos.
     */
    protected void configurarInterfazHijos() {
        JPanel panel = new JPanel(new GridBagLayout());

        // Crear paneles para cada grupo de filas
        JPanel panelAlfabeto = new JPanel(new GridBagLayout());
        JPanel panelFrecuencia = new JPanel(new GridBagLayout());
        JPanel panelInferior = new JPanel(new BorderLayout());
        JPanel notaPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));

        int initialWidthArea = 250;
        int initialHeightArea = 250;
        // Configurar el JScrollPane con el JTextArea
        JScrollPane scrollPane = new JScrollPane(frecuenciaArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(initialWidthArea, initialHeightArea));

        // Utilizando GridBagLayout para mayor control sobre la disposición
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente

        int initialWidthLabel = 150;
        int initialHeightLabel = 50;

        int initialWidthField = 350;
        int initialHeightField = 35;

        int initialWidthButton = 220;
        int initialHeightButton = 50;

        addAlfabetoButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        addFrecuenciasButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        alfabetoField.setPreferredSize(new Dimension(initialWidthField, initialHeightField));
        idFrecuenciaField.setPreferredSize(new Dimension(100, 35));
        idalfabetoField.setPreferredSize(new Dimension(100, 35));
        alfabetoLabel.setPreferredSize(new Dimension(80, 35));
        frecuenciaLabel.setPreferredSize(new Dimension(250, initialHeightLabel));
        idAlfLabel.setPreferredSize(new Dimension(100, 35));
        idFreLabel.setPreferredSize(new Dimension(100, 35));
        tecladoButton.setPreferredSize(new Dimension(160,35));
        tecladoLabel.setPreferredSize(new Dimension(165, initialHeightLabel));
        backButton.setPreferredSize(new Dimension(160, 35));

        importarAlfLabel.setPreferredSize(new Dimension(initialWidthLabel, initialHeightLabel));
        importarFreLabel.setPreferredSize(new Dimension(90, initialHeightLabel));
        importarAlfButton.setPreferredSize(new Dimension(35, 30));
        importarFreButton.setPreferredSize(new Dimension(35, 30));

        // Configurar el panel de alfabeto
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelAlfabeto.add(idAlfLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelAlfabeto.add(idalfabetoField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panelAlfabeto.add(alfabetoLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        panelAlfabeto.add(importarAlfLabel, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelAlfabeto.add(alfabetoField, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        gbc.fill = GridBagConstraints.NONE;  // Cambiar fill a NONE
        panelAlfabeto.add(importarAlfButton, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        panelAlfabeto.add(addAlfabetoButton, gbc);

        // Configurar el panel de frecuencia
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFrecuencia.add(idFreLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelFrecuencia.add(idFrecuenciaField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panelFrecuencia.add(frecuenciaLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        panelFrecuencia.add(importarFreLabel, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panelFrecuencia.add(scrollPane, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        gbc.fill = GridBagConstraints.NONE;  // Cambiar fill a NONE
        panelFrecuencia.add(importarFreButton, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;  // Se extiende a lo ancho
        gbc.fill = GridBagConstraints.NONE;  // Cambiar fill a NONE
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelFrecuencia.add(addFrecuenciasButton, gbc);

        // Agregar paneles al panel principal
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 20, 0);  // Agrega espacio vertical de 10 píxeles
        panel.add(panelAlfabeto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(35, 0, 0, 0);  // Agrega espacio vertical de 10 píxeles
        panel.add(panelFrecuencia, gbc);

        // Restaura los insets a su valor predeterminado
        gbc.insets = new Insets(5, 5, 5, 5);

        // Configurar el panel de botones inferior
        JPanel panelBackButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        panelBackButton.add(backButton);

        JPanel panelTecladoButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        panelTecladoButton.add(tecladoLabel);
        panelTecladoButton.add(tecladoButton);

        panelInferior.add(panelBackButton, BorderLayout.WEST);
        panelInferior.add(panelTecladoButton, BorderLayout.EAST);

        notaPanel.add(info);
        notaPanel.add(infoButton);

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
     * Configura la acción del botón para mostrar la vista de añadir alfabeto y frecuencia.
     */
    protected void configurarBotonAñadirAlfFre() {
        addAlfabetoFrecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarVistaAñadirAlfFre();
                ocultar();
            }
        });
    }

    /**
     * Configura la acción del botón para agregar un alfabeto.
     */
    protected void configurarBotonAñadirAlf() {
        addAlfabetoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idalfabetoField.getText();
                String alfabeto = alfabetoField.getText();

                ctrlDatosPresentacion.agregarAlfabeto(id, alfabeto);
            }
        });
    }

    /**
     * Configura la acción del botón para agregar frecuencias.
     */
    protected void configurarBotonAñadirFre() {
        addFrecuenciasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idFrecuenciaField.getText();
                String frecuencia = frecuenciaArea.getText();
                ctrlDatosPresentacion.agregarFrecuencias(id, frecuencia);
            }
        });
    }

    /**
     * Configura la acción del botón para mostrar la vista de añadir texto.
     */
    protected void configurarBotonAñadirTexto() {
        addTextoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarVistaAñadirTexto();
                ocultar();
            }
        });
    }

    /**
     * Configura la acción del botón para mostrar la vista de generar teclado.
     */
    protected void configurarBotonTeclado() {
        tecladoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                    frame, //a qué vista
                    "¿Quieres ir a generar Teclado?\nPara generar un Teclado debes haber añadido un alfabeto y una frecuencia correctamente.", // mensaje
                    "Ir a Generar Teclado", //titulo del pop-up
                    JOptionPane.YES_NO_OPTION); //opción
                if (opcion == JOptionPane.YES_OPTION) {
                    limpiarCampos();
                    ctrlDatosPresentacion.mostrarVistaGenerarTeclado();
                }
            }
        });
    }

    /**
     * Configura la acción del botón para importar alfabetos desde un archivo.
     */
    protected void configurarBotonImportarAlf() {
        importarAlfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un objeto JFileChooser
                JFileChooser fileChooser = new JFileChooser();

                // Mostrar el diálogo de selección de archivo
                int result = fileChooser.showOpenDialog(frame);

                // Verificar si el usuario seleccionó un archivo
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Obtener el archivo seleccionado
                    File selectedFile = fileChooser.getSelectedFile();

                    // Obtener la ruta del archivo seleccionado
                    String filePath = selectedFile.getAbsolutePath();
                    ctrlDatosPresentacion.importarAlfabeto(filePath);
                }
            }
        });
    }

    /**
     * Configura la acción del botón para importar frecuencias desde un archivo.
     */
    protected void configurarBotonImportarFre() {
        importarFreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un objeto JFileChooser
                JFileChooser fileChooser = new JFileChooser();

                // Mostrar el diálogo de selección de archivo
                int result = fileChooser.showOpenDialog(frame);

                // Verificar si el usuario seleccionó un archivo
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Obtener el archivo seleccionado
                    File selectedFile = fileChooser.getSelectedFile();

                    // Obtener la ruta del archivo seleccionado
                    String filePath = selectedFile.getAbsolutePath();
                    ctrlDatosPresentacion.importarFrecuencia(filePath);
                }
            }
        });
    }

    /**
     * Configura la acción del botón para importar texto desde un archivo.
     */
    protected void configurarBotonImportarTexto() {
        importarTextoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un objeto JFileChooser
                JFileChooser fileChooser = new JFileChooser();

                // Mostrar el diálogo de selección de archivo
                int result = fileChooser.showOpenDialog(frame);

                // Verificar si el usuario seleccionó un archivo
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Obtener el archivo seleccionado
                    File selectedFile = fileChooser.getSelectedFile();

                    // Obtener la ruta del archivo seleccionado
                    String filePath = selectedFile.getAbsolutePath();
                    ctrlDatosPresentacion.importarTexto(filePath);
                }
            }
        });
    }

    /**
     * Configura la acción del botón de información.
     */
    protected void configurarBotonInfo() {
        // Configurar acción del botón de información
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlDatosPresentacion.mostrarInfoGestionar();
            }
        });
    }

    /**
     * Configura la acción del botón para volver a la vista de gestionar datos.
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
     * Establece el texto del campo de identificación del alfabeto.
     * @param id Identificación del alfabeto a establecer.
     */
    public void setIdAlf(String id) {
        idalfabetoField.setText(id);
    }

    /**
     * Establece el texto del campo de alfabeto.
     * @param alf Alfabeto a establecer.
     */
    public void setAlf(String alf) {
        alfabetoField.setText(alf);
    }

    /**
     * Establece el texto del campo de identificación de frecuencia.
     * @param id Identificación de frecuencia a establecer.
     */
    public void setIdFre(String id) {
        idFrecuenciaField.setText(id);
    }

    /**
     * Establece el texto del área de frecuencia.
     * @param fre Frecuencia a establecer.
     */
    public void setFre(String fre) {
        frecuenciaArea.setText(fre);
    }

    /**
     * Establece el texto del campo de identificación de texto.
     * @param id Identificación de texto a establecer.
     */
    public void setIdText(String id) {
        idTextoField.setText(id);
    }

    /**
     * Establece el texto del área de texto.
     * @param text Texto a establecer.
     */
    public void setText(String text) {
        textoArea.setText(text);
    }

    /**
     * Limpia todos los campos de la interfaz.
     */
    protected void limpiarCampos() {
        idalfabetoField.setText("");
        idFrecuenciaField.setText("");
        alfabetoField.setText("");
        frecuenciaArea.setText("");
        textoArea.setText("");
        idTextoField.setText("");
    }

    /**
     * Muestra la ventana principal de la vista.
     */
    @Override
    public void mostrar() {
        frame.setVisible(true);
    }

    /**
     * Oculta la ventana principal de la vista.
     */
    @Override
    public void ocultar() {
        frame.setVisible(false);
    }
}
