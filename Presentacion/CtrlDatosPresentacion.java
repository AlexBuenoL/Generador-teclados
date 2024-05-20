package edu.upc.prop.clusterxx.Presentacion;

import java.io.IOException;
import java.text.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;

import javax.swing.JOptionPane;

import edu.upc.prop.clusterxx.Dominio.Frecuencia.*;
import edu.upc.prop.clusterxx.Dominio.alfabeto.*;
import edu.upc.prop.clusterxx.Dominio.Texto.*;

/**
 * La clase `CtrlDatosPresentacion` es responsable de gestionar la interacción
 * entre las vistas de la interfaz gráfica y las operaciones relacionadas con
 * alfabetos, frecuencias y textos en el sistema.
 */

public class CtrlDatosPresentacion {
    private VistaAñadirAlfFre vistaAñadirAlfFre;
    private VistaAñadirTexto vistaAñadirTexto;
    private VistaAñadirDatos vistaAñadirDatos;
    private VistaMostrarDatos vistaMostrarDatos;
    private VistaModificarDatos vistaModificarDatos;
    private VistaModificarAlf vistaModificarAlf;
    private VistaModificarFre vistaModificarFre;
    private VistaModificarTexto vistaModificarTexto;
    private VistaGestionarDatos vistaGestionarDatos;
    private VistaBorrarDatos vistaBorrarDatos;
    private VistaBorrarDatos vistaBorrarAlfabeto;
    private VistaBorrarDatos vistaBorrarFrecuencia;
    private VistaBorrarDatos vistaBorrarTexto;
    private CtrlPresentacion ctrlPresentacion;
    private CtrlGestionAlfabetos ctrlGestorAlfabetos;
    private CtrlGestionFrecuencias ctrlGestorFrecuencias;
    private CtrlGestionTextos ctrlGestorTextos;

    /**
 * Convierte un String con pares clave-valor a un conjunto de SimpleEntry.
 *
 * @param input String con pares clave-valor separados por líneas.
 * @return Un conjunto de SimpleEntry con las claves y valores.
 * @throws NumberFormatException Si el formato de entrada no es válido.
 */
    private Set<SimpleEntry<String, Integer>> convertToSet(String input) throws NumberFormatException {
        Set<SimpleEntry<String, Integer>> resultSet = new HashSet<>();

        // Dividir el String en líneas
        String[] lines = input.split("\n");

        for (String line : lines) {
            // Dividir cada línea en palabras
            String[] parts = line.split(" ");

            if (parts.length == 2) {
                // Obtener la clave y el valor
                String key = parts[0];
                int value = Integer.parseInt(parts[1]);

                // Crear una SimpleEntry y agregarla al conjunto
                resultSet.add(new SimpleEntry<>(key, value));
            } else {
                throw new NumberFormatException("Listado de frecuencias incompleto");
            }
        }

        return resultSet;
    }

    /**
 * Convierte un conjunto de SimpleEntry a un String con pares clave-valor separados por líneas.
 *
 * @param set Conjunto de SimpleEntry.
 * @return String con pares clave-valor.
 */
    private String convertirSetAString(Set<SimpleEntry<String, Integer>> set) {
        StringBuilder resultado = new StringBuilder();

        for (SimpleEntry<String, Integer> entry : set) {
            resultado.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }

        return resultado.toString();
    }

    /**
 * Convierte un String a un conjunto de String, dividiendo la entrada por espacios o saltos de línea.
 *
 * @param entrada String de entrada.
 * @return Conjunto de String resultante.
 */
    private Set<String> convertirStringASetString(String entrada) {
        // Dividir la entrada por espacios o saltos de línea
        String[] elementosArray = entrada.split("[\\s\\n]+");
        
        // Crear un Set a partir del array
        Set<String> conjunto = new HashSet<>(Arrays.asList(elementosArray));

        return conjunto;
    }

