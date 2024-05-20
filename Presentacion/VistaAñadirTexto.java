package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Vista para añadir texto a la aplicación.
 * Extiende la clase VistaAñadirDatos e implementa la configuración específica
 * para la adición de texto, incluyendo la gestión de la interfaz gráfica y eventos.
 */
public class VistaAñadirTexto extends VistaAñadirDatos {

    /**
     * Constructor de la clase VistaAñadirTexto.
     * @param ctrlDatosPresentacion Controlador de la capa de presentación de datos.
     */
    public VistaAñadirTexto(CtrlDatosPresentacion ctrlDatosPresentacion) {
        super(ctrlDatosPresentacion, "Añadir Texto");
        this.ctrlDatosPresentacion = ctrlDatosPresentacion;
        configurarInterfazHijos();
    }

    /**
     * Configura la interfaz principal de la vista.
     * Este método se sobrescribe de la clase padre, pero en esta clase no se utiliza.
     */
    @Override
    public void configurarInterfaz() {
        // No se utiliza en esta clase
    }

    /**
     * Configura la interfaz de los elementos secundarios de la vista.
     * Organiza los componentes en paneles y establece su disposición.
     */
    @Override
    protected void configurarInterfazHijos() {
        JPanel panel = new JPanel(new GridBagLayout());

        // Crear paneles para cada grupo de filas
        JPanel panelidTexto = new JPanel(new GridBagLayout());
        JPanel panelAlfFre = new JPanel(new GridBagLayout());
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelInferior = new JPanel(new BorderLayout());

        JScrollPane panelAreaTexto = new JScrollPane(textoArea);
        panelAreaTexto.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Utilizando GridBagLayout para mayor control sobre la disposición
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centro los componentes vertical y horizontalmente

        int initialWidthLabel = 150;
        int initialHeightLabel = 50;

        int initialWidthButton = 220;
        int initialHeightButton = 50;

        addTextoButton.setPreferredSize(new Dimension(initialWidthButton, initialHeightButton));
        idTextoField.setPreferredSize(new Dimension(100, 35));
        textoLabel.setPreferredSize(new Dimension(initialWidthLabel, initialHeightLabel));
        idTextoLabel.setPreferredSize(new Dimension(70, 35));
        idFrecuenciaField.setPreferredSize(new Dimension(100, 35));
        idalfabetoField.setPreferredSize(new Dimension(100, 35));
        idAlfLabel.setPreferredSize(new Dimension(100, 35));
        idFreLabel.setPreferredSize(new Dimension(100, 35));
        tecladoButton.setPreferredSize(new Dimension(160,35));
        tecladoLabel.setPreferredSize(new Dimension(165, initialHeightLabel));
        backButton.setPreferredSize(new Dimension(160, 35));

        importarTextoLabel.setPreferredSize(new Dimension(110, initialHeightLabel));
        importarTextoButton.setPreferredSize(new Dimension(35, 30));

        //panel idTexto
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelidTexto.add(idTextoLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelidTexto.add(idTextoField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panelidTexto.add(importarTextoLabel, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelidTexto.add(importarTextoButton, gbc);

        //panel Alf Fre
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelAlfFre.add(idAlfLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelAlfFre.add(idalfabetoField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panelAlfFre.add(idFreLabel, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelAlfFre.add(idFrecuenciaField, gbc);

        //panel button
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelButton.add(addTextoButton, gbc);

        // Añadir los paneles al contenedor principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.0;
        panel.add(panelidTexto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 20, 0, 20);
        panel.add(panelAreaTexto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0.0;
        panel.add(panelAlfFre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(15, 0, 0, 0);
        panel.add(panelButton, gbc);

        // Configurar el panel de botones
        JPanel panelBackButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        panelBackButton.add(backButton);

        JPanel panelTecladoButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        panelTecladoButton.add(tecladoLabel);
        panelTecladoButton.add(tecladoButton);

        panelInferior.add(panelBackButton, BorderLayout.WEST);
        panelInferior.add(panelTecladoButton, BorderLayout.EAST);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(panelInferior, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * Configura la acción del botón para añadir texto.
     */
    @Override
    protected void configurarBotonAñadirTexto() {
        addTextoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idTexto = idTextoField.getText();
                String texto = textoArea.getText();
                String idAlf = idalfabetoField.getText();
                String idFre = idFrecuenciaField.getText();
                ctrlDatosPresentacion.agregarTexto(idTexto, texto, idAlf, idFre);
            }
        });
    }

    /**
     * Configura la acción del botón para volver a la vista de añadir datos.
     */
    @Override
    protected void configurarBotonVolver() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
                ctrlDatosPresentacion.mostrarVistaAñadirDatos();
                ocultar();
            }
        });
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
