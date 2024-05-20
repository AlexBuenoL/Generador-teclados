package edu.upc.prop.clusterxx.Persistencia;

import java.io.*;
import java.util.*;

import edu.upc.prop.clusterxx.Dominio.usuario.CtrlGestionUsuarios;

/**
 * Esta clase proporciona métodos para gestionar la persistencia de alfabetos,
 * incluyendo la carga y guardado desde/hacia archivos y operaciones de manipulación.
 */
public class GestionPersistenciaAlfabeto {

    /**
     * Constructor por defecto de la clase GestionPersistenciaAlfabeto.
     */
    public GestionPersistenciaAlfabeto() {}

    /**
     * Carga un TreeMap de Alfabetos desde un archivo .dat.
     *
     * @param archivo La ruta del archivo .dat a cargar.
     * @return Un TreeMap con los Alfabetos cargados desde el archivo.
     */
    private static TreeMap<String, String> cargarDatos(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            @SuppressWarnings("unchecked")
            TreeMap<String, String> AlfabetosCargados = (TreeMap<String, String>) ois.readObject();
            System.out.println("Alfabetos cargados con éxito desde " + archivo);
            return AlfabetosCargados;
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
    private static void guardarDatos(TreeMap<String, String> mapDatos, String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(mapDatos);
            System.out.println("Objeto guardado con éxito en " + archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Guarda un Alfabeto en el archivo "alfabetos.dat".
     *
     * @param idAlf Identificador del Alfabeto.
     * @param alf Contenido del Alfabeto a guardar.
     */
    public void guardarAlfabeto(String idAlf, String alf) {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se obtienen los Alfabetos guardados
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/alfabetos.dat";
        File ficheroAlfabetos = new File(path);

        TreeMap<String, String> mapDatos;
        if (ficheroAlfabetos.exists()) mapDatos = cargarDatos(path);
        else mapDatos = new TreeMap<>(); // Si no existe Alfabetos.dat --> es el primer Alfabeto que se guarda --> se creará al hacer el write

        mapDatos.put(idAlf, alf);
        guardarDatos(mapDatos, path);
    }

    /**
     * Carga todos los Alfabetos del usuario actual.
     *
     * @return Un HashMap con los identificadores y contenidos de los Alfabetos del usuario.
     */
    public HashMap<String, String> cargarAlfabetos() {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se cargan los objetos en un TreeMap
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/alfabetos.dat";

        // Verificar si el archivo existe antes de intentar cargarlo
        File file = new File(path);
        if (!file.exists()) {
            // Devolver un HashMap vacío si el archivo no existe
            return new HashMap<>();
        }

        TreeMap<String, String> mapDatos = cargarDatos(path);

        // Se convierte el TreeMap a HashMap
        HashMap<String, String> Alfabetos = new HashMap<>();
        Alfabetos.putAll(mapDatos);
        return Alfabetos;
    }

    /**
     * Borra un Alfabeto del archivo "alfabetos.dat" según su identificador.
     *
     * @param idAlf Identificador del Alfabeto a borrar.
     */
    public void borrarAlfabeto(String idAlf) {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se obtienen los Alfabetos guardados
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/alfabetos.dat";
        TreeMap<String, String> mapDatos = cargarDatos(path);

        // Se elimina el Alfabeto y se vuelve a guardar en el fichero
        mapDatos.remove(idAlf);
        guardarDatos(mapDatos, path);
    }
}
