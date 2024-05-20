package edu.upc.prop.clusterxx.Dominio.Frecuencia;

import java.util.*;
import java.io.*;
import java.text.Normalizer;
import java.util.AbstractMap.SimpleEntry;

/**
 * La clase GestionFrecuencias se encarga de gestionar las frecuencias de palabras en un sistema.
 */
public class GestionFrecuencias {

    // Atributos privados
    private HashMap<String, Frecuencia> frecuencias;

    /**
     * Constructor por defecto de la clase GestionFrecuencias.
     */
    public GestionFrecuencias() {
        frecuencias = new HashMap<String, Frecuencia>();
    }

    /**
     * Convierte un mapa de cadenas a un mapa de objetos Frecuencia.
     *
     * @param mapaListaString Mapa de cadenas a convertir.
     * @return Mapa de objetos Frecuencia resultante.
     */
    private HashMap<String, Frecuencia> convertirMapaStringAHashMapFrecuencia(HashMap<String, HashMap<String, Integer>> mapaListaString) {
        HashMap<String, Frecuencia> resultado = new HashMap<>();

        for (Map.Entry<String, HashMap<String, Integer>> entry : mapaListaString.entrySet()) {
            String clave = entry.getKey();
            HashMap<String, Integer> valorListaString = entry.getValue();

            Frecuencia frecuencia = new Frecuencia(convertirMapaStringASetSimpleEntry(valorListaString));

            resultado.put(clave, frecuencia);
        }

        return resultado;
    }

    /**
     * Convierte un mapa de cadenas a un conjunto de SimpleEntry.
     *
     * @param mapaString Mapa de cadenas a convertir.
     * @return Conjunto de SimpleEntry resultante.
     */
    private Set<SimpleEntry<String, Integer>> convertirMapaStringASetSimpleEntry(HashMap<String, Integer> mapaString) {
        Set<SimpleEntry<String, Integer>> setSimpleEntry = new HashSet<>();

        for (Map.Entry<String, Integer> entry : mapaString.entrySet()) {
            setSimpleEntry.add(new SimpleEntry<>(entry.getKey(), entry.getValue()));
        }

        return setSimpleEntry;
    }

    /**
     * Convierte una cadena en un array de cadenas dividiendo por espacios o saltos de línea.
     *
     * @param entrada Cadena a convertir.
     * @return Array de cadenas resultante.
     */
    private String[] convertirStringAStringArray(String entrada) {
        String[] elementosArray = entrada.split("[\\s\\n]+");
        return elementosArray;
    }

    /**
     * Carga las frecuencias a partir de un mapa de frecuencias cargadas.
     *
     * @param listaFrecuenciasCargadas Mapa de frecuencias cargadas.
     */
    public void cargarFrecuencias(HashMap<String, HashMap<String, Integer>> listaFrecuenciasCargadas) {
        frecuencias = convertirMapaStringAHashMapFrecuencia(listaFrecuenciasCargadas);
    }

    /**
     * Normaliza una cadena de texto eliminando acentos y convirtiéndola a minúsculas.
     *
     * @param texto Cadena de texto a normalizar.
     * @return Cadena normalizada.
     */
    private String normalizar(String texto) {
        StringBuilder nuevoAlfabeto = new StringBuilder();
        texto = texto.toLowerCase();

        //Elimino diacríticos, números y signos de puntuación
        for (char caracter : texto.toCharArray()) {
            String letraNormalizada = Normalizer.normalize(String.valueOf(caracter), Normalizer.Form.NFD)
                    .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

            if (Character.isLetter(caracter) || Character.isWhitespace(caracter)) nuevoAlfabeto.append(letraNormalizada.charAt(0));
        }
        return nuevoAlfabeto.toString();
    }

    // Consultoras

    /**
     * Verifica si existe una frecuencia con el ID proporcionado.
     *
     * @param id ID de la frecuencia.
     * @return Verdadero si la frecuencia existe, falso en caso contrario.
     */
    public boolean existeFrecuencia(String id) {
        return frecuencias.containsKey(id);
    }

