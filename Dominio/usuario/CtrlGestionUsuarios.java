package edu.upc.prop.clusterxx.Dominio.usuario;

import edu.upc.prop.clusterxx.Dominio.Frecuencia.CtrlGestionFrecuencias;
import edu.upc.prop.clusterxx.Dominio.alfabeto.CtrlGestionAlfabetos;
import edu.upc.prop.clusterxx.Dominio.Texto.CtrlGestionTextos;
import edu.upc.prop.clusterxx.Dominio.teclado.CtrlGestionTeclados;

import edu.upc.prop.clusterxx.Persistencia.GestionPersistenciaUsuario;

import java.util.HashMap;


/**
 * La clase CtrlGestionUsuarios proporciona una interfaz para gestionar la información de los usuarios
 * del sistema, así como sus configuraciones asociadas, como alfabetos, textos, frecuencias y teclados.
 */

public class CtrlGestionUsuarios {

    private GestionUsuarios gestorUsuarios = new GestionUsuarios();

    private static CtrlGestionUsuarios instance;

    private CtrlGestionAlfabetos ctrlGestorAlfabetos = CtrlGestionAlfabetos.getInstance();
    private CtrlGestionFrecuencias ctrlGestorFrecuencias = CtrlGestionFrecuencias.getInstance();
    private CtrlGestionTeclados ctrlGestorTeclados = CtrlGestionTeclados.getInstance();
    private CtrlGestionTextos ctrlGestorTextos = CtrlGestionTextos.getInstance();

    private GestionPersistenciaUsuario gestorPersistenciaUsuario = new GestionPersistenciaUsuario();

    private CtrlGestionUsuarios() {}

    /**
     * Devuelve una instancia única de CtrlGestionUsuarios (Singleton).
     * @return La instancia única de CtrlGestionUsuarios.
     */
    public synchronized static CtrlGestionUsuarios getInstance() {
        if(instance == null) {
            instance = new CtrlGestionUsuarios();
        }
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }


    // getters i setters
    /**
     * Consulta un usuario por su nombre.
     * @param nombre El nombre del usuario a consultar.
     * @return El objeto Usuario correspondiente al nombre proporcionado.
     */
    public Usuario consultarUsuario(String nombre) {
        return gestorUsuarios.consultarUsuario(nombre);
    }

     /**
     * Obtiene el nombre del usuario actualmente logueado.
     * @return El nombre del usuario actual.
     */
    public String getUsuarioActual() {
        return gestorUsuarios.getUsuarioActual();
    }

    /**
     * Establece el nombre del usuario actualmente logueado.
     * @param usr El nombre del usuario a establecer como actual.
     */
    public void setUsuarioActual(String usr) {
        gestorUsuarios.setUsuarioActual(usr);
    }

    /**
     * Carga la información de los usuarios desde la persistencia.
     */
    public void cargarUsuarios() {
        HashMap<String,Usuario> usuariosCargados = gestorPersistenciaUsuario.cargarUsuarios();
        gestorUsuarios.cargarUsuarios(usuariosCargados);
    }

    /**
     * Consulta el nombre de un usuario por su nombre de usuario.
     * @param usr El nombre de usuario a consultar.
     * @return El nombre del usuario asociado al nombre de usuario proporcionado.
     */
    public String consultarNombreUsuario(String usr) {
        return gestorUsuarios.consultarNombreUsuario(usr);
    }

     /**
     * Consulta el nombre de un usuario por objeto Usuario.
     * @param usr El objeto Usuario a consultar.
     * @return El nombre del usuario asociado al objeto Usuario proporcionado.
     */
    public String consultarNombreUsuario(Usuario usr) {
        return gestorUsuarios.consultarNombreUsuario(usr);
    }

    /**
     * Consulta la contraseña de un usuario por su nombre.
     * @param nombre El nombre del usuario.
     * @return La contraseña asociada al usuario.
     */
    public String consultarContraseñaUsuario(String nombre) {
        return gestorUsuarios.consultarContraseñaUsuario(nombre);
    }

    /**
     * Obtiene el listado de usuarios en el sistema.
     * @return Un mapa que asocia los nombres de usuario con objetos Usuario.
     */
    public HashMap<String, Usuario> getlistadoUsuarios() {
        return gestorUsuarios.getlistadoUsuarios();
    }


    // consultora
     /**
     * Verifica si existe un usuario dado su nombre.
     * @param nombre El nombre del usuario a verificar.
     * @return `true` si el usuario existe, `false` en caso contrario.
     */
    public boolean existeUsuario(String nombre) {
        return gestorUsuarios.existeUsuario(nombre);
    }


    // modificadoras
    /**
     * Registra un nuevo usuario en el sistema con un nombre y una contraseña.
     * @param nombre El nombre del nuevo usuario.
     * @param contraseña La contraseña del nuevo usuario.
     */
    public void registrarUsuario(String nombre, String contraseña) {
        gestorUsuarios.registrarUsuario(nombre, contraseña);
        Usuario usr = gestorUsuarios.consultarUsuario(nombre);
        gestorPersistenciaUsuario.guardarUsuario(nombre, usr.getContraseña());
    }
    /**
     * Borra un usuario del sistema dada su nombre.
     * @param nombre El nombre del usuario a borrar.
     */
    public void borrarUsuario(String nombre) {
        gestorUsuarios.borrarUsuario(nombre);
        gestorPersistenciaUsuario.borrarUsuario(nombre);
    }
    
    /**
     * Cierra la sesión del usuario actual, limpiando las configuraciones asociadas a su sesión.
     */
    public void logout() {
        ctrlGestorAlfabetos.clear();
        ctrlGestorTextos.clear();
        ctrlGestorFrecuencias.clear();
        ctrlGestorTeclados.clear();
    }
}
