package edu.upc.prop.clusterxx.Dominio.algoritmo;
import java.io.Serializable;
import java.util.*;

public class QAP implements Algoritmo, Serializable{
    /*planteamiento: Queremos asignar n teclas a n posiciones
    . Por eso tenemos dos variables: letra 1 va delante de tecla 2.
    Se podria hacer como que la suma del producto de estos
    dos elementos sea máxima (o minima). Asignar teclas mas freq
    a pos mas centricas. Tenemos un
    Linear Assignment Problems == Minimum Weighted Matchings
    Utilizaremos Hungarian Algorithm
    */

    //matriz que guardara las frecuencias entre dos letras para
    //aplciar el algoritmo

    //frecuencias
    //private HashMap<String, Integer> frequencia; //constante

    private char[][] res; //matriu amb el resultat que es retornara al teclat

    private List<Map.Entry<String, Integer>> listFreq; //Hashmap de frequencies passat a llista per comoditat (sort)

    private HashMap<String, Integer> mapFreq; //Frequencies que ens pasa el teclat

    private char[][] matriuGreedy; //solucion inicial greedy (teclat)

    private double[][] taulaDist; //taula de distancies entre les posicions del teclat

    private String alfabet; //alfabet del teclat on estan les tecles que colocar

    private int nColTecles; //columnes que tindra el teclat (suposant que tindrem 3 files)

    private Boolean[] lletresUsades; //per marcar les lletres ja utilitzades

    private AssignacioGreedy assigGreedy; //instancia de solucio inicial

    private double cotaGreedy; //cota associada al teclat inicial

    private char[][] mejorSol; //millor solucio de teclat (senblant a res)
    
    private double mejorCota; //cota del millor teclat fins el moment 

    /**
 * Calcula la distancia euclidiana entre dos puntos en un plano.
 *
 * @param x1 Coordenada x del primer punto.
 * @param y1 Coordenada y del primer punto.
 * @param x2 Coordenada x del segundo punto.
 * @param y2 Coordenada y del segundo punto.
 * @return Distancia euclidiana entre los dos puntos.
 */
private double distEucl(double x1, double y1, double x2, double y2) {
    double distancia = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    return distancia;
}

/**
 * Determina a qué fila del teclado está una posición dada.
 *
 * @param n        Número de columnas en el teclado.
 * @param posicion Posición en el teclado.
 * @return Número de fila correspondiente a la posición.
 */
private int evaluaFila(int n, int posicion) {
    if (posicion < n) return 0; // Número de fila menor a n columnas -> fila 0. Ejemplo: 1, 2, 3
    else if (posicion < 2 * n) return 1;
    else return 2;
}

/**
 * Crea una tabla de distancias entre las posiciones del teclado (representado como una matriz).
 *
 * @param n           Número de columnas en el teclado.
 * @param nColTecles  Número de columnas en el teclado.
 * @return Matriz de distancias entre posiciones del teclado.
 */
private double[][] creaTaulaDist(int n, int nColTecles) {
    int lletresTotal = nColTecles * 3;
    double[][] t = new double[lletresTotal + 1][lletresTotal + 1]; // Añadimos espacio a la tabla
    // La matriz de distancias es cuadrada de n+1 elementos (letras + espacio)
    for (int i = 0; i < lletresTotal + 1; i++) {
        int filaI = evaluaFila(nColTecles, i);
        int colI = i % nColTecles;
        // Como la matriz es simétrica, solo necesitamos iterar en pirámide
        for (int j = 0; j <= i; j++) {
            int filaJ = evaluaFila(nColTecles, j);
            int colJ = j % nColTecles;
            if (i == j) t[i][i] = 0; // Misma posición
            else if (i == lletresTotal) {
                t[i][j] = distEucl(3, n / 2.0, filaJ, colJ);
                t[j][i] = t[i][j]; // Posiciones simétricas
            } else {
                t[i][j] = distEucl(filaI, colI, filaJ, colJ);
                t[j][i] = t[i][j];
            }
        }
    }
    return t;
}

/**
 * Obtiene la posición de una letra en el teclado.
 *
 * @param m Matriz que representa el teclado.
 * @param c Letra a buscar en el teclado.
 * @return Posición de la letra en el teclado.
 */
private int getPosLletra(char[][] m, char c) {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < nColTecles; j++) {
            if (m[i][j] == c) {
                return (nColTecles * i + j); // Posición
            }
        }
    }
    return -1;
}

