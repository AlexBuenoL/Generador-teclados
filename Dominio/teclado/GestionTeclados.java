package edu.upc.prop.clusterxx.Dominio.teclado;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Clase gestora del conjunto de teclados de los usuarios
 */
public class GestionTeclados {

    // Atributos privados

    /**
     * Numero de teclados
     */
    private int numTeclados;

    /**
     * Clase gestora del conjunto de teclados de los usuarios
     */
    private HashMap<String,Teclado> teclados = new HashMap<String,Teclado>();

    /**
     * Constructora de la clase
     */
    public GestionTeclados() {
        numTeclados = 0;
    }

    /**
     * Convierte un mapa de matrices de caracteres en un mapa de objetos Teclado.
     * @param mapaAntiguo Mapa original de matrices de caracteres.
     * @return Nuevo mapa de objetos Teclado.
     */
    private HashMap<String, Teclado> convertirMapa(HashMap<String, char[][]> mapaAntiguo) {
        HashMap<String, Teclado> nuevoMapa = new HashMap<>();

        for (HashMap.Entry<String, char[][]> entrada : mapaAntiguo.entrySet()) {
            String nombreTeclado = entrada.getKey();
            char[][] teclas = entrada.getValue();;

            Teclado teclado = new Teclado(teclas);
            nuevoMapa.put(nombreTeclado, teclado);
        }

        return nuevoMapa;
    }

    /**
     * Carga teclados en la instancia actual a partir de un mapa de matrices de caracteres.
     * @param tecladosCargados Mapa de matrices de caracteres representando teclados.
     */
    public void cargarTeclados(HashMap<String,char[][]> tecladosCargados ) {
        teclados = convertirMapa(tecladosCargados);
    }

    /**
     * Obtiene el número de teclados en la gestión.
     * @return Número de teclados.
     */
    public int getNumTeclados() {
        return numTeclados;
    }

    /**
     * Verifica si un teclado con el identificador dado existe.
     * @param idTeclado Identificador del teclado.
     * @return `true` si el teclado existe, `false` en caso contrario.
     */
    public boolean existeTeclado(String idTeclado) {
        return teclados.containsKey(idTeclado);
    }

    /**
     * Obtiene un objeto Teclado con el identificador especificado.
     * @param id Identificador del teclado.
     * @return Objeto Teclado con el identificador proporcionado.
     * @throws NullPointerException Si el teclado con el identificador no existe.
     */
    public Teclado getTeclado(String id) throws NullPointerException {
        if (!teclados.containsKey(id)) throw new NullPointerException("El teclado con id " + id + " no existe.");
        else return teclados.get(id);
    }

    /**
     * Obtiene las teclas de un teclado con el identificador especificado.
     * @param id Identificador del teclado.
     * @return Matriz de caracteres que representa las teclas del teclado.
     * @throws NullPointerException Si el teclado con el identificador no existe.
     */
    public char[][] getTeclasTeclado(String id) throws NullPointerException {
        if (!teclados.containsKey(id)) throw new NullPointerException("El teclado con id " + id + " no existe.");
        else return teclados.get(id).getTeclas();
    }

    /**
     * Obtiene la lista de todos los teclados en la gestión.
     * @return HashMap que contiene todos los teclados.
     * @throws NoSuchElementException Si la lista de teclados está vacía.
     */
    public HashMap<String,Teclado> getListaTeclados() {
        if (teclados.isEmpty()) throw new NoSuchElementException("Lista de teclados vacía.");
        else return teclados;
    }

    /**
     * Obtiene la optimalidad de un teclado con el identificador especificado.
     * @param id Identificador del teclado.
     * @return Valor de optimalidad del teclado.
     */
    public double getOptimalidad(String id) {
        return teclados.get(id).getOptimalidad();
    }
    

    // Modificadoras

    /**
     * Genera un teclado con el identificador, algoritmo, alfabeto y frecuencia especificados.
     * @param idTec Identificador del teclado generado.
     * @param alg Algoritmo utilizado para la generación del teclado.
     * @param alf Alfabeto utilizado en la generación del teclado.
     * @param frec Frecuencia utilizada en la generación del teclado.
     * @return Matriz de caracteres que representa las teclas del teclado generado.
     * @throws IllegalArgumentException Si el teclado con el identificador ya existe.
     */
    public char[][] generarTeclado(String idTec, String alg, String alf, HashMap<String,Integer> frec) throws IllegalArgumentException {
        if (teclados.containsKey(idTec))
            throw new IllegalArgumentException("El teclado con id " + idTec + " ya existe");
        else {
            Teclado newTec = new Teclado(idTec);
            newTec.generarTeclado(alg,alf,frec);
            return newTec.getTeclas();
        }
    }

    
    /**
     * Guarda un teclado con el identificador y las teclas especificadas.
     * @param idTec Identificador del teclado a guardar.
     * @param teclas Matriz de caracteres que representa las teclas del teclado.
     */
    public void guardarTeclado(String idTec, char[][] teclas) {
        Teclado tec = new Teclado(teclas);
        teclados.put(idTec, tec);
        ++numTeclados;
    }

    /**
     * Modifica las teclas de un teclado con los caracteres especificados.
     * @param idTeclado Identificador del teclado a modificar.
     * @param x Uno de los caracteres a intercambiar
     * @param y Otro de los caracteres a intercambiar
     * @throws NoSuchElementException Si la lista de teclados está vacía.
     * @throws NullPointerException Si el teclado con el identificador no existe.
     */
    public void modificarTeclasTeclado(String idTeclado, char x, char y) throws NoSuchElementException, NullPointerException {

        if (teclados.isEmpty()) 
            throw new NoSuchElementException("La lista de teclados esta vacia.");

        else if (!teclados.containsKey(idTeclado)) 
            throw new NullPointerException("El teclado con id " + idTeclado + " no existe.");

        else 
            teclados.get(idTeclado).modificarTeclasTeclado(x, y); 
    }

    /**
     * Borra un teclado con el identificador especificado.
     * @param idTeclado Identificador del teclado a borrar.
     * @throws NoSuchElementException Si la lista de teclados está vacía.
     * @throws NullPointerException Si el teclado con el identificador no existe.
     */
    public void borrarTeclado(String idTeclado) throws NoSuchElementException, NullPointerException {

         if (teclados.isEmpty()) 
            throw new NoSuchElementException("La lista de teclados esta vacia.");

        else if (!teclados.containsKey(idTeclado)) 
            throw new NullPointerException("El teclado con id " + idTeclado + " no existe.");

        else 
            teclados.remove(idTeclado);
            
        --numTeclados;
    }

    /**
     * Borra todos los teclados y establece el número de teclados a cero.
     */
    public void clear() {
        for (String id : teclados.keySet()) {
            borrarTeclado(id);
        }
        numTeclados = 0;
    }
}