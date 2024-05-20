package edu.upc.prop.clusterxx.Dominio.Frecuencia;

import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Set;

import edu.upc.prop.clusterxx.Persistencia.GestionPersistenciaFrecuencia;

/**
 * Clase que actúa como controlador para la gestión de frecuencias de palabras.
 */
public class CtrlGestionFrecuencias {

    private GestionFrecuencias gestorFrecuencias;
    private static CtrlGestionFrecuencias instance;
    private GestionPersistenciaFrecuencia gestorPersistenciaFrecuencia = new GestionPersistenciaFrecuencia();

    /**
     * Constructor privado para implementar el patrón Singleton.
     */
    private CtrlGestionFrecuencias() {
        gestorFrecuencias = new GestionFrecuencias();
    }

    /**
     * Método que devuelve la única instancia de la clase CtrlGestionFrecuencias (Singleton).
     *
     * @return Instancia única de CtrlGestionFrecuencias.
     */
    public synchronized static CtrlGestionFrecuencias getInstance() {
        if (instance == null) {
            instance = new CtrlGestionFrecuencias();
        }
        return instance;
    }

    /**
     * Método de seguridad para evitar la clonación de la instancia (Singleton).
     *
     * @return No debería devolver nada ya que lanza una excepción.
     * @throws CloneNotSupportedException Si se intenta clonar la instancia.
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    /**
     * Reinicia el estado del gestor de frecuencias.
     */
    public void reiniciarEstado() {
        gestorFrecuencias = new GestionFrecuencias();
    }

    /**
     * Carga las frecuencias almacenadas en la persistencia.
     */
    public void cargarFrecuencias() {
        HashMap<String, HashMap<String, Integer>> listadoFrecuenciasCargadas = gestorPersistenciaFrecuencia.cargarListasUsr();
        gestorFrecuencias.cargarFrecuencias(listadoFrecuenciasCargadas);
    }

    // Consultoras

    /**
     * Verifica si existe un listado de frecuencias con el ID proporcionado.
     *
     * @param id ID del listado de frecuencias.
     * @return true si existe, false si no.
     */
    public boolean existeFrecuencia(String id) {
        return gestorFrecuencias.existeFrecuencia(id);
    }

    /**
     * Comprueba si no hay ningún listado de frecuencias en el sistema.
     *
     * @return true si está vacío, false si no.
     */
    public boolean isEmpty() {
        return gestorFrecuencias.isEmpty();
    }

    /**
     * Comprueba si un listado de frecuencias identificado por su ID está vacío.
     *
     * @param id ID del listado de frecuencias.
     * @return true si está vacío, false si no.
     */
    public boolean freqIsEmpty(String id) {
        return gestorFrecuencias.freqIsEmpty(id);
    }

    /**
     * Obtiene la frecuencia de caracteres de un listado identificado por su ID.
     *
     * @param id ID del listado de frecuencias.
     * @return Frecuencia de caracteres.
     */
    public HashMap<String, Integer> getFrecuencia(String id) {
        return gestorFrecuencias.getFrecuencia(id);
    }

    /**
     * Obtiene la frecuencia de palabras de un listado identificado por su ID.
     *
     * @param id ID del listado de frecuencias.
     * @return Frecuencia de palabras.
     */
    public HashMap<String, Integer> getListadoFrecuencias(String id) {
        return gestorFrecuencias.getListadoFrecuencias(id);
    }

    /**
     * Obtiene todos los listados de frecuencias.
     *
     * @return Mapa con todos los listados de frecuencias.
     */
    public HashMap<String, Frecuencia> getTodosListadosFrecuencias() {
        return gestorFrecuencias.getTodosListadosFrecuencias();
    }

    // Modificadoras

    /**
     * Obtiene la frecuencia de palabras a partir de un texto y la guarda en un listado identificado por su ID.
     *
     * @param id  ID del listado de frecuencias.
     * @param txt Texto del cual se obtiene la frecuencia de palabras.
     */
    public void obtenerFrecuenciaConTexto(String id, String txt) {
        gestorFrecuencias.obtenerFrecuenciaConTexto(id, txt);
        gestorPersistenciaFrecuencia.guardarListaUsuario(id, gestorFrecuencias.getListadoFrecuencias(id));
    }

    /**
     * Añade un listado de frecuencias identificado por su ID.
     *
     * @param id    ID del listado de frecuencias.
     * @param pairs Conjunto de pares (palabra, número de veces) a añadir.
     */
    public void añadirListadoFrecuencias(String id, Set<SimpleEntry<String, Integer>> pairs) {
        gestorFrecuencias.añadirListadoFrecuencias(id, pairs);
        gestorPersistenciaFrecuencia.guardarListaUsuario(id, gestorFrecuencias.getListadoFrecuencias(id));
    }

    /**
     * Borra un listado de frecuencias identificado por su ID.
     *
     * @param id ID del listado de frecuencias a borrar.
     */
    public void borrarListadoFrecuencias(String id) {
        gestorFrecuencias.borrarListadoFrecuencias(id);
        gestorPersistenciaFrecuencia.borrarFrecuencia(id);
        gestorPersistenciaFrecuencia.borrarListaUsr(id);
    }

    /**
     * Añade un conjunto de frecuencias a un listado identificado por su ID.
     *
     * @param id    ID del listado de frecuencias.
     * @param pairs Conjunto de pares (palabra, número de veces) a añadir.
     */
    public void añadirFrecuenciasListado(String id, Set<SimpleEntry<String, Integer>> pairs) {
        gestorPersistenciaFrecuencia.borrarFrecuencia(id);
        gestorPersistenciaFrecuencia.borrarListaUsr(id);

        gestorFrecuencias.añadirFrecuenciasListado(id, pairs);
        gestorPersistenciaFrecuencia.guardarListaUsuario(id, gestorFrecuencias.getListadoFrecuencias(id));
    }

    /**
     * Elimina un conjunto de palabras de un listado identificado por su ID.
     *
     * @param id       ID del listado de frecuencias.
     * @param palabras Conjunto de palabras a eliminar.
     */
    public void eliminarFrecuenciasListado(String id, Set<String> palabras) {
        gestorFrecuencias.eliminarFrecuenciasListado(id, palabras);

        gestorPersistenciaFrecuencia.borrarFrecuencia(id);
        gestorPersistenciaFrecuencia.borrarListaUsr(id);

        if (existeFrecuencia(id)) gestorPersistenciaFrecuencia.guardarListaUsuario(id, gestorFrecuencias.getListadoFrecuencias(id));
    }

    /**
     * Elimina todos los listados de frecuencias y limpia la gestión de frecuencias.
     */
    public void clear() {
        gestorFrecuencias.clear();
    }

    /**
     * Lee un conjunto de frecuencias desde un archivo.
     *
     * @param rutaArchivo Ruta del archivo desde el cual cargar las frecuencias.
     * @return Conjunto de pares (ID, frecuencias) cargadas desde el archivo.
     * @throws IOException            Si hay un error al leer el archivo.
     * @throws IllegalStateException Si hay un formato incorrecto en el archivo.
     */
    public Set<SimpleEntry<String, Set<SimpleEntry<String, Integer>>>> leerDesdeArchivo(String rutaArchivo) throws IOException, IllegalStateException {
        return gestorFrecuencias.cargarFrecuenciaDesdeArchivo(rutaArchivo);
    }
}
