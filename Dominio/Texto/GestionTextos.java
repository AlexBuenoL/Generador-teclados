package edu.upc.prop.clusterxx.Dominio.Texto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * La clase GestionTextos proporciona métodos para gestionar textos, incluyendo la carga, consulta,
 * adición, borrado y modificación de textos en el sistema.
 */

public class GestionTextos {
    //privado
    private HashMap<String,Texto> textos;

    private HashMap<String,String> alfabetosTexto;

    private HashMap<String,String> frecuenciasTexto;

    //constructora
     /**
     * Crea una nueva instancia de GestionTextos con mapas inicializados para almacenar textos, alfabetos
     * y frecuencias asociadas.
     */
    public GestionTextos() {
        textos = new HashMap<String,Texto>();
        alfabetosTexto = new HashMap<String,String>();
        frecuenciasTexto = new HashMap<String,String>();
    }

     /**
     * Convierte un mapa de cadenas a un mapa de textos.
     * @param mapaString El mapa de cadenas a convertir.
     * @return Un nuevo mapa que asocia claves con instancias de la clase Texto.
     */
    private HashMap<String, Texto> convertirMapaStringAHashMapTexto(HashMap<String, String> mapaString) {
        HashMap<String, Texto> resultado = new HashMap<>();

        for (Map.Entry<String, String> entry : mapaString.entrySet()) {
            String clave = entry.getKey();
            String valorString = entry.getValue();

            // Crear un objeto Texto a partir del valor del mapa
            Texto texto = new Texto(valorString);

            // Agregar el Texto al nuevo mapa
            resultado.put(clave, texto);
        }

        return resultado;
    }

    /**
     * Carga los textos, alfabetos y frecuencias desde mapas proporcionados a los campos de la clase.
     * @param textosCargados Mapa de textos a cargar.
     * @param alfTextCargados Mapa de alfabetos asociados a textos a cargar.
     * @param freTextCargados Mapa de frecuencias asociadas a textos a cargar.
     */
    public void cargarTextos(HashMap<String,String> textosCargados, HashMap<String,String> alfTextCargados, HashMap<String,String> freTextCargados) {
        textos = convertirMapaStringAHashMapTexto(textosCargados);
        alfabetosTexto = alfTextCargados;
        frecuenciasTexto = freTextCargados;
    }

    //consultoras
    /**
     * Verifica la existencia de un texto en el sistema.
     * @param id El identificador del texto a verificar.
     * @return true si el texto existe, false en caso contrario.
     */
    public boolean existe_Texto(String id) {
        return textos.containsKey(id);
    }

    /**
     * Consulta el contenido de un texto específico por su identificador.
     * @param id El identificador del texto a consultar.
     * @return Una cadena que representa el contenido del texto.
     * @throws NoSuchElementException Si no se encuentra el texto.
     * @throws NullPointerException Si el texto es nulo.
     */
    public String consultarTexto(String id) throws NoSuchElementException, NullPointerException {
        if(textos.isEmpty()) throw new NoSuchElementException("No hay ningun texto en el sistema");
        else if(!existe_Texto(id)) throw new NullPointerException("No existe el texto " + id);
        else {
            String texto = textos.get(id).getTexto();
            return texto;
        }
    }

     /**
     * Consulta el identificador del alfabeto asociado a un texto.
     * @param idTexto El identificador del texto.
     * @return El identificador del alfabeto asociado al texto.
     */
    public String getIdAlfabetoTexto(String idTexto) {
        return alfabetosTexto.get(idTexto);
    }

    /**
     * Consulta el identificador de la frecuencia asociada a un texto.
     * @param idTexto El identificador del texto.
     * @return El identificador de la frecuencia asociada al texto.
     */
    public String getIdFrecuenciaTexto(String idTexto) {
        return frecuenciasTexto.get(idTexto);
    }

