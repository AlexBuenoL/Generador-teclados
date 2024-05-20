package edu.upc.prop.clusterxx.Dominio.algoritmo;

import java.io.Serializable;

/**
 * La interfaz Algoritmo define los métodos que deben ser implementados por
 * cualquier clase que represente un algoritmo. Proporciona métodos para obtener
 * el resultado del algoritmo en forma de matriz de caracteres y para obtener
 * el valor de optimalidad del resultado.
 *
 * La interfaz extiende la interfaz Serializable para permitir la serialización
 * de las instancias que la implementan.
 */
public interface Algoritmo extends Serializable {

    /**
     * Obtiene el resultado del algoritmo en forma de matriz de caracteres.
     *
     * @return Matriz de caracteres que representa el resultado del algoritmo.
     */
    char[][] getResultat();

    /**
     * Obtiene el valor de optimalidad del resultado del algoritmo.
     *
     * @return Valor de optimalidad del resultado del algoritmo.
     */
    double getOptimalidad();
}
