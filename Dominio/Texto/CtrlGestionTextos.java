package edu.upc.prop.clusterxx.Dominio.Texto;

import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Set;

import edu.upc.prop.clusterxx.Dominio.Frecuencia.CtrlGestionFrecuencias;
import edu.upc.prop.clusterxx.Dominio.alfabeto.CtrlGestionAlfabetos;

import edu.upc.prop.clusterxx.Persistencia.GestionPersistenciaTextos;
import edu.upc.prop.clusterxx.Persistencia.GestionPersistenciaAlfabeto;
import edu.upc.prop.clusterxx.Persistencia.GestionPersistenciaFrecuencia;

/**
 * La clase CtrlGestionTextos proporciona métodos para gestionar textos, incluyendo la carga, consulta,
 * adición, borrado y modificación de textos en el sistema. Además, facilita la interacción entre las clases
 * de dominio y persistencia relacionadas con los textos.
 */

public class CtrlGestionTextos {
    private GestionTextos gestorTextos;

    private static CtrlGestionTextos instance;

    private GestionPersistenciaTextos gestorPersistenciaTextos = new GestionPersistenciaTextos();
    private GestionPersistenciaAlfabeto gestorPersistenciaAlfabeto = new GestionPersistenciaAlfabeto();
    private GestionPersistenciaFrecuencia gestorPersistenciaFrecuencia = new GestionPersistenciaFrecuencia();

    //constructora
     /**
     * Crea una nueva instancia de CtrlGestionTextos con un gestor de textos inicializado.
     * La instancia es privada para seguir el patrón de diseño Singleton.
     */
    private CtrlGestionTextos() {
        gestorTextos = new GestionTextos();
    } 

    /**
     * Obtiene la instancia única de CtrlGestionTextos utilizando el patrón de diseño Singleton.
     * @return La única instancia de CtrlGestionTextos.
     */
    public synchronized static CtrlGestionTextos getInstance() {
        if(instance == null) {
            instance = new CtrlGestionTextos();
        }
        return instance;
    }

    /**
     * Método de prevención para evitar la clonación de la instancia.
     * @return No retorna ningún valor.
     * @throws CloneNotSupportedException Si se intenta clonar la instancia.
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

     /**
     * Reinicia el estado del gestor de textos, creando un nuevo objeto GestionTextos.
     */
    public void reinicarEstado() {
        gestorTextos = new GestionTextos();
    }

    /**
     * Carga los textos, alfabetos y frecuencias desde la persistencia al gestor de textos.
     */
    public void cargarTextos() {
        HashMap<String,String> textosCargados = gestorPersistenciaTextos.cargarTextos();
        HashMap<String,String> alfTextCargados = gestorPersistenciaTextos.cargarAlfabetoTexto();
        HashMap<String,String> freTextCargados = gestorPersistenciaTextos.cargarFrecuenciaTexto();
        gestorTextos.cargarTextos(textosCargados, alfTextCargados, freTextCargados);
    }

    //consultoras
    /**
     * Verifica la existencia de un texto en el sistema.
     * @param id El identificador del texto a verificar.
     * @return true si el texto existe, false en caso contrario.
     */
    public boolean existe_Texto(String id) {
        return gestorTextos.existe_Texto(id);
    }

    /**
     * Consulta el contenido de un texto específico por su identificador.
     * @param id El identificador del texto a consultar.
     * @return Una cadena que representa el contenido del texto.
     */
    public String consultarTexto(String id) {
        return gestorTextos.consultarTexto(id);
    }

    /**
     * Consulta el identificador del alfabeto asociado a un texto.
     * @param idTexto El identificador del texto.
     * @return El identificador del alfabeto asociado al texto.
     */
    public String getIdAlfabetoTexto(String idTexto) {
        return gestorTextos.getIdAlfabetoTexto(idTexto);
    }

     /**
     * Consulta el identificador de la frecuencia asociada a un texto.
     * @param idTexto El identificador del texto.
     * @return El identificador de la frecuencia asociada al texto.
     */
    public String getIdFrecuenciaTexto(String idTexto) {
        return gestorTextos.getIdFrecuenciaTexto(idTexto);
    } 

     /**
     * Consulta el identificador del texto asociado a un alfabeto.
     * @param idAlf El identificador del alfabeto.
     * @return El identificador del texto asociado al alfabeto.
     */
    public String getIdTextoAlfabeto(String idAlf) {
        return gestorTextos.getIdTextoAlfabeto(idAlf);
    }

    /**
     * Consulta el identificador del texto asociado a una frecuencia.
     * @param idFre El identificador de la frecuencia.
     * @return El identificador del texto asociado a la frecuencia.
     */
    public String getIdTextoFrec(String idFre) {
        return gestorTextos.getIdTextoFrec(idFre);
    }

