package edu.upc.prop.clusterxx.Dominio.Frecuencia;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Set;

/**
 * La clase Frecuencia se encarga de gestionar las frecuencias de palabras y conjuntos de letras en un texto.
 */
public class Frecuencia {

    // Atributos privados

    // Los dos atributos siguientes hacen referencia al listado de frecuencias

    /**
     * Lista que contiene el número de apariciones de cada conjunto de 2 letras.
     * Por ejemplo, ("ia", 2) significa que las letras 'i' y 'a' van seguidas en este orden 2 veces.
     */
    private HashMap<String, Integer> frecuencias;

    /**
     * Lista que contiene el número de apariciones de cada palabra.
     * Por ejemplo, ("casa", 2) significa que la palabra "casa" aparece 2 veces.
     */
    private HashMap<String, Integer> listadoFrecuencias;

    // Creadoras

    /**
     * Constructor por defecto de la clase Frecuencia.
     * Inicializa las listas de frecuencias.
     */
    public Frecuencia() {
        frecuencias = new HashMap<String, Integer>();
        listadoFrecuencias = new HashMap<String, Integer>();
    }

    /**
     * Constructor que permite inicializar las frecuencias a partir de un conjunto de palabras y sus apariciones.
     *
     * @param words Conjunto de palabras y sus apariciones.
     */
    public Frecuencia(Set<SimpleEntry<String, Integer>> words) {
        frecuencias = new HashMap<String, Integer>();
        listadoFrecuencias = new HashMap<String, Integer>();

        for (SimpleEntry<String, Integer> word : words) {
            String pal = word.getKey();  // palabra
            int nVec = word.getValue();  // número de veces

            añadirFrecuenciasListado(pal, nVec);
        }
    }

    // Consultoras

    /**
     * Obtiene la lista que contiene el número de apariciones de cada conjunto de 2 letras.
     *
     * @return La lista que contiene el número de apariciones de cada conjunto de 2 letras.
     */
    public HashMap<String, Integer> getFrecuencia() {
        return frecuencias;
    }

    /**
     * Obtiene la lista que contiene el número de apariciones de cada palabra.
     *
     * @return La lista que contiene el número de apariciones de cada palabra.
     */
    public HashMap<String, Integer> getListadoFrecuencias() {
        return listadoFrecuencias;
    }

    /**
     * Verifica si el conjunto de frecuencias está vacío.
     *
     * @return true si el conjunto de frecuencias está vacío, false de lo contrario.
     */
    public boolean isEmpty() {
        return frecuencias.isEmpty();
    }

    /**
     * Verifica si una palabra existe en el listado de frecuencias.
     *
     * @param palabra La palabra a verificar.
     * @return true si la palabra existe en el listado de frecuencias, false de lo contrario.
     */
    public boolean existePalabra(String palabra) {
        return listadoFrecuencias.get(palabra) != null;
    }

    // Modificadoras

    // n = nº apariciones (uso en listado de palabras) : 1) En un texto n=1   2) En un listado de palabras n=nºapariciones de la palabra

    /**
     * Añade las frecuencias al listado de palabras y conjuntos de letras.
     *
     * @param txt La palabra o conjunto de letras.
     * @param n   Número de apariciones.
     */
    public void añadirFrecuenciasListado(String txt, int n) {
        //Añado la palabra al listado de palabras y si ya existe aumento el numero de apariciones
        if (listadoFrecuencias.get(txt) == null) listadoFrecuencias.put(txt, n);
        else listadoFrecuencias.put(txt, listadoFrecuencias.get(txt) + n);
        int i = 0, j = 1;
        String result;

        //Para identificar los espacios al tratar con frecuencias usamos _

        if (txt.length() == 1) {
            char c1 = txt.charAt(i);
            char c2 = '_';
            result = Character.toString(c1) + Character.toString(c2);

            //Añado el par de caracteres al listado de caracteres y si ya existe aumento el numero de apariciones
            if (frecuencias.get(result) == null) frecuencias.put(result, n);
            else frecuencias.put(result, frecuencias.get(result) + n);
        }

        while (j < txt.length()) {
            char c1 = txt.charAt(i);
            char c2 = txt.charAt(j);
            if (c2 == ' ') c2 = '_';
            if (c1 == ' ') c1 = '_';
            result = Character.toString(c1) + Character.toString(c2);
            
            //Añado el par de caracteres al listado de caracteres y si ya existe aumento el numero de apariciones
            if (frecuencias.get(result) == null) frecuencias.put(result, n);
            else frecuencias.put(result, frecuencias.get(result) + n);
            ++i;
            ++j;
        }
    }

    /**
     * Elimina las frecuencias del listado de palabras y conjuntos de letras.
     *
     * @param palabras Conjunto de palabras a eliminar.
     */
    public void eliminarFrecuenciasListado(Set<String> palabras) {
        for (String palabra : palabras) {
            int i = 0, j = 1;
            String result;
            
            //Para identificar los espacios al tratar con frecuencias usamos _

            if (palabra.length() == 1) {
                char c1 = palabra.charAt(i);
                char c2 = '_';
                result = Character.toString(c1) + Character.toString(c2);

                frecuencias.remove(result);
            }

            while (j < palabra.length()) {
                char c1 = palabra.charAt(i);
                char c2 = palabra.charAt(j);
                if (c2 == ' ') c2 = '_';
                if (c1 == ' ') c1 = '_';
                result = Character.toString(c1) + Character.toString(c2);

                frecuencias.remove(result);
                ++i;
                ++j;
            }
            listadoFrecuencias.remove(palabra);
        }
    }

    /**
     * Limpia el conjunto de frecuencias de conjuntos de letras.
     */
    public void clear() {
        frecuencias.clear();
    }
}
