// package edu.upc.prop.clusterxx.Dominio;
// import java.text.Normalizer;

// import java.io.IOException;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.InputMismatchException;
// import java.util.NoSuchElementException;
// import java.util.Scanner;
// import java.util.Set;

// import edu.upc.prop.clusterxx.Dominio.alfabeto.Alfabeto;

// import edu.upc.prop.clusterxx.Dominio.alfabeto.CtrlGestionAlfabetos;

// public class DriverAlfabeto {
//     public static int leer_idAlfabeto(Scanner scanner, String detalle) {
//         System.out.print("Escriba la id del alfabeto " + detalle + ": ");
//         int id = scanner.nextInt();  // Read user input
//         scanner.nextLine(); // Consumir el salto de línea
//         return id;
//     }
//     public static String leer_letrasAlfabeto(Scanner scanner, String detalle) {
//         System.out.print("Escriba las letras del alfabeto " + detalle + ": ");
//         String letras = scanner.nextLine();  // Read user input
//         return letras;
//     }
//     public static int leer_opcion(Scanner scanner, String detalle) {
//         System.out.print("Escriba 1 si quiere añadir " + detalle + " o 2 si quiere quitar " + detalle +": ");
//         int opcion = scanner.nextInt();
//         if(opcion != 1 && opcion != 2) return -1;
//         else return opcion;
//     }
//     public static void consultarAlfabeto(int id, String alfabeto) {
//         System.out.println("id: " + id);
//         alfabeto = alfabeto.toLowerCase();
        
//         if (alfabeto.startsWith("[")) {
//             // El alfabeto ya está representado como un conjunto, imprímelo tal cual
//             System.out.println("Alfabeto: " + alfabeto);
//         } 
//         else {
//             // El alfabeto está en formato original "abc", cámbialo a [a, b, c]
//             Set<Character> alfabetoSet = new HashSet<>();
//             for (char caracter : alfabeto.toCharArray()) {
//                 // Normaliza y quita diacríticas de la letra
//                 String letraNormalizada = Normalizer.normalize(String.valueOf(caracter), Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    
//                 // Agrega solo caracteres diferentes (sin distinción de mayúsculas o minúsculas)
//                 if (Character.isLetter(caracter) && !alfabetoSet.contains(letraNormalizada.charAt(0))) {
//                     alfabetoSet.add(letraNormalizada.charAt(0));
//                 }
//             }
//             System.out.println("Alfabeto: " + alfabetoSet);
//         }
//     }
//     public static void escribirAlfabetos(HashMap<Integer,Alfabeto> alfabetos) {
//         System.out.println("Los alfabetos del sistema son los siguientes:");
//         for(int i : alfabetos.keySet()) {
//             String alfabetoString = alfabetos.get(i).toString();
//             consultarAlfabeto(i, alfabetoString);
//         }
//     }


//     public static void menuComandos() {
//         System.out.println("**************************************************");
//         System.out.println("****          Comandos Disponibles           *****");
//         System.out.println("**************************************************");
//         System.out.println("**  0 - Salir del programa                      **");
//         System.out.println("**  1 - Añadir Alfabeto                         **");
//         System.out.println("**  2 - Escribir Alfabetos                      **");
//         System.out.println("**  3 - Consultar Alfabeto                      **");
//         System.out.println("**  4 - Modificar Alfabeto                      **");
//         System.out.println("**  5 - Borrar Alfabeto                         **");
//         System.out.println("**  6 - leer de archivo                         **");
//         System.out.println("**  7 - Help                                    **");
//         System.out.println("**************************************************");
//         System.out.println("Driver para comprobar la funcionalidad de Alfabeto:");
//     }

//     public static void main(String[] args) {
//         CtrlGestionAlfabetos ctrlGestorAlfabetos = CtrlGestionAlfabetos.getInstance();
//         menuComandos();

//         Scanner lectura = new Scanner(System.in);  // lectura comando   
//         int comandoNum;

//         System.out.println();
//         System.out.print("Introduza un comando (6 para solicitar ayuda): ");


