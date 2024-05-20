package edu.upc.prop.clusterxx.Dominio.alfabeto;

import java.text.Normalizer;
import java.util.Arrays;

/**
 * La clase Alfabeto representa un conjunto ordenado de letras.
 * Permite realizar operaciones como normalización, agregación y eliminación de letras.
 */
public class Alfabeto {

    // Atributos privados
    private String alfabeto;

    /**
     * Método privado que ordena el alfabeto en orden ascendente.
     */
    private void ordenarAlfabeto() {
        char[] alfabetoArray = alfabeto.toCharArray();
        Arrays.sort(alfabetoArray);
        alfabeto = new String(alfabetoArray);
    }

    /**
     * Devuelve una representación en cadena del alfabeto.
     * @return El alfabeto como una cadena de caracteres.
     */
    @Override
    public String toString() {
        return alfabeto;
    }

    /**
     * Constructor por defecto de la clase Alfabeto.
     */
    public Alfabeto() {

    }

    /**
     * Constructor que inicializa el alfabeto con la cadena especificada.
     * @param alf La cadena que representa el alfabeto.
     */
    public Alfabeto(String alf) {
        alfabeto = alf;
    }

    /**
     * Obtiene el alfabeto ordenado.
     * @return El alfabeto ordenado.
     */
    public String getAlfabeto() {
        ordenarAlfabeto();
        return alfabeto;
    }

    /**
     * Normaliza un texto, eliminando caracteres diacríticos y convirtiendo a minúsculas.
     * @param texto El texto a normalizar.
     * @return El texto normalizado.
     */
    public String normalizar(String texto) {
        StringBuilder nuevoAlfabeto = new StringBuilder();
        texto = texto.toLowerCase();

        for (char caracter : texto.toCharArray()) {
            String letraNormalizada = Normalizer.normalize(String.valueOf(caracter), Normalizer.Form.NFD)
                    .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

            // Agrega solo caracteres diferentes (sin distinción de mayúsculas o minúsculas)
            if (Character.isLetter(caracter) && nuevoAlfabeto.indexOf(String.valueOf(letraNormalizada.charAt(0))) == -1) {
                nuevoAlfabeto.append(letraNormalizada.charAt(0));
            }
        }
        return nuevoAlfabeto.toString();
    }

    /**
     * Establece el alfabeto con la cadena especificada.
     * @param alf La cadena que representa el nuevo alfabeto.
     */
    public void setAlfabeto(String alf) {
        alfabeto = alf;
    }

    /**
     * Elimina el contenido del alfabeto.
     */
    public void clear() {
        alfabeto = null;
    }

    /**
     * Agrega nuevas letras al alfabeto después de normalizarlas.
     * @param nuevasLetras Las letras a agregar.
     */
    public void agregarLetras(String nuevasLetras) {
        nuevasLetras = normalizar(nuevasLetras);
        alfabeto = alfabeto.toLowerCase();
        for (char letra : nuevasLetras.toCharArray()) {
            if (!alfabeto.contains(String.valueOf(letra))) {
                alfabeto += letra;
            }
        }
        ordenarAlfabeto();
    }

    /**
     * Elimina las letras especificadas del alfabeto después de normalizarlas.
     * @param letrasAQuitar Las letras a quitar.
     * @return 1 si todas las letras se quitaron correctamente, -1 si alguna letra no estaba presente.
     */
    public int quitarLetras(String letrasAQuitar) {
        letrasAQuitar = normalizar(letrasAQuitar);
        alfabeto = alfabeto.toLowerCase();

        // Verificar si todas las letras a quitar están presentes en el alfabeto
        for (char letra : letrasAQuitar.toCharArray()) {
            if (!alfabeto.contains(String.valueOf(letra))) {
                return -1; // Devolver -1 si alguna letra no está presente en el alfabeto
            }
        }

        StringBuilder nuevoAlfabeto = new StringBuilder();
        for (char letra : alfabeto.toCharArray()) {
            String letraNormalizada = Normalizer.normalize(String.valueOf(letra), Normalizer.Form.NFD)
                    .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

            // Agrega la letra al nuevo alfabeto solo si no está en las letras a quitar
            // y si aún no ha sido agregada
            if (Character.isLetter(letra) && letrasAQuitar.indexOf(letraNormalizada.charAt(0)) == -1 && nuevoAlfabeto.indexOf(String.valueOf(letraNormalizada.charAt(0))) == -1) {
                nuevoAlfabeto.append(letraNormalizada.charAt(0));
            }
        }

        alfabeto = nuevoAlfabeto.toString();
        ordenarAlfabeto();
        return 1;
    }
}
