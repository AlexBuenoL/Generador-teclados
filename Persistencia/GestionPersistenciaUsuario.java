package edu.upc.prop.clusterxx.Persistencia;

import java.io.*;
import java.util.HashMap;

import edu.upc.prop.clusterxx.Dominio.usuario.*;

/**
 * Esta clase proporciona métodos para gestionar la persistencia de usuarios,
 * incluyendo la creación, carga, y eliminación de usuarios y sus contraseñas.
 * Los datos se almacenan en archivos en el sistema de archivos.
 */
public class GestionPersistenciaUsuario {

    /**
     * Constructor por defecto de la clase.
     */
    public GestionPersistenciaUsuario() {}

    /**
     * Crea un nuevo usuario con el nombre proporcionado y guarda su contraseña en un archivo.
     *
     * @param nom Nombre del nuevo usuario.
     * @param contra Contraseña del nuevo usuario.
     */
    public void guardarUsuario(String nom, String contra) {
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom;
        File carpetaUsr = new File(path);

        if (!carpetaUsr.exists()) {

            if (carpetaUsr.mkdirs()) 
                System.out.println("Carpeta creada con éxito.");
            else 
                System.out.println("No se pudo crear la carpeta.");

            // Además de la carpeta del usuario, se crea un fichero dentro para guardar su contraseña
            String pathFicheroUsr = path + "/info_usr.txt";
            File info = new File(pathFicheroUsr);

            try {
                // Verifica si el fichero ya existe
                if (info.createNewFile()) 
                    System.out.println("Fichero creado con éxito.");
                else 
                    System.out.println("El fichero ya existe.");

            } catch (IOException e) {
                System.out.println("Error al crear el fichero: " + e.getMessage());
            }

            // En el fichero solo se guarda la contraseña
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathFicheroUsr))) {
                // Escribe el contenido en el fichero
                writer.write(contra);
                System.out.println("Contenido escrito en el fichero con éxito.");
            } catch (IOException e) {
                System.err.println("Error al escribir en el fichero: " + e.getMessage());
            }
        }
    }

    /**
     * Obtiene la contraseña del usuario con el nombre proporcionado.
     *
     * @param nom Nombre del usuario.
     * @return La contraseña del usuario o null si no se encuentra.
     */
    private String obtenerContra(String nom) {
        // Se obtiene el fichero donde está guardada la contraseña dentro de la carpeta del usuario
        String path = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom + "/info_usr.txt";
        String contra = null;

        // Se lee la contraseña 
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            contra = br.readLine();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        return contra;
    }

    /**
     * Carga todos los usuarios y sus contraseñas almacenados en el sistema de archivos.
     *
     * @return Un HashMap que asocia nombres de usuarios con instancias de la clase Usuario.
     */
    public HashMap<String, Usuario> cargarUsuarios() {
        String rutaCarpeta = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos";

        File carpeta = new File(rutaCarpeta);

        HashMap<String, Usuario> users = new HashMap<>();

        // Recorre la carpeta "datos" para obtener los usuarios guardados
        if (carpeta.isDirectory()) {
            File[] carpetas = carpeta.listFiles(File::isDirectory);

            if (carpetas != null) {
                for (File subcarpeta : carpetas) {
                    String nom = subcarpeta.getName();
                    String contra = obtenerContra(nom);
                    Usuario usr = new Usuario(contra);
                    users.put(nom, usr);
                }
            }
        } else {
            System.out.println("La ruta especificada no es una carpeta.");
        }

        return users;
    }

    /**
     * Elimina la carpeta del usuario y los archivos que contiene.
     *
     * @param carpeta La carpeta del usuario a eliminar.
     */
    private void eliminarCarpeta(File carpeta) {
        // Verifica si la carpeta existe
        if (carpeta.exists()) {
            // Obtiene la lista de archivos en el directorio
            File[] archivos = carpeta.listFiles();

            // Elimina cada archivo en la carpeta
            if (archivos != null) {
                for (File archivo : archivos) {
                    archivo.delete();
                    System.out.println("Archivo eliminado: " + archivo.getAbsolutePath());
                }
            }

            // Elimina la carpeta
            carpeta.delete();
            System.out.println("Carpeta eliminada: " + carpeta.getAbsolutePath());
        } else {
            System.out.println("La carpeta no existe: " + carpeta.getAbsolutePath());
        }
    }

    /**
     * Elimina un usuario y todos sus datos del sistema de archivos.
     *
     * @param nom Nombre del usuario a borrar.
     */
    public void borrarUsuario(String nom) {
        String rutaCarpeta = "src/main/java/edu/upc/prop/clusterxx/Persistencia/Datos/" + nom;
        File carpeta = new File(rutaCarpeta);
        eliminarCarpeta(carpeta);
    }
}
