package edu.upc.prop.clusterxx.Dominio.alfabeto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * La clase GestionAlfabetos proporciona métodos para gestionar un conjunto de alfabetos.
 * Permite cargar, consultar, añadir, borrar, modificar y listar alfabetos.
 */

public class GestionAlfabetos {
    //atributos privados
    private HashMap<String,Alfabeto> alfabetos;
    private Alfabeto alfabeto;

    //constructora
    /**
     * Crea una nueva instancia de GestionAlfabetos con un conjunto vacío de alfabetos y un objeto Alfabeto.
     */
    public GestionAlfabetos() {
        alfabetos = new HashMap<String,Alfabeto>();
        alfabeto = new Alfabeto();
    }

    /**
     * Convierte un mapa de cadenas a objetos Alfabeto.
     * @param mapaString El mapa de cadenas a convertir.
     * @return Un mapa de objetos Alfabeto.
     */
    
    private HashMap<String, Alfabeto> convertirMapaStringAAlfabeto(HashMap<String, String> mapaString) {
        HashMap<String, Alfabeto> resultado = new HashMap<>();

        for (Map.Entry<String, String> entry : mapaString.entrySet()) {
            String clave = entry.getKey();
            String valorString = entry.getValue();

            // Crear un objeto Alfabeto a partir del valor de cadena
            Alfabeto alfabeto = new Alfabeto(valorString);

            // Agregar el Alfabeto al nuevo mapa
            resultado.put(clave, alfabeto);
        }

        return resultado;
    }

    /**
     * Carga un conjunto de alfabetos a partir de un mapa de cadenas.
     * @param alfabetosCargados El mapa de cadenas que representa los alfabetos.
     */

    public void cargarAlfabetos(HashMap<String,String> alfabetosCargados) {
        alfabetos = convertirMapaStringAAlfabeto(alfabetosCargados);
    }

    //consultoras
     /**
     * Consulta un alfabeto específico por su identificador y devuelve una versión ordenada alfabéticamente.
     * @param id El identificador del alfabeto a consultar.
     * @return Una cadena que representa el alfabeto ordenado alfabéticamente.
     * @throws NoSuchElementException Si no hay alfabetos en el sistema o si no existe el alfabeto con el identificador proporcionado.
     * @throws NullPointerException Si el alfabeto consultado es nulo.
     */
    public String consultarAlfabeto(String id) throws NoSuchElementException, NullPointerException {
        if(alfabetos.isEmpty()) throw new NoSuchElementException("No hay ningun alfabeto en el sistema");
        else if(!existe_alfabeto(id)) throw new NullPointerException("No existe el alfabeto " + id);
        else {
            Alfabeto alfabetoObj = alfabetos.get(id);
            char[] alfabetoArray = alfabetoObj.getAlfabeto().toCharArray();
        
            // Ordena el alfabeto alfabéticamente
            Arrays.sort(alfabetoArray);
        
            // Convierte el alfabeto ordenado a una cadena
            String alfabetoOrdenado = new String(alfabetoArray);
        
            return alfabetoOrdenado;
        }
    }

    /**
     * Consulta un objeto Alfabeto específico por su identificador.
     * @param id El identificador del alfabeto a consultar.
     * @return El objeto Alfabeto asociado al identificador.
     * @throws NoSuchElementException Si no hay alfabetos en el sistema o si no existe el alfabeto con el identificador proporcionado.
     * @throws NullPointerException Si el alfabeto consultado es nulo.
     */

    public Alfabeto consultarAlfabetoObjeto(String id) {
        if (alfabetos.isEmpty()) throw new NoSuchElementException("No hay ningún usuario en el sistema");
        else if (!existe_alfabeto(id)) throw new NullPointerException ("No existe el usuario " + id);
        else return alfabetos.get(id);
    }

    /**
     * Verifica la existencia de un alfabeto en el sistema.
     * @param id El identificador del alfabeto a verificar.
     * @return true si el alfabeto existe, false en caso contrario.
     */

    public boolean existe_alfabeto(String id) {
        return alfabetos.containsKey(id);
    }
    
    //modificadoras
    /**
     * Añade un nuevo alfabeto al sistema.
     * @param id El identificador del nuevo alfabeto.
     * @param letras Las letras que formarán el alfabeto.
     * @throws IllegalStateException Si ya existe un alfabeto con el mismo identificador, o si el alfabeto está vacío después de la normalización.
     * @throws NullPointerException Si las letras del alfabeto son nulas.
     */
    public void añadirAlfabeto(String id, String letras) throws IllegalStateException, NullPointerException {    
        if(existe_alfabeto(id)) {
            throw new IllegalStateException("Alfabeto con id " + id + " ya existe.");
        }  
        else if (letras.length() == 0) throw new NullPointerException("Alfabeto vacío.");
        else {
            letras = alfabeto.normalizar(letras);
            if(letras.isEmpty()) throw new IllegalStateException("El alfabeto no contiene ninguna letra");
            alfabetos.put(id,new Alfabeto(letras));
        }
    }

