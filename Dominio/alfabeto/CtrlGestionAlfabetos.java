package edu.upc.prop.clusterxx.Dominio.alfabeto;

import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Set;

import edu.upc.prop.clusterxx.Persistencia.GestionPersistenciaAlfabeto;

/**
 * La clase CtrlGestionAlfabetos proporciona una interfaz para gestionar operaciones relacionadas con alfabetos.
 * Se encarga de interactuar con las clases de dominio relacionadas y la persistencia de alfabetos.
 */
public class CtrlGestionAlfabetos {

    private GestionAlfabetos gestorAlfabetos;
    private static CtrlGestionAlfabetos instance;
    private GestionPersistenciaAlfabeto gestorPersistenciaAlfabeto = new GestionPersistenciaAlfabeto();

    /**
     * Constructor privado que inicializa la instancia de CtrlGestionAlfabetos.
     */
    private CtrlGestionAlfabetos() {
        gestorAlfabetos = new GestionAlfabetos();
    }

    /**
     * Obtiene la instancia única de CtrlGestionAlfabetos (patrón Singleton).
     * @return La instancia única de CtrlGestionAlfabetos.
     */
    public synchronized static CtrlGestionAlfabetos getInstance() {
        if(instance == null) {
            instance = new CtrlGestionAlfabetos();
        }
        return instance;
    }

    // Restringir la clonación para cumplir con el patrón Singleton
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    /**
     * Reinicia el estado del controlador de gestión de alfabetos.
     */
    public void reiniciarEstado() {
        gestorAlfabetos = new GestionAlfabetos();
    }

    /**
     * Carga los alfabetos almacenados persistentemente.
     */
    public void cargarAlfabetos() {
        HashMap<String, String> alfabetosCargados = gestorPersistenciaAlfabeto.cargarAlfabetos();
        gestorAlfabetos.cargarAlfabetos(alfabetosCargados);
    }

    // Consultoras

    /**
     * Consulta el alfabeto asociado a un identificador.
     * @param id El identificador del alfabeto.
     * @return La representación en cadena del alfabeto asociado al identificador.
     */
    public String consultarAlfabeto(String id) {
        return gestorAlfabetos.consultarAlfabeto(id);
    }

    /**
     * Verifica si existe un alfabeto dado su identificador.
     * @param id El identificador del alfabeto.
     * @return true si el alfabeto existe, false en caso contrario.
     */
    public boolean existeAlfabeto(String id) {
        return gestorAlfabetos.existe_alfabeto(id);
    }

    // Modificadoras

    /**
     * Convierte un texto dado a un alfabeto específico.
     * @param texto El texto a convertir.
     * @param idAlf El identificador del alfabeto al que se desea convertir el texto.
     * @return El texto convertido al alfabeto especificado.
     */
    public String convertirAAlfabeto(String texto, String idAlf) {
        return gestorAlfabetos.convertir_a_Alfabeto(texto, idAlf);
    }

    /**
     * Añade un nuevo alfabeto al sistema.
     * @param id El identificador del nuevo alfabeto.
     * @param letras Las letras que forman el alfabeto.
     */
    public void añadirAlfabeto(String id, String letras) {    
        gestorAlfabetos.añadirAlfabeto(id, letras);
        gestorPersistenciaAlfabeto.guardarAlfabeto(id, letras);
    }

    /**
     * Borra un alfabeto del sistema.
     * @param id El identificador del alfabeto a borrar.
     */
    public void borrarAlfabeto(String id) {
       gestorAlfabetos.borrarAlfabeto(id);
       gestorPersistenciaAlfabeto.borrarAlfabeto(id);
    }

    /**
     * Modifica un alfabeto existente.
     * @param id El identificador del alfabeto a modificar.
     * @param letras Las nuevas letras que formarán el alfabeto.
     * @param opcion Opción que indica el tipo de modificación.
     * @return 1 si la modificación fue exitosa, -1 si hubo algún error.
     */
    public int modificarAlfabeto(String id, String letras, int opcion) {
        int resultado = gestorAlfabetos.modificarAlfabeto(id, letras, opcion);
        gestorPersistenciaAlfabeto.borrarAlfabeto(id);
        gestorPersistenciaAlfabeto.guardarAlfabeto(id, letras);
        return resultado;
    } 

    /**
     * Elimina todos los alfabetos del sistema.
     */
    public void clear() {
        gestorAlfabetos.clear();
    }

    // Escritura

    /**
     * Obtiene un listado de alfabetos del sistema.
     * @return Un mapa que asocia identificadores con objetos Alfabeto.
     */
    public HashMap<String, Alfabeto> getListadoAlfabetos() {
        return gestorAlfabetos.getlistadoAlfabetos();
    }

    // Lectura

    /**
     * Lee un conjunto de entradas de alfabetos desde un archivo.
     * @param rutaArchivo La ruta del archivo desde el cual leer.
     * @return Un conjunto de entradas (identificador, letras) leídas desde el archivo.
     * @throws IOException Si ocurre un error de entrada/salida.
     * @throws IllegalStateException Si el estado del sistema no permite la operación.
     */
    public Set<SimpleEntry<String, String>> leerDesdeArchivo(String rutaArchivo) throws IOException, IllegalStateException {
        return gestorAlfabetos.leerDesdeArchivo(rutaArchivo);
    }
}
