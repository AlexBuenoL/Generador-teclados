package edu.upc.prop.clusterxx.Persistencia;

import java.io.*;
import java.util.*;

import edu.upc.prop.clusterxx.Dominio.usuario.CtrlGestionUsuarios;

/**
 * Esta clase proporciona métodos para gestionar la persistencia de textos,
 * incluyendo la carga y guardado desde/hacia archivos y operaciones de manipulación.
 */
public class GestionPersistenciaTextos {

    /**
     * Carga un TreeMap de textos desde un archivo .dat.
     *
     * @param archivo La ruta del archivo .dat a cargar.
     * @return Un TreeMap con los textos cargados desde el archivo.
     */
    private static TreeMap<String, String> cargarDatosTexto(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            @SuppressWarnings("unchecked")
            TreeMap<String, String> TextosCargados = (TreeMap<String, String>) ois.readObject();
            System.out.println("Textos cargados con éxito desde " + archivo);
            return TextosCargados;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new TreeMap<>(); // Devolver un TreeMap vacío en caso de error
        }
    }

    /**
     * Carga un TreeMap de datos (alfabetos o frecuencias) desde un archivo .dat.
     *
     * @param archivo La ruta del archivo .dat a cargar.
     * @return Un TreeMap con los datos cargados desde el archivo.
     */
    private static TreeMap<String, String> cargarDatosAlfFre(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            @SuppressWarnings("unchecked")
            TreeMap<String, String> DatosCargados = (TreeMap<String, String>) ois.readObject();
            System.out.println("Datos cargados con éxito desde " + archivo);
            return DatosCargados;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new TreeMap<>(); // Devolver un TreeMap vacío en caso de error
        }
    }

    /**
     * Guarda un TreeMap de textos en un archivo .dat.
     *
     * @param mapDatos El TreeMap de textos a guardar.
     * @param archivo La ruta del archivo .dat donde se guardarán los textos.
     */
    private static void guardarDatosTexto(TreeMap<String, String> mapDatos, String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(mapDatos);
            System.out.println("Objeto guardado con éxito en " + archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Guarda un TreeMap de datos (alfabetos o frecuencias) en un archivo .dat.
     *
     * @param mapDatos El TreeMap de datos a guardar.
     * @param archivo La ruta del archivo .dat donde se guardarán los datos.
     */
    private static void guardarDatosAlfFre(TreeMap<String, String> mapDatos, String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(mapDatos);
            System.out.println("Objeto guardado con éxito en " + archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Guarda un texto con un identificador en el archivo "textos.dat".
     *
     * @param idTex Identificador del texto.
     * @param tex Contenido del texto.
     */
    public void guardarTexto(String idTex, String tex) {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se obtienen los textos guardados
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/textos.dat";
        File ficheroTextos = new File(path);

        TreeMap<String, String> mapDatos;
        if (ficheroTextos.exists()) mapDatos = cargarDatosTexto(path); // Si ya existe textos.dat, se cargan los datos en el TreeMap
        else mapDatos = new TreeMap<>(); // Si no existe textos.dat --> es el primer texto que se guarda --> se creará al hacer el write

        mapDatos.put(idTex, tex);
        guardarDatosTexto(mapDatos, path);
    }

    /**
     * Guarda la relación entre un texto y un alfabeto en el archivo "alfText.dat".
     *
     * @param idText Identificador del texto.
     * @param idAlf Identificador del alfabeto asociado al texto.
     */
    public void guardarAlfabetoTexto(String idText, String idAlf) {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se obtienen los textos guardados
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/alfText.dat";
        File ficheroAlfText = new File(path);

        TreeMap<String, String> mapDatos;
        if (ficheroAlfText.exists()) mapDatos = cargarDatosAlfFre(path); // Si ya existe alfText.dat, se cargan los datos en el TreeMap
        else mapDatos = new TreeMap<>(); // Si no existe alfText.dat --> es la primera relación que se guarda --> se creará al hacer el write

        mapDatos.put(idText, idAlf);
        guardarDatosAlfFre(mapDatos, path);
    }

    /**
     * Guarda la relación entre un texto y una frecuencia en el archivo "FreText.dat".
     *
     * @param idText Identificador del texto.
     * @param idFre Identificador de la frecuencia asociada al texto.
     */
    public void guardarFrecuenciaTexto(String idText, String idFre) {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se obtienen los textos guardados
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/FreText.dat";
        File ficheroFreText = new File(path);

        TreeMap<String, String> mapDatos;
        if (ficheroFreText.exists()) mapDatos = cargarDatosAlfFre(path); // Si ya existe FreText.dat, se cargan los datos en el TreeMap
        else mapDatos = new TreeMap<>(); // Si no existe FreText.dat --> es la primera relación que se guarda --> se creará al hacer el write

        mapDatos.put(idText, idFre);
        guardarDatosAlfFre(mapDatos, path);
    }

    /**
     * Carga todos los textos del usuario actual.
     *
     * @return Un HashMap con los identificadores y contenidos de los textos del usuario.
     */
    public HashMap<String, String> cargarTextos() {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se cargan los objetos en un TreeMap
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/textos.dat";

        // Verificar si el archivo existe antes de intentar cargarlo
        File file = new File(path);
        if (!file.exists()) {
            // Devolver un HashMap vacío si el archivo no existe
            return new HashMap<>();
        }

        TreeMap<String, String> mapDatos = cargarDatosTexto(path);

        // Se convierte el TreeMap a HashMap
        HashMap<String, String> Textos = new HashMap<>();
        Textos.putAll(mapDatos);
        return Textos;
    }

    /**
     * Carga todas las relaciones entre textos y alfabetos del usuario actual.
     *
     * @return Un HashMap con los identificadores de textos y alfabetos asociados del usuario.
     */
    public HashMap<String, String> cargarAlfabetoTexto() {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se cargan los objetos en un TreeMap
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/alfText.dat";

        // Verificar si el archivo existe antes de intentar cargarlo
        File file = new File(path);
        if (!file.exists()) {
            // Devolver un HashMap vacío si el archivo no existe
            return new HashMap<>();
        }

        TreeMap<String, String> mapDatos = cargarDatosAlfFre(path);

        // Se convierte el TreeMap a HashMap
        HashMap<String, String> AlfTextos = new HashMap<>();
        AlfTextos.putAll(mapDatos);
        return AlfTextos;
    }

    /**
     * Carga todas las relaciones entre textos y frecuencias del usuario actual.
     *
     * @return Un HashMap con los identificadores de textos y frecuencias asociadas del usuario.
     */
    public HashMap<String, String> cargarFrecuenciaTexto() {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se cargan los objetos en un TreeMap
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/FreText.dat";

        // Verificar si el archivo existe antes de intentar cargarlo
        File file = new File(path);
        if (!file.exists()) {
            // Devolver un HashMap vacío si el archivo no existe
            return new HashMap<>();
        }

        TreeMap<String, String> mapDatos = cargarDatosAlfFre(path);

        // Se convierte el TreeMap a HashMap
        HashMap<String, String> FreTextos = new HashMap<>();
        FreTextos.putAll(mapDatos);
        return FreTextos;
    }

    /**
     * Borra un texto del archivo "textos.dat" según su identificador.
     *
     * @param idTex Identificador del texto a borrar.
     */
    public void borrarTexto(String idTex) {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se obtienen los textos guardados
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/textos.dat";
        TreeMap<String, String> mapDatos = cargarDatosTexto(path);

        // Se elimina el texto y se vuelve a guardar en el fichero
        mapDatos.remove(idTex);
        guardarDatosTexto(mapDatos, path);
    }

    /**
     * Borra la relación entre un texto y un alfabeto del archivo "alfText.dat" según su identificador de texto.
     *
     * @param idTex Identificador del texto cuya relación con el alfabeto se desea borrar.
     */
    public void borrarAlfabetoTexto(String idTex) {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se obtienen los textos guardados
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/alfText.dat";
        TreeMap<String, String> mapDatos = cargarDatosAlfFre(path);

        // Se elimina la relación entre el texto y el alfabeto y se vuelve a guardar en el fichero
        mapDatos.remove(idTex);
        guardarDatosAlfFre(mapDatos, path);
    }

    /**
     * Borra la relación entre un texto y una frecuencia del archivo "FreText.dat" según su identificador de texto.
     *
     * @param idTex Identificador del texto cuya relación con la frecuencia se desea borrar.
     */
    public void borrarFrecuenciaTexto(String idTex) {
        CtrlGestionUsuarios ctrlGestorUsuarios = CtrlGestionUsuarios.getInstance();
        String usr = ctrlGestorUsuarios.getUsuarioActual();
        String nom_usr = ctrlGestorUsuarios.consultarNombreUsuario(usr);

        // Se obtienen los textos guardados
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom_usr + "/FreText.dat";
        TreeMap<String, String> mapDatos = cargarDatosAlfFre(path);

        // Se elimina la relación entre el texto y la frecuencia y se vuelve a guardar en el fichero
        mapDatos.remove(idTex);
        guardarDatosAlfFre(mapDatos, path);
    }
}
