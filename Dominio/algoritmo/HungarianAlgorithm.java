package edu.upc.prop.clusterxx.Dominio.algoritmo;

import java.io.Serializable;
import java.util.Arrays;

public class HungarianAlgorithm implements Serializable {

    //private static final double INF = 1e8;

    private int N;
    private double maxMatch;
    private double[] priorityKeys;
    private double[] priorityPositions;
    private int[] keyToPosition;
    private int[] positionToKey;
    private boolean[] S;
    private boolean[] T;
    private double[] minCostToAssign;
    private int[] keyCausingMinCost;
    private int[] previousKeyInTree;

    /**
     * Constructor que inicializa el algoritmo con un tamaño específico.
     *
     * @param n El tamaño de la matriz, que es el número de teclas o posiciones.
     */
    public HungarianAlgorithm(int n) {
        N = n;
        maxMatch = 0;
        priorityKeys = new double[N];
        priorityPositions = new double[N];
        keyToPosition = new int[N];
        positionToKey = new int[N];
        S = new boolean[N];
        T = new boolean[N];
        minCostToAssign = new double[N];
        keyCausingMinCost = new int[N];
        previousKeyInTree = new int[N];
    }

    /**
     * Añade una tecla al árbol de expansión del algoritmo húngaro y actualiza la información de 'slack'.
     *
     * @param current La tecla actual que se está considerando para la asignación.
     * @param prev La tecla previa conectada a la actual en el árbol de expansión.
     * @param cost La matriz de costos que representa las incompatibilidades entre teclas y posiciones.
     */
    private void addToTree(int current, int prev, double[][] cost) {
        double slackForNewNode;

        S[current] = true;
        previousKeyInTree[current] = prev;

        for (int i = 0; i < N; ++i) {
            if (!T[i]) {
                slackForNewNode = priorityKeys[current] + priorityPositions[i] - cost[current][i];
                if (slackForNewNode < minCostToAssign[i]) {
                    minCostToAssign[i] = slackForNewNode;
                    keyCausingMinCost[i] = current;
                }
            }
        }
    }

    /**
     * Inicializa las etiquetas (prioridades) de las teclas y las posiciones.
     *
     * @param cost La matriz de costos que representa las incompatibilidades entre teclas y posiciones.
     */
    private void initLabels(double[][] cost) {
        Arrays.fill(priorityPositions, 0);
        Arrays.fill(priorityKeys, Integer.MIN_VALUE);

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                priorityKeys[i] = Math.max(priorityKeys[i], cost[i][j]);
            }
        }

        Arrays.fill(keyToPosition, -1);
        Arrays.fill(positionToKey, -1);
    }

    /**
     * Actualiza las etiquetas (prioridades) de las teclas y las posiciones para el método de etiquetado.
     */
    private void updateLabels() {
        double delta = Double.MAX_VALUE;

        for (int i = 0; i < N; ++i) {
            if (!T[i]) {
                delta = Math.min(delta, minCostToAssign[i]);
            }
        }

        for (int i = 0; i < N; ++i) {
            if (S[i]) {
                priorityKeys[i] -= delta;
            }
            if (T[i]) {
                priorityPositions[i] += delta;
            }
        }

        for (int i = 0; i < N; ++i) {
            if (!T[i]) {
                minCostToAssign[i] -= delta;
            }
        }
    }

    /**
     * Intenta aumentar el número máximo de asignaciones posibles.
     *
     * @param cost La matriz de costos que representa las incompatibilidades entre teclas y posiciones.
     */
    private void augment(double[][] cost) {
        if (maxMatch == N) {
            return;
        }

        Arrays.fill(S, false);
        Arrays.fill(T, false);
        Arrays.fill(previousKeyInTree, -1);

        int[] queue = new int[N];
        int qHead = 0;
        int qTail = 0;

        int root = -1;
        for (int i = 0; i < N; ++i) {
            if (keyToPosition[i] == -1) {
                queue[qTail++] = i;
                root = i;
                previousKeyInTree[i] = -2;
                S[i] = true;
                break;
            }
        }

        for (int j = 0; j < N; ++j) {
            minCostToAssign[j] = priorityKeys[root] + priorityPositions[j] - cost[root][j];
            keyCausingMinCost[j] = root;
        }

        int x = -1;
        int y = -1;
        while (true) {
            while (qHead < qTail) {
                x = queue[qHead++];
                for (y = 0; y < N; y++) {
                    if (cost[x][y] == priorityKeys[x] + priorityPositions[y] && !T[y]) {
                        if (positionToKey[y] == -1) {
                            break;
                        }
                        T[y] = true;
                        queue[qTail++] = positionToKey[y];
                        addToTree(positionToKey[y], x, cost);
                    }
                }
                if (y < N) {
                    break;
                }
            }
            if (y < N) {
                break;
            }

            updateLabels();

            qHead = 0;
            qTail = 0;

            for (y = 0; y < N; y++) {
                if (!T[y] && minCostToAssign[y] == 0) {
                    if (positionToKey[y] == -1) {
                        x = keyCausingMinCost[y];
                        break;
                    } else {
                        T[y] = true;
                        if (!S[positionToKey[y]]) {
                            queue[qTail++] = positionToKey[y];
                            addToTree(positionToKey[y], keyCausingMinCost[y], cost);
                        }
                    }
                }
            }
            if (y < N) {
                break;

            }
        }

        if (y < N) {
            maxMatch++;
            for (int cx = x, cy = y, ty; cx != -2; cx = previousKeyInTree[cx], cy = ty) {
                ty = keyToPosition[cx];
                positionToKey[cy] = cx;
                keyToPosition[cx] = cy;
            }
            augment(cost);
        }
    }

    /**
     * Calcula la asignación con el menor costo total utilizando el algoritmo húngaro.
     *
     * @param cost La matriz de costos que representa las incompatibilidades entre teclas y posiciones.
     * @return El costo total de la asignación óptima.
     */
    public double hungarianLeastCost(double[][] cost) {
        double maxCost = 0.0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                cost[i][j] = maxCost - cost[i][j];
            }
        }

        initLabels(cost);
        augment(cost);

        double totalCost = 0;
        for (int i = 0; i < N; ++i) {
            totalCost += cost[i][keyToPosition[i]];
        }
        totalCost = maxCost * N - totalCost; // Adjust cost back to the original
        return totalCost;
    }
}