    /**
     * Verifica si la gestión de frecuencias está vacía.
     *
     * @return Verdadero si está vacía, falso en caso contrario.
     */
    public boolean isEmpty() {
        return frecuencias.isEmpty();
    }

    /**
     * Verifica si la frecuencia con el ID proporcionado está vacía.
     *
     * @param id ID de la frecuencia.
     * @return Verdadero si está vacía, falso en caso contrario.
     */
    public boolean freqIsEmpty(String id) {
        return frecuencias.get(id).isEmpty();
    }

    /**
     * Obtiene la frecuencia de caracteres de un listado identificado por su ID..
     *
     * @param id ID de la frecuencia.
     * @return Frecuencia de caracteres.
     * @throws NoSuchElementException Si no hay ningún listado de frecuencias en el sistema.
     * @throws NullPointerException     Si la frecuencia con el ID proporcionado no existe.
     */
    public HashMap<String, Integer> getFrecuencia(String id) throws NoSuchElementException, NullPointerException {
        if (isEmpty()) throw new NoSuchElementException("No hay ningún listado de frecuencias en el sistema");
        else if (!existeFrecuencia(id))
            throw new NullPointerException("Listado de frecuencias con id " + id + " no existe.");

        return frecuencias.get(id).getFrecuencia();
    }

    /**
     * Obtiene la frecuencia de palabras de un listado identificado por su ID.
     *
     * @param id ID de la frecuencia.
     * @return Frecuencia de palabras.
     * @throws NoSuchElementException Si no hay ningún listado de frecuencias en el sistema.
     * @throws NullPointerException     Si la frecuencia con el ID proporcionado no existe.
     */
    public HashMap<String, Integer> getListadoFrecuencias(String id) throws NoSuchElementException, NullPointerException {
        if (isEmpty()) throw new NoSuchElementException("No hay ningún listado de frecuencias en el sistema");
        else if (!existeFrecuencia(id))
            throw new NullPointerException("Listado de frecuencias con id " + id + " no existe.");

        return frecuencias.get(id).getListadoFrecuencias();
    }

