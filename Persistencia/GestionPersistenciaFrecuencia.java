package edu.upc.prop.clusterxx.Persistencia;

import java.io.*;
import java.util.*;

import edu.upc.prop.clusterxx.Dominio.usuario.CtrlGestionUsuarios;

/**
 * Esta clase proporciona métodos para gestionar la persistencia de frecuencias,
 * incluyendo la carga y guardado desde/hacia archivos y operaciones de manipulación.
 */
public class GestionPersistenciaFrecuencia {

    /**
     * Carga un TreeMap de Frecuencias desde un archivo .dat.
     *
     * @param archivo La ruta del archivo .dat a cargar.
     * @return Un TreeMap con las Frecuencias cargadas desde el archivo.
     */
    private static TreeMap<String, HashMap<String, Integer>> cargarDatos(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            @SuppressWarnings("unchecked")
            TreeMap<String, HashMap<String, Integer>> FrecuenciasCargados = (TreeMap<String, HashMap<String, Integer>>) ois.readObject();
            System.out.println("Frecuencias cargadas con éxito desde " + archivo);
            return FrecuenciasCargados;
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
    private static void guardarDatos(TreeMap<String, HashMap<String, Integer>> mapDatos, String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(mapDatos);
            System.out.println("Objeto guardado con éxito en " + archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Guarda una lista de usuarios asociada a una frecuencia en el archivo "listasUsr.dat".
     *
     * @param idFrec Identificador de la frecuencia.
     * @param listaUsr HashMap que contiene las frecuencias de cada usuario.
     */
    public void guardarListaUsuario(String idFrec, HashMap<String, Integer> listaUsr) {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se obtienen las Frecuencias guardadas
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/listasUsr.dat";
        File ficheroListas = new File(path);

        TreeMap<String, HashMap<String, Integer>> mapDatos;
        if (ficheroListas.exists()) mapDatos = cargarDatos(path);
        else mapDatos = new TreeMap<>(); // Si no existe listasUsr.dat --> es la primera lista que se guarda --> se creará al hacer el write

        mapDatos.put(idFrec, listaUsr);
        guardarDatos(mapDatos, path);
    }

    /**
     * Carga todas las listas de usuarios asociadas a frecuencias del usuario actual.
     *
     * @return Un HashMap con los identificadores y listas de usuarios asociadas a frecuencias.
     */
    public HashMap<String, HashMap<String, Integer>> cargarListasUsr() {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se cargan los objetos en un TreeMap
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/listasUsr.dat";

        // Verificar si el archivo existe antes de intentar cargarlo
        File file = new File(path);
        if (!file.exists()) {
            // Devolver un HashMap vacío si el archivo no existe
            return new HashMap<>();
        }

        TreeMap<String, HashMap<String, Integer>> mapDatos = cargarDatos(path);

        // Se convierte el TreeMap a HashMap
        HashMap<String, HashMap<String, Integer>> listasUsr = new HashMap<>();
        listasUsr.putAll(mapDatos);
        return listasUsr;
    }

    /**
     * Borra una frecuencia del archivo "frecuencias.dat" según su identificador.
     *
     * @param idFrec Identificador de la frecuencia a borrar.
     */
    public void borrarFrecuencia(String idFrec) {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se obtienen las Frecuencias guardadas
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/frecuencias.dat";
        TreeMap<String, HashMap<String, Integer>> mapDatos = cargarDatos(path);

        // Se elimina la Frecuencia y se vuelve a guardar en el fichero
        mapDatos.remove(idFrec);
        guardarDatos(mapDatos, path);
    }

    /**
     * Borra una lista de usuarios asociada a una frecuencia del archivo "listasUsr.dat" según su identificador.
     *
     * @param idFrec Identificador de la frecuencia a la que pertenece la lista de usuarios.
     */
    public void borrarListaUsr(String idFrec) {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se obtienen las Frecuencias guardadas
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/listasUsr.dat";
        TreeMap<String, HashMap<String, Integer>> mapDatos = cargarDatos(path);

        // Se elimina la lista de usuarios asociada a la Frecuencia y se vuelve a guardar en el fichero
        mapDatos.remove(idFrec);
        guardarDatos(mapDatos, path);
    }
}
