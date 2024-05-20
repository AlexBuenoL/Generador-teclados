package edu.upc.prop.clusterxx.Presentacion;

import java.util.Arrays;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import edu.upc.prop.clusterxx.Dominio.usuario.CtrlGestionUsuarios;

/**
 * Controlador de la interfaz de usuario relacionada con la gestión de usuarios,
 * incluyendo operaciones como inicio de sesión, registro y carga de usuarios.
 * También gestiona la transición entre las vistas de inicio de sesión, registro y la vista de usuario.
 * Utiliza instancias de las clases VistaLogIn, VistaRegistro y VistaUsuario.
 */

public class CtrlUsuarioPresentacion {
    private VistaLogIn vistaLogIn;
    private VistaUsuario vistaUsuario;
    private VistaRegistro vistaRegistro;
    private CtrlPresentacion ctrlPresentacion;
    private CtrlGestionUsuarios ctrlGestorUsuarios;

    /**
     * Constructor del controlador de la interfaz de usuario de gestión de usuarios.
     *
     * @param ctrlPresentacion Instancia del controlador de presentación.
     */
    public CtrlUsuarioPresentacion(CtrlPresentacion ctrlPresentacion) {
        this.ctrlPresentacion = ctrlPresentacion;
        this.ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        this.vistaLogIn = new VistaLogIn(this);
        this.vistaRegistro = new VistaRegistro(this);
        this.vistaUsuario = new VistaUsuario(this, "Usuario");
    }

    /**
     * Obtiene la instancia del controlador de presentación asociado.
     *
     * @return Instancia del controlador de presentación.
     */
    public CtrlPresentacion getCtrlPresentacion() {
        return ctrlPresentacion;
    }

    /**
     * Realiza el proceso de inicio de sesión, cargando información y mostrando mensajes según el resultado.
     *
     * @param username Nombre de usuario proporcionado.
     * @param password Contraseña proporcionada.
     */

    public void login(String username, char[] password) {
            try {
                String storedUsername = ctrlGestorUsuarios.consultarNombreUsuario(username);
                storedUsername = storedUsername.trim();
                if(password.length == 0) throw new IllegalAccessError("La contraseña no puede estar vacía.");
                String storedPassword = ctrlGestorUsuarios.consultarContraseñaUsuario(username);
                if (storedPassword.equals(String.valueOf(password))) {
                    ctrlGestorUsuarios.setUsuarioActual(storedUsername);
                    ctrlPresentacion.cargarAlfabetos();
                    ctrlPresentacion.cargarFrecuencias();
                    ctrlPresentacion.cargarTextos();
                    ctrlPresentacion.cargarTeclados();
                    vistaLogIn.mostrarMensajeInform("Bienvenido " + storedUsername + "!");
                    mostrarVistaInicial();
                } else {
                    vistaLogIn.mostrarMensajeError("Contraseña incorrecta");
                }
            } catch (IllegalArgumentException e) {
                vistaLogIn.mostrarMensajeError("Error: " + e.getMessage());
            } catch (NullPointerException e1) {
                // El usuario no está registrado
                int opcion = JOptionPane.showConfirmDialog(
                    vistaLogIn.frame, //a que vista
                    e1.getMessage() + " ¿Deseas registrarte?", //mensaje
                    "Usuario no registrado", //titulo
                    JOptionPane.YES_NO_OPTION); //opcion
                if (opcion == JOptionPane.YES_OPTION) {
                    // Ir a la vista de registro
                    vistaUsuario.setEyeClose();
                    mostrarVistaRegistro();
                } else {
                    vistaUsuario.setEyeClose();
                    vistaLogIn.limpiarCampos();
                }
            } catch (NoSuchElementException e2) {
                vistaLogIn.mostrarMensajeError("Error: " + e2.getMessage());
            } catch (IllegalAccessError e3) {
                vistaLogIn.mostrarMensajeError("Error: " + e3.getMessage());
            }
    }

    /**
     * Realiza el proceso de registro de un usuario, mostrando mensajes según el resultado.
     *
     * @param username           Nombre de usuario proporcionado.
     * @param password           Contraseña proporcionada.
     * @param confirmPassword    Confirmación de la contraseña.
     */
    public void registro(String username, char[] password, char[] confirmPassword) {
        if(Arrays.equals(password, confirmPassword)) {
            try {
                username = username.trim();
                ctrlGestorUsuarios.registrarUsuario(username, String.valueOf(password));
                int opcion = JOptionPane.showConfirmDialog(
                    vistaRegistro.frame, //a que vista
                    "Registro exitoso! ¿Deseas iniciar sesion?", // mensaje
                    "Usuario registrado", //titulo del pop-up
                    JOptionPane.YES_NO_OPTION); //opcion
                if (opcion == JOptionPane.YES_OPTION) {
                    vistaUsuario.setEyeClose();
                    mostrarVistaLogin();
                } else {
                    vistaUsuario.setEyeClose();
                    vistaRegistro.limpiarCampos();
                }   
            } catch (IllegalStateException e) {
            vistaRegistro.mostrarMensajeError("Error en el registro: " + e.getMessage());
            } catch (IllegalArgumentException e1) {
            vistaRegistro.mostrarMensajeError("Error en el registro: " + e1.getMessage());
            }
        }
        else {
            vistaRegistro.mostrarMensajeError("Error en el registro: las contraseñas no coinciden");
        }
        
    }

     /**
     * Carga la información de los usuarios.
     */
    public void cargarUsuarios() {
        ctrlGestorUsuarios.cargarUsuarios();
    }

    /**
     * Muestra un mensaje de confirmación antes de cerrar la aplicación.
     * Si el usuario confirma, la aplicación se cierra.
     */
    public void salir() {
        int opcion = JOptionPane.showConfirmDialog(
                vistaUsuario.frame, //a que vista
                "¿Estás seguro de que deseas salir de la aplicación?",
                "Confirmar cierre de aplicación", //titulo del pop-up
                JOptionPane.YES_NO_OPTION); //opcion
            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }  
    }


     /**
     * Cambia a la vista inicial y oculta la vista de inicio de sesión.
     */
    public void mostrarVistaInicial() {
        ctrlPresentacion.mostrarVistaInicial();
        vistaLogIn.frame.setVisible(false);
    }

     /**
     * Muestra la vista de inicio de sesión y limpia sus campos.
     * Oculta las vistas de usuario y registro.
     */
    public void mostrarVistaLogin() {
        vistaLogIn.limpiarCampos();
        vistaLogIn.mostrar();
        vistaUsuario.frame.setVisible(false);
        vistaRegistro.frame.setVisible(false);
    }

    /**
     * Muestra la vista de registro y limpia sus campos.
     * Oculta las vistas de usuario e inicio de sesión.
     */
    public void mostrarVistaRegistro() {
        vistaRegistro.limpiarCampos();
        vistaRegistro.mostrar();
        vistaUsuario.frame.setVisible(false);
        vistaLogIn.frame.setVisible(false);
    }

      /**
     * Muestra la vista de usuario.
     * Oculta las vistas de inicio de sesión y registro.
     */
     public void mostrarVistaUsuario() {
        vistaUsuario.mostrar();
        vistaLogIn.frame.setVisible(false);
        vistaRegistro.frame.setVisible(false);
    }
}
