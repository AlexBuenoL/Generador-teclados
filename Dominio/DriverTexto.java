// package edu.upc.prop.clusterxx.Dominio;

// import java.io.IOException;
// import java.text.Normalizer;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.InputMismatchException;
// import java.util.NoSuchElementException;
// import java.util.Scanner;
// import java.util.Set;

// import edu.upc.prop.clusterxx.Dominio.Texto.CtrlGestionTextos;
// import edu.upc.prop.clusterxx.Dominio.alfabeto.CtrlGestionAlfabetos;
// import edu.upc.prop.clusterxx.Dominio.Frecuencia.CtrlGestionFrecuencias;
// import edu.upc.prop.clusterxx.Dominio.Texto.Texto;

// public class DriverTexto {
//     public static int leer_idAlfabeto(Scanner scanner, String detalle) {
//         System.out.print("Escriba la id del alfabeto " + detalle + ": ");
//         int id = scanner.nextInt();  // Read user input
//         scanner.nextLine(); // Consumir el salto de línea
//         return id;
//     }
//     public static int leer_idFrecuencia(Scanner scanner) {
//         System.out.print("Escriba la id de su listado de frecuencias: ");
//         int id = scanner.nextInt();  // Read user input
//         scanner.nextLine(); // Consumir el salto de línea
//         return id;
//     }
//     public static int leer_idTexto(Scanner scanner, String detalle) {
//         System.out.print("Escriba la id del texto " + detalle + ": ");
//         int texto = scanner.nextInt();
//         scanner.nextLine(); // Consumir el salto de línea
//         return texto;
//     }
//     public static String leer_Texto(Scanner scanner, String detalle) { //eliminar cuando se solucione frecuencia
//         System.out.print("Escriba el texto " + detalle + ": ");
//         String texto = scanner.nextLine();
//         return texto;
//     }
//     public static int leer_opcion(Scanner scanner, String detalle) {
//         System.out.print("Escriba 1 si quiere añadir " + detalle + " o 2 si quiere quitar " + detalle +": ");
//         int opcion = scanner.nextInt();
//         if(opcion != 1 && opcion != 2) return -1;
//         else return opcion;
//     }
//     public static void escribirTextos(HashMap<Integer,Texto> textos) {
//         for(int i : textos.keySet()) {
//             System.out.println("id: " + i);
//             String textoString = textos.get(i).toString();
//             System.out.println("texto: " + textoString);
//         }
//     }

//     public static Set<Character> consultarAlfabeto(String alfabeto) {
//         alfabeto = alfabeto.toLowerCase();
//         // El alfabeto está en formato original "abc", cámbialo a [a, b, c]
//         Set<Character> alfabetoSet = new HashSet<>();
//         for (char caracter : alfabeto.toCharArray()) {
//             // Normaliza y quita diacríticas de la letra
//             String letraNormalizada = Normalizer.normalize(String.valueOf(caracter), Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

//             // Agrega solo caracteres diferentes (sin distinción de mayúsculas o minúsculas)
//             if (Character.isLetter(caracter) && !alfabetoSet.contains(letraNormalizada.charAt(0))) {
//                 alfabetoSet.add(letraNormalizada.charAt(0));
//             }
//         }
//         return alfabetoSet;
//     }

//     public static void menuComandos() {
//         System.out.println("**************************************************");
//         System.out.println("****          Comandos Disponibles           *****");
//         System.out.println("**************************************************");
//         System.out.println("**  0 - Salir del programa                      **");
//         System.out.println("**  1 - Añadir Texto                            **");
//         System.out.println("**  2 - Modificar Texto                         **");
//         System.out.println("**  3 - Borrar Texto                            **");
//         System.out.println("**  4 - Escribir Textos                         **");
//         System.out.println("**  5 - Leer desde fichero                      **");
//         System.out.println("**  6 - Help                                    **");
//         System.out.println("**************************************************");
//         System.out.println("Driver para comprobar la funcionalidad de Texto: ");
//     }

//     public static void main(String[] args) {
//         CtrlGestionTextos ctrlGestorTextos = CtrlGestionTextos.getInstance();
//         CtrlGestionAlfabetos ctrlGestorAlfabetos = CtrlGestionAlfabetos.getInstance();
//         CtrlGestionFrecuencias ctrlGestorFrecuencias = CtrlGestionFrecuencias.getInstance();

//         menuComandos();

//         Scanner lectura = new Scanner(System.in);  // lectura comando   
//         int comandoNum;

//         System.out.println();
//         System.out.print("Introduza un comando (6 para solicitar ayuda): ");

//         while(lectura.hasNextLine()) {
//             //System.out.print("Introduce un numero de comanda: ");
//             try {
//                 comandoNum = lectura.nextInt();
//                 if(comandoNum < 0 || comandoNum > 6) System.err.println("Error: Se ha de introducir un numero del 0 al 4.");
//                 else if (comandoNum == 0) break;
//                 else {
//                     if(comandoNum == 1) { //añadir texto
//                         try {
//                             int idTexto = leer_idTexto(lectura, "a añadir");
//                             String texto = leer_Texto(lectura, "a añadir");
//                             System.out.println();
//                             System.out.println("Ahora se procederá a convertir el texto...");
                            
//                             int idAlf = leer_idAlfabeto(lectura, "a convertir");
//                             //convertir a alfabeto
//                             //String nuevoAlfabeto = ctrlGestorTextos.convertirTextoAAlfabeto(ctrlGestorAlfabetos, idAlf, texto);

//                             int idFre = leer_idFrecuencia(lectura);
//                             //convertir a frecuencia
//                             //ctrlGestorTextos.convertirTextoAFrecuencia(ctrlGestorFrecuencias, idFre, texto);
                            