    /**
     * Obtiene todos los listados de frecuencias en el sistema.
     *
     * @return Mapa de todas las frecuencias en el sistema.
     * @throws NoSuchElementException Si no hay ningún listado de frecuencias en el sistema.
     */
    public HashMap<String, Frecuencia> getTodosListadosFrecuencias() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("No hay ningún listado de frecuencias en el sistema");
        return frecuencias;
    }

    // Modificadoras

    /**
     * Obtiene la frecuencia de palabras a partir de un texto y la asigna a una frecuencia con el ID proporcionado.
     *
     * @param id  ID de la frecuencia.
     * @param txt Texto del cual se obtiene la frecuencia de palabras.
     * @throws IllegalStateException Si la frecuencia con el ID proporcionado ya existe.
     */
    public void obtenerFrecuenciaConTexto(String id, String txt) throws IllegalStateException {
        if (existeFrecuencia(id)) {
            throw new IllegalStateException("Listado de frecuencias con id " + id + " ya existe.");
        }

        frecuencias.put(id, new Frecuencia());

        //normalizo el texto
        txt = normalizar(txt);

        String[] words;
        words = convertirStringAStringArray(txt);

        for (String word : words) {
            frecuencias.get(id).añadirFrecuenciasListado(word, 1);
        }
    }

    /**
     * Añade un listado de frecuencias a la gestión de frecuencias con el ID proporcionado.
     *
     * @param id    ID de la frecuencia.
     * @param pairs Conjunto de pares (palabra, número de veces) a añadir.
     * @throws IllegalStateException Si la frecuencia con el ID proporcionado ya existe.
     * @throws NullPointerException  Si el conjunto de frecuencias está vacío.
     */
    public void añadirListadoFrecuencias(String id, Set<SimpleEntry<String, Integer>> pairs)
            throws IllegalStateException, NullPointerException {
        if (existeFrecuencia(id)) {
            throw new IllegalStateException("Listado de frecuencias con id " + id + " ya existe.");
        } else if (pairs.size() == 0)
            throw new NullPointerException("Su listado de frecuencias está vacío");
        else {
            Set<SimpleEntry<String, Integer>> pairs_normalizado = new HashSet<>();

            //Para cada palabra compruebo si es vacía y si el número de apariciones es negativo y entonces lanzo excepción
            for (SimpleEntry<String, Integer> pair : pairs) {
                String pal = pair.getKey(); // palabra
                int nVec = pair.getValue(); // número de veces
                pal = normalizar(pal);

                if (pal.isEmpty())
                    throw new IllegalArgumentException("El listado de frecuencias contiene alguna palabra vacía\n" + //
                            "\"INFORMACIÓN: Si el listado de frecuencias contiene numeros o signos de puntuación no se añaden.");

                if (nVec < 0)
                    throw new IllegalArgumentException("El número de apariciones de una palabra no puede ser negativo");

                pairs_normalizado.add(new SimpleEntry<>(pal, nVec));
            }

            // Creo la frecuencia
            Frecuencia freq = new Frecuencia();
            frecuencias.put(id, freq);

            for (SimpleEntry<String, Integer> pair : pairs_normalizado) {
                String pal = pair.getKey(); // palabra
                int nVec = pair.getValue(); // número de veces

                freq.añadirFrecuenciasListado(pal, nVec);
            }
        }
    }

    /**
     * Elimina un listado de frecuencias identificado por su ID.
     *
     * @param id ID del listado de frecuencias a eliminar.
     * @throws NoSuchElementException Si no hay ningún listado de frecuencias en el sistema.
     * @throws NullPointerException     Si el listado de frecuencias con el ID proporcionado no existe.
     */
    public void borrarListadoFrecuencias(String id) throws NoSuchElementException, NullPointerException {
        if (isEmpty()) throw new NoSuchElementException("No hay ningún listado de frecuencias en el sistema");
        else if (!existeFrecuencia(id))
            throw new NullPointerException("No existe el listado de frecuencias con id " + id);
        else {
            frecuencias.remove(id);
        }
    }

    /**
     * Añade un conjunto de frecuencias a un listado identificado por su ID.
     *
     * @param id    ID del listado de frecuencias.
     * @param pairs Conjunto de pares (palabra, número de veces) a añadir.
     * @throws NoSuchElementException Si no hay ningún listado de frecuencias en el sistema.
     * @throws NullPointerException     Si el listado de frecuencias con el ID proporcionado no existe.
     */
    public void añadirFrecuenciasListado(String id, Set<SimpleEntry<String, Integer>> pairs)
            throws NoSuchElementException, NullPointerException {
        if (isEmpty()) {
            throw new NoSuchElementException("No hay ningún listado de frecuencias en el sistema");
        } else if (!existeFrecuencia(id))
            throw new NullPointerException("No existe ningún listado de frecuencias con id " + id);
        else {
            Set<SimpleEntry<String, Integer>> pairs_normalizado = new HashSet<>();
            
            //Para cada palabra compruebo si es vacía y si el número de apariciones es negativo y entonces lanzo excepción
            for (SimpleEntry<String, Integer> pair : pairs) {
                String pal = pair.getKey();  // palabra
                int nVec = pair.getValue();  // número de veces
                pal = normalizar(pal);

                if (pal.isEmpty())
                    throw new IllegalArgumentException("El listado de frecuencias contiene alguna palabra vacía\n" + //
                            "\"INFORMACIÓN: Si el listado de frecuencias contiene números o signos de puntuación, no se añaden.");

                if (nVec < 0)
                    throw new IllegalArgumentException("El número de apariciones de una palabra no puede ser negativo");

                pairs_normalizado.add(new SimpleEntry<>(pal, nVec));
            }

            //Añado las frecuencias al listado
            Frecuencia freq = frecuencias.get(id);

            for (SimpleEntry<String, Integer> pair : pairs_normalizado) {
                String pal = pair.getKey();  // palabra
                int nVec = pair.getValue();  // número de veces

                freq.añadirFrecuenciasListado(pal, nVec);
            }
        }
    }

    /**
     * Elimina un conjunto de palabras de un listado de frecuencias identificado por su ID.
     *
     * @param id       ID del listado de frecuencias.
     * @param palabras Conjunto de palabras a eliminar.
     * @throws NoSuchElementException Si no hay ningún listado de frecuencias en el sistema.
     * @throws NullPointerException     Si el listado de frecuencias con el ID proporcionado no existe.
     * @throws IllegalArgumentException Si se intenta eliminar palabras que no existen en el listado de frecuencias.
     */
    public void eliminarFrecuenciasListado(String id, Set<String> palabras) throws NoSuchElementException, NullPointerException, IllegalArgumentException {
        if (isEmpty()) {
            throw new NoSuchElementException("No hay ningún listado de frecuencias en el sistema");
        } else if (!existeFrecuencia(id))
            throw new NullPointerException("No existe ningún listado de frecuencias con id " + id);
        else {
            Frecuencia freq = frecuencias.get(id);
            Set<String> palabrasNormalizadas = new HashSet<>();

            //Normalizo las palabras y compruebo que existan todas en el listado de frecuencias
            for (String palabra : palabras) {
                palabra = normalizar(palabra);
                if (!freq.existePalabra(palabra))
                    throw new IllegalArgumentException("Debes introducir palabras que existan en tu listado de frecuencias");
                palabrasNormalizadas.add(palabra);
            }

            freq.eliminarFrecuenciasListado(palabrasNormalizadas);

            if (freqIsEmpty(id)) frecuencias.remove(id);
        }
    }

    /**
     * Elimina todos los listados de frecuencias y limpia la gestión de frecuencias.
     */
    public void clear() {
        for (Map.Entry<String, Frecuencia> entry : frecuencias.entrySet()) {
            Frecuencia frecuencia = entry.getValue();
            frecuencia.clear();
        }

        frecuencias.clear();
    }

    /**
     * Carga frecuencias desde un archivo y devuelve un conjunto de ID-frecuencias.
     *
     * @param rutaArchivo Ruta del archivo desde el cual cargar las frecuencias.
     * @return Conjunto de pares (ID, frecuencias) cargadas desde el archivo.
     * @throws IOException            Si hay un error al leer el archivo.
     * @throws IllegalStateException Si hay un formato incorrecto en el archivo.
     */
    public Set<SimpleEntry<String, Set<SimpleEntry<String, Integer>>>> cargarFrecuenciaDesdeArchivo(String rutaArchivo) throws IOException, IllegalStateException {
        Set<SimpleEntry<String, Set<SimpleEntry<String, Integer>>>> result = new HashSet<>();

        BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
        String id = "-1";
        Set<SimpleEntry<String, Integer>> setOfPairs = new HashSet<>();

        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith("Id:")) {
                // Cuando encontramos una nueva entrada "Id:", creamos una nueva instancia de Frecuencia
                id = (line.substring(4).trim());
                setOfPairs = new HashSet<>();
            } else if (line.equals("Frecuencia:") && setOfPairs != null) {
                // Leemos las frecuencias y las añadimos a la frecuencia actual
                while (!(line = br.readLine()).equals("finLista")) {
                    String[] parts = line.split(" ");
                    String palabra = parts[0];
                    int nVec = Integer.parseInt(parts[1]);

                    setOfPairs.add(new SimpleEntry<>(palabra, nVec));
                }

                // Añadimos la frecuencia actual al Set de resultado
                if (id != "-1" && setOfPairs != null) {
                    result.add(new SimpleEntry<>(id, new HashSet<>(setOfPairs)));
                }
            }
        }
        br.close();

        return result;
    }
}

