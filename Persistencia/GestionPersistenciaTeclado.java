package edu.upc.prop.clusterxx.Persistencia;

import edu.upc.prop.clusterxx.Dominio.usuario.CtrlGestionUsuarios;

import java.io.*;
import java.util.TreeMap;
import java.util.HashMap;

/**
 * Esta clase proporciona métodos para gestionar la persistencia de teclados,
 * incluyendo la carga y guardado desde/hacia archivos y operaciones de manipulación.
 *
 * Implementa la interfaz Serializable para permitir la serialización de objetos.
 */
public class GestionPersistenciaTeclado implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto de la clase GestionPersistenciaTeclado.
     */
    public GestionPersistenciaTeclado() {}

    /**
     * Carga un TreeMap de teclados desde un archivo .dat.
     *
     * @param archivo La ruta del archivo .dat a cargar.
     * @return Un TreeMap con los teclados cargados desde el archivo.
     */
    private static TreeMap<String, char[][]> cargarDatos(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            @SuppressWarnings("unchecked")
            TreeMap<String, char[][]> tecladosCargados = (TreeMap<String, char[][]>) ois.readObject();
            System.out.println("Teclados cargados con éxito desde " + archivo);
            return tecladosCargados;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new TreeMap<>(); // Devolver un TreeMap vacío en caso de error
        }
    }

    /**
     * Guarda un TreeMap de datos en un archivo .dat.
     *
     * @param mapDatos El TreeMap de datos a guardar.
     * @param archivo La ruta del archivo .dat donde se guardarán los datos.
     */
    private static void guardarDatos(TreeMap<String, char[][]> mapDatos, String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(mapDatos);
            System.out.println("Objeto guardado con éxito en " + archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Guarda un teclado en el archivo "teclados.dat".
     *
     * @param idTec Identificador del teclado.
     * @param tec Matriz de caracteres que representa el teclado a guardar.
     */
    public void guardarTeclado(String idTec, char[][] tec) {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se obtienen los teclados guardados
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/teclados.dat";
        File ficheroTeclados = new File(path);

        TreeMap<String, char[][]> mapDatos;
        if (ficheroTeclados.exists()) mapDatos = cargarDatos(path); // Si ya existe teclados.dat, se cargan los datos en el TreeMap
        else mapDatos = new TreeMap<>(); // Si no existe teclados.dat --> es el primer teclado que se guarda --> se creará al hacer el write

        mapDatos.put(idTec, tec);
        guardarDatos(mapDatos, path);
    }

    /**
     * Carga todos los teclados del usuario actual.
     *
     * @return Un HashMap con los identificadores y matrices de caracteres de los teclados del usuario.
     */
    public HashMap<String, char[][]> cargarTeclados() {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se cargan los objetos en un TreeMap
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/teclados.dat";

        // Verificar si el archivo existe antes de intentar cargarlo
        File file = new File(path);
        if (!file.exists()) {
            // Devolver un HashMap vacío si el archivo no existe
            return new HashMap<>();
        }

        TreeMap<String, char[][]> mapDatos = cargarDatos(path);

        // Se convierte el TreeMap a HashMap
        HashMap<String, char[][]> teclados = new HashMap<>();
        teclados.putAll(mapDatos);
        return teclados;
    }

    /**
     * Borra un teclado del archivo "teclados.dat" según su identificador.
     *
     * @param idTec Identificador del teclado a borrar.
     */
    public void borrarTeclado(String idTec) {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se obtienen los teclados guardados
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/teclados.dat";
        TreeMap<String, char[][]> mapDatos = cargarDatos(path);

        // Se elimina el teclado y se vuelve a guardar en el fichero
        mapDatos.remove(idTec);
        guardarDatos(mapDatos, path);
    }
}
