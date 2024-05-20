package edu.upc.prop.clusterxx.Dominio.teclado;

import java.io.Serializable;
import java.util.HashMap;
import edu.upc.prop.clusterxx.Dominio.algoritmo.*;

public class Teclado implements Serializable {

    private static final long serialVersionUID = 1L;

    // Atributos privados

    /**
     * Identificador del teclado
     */
    private String idTeclado;

    /**
     * Matriz de chars que representa la teclas del teclado
     */
    private char[][] teclas;

    /**
     * String que representa el alfabeto con el que se genera el teclado
     */
    private String alfabeto;

    /**
     * HashMap que representa la lista de frecuencias con la que se genera el teclado
     */
    private HashMap<String,Integer> listaFrecuencias = new HashMap<String,Integer>();

    /**
     * Objeto algoritmo con el que se ha generado el teclado
     */
    private Algoritmo alg;

    /**
     * String con el nombre del algoritmo utilizado para generar el teclado
     */
    private String nomAlg;

    /**
     * Optimalidad del teclado 
     */
    private double optimalidad;

    /**
     * Constructor por defecto de la clase Teclado.
     */
    public Teclado() {

    }

    /**
     * Constructor con el nombre del teclado.
     * @param nombre Nombre del teclado.
     */
    public Teclado(String nombre) {
        idTeclado = nombre;
    }

    /**
     * Constructor con las teclas del teclado.
     * @param teclas Matriz de caracteres que representa las teclas del teclado.
     */
    public Teclado(char[][] teclas) {
        this.teclas = teclas;
    }

    /**
     * Constructor con nombre, alfabeto y lista de frecuencias del teclado.
     * @param nombre   Nombre del teclado.
     * @param alf      Alfabeto del teclado.
     * @param lf       Lista de frecuencias del teclado.
     */
    public Teclado(String nombre, String alf, HashMap<String,Integer> lf) {
        idTeclado = nombre;
        alfabeto = alf;
        listaFrecuencias = lf;
    }

    // setters / getters

    /**
     * Obtiene el nombre del teclado.
     * @return Nombre del teclado.
     */
    public String getNombre() {
        return idTeclado;
    }

    /**
     * Obtiene la optimalidad del teclado.
     * @return Valor de optimalidad del teclado.
     */
    public double getOptimalidad() {
        return optimalidad;
    }

    /**
     * Establece el nombre del teclado.
     * @param nombre Nuevo nombre del teclado.
     */
    public void setNombre(String nombre) {
        idTeclado = nombre;
    }

    /**
     * Obtiene las teclas del teclado.
     * @return Matriz de caracteres que representa las teclas del teclado.
     * @throws NullPointerException Si las teclas no han sido asignadas.
     */
    public char[][] getTeclas() throws NullPointerException {
        if (teclas == null) throw new NullPointerException("Teclas no asignadas");
        else return teclas;
    }

    /**
     * Establece las teclas del teclado.
     * @param tec Nueva matriz de caracteres que representa las teclas del teclado.
     */
    public void setTeclas(char[][] tec) {
        teclas = tec;
    }

    /**
     * Obtiene la lista de frecuencias del teclado.
     * @return HashMap que contiene la lista de frecuencias del teclado.
     * @throws NullPointerException Si la lista de frecuencias no ha sido asignada.
     */
    public HashMap<String,Integer> getListadoFrecuencias() throws NullPointerException {
        if (listaFrecuencias == null) throw new NullPointerException("Lista de frecuencias vacia");
        else return listaFrecuencias;
    }

    /**
     * Establece la lista de frecuencias del teclado.
     * @param lf Nueva lista de frecuencias del teclado.
     */
    public void setListadoFrecuencias(HashMap<String,Integer> lf) {
        listaFrecuencias = lf;
    }

    /**
     * Obtiene el alfabeto del teclado.
     * @return Alfabeto del teclado.
     * @throws NullPointerException Si el alfabeto no ha sido asignado.
     */
    public String getAlfabeto() throws NullPointerException {
        if (alfabeto == null) throw new NullPointerException("Alfabeto no asignado");
        else return alfabeto;
    }

    /**
     * Establece el alfabeto del teclado.
     * @param alf Nuevo alfabeto del teclado.
     */
    public void setAlfabeto(String alf) {
        alfabeto = alf;
    }

    /**
     * Obtiene el nombre del algoritmo utilizado en la generación del teclado.
     * @return Nombre del algoritmo.
     * @throws NullPointerException Si el algoritmo no ha sido asignado.
     */
    public String getAlgoritmo() throws NullPointerException {
        if (nomAlg == null) throw new NullPointerException("Algoritmo aun no asignado");
        else return nomAlg;
    }


    // Modificadoras

    /**
     * Genera un teclado utilizando un algoritmo, un alfabeto y una lista de frecuencias.
     * @param Talg Nombre del algoritmo utilizado.
     * @param alf  Alfabeto utilizado en la generación del teclado.
     * @param frec Lista de frecuencias utilizada en la generación del teclado.
     * @return Matriz de caracteres que representa las teclas del teclado generado.
     */
    public char[][] generarTeclado(String Talg, String alf, HashMap<String,Integer> frec) {
        alfabeto = alf;
        listaFrecuencias = frec;
        nomAlg = Talg;
        
        if (Talg == "QAP") alg = new QAP(frec, alf);
        else if (Talg == "SA") alg = new SimulatedAnnealing(frec, alf);
        teclas = alg.getResultat();
        optimalidad = alg.getOptimalidad();
        return teclas;
    }
    
    /**
     * Modifica un teclado intercambiando dos teclas de posición.
     * @param x 1a tecla a intercambiar
     * @param y 2a tecla a intercambiar
     */
    public void modificarTeclasTeclado(char x, char y) throws NullPointerException {
        if (teclas == null) throw new NullPointerException("Teclas no asignadas");
        int pos1X = -1, pos2X = -1, pos1Y = -1, pos2Y = -1;
        int f = teclas.length;
        int c = teclas[0].length;
        for (int i = 0; i < f; ++i) {
            for (int j = 0; j < c; ++j) {
                if (teclas[i][j] == x) {
                    pos1X = i;
                    pos2X = j;
                }
                else if (teclas[i][j] == y) {
                    pos1Y = i;
                    pos2Y = j;
                }
            }
        }
        if (pos1X == -1 || pos2X == -1 || pos1Y == -1 || pos2Y == -1) 
            throw new NullPointerException("Tecla/s no estan en el teclado");
        else {
            teclas[pos1X][pos2X] = y;
            teclas[pos1Y][pos2Y] = x;
        }
    }
}