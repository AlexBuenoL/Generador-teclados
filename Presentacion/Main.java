package edu.upc.prop.clusterxx.Presentacion;

/**
 * Clase principal que contiene el método main para iniciar la aplicación.
 */
public class Main {
    
    /**
     * Método principal que inicia la aplicación utilizando el patrón MVC.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(
            new Runnable() {
                /**
                 * Método run que se ejecuta en el hilo de despacho de eventos de Swing.
                 */
                public void run() {
                    // Crear una instancia del controlador de presentación
                    CtrlPresentacion ctrlPresentacion = new CtrlPresentacion();
                    
                    // Iniciar la aplicación a través del controlador de presentación
                    ctrlPresentacion.iniciarApp();
                }
            }
        );
    }
}