//                             //confirmamos la alta
//                             ctrlGestorTextos.añadirTexto(idTexto, texto, idAlf, idFre);
//                             System.out.println("Texto con id " + idTexto + " añadido con éxito!");

//                             //mostramos el alfabeto extraido
//                             System.out.println("Alfabeto extraído con éxito!");
//                             //confirmamos las frecuencias
//                             System.out.println("Frecuencia extraida con éxito!");
//                         } catch (IllegalAccessError e) {
//                             System.err.println("Error: " + e.getMessage());
//                         } catch (IllegalStateException e) {
//                             System.err.println("Error: " + e.getMessage());
//                         }                      
//                     }
//                     else if(comandoNum == 2) { //modificar texto
//                         try {
//                             //muestro todos los textos
//                             HashMap<Integer,Texto> textos = ctrlGestorTextos.getlistadoTextos();
//                             escribirTextos(textos);
//                             //leo del usuario
//                             int id = leer_idTexto(lectura, "a modificar");
//                             String texto = leer_Texto(lectura, "a modificar");
//                             int opcion = leer_opcion(lectura, "palabras");
//                             if(opcion == -1) System.out.print("Error: Tienes que escribir la opcion 1 o 2.");
//                             else {
//                                 int modificado = ctrlGestorTextos.modificarTexto(id, texto, opcion);
//                                 if(modificado == 1) {
//                                     System.out.println("Texto con id " + id + " modificado con éxito!");
//                                     System.out.println("Se ha actualizado el alfabeto asociado al texto.");
//                                 }
//                                 else  {
//                                     System.out.println("Texto con id " + id + " borrado porque ha eliminado todas las palabras.");
//                                     System.out.println("Alfabeto asociado borrado.");
//                                 }
//                             }
//                         } catch (NoSuchElementException e1) {
//                             System.err.println("Error: " + e1.getMessage());
//                         } catch (NullPointerException e2) {
//                             System.err.println("Error: " + e2.getMessage());
//                         } catch (IllegalArgumentException e3) {
//                             System.err.println("Error: " + e3.getMessage());
//                         } catch (IllegalStateException e4) {
//                             System.err.println("Error: " + e4.getMessage());
//                         } 
//                     }
//                     else if(comandoNum == 3) { //borrar texto     
//                         try {
//                             //muestro todos los textos
//                             HashMap<Integer,Texto> textos = ctrlGestorTextos.getlistadoTextos();
//                             escribirTextos(textos);
//                             //leo del usuario
//                             int id = leer_idTexto(lectura, "a borrar");
//                             ctrlGestorTextos.borrarTexto(id); 
//                             System.out.println("Texto con id " + id + " borrado con éxito!");
//                         } catch (NoSuchElementException e1) {
//                             System.err.println("Error: " + e1.getMessage());
//                         } catch (NullPointerException e2) {
//                             System.err.println("Error: " + e2.getMessage());
//                         }
//                     }
//                     else if(comandoNum == 4) { //escribir textos
//                         try {
//                             HashMap<Integer,Texto> textos = ctrlGestorTextos.getlistadoTextos();
//                             escribirTextos(textos);
//                         } catch (NoSuchElementException e) {
//                             System.err.println("Error: " + e.getMessage());
//                         }  
//                     }
//                     else if(comandoNum == 5) { // Leer desde archivo
//                         // try {
//                         //     System.out.print("Escriba la ruta del archivo de texto: ");
//                         //     String rutaArchivo = lectura.next();
//                         //     //error
//                         //     System.out.println();

//                         //     System.out.println("Ahora se procederá a convertir el texto...");

//                         //     //comprobamos estos errores a parte para evitar que se añada el texto en caso de error de lectura
//                         //     int idAlf = leer_idAlfabeto(lectura, "a convertir");
//                         //     int idFre = leer_idFrecuencia(lectura);  
//                         //     if(ctrlGestorFrecuencias.existeFrecuencia(idFre)) {
//                         //         System.out.println("Error: Listado de frecuencias con id " + idFre + " ya existe.");
//                         //     }
//                         //     else if (ctrlGestorAlfabetos.existe_alfabeto(idAlf)) {
//                         //         System.out.println("Error: Alfabeto con id " + idAlf + " ya existe.");
//                         //     }
//                         //     else {
//                         //         String texto = ctrlGestorTextos.leerDesdeArchivo(rutaArchivo, idAlf, idFre);
//                         //         System.out.println("Texto añadido desde el archivo con éxito!");

//                         //         //convertir a alfabeto
//                         //         String nuevoAlfabeto = ctrlGestorTextos.convertirTextoAAlfabeto(ctrlGestorAlfabetos, idAlf, texto);

//                         //         //convertir a frecuencia
//                         //         ctrlGestorTextos.convertirTextoAFrecuencia(ctrlGestorFrecuencias, idFre, texto);

//                         //         System.out.println("El alfabeto extraído del texto es: " + consultarAlfabeto(nuevoAlfabeto));
//                         //     }
//                         // } catch (IOException e) {
//                         //     System.err.println("Error al leer desde el archivo: " + e.getMessage());
//                         // } catch (IllegalStateException e) {
//                         //     System.err.println("Error: " + e.getMessage());
//                         // } catch (IllegalAccessError e) {
//                         //     System.err.println("Error: " + e.getMessage());
//                         // }         
//                     }
//                     else if(comandoNum == 6) {
//                         menuComandos();
//                     }
//                 }
//             } catch (InputMismatchException e) {
//                 System.err.println("Error: Se esperaba un numero entero.");
//                 // Limpiar el buffer de entrada para evitar bucles infinitos
//                 lectura.nextLine(); 
//             }
//             System.out.println();
//             System.out.print("Introduza un comando (6 para solicitar ayuda): "); 
//         }  
//     }
// }
