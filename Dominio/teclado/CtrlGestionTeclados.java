package edu.upc.prop.clusterxx.Dominio.teclado;

import java.util.HashMap;

import edu.upc.prop.clusterxx.Dominio.Frecuencia.CtrlGestionFrecuencias;
import edu.upc.prop.clusterxx.Dominio.alfabeto.CtrlGestionAlfabetos;

import edu.upc.prop.clusterxx.Persistencia.GestionPersistenciaTeclado;

public class CtrlGestionTeclados {

    /**
     * Relacion con el gestor de teclados
     */
    private GestionTeclados gestorTeclados = new GestionTeclados();

    /**
     * Instancia del controlador de gestion teclados
     */
    private static CtrlGestionTeclados instance;

    /**
     * Relacion con el controlador de alfabetos
     */
    private CtrlGestionAlfabetos ctrlGestorAlfabetos = CtrlGestionAlfabetos.getInstance();

    /**
     * Relacion con el controlador de frecuencias
     */
    private CtrlGestionFrecuencias ctrlGestorFrecuencias = CtrlGestionFrecuencias.getInstance();

    /**
     * Relacion con el gestor de persistencia de teclados
     */
    private GestionPersistenciaTeclado gestorPersistenciaTeclado = new GestionPersistenciaTeclado();

    /**
     * Constructora de la clase
     */
    private CtrlGestionTeclados() {}