    /**
     * Consulta el identificador del texto asociado a un alfabeto.
     * @param idAlf El identificador del alfabeto.
     * @return El identificador del texto asociado al alfabeto.
     */
    public String getIdTextoAlfabeto(String idAlf) {
        for (Map.Entry<String, String> entry : alfabetosTexto.entrySet()) {
            if (entry.getValue() == idAlf) {
                String claveEncontrada = entry.getKey();
                return claveEncontrada;// Puedes detener la iteración una vez que encuentres la primera coincidencia si solo esperas una clave única
            }
        }
        return "-1";
    }

    /**
     * Consulta el identificador del texto asociado a una frecuencia.
     * @param idFre El identificador de la frecuencia.
     * @return El identificador del texto asociado a la frecuencia.
     */
    public String getIdTextoFrec(String idFre) {
        for (Map.Entry<String, String> entry : frecuenciasTexto.entrySet()) {
            if (entry.getValue() == idFre) {
                String claveEncontrada = entry.getKey();
                return claveEncontrada;// Puedes detener la iteración una vez que encuentres la primera coincidencia si solo esperas una clave única
            }
        }
        return "-1";
    }

     /**
     * Verifica la existencia de un alfabeto asociado a un texto.
     * @param idTexto El identificador del texto.
     * @return true si existe un alfabeto asociado al texto, false en caso contrario.
     */
    public boolean existe_AlfabetoTexto(String idTexto) {
        return alfabetosTexto.containsKey(idTexto);
    }

    /**
     * Verifica la existencia de una frecuencia asociada a un texto.
     * @param idTexto El identificador del texto.
     * @return true si existe una frecuencia asociada al texto, false en caso contrario.
     */
    public boolean existeFrecuenciaTexto(String idTexto) {
        return frecuenciasTexto.containsKey(idTexto);
    }

    //modificadoras
    /**
     * Elimina todos los textos del sistema.
     */
    public void clear() {
        for (Texto texto : textos.values()) {
            texto.clear();
        }
        textos.clear();
    }

     /**
     * Añade un nuevo texto al sistema, con su correspondiente alfabeto y frecuencia.
     * @param id El identificador del nuevo texto.
     * @param texto El contenido del nuevo texto.
     * @param idAlf El identificador del alfabeto asociado al texto.
     * @param idFre El identificador de la frecuencia asociada al texto.
     * @throws IllegalAccessError Si ya existe un texto con el mismo identificador.
     * @throws NullPointerException Si el texto proporcionado es nulo o el texto está vacío.
     */
    public void añadirTexto(String id, String texto, String idAlf, String idFre) throws IllegalAccessError, NullPointerException {
        if(existe_Texto(id)) {
            throw new IllegalAccessError("Texto con id " + id + " ya existe.");
        } 
        else if(texto.isEmpty()) throw new NullPointerException("Texto vacío"); 
        else {
            textos.put(id,new Texto(texto));
            alfabetosTexto.put(id,idAlf);
            frecuenciasTexto.put(id, idFre);
        }
    }

    /**
     * Elimina un texto del sistema, junto con su alfabeto y frecuencia asociados.
     * @param id El identificador del texto a borrar.
     * @throws NoSuchElementException Si el texto no existe.
     * @throws NullPointerException Si el identificador del texto es nulo.
     */
    public void borrarTexto(String id) throws NoSuchElementException, NullPointerException {
        if(textos.isEmpty()) throw new NoSuchElementException("No hay ningun texto en el sistema");
        else if(!existe_Texto(id)) throw new NullPointerException("No existe el texto con id " + id);
        else {
            textos.remove(id);
            if(existe_AlfabetoTexto(id))alfabetosTexto.remove(id);
            if(existeFrecuenciaTexto(id)) frecuenciasTexto.remove(id);
        }
    }

