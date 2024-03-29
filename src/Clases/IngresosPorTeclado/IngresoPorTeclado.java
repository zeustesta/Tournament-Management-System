package Clases.IngresosPorTeclado;
import Clases.Excepciones.IngresoAlfanumerico_Excepcion;
import Clases.Excepciones.IngresoNumero_Excepcion;
import Clases.Excepciones.IngresoPalabra_Excepcion;
import java.util.ArrayList;
import java.util.Scanner;
public class IngresoPorTeclado {

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // METODOS

        // INGRESO NUMERICO
            public static int IngresarNumero(Scanner Teclado, String Mensaje, boolean ConConfirmacion, boolean PuedeSerMenos1){
                boolean Continuar_1=true ,Continuar_2=true;
                String Confirmacion;
                String Leido;
                int Retornar=-2;
                while (Continuar_1) {
                    try {
                        System.out.print(Mensaje);
                        Leido = Teclado.nextLine();
                        if (PuedeSerMenos1 && Leido.equals("-1")){
                            Continuar_1 = false;
                            Retornar = -1;
                        }
                        else if (Leido.isBlank()){
                            System.out.print("\n Debe ingresar algo!");
                        }
                        else{
                            EsNumerica(Leido);
                            Retornar = Integer.parseInt(Leido);
                            if (ConConfirmacion){
                                while (Continuar_2){
                                    System.out.print("\n ¿Lo ingresado es correcto? (S/N)");
                                    System.out.print("\n Continuar: ");
                                    Confirmacion = Teclado.nextLine();
                                    if (Confirmacion.equalsIgnoreCase("S")){
                                        Continuar_1 = false;
                                        Continuar_2 = false;
                                    }
                                    else if (Confirmacion.equalsIgnoreCase("N")){
                                        Continuar_2 = false;
                                    }
                                }
                                Continuar_2 = true;
                            }
                            else{
                                Continuar_1 = false;
                            }
                        }
                    }
                    catch (IngresoNumero_Excepcion e) {
                        System.out.print(e.getMessage());
                    }
                }
                return Retornar;
            }

        // INGRESO ALFABETICO
            public static String IngresarPalabra(Scanner Teclado, String Mensaje, boolean ConConfirmacion, boolean PuedeSerMenos1) {
                boolean Continuar_1=true, Continuar_2=true;
                String Palabra="";
                String Confirmacion;
                while(Continuar_1){
                    try {
                        System.out.print(Mensaje);
                        Palabra = Teclado.nextLine();
                        if (PuedeSerMenos1 && Palabra.equals("-1")){
                            Continuar_1 = false;
                            Palabra = "-1";
                        }
                        else if (Palabra.isBlank()){
                            System.out.print("\n Debe ingresar algo!");
                        }
                        else{
                            EsAlfabetica(Palabra);
                            if (ConConfirmacion){
                                while (Continuar_2){
                                    System.out.print("\n ¿Lo ingresado es correcto? (S/N)");
                                    System.out.print("\n Continuar: ");
                                    Confirmacion = Teclado.nextLine();
                                    if (Confirmacion.equalsIgnoreCase("S")){
                                        Continuar_1 = false;
                                        Continuar_2 = false;
                                    }
                                    else if (Confirmacion.equalsIgnoreCase("N")){
                                        Continuar_2 = false;
                                    }
                                }
                                Continuar_2 = true;
                            }
                            else{
                                Continuar_1 = false;
                            }
                        }
                    }
                    catch (IngresoPalabra_Excepcion e) {
                        System.out.print(e.getMessage());
                    }
                }
                return Palabra;
            }

        // INGRESO ALFANUMERICO
            public static String IngresarPalabraAlfanumerica(Scanner Teclado, String Mensaje, boolean ConConfirmacion, boolean PuedeSerMenos1) {
                boolean Continuar_1=true, Continuar_2=true;
                String Palabra="";
                String Confirmacion;
                while(Continuar_1){
                    try {
                        System.out.print(Mensaje);
                        Palabra = Teclado.nextLine();
                        if (PuedeSerMenos1 && Palabra.equals("-1")){
                            Continuar_1 = false;
                            Palabra = "-1";
                        }
                        else if (Palabra.isBlank()){
                            System.out.print("\n Debe ingresar algo!");
                        }
                        else{
                            EsAlfanumerica(Palabra);
                            if (ConConfirmacion){
                                while (Continuar_2){
                                    System.out.print("\n ¿Lo ingresado es correcto? (S/N)");
                                    System.out.print("\n Continuar: ");
                                    Confirmacion = Teclado.nextLine();
                                    if (Confirmacion.equalsIgnoreCase("S")){
                                        Continuar_1 = false;
                                        Continuar_2 = false;
                                    }
                                    else if (Confirmacion.equalsIgnoreCase("N")){
                                        Continuar_2 = false;
                                    }
                                }
                                Continuar_2 = true;
                            }
                            else{
                                Continuar_1 = false;
                            }
                        }
                    }
                    catch (IngresoAlfanumerico_Excepcion e) {
                        System.out.print(e.getMessage());
                    }
                }
                return Palabra;
            }

