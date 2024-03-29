package Clases.Partido;
import Clases.Equipo.Equipo_Tennis;
import Clases.IngresosPorTeclado.IngresoPorTeclado;
import Clases.Persona.Arbitro;
import Interfaces.Menu_Paleta_Raqueta;

import java.util.Arrays;
import java.util.Scanner;
public class Partido_Tennis extends Partido implements Menu_Paleta_Raqueta{

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ATRIBUTOS
        private Equipo_Tennis Equipo_1, Equipo_2;
        private int Aces_E1, Aces_E2;
        private int Puntos_E1, Puntos_E2;
        private int DobleFalta_E1, DobleFalta_E2;
        private int[][] Resultados;
        private int Contador_E1, Contador_E2;
        private int SetActual;
        private int SetsGanados_E1, SetsGanados_E2;
        private int GamesGanados_E1, GamesGanados_E2;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // CONSTRUCTORES
        public Partido_Tennis(int _Nro_Ronda, Arbitro _Arbitro){
            super(_Nro_Ronda, _Arbitro);
            SetsGanados_E1=0;
            SetsGanados_E2=0;
            GamesGanados_E1=0;
            GamesGanados_E2=0;
            Puntos_E1=0;
            Puntos_E2=0;
            Aces_E1=0;
            Aces_E2=0;
            DobleFalta_E1=0;
            DobleFalta_E2=0;
            Equipo_1 = null;
            Equipo_2 = null;
            SetActual=0;
            Contador_E1=0;
            Contador_E2=0;
            this.Resultados = new int[2][5];
            Resultados[0][SetActual]=0;
            Resultados[1][SetActual]=0;
        }
        public Partido_Tennis(Equipo_Tennis _Equipo_1, Equipo_Tennis _Equipo_2, Arbitro _Arbitro, int _Nro_Ronda){
            super(_Nro_Ronda, _Arbitro);
            this.Equipo_1 = _Equipo_1;
            this.Equipo_2 = _Equipo_2;
            SetsGanados_E1=0;
            SetsGanados_E2=0;
            GamesGanados_E1=0;
            GamesGanados_E2=0;
            Puntos_E1=0;
            Puntos_E2=0;
            Aces_E1=0;
            Aces_E2=0;
            DobleFalta_E1=0;
            DobleFalta_E2=0;
            Contador_E1=0;
            Contador_E2=0;
            SetActual=0;
            this.Resultados = new int[2][5];
            Resultados[0][SetActual]=0;
            Resultados[1][SetActual]=0;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // METODOS
        public void AgregarEquipo(Equipo_Tennis Agregar){
            if (Equipo_1==null){
                Equipo_1 = Agregar;
            }
            else{
                Equipo_2 = Agregar;
            }
        }
        private void AumentarPuntaje(){
            if (TerminoElGame()){ // AUMENTAR GAME AL GANADOR
                if (Contador_E1 > Contador_E2){ // SI LO GANO EL JUGADOR 1
                    Resultados[0][SetActual]++;
                    GamesGanados_E1++;
                    Equipo_1.getJugador().AumentarGamesGanados();
                    Equipo_2.getJugador().AumentarGamesPerdidos();
                }
                else{ // SI LO GANO EL JUGADOR 2
                    Resultados[1][SetActual]++;
                    GamesGanados_E2++;
                    Equipo_2.getJugador().AumentarGamesGanados();
                    Equipo_1.getJugador().AumentarGamesPerdidos();
                }
                if (TerminoElSet()){ // AUMENTAR SET ACTUAL, Y CONTADORES DEL GANADOR
                    if (Resultados[0][SetActual] > Resultados[1][SetActual]){ // SI LO GANO EL JUGADOR 1
                        SetsGanados_E1++;
                        Equipo_1.getJugador().AumentarSetsGanados();
                        Equipo_2.getJugador().AumentarSetsPerdidos();
                    }
                    else{ // SI LO GANO EL JUGADOR 2
                        SetsGanados_E2++;
                        Equipo_2.getJugador().AumentarSetsGanados();
                        Equipo_1.getJugador().AumentarSetsPerdidos();
                    }
                    if (TerminoElPartido()){ // TERMINAR EL PARTIDO
                        setGanador(SetsGanados_E1>SetsGanados_E2);
                        Activo = false;
                    }
                    else{
                        SetActual++;
                        Resultados[0][SetActual] = 0;
                        Resultados[1][SetActual] = 0;
                    }
                }

                Contador_E1 = 0;
                Contador_E2 = 0;
            }
        }
        public boolean EstaElPartidoCompleto(){
            return (Equipo_1!=null && Equipo_2!=null);
        }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // VERIFICADORES
        private boolean TerminoElGame(){
            boolean Retornar = false;
            if ((Contador_E1>=4) && (Contador_E1-Contador_E2>=2)){
                Retornar = true;
            }
            else if ((Contador_E2>=4) && (Contador_E2-Contador_E1>=2)){
                Retornar = true;
            }
            return Retornar;
        }
        private boolean TerminoElSet(){
            boolean Retornar = false;
            if (Resultados[0][SetActual] >= 6 && Resultados[0][SetActual]-Resultados[1][SetActual] >= 2) {
                Retornar = true;
            }
            else if(Resultados[1][SetActual] >= 6 && Resultados[1][SetActual]-Resultados[0][SetActual] >= 2){
                Retornar = true;
            }
            return Retornar;
        }
        private boolean TerminoElPartido(){
            boolean Retornar = false;
            if ((SetsGanados_E1>=3) && (SetsGanados_E1>SetsGanados_E2)){
                Retornar = true;
            }
            else if ((SetsGanados_E2>=3) && (SetsGanados_E2>SetsGanados_E1)){
                Retornar = true;
            }
            return Retornar;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDES
        @Override
        public void MenuPartido(Scanner Teclado){
            if (Activo){
                int Opcion;
                boolean Continuar = true;
                while(Continuar){
                    System.out.print("\n "+Equipo_1.getJugador().getNombre()+" "+Equipo_1.getJugador().getApellido()+":");
                    System.out.print(" Sets ganados: "+SetsGanados_E1+". Games Ganados: "+GamesGanados_E1+". Puntos Ganados: "+Puntos_E1+". Aces: "+Aces_E1+". Dobles faltas: "+DobleFalta_E1+".");
                    System.out.print("\n "+Equipo_2.getJugador().getNombre()+" "+Equipo_2.getJugador().getApellido()+":");
                    System.out.print(" Sets ganados: "+SetsGanados_E2+". Games Ganados: "+GamesGanados_E2+". Puntos Ganados: "+Puntos_E2+". Aces: "+Aces_E2+". Dobles faltas: "+DobleFalta_E2+".");
                    System.out.print("\n");

                    System.out.print("\n Resultados:");

                    System.out.print("\n "+Equipo_1.getJugador().getNombre()+" "+Equipo_1.getJugador().getApellido()+":");
                    for (int i=0 ; i<SetActual+1 ; i++){
                        System.out.printf("|%d", Resultados[0][i]);
                    }
                    System.out.print("|");
                    System.out.print(RetornarPuntaje(Contador_E1, Contador_E2)+"|");

                    System.out.print("\n "+Equipo_2.getJugador().getNombre()+" "+Equipo_1.getJugador().getApellido()+":");
                    for (int i=0 ; i<SetActual+1 ; i++){
                        System.out.printf("|%d", Resultados[1][i]);
                    }
                    System.out.print("|");
                    System.out.print(RetornarPuntaje(Contador_E2, Contador_E1)+"|");
                    // FALTA GAMES Y PUNTOS ACTUALES

                    System.out.print("\n");
                    System.out.print("\n Elija una de las siguientes opciones.");
                    System.out.print("\n 01 - Punto");
                    System.out.print("\n 02 - Ace");
                    System.out.print("\n 03 - Doble Falta");
                    System.out.print("\n 04 - Suspender partido");
                    System.out.print("\n");
                    Opcion = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion: ", false, false);
                    switch (Opcion) {
                        case 1 -> MenuPuntos(Teclado);
                        case 2 -> MenuAces(Teclado);
                        case 3 -> MenuSaquesErrados(Teclado);
                        case 4 -> {
                            System.out.print("\n El partido esta suspendido hasta su continuacion!");
                            Continuar = false;
                        }
                        default ->{}
                    }
                    if (!Activo){
                        Continuar = false;
                    }
                }
            }
            if (!Activo){
                System.out.print("\n El partido ha finalizado! A continuacion las estadisticas.");
                MostrarEstadisticas();
                System.out.print("\n ");
                Teclado.nextLine();
            }
        }
        @Override
        public void MenuPuntos(Scanner Teclado) {
            boolean Continuar = true;
            int Swtch;
            while (Continuar){

                System.out.print("\n ¿De que equipo fue el punto?");
                System.out.print("\n");
                System.out.print("\n Ingrese -1 para volver al menu anterior.");
                System.out.print("\n");
                System.out.print("\n 01 - "+Equipo_1.getJugador().getNombre()+" "+Equipo_1.getJugador().getApellido()+".");
                System.out.print("\n 02 - "+Equipo_2.getJugador().getNombre()+" "+Equipo_2.getJugador().getApellido()+".");
                System.out.print("\n");
                Swtch = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion: ", false, true);
                if (Swtch==1){
                    Puntos_E1++;
                    Contador_E1++;
                    Equipo_1.getJugador().AumentarPuntosGanados_Goles();
                    Equipo_2.getJugador().AumentarPuntosPerdidos();
                    AumentarPuntaje();
                    Continuar = false;
                }
                else if (Swtch==2){
                    Puntos_E2++;
                    Contador_E2++;
                    Equipo_2.getJugador().AumentarPuntosGanados_Goles();
                    Equipo_1.getJugador().AumentarPuntosPerdidos();
                    AumentarPuntaje();
                    Continuar = false;
                }
                else if (Swtch == -1){
                    Continuar = false;
                }
            }
        }
        @Override
        public void MenuAces(Scanner Teclado) {
            boolean Continuar = true;
            int Swtch;
            while (Continuar){

                System.out.print("\n ¿De que equipo fue el ACE?");
                System.out.print("\n");
                System.out.print("\n Ingrese -1 para volver al menu anterior.");
                System.out.print("\n");
                System.out.print("\n 01 - "+Equipo_1.getJugador().getNombre()+" "+Equipo_1.getJugador().getApellido()+".");
                System.out.print("\n 02 - "+Equipo_2.getJugador().getNombre()+" "+Equipo_2.getJugador().getApellido()+".");
                System.out.print("\n");
                Swtch = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion: ", false, true);
                if (Swtch==1){
                    Puntos_E1++;
                    Contador_E1++;
                    Aces_E1++;
                    Equipo_1.getJugador().AumentarPuntosGanados_Goles();
                    Equipo_1.getJugador().AumentarAces();
                    Equipo_2.getJugador().AumentarPuntosPerdidos();
                    Equipo_2.getJugador().AumentarSaquesNoRecibidos();
                    AumentarPuntaje();
                    Continuar = false;
                }
                else if (Swtch==2){
                    Puntos_E2++;
                    Contador_E2++;
                    Aces_E2++;
                    Equipo_2.getJugador().AumentarPuntosGanados_Goles();
                    Equipo_2.getJugador().AumentarAces();
                    Equipo_1.getJugador().AumentarPuntosPerdidos();
                    Equipo_1.getJugador().AumentarSaquesNoRecibidos();
                    AumentarPuntaje();
                    Continuar = false;
                }
                else if (Swtch == -1){
                    Continuar = false;
                }
            }
        }
        @Override
        public void MenuSaquesErrados(Scanner Teclado) {
            boolean Continuar = true;
            int Swtch;
            while (Continuar){
                System.out.print("\n ¿De que equipo fue la doble falta?");
                System.out.print("\n");
                System.out.print("\n Ingrese -1 para volver al menu anterior.");
                System.out.print("\n");
                System.out.print("\n 01 - "+Equipo_1.getJugador().getNombre()+" "+Equipo_1.getJugador().getApellido()+".");
                System.out.print("\n 02 - "+Equipo_2.getJugador().getNombre()+" "+Equipo_2.getJugador().getApellido()+".");
                System.out.print("\n");
                Swtch = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion: ", false, true);
                if (Swtch==1){
                    Puntos_E2++;
                    Contador_E2++;
                    DobleFalta_E1++;
                    Equipo_1.getJugador().AumentarPuntosPerdidos();
                    Equipo_1.getJugador().AumentarSaqueIncorrecto();
                    Equipo_2.getJugador().AumentarPuntosGanados_Goles();
                    AumentarPuntaje();
                    Continuar = false;
                }
                else if (Swtch==2){
                    Puntos_E1++;
                    Contador_E1++;
                    DobleFalta_E2++;
                    Equipo_2.getJugador().AumentarPuntosPerdidos();
                    Equipo_2.getJugador().AumentarSaqueIncorrecto();
                    Equipo_1.getJugador().AumentarPuntosGanados_Goles();
                    AumentarPuntaje();
                    Continuar = false;
                }
                else if (Swtch == -1){
                    Continuar = false;
                }
            }
        }
        @Override
        public void MostrarEstadisticas() {
            System.out.print("\n ------------------------------------------------------------------------------------------------");
            System.out.print("\n |Nombre de Equipo|-|Sets Ganados|-|Games Ganados|-|Puntos Ganados|-|Aces Hechos|-|Dobles Faltas|");
            System.out.printf("\n |%16s|", Equipo_1.getJugador().getNombre()+" "+Equipo_1.getJugador().getApellido());
            System.out.printf("-|%12s|", SetsGanados_E1);
            System.out.printf("-|%13s|", GamesGanados_E1);
            System.out.printf("-|%14s|", Puntos_E1);
            System.out.printf("-|%11s|", Aces_E1);
            System.out.printf("-|%13s|", DobleFalta_E1);
            System.out.printf("\n |%16s|", Equipo_2.getJugador().getNombre()+" "+Equipo_1.getJugador().getApellido());
            System.out.printf("-|%12s|", SetsGanados_E2);
            System.out.printf("-|%13s|", GamesGanados_E2);
            System.out.printf("-|%14s|", Puntos_E2);
            System.out.printf("-|%11s|", Aces_E2);
            System.out.printf("-|%13s|", DobleFalta_E2);
            System.out.print("\n ------------------------------------------------------------------------------------------------");
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // RETORNADORES GETTERS SETTERS
        private String RetornarPuntaje(int Contador, int Contrario){
            String Retornar="";
            if (Contador == 0) {
                Retornar += "00";
            }
            else if (Contador == 1) {
                Retornar += "15";
            }
            else if (Contador == 2) {
                Retornar += "30";
            }
            else if ((Contador==Contrario) && (Contador>=3)){
                Retornar += "40";
            }
            else if (Contador==3 && Contrario<=3){
                Retornar += "40";
            }
            else if (Contador-Contrario==1 && Contador>=4){
                Retornar += "AD";
            }
            else if (Contrario-Contador==1 && Contrario>=4){
                Retornar += "40";
            }
            return Retornar;
        }
        public String GanadorString(){
            String Retornar="";
            if (!Activo){
                if (Ganador){
                    Retornar += Equipo_1.getJugador().getNombre()+" "+Equipo_1.getJugador().getApellido();
                }
                else{
                    Retornar += Equipo_2.getJugador().getNombre()+" "+Equipo_2.getJugador().getApellido();
                }
            }
            return Retornar;
        }
        public Equipo_Tennis getEquipo_1() {
            return Equipo_1;
        }
        public Equipo_Tennis getEquipo_2() {
            return Equipo_2;
        }
        public int getAces_E1() {
            return Aces_E1;
        }
        public int getAces_E2() {
            return Aces_E2;
        }
        public int getPuntos_E1() {
            return Puntos_E1;
        }
        public int getPuntos_E2() {
            return Puntos_E2;
        }
        public int getDobleFalta_E1() {
            return DobleFalta_E1;
        }
        public int getDobleFalta_E2() {
            return DobleFalta_E2;
        }
        public int getSetsGanados_E1() {
            return SetsGanados_E1;
        }
        public int getSetsGanados_E2() {
            return SetsGanados_E2;
        }
        public int getGamesGanados_E1() {
            return GamesGanados_E1;
        }
        public int getGamesGanados_E2() {
            return GamesGanados_E2;
            }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDE
        @Override
        public String toString() {
            return "Partido_Tennis{" +
                    "Equipo_1=" + Equipo_1 +
                    ", Equipo_2=" + Equipo_2 +
                    ", Aces_E1=" + Aces_E1 +
                    ", Aces_E2=" + Aces_E2 +
                    ", Puntos_E1=" + Puntos_E1 +
                    ", Puntos_E2=" + Puntos_E2 +
                    ", DobleFalta_E1=" + DobleFalta_E1 +
                    ", DobleFalta_E2=" + DobleFalta_E2 +
                    ", Resultados=" + Arrays.toString(Resultados) +
                    ", Contador_E1=" + Contador_E1 +
                    ", Contador_E2=" + Contador_E2 +
                    ", SetActual=" + SetActual +
                    ", SetsGanados_E1=" + SetsGanados_E1 +
                    ", SetsGanados_E2=" + SetsGanados_E2 +
                    ", GamesGanados_E1=" + GamesGanados_E1 +
                    ", GamesGanados_E2=" + GamesGanados_E2 +
                    ", Activo=" + Activo +
                    ", Id_Partido=" + Id_Partido +
                    ", Arbitro=" + Arbitro +
                    ", Nro_Ronda=" + Nro_Ronda +
                    ", Ganador=" + Ganador +
                    '}';
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}