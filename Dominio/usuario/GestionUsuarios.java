package edu.upc.prop.clusterxx.Dominio.usuario;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * La clase GestionUsuarios proporciona métodos para gestionar la información de los usuarios
 * registrados en el sistema.
 */

public class GestionUsuarios {
    //atributos privados
    private HashMap<String,Usuario> usuarios;

    private String usuarioActual;

    //constructoras
    /**
     * Constructora que inicializa la estructura de datos para almacenar usuarios.
     */
    public GestionUsuarios() {
        usuarios = new HashMap<String,Usuario>();
    }

    //consultoras
     /**
     * Obtiene el nombre del usuario actualmente logueado.
     * @return El nombre del usuario actual.
     */
    public String getUsuarioActual() {
        return usuarioActual;
    }

    /**
     * Establece el nombre del usuario actualmente logueado.
     * @param usr El nombre del usuario a establecer como actual.
     */
    public void setUsuarioActual(String usr) {
        usuarioActual = usr;
    }

    /**
     * Carga la información de los usuarios desde un mapa previamente cargado.
     * @param usuariosCargados El mapa de usuarios cargado desde persistencia.
     */
    public void cargarUsuarios(HashMap<String,Usuario> usuariosCargados) {
        usuarios = usuariosCargados;
    }
    
    /**
     * Consulta un usuario por su nombre.
     * @param nombre El nombre del usuario a consultar.
     * @return El objeto Usuario correspondiente al nombre proporcionado.
     * @throws NoSuchElementException Si no hay usuarios en el sistema o el usuario no existe.
     * @throws NullPointerException Si el nombre de usuario proporcionado es nulo.
     */
    public Usuario consultarUsuario(String nombre) {
        if (usuarios.isEmpty()) throw new NoSuchElementException("No hay ningún usuario en el sistema");
        else if (!existeUsuario(nombre)) throw new NullPointerException ("No existe el usuario " + nombre);
        else return usuarios.get(nombre);
    }

    /**
     * Consulta el nombre de un usuario por su nombre de usuario.
     * @param nombre El nombre de usuario a consultar.
     * @return El nombre del usuario asociado al nombre de usuario proporcionado.
     * @throws NoSuchElementException Si no hay usuarios en el sistema o el usuario no existe.
     * @throws NullPointerException Si el nombre de usuario proporcionado es nulo.
     * @throws IllegalArgumentException Si el nombre de usuario proporcionado está vacío.
     */
    public String consultarNombreUsuario(String nombre) throws NoSuchElementException, NullPointerException, IllegalArgumentException {
        if(nombre.isEmpty()) throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        else if (!existeUsuario(nombre)) throw new NullPointerException ("Usuario " + nombre + " no registrado");
        else if (usuarios.isEmpty()) throw new NoSuchElementException("No hay ningún usuario en el sistema");
        else return nombre;
    }

    /**
     * Consulta el nombre de un usuario por objeto Usuario.
     * @param usr El objeto Usuario a consultar.
     * @return El nombre del usuario asociado al objeto Usuario proporcionado.
     * @throws NoSuchElementException Si no hay usuarios en el sistema o el usuario no existe.
     * @throws NullPointerException Si el objeto Usuario proporcionado es nulo.
     */
    public String consultarNombreUsuario(Usuario usr) throws NoSuchElementException, NullPointerException {
        if (usuarios.isEmpty()) throw new NoSuchElementException("No hay ningún usuario en el sistema");
        else if (!usuarios.containsValue(usr)) throw new NullPointerException ("No existe el usuario");
        else return usr.getNombre();
    }

        /**
     * Consulta la contraseña de un usuario por su nombre.
     * @param nombre El nombre del usuario.
     * @return La contraseña asociada al usuario.
     * @throws NoSuchElementException Si no hay usuarios en el sistema o el usuario no existe.
     * @throws NullPointerException Si el nombre de usuario proporcionado es nulo.
     */
    public String consultarContraseñaUsuario(String nombre) throws NoSuchElementException, NullPointerException {
        if (!existeUsuario(nombre)) throw new NullPointerException ("No existe el usuario " + nombre);
        else if (usuarios.isEmpty()) throw new NoSuchElementException("No hay ningún usuario en el sistema");
        else return usuarios.get(nombre).getContraseña();
    }

    /**
     * Verifica si existe un usuario dado su nombre.
     * @param nombre El nombre del usuario a verificar.
     * @return `true` si el usuario existe, `false` en caso contrario.
     */
    public boolean existeUsuario(String nombre) {
        return usuarios.containsKey(nombre);
    }

    /**
     * Obtiene el listado de usuarios en el sistema.
     * @return Un mapa que asocia los nombres de usuario con objetos Usuario.
     * @throws NoSuchElementException Si no hay usuarios en el sistema.
     */
    public HashMap<String, Usuario> getlistadoUsuarios() throws NoSuchElementException {
        if(usuarios.isEmpty()) throw new NoSuchElementException("No hay ningun usuario en el sistema");
        else return usuarios;
    }

    //modificadoras
    /**
     * Verifica si una contraseña cumple con los requisitos de tener al menos 8 caracteres,
     * al menos 1 mayúscula y al menos 1 número.
     * @param contraseña La contraseña a verificar.
     * @return `true` si la contraseña cumple con los requisitos, `false` en caso contrario.
     */
    private boolean contieneMayusculaYNumero(String contraseña) {
        boolean tieneMayuscula = false;
        boolean tieneNumero = false;
    
        for (char c : contraseña.toCharArray()) {
            if (Character.isUpperCase(c)) {
                tieneMayuscula = true;
            } else if (Character.isDigit(c)) {
                tieneNumero = true;
            }
    
            // Si ya encontramos al menos una mayúscula y un número, podemos salir del bucle
            if (tieneMayuscula && tieneNumero) {
                break;
            }
        }
    
        return tieneMayuscula && tieneNumero;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * @param nombre El nombre del nuevo usuario.
     * @param contraseña La contraseña del nuevo usuario.
     * @throws IllegalStateException Si el usuario ya existe.
     * @throws IllegalArgumentException Si el nombre de usuario o la contraseña no cumplen con los requisitos.
     */
    public void registrarUsuario(String nombre, String contraseña) throws IllegalStateException, IllegalArgumentException {
        if(nombre.isEmpty()) throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        else if(contraseña.isEmpty()) throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        else if (contraseña.length() < 8 || !contieneMayusculaYNumero(contraseña)) {
            throw new IllegalArgumentException("La contraseña debe tener mínimo 8 caracteres, al menos 1 mayúscula y 1 número.");
        }
        else {
            if(existeUsuario(nombre)) {
            throw new IllegalStateException("Usuario con nombre " + nombre + " ya existe.");
            }
            else {
                usuarios.put(nombre,new Usuario(contraseña));
            }
        }
    }

    /**
     * Borra un usuario del sistema.
     * @param nombre El nombre del usuario a borrar.
     * @throws NoSuchElementException Si no hay usuarios en el sistema o el usuario no existe.
     * @throws NullPointerException Si el nombre de usuario proporcionado es nulo.
     */
    public void borrarUsuario(String nombre) throws NoSuchElementException, NullPointerException {
        if(usuarios.isEmpty()) throw new NoSuchElementException("No hay ningun usuario en el sistema");
        else if(!existeUsuario(nombre)) throw new NullPointerException("No existe el usuario " + nombre);
        else {
            usuarios.remove(nombre);
        }
    }
}
