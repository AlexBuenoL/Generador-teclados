package edu.upc.prop.clusterxx.Presentacion;

import java.util.*;
import javax.swing.*;

import edu.upc.prop.clusterxx.Dominio.teclado.*;

/**
 * La clase `CtrlTecladosPresentacion` es el controlador encargado de gestionar las operaciones
 * relacionadas con los teclados en la capa de presentación.
 */

public class CtrlTecladosPresentacion {
    private VistaGenerarTeclado vistaGenerarTeclado;
    private VistaCrearDosTeclados vistaCrearDosTeclados;
    private VistaCrearUnTeclado vistaCrearUnTeclado;
    private VistaGestionTeclado vistaGestionTeclado;
    private VistaMostrarTeclado vistaMostrarTeclado;
    private VistaModificarTeclado vistaModificarTeclado;
    private VistaBorrarTeclado vistaBorrarTeclado;
    private CtrlPresentacion ctrlPresentacion;
    private CtrlDatosPresentacion ctrlDatosPresentacion;
    private CtrlGestionTeclados ctrlGestionTeclados;

     /**
     * Obtiene el controlador de presentación principal asociado.
     *
     * @return El controlador de presentación principal.
     */
    public CtrlPresentacion getCtrlPresentacion() {
        return ctrlPresentacion;
    }

     /**
     * Carga los teclados desde el almacenamiento.
     */
    public void cargarTeclados() {
        ctrlGestionTeclados.cargarTeclados();
    }

     /**
     * Escribe la representación de un teclado en una cadena y la muestra en la vista correspondiente.
     *
     * @param id     Identificador del teclado.
     * @param teclas Matriz de caracteres representando el teclado.
     * @param vista  Nombre de la vista donde se mostrará la información.
     */
    private void escribirTeclado(String id, char[][] teclas, String vista) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id: ").append(id).append("\n");
    
        int n = teclas.length, m = teclas[0].length;
        for (int i = 0; i < n; ++i) {
            stringBuilder.append(teclas[i][0]);
            for (int j = 1; j < m; ++j) {
                stringBuilder.append(" ").append(teclas[i][j]);
            }
            stringBuilder.append("\n");
        }
    
