package Clases.Administrador;
import Clases.Equipo.*;
import Clases.Exportador.*;
import Clases.Generica.ListaDeDatos;
import Clases.IngresosPorTeclado.*;
import Clases.Persona.*;
import Clases.Torneo.*;
import Enum.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class Administrador_Nueva {

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ATRIBUTOS

        // FUTBOL
            private ArrayList<Equipo_Futbol> Equipos_Futbol;
            private ArrayList<Torneo_Futbol> Torneos_Futbol;

        // TENNIS
            private ArrayList<Equipo_Tennis> Equipos_Tennis;
            private ArrayList<Torneo_Tennis> Torneos_Tennis;

        // PING PONG
            private ArrayList<Equipo_PingPong> Equipos_PingPong;
            private ArrayList<Torneo_PingPong> Torneos_PingPong;

        // ARBITROS
            private ListaDeDatos<Arbitro> Arbitros;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // CONSTRUCTOR
        public Administrador_Nueva() {
            // FUTBOL
                Equipos_Futbol = new ArrayList<>();
                Torneos_Futbol = new ArrayList<>();

            // TENNIS
                Equipos_Tennis = new ArrayList<>();
                Torneos_Tennis = new ArrayList<>();

            // PING PONG
                Equipos_PingPong = new ArrayList<>();
                Torneos_PingPong = new ArrayList<>();

            // ARBITROS
                Arbitros = new ListaDeDatos<>();
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // MENU PRINCIPAL
            public void Menu_Principal(Scanner Teclado) {
                boolean Retornar = true;
                int Swtch;
                while (Retornar) {
                    System.out.print("\n Bienvenido!");
                    System.out.print("\n");
                    System.out.print("\n Elija una de las siguientes opciones.");
                    System.out.print("\n");
                    System.out.print("\n 01 - Menu de Torneos.");
                    System.out.print("\n 02 - Menu de Equipos.");
                    System.out.print("\n 03 - Menu de Arbitros.");
                    System.out.print("\n");
                    System.out.print("\n 04 - Exportar.");
                    System.out.print("\n");
                    System.out.print("\n 00 - Guardar y salir.");
                    System.out.print("\n");
                    Swtch = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion elegida: ", false, false);
                    switch (Swtch) {
                        case 1 -> // MENU TORNEOS
                                MenuTorneos(Teclado);
                        case 2 -> // MENU EQUIPOS
                                MenuEquipos(Teclado);
                        case 3 -> // MENU ARBITROS
                                MenuArbitros(Teclado);
                        case 4 -> // MENU EXPORTAR
                                ExportarDatos(Teclado);
                        case 0 -> // SALIR
                                Retornar = false;
                        default -> {}
                    }
                }
            }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // MENU TORNEOS
                private void MenuTorneos(Scanner Teclado){
                    boolean Retornar = true;
                    int Swtch;
                    while (Retornar){
                        System.out.print("\n Elija una de las siguientes opciones:");
                        System.out.print("\n");
                        System.out.print("\n 01 - Ver todos los torneos.");
                        System.out.print("\n 02 - Crear torneo.");
                        System.out.print("\n 03 - Eliminar torneo.");
                        System.out.print("\n 04 - Comenzar/Continuar torneo.");
                        System.out.print("\n");
                        System.out.print("\n 00 - Volver.");
                        System.out.print("\n");
                        Swtch = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion elegida:", false, false);
                        switch (Swtch) {
                            case 1 -> { // MENU VER TODOS LOS TORNEO
                                if (ExistenTorneosParaMostrar()) {
                                    MenuTorneos_MostrarTorneos(Teclado);
                                } else {
                                    System.out.print("\n No hay ningun torneo en el historial para mostrar.");

                                }
                            }
                            case 2 -> {
                                // CREAR TORNEOS
                                if (Arbitros.size() == 0) {
                                    System.out.print("\n No existe ningun arbitro activo para arbitrar en un torneo!");

                                } else {
                                    Crear_Torneo(Teclado);
                                }
                            }
                            case 3 -> { // ELIMINAR TORNEO
                                if (ExistenTorneosParaMostrar()) {
                                    EliminarTorneo(Teclado);
                                } else {
                                    System.out.print("\n No existen torneos para eliminar!.");
                                }
                            }
                            case 4 -> { // COMENZAR / CONTINUAR TORNEO
                                if (ExistenTorneosParaContinuar()) {
                                    ComenzarContinuar_Torneo(Teclado);
                                } else {
                                    System.out.print("\n No existen torneos activos para Comenzar o Continuar!");
                                }
                            }
                            case 0 -> // SALIR
                                    Retornar = false;
                            default -> {
                            }
                        }
                    }
                }

                // MENU MOSTRAR TORNEOS
                    private void MenuTorneos_MostrarTorneos(Scanner Teclado){
                        boolean Continuar = true;
                        int Opcion;
                        while(Continuar){
                            Mostrar_Torneos();
                            System.out.print("\n Ingrese el Id de un equipo para ver todos sus detalles.");
                            System.out.print("\n Ingrese -1 para volver al menu anterior.");
                            System.out.print("\n");
                            Opcion = IngresoPorTeclado.IngresarNumero(Teclado, "\n Id: ", false, true);
                            if (Opcion == -1){
                                Continuar=false;
                            }
                            else{
                                if (ExisteIdTorneo(Opcion)){
                                    Torneo Mostrar = RetornarTorneo_Id(Opcion);
                                    if (Mostrar instanceof Torneo_Futbol Aux){
                                        Mostrar_Torneo_Futbol_Detallado(Aux);
                                    }
                                    else if (Mostrar instanceof Torneo_Tennis Aux){
                                        Mostrar_Torneo_Tennis_Detallado(Aux);
                                    }
                                    else if (Mostrar instanceof Torneo_PingPong Aux){
                                        Mostrar_Torneo_PingPong_Detallado(Aux);
                                    }
                                    System.out.print("\n ");
                                    Teclado.nextLine();
                                }
                            }
                        }
                    }

                    // MOSTRAR TORNEOS
                        private void Mostrar_Torneos(){
                            System.out.print("\n ---------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n |-Deporte-|-|Id de Torneo|-|-------Nombre del Torneo-------|-|--------Activo--------|-|--------Ganador--------|");
                            for (Torneo_Futbol TorneoAux : Torneos_Futbol){
                                Mostrar_Torneo_Futbol(TorneoAux);
                            }
                            System.out.print("\n ---------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n |-Deporte-|-|Id de Torneo|-|-------Nombre del Torneo-------|-|--------Activo--------|-|--------Ganador--------|");
                            for (Torneo_Tennis TorneoAux : Torneos_Tennis){
                                Mostrar_Torneo_Tennis(TorneoAux);
                            }
                            System.out.print("\n ---------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n |-Deporte-|-|Id de Torneo|-|-------Nombre del Torneo-------|-|--------Activo--------|-|--------Ganador--------|");
                            for (Torneo_PingPong TorneoAux : Torneos_PingPong){
                                Mostrar_Torneo_PingPong(TorneoAux);
                            }
                            System.out.print("\n ---------------------------------------------------------------------------------------------------------------");
                        }

                        // MOSTRAR TORNEOS DE FUTBOL
                            private void Mostrar_Torneo_Futbol(Torneo_Futbol Mostrar){
                                System.out.print("\n |   Futbol|");
                                System.out.printf("-|%12d|", Mostrar.getId());
                                System.out.printf("-|%31s|", Mostrar.getNombre());
                                System.out.printf("-|%22s|", Mostrar.EstaActivo());
                                if (Mostrar.isActivo()){
                                    System.out.print("-|                       |");
                                }
                                else{
                                    System.out.printf("-|%23s|", Mostrar.GanadorString());
                                }
                            }

                        // MOSTRAR TORNEOS DE TENNIS
                            private void Mostrar_Torneo_Tennis(Torneo_Tennis Mostrar){
                                System.out.print("\n |   Tennis|");
                                System.out.printf("-|%12d|", Mostrar.getId());
                                System.out.printf("-|%31s|", Mostrar.getNombre());
                                System.out.printf("-|%22s|", Mostrar.EstaActivo());
                                if (Mostrar.isActivo()){
                                    System.out.print("-|                       |");
                                }
                                else{
                                    System.out.printf("-|%23s|", Mostrar.GanadorString());
                                }
                            }

                        // MOSTRAR TORNEOS DE PING PONG
                            private void Mostrar_Torneo_PingPong(Torneo_PingPong Mostrar){
                                System.out.print("\n |Ping Pong|");
                                System.out.printf("-|%12d|", Mostrar.getId());
                                System.out.printf("-|%31s|", Mostrar.getNombre());
                                System.out.printf("-|%22s|", Mostrar.EstaActivo());
                                if (Mostrar.isActivo()){
                                    System.out.print("-|                       |");
                                }
                                else{
                                    System.out.printf("-|%23s|", Mostrar.GanadorString());
                                }
                            }

                    // MOSTRAR EN DETALLES TORNEOS DE FUTBOL
                        private void Mostrar_Torneo_Futbol_Detallado(Torneo_Futbol Mostrar){
                            System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n");
                            System.out.print("\n Id: "+Mostrar.getId());
                            System.out.print("\n Nombre: "+Mostrar.getNombre());
                            System.out.print("\n Estado: " +Mostrar.EstaActivo());
                            if (!Mostrar.isActivo()){
                                System.out.print("\n Ganador: "+Mostrar.GanadorString());
                            }
                            System.out.print("\n");
                            System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n Equipos Participantes:");
                            Mostrar.MostrarEquiposParticipantes();
                            System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n Todos los partidos:");
                            Mostrar.MostrarTodosLosPartidos();
                            System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        }

                    // MOSTRAR EN DETALLES TORNEOS DE TENNIS
                        private void Mostrar_Torneo_Tennis_Detallado(Torneo_Tennis Mostrar){
                            System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n");
                            System.out.print("\n Id: "+Mostrar.getId());
                            System.out.print("\n Nombre: "+Mostrar.getNombre());
                            System.out.print("\n Estado: " +Mostrar.EstaActivo());
                            if (!Mostrar.isActivo()){
                                System.out.print("\n Ganador: "+Mostrar.GanadorString());
                            }
                            System.out.print("\n");
                            System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n Equipos Participantes:");
                            Mostrar.MostrarEquiposParticipantes();
                            System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n Todos los partidos:");
                            Mostrar.MostrarTodosLosPartidos();
                            System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        }

                    // MOSTRAR EN DETALLES TORNEOS DE PING PONG
                        private void Mostrar_Torneo_PingPong_Detallado(Torneo_PingPong Mostrar){
                            System.out.print("\n ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n");
                            System.out.print("\n Id: "+Mostrar.getId());
                            System.out.print("\n Nombre: "+Mostrar.getNombre());
                            System.out.print("\n Estado: " +Mostrar.EstaActivo());
                            if (!Mostrar.isActivo()){
                                System.out.print("\n Ganador: "+Mostrar.GanadorString());
                            }
                            System.out.print("\n");
                            System.out.print("\n ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n Equipos Participantes:");
                            Mostrar.MostrarEquiposParticipantes();
                            System.out.print("\n ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n Todos los partidos:");
                            Mostrar.MostrarTodosLosPartidos();
                            System.out.print("\n ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                        }

                // CREAR TORNEO
                    private void Crear_Torneo(Scanner Teclado){
                        boolean Retornar = true;
                        int Swtch;
                        while (Retornar){
                            System.out.print("\n Elija un deporte para crear el torneo.");
                            System.out.print("\n");
                            System.out.print("\n 01 - Futbol.");
                            System.out.print("\n 02 - Tennis.");
                            System.out.print("\n 03 - Ping Pong.");
                            System.out.print("\n");
                            System.out.print("\n 00 - Volver.");
                            System.out.print("\n");
                            Swtch = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion elegida: ", false, false);
                            switch (Swtch) {
                                case 1 -> { // FUTBOL
                                    if (Equipos_Futbol.size()>=16){
                                        Crear_Torneo_Futbol(Teclado);
                                        Retornar = false;

                                    }
                                    else{
                                        System.out.print("\n No hay suficientes equipos de Futbol para crear un torneo.");
                                    }
                                }
                                case 2 -> { // TENNIS
                                    if (Equipos_Tennis.size()>=16){
                                        Crear_Torneo_Tennis(Teclado);
                                        Retornar = false;

                                    }
                                    else{
                                        System.out.print("\n No hay suficientes equipos de Tennis para crear un torneo.");
                                    }
                                }
                                case 3 -> { // PING PONG
                                    if (Equipos_PingPong.size()>=16){
                                        Crear_Torneo_PingPong(Teclado);
                                        Retornar = false;
                                    }
                                    else{
                                        System.out.print("\n No hay suficientes equipos de Ping Pong para crear un torneo.");
                                    }
                                }
                                case 0 -> // SALIR
                                        Retornar = false;
                                default -> {}
                            }
                        }
                    }

                    // CREAR TORNEOS DE FUTBOL
                        private void Crear_Torneo_Futbol(Scanner Teclado){
                            String NombreTorneo = "";
                            boolean Continuar = true;
                            boolean Cancelar = false;
                            int OpcionElegida;

                            // INGRESAR NOMBRE DEL TORNEO
                            while (Continuar){
                                System.out.print("\n Creacion de torneo de Futbol.");
                                System.out.print("\n");
                                System.out.print("\n Ingrese -1 para cancelar.");
                                NombreTorneo = IngresoPorTeclado.IngresarPalabra(Teclado, "\n Ingrese el nombre de su torneo: ", true, true);
                                if (NombreTorneo.equals("-1")){
                                    Cancelar = true;
                                    Continuar = false;
                                }
                                else if (ExisteTorneo_Nombre(NombreTorneo)){
                                    System.out.print("\n El nombre ingresado ya existe en otro torneo del mismo u otro deporte.");
                                }
                                else{
                                    Continuar = false;
                                }
                            }
                            if (!Cancelar) {
                                ArrayList<Equipo_Futbol> NuevoTorneo_Equipos = new ArrayList<>(16);
                                int Id_Buscado;
                                // INGRESAR OPCION
                                Continuar = true;
                                while (Continuar) {
                                    System.out.print("\n Elija una de las siguientes opciones:");
                                    System.out.print("\n");
                                    System.out.print("\n 1 - Elegir equipos.");
                                    System.out.print("\n 2 - Agregar al torneo los primeros 16 equipos.");
                                    System.out.print("\n");
                                    System.out.print("\n -1 - Cancelar.");
                                    System.out.print("\n");
                                    OpcionElegida = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion elegida: ", false, true);
                                    if (OpcionElegida == -1) {
                                        Continuar = false;
                                        Cancelar = true;
                                        System.out.print("\n Cancelado!");
                                    }
                                    else if (OpcionElegida == 1) {
                                        Continuar = false;
                                        int i = 0;
                                        while (i < 16) {
                                            System.out.print("\n Ingrese el Id de alguno de los siguientes equipos para añadirlo en el torneo.");
                                            System.out.print("\n Recuerde que solo apareceran aquellos que esten listos para jugar, y que no haya sido añadido anteriormente.");
                                            System.out.print("\n Ingrese -1 para volver.");
                                            System.out.print("\n Faltan " + (16 - NuevoTorneo_Equipos.size()) + " equipos por añadir.");
                                            System.out.print("\n ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                            System.out.print("\n |-Deporte-|-|Id de Equipo|-|-------Nombre del Equipo-------|-|------Nombre del Entrenador------|-|Tornes ganados|-|Torneos perdidos|-|Partidos ganador|-|Partidos perdidos|");
                                            for (Equipo_Futbol Equipo_Aux : Equipos_Futbol) {
                                                if (!NuevoTorneo_Equipos.contains(Equipo_Aux)) {
                                                    Mostrar_Equipo_Futbol(Equipo_Aux);
                                                }
                                            }
                                            System.out.print("\n ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                            Id_Buscado = IngresoPorTeclado.IngresarNumero(Teclado, "\n Id del Equipo: ", false, true);
                                            Id_Buscado = RetornarPosicionEquipo_PorId(Id_Buscado, 1);
                                            if (Id_Buscado != -1) {
                                                Equipo_Futbol A_Agregar_Si_Verifica = Equipos_Futbol.get(Id_Buscado);
                                                if (!NuevoTorneo_Equipos.contains(A_Agregar_Si_Verifica)) {
                                                    i++;
                                                    NuevoTorneo_Equipos.add(A_Agregar_Si_Verifica);
                                                }
                                                else {
                                                    System.out.print("\n No existe ningun equipo de futbol activo y que no este en la lista con el Id ingresado.");
                                                }
                                            }
                                            else {
                                                System.out.print("\n Cancelado!");
                                                Cancelar = true;
                                                break;
                                            }
                                        }
                                    }
                                    else if (OpcionElegida == 2) {
                                        Continuar = false;
                                        for (Equipo_Futbol Equipo_Aux : Equipos_Futbol) {
                                            if (NuevoTorneo_Equipos.size() < 16) {
                                                NuevoTorneo_Equipos.add(Equipo_Aux);
                                            }
                                            else {
                                                break;
                                            }
                                        }
                                    }
                                    else {
                                        System.out.print("\n Elija una de las opciones ofrecidas!");
                                    }
                                }
                                if (!Cancelar){
                                    Torneo_Futbol Nuevo_Torneo = new Torneo_Futbol(NombreTorneo, NuevoTorneo_Equipos, Arbitros);
                                    Torneos_Futbol.add(Nuevo_Torneo);
                                    System.out.println("\n Torneo creado!");
                                }
                            }
                        }

                    // CREAR TORNEOS DE TENNIS
                        private void Crear_Torneo_Tennis(Scanner Teclado){
                            String NombreTorneo="";
                            boolean Continuar = true;
                            boolean Cancelar=false;
                            int OpcionElegida;

                            // INGRESAR NOMBRE DEL TORNEO
                            while (Continuar){
                                System.out.print("\n Creacion de torneo de Tennis.");
                                System.out.print("\n");
                                System.out.print("\n Ingrese -1 para cancelar.");
                                NombreTorneo = IngresoPorTeclado.IngresarPalabra(Teclado, "\n Ingrese el nombre de su torneo: ", true, true);
                                if (NombreTorneo.equals("-1")){
                                    Continuar = false;
                                    Cancelar = true;
                                    System.out.print("\n Cancelado!");
                                }
                                else if (ExisteTorneo_Nombre(NombreTorneo)){
                                    System.out.print("\n El nombre ingresado ya existe en otro torneo del mismo u otro deporte.");
                                }
                                else{
                                    Continuar = false;
                                }
                            }
                            if (!Cancelar){
                                ArrayList<Equipo_Tennis> NuevoTorneo_Equipos = new ArrayList<>(16);
                                int Id_Buscado;
                                // INGRESAR OPCION
                                Continuar = true;
                                while (Continuar){
                                    System.out.print("\n Elija una de las siguientes opciones:");
                                    System.out.print("\n");
                                    System.out.print("\n 1 - Elegir equipos.");
                                    System.out.print("\n 2 - Agregar al torneo los primeros 16 equipos.");
                                    System.out.print("\n");
                                    System.out.print("\n -1 - Cancelar.");
                                    System.out.print("\n");
                                    OpcionElegida = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion elegida: ", false, true);
                                    if (OpcionElegida==-1){
                                        Cancelar = true;
                                        Continuar = false;
                                        System.out.print("\n Cancelado!");
                                    }
                                    else if (OpcionElegida == 1){
                                        int i=0;
                                        while (i<16){
                                            System.out.print("\n Ingrese el Id de alguno de los siguientes equipos para añadirlo en el torneo.");
                                            System.out.print("\n Recuerde que solo apareceran aquellos que esten listos para jugar, y que no haya sido añadido anteriormente.");
                                            System.out.print("\n Ingrese -1 para volver.");
                                            System.out.print("\n Faltan "+(16-NuevoTorneo_Equipos.size())+" equipos por añadir.");
                                            System.out.print("\n ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                            System.out.print("\n |-Deporte-|-|Id de Equipo|-|-------Nombre del Jugador-------|-|------Nombre del Entrenador------|-|Tornes ganados|-|Torneos perdidos|-|Partidos ganador|-|Partidos perdidos|");
                                            for (Equipo_Tennis Equipo_Aux : Equipos_Tennis){
                                                if (!NuevoTorneo_Equipos.contains(Equipo_Aux) ){
                                                    Mostrar_Equipo_Tennis(Equipo_Aux);
                                                }
                                            }
                                            System.out.print("\n ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                            Id_Buscado = IngresoPorTeclado.IngresarNumero(Teclado, "\n Id del Equipo: ", false, true);
                                            Id_Buscado = RetornarPosicionEquipo_PorId(Id_Buscado, 2);
                                            if (Id_Buscado!=-1){
                                                Equipo_Tennis A_Agregar_Si_Verifica = Equipos_Tennis.get(Id_Buscado);
                                                if (!NuevoTorneo_Equipos.contains(A_Agregar_Si_Verifica)){
                                                    i++;
                                                    NuevoTorneo_Equipos.add(A_Agregar_Si_Verifica);
                                                }
                                                else{
                                                    System.out.print("\n No existe ningun equipo de futbol activo y que no este en la lista con el Id ingresado.");
                                                }
                                            }
                                            else{
                                                System.out.print("\n Cancelado!");
                                                Cancelar = true;
                                                break;
                                            }
                                        }
                                        Continuar = false;
                                    }
                                    else if (OpcionElegida == 2){
                                        for (Equipo_Tennis Equipo_Aux : Equipos_Tennis){
                                            if (NuevoTorneo_Equipos.size()<16){
                                                NuevoTorneo_Equipos.add(Equipo_Aux);
                                            }
                                            else{
                                                break;
                                            }
                                        }
                                        Continuar = false;
                                    }
                                    else{
                                        System.out.print("\n Elija una de las opciones ofrecidas!");
                                    }
                                }
                                if (!Cancelar){
                                    Torneo_Tennis Nuevo_Torneo = new Torneo_Tennis(NombreTorneo, NuevoTorneo_Equipos, Arbitros);
                                    Torneos_Tennis.add(Nuevo_Torneo);
                                    System.out.println("\n Torneo creado!");
                                }
                            }
                        }

                    // CREAR TORNEOS DE PING PONG
                        private void Crear_Torneo_PingPong(Scanner Teclado){
                            String NombreTorneo="";
                            boolean Continuar = true;
                            boolean Cancelar = false;
                            int OpcionElegida;

                            // INGRESAR NOMBRE DEL TORNEO
                            while (Continuar){
                                System.out.print("\n Creacion de torneo de Ping Pong.");
                                System.out.print("\n");
                                System.out.print("\n Ingrese -1 para cancelar.");
                                NombreTorneo = IngresoPorTeclado.IngresarPalabra(Teclado, "\n Ingrese el nombre de su torneo: ", true, true);
                                if (NombreTorneo.equals("-1")){
                                    Cancelar = true;
                                    Continuar = false;
                                    System.out.print("\n Cancelado!");
                                }
                                else if (ExisteTorneo_Nombre(NombreTorneo)){
                                    System.out.print("\n El Id ingresado ya existe en otro torneo del mismo u otro deporte.");
                                }
                                else{
                                    Continuar = false;
                                }
                            }
                            if (!Cancelar){
                                ArrayList<Equipo_PingPong> NuevoTorneo_Equipos = new ArrayList<>(16);
                                int Id_Buscado;
                                // INGRESAR OPCION
                                Continuar = true;
                                while(Continuar){
                                    System.out.print("\n Elija una de las siguientes opciones:");
                                    System.out.print("\n");
                                    System.out.print("\n 1 - Elegir equipos.");
                                    System.out.print("\n 2 - Agregar al torneo los primeros 16 equipos.");
                                    System.out.print("\n");
                                    System.out.print("\n -1 - Cancelar.");
                                    System.out.print("\n");
                                    OpcionElegida = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion elegida: ", false, true);
                                    if (OpcionElegida == -1){
                                        Continuar = false;
                                        Cancelar = true;
                                        System.out.print("\n Cancelado!");
                                    }
                                    else if (OpcionElegida == 1){
                                        Continuar = false;
                                        int i=0;
                                        while (i<16){
                                            System.out.print("\n Ingrese el Id de alguno de los siguientes equipos para añadirlo en el torneo.");
                                            System.out.print("\n Recuerde que solo apareceran aquellos que esten listos para jugar, y que no haya sido añadido anteriormente.");
                                            System.out.print("\n Ingrese -1 para volver.");
                                            System.out.print("\n Faltan "+(16-NuevoTorneo_Equipos.size())+" equipos por añadir.");
                                            System.out.print("\n ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                            System.out.print("\n |-Deporte-|-|Id de Equipo|-|-------Nombre del Jugador-------|-|------Nombre del Entrenador------|-|Tornes ganados|-|Torneos perdidos|-|Partidos ganador|-|Partidos perdidos|");
                                            for (Equipo_PingPong Equipo_Aux : Equipos_PingPong){
                                                if (!NuevoTorneo_Equipos.contains(Equipo_Aux) ){
                                                    Mostrar_Equipo_PingPong(Equipo_Aux);
                                                }
                                            }
                                            System.out.print("\n ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                                            Id_Buscado = IngresoPorTeclado.IngresarNumero(Teclado, "\n Id del Equipo: ", false, true);
                                            Id_Buscado = RetornarPosicionEquipo_PorId(Id_Buscado, 3);
                                            if (Id_Buscado!=-1){
                                                Equipo_PingPong A_Agregar_Si_Verifica = Equipos_PingPong.get(Id_Buscado);
                                                if (!NuevoTorneo_Equipos.contains(A_Agregar_Si_Verifica)){
                                                    i++;
                                                    NuevoTorneo_Equipos.add(A_Agregar_Si_Verifica);
                                                }
                                                else{
                                                    System.out.print("\n No existe ningun equipo de futbol activo y que no este en la lista con el Id ingresado.");
                                                }
                                            }
                                            else{
                                                System.out.print("\n Cancelado!");

                                                Cancelar = true;
                                                break;
                                            }
                                        }
                                    }
                                    else if (OpcionElegida == 2){
                                        Continuar = false;
                                        for (Equipo_PingPong Equipo_Aux : Equipos_PingPong){
                                            if (NuevoTorneo_Equipos.size()<16){
                                                NuevoTorneo_Equipos.add(Equipo_Aux);
                                            }
                                            else{
                                                break;
                                            }
                                        }
                                    }
                                    else{
                                        System.out.print("\n Elija una de las opciones ofrecidas!");
                                    }
                                }
                                if (!Cancelar){
                                    Torneo_PingPong Nuevo_Torneo = new Torneo_PingPong(NombreTorneo, NuevoTorneo_Equipos, Arbitros);
                                    Torneos_PingPong.add(Nuevo_Torneo);
                                    System.out.println("\n Torneo creado!");
                                }
                            }
                        }

                // ELIMINAR TORNEO
                    private void EliminarTorneo(Scanner Teclado){
                        int IdBuscado;
                        boolean Continuar = true;
                        while (Continuar){
                            System.out.print("\n Ingrese el Id de alguno de los siguientes torneo para eliminarlo.");
                            System.out.print("\n Recuerde que solo puede eliminar un torneo si y solo si no esta en curso.");
                            System.out.print("\n Ingrese -1 para volver al menu anterior.");
                            // MOSTRAR TORNEOS
                            Mostrar_Torneos();
                            IdBuscado = IngresoPorTeclado.IngresarNumero(Teclado, "\n Id: ", true, true);
                            if (IdBuscado!=-1){
                                if (ExisteIdTorneo(IdBuscado)){
                                    Torneo Aux = RetornarTorneo_Id(IdBuscado);
                                    if (SePuedeEliminarTorneo(Aux)){ // ELIMINAR
                                        EliminarTorneoLista(Aux);
                                        Continuar = false;
                                        System.out.print("\n Equipo eliminado!");
                                    }
                                    else{
                                        System.out.print("\n El torneo elegido no se puede eliminar debido a que esta en curso!");
                                        Continuar = false;
                                    }
                                }
                                else{
                                    System.out.print("\n El Id ingresado no es un Id de Torneo valido!");
                                }
                            }
                            else{
                                Continuar = false;
                                System.out.print("\n Cancelado!");
                            }
                        }
                    }

                        // RETORNA SI SE PUEDE ELIMINAR UN TORNEO
                        private boolean SePuedeEliminarTorneo(Torneo Aux){
                            boolean Retornar = true;
                            if (Aux instanceof Torneo_Tennis TennisAux && TennisAux.isActivo()){
                                Retornar = false;
                            }
                            else if (Aux instanceof Torneo_Futbol FutbolAux && FutbolAux.isActivo()){
                                Retornar = false;
                            }
                            else if (Aux instanceof Torneo_PingPong PingPongAux && PingPongAux.isActivo()){
                                Retornar = false;
                            }
                            return Retornar;
                        }

                        // ELIMINAR TORNEO DE LA LISTA
                        private void EliminarTorneoLista(Torneo Aux){
                            if (Aux instanceof Torneo_Futbol FutbolAux){
                                for (Torneo_Futbol TorneoAux : Torneos_Futbol ){
                                    if (TorneoAux.equals(FutbolAux)){
                                        Torneos_Futbol.remove(TorneoAux);
                                        break;
                                    }
                                }
                            }
                            else if (Aux instanceof Torneo_Tennis TennisAux){
                                for (Torneo_Tennis TorneoAux : Torneos_Tennis ){
                                    if (TorneoAux.equals(TennisAux)){
                                        Torneos_Tennis.remove(TorneoAux);
                                        break;
                                    }
                                }
                            }
                            else if (Aux instanceof Torneo_PingPong PingPongAux){
                                for (Torneo_PingPong TorneoAux : Torneos_PingPong ){
                                    if (TorneoAux.equals(PingPongAux)){
                                        Torneos_PingPong.remove(TorneoAux);
                                        break;
                                    }
                                }
                            }
                        }

                // COMENZAR/CONTINUAR TORNEO
                    private void ComenzarContinuar_Torneo(Scanner Teclado){
                        boolean Continar=true;
                        int IdTorneo;
                        boolean MostrarFutbol, MostrarTennis, MostrarPingPong;
                        MostrarFutbol = Torneos_Futbol.size()>0;
                        MostrarTennis = Torneos_Tennis.size()>0;
                        MostrarPingPong = Torneos_PingPong.size()>0;
                        ArrayList<String> Permitidos = new ArrayList<>();
                        Permitidos.add("-2");
                        Permitidos.add("-3");
                        Permitidos.add("-4");
                        while (Continar){
                            System.out.print("\n Ingrese el ID de alguno de lo siguientes torneos para Comenzarlos o Continuarlo.");
                            System.out.print("\n");
                            System.out.print("\n -1 - Volver.");
                            System.out.print("\n -2 - Mostrar/no mostrar los torneos de futbol.");
                            System.out.print("\n -3 - Mostrar/no mostrar los torneos de tennis.");
                            System.out.print("\n -4 - Mostrar/no mostrar los torneos de ping pong.");
                            System.out.print("\n");
                            if (MostrarFutbol){
                                for (Torneo_Futbol Aux : Torneos_Futbol){
                                    System.out.print("\n ---------------------------------------------------------------------------------------------------------------");
                                    System.out.print("\n |-Deporte-|-|Id de Torneo|-|-------Nombre del Torneo-------|-|--------Activo--------|-|--------Ganador--------|");
                                    if (Aux.isActivo()){
                                        Mostrar_Torneo_Futbol(Aux);
                                    }
                                }
                                System.out.print("\n ---------------------------------------------------------------------------------------------------------------");
                            }
                            if (MostrarTennis){
                                if (!MostrarFutbol){
                                    System.out.print("\n ---------------------------------------------------------------------------------------------------------------");
                                }
                                for (Torneo_Tennis Aux : Torneos_Tennis){
                                    System.out.print("\n |-Deporte-|-|Id de Torneo|-|-------Nombre del Torneo-------|-|--------Activo--------|-|--------Ganador--------|");
                                    if (Aux.isActivo()){
                                        Mostrar_Torneo_Tennis(Aux);
                                    }
                                }
                                System.out.print("\n ---------------------------------------------------------------------------------------------------------------");
                            }
                            if (MostrarPingPong){
                                if (!MostrarTennis){
                                    System.out.print("\n ---------------------------------------------------------------------------------------------------------------");
                                }
                                for (Torneo_PingPong Aux : Torneos_PingPong){
                                    System.out.print("\n |-Deporte-|-|Id de Torneo|-|-------Nombre del Torneo-------|-|--------Activo--------|-|--------Ganador--------|");
                                    if (Aux.isActivo()){
                                        Mostrar_Torneo_PingPong(Aux);
                                    }
                                }
                                System.out.print("\n ---------------------------------------------------------------------------------------------------------------");
                            }
                            System.out.print("\n");
                            IdTorneo = IngresoPorTeclado.IngresarNumero(Teclado, "\n Id: ", false, true, Permitidos);
                            if (IdTorneo==-1){
                                Continar = false;
                                System.out.print("\n Cancelado!");
                            }
                            else if (IdTorneo == (-2)){
                                if (Torneos_Futbol.size()>0){
                                    MostrarFutbol = !MostrarFutbol;
                                }
                                else{
                                    System.out.print("\n No hay torneos de Futbol activos para mostrar!");
                                }
                            }
                            else if (IdTorneo == (-3)){
                                if (Torneos_Tennis.size()>0){
                                    MostrarTennis = !MostrarTennis;
                                }
                                else{
                                    System.out.print("\n No hay torneos de Tennis activos para mostrar!");
                                }
                            }
                            else if (IdTorneo == (-4)){
                                if (Torneos_PingPong.size()>0){
                                    MostrarPingPong = !MostrarPingPong;
                                }
                                else{
                                    System.out.print("\n No hay torneos de Ping Pong activos para mostrar!");
                                }
                            }
                            else{
                                if (ExisteIdTorneo(IdTorneo)){
                                    Torneo Aux = RetornarTorneo_Id(IdTorneo);
                                    Aux.MenuTorneo(Teclado);
                                    if (Aux.isActivo()){
                                        Continar = false;
                                        System.out.print("\n Torneo finalizado!");
                                    }
                                }
                                else{
                                    System.out.print("\n El ID ingresado no corresponde a ningun torneo existente.");
                                }
                            }
                        }
                    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // MENU - EQUIPOS
                private void MenuEquipos(Scanner Teclado){
                    boolean Retornar = true;
                    int Swtch;
                    while (Retornar){
                        System.out.print("\n Elija una de las siguientes opciones:");
                        System.out.print("\n");
                        System.out.print("\n 01 - Ver todos los equipos.");
                        System.out.print("\n 02 - Crear equipo.");
                        System.out.print("\n 03 - Eliminar equipo.");
                        System.out.print("\n");
                        System.out.print("\n 00 - Volver.");
                        System.out.print("\n");
                        Swtch = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion elegida: ", false, false);
                        switch (Swtch) {
                            case 1 -> // MENU VER TODOS LOS EQUIPOS
                                    MenuEquipos_MostrarEquipos(Teclado);
                            case 2 -> // CREAR EQUIPO
                                    Crear_Equipo(Teclado);
                            case 3 -> // ELIMINAR EQUIPO
                                    EliminarEquipo(Teclado);
                            case 0 -> // SALIR
                                    Retornar = false;
                            default -> {} // DEFAULT
                        }
                    }
                }

                // MENU MOSTRAR EQUIPOS
                private void MenuEquipos_MostrarEquipos(Scanner Teclado){
                    int Opcion;
                    boolean Continuar = true;
                    while (Continuar){
                        Mostrar_Equipos();
                        System.out.print("\n");
                        System.out.print("\n Ingrese el Id de un equipo para ver todos sus detalles.");
                        System.out.print("\n Ingrese -1 para volver al menu anterior.");
                        Opcion = IngresoPorTeclado.IngresarNumero(Teclado, "\n Id: ", false, true);
                        if (Opcion == -1){
                            Continuar=false;
                        }
                        else{
                            if (ExisteIdEquipos(Opcion)){
                                Equipo Mostrar = RetornarEquipo_Id(Opcion);
                                if (Mostrar instanceof Equipo_Futbol Aux){
                                    Mostrar_Equipo_Futbol_Detallado(Aux);
                                }
                                else if (Mostrar instanceof Equipo_Tennis Aux){
                                    Mostrar_Equipo_Tennis_Detallado(Aux);
                                }
                                else if (Mostrar instanceof Equipo_PingPong Aux){
                                    Mostrar_Equipo_PingPong_Detallado(Aux);
                                }
                                System.out.print("\n ");
                                Teclado.nextLine();
                            }
                        }

                    }
                }

                    // MOSTRAR EQUIPOS
                        private void Mostrar_Equipos(){
                            System.out.print("\n ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n |-Deporte-|-|Id de Equipo|-|-------Nombre del Equipo-------|-|------Nombre del Entrenador------|-|Torneos ganados|-|Torneos perdidos|-|Partidos ganados|-|Partidos perdidos|");
                            for (Equipo_Futbol EquipoAux : Equipos_Futbol) {
                                Mostrar_Equipo_Futbol(EquipoAux);
                            }
                            System.out.print("\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n |-Deporte-|-|Id de Equipo|-|-------Nombre del Jugador-------|-|------Nombre del Entrenador------|-|Torneos ganados|-|Torneos perdidos|-|Partidos ganados|-|Partidos perdidos|");
                            for (Equipo_Tennis EquipoAux : Equipos_Tennis) {
                                Mostrar_Equipo_Tennis(EquipoAux);
                            }
                            System.out.print("\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n |-Deporte-|-|Id de Equipo|-|-------Nombre del Jugador-------|-|------Nombre del Entrenador------|-|Torneos ganados|-|Torneos perdidos|-|Partidos ganados|-|Partidos perdidos|");
                            for (Equipo_PingPong EquipoAux : Equipos_PingPong) {
                                Mostrar_Equipo_PingPong(EquipoAux);
                            }
                            System.out.print("\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    }

                        // MOSTRAR EQUIPO DE FUTBOL
                            private void Mostrar_Equipo_Futbol(Equipo_Futbol Mostrar){
                                System.out.print("\n |   Futbol|");
                                System.out.printf("-|%12d|", Mostrar.getId_Equipo());
                                System.out.printf("-|%31s|", Mostrar.getNombreEquipo());
                                System.out.printf("-|%33s|", Mostrar.getEntrenador().getNombre()+" "+Mostrar.getEntrenador().getApellido());
                                System.out.printf("-|%15s|", Mostrar.getTorneosGanados());
                                System.out.printf("-|%16s|", Mostrar.getTorneosPerdidos());
                                System.out.printf("-|%16s|", Mostrar.getPartidosGanados());
                                System.out.printf("-|%17s|", Mostrar.getPartidosPerdidos());
                            }

                        // MOSTRAR EQUIPO DE TENNIS
                            private void Mostrar_Equipo_Tennis(Equipo_Tennis Mostrar){
                                System.out.print("\n |   Tennis|");
                                System.out.printf("-|%12d|", Mostrar.getId_Equipo());
                                System.out.printf("-|%32s|", Mostrar.getJugador().getNombre()+" "+Mostrar.getJugador().getApellido());
                                System.out.printf("-|%33s|", Mostrar.getEntrenador().getNombre()+" "+Mostrar.getEntrenador().getApellido());
                                System.out.printf("-|%15s|", Mostrar.getTorneosGanados());
                                System.out.printf("-|%16s|", Mostrar.getTorneosPerdidos());
                                System.out.printf("-|%16s|", Mostrar.getPartidosGanados());
                                System.out.printf("-|%17s|", Mostrar.getPartidosPerdidos());
                            }

                        // MOSTRAR EQUIPO DE PING PONG
                            private void Mostrar_Equipo_PingPong(Equipo_PingPong Mostrar){
                                System.out.print("\n |Ping Pong|");
                                System.out.printf("-|%12d|", Mostrar.getId_Equipo());
                                System.out.printf("-|%32s|", Mostrar.getJugador().getNombre()+" "+Mostrar.getJugador().getApellido());
                                System.out.printf("-|%33s|", Mostrar.getEntrenador().getNombre()+" "+Mostrar.getEntrenador().getApellido());
                                System.out.printf("-|%15s|", Mostrar.getTorneosGanados());
                                System.out.printf("-|%16s|", Mostrar.getTorneosPerdidos());
                                System.out.printf("-|%16s|", Mostrar.getPartidosGanados());
                                System.out.printf("-|%17s|", Mostrar.getPartidosPerdidos());
                            }

                    // MOSTRAR EQUIPO DE FUTBOL DETALLADO
                        private void Mostrar_Equipo_Futbol_Detallado(Equipo_Futbol Mostrar){
                            System.out.print("\n ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n");
                            System.out.print("\n Nombre: "+Mostrar.getNombreEquipo());
                            System.out.print("\n");
                            System.out.print("\n Torneos Ganados: "+Mostrar.getTorneosGanados());
                            System.out.print("\n Torneos Perdidos: "+Mostrar.getTorneosPerdidos());
                            System.out.print("\n Partidos Ganados: "+Mostrar.getPartidosGanados());
                            System.out.print("\n Partidos Perdidos: "+Mostrar.getPartidosPerdidos());
                            System.out.print("\n");
                            System.out.print("\n Entrenador:");
                            System.out.print("\n");
                            System.out.print("\n Dni: "+Mostrar.getEntrenador().getDni());
                            System.out.print("\n Nombre: "+Mostrar.getEntrenador().getNombre());
                            System.out.print("\n Apellido: "+Mostrar.getEntrenador().getApellido());
                            System.out.print("\n Fecha de Nacimiento: "+Mostrar.getEntrenador().getFecha_Nacimiento().toString());
                            System.out.print("\n Nacionalidad: "+Mostrar.getEntrenador().getNacionalidad());
                            System.out.print("\n");
                            System.out.print("\n Jugadores:");
                            System.out.print("\n ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n |----Dni----|-|----Nombre----|-|----Apellido----|-|Fecha de Nacimiento|-|Nacionalidad|-|Posicion de Juego|-|Torneos Ganados|-|Torneos Perdidos|-|Partidos Ganados|-|Partidos Perdidos|-|Goles Hechos|-|Amarillas recibidas|-|Rojas recibidas|");
                            for (Jugador_Futbol Aux : Mostrar.getJugadores()){
                                System.out.printf("\n |%11s|", Aux.getDni());
                                System.out.printf("-|%14s|", Aux.getNombre());
                                System.out.printf("-|%16s|", Aux.getApellido());
                                System.out.printf("-|%19s|", Aux.getFecha_Nacimiento().toString());
                                System.out.printf("-|%12s|", Aux.getNacionalidad());
                                System.out.printf("-|%17s|", Aux.getPosicion_Juego());
                                System.out.printf("-|%15s|", Aux.getTorneosGanados());
                                System.out.printf("-|%16s|", Aux.getTorneosPerdidos());
                                System.out.printf("-|%16s|", Aux.getPartidosGanados());
                                System.out.printf("-|%17s|", Aux.getPartidosPerdidos());
                                System.out.printf("-|%12s|", Aux.getPuntoGanados_Goles());
                                System.out.printf("-|%19s|", Aux.getAmarillasRecibidas());
                                System.out.printf("-|%15s|", Aux.getRojasRecibidas());
                            }
                            System.out.print("\n ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        }

                    // MOSTRAR EQUIPO DE TENNIS DETALLADO
                        private void Mostrar_Equipo_Tennis_Detallado(Equipo_Tennis Mostrar){
                            System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n");
                            System.out.print("\n Entrenador:");
                            System.out.print("\n");
                            System.out.print("\n Dni: "+Mostrar.getEntrenador().getDni());
                            System.out.print("\n Nombre: "+Mostrar.getEntrenador().getNombre());
                            System.out.print("\n Apellido: "+Mostrar.getEntrenador().getApellido());
                            System.out.print("\n Fecha de Nacimiento: "+Mostrar.getEntrenador().getFecha_Nacimiento().toString());
                            System.out.print("\n Nacionalidad: "+Mostrar.getEntrenador().getNacionalidad());
                            System.out.print("\n");
                            System.out.print("\n Torneos Ganados: "+Mostrar.getTorneosGanados());
                            System.out.print("\n Torneos Perdidos: "+Mostrar.getTorneosPerdidos());
                            System.out.print("\n Partidos Ganados: "+Mostrar.getPartidosGanados());
                            System.out.print("\n Partidos Perdidos: "+Mostrar.getPartidosPerdidos());
                            System.out.print("\n");
                            System.out.print("\n Jugadores:");
                            System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n |----Dni----|-|----Nombre----|-|----Apellido----|-|Fecha de Nacimiento|-|Nacionalidad|-|------Torneos------|-|-----Partidos-----|-|-------Sets-------|-|-------Games-------|-|------Puntos------|-|-------Aces-------|-|Dobles Faltas|");
                            System.out.print("\n |           |-|              |-|                |-|                   |-|            |-|Ganados|--|Perdidos|-|Ganados|-|Perdidos|-|Ganados|-|Perdidos|-|Ganados|--|Perdidos|-|Ganados|-|Perdidos|-|Hechos|-|Recibidos|-|             |");
                            System.out.printf("\n |%11s|", Mostrar.getJugador().getDni());
                            System.out.printf("-|%14s|", Mostrar.getJugador().getNombre());
                            System.out.printf("-|%16s|", Mostrar.getJugador().getApellido());
                            System.out.printf("-|%19s|", Mostrar.getJugador().getFecha_Nacimiento().toString());
                            System.out.printf("-|%12s|", Mostrar.getJugador().getNacionalidad());
                            System.out.printf("-|%7s|", Mostrar.getJugador().getTorneosGanados());
                            System.out.printf("--|%8s|", Mostrar.getJugador().getTorneosPerdidos());
                            System.out.printf("-|%7s|", Mostrar.getJugador().getPartidosGanados());
                            System.out.printf("-|%8s|", Mostrar.getJugador().getPartidosPerdidos());
                            System.out.printf("-|%7s|", Mostrar.getJugador().getSetsGanados());
                            System.out.printf("-|%8s|", Mostrar.getJugador().getSetsPerdidos());
                            System.out.printf("-|%7s|", Mostrar.getJugador().getGamesGanados());
                            System.out.printf("--|%8s|", Mostrar.getJugador().getGamesPerdidos());
                            System.out.printf("-|%7s|", Mostrar.getJugador().getPuntoGanados_Goles());
                            System.out.printf("-|%8s|", Mostrar.getJugador().getPuntosPerdidos());
                            System.out.printf("-|%6s|", Mostrar.getJugador().getAces());
                            System.out.printf("-|%9s|", Mostrar.getJugador().getSaqueNoRecibido());
                            System.out.printf("-|%13s|", Mostrar.getJugador().getDobleFalta());
                            System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        }

                    // MOSTRAR EQUIPO DE PING PONG DETALLADO
                        private void Mostrar_Equipo_PingPong_Detallado(Equipo_PingPong Mostrar){
                            System.out.print("\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n");
                            System.out.print("\n Entrenador:");
                            System.out.print("\n");
                            System.out.print("\n Dni: "+Mostrar.getEntrenador().getDni());
                            System.out.print("\n Nombre: "+Mostrar.getEntrenador().getNombre());
                            System.out.print("\n Apellido: "+Mostrar.getEntrenador().getApellido());
                            System.out.print("\n Fecha de Nacimiento: "+Mostrar.getEntrenador().getFecha_Nacimiento().toString());
                            System.out.print("\n Nacionalidad: "+Mostrar.getEntrenador().getNacionalidad());
                            System.out.print("\n");
                            System.out.print("\n Torneos Ganados: "+Mostrar.getTorneosGanados());
                            System.out.print("\n Torneos Perdidos: "+Mostrar.getTorneosPerdidos());
                            System.out.print("\n Partidos Ganados: "+Mostrar.getPartidosGanados());
                            System.out.print("\n Partidos Perdidos: "+Mostrar.getPartidosPerdidos());
                            System.out.print("\n");
                            System.out.print("\n Jugadores:");
                            System.out.print("\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n |----Dni----|-|----Nombre----|-|----Apellido----|-|Fecha de Nacimiento|-|Nacionalidad|-|------Torneos------|-|-----Partidos-----|-|-------Sets-------|-|------Puntos------|-|-------Aces-------|-|Saques Errados|");
                            System.out.print("\n |           |-|              |-|                |-|                   |-|            |-|Ganados|--|Perdidos|-|Ganados|-|Perdidos|-|Ganados|-|Perdidos|-|Ganados|-|Perdidos|-|Hechos|-|Recibidos|-|              |");
                            System.out.printf("\n |%11s|", Mostrar.getJugador().getDni());
                            System.out.printf("-|%14s|", Mostrar.getJugador().getNombre());
                            System.out.printf("-|%16s|", Mostrar.getJugador().getApellido());
                            System.out.printf("-|%19s|", Mostrar.getJugador().getFecha_Nacimiento().toString());
                            System.out.printf("-|%12s|", Mostrar.getJugador().getNacionalidad());
                            System.out.printf("-|%7d|", Mostrar.getJugador().getTorneosGanados());
                            System.out.printf("--|%8d|", Mostrar.getJugador().getTorneosPerdidos());
                            System.out.printf("-|%7d|", Mostrar.getJugador().getPartidosGanados());
                            System.out.printf("-|%8d|", Mostrar.getJugador().getPartidosPerdidos());
                            System.out.printf("-|%7d|", Mostrar.getJugador().getSetsGanados());
                            System.out.printf("-|%8d|", Mostrar.getJugador().getSetsPerdidos());
                            System.out.printf("-|%7d|", Mostrar.getJugador().getPuntoGanados_Goles());
                            System.out.printf("-|%8d|", Mostrar.getJugador().getPuntosPerdidos());
                            System.out.printf("-|%6d|", Mostrar.getJugador().getAces());
                            System.out.printf("-|%9d|", Mostrar.getJugador().getSaqueNoRecibido());
                            System.out.printf("-|%14d|", Mostrar.getJugador().getSaqueErrado());
                            System.out.print("\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        }

                // CREAR EQUIPO
                    public void Crear_Equipo(Scanner Teclado){
                        boolean Retornar = true;
                        int Swtch;
                        while (Retornar){
                            System.out.print("\n Elija una de las siguientes opciones:");
                            System.out.print("\n");
                            System.out.print("\n 01 - Futbol.");
                            System.out.print("\n 02 - Tennis.");
                            System.out.print("\n 03 - Ping Pong.");
                            System.out.print("\n");
                            System.out.print("\n 00 - Volver.");
                            System.out.print("\n");
                            Swtch = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion elegida: ", false, false);
                            switch (Swtch) {
                                case 1 -> { // FUTBOL
                                    Crear_Equipo_Futbol(Teclado);
                                    Retornar = false;
                                }
                                case 2 -> { // TENNIS
                                    Crear_Equipo_Tennis(Teclado);
                                    Retornar = false;
                                }
                                case 3 -> { // PING PONG
                                    Crear_Equipo_PingPong(Teclado);
                                    Retornar = false;
                                }
                                case 0 -> // SALIR
                                        Retornar = false;
                                default -> {
                                } // DEFAULT
                            }
                        }
                    }

                    // FUTBOL
                        private void Crear_Equipo_Futbol(Scanner Teclado){
                            String Nombre = "";
                            boolean Continuar = true;

                            Persona Persona_Aux;

                            System.out.println("\n A continuacion, le pediremos informacion del nuevo equipo.");

                            while (Continuar){
                                Nombre = IngresoPorTeclado.IngresarPalabra(Teclado, "\n Nombre del Equipo: ", true, false);
                                if (ExisteNombreEquipo(Nombre)){
                                    System.out.print("\n No debe ingresar el nombre de un equipo que ya existe.");
                                }
                                else{
                                    Continuar = false;
                                }
                            }

                            System.out.println("\n A continuacion, le pediremos informacion del nuevo entrenador.");
                            Persona_Aux = Crear_Persona(Teclado);

                            Equipo_Futbol Nuevo_Equipo = new Equipo_Futbol(Nombre, new Tecnico_Entrenador(Persona_Aux));
                            Equipos_Futbol.add(Nuevo_Equipo);

                            System.out.println("\n A continuacion, le pediremos informacion de los nuevos jugadores.");
                            for (int i=0 ; i<11 ; i++){
                                System.out.println("Ingrese la informacion del jugador n°"+(i+1)+".");
                                Persona_Aux = Crear_Persona(Teclado);
                                Continuar = true;
                                while (Continuar){
                                    System.out.print("\n Ingrese alguna de las siguientes posiciones:");
                                    System.out.print("\n -'Arquero'");
                                    System.out.print("\n -'Central'");
                                    System.out.print("\n -'Lateral'");
                                    System.out.print("\n -'Mediocampista'");
                                    System.out.print("\n -'Delantero'");
                                    System.out.print("\n");
                                    Nombre = IngresoPorTeclado.IngresarPalabra(Teclado, "\n Posicion: ", true, false);
                                    if (EsPosicionJuegoValido(Nombre)){
                                        Continuar = false;
                                    }
                                    else{
                                        System.out.print("\n Ingrese una posicion de juego valida por favor!");
                                    }
                                }
                                Equipos_Futbol.get(Equipos_Futbol.size()-1).AgregarJugador(new Jugador_Futbol(Persona_Aux, Nombre));
                            }

                            System.out.print("\n Equipo de Futbol creado!");

                        }

                    // TENNIS
                        private void Crear_Equipo_Tennis(Scanner Teclado){

                            Persona Persona1, Persona2;

                            System.out.print("\n A continuacion, ingrese la informacion del Entrenador.");
                            Persona2 = Crear_Persona(Teclado);

                            Equipo_Tennis Nuevo_Equipo = new Equipo_Tennis();
                            Equipos_Tennis.add(Nuevo_Equipo);

                            Equipos_Tennis.get(Equipos_Tennis.size()-1).Agregar_Tecnico(new Tecnico_Entrenador(Persona2));

                            System.out.print("\n A continuacion, ingrese la informacion del Jugador.");
                            Persona1 = Crear_Persona(Teclado);

                            Equipos_Tennis.get(Equipos_Tennis.size()-1).AgregarJugador(new Jugador_Tennis(Persona1));

                            System.out.print("\n Equipo de Tennis creado!");

                        }

                    // PING PONG
                        private void Crear_Equipo_PingPong(Scanner Teclado){

                            Persona Persona1, Persona2;

                            System.out.print("\n A continuacion, ingrese la informacion del Entrenador..");
                            Persona2 = Crear_Persona(Teclado);

                            Equipo_PingPong Nuevo_Equipo = new Equipo_PingPong();
                            Equipos_PingPong.add(Nuevo_Equipo);

                            Equipos_PingPong.get(Equipos_PingPong.size()-1).Agregar_Tecnico(new Tecnico_Entrenador(Persona2));

                            System.out.print("\n A continuacion, ingrese la informacion del Jugador.");
                            Persona1 = Crear_Persona(Teclado);

                            Equipos_PingPong.get(Equipos_PingPong.size()-1).AgregarJugador(new Jugador_PingPong(Persona1));

                            System.out.print("\n Equipo de Ping Pong creado!");

                        }

                // ELIMINAR EQUIPO
                    private void EliminarEquipo(Scanner Teclado){
                        int IdBuscado;
                        boolean Continuar = true;
                        while (Continuar){
                            System.out.print("\n Ingrese el Id de alguno de los siguientes equipos para eliminarlo.");
                            System.out.print("\n Recuerde que solo puede eliminar un equipo si y solo si no esta inscripto en ningun torneo en curso.");
                            System.out.print("\n Ingrese -1 para volver al menu anterior.");
                            // MOSTRAR EQUIPOS
                            System.out.print("\n ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n |-Deporte-|-|Id de Equipo|-|-------Nombre del Equipo-------|-|------Nombre del Entrenador------|-|Tornes ganados|-|Torneos perdidos|-|Partidos ganador|-|Partidos perdidos|");
                            for (Equipo_Futbol EquipoAux : Equipos_Futbol){
                                Mostrar_Equipo_Futbol(EquipoAux);
                            }
                            System.out.print("\n ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n |-Deporte-|-|Id de Equipo|-|-------Nombre del Jugador-------|-|------Nombre del Entrenador------|-|Tornes ganados|-|Torneos perdidos|-|Partidos ganador|-|Partidos perdidos|");
                            for (Equipo_Tennis EquipoAux : Equipos_Tennis){
                                Mostrar_Equipo_Tennis(EquipoAux);
                            }
                            System.out.print("\n ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n |-Deporte-|-|Id de Equipo|-|-------Nombre del Jugador-------|-|------Nombre del Entrenador------|-|Tornes ganados|-|Torneos perdidos|-|Partidos ganador|-|Partidos perdidos|");
                            for (Equipo_PingPong EquipoAux : Equipos_PingPong){
                                Mostrar_Equipo_PingPong(EquipoAux);
                            }
                            System.out.print("\n ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n");
                            IdBuscado = IngresoPorTeclado.IngresarNumero(Teclado, "\n Id : ", true, true);
                            if (IdBuscado!=-1){
                                if (ExisteIdEquipos(IdBuscado)){
                                    Equipo Aux = RetornarEquipo_Id(IdBuscado);
                                    if (SePuedeEliminarEquipo(Aux)){
                                        // ELIMINAR
                                        EliminarEquipoLista(Aux);
                                        Continuar = false;
                                        System.out.print("\n Equipo eliminado!");
                                    }
                                    else{
                                        System.out.print("\n El equipo elegido no se puede eliminar debido a que esta inscripto en un torneo en curso!");
                                    }
                                    System.out.print("\n ");
                                    Teclado.nextLine();
                                }
                                else{
                                    System.out.print("\n El Id ingresado no es un Id de Equipo valido!");
                                }
                                System.out.print("\n ");
                                Teclado.nextLine();
                            }
                            else{
                                Continuar = false;
                            }


                        }
                    }

                    // SE PUEDE ELIMINAR UN EQUIPO
                        private boolean SePuedeEliminarEquipo(Equipo Aux){
                            boolean Retornar = true;
                            if (Aux instanceof Equipo_Futbol FutbolAux){
                                for (Torneo_Futbol TorneoAux : Torneos_Futbol ){
                                    if (TorneoAux.isActivo()){
                                        if (TorneoAux.ExisteEquipo(FutbolAux)){
                                            Retornar = false;
                                            break;
                                        }
                                    }
                                }
                            }
                            else if (Aux instanceof Equipo_Tennis TennisAux){
                                for (Torneo_Tennis TorneoAux : Torneos_Tennis ){
                                    if (TorneoAux.isActivo()){
                                        if (TorneoAux.ExisteEquipo(TennisAux)){
                                            Retornar = false;
                                            break;
                                        }
                                    }
                                }
                            }
                            else if (Aux instanceof Equipo_PingPong PingPongAux){
                                for (Torneo_PingPong TorneoAux : Torneos_PingPong ){
                                    if (TorneoAux.isActivo()){
                                        if (TorneoAux.ExisteEquipo(PingPongAux)){
                                            Retornar = false;
                                            break;
                                        }
                                    }
                                }
                            }
                            return Retornar;
                        }

                    // ELIMINAR EQUIPO
                        private void EliminarEquipoLista(Equipo Aux){
                            if (Aux instanceof Equipo_Futbol FutbolAux){
                                for (Equipo_Futbol EquipoAux : Equipos_Futbol ){
                                    if (EquipoAux.equals(FutbolAux)){
                                        Equipos_Futbol.remove(EquipoAux);
                                        break;
                                    }
                                }
                            }
                            else if (Aux instanceof Equipo_Tennis TennisAux){
                                for (Equipo_Tennis EquipoAux : Equipos_Tennis ){
                                    if (EquipoAux.equals(TennisAux)){
                                        Equipos_Tennis.remove(EquipoAux);
                                        break;
                                    }
                                }
                            }
                            else if (Aux instanceof Equipo_PingPong PingPongAux){
                                for (Equipo_PingPong EquipoAux : Equipos_PingPong ){
                                    if (EquipoAux.equals(PingPongAux)){
                                        Equipos_PingPong.remove(EquipoAux);
                                        break;
                                    }
                                }
                            }
                        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // MENU - ARBITROS
                private void MenuArbitros(Scanner Teclado){
                    boolean Retornar = true;
                    int Swtch;
                    while (Retornar){
                        System.out.print("\n Elija una de las siguientes opciones:");
                        System.out.print("\n");
                        System.out.print("\n 01 - Ver todos los arbitros.");
                        System.out.print("\n 02 - Agregar arbitro.");
                        System.out.print("\n 03 - Eliminar arbitro.");
                        System.out.print("\n");
                        System.out.print("\n 00 - Volver.");
                        System.out.print("\n");
                        Swtch = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion elegida: ", false, false);
                        switch (Swtch) {
                            case 1 -> {// MENU VER TODOS LOS ARBITROS
                                Mostrar_Arbitros();
                                System.out.print("\n ");
                                Teclado.nextLine();
                            }
                            case 2 -> // AGREGAR ARBITRO
                                    AgregarArbitro(Teclado);
                            case 3 -> // ELIMINAR ARBITRO
                                    EliminarArbitro(Teclado);
                            case 0 -> // SALIR
                                    Retornar = false;
                            default -> {
                            } // DEFAULT
                        }
                    }
                }

                // MOSTRAR ARBITROS
                    private void Mostrar_Arbitros(){
                        System.out.print("\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.print("\n |Documento Nacional de Identidad|-|-------Nombre-------|-|------Apellido------|-|----Nacionalidad----|-|Fecha de nacimiento|-|Partidos Arbitrados|-|Torneos Arbitrados|");
                        for (Arbitro Arbitro_Aux : Arbitros){
                            Mostrar_Arbitro(Arbitro_Aux);
                        }
                        System.out.print("\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    }

                    // MOSTRAR ARBITRO
                        private void Mostrar_Arbitro(Arbitro Mostrar){
                            System.out.printf("\n |%31s|", Mostrar.getDni());
                            System.out.printf("-|%20s|", Mostrar.getNombre());
                            System.out.printf("-|%20s|", Mostrar.getApellido());
                            System.out.printf("-|%20s|", Mostrar.getNacionalidad());
                            System.out.printf("-|%19s|", Mostrar.getFecha_Nacimiento().toString());
                            System.out.printf("-|%19s|", Mostrar.getPartidosArbitrados());
                            System.out.printf("-|%18s|", Mostrar.getTorneosArbitrados());
                        }

                // CREAR ARBITRO
                    private void AgregarArbitro(Scanner Teclado){
                        Persona NuevaPersona = Crear_Persona(Teclado);
                        Arbitro NuevoArbitro = new Arbitro(NuevaPersona);
                        Arbitros.add(NuevoArbitro);

                        System.out.print("\n Arbitro agregado exitosamente!");

                    }

                // ELIMINAR ARBITRO
                    private void EliminarArbitro(Scanner Teclado){
                        String DniBuscado;
                        boolean Continuar = true;
                        while (Continuar){
                            System.out.print("\n Ingrese alguno el DNI del arbitro que desea eliminar.");
                            System.out.print("\n Ingrese '-1' para volver al menu anterior.");
                            System.out.print("\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n |Documento Nacional de Identidad|-|-------Nombre-------|-|------Apellido------|-|----Nacionalidad----|-|Fecha de nacimiento|-|Partidos Arbitrados|-|Torneos Arbitrados|");
                            for (Arbitro Arbitro_Aux : Arbitros){
                                Mostrar_Arbitro(Arbitro_Aux);
                            }
                            System.out.print("\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            DniBuscado = IngresoPorTeclado.IngresarPalabraAlfanumerica(Teclado, "\n Dni: ", true, true);
                            if (!DniBuscado.equals("-1")){
                                if (ExisteDni_Arbitro(DniBuscado)){
                                    int Posicion = RetornarPosicionArbitro(DniBuscado);
                                    if (SePuedeEliminarArbitro(Arbitros.get(Posicion))){
                                        Arbitros.remove(Posicion);
                                        System.out.print("\n Arbitro eliminado!");
                                        Continuar = false;
                                    }
                                    else{
                                        System.out.print("\n No se puede eliminar el Arbitro!");
                                        System.out.print("\n Debido a que este esta inscripto en un Torneo en Curso!");
                                        System.out.print("\n Ingrese otro Dni, elimine el torneo, o finalicelo!");
                                    }
                                }
                                else{
                                    System.out.print("\n No existe ningun arbitro con el Dni ingresado! Ingrese otro.");
                                }
                            }
                            else{
                                Continuar = false;
                            }
                        }
                    }

                    // SE PUEDE ELIMINAR UN ARBITRO
                    private boolean SePuedeEliminarArbitro(Arbitro Aux){
                        boolean Retornar = true;
                        for (Torneo_Futbol TorneoAux : Torneos_Futbol){
                            if (TorneoAux.isActivo() && TorneoAux.ExisteArbitro(Aux)){
                                Retornar = false;
                                break;
                            }
                        }
                        if (Retornar){
                            for (Torneo_Tennis TorneoAux : Torneos_Tennis){
                                if (TorneoAux.isActivo() && TorneoAux.ExisteArbitro(Aux)){
                                    Retornar = false;
                                    break;
                                }
                            }
                        }
                        if (Retornar){
                            for (Torneo_PingPong TorneoAux : Torneos_PingPong){
                                if (TorneoAux.isActivo() && TorneoAux.ExisteArbitro(Aux)){
                                    Retornar = false;
                                    break;
                                }
                            }
                        }
                        return Retornar;
                    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // MENU EXPORTAR
        private void ExportarDatos(Scanner Teclado){
            boolean Continuar = true;
            String Ruta="src", Nombre="Cambiame";
            int Swtch;
            while (Continuar) {
                System.out.print("\n Elija una de las siguientes opciones.");
                System.out.printf("\n Ruta: '%s'", Ruta);
                System.out.printf("\n Nombre: '%s'", Nombre);
                System.out.print("\n");
                System.out.print("\n 01 - Cambiar ruta.");
                System.out.print("\n 02 - Cambiar nombre.");
                System.out.print("\n");
                System.out.print("\n 03 - Exportar Arbitros.");
                System.out.print("\n");
                System.out.print("\n 04 - Exportar Equipo de Futbol.");
                System.out.print("\n 05 - Exportar Equipo de Tennis.");
                System.out.print("\n 06 - Exportar Equipo de Ping Pong.");
                System.out.print("\n");
                System.out.print("\n 07 - Exportar Torneos de Futbol.");
                System.out.print("\n 08 - Exportar Torneos de Tennis.");
                System.out.print("\n 09 - Exportar Torneos de Ping Pong.");
                System.out.print("\n");
                System.out.print("\n 00 - Volver.");
                System.out.print("\n");
                Swtch = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion elegida: ", false, false);
                switch (Swtch) {
                    case 1 ->
                            Ruta = IngresarYVerificarRuta(Teclado, Ruta);
                    case 2 -> {
                        System.out.print("\n A continuacion, ingrese el nombre del archivo sin extension. Solo caracteres alfabeticos.");
                        System.out.print("\n Tenga en cuenta que si escribe el nombre de un archivo existente, este sera sobreescrito.");
                        Nombre = IngresoPorTeclado.IngresarPalabra(Teclado, "\n Nombre sin extension: ", true, false);
                    }
                    case 3 -> {
                        Exportador.ExportarArbitros(Ruta, Nombre, Arbitros);
                        Teclado.nextLine();
                        Continuar = false;
                    }
                    case 4 -> {
                        Exportador.ExportarEquiposFutbol(Ruta, Nombre, Equipos_Futbol);
                        Teclado.nextLine();
                        Continuar = false;
                    }
                    case 5 -> {
                        Exportador.ExportarEquiposTennis(Ruta, Nombre, Equipos_Tennis);
                        Teclado.nextLine();
                        Continuar = false;
                    }
                    case 6 -> {
                        Exportador.ExportarEquiposPingPong(Ruta, Nombre, Equipos_PingPong);
                        System.out.println("\n Exportado!");
                        Continuar = false;
                    }
                    case 7 -> {
                        Exportador.ExportarTorneosFutbol(Ruta, Nombre, Torneos_Futbol);
                        Teclado.nextLine();
                        Continuar = false;
                    }
                    case 8 -> {
                        Exportador.ExportarTorneosTennis(Ruta, Nombre, Torneos_Tennis);
                        Teclado.nextLine();
                        Continuar = false;
                    }
                    case 9 -> {
                        Exportador.ExportarTorneosPingPong(Ruta, Nombre, Torneos_PingPong);
                        System.out.println("\n ");
                        Teclado.nextLine();
                        Continuar = false;
                    }
                    case 0 -> // SALIR
                            Continuar = false;
                    default -> {} // DEFAULT
                }
                if (!Continuar){
                    System.out.println("\n Exportado!");
                    System.out.println("\n ");
                    Teclado.nextLine();
                }
            }
        }

        // INGRESAR RUTA DE ARCHIVO Y VERIFICAR QUE EXISTA
            private String IngresarYVerificarRuta(Scanner Teclado, String _Ruta){
                String Ruta="";
                boolean Continuar = true;
                while (Continuar){
                    System.out.print("\n A continuacion, ingrese la ruta donde desea exportar el archivo.");
                    System.out.print("\n Ingrese -1 para volver al menu anterior.");
                    Ruta = IngresoPorTeclado.IngresarPalabra(Teclado, "\n Ruta: ", true, true);
                    if (!Ruta.equals("-1")){
                        if (new File(Ruta).exists() && new File(Ruta).isDirectory()){
                            Continuar = false;
                        }
                        else{
                            System.out.println("\n Por favor, ingrese una ruta valida a una carpeta existente.");
                        }
                    }
                    else{
                        Ruta = _Ruta;
                    }
                }
                return Ruta;
            }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // VERIFICADORES

        // ES POSICION DE JUEGO VALIDA
            private boolean EsPosicionJuegoValido(String Verificar){
                boolean Retornar = false;
                for (Posiciones_Futbol Aux : Posiciones_Futbol.values()){
                    if (Aux.toString().equalsIgnoreCase(Verificar)){
                        Retornar = true;
                        break;
                    }
                }
                return Retornar;
            }

        // EXISTE DNI
            public boolean ExisteDni(String _Dni){
                boolean Retornar = false;
                if (ExisteDni_Arbitro(_Dni)){
                    Retornar = true;
                }
                else{
                    for (Equipo_Futbol EquipoAux : Equipos_Futbol){
                        if (EquipoAux.getEntrenador().getDni().equals(_Dni)){
                            Retornar = true;
                        }else{
                            for (Jugador_Futbol JugadorAux : EquipoAux.getJugadores()){
                                if (JugadorAux.getDni().equals(_Dni)){
                                    Retornar = true;
                                    break;
                                }
                            }
                        }
                        if (Retornar){
                            break;
                        }
                    }
                    if (!Retornar){
                        for (Equipo_PingPong EquipoAux : Equipos_PingPong){
                            if (EquipoAux.getEntrenador().getDni().equals(_Dni)){
                                Retornar = true;
                                break;
                            }
                            else {
                                if (EquipoAux.EstaJugadorCargado() && EquipoAux.getJugador().getDni().equals(_Dni)){
                                    Retornar = true;
                                    break;
                                }
                            }
                        }
                        if (!Retornar){
                            for (Equipo_Tennis EquipoAux : Equipos_Tennis){
                                if (EquipoAux.getEntrenador().getDni().equals(_Dni)){
                                    Retornar = true;
                                    break;
                                }
                                else {
                                    if (EquipoAux.EstaJugadorCargado() && EquipoAux.getJugador().getDni().equals(_Dni)){
                                        Retornar = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                return Retornar;
            }

        // EXISTE ID EN EQUIPOS
            private boolean ExisteIdEquipos(int IdBuscado){
                boolean Retornar = false;
                for (Equipo_Futbol Aux : Equipos_Futbol){
                    if (Aux.getId_Equipo()==IdBuscado){
                        Retornar = true;
                        break;
                    }
                }
                if (!Retornar){
                    for (Equipo_Tennis Aux : Equipos_Tennis){
                        if (Aux.getId_Equipo()==IdBuscado){
                            Retornar = true;
                            break;
                        }
                    }
                }
                if (!Retornar){
                    for (Equipo_PingPong Aux : Equipos_PingPong){
                        if (Aux.getId_Equipo()==IdBuscado){
                            Retornar = true;
                            break;
                        }
                    }
                }
                return Retornar;
            }

        // EXISTE ID EN TORNEOS
            private boolean ExisteIdTorneo(int IdBuscado){
                boolean Retornar = false;
                for (Torneo_Futbol Aux : Torneos_Futbol){
                    if (Aux.getId()==IdBuscado){
                        Retornar = true;
                        break;
                    }
                }
                if (!Retornar){
                    for (Torneo_Tennis Aux : Torneos_Tennis){
                        if (Aux.getId()==IdBuscado){
                            Retornar = true;
                            break;
                        }
                    }
                }
                if (!Retornar){
                    for (Torneo_PingPong Aux : Torneos_PingPong){
                        if (Aux.getId()==IdBuscado){
                            Retornar = true;
                            break;
                        }
                    }
                }
                return Retornar;
            }

        // EXISTE DNI DE ARBITRO
            private boolean ExisteDni_Arbitro(String _DniBuscado){
                boolean Retornar = false;
                for (Arbitro Aux : Arbitros){
                    if (Aux.getDni().equals(_DniBuscado)){
                        Retornar = true;
                        break;
                    }
                }
                return Retornar;
            }

        // EXISTE EL NOMBRE DEL TORNEO
            private boolean ExisteTorneo_Nombre(String _NombreBuscado){
                boolean Retornar = false;
                for (Torneo_Futbol TorneoAux : Torneos_Futbol){
                    if (TorneoAux.getNombre().equalsIgnoreCase(_NombreBuscado)){
                        Retornar = true;
                        break;
                    }
                }
                if (!Retornar){
                    for (Torneo_Tennis TorneoAux : Torneos_Tennis){
                        if (TorneoAux.getNombre().equalsIgnoreCase(_NombreBuscado)){
                            Retornar = true;
                            break;
                        }
                    }
                }
                if (!Retornar){
                    for (Torneo_PingPong TorneoAux : Torneos_PingPong){
                        if (TorneoAux.getNombre().equalsIgnoreCase(_NombreBuscado)){
                            Retornar = true;
                            break;
                        }
                    }
                }
                return Retornar;
            }

        // EXISTEN TORNEOS PARA MOSTRAR
            private boolean ExistenTorneosParaMostrar(){
                boolean Retornar = false;
                if (Torneos_Futbol.size()!=0){
                    Retornar = true;
                }
                else if (Torneos_Tennis.size()!=0){
                    Retornar = true;
                }
                else if (Torneos_PingPong.size()!=0){
                    Retornar = true;
                }
                return Retornar;
            }

        // EXISTEN TORNEOS PARA CONTINUAR/COMENZAR
            private boolean ExistenTorneosParaContinuar(){
                boolean Retornar = false;
                for (Torneo_Futbol Aux : Torneos_Futbol){
                    if (Aux.isActivo()){
                        Retornar = true;
                        break;
                    }
                }
                if (!Retornar){
                    for (Torneo_Tennis Aux : Torneos_Tennis){
                        if (Aux.isActivo()){
                            Retornar = true;
                            break;
                        }
                    }
                }
                if (!Retornar){
                    for (Torneo_PingPong Aux : Torneos_PingPong){
                        if (Aux.isActivo()){
                            Retornar = true;
                            break;
                        }
                    }
                }
                return Retornar;
            }

        // EXISTE EL NOMBRE DEL EQUIPO
            private boolean ExisteNombreEquipo(String _NombreBuscado){
                boolean Retornar=false;
                for (Equipo_Futbol EquipoAux : Equipos_Futbol){
                    if (EquipoAux.getNombreEquipo().equalsIgnoreCase(_NombreBuscado)){
                        Retornar = true;
                        break;
                    }
                }
                return Retornar;
            }

        // EXISTE ID PARA EQUIPO
            private int RetornarPosicionEquipo_PorId(int _IdBuscado, int TipoBuscado){
                int Retornar = -1; // SI NO SE ENCUENTRA RETORNA -1
                if (TipoBuscado == 1){ // FUTBOL
                    for (Equipo_Futbol Aux : Equipos_Futbol){
                        if (Aux.getId_Equipo()==_IdBuscado){
                            Retornar = Equipos_Futbol.indexOf(Aux);
                            break;
                        }
                    }
                }
                else if (TipoBuscado == 2){ // TENNIS
                    for (Equipo_Tennis Aux : Equipos_Tennis){
                        if (Aux.getId_Equipo()==_IdBuscado){
                            Retornar = Equipos_Tennis.indexOf(Aux);
                            break;
                        }
                    }
                }
                else if (TipoBuscado == 3){ // PING PONG
                    for (Equipo_PingPong Aux : Equipos_PingPong){
                        if (Aux.getId_Equipo()==_IdBuscado){
                            Retornar = Equipos_PingPong.indexOf(Aux);
                            break;
                        }
                    }
                }
                return Retornar;
            }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // RETORNADORES // GETTERS // SETTERS

        // RETORNAR POSICION DEL ARBITRO POR DNI
            private int RetornarPosicionArbitro(String _Dni){
                int Index=0;
                for (Arbitro Aux : Arbitros){
                    if (Aux.getDni().equals(_Dni)){
                        Index = Arbitros.indexOf(Aux);
                        break;
                    }
                }
                return Index;
            }

        // RETORNAR EQUIPO POR ID
            private Equipo RetornarEquipo_Id(int Id){
                Equipo Retornar = null;
                for (Equipo_Futbol Aux : Equipos_Futbol){
                    if (Aux.getId_Equipo() == Id){
                        Retornar = Aux;
                        break;
                    }
                }
                if (Retornar == null){
                    for (Equipo_Tennis Aux : Equipos_Tennis){
                        if (Aux.getId_Equipo() == Id){
                            Retornar = Aux;
                            break;
                        }
                    }
                }
                if (Retornar == null){
                    for (Equipo_PingPong Aux : Equipos_PingPong){
                        if (Aux.getId_Equipo() == Id){
                            Retornar = Aux;
                            break;
                        }
                    }
                }
                return Retornar;
            }

        // RETOERNAR TORNEO POR ID
            private Torneo RetornarTorneo_Id(int Id){
                Torneo Retornar = null;
                for (Torneo_Futbol Aux : Torneos_Futbol){
                    if (Aux.getId() == Id){
                        Retornar = Aux;
                        break;
                    }
                }
                if (Retornar == null){
                    for (Torneo_Tennis Aux : Torneos_Tennis){
                        if (Aux.getId() == Id){
                            Retornar = Aux;
                            break;
                        }
                    }
                }
                if (Retornar == null){
                    for (Torneo_PingPong Aux : Torneos_PingPong){
                        if (Aux.getId() == Id){
                            Retornar = Aux;
                            break;
                        }
                    }
                }
                return Retornar;
            }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // CREAR PERSONA
        private Persona Crear_Persona(Scanner Teclado){
            String Nombre, Apellido, Dni="", Nacionalidad;
            int Dia=0, Mes=0, Anio=0;
            boolean Continuar=true;
            boolean ChequearDiaConMes=true;

            // DNI
            while (Continuar){
                Dni = String.valueOf(IngresoPorTeclado.IngresarNumero(Teclado, "\n Dni: ", true, false));
                if (ExisteDni(Dni)){
                    System.out.print("\n El Dni ingresado ya corresponde a una persona existente.");
                }
                else{
                    Continuar = false;
                }
            }

            // NOMBRE APELLIDO Y NACIONALIDAD
            Nombre = IngresoPorTeclado.IngresarPalabra(Teclado, "\n Nombre: ", true, false);
            Apellido = IngresoPorTeclado.IngresarPalabra(Teclado, "\n Apellido: ", true, false);
            Nacionalidad = IngresoPorTeclado.IngresarPalabra(Teclado, "\n Nacionalidad: ", true, false);

            while (ChequearDiaConMes){
                // DIA DE NACIMIENTO
                Continuar = true;
                while(Continuar){
                    Dia = IngresoPorTeclado.IngresarNumero(Teclado, "\n Dia de nacimiento: ", true, false);
                    if (Dia>31){
                        System.out.print("\n Debe ingresar un dia entre 1 y 31.\n");
                    }
                    else if (Dia<1){
                        System.out.print("\n Debe ingresar un dia entre 1 y 31.\n");
                    }
                    else{
                        Continuar = false;
                    }
                }

                // MES DE NACIMIENTO
                Continuar = true;
                while(Continuar){
                    Mes = IngresoPorTeclado.IngresarNumero(Teclado, "\n Mes de nacimiento: ", true, false);
                    if (Mes>12){
                        System.out.print("\n Debe ingresar un mes entre 1 y 12.\n");
                    }
                    else if (Mes<1){
                        System.out.print("\n Debe ingresar un mes entre 1 y 12.\n");
                    }
                    else{
                        Continuar = false;
                    }
                }

                if (Mes==2 && Dia>29){
                    System.out.print("\n Debe ingresar una fecha valida. Febrero tiene como maximo 29 dias!");
                }
                else{
                    ChequearDiaConMes=false;
                }
            }

            // ANIO DE NACIMIENTO
            Continuar = true;
            while(Continuar){
                Anio = IngresoPorTeclado.IngresarNumero(Teclado, "\n Año de nacimiento: ", true, false);
                if (Anio>2005){
                    System.out.print("\n Debe ingresar un anio entre 1935 y 2005.\n");
                }
                else if (Anio<1935){
                    System.out.print("\n Debe ingresar un anio entre 1935 y 2005.\n");
                }
                else{
                    Continuar = false;
                }
            }

            return new Persona(
                    Nombre,
                    Apellido,
                    Dni,
                    Nacionalidad,
                    LocalDate.parse(
                            new DecimalFormat("00").format(Dia)+
                                    "-"+
                                    new DecimalFormat("00").format(Mes)+
                                    "-"+
                                    new DecimalFormat("00").format(Anio),
                            DateTimeFormatter.ofPattern("dd-MM-yyyy")
                    ));
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // IMPORTAR
        public void Importar_Personas_Equipos(){
            FileReader Archivo;
            BufferedReader Lector;
            try{
                Archivo = new FileReader("src/Clases/Administrador/Personas_Equipos.txt");
                if (Archivo.ready()){
                    Lector = new BufferedReader(Archivo);
                    ArrayList<String> Leidos = new ArrayList<>();
                    String Leido;
                    Persona NuevaPersona, NuevaPersona_2;
                    while ((Leido = Lector.readLine())!=null){
                        Leidos.add(Leido);
                    }

                    // ARBITROS
                    for(int i=0 ; i<7 ; i=i+7){
                        NuevaPersona = new Persona(
                                Leidos.get(i),
                                Leidos.get(i+1),
                                Leidos.get(i+2),
                                Leidos.get(i+3),
                                LocalDate.parse(
                                        new DecimalFormat("00").format(Integer.parseInt(Leidos.get(i+4)))+
                                                "-"+
                                                new DecimalFormat("00").format(Integer.parseInt(Leidos.get(i+5)))+
                                                "-"+
                                                new DecimalFormat("00").format(Integer.parseInt(Leidos.get(i+6))),
                                        DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                        );
                        Arbitros.add(new Arbitro(NuevaPersona));
                    }

                    // TENNIS
                    for (int i=7 ; i<167 ; i=i+10){
                        NuevaPersona = new Persona(
                                Leidos.get(i),
                                Leidos.get(i+1),
                                Leidos.get(i+2),
                                Leidos.get(i+3),
                                LocalDate.parse(
                                        Leidos.get(i+4),
                                        DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                        );
                        NuevaPersona_2 = new Persona(
                                Leidos.get(i+5),
                                Leidos.get(i+6),
                                Leidos.get(i+7),
                                Leidos.get(i+8),
                                LocalDate.parse(
                                        Leidos.get(i+9),
                                        DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                        );
                        Equipo_Tennis NuevoEquipoTennis = new Equipo_Tennis(new Tecnico_Entrenador(NuevaPersona), new Jugador_Tennis(NuevaPersona_2));
                        Equipos_Tennis.add(NuevoEquipoTennis);
                    }

                    // PING PONG
                    for (int i=167 ; i<327 ; i=i+10){
                        NuevaPersona = new Persona(
                                Leidos.get(i),
                                Leidos.get(i+1),
                                Leidos.get(i+2),
                                Leidos.get(i+3),
                                LocalDate.parse(
                                        Leidos.get(i+4),
                                        DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                        );
                        NuevaPersona_2 = new Persona(
                                Leidos.get(i+5),
                                Leidos.get(i+6),
                                Leidos.get(i+7),
                                Leidos.get(i+8),
                                LocalDate.parse(
                                        Leidos.get(i+9),
                                        DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                        );
                        Equipo_PingPong NuevoEquipoPingPong = new Equipo_PingPong(new Tecnico_Entrenador(NuevaPersona), new Jugador_PingPong(NuevaPersona_2));
                        Equipos_PingPong.add(NuevoEquipoPingPong);
                    }

                    // FUTBOL
                    int i=327;
                    for (int j=0 ; j<16 ; j++){
                        NuevaPersona = new Persona(
                                Leidos.get(i),
                                Leidos.get(i+1),
                                Leidos.get(i+2),
                                Leidos.get(i+3),
                                LocalDate.parse(
                                        Leidos.get(i+4),
                                        DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                        );
                        i+=6;
                        Equipo_Futbol NuevoEquipo = new Equipo_Futbol(new Tecnico_Entrenador(NuevaPersona), "Equipo nro: "+ j);
                        for (int x=0 ; x<11 ; x++){
                            NuevaPersona = new Persona(
                                    Leidos.get(i),
                                    Leidos.get(i+1),
                                    Leidos.get(i+2),
                                    Leidos.get(i+3),
                                    LocalDate.parse(
                                            Leidos.get(i+4),
                                            DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                            );
                            NuevoEquipo.AgregarJugador(new Jugador_Futbol(NuevaPersona, Leidos.get(i+5)));
                            i+=6;
                        }
                        Equipos_Futbol.add(NuevoEquipo);
                    }
                    Lector.close();
                }
                else{
                    System.out.println("Archivo no ready");
                }
            }
            catch (Exception _Exception){
                System.out.println(_Exception.getMessage());
            }
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDES
        @Override
        public String toString() {
            return "Administrador_Nueva{" +
                    "Equipos_Futbol=" + Equipos_Futbol +
                    ", Torneos_Futbol=" + Torneos_Futbol +
                    ", Equipos_Tennis=" + Equipos_Tennis +
                    ", Torneos_Tennis=" + Torneos_Tennis +
                    ", Equipos_PingPong=" + Equipos_PingPong +
                    ", Torneos_PingPong=" + Torneos_PingPong +
                    ", Arbitros=" + Arbitros +
                    '}';
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}