    /**
     * Metodo para que el controlador pueda ser accesible desde otros controladores
     */
    public synchronized static CtrlGestionTeclados getInstance() {
        if(instance == null) {
            instance = new CtrlGestionTeclados();
        }
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    /**
     * Metodo para cargar los teclados en el gestor cuando el usuario inicie sesion
     */
    public void cargarTeclados() {
        HashMap<String,char[][]> tecladosCargados = gestorPersistenciaTeclado.cargarTeclados();
        gestorTeclados.cargarTeclados(tecladosCargados);
    }

    /**
     * Devuelve el numero de teclados en el sistema
     * @return Numero de teclados
     */
    public int getNumTeclados() {
        return gestorTeclados.getNumTeclados();
    }

    /**
     * Devuelve si existe o no el teclado pasado como parametro
     * @param idTeclado Identificador del teclado
     * @return Valor booleano que inica si existe o no el teclado con id = idTeclado
     */
    public boolean existeTeclado(String idTeclado) {
        return gestorTeclados.existeTeclado(idTeclado);
    }

    /**
     * Devuelve un objeto Teclado con el identificador pasado como parametro
     * @param id Identificador del teclado
     * @return Teclado con identificador = id
     */
    public Teclado getTeclado(String id) {
        return gestorTeclados.getTeclado(id);
    }

    /**
     * Devuelve la matriz de caracteres (teclas) del teclado con el identificador pasado como parametro
     * @param id Identificador del teclado
     * @return Matriz de chars que representa las teclas del teclado con identificador = id
     */
    public char[][] getTeclasTeclado(String id) {
        return gestorTeclados.getTeclasTeclado(id);
    }

    /**
     * Devuelve la lista de todos los teclados del usuario
     * @return HashMap que contiene todos los teclados del usuario
     */
    public HashMap<String,Teclado> getListaTeclados() {
        return gestorTeclados.getListaTeclados();
    }


    /**
     * Genera un teclado utilizando el algoritmo especificado, alfabeto, y frecuencia.
     * @param idTec Identificador del teclado generado.
     * @param alg Algoritmo utilizado para la generación del teclado.
     * @param idAlf Identificador del alfabeto utilizado en la generación.
     * @param idFrec Identificador de la frecuencia utilizada en la generación.
     * @return Matriz de caracteres que representa las teclas del teclado generado.
     * @throws IllegalArgumentException Si se intenta generar un QAP con un alfabeto mayor a 10 letras.
     */
    public char[][] generarTeclado(String idTec, String alg, String idAlf, String idFrec) {
        String alf = ctrlGestorAlfabetos.consultarAlfabeto(idAlf);
        if (alg == "QAP" & alf.length() > 10) throw new IllegalArgumentException("No es posible generar QAP con un alfabeto mayor a 10 letras\nEl algoritmo se quedaría bloqueado.");
        HashMap<String,Integer> frec = ctrlGestorFrecuencias.getFrecuencia(idFrec);
        return gestorTeclados.generarTeclado(idTec, alg, alf, frec);
    }

    /**
     * Guarda un teclado con el identificador y las teclas especificadas.
     * @param idTec Identificador del teclado a guardar.
     * @param teclas Matriz de caracteres que representa las teclas del teclado.
     */
    public void guardarTeclado(String idTec, char[][] teclas) { 
        gestorTeclados.guardarTeclado(idTec, teclas);
        gestorPersistenciaTeclado.guardarTeclado(idTec, gestorTeclados.getTeclasTeclado(idTec));
    }

    /**
     * Modifica las teclas de un teclado con los caracteres especificados.
     * @param idTeclado Identificador del teclado a modificar.
     * @param x Primera letra a intercambiar
     * @param y Segunda letra a intercambiar
     */
    public void modificarTeclasTeclado(String idTeclado, char x, char y) {
        gestorTeclados.modificarTeclasTeclado(idTeclado, x, y);
        gestorPersistenciaTeclado.borrarTeclado(idTeclado);
        gestorPersistenciaTeclado.guardarTeclado(idTeclado, gestorTeclados.getTeclasTeclado(idTeclado));
    }

    /**
     * Borra un teclado con el identificador especificado.
     * @param idTeclado Identificador del teclado a borrar.
     */
    public void borrarTeclado(String idTeclado) {
        gestorTeclados.borrarTeclado(idTeclado);
        gestorPersistenciaTeclado.borrarTeclado(idTeclado);
    }

    /**
     * Calcula la optimalidad de un teclado generado por el algoritmo.
     * @param teclat Matriz de caracteres que representa las teclas del teclado.
     * @param idFre Identificador de la frecuencia utilizada en el cálculo de optimalidad.
     * @return Valor de optimalidad del teclado.
     */
    public double getOptimalidad(char[][] teclat, String idFre) {
        double sumaTot = 0;
        HashMap<String,Integer> llistaFrequencies = ctrlGestorFrecuencias.getFrecuencia(idFre);

        for (String par : llistaFrequencies.keySet()) {
            char c1 = par.charAt(0);
            char c2 = par.charAt(1);
            double dist = distEucl(teclat, c1, c2);
            double fraccioFrec = (double)(llistaFrequencies.get(par)); 
            sumaTot += dist * fraccioFrec;
        }

        return sumaTot;
    }

    /**
     * Calcula la optimalidad de un teclado generado por el algoritmo con una lista de frecuencias.
     * @param teclat Matriz de caracteres que representa las teclas del teclado.
     * @param llistaFrequencies Lista de frecuencias utilizada en el cálculo de optimalidad.
     * @return Valor de optimalidad del teclado.
     */
    public double getOptimalidadConLista(char[][] teclat, HashMap<String,Integer> llistaFrequencies) {
        double sumaTot = 0;

        for (String par : llistaFrequencies.keySet()) {
            char c1 = par.charAt(0);
            char c2 = par.charAt(1);
            double dist = distEucl(teclat, c1, c2);
            double fraccioFrec = (double)(llistaFrequencies.get(par)); 
            sumaTot += dist * fraccioFrec;
        }

        return sumaTot;
    }

    /**
     * Distancia euclidiana entre dos teclas de la matriz
     * @param teclat Matriz de chars que representa el estado actual en el algoritmo
     * @param c1 Una de las teclas que se intercambian
     * @param c2 Otra de las teclas que se intercambian
     * @return Valor que representa la distancia euclidiana entre c1 y c2
     */
    private double distEucl(char[][] teclat, char c1, char c2) {

        int f = teclat.length;
        int c = teclat[0].length;

        double x1 = 0;
        double y1 = 0;
        double x2 = 0;
        double y2 = 0;

        // trobar coords de les lletres c1 i c2 a la matriu
        for (int i = 0; i < f; ++i) {
            for (int j = 0; j < c; ++j) {
                if (teclat[i][j] == c1) {
                    x1 = j; y1 = i;
                }
                else if (teclat[i][j] == c2) {
                    x2 = j; y2 = i;
                }
            }
        }

        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    /**
     * Borra los datos del gestor
     */
    public void clear() {
        gestorTeclados.clear();
    }
}