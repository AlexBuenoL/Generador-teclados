package edu.upc.prop.clusterxx.Dominio.Texto;
/**
 * La clase Texto representa un contenido textual y proporciona métodos para manipular y consultar dicho texto.
 */

public class Texto {
    //atributos privados
    private String texto;

    /**
     * Devuelve una representación en cadena del objeto Texto.
     * @return La representación en cadena del contenido del objeto Texto.
     */
    @Override //para que muestre el contenido y no la @memoria
    public String toString() {
        return texto;
    }

    //constructoras
    /**
     * Crea una instancia de Texto sin contenido inicial.
     */
    public Texto() {

    }

     /**
     * Crea una instancia de Texto con un contenido inicial.
     * @param text El contenido inicial del texto.
     */
    public Texto (String text) {
        texto = text;
    }

    //consultoras
    /**
     * Obtiene el contenido textual del objeto Texto.
     * @return Una cadena que representa el contenido del objeto Texto.
     */
    public String getTexto() {
        return texto;
    }

    //modificadoras
    /**
     * Elimina el contenido del objeto Texto, estableciéndolo como nulo.
     */
    public void clear() {
        texto = null;
    }
     /**
     * Agrega nuevo texto al objeto Texto, si no está contenido previamente.
     * @param nuevoTexto El texto a agregar.
     * @return 1 si el texto fue agregado correctamente, -1 si ya estaba contenido.
     */
    public int agregarTexto(String nuevoTexto) {
        if(!texto.contains(nuevoTexto)) {
            texto += " " + nuevoTexto;
            return 1;
        }
        else return -1;
    }

    /**
     * Quita un fragmento de texto del objeto Texto, si está contenido.
     * @param textoAQuitar El texto a quitar.
     * @return 1 si el texto fue quitado correctamente, -1 si no estaba contenido.
     */
    public int quitarTexto(String textoAQuitar) {
        if(texto.contains(textoAQuitar)) {
            texto = texto.replace(textoAQuitar, "");
            texto = texto.trim();
            return 1;
        }
        else return -1;
    }
    
    /**
     * Establece un nuevo contenido para el objeto Texto.
     * @param nuevoTexto El nuevo contenido del texto.
     */
    public void setTexto(String nuevoTexto) {
        this.texto = nuevoTexto;
    }
}