/**
 * Accede a la tabla de distancias para obtener la distancia entre posiciones.
 *
 * @param i1 Posición 1.
 * @param i2 Posición 2.
 * @return Distancia entre las dos posiciones.
 */
private double getDistancia(int i1, int i2) {
    return taulaDist[i1][i2];
}

/**
 * Obtiene la frecuencia entre dos letras.
 *
 * @param c1 Primera letra.
 * @param c2 Segunda letra.
 * @return Frecuencia entre las dos letras.
 */
private double getFrecuencia(char c1, char c2) {
    String aux = Character.toString(c1) + Character.toString(c2);
    if (mapFreq.containsKey(aux)) return mapFreq.get(aux); // Se accede al mapa de frecuencias
    else return 0;
}

/**
 * Determina si se ha utilizado una letra.
 *
 * @param c Letra a verificar.
 * @return true si la letra ha sido usada, false en caso contrario.
 */
private Boolean esUsat(char c) {
    for (int i = 0; i < alfabet.length(); i++) {
        if (alfabet.charAt(i) == c) {
            if (lletresUsades[i]) return true; // Vector que indica si una letra ha sido usada
            else return false;
        }
    }
    return false;
}

/**
 * Calcula el producto escalar entre dos vectores previamente ordenados.
 *
 * @param l1 Primer vector.
 * @param l2 Segundo vector.
 * @return Producto escalar de los dos vectores.
 */
