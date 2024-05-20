package edu.upc.prop.clusterxx.Dominio.algoritmo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

/**
 * La clase AssignacioGreedy representa un algoritmo de asignación greedy para
 * generar una disposición de letras en un teclado. Implementa la interfaz
 * Serializable para permitir la serialización de instancias de la clase.
 *
 * El algoritmo asigna las letras en pares a posiciones de un teclado, priorizando
 * las posiciones centrales.
 */
public class AssignacioGreedy implements Serializable {
    private static final long serialVersionUID = 1L;

    private String alfabet;
    private Boolean lletresUsats[];

    /**
     * Constructor de la clase AssignacioGreedy.
     *
     * @param a      Alfabeto utilizado.
     * @param nLetr  Número de letras en el alfabeto.
     */
    public AssignacioGreedy(String a, int nLetr) {
        alfabet = a;
        lletresUsats = new Boolean[nLetr];
        Arrays.fill(lletresUsats, false);
    }

    /**
     * Crea y devuelve una matriz que representa la disposición de letras en un teclado,
     * asignando las letras de una lista al teclado utilizando el algoritmo greedy.
     *
     * @param l           Lista de pares de letras con sus frecuencias.
     * @param lletres     Número total de letras a asignar.
     * @param nColTecles  Número de columnas del teclado.
     * @return            Matriz que representa la disposición de letras en el teclado.
     */
    public char[][] creaMatriu(List<Map.Entry<String, Integer>> l, int lletres, int nColTecles) {
        char[][] res = new char[3][nColTecles];
        // Inicialización vacía
        Arrays.fill(res[0], '-');
        Arrays.fill(res[1], '-');
        Arrays.fill(res[2], '-');
        double cent = Math.floor(nColTecles / 2.0); // Posición central
        int centre = (int) cent;
        // Asignación de las primeras letras
        char c1 = l.get(0).getKey().charAt(0);
        res[1][centre] = c1;
        carcterUsat(c1);
        char c2 = l.get(0).getKey().charAt(1);
        if (c2 != '_') {
            carcterUsat(c2);
            if (nColTecles == 1) res[0][centre] = c2;
            else res[1][centre - 1] = c2;
        }
        for (int i = 1; i < l.size(); i++) {
            c1 = l.get(i).getKey().charAt(0);
            c2 = l.get(i).getKey().charAt(1);
            if (c1 != '_' && !esUsat(c1)) {
                carcterUsat(c1);
                asignaPosLliure(res, c1, centre);
            }
            if (c2 != '_' && !esUsat(c2)) {
                carcterUsat(c2);
                asignaPosLliure(res, c2, centre);
            }
        }
        return res;
    }

    /**
     * Marca una letra como utilizada.
     *
     * @param c Letra que se marca como utilizada.
     */
    private void carcterUsat(char c) {
        for (int i = 0; i < alfabet.length(); i++) {
            if (alfabet.charAt(i) == c) lletresUsats[i] = true;
        }
    }

    /**
     * Consulta si una letra ha sido utilizada.
     *
     * @param c Letra a consultar.
     * @return  true si la letra ha sido utilizada, false de lo contrario.
     */
    private Boolean esUsat(char c) {
        for (int i = 0; i < alfabet.length(); i++) {
            if (alfabet.charAt(i) == c) {
                return lletresUsats[i];
            }
        }
        return false;
    }

    /**
     * Asigna la letra a una posición libre en el teclado.
     *
     * @param teclatIni  Teclado inicial.
     * @param c          Letra a asignar.
     * @param centre     Posición central del teclado.
     */
    private void asignaPosLliure(char[][] teclatIni, char c, int centre) {
        int j = teclatIni[0].length;
        Boolean assignat = false;
        int aux = 0;
        // Intenta colocar la letra en posiciones alternas, arriba, abajo y centrado hacia afuera.
        while (!assignat) {
            if (centre + aux < j) {
                if (teclatIni[1][centre + aux] == '-') {
                    teclatIni[1][centre + aux] = c;
                    assignat = true;
                } else if (teclatIni[0][centre + aux] == '-' && !assignat) {
                    teclatIni[0][centre + aux] = c;
                    assignat = true;
                } else if (teclatIni[2][centre + aux] == '-' && !assignat) {
                    teclatIni[2][centre + aux] = c;
                    assignat = true;
                }
            }
            if (centre - aux >= 0 && !assignat) {
                if (teclatIni[1][centre - aux] == '-') {
                    teclatIni[1][centre - aux] = c;
                    assignat = true;
                } else if (teclatIni[0][centre - aux] == '-' && !assignat) {
                    teclatIni[0][centre - aux] = c;
                    assignat = true;
                } else if (teclatIni[2][centre - aux] == '-' && !assignat) {
                    teclatIni[2][centre - aux] = c;
                    assignat = true;
                }
            }
            ++aux;
        }
    }
}