        if(vista == "mostrar") vistaMostrarTeclado.setText(stringBuilder.toString());
    }

     /**
     * Escribe la representación de un teclado generado en una cadena y la muestra en la vista correspondiente.
     *
     * @param teclas Matriz de caracteres representando el teclado generado.
     * @param vista  Nombre de la vista donde se mostrará la información.
     */
    private void escribirTecladoGenerar(char[][] teclas, String vista) {
        StringBuilder stringBuilder = new StringBuilder();

        int n = teclas.length, m = teclas[0].length;
        for (int i = 0; i < n; ++i) {
            stringBuilder.append(teclas[i][0]);
            for (int j = 1; j < m; ++j) {
                stringBuilder.append(" ").append(teclas[i][j]);
            }
            stringBuilder.append("\n");
        }
    
        if(vista == "generar") vistaCrearUnTeclado.setText(stringBuilder.toString());
        else if(vista == "generar1") vistaCrearDosTeclados.setTextTeclado1(stringBuilder.toString());
        else vistaCrearDosTeclados.setTextTeclado2(stringBuilder.toString());
    }
    
     /**
     * Escribe la representación de una lista de teclados en una cadena y la muestra en la vista correspondiente.
     *
     * @param teclados        Mapa de teclados con sus identificadores como claves.
     * @param ctrlGestorTeclados Controlador de gestión de teclados.
     * @param vista           Nombre de la vista donde se mostrará la información.
     */
    private void escribirListaTeclados(HashMap<String,Teclado> teclados, CtrlGestionTeclados ctrlGestorTeclados, String vista) {
        StringBuilder stringBuilder = new StringBuilder();
    
        for (String id : teclados.keySet()) {
            stringBuilder.append("Id: ").append(id).append("\n");
    
            char[][] teclas = ctrlGestorTeclados.getTeclasTeclado(id);
            int n = teclas.length, m = teclas[0].length;
    
            for (int i = 0; i < n; ++i) {
                stringBuilder.append(teclas[i][0]);
                for (int j = 1; j < m; ++j) {
                    stringBuilder.append(" ").append(teclas[i][j]);
                }
                stringBuilder.append("\n");
            }
    
            stringBuilder.append("-----------------------------------\n");
        }
    
        if(vista=="mostrar") vistaMostrarTeclado.setText(stringBuilder.toString());
        else if(vista == "borrar") vistaBorrarTeclado.setText(stringBuilder.toString());
        else if(vista == "modificar") vistaModificarTeclado.setText(stringBuilder.toString());
    }

    /**
     * Constructor de la clase `CtrlTecladosPresentacion`.
     *
     * @param ctrlPresentacion    El controlador de presentación principal.
     * @param ctrlDatosPresentacion El controlador de presentación de datos.
     */
    public CtrlTecladosPresentacion(CtrlPresentacion ctrlPresentacion, CtrlDatosPresentacion ctrlDatosPresentacion) {
        this.ctrlPresentacion = ctrlPresentacion;
        this.vistaGenerarTeclado = new VistaGenerarTeclado(this);
        this.vistaCrearDosTeclados = new VistaCrearDosTeclados(this);
        this.vistaCrearUnTeclado = new VistaCrearUnTeclado(this);
        this.vistaGestionTeclado = new VistaGestionTeclado(this);
        this.vistaMostrarTeclado = new VistaMostrarTeclado(this);
        this.vistaModificarTeclado = new VistaModificarTeclado(this);
        this.vistaBorrarTeclado = new VistaBorrarTeclado(this);
        this.ctrlDatosPresentacion = ctrlDatosPresentacion;
        this.ctrlGestionTeclados = CtrlGestionTeclados.getInstance();
    }

    /**
 * Muestra la vista de generación de teclado.
 * Realiza la carga de alfabetos y frecuencias antes de mostrar la vista.
 */
    public void mostrarVistaGenerarTeclado() {
        mostrarTodosAlfabetos();
        mostrarTodasFrecuencias();
        vistaGenerarTeclado.mostrar();
        ctrlPresentacion.ocultarVistaInicial();
    }

    /**
 * Muestra la vista de creación de dos teclados.
 *
 * @param id       Identificador de los teclados.
 * @param teclado1 Matriz de caracteres representando el primer teclado.
 * @param teclado2 Matriz de caracteres representando el segundo teclado.
 */
    public void mostrarVistaCrearDosTeclados(String id, char[][] teclado1, char[][] teclado2) {
        vistaCrearDosTeclados.asignarValores(id, teclado1, teclado2);
        vistaCrearDosTeclados.mostrar();
        vistaGenerarTeclado.ocultar();
    }

    /**
 * Muestra la vista de creación de un teclado.
 *
 * @param id      Identificador del teclado.
 * @param teclado Matriz de caracteres representando el teclado.
 */
    public void mostrarVistaCrearUnTeclado(String id, char[][] teclado) {
        vistaCrearUnTeclado.asignarValores(id, teclado);
        vistaCrearUnTeclado.mostrar();
        vistaGenerarTeclado.ocultar();
    }

    /**
 * Muestra la vista de gestión de teclados.
 */
    public void mostrarVistaGestionTeclado() {
        vistaGestionTeclado.mostrar();
        ctrlPresentacion.ocultarVistaInicial();
    }

    /**
 * Muestra la vista de mostrar un teclado.
 * Limpia los campos antes de mostrar la vista.
 */
    public void mostrarVistaMostrarTeclado() {
        vistaMostrarTeclado.limpiarCampos();
        vistaMostrarTeclado.mostrar();
        vistaGestionTeclado.ocultar();
    }

    /**
 * Muestra la vista de modificar un teclado.
 */
    public void mostrarVistaModificarTeclado() {
        vistaModificarTeclado.mostrar();
        vistaGestionTeclado.ocultar();
    }

    /**
 * Muestra la vista de borrar un teclado.
 */
    public void mostrarVistaBorrarTeclado() {
        vistaBorrarTeclado.mostrar();
        vistaGestionTeclado.ocultar();
    }

    /**
 * Muestra la vista inicial, ocultando otras vistas relacionadas con teclados.
 */
    public void mostrarVistaInicial() {
        ctrlPresentacion.mostrarVistaInicial();
        vistaGenerarTeclado.ocultar();
        vistaGestionTeclado.ocultar();
    }

    /**
 * Muestra la vista de añadir alfabetos y frecuencias, ocultando la vista de generación de teclado.
 */
    public void mostrarVistaAñadirAlfFre() {
        ctrlPresentacion.mostrarVistaAñadirAlfFre();
        vistaGenerarTeclado.ocultar();
    }

    /**
 * Muestra todos los alfabetos en la vista de generación de teclado.
 * Captura las posibles excepciones y maneja la visualización de mensajes de error.
 */
    public void mostrarTodosAlfabetos() {
        try {
            String allAlfs = ctrlDatosPresentacion.mostrarTodosAlfabetosGenerar();
            vistaGenerarTeclado.setTextAlf(allAlfs);
        } catch (NullPointerException | NoSuchElementException e) {
            vistaGenerarTeclado.setTextAlf("No hay alfabetos en el sistema");
        }
    }

    /**
 * Muestra todas las frecuencias en la vista de generación de teclado.
 * Captura las posibles excepciones y maneja la visualización de mensajes de error.
 */
    public void mostrarTodasFrecuencias() {
        try {
            String freqs = ctrlDatosPresentacion.mostrarTodasFrecuenciasGenerar();
            vistaGenerarTeclado.setTextFre(freqs);
        } catch (NullPointerException | NoSuchElementException e) {
            vistaGenerarTeclado.setTextFre("No hay frecuencias en el sistema");
        }
    }

    /**
 * Muestra un teclado específico en la vista MostrarTeclado.
 * Captura las posibles excepciones y maneja la visualización de mensajes de error.
 */
    public void mostrarTeclado() {
        try {
            String id = JOptionPane.showInputDialog("Escriba la ID de su teclado:");
            char[][] teclas = ctrlGestionTeclados.getTeclasTeclado(id);
            escribirTeclado(id, teclas, "mostrar");
        } catch (NullPointerException e) {
            vistaMostrarTeclado.mostrarMensajeError(e.getMessage());
        }
    }

    /**
     * Muestra un teclado específico en la vista GenerarUnTeclado.
     *
     * @param teclas Matriz de caracteres representando el teclado.
     * @param vista  Identificador de la vista en la que se muestra el teclado.
     */
    public void mostrarTecladoGenerar(char[][] teclas, String vista) {
            escribirTecladoGenerar(teclas, vista);
    }

    /**
 * Muestra todos los teclados en la vista MostrarTeclado.
 * Captura las posibles excepciones y maneja la visualización de mensajes de error.
 */
    public void mostrarTodosTeclados() {
        try {
            HashMap<String,Teclado> teclados = ctrlGestionTeclados.getListaTeclados();
            escribirListaTeclados(teclados, ctrlGestionTeclados, "mostrar");
        } catch (NoSuchElementException e) {
            vistaMostrarTeclado.mostrarMensajeError(e.getMessage());
        }  
    }

    /**
 * Muestra todos los teclados en la vista BorrarTeclado.
 * Captura las posibles excepciones y maneja la visualización de mensajes de error.
 */
    public void mostrarTodosTecladosBorrar() {
        try {
            HashMap<String,Teclado> teclados = ctrlGestionTeclados.getListaTeclados();
            escribirListaTeclados(teclados, ctrlGestionTeclados, "borrar");
        } catch (NoSuchElementException e) {
            vistaBorrarTeclado.setText("No hay teclados en el sistema");
        }  
    }

    /**
 * Muestra todos los teclados en la vista ModificarTeclado.
 * Captura las posibles excepciones y maneja la visualización de mensajes de error.
 */
    public void mostrarTodosTecladosModifcar() {
        try {
            HashMap<String,Teclado> teclados = ctrlGestionTeclados.getListaTeclados();
            escribirListaTeclados(teclados, ctrlGestionTeclados, "modificar");
        } catch (NoSuchElementException e) {
            vistaModificarTeclado.setText("No hay teclados en el sistema");
        }  
    }

    /**
 * Genera un teclado y muestra los resultados en las vistas correspondientes.
 *
 * @param idTeclado Identificador del teclado a generar.
 * @param algoritmo Algoritmo de generación a utilizar.
 * @param idAlf     Identificador del alfabeto a utilizar.
 * @param idFre     Identificador de la frecuencia a utilizar.
 */
    public void generarTeclado(String idTeclado, String algoritmo, String idAlf, String idFre) {
        try {
            if(idTeclado.isEmpty()) throw new NullPointerException("Debes introducir un id de teclado a añadir");
            if(idAlf.isEmpty()) throw new NullPointerException("Debes introducir un id de alfabeto a añadir");
            if(idFre.isEmpty()) throw new NullPointerException("Debes introducir un id de frecuencia a añadir");
            if(!ctrlDatosPresentacion.comprobarFrecuenciasEnAlfabeto(idAlf, idFre)) {
                throw new IllegalArgumentException("Las letras de la frecuencia deben estar presentes en el alfabeto");
            }
            if(!ctrlDatosPresentacion.existeAlf(idAlf)) vistaGenerarTeclado.mostrarMensajeError("No existe el alfabeto con id " + idAlf);
            else if(!ctrlDatosPresentacion.existeFre(idFre)) vistaGenerarTeclado.mostrarMensajeError("No existe la frecuencia con id " + idFre);
            else {
                if(algoritmo != "both") {
                    char[][] teclado = ctrlGestionTeclados.generarTeclado(idTeclado, algoritmo, idAlf, idFre);
                    mostrarTecladoGenerar(teclado, "generar");
                    mostrarVistaCrearUnTeclado(idTeclado, teclado);
                }
                else {
                    char[][] teclado1 = ctrlGestionTeclados.generarTeclado(idTeclado, "QAP", idAlf, idFre);
                    double optQAP = ctrlGestionTeclados.getOptimalidad(teclado1, idFre);
                    char[][] teclado2 = ctrlGestionTeclados.generarTeclado(idTeclado, "SA", idAlf, idFre);
                    double optSA = ctrlGestionTeclados.getOptimalidad(teclado2, idFre);

                    double valorQAP = (optQAP / (optQAP+optSA))*100;
                    double valorSA = (optSA / (optQAP+optSA))*100;

                    String valorQAPString = Double.toString(valorQAP);
                    String valorSAString = Double.toString(valorSA);

                    vistaCrearDosTeclados.setOptQAP(valorQAPString);
                    vistaCrearDosTeclados.setOptSA(valorSAString);

                    mostrarTecladoGenerar(teclado1, "generar1");
                    mostrarTecladoGenerar(teclado2, "generar2");
                    mostrarVistaCrearDosTeclados(idTeclado, teclado1, teclado2);
                }
            }  
        } catch (NullPointerException e) {
            vistaGenerarTeclado.mostrarMensajeError(e.getMessage());
        } catch (IllegalArgumentException e) {
            vistaGenerarTeclado.mostrarMensajeError(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            vistaGenerarTeclado.mostrarMensajeError("Error inesperado!!");
        }      
    }

    /**
 * Guarda un teclado en el sistema y muestra mensajes informativos o de error según la operación.
 *
 * @param guardar Indica si se debe guardar el teclado.
 * @param id      Identificador del teclado.
 * @param teclas  Matriz de caracteres representando el teclado.
 */
    public void guardarTeclados(boolean guardar, String id, char[][] teclas) {
        if(guardar) {
            ctrlGestionTeclados.guardarTeclado(id, teclas);
            vistaCrearDosTeclados.mostrarMensajeInform("Teclado guardado correctamente");
        }
        else {
            vistaCrearDosTeclados.mostrarMensajeError("No has seleccionado ningun teclado");
        }
    }

    /**
 * Borra un teclado del sistema y muestra mensajes informativos o de error según la operación.
 *
 * @param id Identificador del teclado a borrar.
 */
    public void borrarTeclado(String id) {
        try {
            if(id.isEmpty()) throw new NullPointerException("Debes introducir un id de teclado a borrar");
            ctrlGestionTeclados.borrarTeclado(id);
            vistaBorrarTeclado.mostrarMensajeInform("Teclado con id " + id + " borrado!");
        } catch (NullPointerException e) {
            vistaBorrarTeclado.mostrarMensajeError(e.getMessage());
        } catch (NoSuchElementException e1) {
            vistaBorrarTeclado.mostrarMensajeError(e1.getMessage());
        } 
    }

    /**
 * Modifica las teclas de un teclado y muestra mensajes informativos o de error según la operación.
 *
 * @param id Identificador del teclado a modificar.
 * @param x  Nueva letra para la primera tecla.
 * @param y  Nueva letra para la segunda tecla.
 */
    public void modificarTeclado(String id, String x, String y) {
        try {
            if(id.isEmpty()) throw new NullPointerException("Debes introducir un id de teclado a borrar");
            if(x.length() > 1 | y.length() > 1) throw new InputMismatchException("Solo puedes introducir una tecla por campo");
            if(x.isEmpty() | y.isEmpty()) throw new InputMismatchException("Has de introducir las letras a modificar");
            char xc = x.charAt(0);
            char yc = y.charAt(0);
            if(!Character.isLetter(xc) | !Character.isLetter(yc)) throw new InputMismatchException("Las teclas han de ser letras");
            ctrlGestionTeclados.modificarTeclasTeclado(id, xc, yc);
            vistaModificarTeclado.mostrarMensajeInform("Teclas intercambiadas con éxito!");
        } catch (InputMismatchException e) {
            vistaModificarTeclado.mostrarMensajeError(e.getMessage());
        } catch (NoSuchElementException e1) {
            vistaModificarTeclado.mostrarMensajeError(e1.getMessage());
        } catch (NullPointerException e2) {
            vistaModificarTeclado.mostrarMensajeError(e2.getMessage());
        }
    }

    /**
 * Muestra información sobre la optimalidad de dos teclados en la vista de creación de dos teclados.
 */
    public void mostrarInfo() {
        vistaCrearDosTeclados.mostrarMensajeInform("La optimalidad es la comparacion entre los dos teclados.\nComo mas pequeña sea el valor, más optimo es respecto al otro teclado.");
    }
}
