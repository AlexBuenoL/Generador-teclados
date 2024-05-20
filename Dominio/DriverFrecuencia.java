// package edu.upc.prop.clusterxx.Dominio;

// import java.util.AbstractMap.SimpleEntry;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.NoSuchElementException;
// import java.util.Scanner;
// import java.util.Set;

// import edu.upc.prop.clusterxx.Dominio.Frecuencia.CtrlGestionFrecuencias;
// import edu.upc.prop.clusterxx.Dominio.Frecuencia.Frecuencia;

// public class DriverFrecuencia {

//     public static int leerID(Scanner scanner) {
//         System.out.println("Escriba el ID de su listado de frecuencias: ");
//         int id = scanner.nextInt();  // Read user input
//         return id;
//     }

//     public static String leerPalabras(Scanner scanner) {
//         String palabra = scanner.next();   //Read user input
//         return palabra;
//     }

//     public static int leerFrecuenciasPalabras(Scanner scanner) {
//         int nVeces = scanner.nextInt();
//         return nVeces;
//     }

//     public static Set<SimpleEntry<String, Integer>> leerCadenasStringInt(Scanner lectura) {
//         Set<SimpleEntry<String, Integer>> setOfPairs = new HashSet<>();
//         while (true) {
//             String palabra = leerPalabras(lectura); 
//             if (palabra.equalsIgnoreCase("finLista")) {
//                 break; // Salir del bucle si el usuario ingresa "fin"
//             }
//             int nVeces = leerFrecuenciasPalabras(lectura);
//             setOfPairs.add(new SimpleEntry<>(palabra, nVeces));
//         }
//         return setOfPairs;
//     }

//     public static Set<String> leerListaPalabras(Scanner lectura) {
//         Set<String> Cjt_palabras = new HashSet<>();
//         while (true) {
//             String palabra = leerPalabras(lectura); 
//             if (palabra.equalsIgnoreCase("finLista")) {
//                 break; // Salir del bucle si el usuario ingresa "fin"
//             }
//             Cjt_palabras.add(palabra);
//         }
//         return Cjt_palabras;
//     }

//     public static void listarFrecuencia(HashMap<String,Integer> frecuencia) {
//         System.out.println("Frecuencia:");

//         for(String i : frecuencia.keySet()) {
//             System.out.println(i + " " + frecuencia.get(i));
//         }
//     }

//     public static void listarTodasFrecuencias(HashMap<Integer,Frecuencia> frecuencias) {
//         System.out.println("Listar frecuencias:");

//         for (int id : frecuencias.keySet()) {
//             System.out.println("-Listado de frecuencias " + id + ":");
//             HashMap<String,Integer> freq = frecuencias.get(id).getFrecuencia();
//             listarFrecuencia(freq);
//         }
//     }

//     public static void menuComandos() {
//         System.out.println("**************************************************");
//         System.out.println("****          Comandos Disponibles           *****");
//         System.out.println("**************************************************");
//         System.out.println("**  0 - Salir del programa                      **");
//         System.out.println("**  1 - Añadir listado Frecuencias              **");
//         System.out.println("**  2 - Consultar Frecuencia                    **");
//         System.out.println("**  3 - Borrar listado Frecuencias              **");
//         System.out.println("**  4 - Escribir listados Frecuencias           **");
//         System.out.println("**  5 - Modificar listados Frecuencias          **");
//         System.out.println("**  6 - Menu Comandos                           **");
//         System.out.println("**************************************************");
//         System.out.println("Driver para comprobar las funcionalidades de los datos del teclado, es decir:");
//         System.out.println("Los alfabetos, las frecuencias y los textos.");
//     }

//     /**
//      * @param args
//      */
//     public static void main(String[] args) {
//         CtrlGestionFrecuencias ctrlGestorFrecuencias = CtrlGestionFrecuencias.getInstance();

//         menuComandos();

//         Scanner lectura = new Scanner(System.in);  // lectura comando   
//         int comandoNum;

//         System.out.println();
//         System.out.println("Introduza un comando (6 para solicitar ayuda): ");

//         while(lectura.hasNextLine()) {
//             comandoNum = lectura.nextInt();

//             if (comandoNum < 0 || comandoNum > 6) System.out.println("Has de introducir un numero entre 0 y 6");
//             else if (comandoNum == 0) break;
//             else {
//                 if(comandoNum == 1) { //añadir un listado de frecuencias
//                     int id = leerID(lectura);  // Read user input