    //pre: existe el texto
    /**
     * Convierte un texto a un nuevo alfabeto y lo añade al sistema.
     * @param texto El texto a convertir.
     * @param idAlf El identificador del nuevo alfabeto.
     * @return Una cadena que representa el nuevo alfabeto.
     * @throws IllegalStateException Si el alfabeto resultante está vacío después de la normalización.
     */
    public String convertir_a_Alfabeto(String texto, String idAlf) throws IllegalStateException {  
        String nuevoAlfabetoString = alfabeto.normalizar(texto);
    
        // lo añadimos
        añadirAlfabeto(idAlf, nuevoAlfabetoString);
    
        return nuevoAlfabetoString;
    }

    /**
     * Borra un alfabeto del sistema.
     * @param id El identificador del alfabeto a borrar.
     * @throws NoSuchElementException Si no hay alfabetos en el sistema o si no existe el alfabeto con el identificador proporcionado.
     * @throws NullPointerException Si el alfabeto a borrar es nulo.
     */
    public void borrarAlfabeto(String id) throws NoSuchElementException, NullPointerException {
       if(alfabetos.isEmpty()) {
        throw new NoSuchElementException("No hay ningun alfabeto en el sistema");
       }
       else if(!existe_alfabeto(id)) throw new NullPointerException("No existe el alfabeto con id " + id); 
       else {
        alfabetos.remove(id);
       }
    }

    /**
     * Modifica un alfabeto existente añadiendo o quitando letras.
     * @param id El identificador del alfabeto a modificar.
     * @param letras Las letras a añadir o quitar.
     * @param opcion La opción que indica si se deben añadir (1) o quitar (2) las letras.
     * @return 0 si la operación se realiza correctamente, -1 si el alfabeto resultante está vacío después de la modificación.
     * @throws NoSuchElementException Si no hay alfabetos en el sistema o si no existe el alfabeto con el identificador proporcionado.
     * @throws NullPointerException Si las letras del alfabeto son nulas.
     * @throws IllegalArgumentException Si la opción proporcionada no es válida (debe ser 1 o 2).
     * @throws InputMismatchException Si las letras a añadir o quitar son vacías.
     */
    public int modificarAlfabeto(String id, String letras, int opcion) throws NoSuchElementException, NullPointerException, IllegalArgumentException, InputMismatchException {
        if(alfabetos.isEmpty()) {
            throw new NoSuchElementException("No hay ningun alfabeto en el sistema");
        }
        else if(!existe_alfabeto(id)) throw new NullPointerException("No existe el alfabeto con id " + id); 
        else if(opcion != 1 && opcion != 2) throw new InputMismatchException("La opcion debe ser añadir ó quitar.");
        else if (letras.length() == 0) throw new NullPointerException("Letras a poner o quitar vacias.");
        else {
            letras = alfabeto.normalizar(letras);
            Alfabeto alfabeto = alfabetos.get(id);

            if(opcion == 1) { //añadir letras
                alfabeto.agregarLetras(letras);
            }
            else { //quitar letras
                int quitar = alfabeto.quitarLetras(letras);
                if(alfabeto.getAlfabeto().trim().isEmpty()) {
                    borrarAlfabeto(id);
                    return -1;
                }
                if(quitar == -1) throw new IllegalArgumentException("Has de poner letras que esten en el alfabeto.");
            }
        }
        return 0;
    } 

     /**
     * Elimina todos los alfabetos del sistema.
     */
    public void clear() {
        for (Alfabeto alfabeto : alfabetos.values()) {
            alfabeto.clear();
        }
        alfabetos.clear();
    }
    
    //escritura
    /**
     * Obtiene un listado de todos los alfabetos en el sistema.
     * @return Un mapa que asocia identificadores con objetos Alfabeto.
     * @throws NoSuchElementException Si no hay alfabetos en el sistema.
     */
    public HashMap<String,Alfabeto> getlistadoAlfabetos() throws NoSuchElementException {
        if(alfabetos.isEmpty()) throw new NoSuchElementException("No hay ningun alfabeto en el sistema");
        else return alfabetos;
    }

    /**
     * Lee un conjunto de entradas de alfabetos desde un archivo.
     * @param rutaArchivo La ruta del archivo desde el cual leer.
     * @return Un conjunto de entradas (identificador, letras) leídas desde el archivo.
     * @throws IOException Si ocurre un error de entrada/salida.
     * @throws IllegalStateException Si el estado del sistema no permite la operación.
     */
    public Set<SimpleEntry<String, String>> leerDesdeArchivo(String rutaArchivo) throws IOException, IllegalStateException {
    BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
    Set<SimpleEntry<String, String>> datosSet = new HashSet<>();

    String linea;
    while ((linea = br.readLine()) != null) {
        // Procesar cada línea del archivo aquí
        String[] partes = linea.split(" "); // Dividir la línea en partes basadas en espacios
        String id = "-1"; // Inicializar id a un valor no válido
        String alfabeto = null; // Inicializar alfabeto a null

        // Iterar sobre las partes para encontrar id y alfabeto
        for (String parte : partes) {
            if (parte.startsWith("id:")) {
                id = parte.split(":")[1];
            } else if (parte.startsWith("alfabeto:")) {
                alfabeto = parte.split(":")[1];
            }
        }

        // Verificar si se encontraron id y alfabeto antes de añadir al sistema
        if (id != "-1" && alfabeto != null) {
            // Eliminar las comillas del alfabeto si están presentes
            alfabeto = alfabeto.replaceAll("\"", "");
            datosSet.add(new SimpleEntry<>(id, alfabeto));
        }
    }

    br.close();
    return datosSet;
}
}