private double producteEscalarGL(Double[] l1, Double[] l2) {
    double res = 0.0;
    for (int i = 0; i < l1.length; i++) {
        res += l1[i] * l2[l2.length - 1 - i]; // l2 hará recorrido inverso
    }
    return res;
}


    /**
     * Calcula la cota de Gilmore-Lawler para el teclado actual y el número de letras usadas.
     * La cota de Gilmore-Lawler es una medida de la calidad de la asignación de letras en un teclado.
     *
     * @param m     Matriz que representa el teclado actual.
     * @param usats Número de letras ya usadas en el teclado.
     * @return Valor de la cota de Gilmore-Lawler para el teclado actual.
     */
    public double gilmoreLawler(char[][] m, int usats) {
        double res = 0.0; //cota que se devolvera

        int lletres = alfabet.length();

        //primer terme lletres usades sobre no usasdes
        for (int i = 0; i < usats; i++) { //iteramos en orden alfabetico del alfabeto
            char lletra1 = alfabet.charAt(i);
            if(esUsat(lletra1)) {
                int pos1 = getPosLletra(m, lletra1);
                for (int j = 0; j < usats; j++) { //iteramos tambien en orden alfabetico
                    char lletra2 = alfabet.charAt(j);
                    int pos2 = getPosLletra(m, lletra2);
                    //calculamos frecuencia y distancia entre las letras
                    double freq = getFrecuencia(lletra1,lletra2);
                    double dist = getDistancia(pos1, pos2);
                    res += freq*dist;
                }
                //casos especials de distancia y frecuencia con el teclado
                double freqesp = getFrecuencia(lletra1, '_');
                double distesp1 = getDistancia(pos1, lletres);
                res += freqesp*distesp1;
                double freqesp2 = getFrecuencia('_', lletra1);
                double distesp2 = getDistancia(lletres, pos1);
                res += freqesp2*distesp2;
            }
        }

        //Lista donde guardaremos las posiciones de las letras colocadas y no
        List<Integer> listaNoNum = new ArrayList<>();
        List<Integer> listaSiNum = new ArrayList<>();

        int noUsats = lletres - usats;
        
        //guardar posicions no utilitzades
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < nColTecles; j++) {
                if (m[i][j] == '-' && (listaNoNum.size() < noUsats)) {
                    listaNoNum.add(nColTecles*i + j); //posicio
                }
                else listaSiNum.add(nColTecles*i + j);
            }
        }
        //Si no hay letras no usadas no tiene sentido calcular los otros elementos de la cota
        if (listaNoNum.size() > 0) {
            //matrices que simulan la matriz de costes de la cota mencionada en el enunciado
            double c1[][] = new double[noUsats][noUsats];
            double c2[][] = new double[noUsats][noUsats];
            double suma[][] = new double[noUsats][noUsats];

            //calcul C1 no usados sobre usados
            for (int i = 0; i < noUsats; i++) { //iteramos sobre las posiciones de letras no usasdas
                char lletra1 = alfabet.charAt(usats+i); //como ponemos letras en orden alfabetico podemos iterar de forma mas sencilla
                for(int k = 0; k < listaNoNum.size(); k++) { //posiciones de letras no usadas
                    int pos1 = listaNoNum.get(k);
                    double sumterm = 0.0;
                    for (int j = 0; j < usats; j++) { //posiciones de letras usadas
                        char lletra2 = alfabet.charAt(j);
                        //calculo de cota a partir de frecuencia y distancia
                        double freq = getFrecuencia(lletra1,lletra2);
                        int pos2 = getPosLletra(m, lletra2);
                        double dist = getDistancia(pos1, pos2);
                        sumterm += freq*dist;
                    }
                    //casos especiales del espacio
                    double freqesp = getFrecuencia(lletra1, '_');
                    double distesp1 = getDistancia(pos1, lletres);
                    sumterm += freqesp*distesp1;
                    double freqesp2 = getFrecuencia('_', lletra1);
                    double distesp2 = getDistancia(lletres, pos1);
                    sumterm += freqesp2*distesp2;
                    c1[i][k] = sumterm;
                }
            }


            //calcul C2

            for (int i = 0; i < noUsats; i++) { //iteramos sobre las letras no usadas
                char lletra1 = alfabet.charAt(usats+i);
                for(int k = 0; k < listaNoNum.size(); k++) { //no usats y listaNoNum tienen mismo tamaño
                    int pos1 = listaNoNum.get(k);
                    List<Double> distancies = new ArrayList<>();
                    List<Double> frequencies = new ArrayList<>(); 
                    for (int j = 0; j < noUsats; j++) {
                        if (k != j) { //evitamos calcular casos entre las mismas letras
                            //calculo de cota con frecuencia y distancia
                            char lletra2 = alfabet.charAt(usats+j);
                            frequencies.add(getFrecuencia(lletra1, lletra2));
                            int pos2 = listaNoNum.get(k); 
                            distancies.add(getDistancia(pos1, pos2));
                        }
                    }
                    //ordenacion de frecuencia y distancias y aplicacion del producto escalar
                    Collections.sort(frequencies);
                    Collections.sort(distancies, Collections.reverseOrder());
                    c2[i][k] = producteEscalarGL(frequencies.toArray(new Double[frequencies.size()]), distancies.toArray(new Double[distancies.size()]));;
                }
            }

            //sumar c1 i c2 para hacer suma segundo i tercer termino
            for (int i = 0; i < noUsats; i++) {
                for (int j = 0; j < noUsats; j++) {
                    suma[i][j] = c1[i][j] + c2[i][j];
                }
            }

            //se escoge la asignacion optima

            HungarianAlgorithm ha = new HungarianAlgorithm(noUsats);
            res += ha.hungarianLeastCost(suma);
        }

        return res;
    }

    /**
     * Convierte un HashMap a una lista de Map.Entry y la ordena por los valores en orden decreciente.
     *
     * @param f HashMap a convertir y ordenar.
     * @return Lista de Map.Entry ordenada por valores en orden decreciente.
     */
    private List<Map.Entry<String, Integer>> creaLlista(HashMap<String, Integer> f) {

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(f.entrySet());

        // Ordenar la lista por valores en orden creciente
        Collections.sort(entryList, Map.Entry.comparingByValue());

        Collections.reverse(entryList);

        return entryList; 
    }
    
    //copia de la matriz
    private char[][] copiaMatriu(char[][] x) {
        char[][] res = new char[x.length][x[0].length];
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                res[i][j] = x[i][j];
            }
        }
        return res;
    }

    /**
     * Implementación del algoritmo de Branch and Bound para encontrar la asignación óptima
     * de letras en el teclado. Genera un árbol de posibles asignaciones y realiza podas
     * para evitar explorar soluciones no prometedoras.
     *
     * @param level  Nivel actual en el árbol de asignaciones.
     * @param matrix Matriz que representa la disposición actual de letras en el teclado.
     * @param cota   Cota superior actual para la energía total.
     */
    private void branchAndBound(int level, char[][] matrix, double cota) {
        if (level == alfabet.length()) {
            // Verificar si encontramos una solución mejor
            if (cota < mejorCota) {
                mejorCota = cota;
                mejorSol = Arrays.copyOf(matrix, matrix.length);
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < nColTecles; j++) {
                // Crear un nuevo hijo agregando un elemento en la posición actual de la matriz
                if (matrix[i][j] == '-') {
                    char[][] childMatrix = copiaMatriu(matrix);
                    childMatrix[i][j] = alfabet.charAt(level);
                    lletresUsades[level] = true;

                    // Calcular la cota superior para el nuevo hijo
                    double cotaFill = gilmoreLawler(childMatrix, level+1);

                    // Podar si la cota superior es menor que la mejor solución encontrada hasta el momento
                    if (cotaFill < cotaGreedy) {
                        branchAndBound(level + 1, childMatrix, cotaFill);
                    }
                }
            }
        }
    }

     /**
     * Devuelve la disposición óptima de letras en el teclado.
     *
     * @return Matriz que representa la disposición óptima de letras en el teclado.
     */
    public char[][] getResultat() { return res; }

     /**
     * Constructor de la clase QAP.
     *
     * @param f     Mapa que contiene las frecuencias de pares de letras.
     * @param a     Alfabeto que representa las letras a asignar al teclado.
     */
    public QAP(HashMap<String, Integer> f, String a) {
        alfabet = a;
        int nLetr = a.length(); 
        mapFreq = f;
        listFreq = creaLlista(f); //pasar map a llista
        double ncT = Math.ceil(nLetr/3.0); //numero columnes de lletres
        nColTecles = (int)ncT; //pasar el paramatre anterior a int
        taulaDist = creaTaulaDist(nLetr, nColTecles); 
        lletresUsades = new Boolean[nLetr];
        Arrays.fill(lletresUsades, true); 
        //creacio de la solucio inicial i la seva cota
        assigGreedy = new AssignacioGreedy(a, nLetr);
        matriuGreedy = assigGreedy.creaMatriu(listFreq, nLetr, nColTecles);     
        cotaGreedy = gilmoreLawler(matriuGreedy, nLetr);
        Arrays.fill(lletresUsades, false); 
        mejorSol = matriuGreedy;
        mejorCota = cotaGreedy;
        char[][] matriuBuida = new char[3][nColTecles];
        Arrays.fill(matriuBuida[0], '-');
        Arrays.fill(matriuBuida[1], '-');
        Arrays.fill(matriuBuida[2], '-');
        //aplicacio del branch and bound per trobar assignacio optima
        branchAndBound(0, matriuBuida, cotaGreedy);
        res = mejorSol;
    }

        /**
     * Calcula la energía total asociada a la disposición actual de letras en el teclado.
     * La energía se calcula sumando la distancia ponderada entre pares de letras según sus
     * frecuencias.
     *
     * @param teclat Matriz que representa la disposición actual de letras en el teclado.
     * @return Valor que representa la energía total de la disposición actual.
     */
    private double calculaEnergia(char[][] teclat) {
        double sumaTot = 0;

        // Calcular frecuencia total
        int frecTot = 0;
        for (String s : mapFreq.keySet()) {
            frecTot += mapFreq.get(s);
        }

        // Calcular la energía sumando la distancia ponderada entre pares de letras
        for (String par : mapFreq.keySet()) {
            char c1 = par.charAt(0);
            char c2 = par.charAt(1);
            int pos1 = getPosLletra(teclat, c1);
            int pos2 = getPosLletra(teclat, c2);
            double dist = getDistancia(pos1, pos2);
            double fraccioFrec = (double) (mapFreq.get(par)) / frecTot;
            sumaTot += dist * fraccioFrec;
        }

        return sumaTot;
    }


    /**
     * Calcula la optimalidad de la disposición actual de letras en el teclado.
     *
     * @return Valor que representa la optimalidad de la disposición actual.
     */
    public double getOptimalidad() {
        return calculaEnergia(res);
    }
}