        // INGRESO ALFABETICO
            public static int IngresarNumero(Scanner Teclado, String Mensaje, boolean ConConfirmacion, boolean PuedeSerMenos1, ArrayList<String> Permitidos){
                boolean Continuar_1=true ,Continuar_2=true;
                String Confirmacion;
                String Leido;
                int Retornar=-2;
                while (Continuar_1) {
                    try {
                        System.out.print(Mensaje);
                        Leido = Teclado.nextLine();
                        if (Permitidos.contains(Leido)){
                            Continuar_1 = false;
                            Retornar = Integer.parseInt(Leido);
                        }
                        else if (PuedeSerMenos1 && Leido.equals("-1")){
                            Continuar_1 = false;
                            Retornar = -1;
                        }
                        else if (Leido.isBlank()){
                            System.out.print("\n Debe ingresar algo!");
                        }
                        else{
                            EsNumerica(Leido);
                            Retornar = Integer.parseInt(Leido);
                            if (ConConfirmacion){
                                while (Continuar_2){
                                    System.out.print("\n ¿Lo ingresado es correcto? (S/N)");
                                    System.out.print("\n Continuar: ");
                                    Confirmacion = Teclado.nextLine();
                                    if (Confirmacion.equalsIgnoreCase("S")){
                                        Continuar_1 = false;
                                        Continuar_2 = false;
                                    }
                                    else if (Confirmacion.equalsIgnoreCase("N")){
                                        Continuar_2 = false;
                                    }
                                }
                                Continuar_2 = true;
                            }
                            else{
                                Continuar_1 = false;
                            }
                        }
                    }
                    catch (IngresoNumero_Excepcion e) {
                        System.out.print(e.getMessage());
                    }
                }
                return Retornar;
            }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // VERIFICADORES

        // ES NUMERICA
            private static void EsNumerica(String Chequear) throws IngresoNumero_Excepcion {
                for (int i=0 ; i<Chequear.length() ; i++){
                    if (Chequear.charAt(i)<48){
                        throw new IngresoNumero_Excepcion();
                    }
                    else if (Chequear.charAt(i)>57){
                        throw new IngresoNumero_Excepcion();
                    }
                }
            }

        // ES ALFABETICA
            private static void EsAlfabetica(String Chequear) throws IngresoPalabra_Excepcion {
                for (int i=0 ; i<Chequear.length() ; i++){
                    if (Chequear.charAt(i)!=32){
                        if (Chequear.charAt(i)<65){
                            throw new IngresoPalabra_Excepcion();
                        }
                        else if (Chequear.charAt(i)>122){
                            throw new IngresoPalabra_Excepcion();
                        }
                        else if(Chequear.charAt(i)>90 && Chequear.charAt(i)<97){
                            throw new IngresoPalabra_Excepcion();
                        }
                    }
                }
            }

        // ES ALFANUMERICA
            private static void EsAlfanumerica(String Chequear) throws IngresoAlfanumerico_Excepcion {
                for (int i=0 ; i<Chequear.length() ; i++){
                    if (Chequear.charAt(i)!=32){
                        if (Chequear.charAt(i) < 48) {
                            throw new IngresoAlfanumerico_Excepcion();
                        }
                        else if (Chequear.charAt(i) > 57 && Chequear.charAt(i) < 65) {
                            throw new IngresoAlfanumerico_Excepcion();
                        }
                        else if (Chequear.charAt(i) > 122) {
                            throw new IngresoAlfanumerico_Excepcion();
                        }
                        else if (Chequear.charAt(i) > 90 && Chequear.charAt(i) < 97) {
                            throw new IngresoAlfanumerico_Excepcion();
                        }
                    }
                }
            }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}