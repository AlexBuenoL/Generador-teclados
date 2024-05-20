package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * La clase VistaUsuario representa la interfaz de usuario para operaciones relacionadas con usuarios.
 * Extiende de VistaBase y utiliza el framework Swing para la construcción de la interfaz gráfica.
 */
public class VistaUsuario extends VistaBase {
    // Atributos de la clase
    protected CtrlUsuarioPresentacion ctrlUsuarioPresentacion;
    protected JButton loginButton;
    protected JButton registroButton;
    protected JButton backButton;
    protected JTextField usernameField;
    protected JPasswordField passwordField;
    protected JLabel usernameLabel;
    protected JLabel passwordLabel;
    protected JLabel confirmPasswordLabel;
    protected JPasswordField confirmPasswordField;
    protected JLabel newAccLabel;
    protected JButton newAccButton;
    protected JButton showPasswordButton;
    protected boolean showPassword;
    protected ImageIcon openEyeIcon;
    protected ImageIcon closeEyeIcon;
    protected Image openEyeImage;
    protected Image closeEyeImage;

    private JButton exitButton;
    protected JPanel panel;
    protected GridBagConstraints gbc;

    /**
     * Constructor de la clase VistaUsuario.
     * @param ctrlUsuarioPresentacion Controlador de presentación de usuario.
     * @param titulo Título de la ventana.
     */
    public VistaUsuario(CtrlUsuarioPresentacion ctrlUsuarioPresentacion, String titulo) {
        super(ctrlUsuarioPresentacion.getCtrlPresentacion(), titulo);
        this.ctrlUsuarioPresentacion = ctrlUsuarioPresentacion;

        // Configuración inicial de los botones e interfaz
        configurarBotones();
        configurarInterfaz();
    }

    /**
     * Configura los botones utilizados en la interfaz de usuario.
     */
    protected void configurarBotones() {
        loginButton = new JButton("Iniciar Sesión");
        registroButton = new JButton("Registrarse");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        usernameLabel = new JLabel("Nombre de Usuario:");
        passwordLabel = new JLabel("Contraseña:");
        confirmPasswordLabel = new JLabel("Confirmar contraseña:");
        confirmPasswordField = new JPasswordField();
        newAccLabel = new JLabel("No tienes una cuenta?");
        newAccButton = new JButton("Registrate!");
        backButton = new JButton("Volver");
        exitButton = new JButton("Salir");
        showPasswordButton = new JButton();
        showPasswordButton.setToolTipText("Mostrar Contraseña");
        showPassword = false;

        configurarImagenes();

        configurarBotonLogin();
        configurarBotonRegistro();
        configurarBotonSalir();
        configurarBotonVolver();
        configurarBotonMostrarPsw();
        configurarBotonNewAcc();
    }

    /**
     * Configura las imágenes utilizadas en la interfaz.
     */
    protected void configurarImagenes() {
        // Cargar las imágenes de los ojos abierto y cerrado
        openEyeIcon = new ImageIcon("icons/openEye.png");
        closeEyeIcon = new ImageIcon("icons/closeEye.png");
        // Escalar las imágenes al tamaño del botón
        openEyeImage = openEyeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        closeEyeImage = closeEyeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        // Establecer las imágenes escaladas en el botón
        showPasswordButton.setIcon(new ImageIcon(closeEyeImage)); // Inicia con el ojo cerrad
    }