//                     System.out.print("Escriba las palabras y las frecuencias con las que aparecen (para finalizar escriba ''finLista''): ");
//                     Set<SimpleEntry<String, Integer>> setOfPairs = leerCadenasStringInt(lectura);

//                     try {
//                         ctrlGestorFrecuencias.añadirListadoFrecuencias(id, setOfPairs);
//                         System.out.println("Listado de frecuencias con id " + id + " añadido con éxito!");
//                     } catch (IllegalStateException e) {
//                         System.err.println("Error: " + e.getMessage());
//                     }
//                 }
//                 else if(comandoNum == 2) { //consultar una frecuencia
//                     int id = leerID(lectura);  // Read user input
//                     try {
//                         HashMap<String,Integer> freq = ctrlGestorFrecuencias.getListadoFrecuencias(id);
//                         System.out.println("id: " + id);
//                         listarFrecuencia(freq);
//                     } catch (NoSuchElementException e1) {
//                         System.err.println("Error: " + e1.getMessage());
//                     } catch (NullPointerException e2) {
//                         System.err.println("Error: " + e2.getMessage());
//                     }
//                 }
//                 else if(comandoNum == 3) { //borrar un listado de frecuencias
//                     int id = leerID(lectura);  // Read user input

//                     try {
//                         ctrlGestorFrecuencias.borrarListadoFrecuencias(id);
//                         System.out.println("Listado de frecuencias con id " + id + " borrado con éxito!");
//                     } catch (NoSuchElementException e1) {
//                         System.err.println("Error: " + e1.getMessage());
//                     } catch (NullPointerException e2) {
//                         System.err.println("Error: " + e2.getMessage());
//                     }
//                 }
//                 else if(comandoNum == 4) { //escribir todos los listados de frecuencias
//                     try {
//                         HashMap<Integer,Frecuencia> freqs = ctrlGestorFrecuencias.getTodosListadosFrecuencias();
//                         listarTodasFrecuencias(freqs);
//                     } catch (NoSuchElementException e) {
//                         System.err.println("Error: " + e.getMessage());
//                     }
//                 }
//                 else if(comandoNum == 5) { //modificar listado de frecuencias
//                     int id = leerID(lectura);
//                     System.out.println("Pulse 1 si quiere añadir frecuencias a su listado o 2 si desea eliminar frecuencias de su listado");

//                     int option = lectura.nextInt();
//                     if (option < 1 || option > 2) System.out.println("Has de introducir o bien un 1, o un 2");
//                     else {
//                         try {
//                             if (option == 1) {
//                                 System.out.println("-Añadir frecuencias al listado");
//                                 System.out.println("Escriba las palabras y las frecuencias con las que aparecen (para finalizar escriba ''finLista''): ");
//                                 Set<SimpleEntry<String, Integer>> setOfPairs = leerCadenasStringInt(lectura);
//                                 ctrlGestorFrecuencias.añadirFrecuenciasListado(id, setOfPairs);
//                                 System.out.println("Todas las frecuencias se han añadido con éxito!");
//                             }
//                             else if (option == 2) {
//                                 System.out.println("-Eliminar frecuencias del listado");
//                                 System.out.println("Escriba los conjuntos de dos letras que desea eliminar de su listado de frecuencias (para finalizar escriba ''finLista''): ");
//                                 Set<String> letras = leerListaPalabras(lectura);
//                                 ctrlGestorFrecuencias.eliminarFrecuenciasListado(id, letras);
//                                 System.out.println("Todas las frecuencias que pertenecían a su listado se han eliminado con éxito!");
//                             }
//                         } catch (NoSuchElementException e1) {
//                             System.err.println("Error: " + e1.getMessage());
//                         } catch (IllegalArgumentException e2) {
//                             System.err.println("Error: " + e2.getMessage());
//                         } catch (NullPointerException e3) {
//                             System.err.println("Error: " + e3.getMessage());
//                         }
//                     }
//                 }
//                 else if(comandoNum == 6) {
//                     menuComandos();
//                 }
//             }

//             System.out.println();
//             System.out.println("Introduza un comando (6 para solicitar ayuda): ");
//         }
//         lectura.close();   
//     }
// }