        /**
     * Verifica la existencia de un alfabeto asociado a un texto.
     * @param idTexto El identificador del texto.
     * @return true si existe un alfabeto asociado al texto, false en caso contrario.
     */
    public boolean existe_AlfabetoTexto(String idTexto) {
        return gestorTextos.existe_AlfabetoTexto(idTexto);
    }

    /**
     * Verifica la existencia de una frecuencia asociada a un texto.
     * @param idTexto El identificador del texto.
     * @return true si existe una frecuencia asociada al texto, false en caso contrario.
     */
    public boolean existeFrecuenciaTexto(String idTexto) {
        return gestorTextos.existeFrecuenciaTexto(idTexto);
    }

    //modificadoras
    /**
     * Elimina todos los textos del sistema.
     */
    public void clear() {
        gestorTextos.clear();
    }

    /**
     * Añade un nuevo texto al sistema, con su correspondiente alfabeto y frecuencia.
     * @param id El identificador del nuevo texto.
     * @param texto El contenido del nuevo texto.
     * @param idAlf El identificador del alfabeto asociado al texto.
     * @param idFre El identificador de la frecuencia asociada al texto.
     */
    public void añadirTexto(String id, String texto, String idAlf, String idFre) { 
        CtrlGestionAlfabetos ctrlGestorAlfabetos = CtrlGestionAlfabetos.getInstance();
        CtrlGestionFrecuencias ctrlGestorFrecuencias = CtrlGestionFrecuencias.getInstance();
        String newAlf = convertirTextoAAlfabeto(ctrlGestorAlfabetos, idAlf, texto);
        convertirTextoAFrecuencia(ctrlGestorFrecuencias, idFre, texto);
        gestorTextos.añadirTexto(id, texto, idAlf, idFre);
        gestorPersistenciaTextos.guardarTexto(id, gestorTextos.consultarTexto(id));
        gestorPersistenciaTextos.guardarAlfabetoTexto(id, idAlf);
        gestorPersistenciaTextos.guardarFrecuenciaTexto(id, idFre);
        gestorPersistenciaAlfabeto.guardarAlfabeto(idAlf, newAlf);
        gestorPersistenciaFrecuencia.guardarListaUsuario(idFre, ctrlGestorFrecuencias.getListadoFrecuencias(idFre)); //palabras
    }

      /**
     * Elimina un texto del sistema, junto con su alfabeto y frecuencia asociados.
     * @param id El identificador del texto a borrar.
     */
    public void borrarTexto(String id) { 
        CtrlGestionAlfabetos ctrlGestorAlfabetos = CtrlGestionAlfabetos.getInstance();
        CtrlGestionFrecuencias ctrlGestorFrecuencias = CtrlGestionFrecuencias.getInstance();
        String idAlf = gestorTextos.getIdAlfabetoTexto(id);
        String idFre = gestorTextos.getIdFrecuenciaTexto(id);
        if(ctrlGestorAlfabetos.existeAlfabeto(idAlf)) ctrlGestorAlfabetos.borrarAlfabeto(idAlf);
        if(ctrlGestorFrecuencias.existeFrecuencia(idFre)) ctrlGestorFrecuencias.borrarListadoFrecuencias(idFre);
        gestorTextos.borrarTexto(id);
        gestorPersistenciaTextos.borrarTexto(id);
        gestorPersistenciaTextos.borrarAlfabetoTexto(id);
        gestorPersistenciaTextos.borrarFrecuenciaTexto(id);
        if(ctrlGestorAlfabetos.existeAlfabeto(idAlf)) gestorPersistenciaAlfabeto.borrarAlfabeto(idAlf);
        if(ctrlGestorFrecuencias.existeFrecuencia(idFre)) gestorPersistenciaFrecuencia.borrarFrecuencia(idFre); //caracteres
        if(ctrlGestorFrecuencias.existeFrecuencia(idFre)) gestorPersistenciaFrecuencia.borrarListaUsr(idFre); //palabras
    }

     /**
     * Modifica el contenido de un texto en el sistema.
     * @param idTexto El identificador del texto a modificar.
     * @param texto El nuevo contenido del texto.
     * @return 1 si la modificación fue exitosa, 2 si el texto quedó sin palabras y fue eliminado,
     *         0 si no se pudo realizar la modificación.
     * @throws NoSuchElementException Si no se encuentra el texto a modificar.
     * @throws NullPointerException Si el nuevo texto está vacío.
     * @throws IllegalArgumentException Si la modificación deja el alfabeto asociado al texto vacío.
     * @throws IllegalStateException Si el estado del sistema no permite la operación.
     */

