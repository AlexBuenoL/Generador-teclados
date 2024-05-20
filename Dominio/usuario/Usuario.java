package edu.upc.prop.clusterxx.Dominio.usuario;

/**
 * La clase Usuario representa un usuario en el sistema.
 * Cada usuario tiene un nombre y una contraseña.
 */
public class Usuario {
    // Atributos privados
    private String nombre;
    private String contraseña;

    /**
     * Constructor por defecto de la clase Usuario.
     * Crea un objeto Usuario sin inicializar sus atributos.
     */
    public Usuario() {
        // Código del constructor por defecto
    }

    /**
     * Constructor de la clase Usuario que recibe la contraseña como parámetro.
     * Crea un objeto Usuario con la contraseña especificada.
     *
     * @param contra La contraseña del usuario.
     */
    public Usuario(String contra) {
        contraseña = contra;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contr La nueva contraseña del usuario.
     */
    public void setContraseña(String contr) {
        contraseña = contr;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nom El nuevo nombre del usuario.
     */
    public void setNombre(String nom) {
        nombre = nom;
    }

    /**
     * Devuelve una representación de cadena del objeto Usuario.
     * Esta implementación devuelve la contraseña del usuario.
     *
     * @return Una cadena que representa la contraseña del usuario.
     */
    @Override
    public String toString() {
        return contraseña;
    }
}