//         while(lectura.hasNextLine()) {
//             //System.out.print("Introduce un numero de comanda: ");
//             try {
//                 comandoNum = lectura.nextInt();
//                 if(comandoNum < 0 || comandoNum > 7) System.err.println("Error: Se ha de introducir un numero del 0 al 5.");
//                 else if (comandoNum == 0) break;
//                 else {
//                     if(comandoNum == 1) { //añadir alfabeto
//                         try {
//                             int id = leer_idAlfabeto(lectura, "a añadir");
//                             String letras = leer_letrasAlfabeto(lectura,"a añadir");
//                             ctrlGestorAlfabetos.añadirAlfabeto(id, letras);
//                             System.out.println("Alfabeto con id " + id + " añadido con éxito!");
//                         } catch (InputMismatchException e2) {
//                             System.err.println("Error: Se esperaba un numero entero.");
//                             // Limpiar el buffer de entrada para evitar bucles infinitos
//                             lectura.nextLine();
//                         } catch (IllegalStateException e1) {
//                             System.err.println("Error: " + e1.getMessage());
//                         } 
//                     }
//                     else if(comandoNum == 2) { //escribir todos los alfabetos
//                         try {
//                             HashMap<Integer,Alfabeto> alfabetos = ctrlGestorAlfabetos.getlistadoAlfabetos();
//                             escribirAlfabetos(alfabetos);
//                         } catch (NoSuchElementException e) {
//                             System.err.println("Error: " + e.getMessage());
//                         }   
//                     }
//                     else if(comandoNum == 3) { //consultar alfabeto
//                         try {
//                             int id = leer_idAlfabeto(lectura, "a consultar"); 
//                             String alfabeto = ctrlGestorAlfabetos.consultarAlfabeto(id);
//                             consultarAlfabeto(id, alfabeto); 
//                         } catch (InputMismatchException e1) {
//                             System.err.println("Error: Se esperaba un numero entero.");
//                             // Limpiar el buffer de entrada para evitar bucles infinitos
//                             lectura.nextLine();
//                         } catch (NoSuchElementException e2) {
//                             System.err.println("Error: " + e2.getMessage());
//                         } catch (NullPointerException e3) {
//                             System.err.println("Error: " + e3.getMessage());
//                         } 
//                     }
//                     else if(comandoNum == 4) { //modificar alfabeto
//                         try {
//                             //primero muestro los alfabetos
//                             HashMap<Integer,Alfabeto> alfabetos = ctrlGestorAlfabetos.getlistadoAlfabetos();
//                             escribirAlfabetos(alfabetos);
//                             //leo del usuario
//                             int id = leer_idAlfabeto(lectura, "a modificar"); 
//                             String letras = leer_letrasAlfabeto(lectura, "a modificar");
//                             int opcion = leer_opcion(lectura, "letras");
//                             if(opcion == -1) System.out.print("Error: Tienes que escribir la opcion 1 o 2.");
//                             else {
//                                 int vacio = ctrlGestorAlfabetos.modificarAlfabeto(id, letras, opcion); 
//                                 if(vacio == -1) {
//                                     System.out.println("Alfabeto con id " + id + " borrado porque has eliminado todas las letras.");
//                                 }
//                                 else System.out.println("Alfabeto con id " + id + " modificado con éxito!");
//                             }
//                         } catch (InputMismatchException e1) {
//                             System.err.println("Error: Se esperaba un numero entero.");
//                             // Limpiar el buffer de entrada para evitar bucles infinitos
//                             lectura.nextLine();
//                         } catch (NoSuchElementException e1) {
//                             System.err.println("Error: " + e1.getMessage());
//                         } catch (NullPointerException e2) {
//                             System.err.println("Error: " + e2.getMessage());
//                         } catch (IllegalArgumentException e3) {
//                             System.err.println("Error: " + e3.getMessage());
//                         }
//                     }
//                     else if(comandoNum == 5) { //borrar alfabeto
//                         try {
//                             //primero muestro los alfabetos
//                             HashMap<Integer,Alfabeto> alfabetos = ctrlGestorAlfabetos.getlistadoAlfabetos();
//                             escribirAlfabetos(alfabetos);
//                             //leo del usuario
//                             int id = leer_idAlfabeto(lectura, "a borrar");  // Read user input
//                             ctrlGestorAlfabetos.borrarAlfabeto(id); 
//                             System.out.println("Alfabeto con id " + id + " borrado con éxito!");
//                         } catch (InputMismatchException e1) {
//                             System.err.println("Error: Se esperaba un numero entero.");
//                             // Limpiar el buffer de entrada para evitar bucles infinitos
//                             lectura.nextLine();
//                         }
//                         catch (NoSuchElementException e1) {
//                             System.err.println("Error: " + e1.getMessage());
//                         } catch (NullPointerException e2) {
//                             System.err.println("Error: " + e2.getMessage());
//                         }
//                     }
//                     else if (comandoNum == 6) { //leer desde archivo
//                         try {
//                             System.out.print("Escriba la ruta del archivo: ");
//                             lectura.nextLine();  // Consumir el salto de línea pendiente
//                             String rutaArchivo = lectura.nextLine();
//                             ctrlGestorAlfabetos.leerDesdeArchivo(rutaArchivo);
//                             System.out.println("Archivo leído correctamente.");
//                         } catch (IOException e) {
//                             System.err.println("Error al leer el archivo: " + e.getMessage());
//                         } catch (IllegalStateException e1) {
//                             System.err.println("Error: " + e1.getMessage());
//                         } 
//                     }
//                     else if(comandoNum == 7) {
//                         menuComandos();
//                     }
//                 }
//             } catch (InputMismatchException e) {
//                 System.err.println("Error: Se esperaba un numero entero.");
//                 // Limpiar el buffer de entrada para evitar bucles infinitos
//                 lectura.nextLine(); 
//             }
//             System.out.println();
//             System.out.print("Introduza un comando (7 para solicitar ayuda): ");
//         }
//     }
// }