    public int modificarTexto(String idTexto, String texto) throws NoSuchElementException, NullPointerException, IllegalArgumentException, IllegalStateException  {
        CtrlGestionAlfabetos ctrlGestorAlfabetos = CtrlGestionAlfabetos.getInstance();
        CtrlGestionFrecuencias ctrlGestorFrecuencias = CtrlGestionFrecuencias.getInstance();
        String idAlf = gestorTextos.getIdAlfabetoTexto(idTexto);
        String idFre = gestorTextos.getIdFrecuenciaTexto(idTexto);
        String textNew = gestorTextos.modificarTexto(idTexto, texto);
        if(textNew.trim().isEmpty()) { //se ha quedado sin palabras
            borrarTexto(idTexto);
            gestorPersistenciaTextos.borrarTexto(idTexto);
            gestorPersistenciaTextos.borrarAlfabetoTexto(idTexto);
            gestorPersistenciaTextos.borrarFrecuenciaTexto(idTexto);
            gestorPersistenciaAlfabeto.borrarAlfabeto(idAlf);
            gestorPersistenciaFrecuencia.borrarFrecuencia(idFre);
            gestorPersistenciaFrecuencia.borrarListaUsr(idFre);
            return 2;
        }
        else {
            ctrlGestorAlfabetos.borrarAlfabeto(idAlf);
            ctrlGestorFrecuencias.borrarListadoFrecuencias(idFre);
            String newAlf = convertirTextoAAlfabeto(ctrlGestorAlfabetos, idAlf, textNew);
            convertirTextoAFrecuencia(ctrlGestorFrecuencias, idFre, textNew);
            gestorTextos.setIdAlfabetoTexto(idTexto, idAlf);
            gestorTextos.setIdFrecuenciaTexto(idTexto, idFre);
            gestorPersistenciaTextos.borrarTexto(idTexto);
            gestorPersistenciaTextos.borrarAlfabetoTexto(idTexto);
            gestorPersistenciaTextos.borrarFrecuenciaTexto(idTexto);
            gestorPersistenciaAlfabeto.borrarAlfabeto(idAlf);
            gestorPersistenciaFrecuencia.borrarFrecuencia(idFre);
            gestorPersistenciaFrecuencia.borrarListaUsr(idFre);
            gestorPersistenciaTextos.guardarTexto(idTexto, textNew);
            gestorPersistenciaTextos.guardarAlfabetoTexto(idTexto, idAlf);
            gestorPersistenciaTextos.guardarFrecuenciaTexto(idTexto, idFre);
            gestorPersistenciaAlfabeto.guardarAlfabeto(idAlf, newAlf);
            gestorPersistenciaFrecuencia.guardarListaUsuario(idFre, ctrlGestorFrecuencias.getListadoFrecuencias(idFre)); //palabras
            return 1;
        }
    }


    /**
     * Convierte un texto en un alfabeto y lo devuelve.
     * @param ctrlGestorAlfabetos El controlador de gestión de alfabetos.
     * @param idAlf El identificador del alfabeto asociado al texto.
     * @param texto El contenido del texto a convertir.
     * @return Una cadena que representa el nuevo alfabeto.
     */
    public String convertirTextoAAlfabeto(CtrlGestionAlfabetos ctrlGestorAlfabetos, String idAlf, String texto) {
        return ctrlGestorAlfabetos.convertirAAlfabeto(texto, idAlf);
    }

     /**
     * Convierte un texto en una frecuencia y la almacena.
     * @param ctrlGestorFrecuencias El controlador de gestión de frecuencias.
     * @param idFre El identificador de la frecuencia asociada al texto.
     * @param texto El contenido del texto a convertir.
     */
    public void convertirTextoAFrecuencia(CtrlGestionFrecuencias ctrlGestorFrecuencias, String idFre, String texto) {  
        ctrlGestorFrecuencias.obtenerFrecuenciaConTexto(idFre, texto);  
    }

    /**
     * Obtiene el listado de textos en el sistema.
     * @return Un mapa que asocia identificadores de texto con instancias de la clase Texto.
     */
    //escritura
    public HashMap<String,Texto> getlistadoTextos() {
        return gestorTextos.getlistadoTextos();
    }

     /**
     * Lee un conjunto de entradas de texto desde un archivo.
     * @param rutaArchivo La ruta del archivo desde el cual leer.
     * @return Un conjunto de entradas (identificador, texto) leídas desde el archivo.
     * @throws IOException Si ocurre un error de entrada/salida.
     * @throws IllegalAccessError Si se produce un error de acceso ilegal al leer desde el archivo.
     */
    public Set<SimpleEntry<String, String>> leerDesdeArchivo(String rutaArchivo) throws IOException, IllegalAccessError {
        return gestorTextos.leerDesdeArchivo(rutaArchivo);
    }
}

