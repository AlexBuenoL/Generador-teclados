package edu.upc.prop.clusterxx.Presentacion;

import javax.swing.JOptionPane;

/**
 * La clase `CtrlPresentacion` es el controlador principal de la capa de presentación.
 * Gestiona la interacción entre las diferentes vistas y controladores de la aplicación.
 */
public class CtrlPresentacion {
    private VistaInicial vistaInicial;
    private CtrlDatosPresentacion ctrlDatosPresentacion;
    private CtrlTecladosPresentacion ctrlTecladosPresentacion;
    private CtrlUsuarioPresentacion ctrlUsuarioPresentacion;

    /**
     * Constructor de la clase `CtrlPresentacion`.
     * Inicializa las instancias de los controladores y vistas necesarios.
     */
    public CtrlPresentacion() {
        this.ctrlDatosPresentacion = new CtrlDatosPresentacion(this);
        this.ctrlTecladosPresentacion = new CtrlTecladosPresentacion(this, ctrlDatosPresentacion);
        this.ctrlUsuarioPresentacion = new CtrlUsuarioPresentacion(this);
        this.vistaInicial = new VistaInicial(this, ctrlDatosPresentacion, ctrlTecladosPresentacion);
    }

    /**
     * Inicia la aplicación cargando los usuarios y mostrando la vista de usuario.
     */
    public void iniciarApp() {
        ctrlUsuarioPresentacion.cargarUsuarios();
        ctrlUsuarioPresentacion.mostrarVistaUsuario();
    }

    /**
     * Carga los alfabetos desde el almacenamiento.
     */
    public void cargarAlfabetos() {
        ctrlDatosPresentacion.cargarAlfabetos();
    }

    /**
     * Carga las frecuencias desde el almacenamiento.
     */
    public void cargarFrecuencias() {
        ctrlDatosPresentacion.cargarFrecuencias();
    }

    /**
     * Carga los textos desde el almacenamiento.
     */
    public void cargarTextos() {
        ctrlDatosPresentacion.cargarTextos();
    }

    /**
     * Carga los teclados desde el almacenamiento.
     */
    public void cargarTeclados() {
        ctrlTecladosPresentacion.cargarTeclados();
    }

    /**
     * Cierra la sesión del usuario actual, mostrando una confirmación al usuario.
     * Si el usuario confirma, oculta la vista inicial y muestra la vista de usuario.
     */
    public void cerrarSesion() {
        int confirmacion = JOptionPane.showConfirmDialog(vistaInicial.frame,
            "¿Estás seguro de que deseas cerrar sesión?",
            "Confirmar cierre de sesión",
            JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // El usuario ha confirmado, cerrar sesión
            ocultarVistaInicial();
            ctrlUsuarioPresentacion.mostrarVistaUsuario();
        }
        // Si el usuario elige NO, no hacer nada
    }

    /**
     * Muestra la vista inicial de la aplicación.
     */
    public void mostrarVistaInicial() {
        vistaInicial.mostrar();
    }

    /**
     * Muestra la vista para generar un nuevo teclado.
     */
    public void mostrarVistaGenerarTeclado() {
        ctrlTecladosPresentacion.mostrarVistaGenerarTeclado();
    }

    /**
     * Muestra la vista para añadir alfabetos o frecuencias.
     */
    public void mostrarVistaAñadirAlfFre() {
        ctrlDatosPresentacion.mostrarVistaAñadirAlfFre();
    }

    /**
     * Oculta la vista inicial de la aplicación.
     */
    public void ocultarVistaInicial() {
        vistaInicial.ocultar();
    }
}