    /**
     * Configura la interfaz de usuario.
     */
    @Override
    public void configurarInterfaz() {
        // Panel para agrupar
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
    
        // Establecer el tamaño preferido inicial en función del tamaño de la ventana
        int initialWidthButton = 220;
        int initialHeightButton = 50;
    
        loginButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        registroButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        exitButton.setPreferredSize(new Dimension(160, 35));

    
        // Botón de inicio de sesión
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(loginButton, gbc);
    
        // Botón de registro
        gbc.gridy = 1;
        buttonPanel.add(registroButton, gbc);
    
        // Botón de salir en la esquina inferior derecha
        exitPanel.add(exitButton);
    
        // Añadir los paneles al contenedor principal
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        frame.getContentPane().add(exitPanel, BorderLayout.SOUTH);
    
        configurarResize(initialWidthButton, initialHeightButton, initialWidthWindow, initialHeightWindow);

        // Centra y ajusta el tamaño de la ventana
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    protected void configurarInterfazEspecificaHijos(JButton button) {

    }

    /**
     * Método común para configurar la interfaz de los hijos.
     * @param button Botón asociado a la interfaz del hijo.
     */
    protected void configurarInterfazComunHijos(JButton button) {
        panel = new JPanel(new GridBagLayout());
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        int initialWidthLabel = 220;
        int initialHeightLabel = 50;

        int initialWidthField = 250;
        int initialHeightField = 35;

        int initialWidthButton = 220;
        int initialHeightButton = 50;

        usernameLabel.setPreferredSize(new Dimension(initialWidthLabel, initialHeightLabel));
        usernameField.setPreferredSize(new Dimension(initialWidthField, initialHeightField));
        passwordLabel.setPreferredSize(new Dimension(initialWidthLabel, initialHeightLabel));
        passwordField.setPreferredSize(new Dimension(initialWidthField, initialHeightField));
        button.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        showPasswordButton.setPreferredSize(new Dimension(20, 20));
        backButton.setPreferredSize(new Dimension(160, 35));
        confirmPasswordLabel.setPreferredSize(new Dimension(initialWidthLabel, initialHeightLabel));
        confirmPasswordField.setPreferredSize(new Dimension(initialWidthField, initialHeightField));
        newAccLabel.setPreferredSize(new Dimension(initialWidthLabel, initialHeightLabel));
        newAccButton.setPreferredSize(new Dimension(130, 30));

        //label username
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        //field username
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(usernameField, gbc);

        //label password
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(passwordLabel, gbc);

        //field password
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(passwordField, gbc);

        // Configurar el botón para mostrar/ocultar la contraseña
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(showPasswordButton, gbc);

        configurarInterfazEspecificaHijos(button);

        // Botón de volver en su propio panel
        backButtonPanel.add(backButton);

        // Añadir los paneles al contenedor principal
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(backButtonPanel, BorderLayout.SOUTH);

        configurarResizeHijos(button, initialWidthLabel, initialHeightLabel, initialWidthField, initialHeightField, initialWidthButton, initialHeightButton, initialWidthWindow, initialHeightWindow);

        // Centra y ajusta el tamaño de la ventana
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * Configura el botón de inicio de sesión.
     */
    protected void configurarBotonLogin() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUsuarioPresentacion.mostrarVistaLogin();
            }
        });
    }

    /**
     * Configura el botón de registro.
     */
    protected void configurarBotonRegistro() {
        registroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUsuarioPresentacion.mostrarVistaRegistro();
            }
        });
    }

    /**
     * Configura el botón de salir.
     */
    private void configurarBotonSalir() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUsuarioPresentacion.salir();
            }
        });
    }

    /**
     * Configura el botón de volver.
     */
    protected void configurarBotonVolver() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para volver a VistaUsuario
                setEyeClose();
                ctrlUsuarioPresentacion.mostrarVistaUsuario();
            }
        });
    }

    /**
     * Configura el botón para mostrar/ocultar la contraseña.
     */
    protected void configurarBotonMostrarPsw() {
        showPasswordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleShowPassword();
            }
        });
    }

    /**
     * Configura el botón de registro en la interfaz de los hijos.
     */
    protected void configurarBotonNewAcc() {
        newAccButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlUsuarioPresentacion.mostrarVistaRegistro();
            }
        });
    }

    /**
     * Alterna entre mostrar y ocultar la contraseña.
     */
    protected void toggleShowPassword() {
        showPassword = !showPassword;

        if (showPassword) {
            passwordField.setEchoChar((char) 0);
            confirmPasswordField.setEchoChar((char) 0);
            showPasswordButton.setIcon(new ImageIcon(openEyeImage)); // Cambiar a ícono de ojo abierto
        } else {
            passwordField.setEchoChar('\u2022');
            confirmPasswordField.setEchoChar('\u2022');
            showPasswordButton.setIcon(new ImageIcon(closeEyeImage)); // Cambiar a ícono de ojo cerrado
        }
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
    
    }

    /**
     * Limpia los campos de entrada.
     */
    protected void limpiarCampos() {
        usernameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    /**
     * Establece el ícono del ojo en estado abierto.
     */
    public void setEyeOpen() {
        showPassword = true;
        showPasswordButton.setIcon(new ImageIcon(openEyeImage));
    }

    /**
     * Establece el ícono del ojo en estado cerrado.
     */
    public void setEyeClose() {
        showPassword = false;
        showPasswordButton.setIcon(new ImageIcon(closeEyeImage));
    }

    /**
     * Configura el redimensionamiento de la interfaz principal en respuesta a eventos de cambio de tamaño.
     * @param initialWidthButton Ancho inicial preferido del botón de inicio de sesión.
     * @param initialHeightButton Altura inicial preferida del botón de inicio de sesión.
     * @param initialWidthWindow Ancho inicial preferido de la ventana.
     * @param initialHeightWindow Altura inicial preferida de la ventana.
     */
    private void configurarResize(int initialWidthButton, int initialHeightButton, int initialWidthWindow, int initialHeightWindow) {
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int currentWidth = frame.getContentPane().getWidth();
                int currentHeight = frame.getContentPane().getHeight();

                // Verifica si la ventana está maximizada
                boolean isMaximized = (frame.getExtendedState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH;

                // Escala proporcionalmente al tamaño actual de la ventana solo si no está maximizada
                if (!isMaximized && currentWidth < initialWidthWindow && currentHeight < initialHeightWindow) {
                    int newWidth = (int) (initialWidthButton * (currentWidth / (double) initialWidthWindow));
                    int newHeight = (int) (initialHeightButton * (currentHeight / (double) initialHeightWindow));

                    newWidth = Math.max(newWidth, 100);  // Establece un ancho mínimo
                    newHeight = Math.max(newHeight, 30);  // Establece una altura mínima

                    loginButton.setPreferredSize(new Dimension(newWidth, newHeight));
                    registroButton.setPreferredSize(new Dimension(newWidth, newHeight));

                    if(newWidth < 160 && newHeight < 35) exitButton.setPreferredSize(new Dimension(newWidth, newHeight));

                    frame.revalidate();
                }
            }
        });
    }

    /**
     * Configura el redimensionamiento de la interfaz de los hijos en respuesta a eventos de cambio de tamaño.
     * @param button Botón asociado a la interfaz del hijo.
     * @param initialWidthLabel Ancho inicial preferido de la etiqueta.
     * @param initialHeightLabel Altura inicial preferida de la etiqueta.
     * @param initialWidthField Ancho inicial preferido del campo de texto.
     * @param initialHeightField Altura inicial preferida del campo de texto.
     * @param initialWidthButton Ancho inicial preferido del botón.
     * @param initialHeightButton Altura inicial preferida del botón.
     * @param initialWidthWindow Ancho inicial preferido de la ventana.
     * @param initialHeightWindow Altura inicial preferida de la ventana.
     */
    private void configurarResizeHijos(JButton button, int initialWidthLabel, int initialHeightLabel, int initialWidthField, int initialHeightField,
                                        int initialWidthButton, int initialHeightButton, int initialWidthWindow, int initialHeightWindow) {
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int currentWidth = frame.getContentPane().getWidth();
                int currentHeight = frame.getContentPane().getHeight();

                // Verifica si la ventana está maximizada
                boolean isMaximized = (frame.getExtendedState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH;

                // Escala proporcionalmente al tamaño actual de la ventana solo si no está maximizada
                if (!isMaximized && currentWidth < initialWidthWindow && currentHeight < initialHeightWindow) {
                    int newButtonWidth = (int) (initialWidthButton * (currentWidth / (double) initialWidthWindow));
                    int newButtonHeight = (int) (initialHeightButton * (currentHeight / (double) initialHeightWindow));

                    int newFieldWidth = (int) (initialWidthField * (currentWidth / (double) initialWidthWindow));
                    int newFieldHeight = (int) (initialHeightField * (currentHeight / (double) initialHeightWindow));

                    int newLabelWidth = (int) (initialWidthLabel * (currentWidth / (double) initialWidthWindow));
                    int newLabelHeight = (int) (initialHeightLabel * (currentHeight / (double) initialHeightWindow));

                    newButtonWidth = Math.max(newButtonWidth, 100);  // Establece un ancho mínimo
                    newButtonHeight = Math.max(newButtonHeight, 30);  // Establece una altura mínima

                    newFieldWidth = Math.max(newFieldWidth, 100);  // Establece un ancho mínimo
                    newFieldHeight = Math.max(newFieldHeight, 30);  // Establece una altura mínima

                    newLabelWidth = Math.max(newLabelWidth, 100);  // Establece un ancho mínimo
                    newLabelHeight = Math.max(newLabelHeight, 30);  // Establece una altura mínima

                    button.setPreferredSize(new Dimension(newButtonWidth, newButtonHeight));

                    usernameField.setPreferredSize(new Dimension(newFieldWidth, newFieldHeight));
                    passwordField.setPreferredSize(new Dimension(newFieldWidth, newFieldHeight));

                    usernameLabel.setPreferredSize(new Dimension(newLabelWidth, newLabelHeight));
                    passwordLabel.setPreferredSize(new Dimension(newLabelWidth, newLabelHeight));

                    if(newButtonWidth < 160 && newButtonHeight < 35) backButton.setPreferredSize(new Dimension(newButtonWidth, newButtonHeight));

                    frame.revalidate();
                }
            }
        });
    }

}