    /**
 * Consulta el alfabeto a partir de una representación de cadena y lo muestra en la vista correspondiente.
 *
 * @param id Identificador del alfabeto.
 * @param alfabeto Representación de cadena del alfabeto.
 * @return Conjunto de caracteres que representa el alfabeto.
 */
    private Set<Character> consultarAlfabeto(String id, String alfabeto) {
        alfabeto = alfabeto.toLowerCase();
        Set<Character> alfabetoSet = new HashSet<>();

        if (alfabeto.startsWith("[")) {
            // El alfabeto ya está representado como un conjunto, imprímelo tal cual
            vistaMostrarDatos.setText("id: " + id + "\nAlfabeto: " + alfabeto);
        } 
        else {
            // El alfabeto está en formato original "abc", cámbialo a [a, b, c]
            for (char caracter : alfabeto.toCharArray()) {
                // Normaliza y quita diacríticas de la letra
                String letraNormalizada = Normalizer.normalize(String.valueOf(caracter), Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    
                // Agrega solo caracteres diferentes (sin distinción de mayúsculas o minúsculas)
                if (Character.isLetter(caracter) && !alfabetoSet.contains(letraNormalizada.charAt(0))) {
                    alfabetoSet.add(letraNormalizada.charAt(0));
                }
            }
            vistaMostrarDatos.setText("id: " + id + "\n Alfabeto: " + alfabetoSet);
        }
        return alfabetoSet;
    }

    /**
 * Lista los alfabetos contenidos en un HashMap y muestra la información en la vista correspondiente.
 *
 * @param alfabetos HashMap que contiene los alfabetos.
 * @param vista Tipo de vista a la que se debe enviar la información.
 * @return Cadena con la información de los alfabetos.
 */
    private String escribirAlfabetos(HashMap<String, Alfabeto> alfabetos, String vista) {
        StringBuilder resultado = new StringBuilder("Los alfabetos del sistema son los siguientes:\n");
    
        for (String i : alfabetos.keySet()) {
            String alfabetoString = alfabetos.get(i).toString();
            Set<Character> alfabetoSet = consultarAlfabeto(i, alfabetoString);
            resultado.append("id: ").append(i).append("\n").append(" Alfabeto: ").append(alfabetoSet).append("\n");
        }
        if(vista == "mostrar") vistaMostrarDatos.setText(resultado.toString());
        else if(vista == "modificar") vistaModificarAlf.setText(resultado.toString());
        else if(vista == "borrar")vistaBorrarAlfabeto.setTextAlf(resultado.toString());
        return resultado.toString();
    }


/**
 * Lista la frecuencia de un conjunto de caracteres y muestra la información en la vista correspondiente.
 *
 * @param frecuencia HashMap que contiene la frecuencia de los caracteres.
 * @return Cadena con la información de la frecuencia.
 */
    private String listarFrecuencia(HashMap<String,Integer> frecuencia) {
        StringBuilder resultado = new StringBuilder ("Frecuencia: \n");

        for(String i : frecuencia.keySet()) {
            resultado.append(i).append(" ").append(frecuencia.get(i)).append("\n");
        }
        String resultadoString = resultado.toString();
        vistaMostrarDatos.setText(resultadoString);
        return resultadoString;
    }

    /**
 * Lista todas las frecuencias contenidas en un HashMap y muestra la información en la vista correspondiente.
 *
 * @param frecuencias HashMap que contiene las frecuencias.
 * @param vista Tipo de vista a la que se debe enviar la información.
 * @return Cadena con la información de todas las frecuencias.
 */
    private String listarTodasFrecuencias(HashMap<String,Frecuencia> frecuencias, String vista) {
        StringBuilder resultado = new StringBuilder ("Listar frecuencias: \n");

        for (String id : frecuencias.keySet()) {
            resultado.append("Listado de frecuencias " + id + ": \n");
            HashMap<String,Integer> freq = frecuencias.get(id).getListadoFrecuencias();
            String listFreq = listarFrecuencia(freq);
            resultado.append(listFreq).append("\n");
        }
        if(vista == "mostrar") vistaMostrarDatos.setText(resultado.toString());
        else if(vista == "modificar")vistaModificarFre.setText(resultado.toString());
        else if(vista == "borrar") vistaBorrarFrecuencia.setTextFre(resultado.toString());
        return resultado.toString();
    }

    /**
 * Lista todos los textos contenidos en un HashMap y muestra la información en la vista correspondiente.
 *
 * @param textos HashMap que contiene los textos.
 * @param vista Tipo de vista a la que se debe enviar la información.
 */
    private void escribirTextos(HashMap<String,Texto> textos, String vista) {
         StringBuilder resultado = new StringBuilder();
        for(String i : textos.keySet()) {
            resultado.append("id: " + i + "\n");
            String textoString = textos.get(i).toString();
            resultado.append(textoString + "\n");
        }
        if(vista == "mostrar") vistaMostrarDatos.setText(resultado.toString());
        else if(vista == "modificar") vistaModificarTexto.setText(resultado.toString());
        else vistaBorrarTexto.setTextTexto(resultado.toString());  
    }

     /**
     * Constructor de la clase `CtrlDatosPresentacion`.
     *
     * @param ctrlPresentacion El controlador de presentación principal.
     */
    public CtrlDatosPresentacion(CtrlPresentacion ctrlPresentacion) {
        this.ctrlPresentacion = ctrlPresentacion;
        this.ctrlGestorAlfabetos = CtrlGestionAlfabetos.getInstance();
        this.ctrlGestorFrecuencias = CtrlGestionFrecuencias.getInstance();
        this.ctrlGestorTextos = CtrlGestionTextos.getInstance();
        this.vistaAñadirAlfFre = new VistaAñadirAlfFre(this);
        this.vistaAñadirTexto = new VistaAñadirTexto(this);
        this.vistaMostrarDatos = new VistaMostrarDatos(this);
        this.vistaAñadirDatos = new VistaAñadirDatos(this, "Añadir Datos");
        this.vistaModificarDatos = new VistaModificarDatos(this,"Modificar Datos");
        this.vistaModificarAlf = new VistaModificarAlf(this);
        this.vistaModificarFre = new VistaModificarFre(this);
        this.vistaModificarTexto = new VistaModificarTexto(this);
        this.vistaGestionarDatos = new VistaGestionarDatos(this);
        this.vistaBorrarDatos = new VistaBorrarDatos(this, "Borrar Datos");
        this.vistaBorrarAlfabeto = new VistaBorrarAlfabeto(this);
        this.vistaBorrarTexto = new VistaBorrarTexto(this);
        this.vistaBorrarFrecuencia = new VistaBorrarFrecuencia(this);
    }

    /**
     * Obtiene el controlador de presentación asociado.
     *
     * @return El controlador de presentación.
     */
    public CtrlPresentacion getCtrlPresentacion() {
        return ctrlPresentacion;
    }

    /**
     * Muestra la vista inicial de la aplicación.
     */
    public void mostrarVistaInicial() {
        ctrlPresentacion.mostrarVistaInicial();
        vistaAñadirAlfFre.ocultar();
        vistaMostrarDatos.ocultar();
    }

    /**
     * Muestra la vista de gestión de datos.
     */
    public void mostrarVistaGestionarDatos() {
        vistaGestionarDatos.mostrar();
        ctrlPresentacion.ocultarVistaInicial();
    }

    /**
     * Muestra la vista de añadir datos.
     */
    public void mostrarVistaAñadirDatos() {
        vistaAñadirAlfFre.limpiarCampos();
        vistaAñadirDatos.mostrar();
        vistaGestionarDatos.ocultar();
    }

    /**
     * Muestra la vista de modificar datos.
     */
    public void mostrarVistaModificarDatos() {
        vistaModificarDatos.mostrar();
        vistaGestionarDatos.ocultar();
    }

      /**
     * Muestra la vista de añadir alfabeto o frecuencia.
     */
    public void mostrarVistaAñadirAlfFre() {
        vistaAñadirAlfFre.limpiarCampos();
        vistaAñadirAlfFre.mostrar();
        ctrlPresentacion.ocultarVistaInicial();
    }

     /**
     * Muestra la vista de añadir texto.
     */
    public void mostrarVistaAñadirTexto() {
        vistaAñadirTexto.mostrar();
        ctrlPresentacion.ocultarVistaInicial();
    }

     /**
     * Muestra la vista de modificar alfabeto.
     */
    public void mostrarVistaModificarAlf() {
        vistaModificarDatos.limpiarCampos();
        vistaModificarAlf.mostrar();
        ctrlPresentacion.ocultarVistaInicial();
    }

     /**
     * Muestra la vista de modificar frecuencia.
     */
    public void mostrarVistaModificarFre() {
        vistaModificarDatos.limpiarCampos();
        vistaModificarFre.mostrar();
        ctrlPresentacion.ocultarVistaInicial();
    }   

    /**
     * Muestra la vista de modificar texto.
     */

    public void mostrarVistaModificarTexto() {
        vistaModificarDatos.limpiarCampos();
        vistaModificarTexto.mostrar();
        ctrlPresentacion.ocultarVistaInicial();
    }

    /**
     * Muestra la vista de mostrar datos.
     */
    public void mostrarVistaMostrarDatos() {
        vistaMostrarDatos.limpiarCampos();
        vistaMostrarDatos.mostrar();
        vistaGestionarDatos.ocultar();
    }

    /**
     * Muestra la vista de borrar datos.
     */
    public void mostrarVistaBorrarDatos() {
        vistaBorrarDatos.mostrar();
        vistaGestionarDatos.ocultar();
    }

    /**
     * Muestra la vista de borrar alfabeto.
     */
    public void mostrarVistaBorrarAlfabeto() {
        vistaBorrarAlfabeto.limpiarCampos();
        vistaBorrarAlfabeto.mostrar();
        vistaBorrarDatos.ocultar();
    }
    
    /**
     * Muestra la vista de borrar frecuencia.
     */
    public void mostrarVistaBorrarFrecuencia() {
        vistaBorrarFrecuencia.limpiarCampos();
        vistaBorrarFrecuencia.mostrar();
        vistaBorrarDatos.ocultar();
    }

    /**
     * Muestra la vista de borrar texto.
     */
    public void mostrarVistaBorrarTexto() {
        vistaBorrarTexto.limpiarCampos();
        vistaBorrarTexto.mostrar();
        vistaBorrarDatos.ocultar();
    }

     /**
     * Muestra la vista de generar teclado.
     */
    public void mostrarVistaGenerarTeclado() {
        ctrlPresentacion.mostrarVistaGenerarTeclado();
        vistaAñadirAlfFre.ocultar();
        vistaAñadirTexto.ocultar();
        vistaAñadirDatos.ocultar();
    }
    /**
     * Verifica si un alfabeto con el identificador dado existe en el sistema.
     *
     * @param id El identificador del alfabeto.
     * @return true si el alfabeto existe, false en caso contrario.
     */
    public boolean existeAlf(String id) {
        return ctrlGestorAlfabetos.existeAlfabeto(id);
    }

    /**
     * Verifica si una frecuencia con el identificador dado existe en el sistema.
     *
     * @param id El identificador de la frecuencia.
     * @return true si la frecuencia existe, false en caso contrario.
     */
    public boolean existeFre(String id) {
        return ctrlGestorFrecuencias.existeFrecuencia(id);
    }
    /**
     * Cierra la sesión actual en la aplicación.
     */
    public void cerrarSesion() {
        ctrlPresentacion.cerrarSesion();
    }
    /**
     * Agrega un nuevo alfabeto al sistema con el identificador y contenido especificados.
     *
     * @param id       El identificador del alfabeto.
     * @param alfabeto La representación en cadena del alfabeto.
     */
    public void agregarAlfabeto(String id, String alfabeto) {
        try {
            if(id.isEmpty()) throw new NullPointerException("Debes introducir un id del alfabeto a añadir");
            ctrlGestorAlfabetos.añadirAlfabeto(id, alfabeto);
            vistaAñadirAlfFre.mostrarMensajeInform("INFORMACIÓN: Si el alfabeto contiene numeros no se añaden.\nAlfabeto añadido con éxito!");
            vistaAñadirAlfFre.limpiarCampos();
        } catch (IllegalStateException e) {
            vistaAñadirAlfFre.mostrarMensajeError("Error: " + e.getMessage());
        } catch (NullPointerException e1) {
            vistaAñadirAlfFre.mostrarMensajeError("Error: " + e1.getMessage());
        }
    }

    /**
     * Agrega nuevas frecuencias al sistema con el identificador y contenido especificados.
     *
     * @param id   El identificador de las frecuencias.
     * @param frec La representación en cadena de las frecuencias.
     */
    public void agregarFrecuencias(String id, String frec) {   
        Set<SimpleEntry<String, Integer>> resultFrec = new HashSet<>();
        try {
            resultFrec = convertToSet(frec);
        } catch (NumberFormatException e) {
            vistaAñadirAlfFre.mostrarMensajeError("Error: Después de la palabra debería ir el número de apariciones de esta, que es un entero");
        }
        if (!resultFrec.isEmpty()) {
            try {
            if(id.isEmpty()) throw new NullPointerException("Debes introducir un id de frecuencia a añadir");
                ctrlGestorFrecuencias.añadirListadoFrecuencias(id, resultFrec);
                vistaAñadirAlfFre.mostrarMensajeInform("Frecuencias añadidas con éxito!");
                vistaAñadirAlfFre.limpiarCampos();
            } catch (IllegalStateException e) {
                vistaAñadirAlfFre.mostrarMensajeError("Error: " + e.getMessage());
            } catch (NullPointerException e1) {
                vistaAñadirAlfFre.mostrarMensajeError("Error: " + e1.getMessage());
            } catch (IllegalArgumentException e2) {
                vistaAñadirAlfFre.mostrarMensajeError("Error: " + e2.getMessage());
            }
        }
    }

    /**
     * Agrega un nuevo texto al sistema con los identificadores y contenido especificados.
     *
     * @param idTexto El identificador del texto.
     * @param texto   El contenido del texto.
     * @param idAlf   El identificador del alfabeto asociado al texto.
     * @param idFre   El identificador de la frecuencia asociada al texto.
     */
    public void agregarTexto(String idTexto, String texto, String idAlf, String idFre) {
        try {
            if(idTexto.isEmpty()) throw new NullPointerException("Debes introducir un id de texto a añadir");
            if(idAlf.isEmpty()) throw new NullPointerException("Debes introducir un id de alfabeto a añadir");
            if(idFre.isEmpty()) throw new NullPointerException("Debes introducir un id de frecuencia a añadir");
            ctrlGestorTextos.añadirTexto(idTexto, texto, idAlf, idFre);
            vistaAñadirTexto.mostrarMensajeInform("Texto añadido con éxito!\n Alfabeto con id " + idAlf + " y frecuencia con id " + idFre + " extraídas con éxito!");
            vistaAñadirTexto.limpiarCampos();
        } catch (IllegalAccessError e) {
            vistaAñadirTexto.mostrarMensajeError("Error: " + e.getMessage());
        } catch (IllegalStateException e1) {
           vistaAñadirTexto.mostrarMensajeError("Error: " + e1.getMessage());
        } catch (NullPointerException e2) {
            vistaAñadirTexto.mostrarMensajeError("Error: " + e2.getMessage());
        }  
    }

    /**
 * Muestra el alfabeto correspondiente a un identificador proporcionado en la interfaz gráfica.
    */
    public void mostrarAlfabeto() {
        try {
            String id = JOptionPane.showInputDialog("Escriba la ID de su alfabeto (Número entero):");
            if (id == null | id.trim().isEmpty()) {
                // Mensaje de error si la entrada es vacía
                JOptionPane.showMessageDialog(vistaMostrarDatos.frame, "Error: Debes introducir un alfabeto", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                String alfabeto = ctrlGestorAlfabetos.consultarAlfabeto(id);
                consultarAlfabeto(id, alfabeto);
            }
        } catch (NoSuchElementException e2) {
            vistaMostrarDatos.mostrarMensajeError("Error: " + e2.getMessage());
        } catch (NullPointerException e3) {
            vistaMostrarDatos.mostrarMensajeError("Error: " + e3.getMessage());
        } 
    }

    /**
 * Muestra las frecuencias correspondientes a un identificador proporcionado en la interfaz gráfica.
 */
    public void mostrarFrecuencias() {
        try {
            String id = JOptionPane.showInputDialog("Escriba la ID de su frecuencia (Número entero):");
            if (id == null | id.trim().isEmpty()) {
                // Mensaje de error si la entrada es vacía
                JOptionPane.showMessageDialog(vistaMostrarDatos.frame, "Error: Debes introducir una frecuencia", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                HashMap<String,Integer> freq = ctrlGestorFrecuencias.getListadoFrecuencias(id);
                listarFrecuencia(freq);
                // String freqString = convertirHashMapAString(freq);
                // vistaMostrarDatos.setText(freqString);
            }
        } catch (NoSuchElementException e2) {
            vistaMostrarDatos.mostrarMensajeError("Error: " + e2.getMessage());
        } catch (NullPointerException e3) {
            vistaMostrarDatos.mostrarMensajeError("Error: " + e3.getMessage());
        } 
    }

    /**
 * Muestra el texto correspondiente a un identificador proporcionado en la interfaz gráfica.
 */
    public void mostrarTexto() {
        try {
            String id = JOptionPane.showInputDialog("Escriba la ID de su texto (Número entero):");
            if (id == null | id.trim().isEmpty()) {
                // Mensaje de error si la entrada es vacía
                JOptionPane.showMessageDialog(vistaMostrarDatos.frame, "Error: Debes introducir un texto", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                String texto = ctrlGestorTextos.consultarTexto(id);
                vistaMostrarDatos.setText(texto);
            }
        } catch (NoSuchElementException e2) {
            vistaMostrarDatos.mostrarMensajeError("Error: " + e2.getMessage());
        } catch (NullPointerException e3) {
            vistaMostrarDatos.mostrarMensajeError("Error: " + e3.getMessage());
        } 
    }

    /**
 * Muestra todos los alfabetos en el sistema en la interfaz gráfica.
 */
    public void mostrarTodosAlfabetos() {
        try {
            HashMap<String,Alfabeto> alfabetos = ctrlGestorAlfabetos.getListadoAlfabetos();
            escribirAlfabetos(alfabetos,"mostrar");
        } catch (NoSuchElementException e) {
            vistaMostrarDatos.mostrarMensajeError("Error: " + e.getMessage());
        }   
    }

    /**
 * Muestra todos los alfabetos en el sistema para modificar en la interfaz gráfica.
 */
    public void mostrarTodosAlfabetosModificar() {
        try {
            HashMap<String,Alfabeto> alfabetos = ctrlGestorAlfabetos.getListadoAlfabetos();
            escribirAlfabetos(alfabetos, "modificar");
        } catch (NoSuchElementException e) {
            vistaModificarAlf.setText("No hay alfabetos en el sistema");
        }   
    }

    /**
 * Muestra todos los alfabetos en el sistema para borrar en la interfaz gráfica.
 */
    public void mostrarTodosAlfabetosBorrar() {
        try {
            HashMap<String,Alfabeto> alfabetos = ctrlGestorAlfabetos.getListadoAlfabetos();
            escribirAlfabetos(alfabetos, "borrar");
            //String alfString = convertirHashMapAString(alfabetos);
        } catch (NoSuchElementException e) {
            vistaBorrarAlfabeto.setTextAlf("No hay alfabetos en el sistema");
        }   
    }
    
    /**
     * Muestra todos los alfabetos en el sistema para generar en la interfaz gráfica.
     *
     * @return Una representación en cadena de todos los alfabetos en el sistema.
     * @throws NoSuchElementException Si no hay alfabetos en el sistema.
     */
    public String mostrarTodosAlfabetosGenerar() {
        HashMap<String,Alfabeto> alfabetos = ctrlGestorAlfabetos.getListadoAlfabetos();
        if(!alfabetos.isEmpty()) return escribirAlfabetos(alfabetos, "generar");
        else throw new NoSuchElementException();
    }

    /**
 * Muestra todas las frecuencias en el sistema en la interfaz gráfica.
 */
    public void mostrarTodasFrecuencias() {
        try {
            HashMap<String,Frecuencia> freqs = ctrlGestorFrecuencias.getTodosListadosFrecuencias();
            listarTodasFrecuencias(freqs, "mostrar");
        } catch (NoSuchElementException e) {
            vistaMostrarDatos.mostrarMensajeError("Error: " + e.getMessage());
        }
    }

    /**
 * Muestra todas las frecuencias en el sistema para modificar en la interfaz gráfica.
 */
    public void mostrarTodasFrecuenciasModificar() {
        try {
            HashMap<String,Frecuencia> freqs = ctrlGestorFrecuencias.getTodosListadosFrecuencias();
            listarTodasFrecuencias(freqs, "modificar");
        } catch (NoSuchElementException e) {
            vistaModificarFre.setText("No hay frecuencias en el sistema");
        }
    }

    /**
 * Muestra todas las frecuencias en el sistema para borrar en la interfaz gráfica.
 */
    public void mostrarTodasFrecuenciasBorrar() {
        try {
            HashMap<String,Frecuencia> freqs = ctrlGestorFrecuencias.getTodosListadosFrecuencias();
            listarTodasFrecuencias(freqs, "borrar");
        } catch (NoSuchElementException e) {
            vistaBorrarFrecuencia.setTextFre("No hay frecuencias en el sistema");
        }
    }

    /**
 * Muestra todas las frecuencias en el sistema para generar en la interfaz gráfica.
 *
 * @return Una representación en cadena de todas las frecuencias en el sistema.
 * @throws NoSuchElementException Si no hay frecuencias en el sistema.
 */
    public String mostrarTodasFrecuenciasGenerar() {
        HashMap<String,Frecuencia> freqs = ctrlGestorFrecuencias.getTodosListadosFrecuencias();
        if(!freqs.isEmpty()) return listarTodasFrecuencias(freqs, "generar");
        else throw new NoSuchElementException();    
    }

    /**
 * Muestra todos los textos en el sistema en la interfaz gráfica.
 */
    public void mostrarTodosTextos() {
        try {
            HashMap<String,Texto> textos = ctrlGestorTextos.getlistadoTextos();
            escribirTextos(textos, "mostrar");
        } catch (NoSuchElementException e) {
            vistaMostrarDatos.mostrarMensajeError("Error: " + e.getMessage());
        }  
    }

    /**
 * Muestra todos los textos en el sistema para modificar en la interfaz gráfica.
 */
    public void mostrarTodosTextosModificar() {
        try {
            HashMap<String,Texto> textos = ctrlGestorTextos.getlistadoTextos();
            escribirTextos(textos, "modificar");
        } catch (NoSuchElementException e) {
            vistaModificarTexto.setText("No hay textos en el sistema");
        }  
    }

    /**
 * Muestra todos los textos en el sistema para borrar en la interfaz gráfica.
 */
    public void mostrarTodosTextosBorrar() {
        try {
            HashMap<String,Texto> textos = ctrlGestorTextos.getlistadoTextos();
            escribirTextos(textos, "borrar");
        } catch (NoSuchElementException e) {
            vistaBorrarTexto.setTextTexto("No hay textos en el sistema");
        }  
    }

    /**
     * Borra un alfabeto del sistema con el ID especificado. Si el alfabeto está asociado a algún texto, no se borra.
     *
     * @param id El ID del alfabeto a borrar.
     * @throws InputMismatchException Si el alfabeto está asociado a algún texto en el sistema.
     * @throws NoSuchElementException Si no se encuentra el alfabeto con el ID especificado.
     * @throws NullPointerException Si el ID del alfabeto es nulo o vacío.
     */
    public void borrarAlfabeto(String id) {
        try {
            if(id.isEmpty()) throw new NullPointerException("Debes introducir un id del alfabeto a borrar");
            if(ctrlGestorTextos.existe_AlfabetoTexto(id)) throw new InputMismatchException("Alfabeto asociado a texto " + ctrlGestorTextos.getIdTextoAlfabeto(id) + "\nPara borrarlo, has de borrar el texto.");
            ctrlGestorAlfabetos.borrarAlfabeto(id);
            vistaBorrarAlfabeto.mostrarMensajeInform("Alfabeto con id " + id + " borrado con éxito!");
        } catch (InputMismatchException e) {
            vistaBorrarAlfabeto.mostrarMensajeError("Error: " + e.getMessage());
        } catch (NoSuchElementException e1) {
            vistaBorrarAlfabeto.mostrarMensajeError("Error: " + e1.getMessage());
        } catch (NullPointerException e2) {
            vistaBorrarAlfabeto.mostrarMensajeError("Error: " + e2.getMessage());
        }
    }

    /**
 * Borra una frecuencia del sistema con el ID especificado. Si la frecuencia está asociada a algún texto, no se borra.
 *
 * @param id El ID de la frecuencia a borrar.
 * @throws InputMismatchException Si la frecuencia está asociada a algún texto en el sistema.
 * @throws NoSuchElementException Si no se encuentra la frecuencia con el ID especificado.
 * @throws NullPointerException Si el ID de la frecuencia es nulo o vacío.
 */
    public void borrarFrecuencia(String id) {
        try {
            if(id.isEmpty()) throw new NullPointerException("Debes introducir un id del frecuencia a borrar");
            if(ctrlGestorTextos.existeFrecuenciaTexto(id)) throw new InputMismatchException("Frecuencia asociada a texto " + ctrlGestorTextos.getIdTextoFrec(id) + "\nPara borrarlo, has de borrar el texto.");
            ctrlGestorFrecuencias.borrarListadoFrecuencias(id);
            vistaBorrarFrecuencia.mostrarMensajeInform("Frecuencia con id " + id + " borrado con éxito!");
        } catch (InputMismatchException e) {
            vistaBorrarFrecuencia.mostrarMensajeError("Error: " + e.getMessage());
        } catch (NoSuchElementException e1) {
            vistaBorrarFrecuencia.mostrarMensajeError("Error: " + e1.getMessage());
        } catch (NullPointerException e2) {
            vistaBorrarFrecuencia.mostrarMensajeError("Error: " + e2.getMessage());
        }
    }

    /**
 * Borra un texto del sistema con el ID especificado. También borra el alfabeto y la frecuencia asociados al texto.
 *
 * @param id El ID del texto a borrar.
 * @throws NoSuchElementException Si no se encuentra el texto con el ID especificado.
 * @throws NullPointerException Si el ID del texto es nulo o vacío.
 */
    public void borrarTexto(String id) {
        try {
            if(id.isEmpty()) throw new NullPointerException("Debes introducir un id del alfabeto a modificar");
            ctrlGestorTextos.borrarTexto(id);
            vistaBorrarTexto.mostrarMensajeInform("Texto con id " + id + " borrado con éxito!\nEl Alfabeto asociado y la frecuencia asociada también se han borrado.");
        } catch (NoSuchElementException e1) {
            vistaBorrarTexto.mostrarMensajeError("Error: " + e1.getMessage());
        } catch (NullPointerException e2) {
            vistaBorrarTexto.mostrarMensajeError("Error: " + e2.getMessage());
        }
    }

    /**
 * Importa un alfabeto desde un archivo en la ruta especificada y actualiza la interfaz gráfica para añadir alfabeto/frecuencia.
 *
 * @param path La ruta del archivo desde el cual importar el alfabeto.
 * @throws IOException Si hay un error al leer desde el archivo.
 * @throws IllegalStateException Si hay un error al procesar los datos del archivo.
 * @throws NoSuchElementException Si no se encuentran datos en el archivo.
 */
    public void importarAlfabeto(String path) {
        try {
            Set<SimpleEntry<String, String>> datos = ctrlGestorAlfabetos.leerDesdeArchivo(path);    
            String id = datos.iterator().next().getKey();
            String alfabeto = datos.iterator().next().getValue();         
            String idString = String.valueOf(id);

            vistaAñadirAlfFre.setAlf(alfabeto);
            vistaAñadirAlfFre.setIdAlf(idString);

        } catch (IOException | IllegalStateException ex) {
            vistaAñadirAlfFre.mostrarMensajeError("Error al leer de archivo.");
        } catch (NoSuchElementException e1) {
            vistaAñadirAlfFre.mostrarMensajeError("Error");
        }
    }

    /**
 * Importa una frecuencia desde un archivo en la ruta especificada y actualiza la interfaz gráfica para añadir alfabeto/frecuencia.
 *
 * @param path La ruta del archivo desde el cual importar la frecuencia.
 * @throws IOException Si hay un error al leer desde el archivo.
 * @throws IllegalStateException Si hay un error al procesar los datos del archivo.
 * @throws NoSuchElementException Si no se encuentran datos en el archivo.
 */
    public void importarFrecuencia(String path) {
        try {
            Set<SimpleEntry<String, Set<SimpleEntry<String, Integer>>>> datos = ctrlGestorFrecuencias.leerDesdeArchivo(path);
            String id = datos.iterator().next().getKey();
            Set<SimpleEntry<String, Integer>> frec = datos.iterator().next().getValue(); 
            String idString = String.valueOf(id);
            String frecString = convertirSetAString(frec);

            vistaAñadirAlfFre.setIdFre(idString);
            vistaAñadirAlfFre.setFre(frecString);

        } catch (IOException | IllegalStateException ex) {
            vistaAñadirAlfFre.mostrarMensajeError("Error al leer de archivo.");
        } catch (NoSuchElementException e1) {
            vistaAñadirAlfFre.mostrarMensajeError("Error");
        }
    }

    /**
 * Importa un texto desde un archivo en la ruta especificada y actualiza la interfaz gráfica para añadir texto.
 *
 * @param path La ruta del archivo desde el cual importar el texto.
 * @throws IOException Si hay un error al leer desde el archivo.
 * @throws IllegalStateException Si hay un error al procesar los datos del archivo.
 * @throws NoSuchElementException Si no se encuentran datos en el archivo.
 */
    public void importarTexto(String path) {
        try {
            Set<SimpleEntry<String, String>> datos = ctrlGestorTextos.leerDesdeArchivo(path);
            String id = datos.iterator().next().getKey();
            String texto = datos.iterator().next().getValue();         
            String idString = String.valueOf(id);

            vistaAñadirTexto.setIdText(idString);
            vistaAñadirTexto.setText(texto);
        } catch (IOException | IllegalStateException ex) {
            vistaAñadirAlfFre.mostrarMensajeError("Error al leer de archivo.");
        } catch (NoSuchElementException e1) {
            vistaAñadirAlfFre.mostrarMensajeError("Error");
        } 
    }

    /**
 * Modifica un alfabeto en el sistema con el ID, nuevo alfabeto y opción de modificación especificados.
 *
 * @param id El ID del alfabeto a modificar.
 * @param newAlf El nuevo alfabeto.
 * @param opc La opción de modificación: 0 para añadir, 1 para eliminar.
 * @throws InputMismatchException Si el alfabeto está asociado a algún texto en el sistema.
 * @throws NoSuchElementException Si no se encuentra el alfabeto con el ID especificado.
 * @throws NullPointerException Si el ID del alfabeto es nulo o vacío.
 * @throws IllegalArgumentException Si la opción de modificación no es válida.
 */
    public void modificarAlfabeto(String id, String newAlf, int opc) {
        try {
            if(id.isEmpty()) throw new NullPointerException("Debes introducir un id del alfabeto a modificar");
            if(ctrlGestorTextos.existe_AlfabetoTexto(id)) throw new InputMismatchException("Alfabeto asociado a texto " + ctrlGestorTextos.getIdTextoAlfabeto(id) + "\nPara modificarlo, has de modificar el texto.");
            int vacio = ctrlGestorAlfabetos.modificarAlfabeto(id, newAlf, opc);
            if(vacio == -1) { //informacion, no logica
                vistaModificarAlf.mostrarMensajeInform("Alfabeto con id " + id + " borrado porque has eliminado todas las letras");
            }
            else {
                vistaModificarAlf.mostrarMensajeInform("INFORMACIÓN: Si el alfabeto contiene numeros no se añaden.\nAlfabeto con id" + id + " modificado con éxito!");
            }
            vistaModificarDatos.limpiarCampos();
        } catch (InputMismatchException e1) {
            vistaModificarAlf.mostrarMensajeError("Error: " + e1.getMessage());
        } catch (NoSuchElementException e1) {
            vistaModificarAlf.mostrarMensajeError("Error: " + e1.getMessage());
        } catch (NullPointerException e2) {
            vistaModificarAlf.mostrarMensajeError("Error: " + e2.getMessage());
        } catch (IllegalArgumentException e3) {
            vistaModificarAlf.mostrarMensajeError("Error: " + e3.getMessage());
        }
    }

    /**
 * Modifica un texto en el sistema con el ID especificado y el nuevo texto.
 *
 * @param id El ID del texto a modificar.
 * @param newText El nuevo texto.
 * @throws InputMismatchException Si el texto está asociado a algún alfabeto o frecuencia en el sistema.
 * @throws NoSuchElementException Si no se encuentra el texto con el ID especificado.
 * @throws NullPointerException Si el ID del texto es nulo o vacío.
 * @throws IllegalArgumentException Si hay un problema con la entrada o el estado del sistema.
 * @throws IllegalStateException Si hay un problema con el estado del sistema durante la modificación.
 */
    public void modificarTexto(String id, String newText) {
        try {
            if(id.isEmpty()) throw new NullPointerException("Debes introducir un id del texto a modificar");
            int modificado = ctrlGestorTextos.modificarTexto(id, newText);
            if(modificado == 1) { //informacion
                vistaModificarTexto.mostrarMensajeInform("Texto con id " + id + " modificado con éxito!\nSe ha actualizado el alfabeto asociado al texto.\nSe ha actualizado el listado de frecuencias asociado al texto.");
            }
            else  {
                vistaModificarTexto.mostrarMensajeInform("Texto con id " + id + " borrado porque ha eliminado todas las palabras.\nAlfabeto y frecuencia no borrados.");
            }   
            vistaModificarDatos.limpiarCampos();    
        } catch (InputMismatchException e) {
            vistaModificarTexto.mostrarMensajeError("Error: " + e.getMessage());
        } catch (NoSuchElementException e1) {
            vistaModificarTexto.mostrarMensajeError("Error: " + e1.getMessage());
        } catch (NullPointerException e2) {
            vistaModificarTexto.mostrarMensajeError("Error: " + e2.getMessage());
        } catch (IllegalArgumentException e3) {
            vistaModificarTexto.mostrarMensajeError("Error: " + e3.getMessage());
        } catch (IllegalStateException e4) {
            vistaModificarTexto.mostrarMensajeError("Error: " + e4.getMessage());
        } 
    }

    /**
 * Modifica un listado de frecuencias en el sistema con el ID, nuevas frecuencias y opción de modificación especificados.
 *
 * @param id El ID del listado de frecuencias a modificar.
 * @param newFrec Las nuevas frecuencias.
 * @param opc La opción de modificación: 1 para añadir, 2 para eliminar, 3 para ninguna.
 * @throws InputMismatchException Si el listado de frecuencias está asociado a algún texto en el sistema.
 * @throws NoSuchElementException Si no se encuentra el listado de frecuencias con el ID especificado.
 * @throws IllegalArgumentException Si hay un problema con la entrada o el estado del sistema.
 * @throws NullPointerException Si el ID del listado de frecuencias es nulo o vacío.
 */
    public void modificarFrecuencia(String id, String newFrec, int opc) {
        try {
            if (opc == 3) throw new InputMismatchException("No has seleccionado ninguna opcion");
            if (id.isEmpty()) throw new NullPointerException("Debes introducir el id de tu listado de frecuencias a modificar");
            
            if(ctrlGestorTextos.existeFrecuenciaTexto(id)) throw new InputMismatchException("Listado de frecuencias asociado a texto " + ctrlGestorTextos.getIdTextoFrec(id) + "\nPara modificarlo, has de modificar el texto.");
            
            boolean entradaCorrecta = true;
            if (opc == 1) {
                Set<SimpleEntry<String, Integer>> setOfPairs = new HashSet<>();
                try {
                    setOfPairs = convertToSet(newFrec);
                } catch (NumberFormatException e) {
                    entradaCorrecta = false;
                    vistaAñadirAlfFre.mostrarMensajeError("Error: Después de la palabra debería ir el número de apariciones de esta, que es un entero");
                }
                if (!setOfPairs.isEmpty()) {
                    ctrlGestorFrecuencias.añadirFrecuenciasListado(id, setOfPairs);
                }
            }
            else if (opc == 2) {
                Set<String> palabras = convertirStringASetString(newFrec);
                ctrlGestorFrecuencias.eliminarFrecuenciasListado(id, palabras);
            }

            if (!ctrlGestorFrecuencias.existeFrecuencia(id)) vistaModificarAlf.mostrarMensajeInform("INFORMACIÓN: Si las palabras contienen números o signos de puntuación, estos no se tienen en cuenta.\n" + //
                    "Listado de frecuencias con id " + id + " borrado porque has eliminado todas las palabras");
            else if (entradaCorrecta) vistaModificarAlf.mostrarMensajeInform("INFORMACIÓN: Si las palabras contienen números o signos de puntuación, estos no se tienen en cuenta.\n" + //
                    "Listado de frecuencias con id " + id + " modificado con éxito!");

        } catch (InputMismatchException e) {
            vistaModificarFre.mostrarMensajeError("Error: " + e.getMessage());
        } catch (NoSuchElementException e1) {
            vistaModificarFre.mostrarMensajeError("Error: " + e1.getMessage());
        } catch (IllegalArgumentException e2) {
            vistaModificarFre.mostrarMensajeError("Error: " + e2.getMessage());
        } catch (NullPointerException e3) {
            vistaModificarFre.mostrarMensajeError("Error: " + e3.getMessage());
        }
    }

    /**
 * Comprueba si todas las palabras de un listado de frecuencias están presentes en un alfabeto.
 *
 * @param idAlf El ID del alfabeto.
 * @param idFre El ID del listado de frecuencias.
 * @return true si todas las palabras están en el alfabeto, false en caso contrario.
 */
    public boolean comprobarFrecuenciasEnAlfabeto(String idAlf, String idFre) {
        HashMap<String, Integer> frecuencias = ctrlGestorFrecuencias.getListadoFrecuencias(idFre);
        String alfabeto = ctrlGestorAlfabetos.consultarAlfabeto(idAlf);
        for (String palabra : frecuencias.keySet()) {
            // Iterar sobre las letras de la palabra
            for (int i = 0; i < palabra.length(); i++) {
                char letra = palabra.charAt(i);
                // Comprobar si la letra no está presente en el alfabeto
                if (alfabeto.indexOf(letra) == -1) {
                    return false; // Si al menos una letra no está en el alfabeto, retornar false
                }
            }
        }
        return true; // Todas las letras están en el alfabeto
    }
    
    /**
 * Carga todos los alfabetos desde el almacenamiento.
 */
    public void cargarAlfabetos() {
        ctrlGestorAlfabetos.cargarAlfabetos();
    }

    /**
 * Carga todas las frecuencias desde el almacenamiento.
 */
    public void cargarFrecuencias() {
        ctrlGestorFrecuencias.cargarFrecuencias();
    }

    /**
 * Carga todos los textos desde el almacenamiento.
 */
    public void cargarTextos() {
        ctrlGestorTextos.cargarTextos();
    }

    /**
 * Muestra información relevante sobre la gestión de datos en la interfaz gráfica.
 */
    public void mostrarInfoGestionar() {
        vistaGestionarDatos.mostrarMensajeInform("Para generar un teclado es necesario un alfabeto y una frecuencia.\nSi se añade un texto, se extrae automaticamente el alfabeto y la frecuencia asociadas.");
    }

    /**
 * Muestra información relevante sobre la adición de datos en la interfaz gráfica.
 */
    public void mostrarInfoAñadir() {
        vistaGestionarDatos.mostrarMensajeInform("Los alfabetos se pueden escribir en el formato que se desee.\nLas frecuencias se han de escribir cada par de letra frecuencia en una linea diferente.");
    }
}