    /**
     * Modifica el contenido de un texto en el sistema.
     * @param id El identificador del texto a modificar.
     * @param newTexto El nuevo contenido del texto.
     * @return Una cadena que representa el contenido del texto después de la modificación.
     * @throws NoSuchElementException Si no se encuentra el texto a modificar.
     * @throws NullPointerException Si el nuevo texto es nulo.
     * @throws IllegalArgumentException Si el nuevo texto deja el alfabeto asociado al texto vacío.
     * @throws InputMismatchException Si se produce un error de tipo al realizar la modificación.
     */
    public String modificarTexto(String id, String newtexto) throws NoSuchElementException, NullPointerException, IllegalArgumentException, InputMismatchException {
        if(textos.isEmpty()) throw new NoSuchElementException("No hay ningun texto en el sistema");
        else if(!existe_Texto(id)) throw new NullPointerException("No existe el texto " + id);
        else if(newtexto.isEmpty()) throw new NullPointerException("Nuevo texto vacío");
        else {
            Texto texto = textos.get(id);

            if(newtexto.trim().isEmpty()) {
                alfabetosTexto.remove(id);
                frecuenciasTexto.remove(id);
                return texto.getTexto();
            }
            else {
                texto.setTexto(newtexto);
                return texto.getTexto();
            }
        }
    }

     /**
     * Establece el identificador del alfabeto asociado a un texto.
     * @param id El identificador del texto.
     * @param idAlf El nuevo identificador del alfabeto.
     */
    public void setIdAlfabetoTexto(String id, String idAlf) {
        alfabetosTexto.put(id,idAlf);
    }

    /**
     * Establece el identificador de la frecuencia asociada a un texto.
     * @param id El identificador del texto.
     * @param idFre El nuevo identificador de la frecuencia.
     */
    public void setIdFrecuenciaTexto(String id, String idFre) {
        frecuenciasTexto.put(id,idFre);
    }

    //escritura
    /**
     * Obtiene el listado de textos en el sistema.
     * @return Un mapa que asocia identificadores de texto con instancias de la clase Texto.
     * @throws NoSuchElementException Si no hay ningún texto en el sistema.
     */
    public HashMap<String,Texto> getlistadoTextos() throws NoSuchElementException {
        if(textos.isEmpty()) throw new NoSuchElementException("No hay ningun texto en el sistema");
        else return textos;
    }
    
    /**
     * Lee un conjunto de entradas de texto desde un archivo.
     * @param rutaArchivo La ruta del archivo desde el cual leer.
     * @return Un conjunto de entradas (identificador, texto) leídas desde el archivo.
     * @throws IOException Si ocurre un error de entrada/salida.
     * @throws IllegalAccessError Si se produce un error de acceso ilegal al leer desde el archivo.
     */
     public Set<SimpleEntry<String, String>> leerDesdeArchivo(String rutaArchivo) throws IOException, IllegalAccessError {
        BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
        StringBuilder textoBuilder = new StringBuilder();
        String id = "-1"; // ID por defecto si no se encuentra en el archivo
        boolean leyendoTexto = false;
        Set<SimpleEntry<String, String>> resultado = new HashSet<>();

        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.startsWith("Id:")) {
                // Comenzamos un nuevo texto, guarda el texto anterior si lo hay
                if (leyendoTexto && id != "-1") {
                    // Añade el ID y el texto al conjunto de resultados
                    resultado.add(new SimpleEntry<>(id, textoBuilder.toString()));
                    textoBuilder = new StringBuilder();
                    leyendoTexto = false;
                }

                // Extrae el ID del encabezado "Id:"
                id = (linea.substring(3).trim());
            } else if (linea.equals("\"")) {
                // Cambia el estado de leyendoTexto al encontrar comillas dobles
                leyendoTexto = !leyendoTexto;
            } else if (leyendoTexto) {
                // Concatena las líneas de texto solo si estamos leyendo el texto
                textoBuilder.append(linea).append(System.lineSeparator());
            }
        }

        // Añade el último texto al conjunto de resultados si estaba en proceso de lectura
        if (leyendoTexto && id != "-1") {
            resultado.add(new SimpleEntry<>(id, textoBuilder.toString()));
        }
        br.close();
        return resultado;
    }
}
