package edu.upc.prop.clusterxx.Dominio.algoritmo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Random;

/**
 * Clase del algoritmo Simulated Annealing, contiene todas las funciones 
 * necesarias para el algoritmo y se ejecutara cuando se llame a su constructor
 */
public class SimulatedAnnealing implements Algoritmo {

    /**
     * Matriz donde se guarda el resultado del algoritmo
     */
    private char[][] teclatRes; 

    /**
     * Lista de palabras de frecuencias que se pasa como parametro al algoritmo
     * para la generacion del teclado optimo
     */
    private HashMap<String,Integer> llistaFrequencies = new HashMap<>(); 

    /**
     * Alfabeto pasado como parametro al algoritmo 
     * para la generación del teclado óptimo
     */
    private String alfabet; 

    /**
     * Solucion inicial: algoritmo Greedy que minimiza la distancia entre tecles consecutivas,
     * dejando margen de mejora para la exploracion con Simulated Annealing
     * @return Matriz de chars (teclado) que representa la solucion inicial
     */
    private char[][] solIni() {

        // 1: Ordenar els parells de chars per frequencia decreixentment
        List<Map.Entry<String, Integer>> llistaOrdenada = new ArrayList<>(llistaFrequencies.entrySet());
        llistaOrdenada.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // 2: Passar els parells de caracters ordenats per frequencia a un array
        char[] vecOrdenat = new char[llistaOrdenada.size()*2];
        int ind = 0;
        for (Map.Entry<String, Integer> ll : llistaOrdenada) {
            char[] str = ll.getKey().toCharArray();
            for (char c : str) {
                vecOrdenat[ind] = c;
                ++ind;
            }
        }

        // 3: Inicialitzar la matriu
        int nLletres = alfabet.length();
        int f = 3;
        int c = (int) Math.ceil((double) nLletres / 3);
        char[][] resIni = new char[f][c];

        // 4: Recorrer la matriu assignan els chars de vecOrdenat de forma que estiguin consecutius

        // conjunt per tenir una actualitzacio de les lletres que hem posat (per si es repeteixen a la llista de frecs)
        Set<Character> lletresAssignades = new HashSet<>();
        ind = 0; // index per recorrer el vector de lletres amb frecuencia ordenades
        boolean assignada;

        // s assignen nomes els caracters que pertanyin a la llista de frequencies
        for (int i = 0; i < f; ++i) {
            for (int j = 0; j < c; ++j) {
                if (lletresAssignades.size() == nLletres) { // ja hem posat totes les lletres
                    resIni[i][j] = '-';
                }
                else { // queden lletres per posar
                    assignada = false;
                    while (!assignada) {
                        if (ind < vecOrdenat.length) { // queden lletres de la llista de frequencies per posar
                            char lletra = vecOrdenat[ind];
                            if (!lletresAssignades.contains(lletra)) {
                                resIni[i][j] = lletra;
                                lletresAssignades.add(lletra);
                                assignada = true;
                            }
                            ++ind;
                        }
                        else break; // no queden lletres de la llista de frequencies per posar
                    }
                }
            }
        }

        // 5: assignar la resta de lletres de l'alfabet
        char[] arrayAlf = alfabet.toCharArray();
        ind = 0; // index per recorrer l'alfabet
        for (int i = 0; i < f; ++i) {
            for (int j = 0; j < c; ++j) {
                if (resIni[i][j] < 'a' || resIni[i][j] > 'z') { // si la casella encara és buida
                    boolean assig = false;
                    while (!assig) {
                        if (ind < arrayAlf.length) { // encara queden lletres per posar
                            char ll = arrayAlf[ind];
                            if (!lletresAssignades.contains(ll)) {
                                resIni[i][j] = ll;
                                assig = true;
                            }
                            ++ind; 
                        }
                        else { // ja no queden lletres per posar --> es posa espai
                            resIni[i][j] = '-';
                            assig = true;
                        }
                    }
                }
            }
        }
        
        return resIni;
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
     * Calcula la energia del estado representado per la matriz teclado:
     * suma total de distancias euclidianas entre dos letras consecutivas de la lista
     * de frecuencias dada, donde estas distancias son ponderadas segun su frecuencia
     * @param teclat matriz de chars que representa un estado en el algoritmo
     * @return Valor de la energia del estado representado por la matriz teclat
     */
    private double calculaEnergia(char[][] teclat) {
        double sumaTot = 0;

        for (String par : llistaFrequencies.keySet()) {
            char c1 = par.charAt(0);
            char c2 = par.charAt(1);
            double dist = distEucl(teclat, c1, c2);
            double fraccioFrec = (double)(llistaFrequencies.get(par)); // calcula la fraccio que representa la frequencia sobre el total
            sumaTot += dist * fraccioFrec;
        }

        return sumaTot;
    }
    
    /**
     * Genera un succesor a partir del estado actual:
     * hace swap entre dos teclas
     * @param estatActual Matriz de chars que representa el estado actual en el algoritmo
     * @return Matriz de chars que representa el estado succesor a estatActual despues de haber aplicado el operador
     */
    private char[][] generaSuccessor(char[][] estatActual) {

        int filas = estatActual.length;
        int cols = estatActual[0].length;

        // clonar matriu 'estatActual' a la matriu 'nouEstat'
        char[][] nouEstat = new char[filas][cols];
        for (int i = 0; i < filas; i++) {
            nouEstat[i] = Arrays.copyOf(estatActual[i], cols);
        }
        
        // seleccionar els 2 elements de la matriu a intercambiar aleatoriament
        // s'ha d'assegurar que no s'intercambia per una posicio buida ' '
        Random rand = new Random();
        boolean valid;
        int f1 = 0, c1 = 0, f2 = 0, c2 = 0;

        valid = false;
        while (!valid) {
            f1 = rand.nextInt(filas);
            c1 = rand.nextInt(cols);
            if (estatActual[f1][c1] != '-') valid = true;
        }

        valid = false;
        while (!valid) {
            f2 = rand.nextInt(filas);
            c2 = rand.nextInt(cols);
            if (estatActual[f2][c2] != '-') valid = true;
        }

        // swap
        char temp = estatActual[f1][c1];
        nouEstat[f1][c1] = estatActual[f2][c2];
        nouEstat[f2][c2] = temp;

        return nouEstat;
    }
    
    /**
     * Función que decide segun la probabilidad de Bolltzmann y otros parametros
     * como la temperatura y el aumento de energia si un nuevo estado es aceptado o no
     * @param diffE Diferencia de energia entre el estado actual y el succesor en el algoritmo
     * @param Tact Temperatura actual en el algoritmo
     * @return Valor booleano que indica si se acepta el estado actual o no
     */
    private boolean acceptat(double diffE, double Tact) {
        Random rand = new Random();
        if (diffE < 0) return true;
        else {
            double r = rand.nextDouble(); // Real uniformement distribuit en el rang [0,1)
            if (r < Math.exp(-diffE/Tact)) // Distribució de probabilitat de Boltzmann 
                return true;
            else 
                return false;
        }
    }

    /**
     * Constructora del algoritmo, 
     * es la funcion encargada de ejecutar el propio algoritmo
     * @param listaFrec Lista de frecuencias con la que se genera el teclado
     * @param alf Alfabeto con el que se genera el teclado
     */
    public SimulatedAnnealing(HashMap<String,Integer> listaFrec, String alf) {

        llistaFrequencies = listaFrec;
        alfabet = alf;
        
        // Inicialitzacio de la temperatura actual (la maxima a l inici) i la minima
        double Tact = 1000.0; 
        double Tmin = 0.0001; 

        // Generació de l'estat inicial i càlcul de la energia
        char [][] estatAct = solIni();
        double Eact = calculaEnergia(estatAct);

        // Inicialitzacio de la temperatura minima
        double Emin = 0.0; 

        // Valor del factor decrementador de temperatura
        double alpha = 0.9999; 

        double Enou, diffE;
        char[][] estatNou;
        //int Niter = 0;
        while (Tact > Tmin && Eact > Emin) {
            estatNou = generaSuccessor(estatAct);
            Enou = calculaEnergia(estatNou);
            diffE = Enou - Eact;

            if (acceptat(diffE, Tact)) {
                estatAct = estatNou;
                Eact = Enou;
                //++Niter;
            }

            Tact = Tact*alpha;
        }
        teclatRes = estatAct;
    }
    
    /**
     * Metodo que devuelve el teclado resultante de aplicar el algoritmo
     * @return Matriz que representa el teclado resultante
     */
    public char[][] getResultat() { return teclatRes; }

    /**
     * Metodo que devuelve la optimalidad del teclado generado por el algoritmo
     * @return Valor de la optimalidad del teclado obtenido
     */
    public double getOptimalidad() {
        return calculaEnergia(teclatRes);
    }
}
