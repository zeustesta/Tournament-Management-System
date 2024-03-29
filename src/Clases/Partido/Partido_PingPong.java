package Clases.Partido;
import Clases.Equipo.Equipo_PingPong;
import Clases.IngresosPorTeclado.IngresoPorTeclado;
import Clases.Persona.Arbitro;
import Interfaces.Menu_Paleta_Raqueta;

import java.util.Arrays;
import java.util.Scanner;
public class Partido_PingPong extends Partido implements Menu_Paleta_Raqueta{

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ATRIBUTOS
        private Equipo_PingPong Equipo_1, Equipo_2;
        private int SetsGanados_E1, SetsGanados_E2;
        private int PuntosGanados_E1, PuntosGanados_E2;
        private int Aces_E1, Aces_E2, SetActual;
        private int SaquesErrados_E1, SaquesErrados_E2;
        private int[][] Resultados;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // CONSTRUCTORES
        public Partido_PingPong(Equipo_PingPong _Equipo_1, Equipo_PingPong _Equipo_2, Arbitro _Arbitro, int _Nro_Ronda){
            super(_Nro_Ronda, _Arbitro);

            SetsGanados_E1=0;
            SetsGanados_E2=0;

            PuntosGanados_E1=0;
            PuntosGanados_E2=0;

            Aces_E1=0;
            Aces_E2=0;

            SaquesErrados_E1=0;
            SaquesErrados_E2=0;

            this.Equipo_1 = _Equipo_1;
            this.Equipo_2 = _Equipo_2;

            this.Resultados = new int[2][5];
            Resultados[0][SetActual]=0;
            Resultados[1][SetActual]=0;
            SetActual=0;
        }
        public Partido_PingPong(int _Nro_Ronda, Arbitro _Arbitro){
            super(_Nro_Ronda, _Arbitro);

            SetsGanados_E1=0;
            SetsGanados_E2=0;

            PuntosGanados_E1=0;
            PuntosGanados_E2=0;

            Aces_E1=0;
            Aces_E2=0;

            SaquesErrados_E1=0;
            SaquesErrados_E2=0;

            Equipo_1 = null;
            Equipo_2 = null;

            Resultados = new int[2][5];
            Resultados[0][SetActual]=0;
            Resultados[1][SetActual]=0;
            SetActual=0;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // METODOS
        private void AumentarPuntaje(){
            if (TerminoElSet()){ // AUMENTAR SET ACTUAL, Y CONTADORES DEL GANADOR
                if (Resultados[0][SetActual] > Resultados[1][SetActual]){ // SI LO GANO EL JUGADOR 1
                    SetsGanados_E1++;
                    Equipo_1.getJugador().AumentarSetsGanados();
                    Equipo_2.getJugador().AumentarSetsPerdidos();
                }
                else{ // SI LO GANO EL JUGADOR 2
                    SetsGanados_E2++;
                    Equipo_1.getJugador().AumentarSetsPerdidos();
                    Equipo_2.getJugador().AumentarSetsGanados();
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
        }
        public void AgregarEquipo(Equipo_PingPong Agregar){
            if (Equipo_1==null){
                Equipo_1 = Agregar;
            }
            else{
                Equipo_2 = Agregar;
            }
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // VERIFICADORES
        public boolean EstaElPartidoCompleto(){
            return (Equipo_1!=null && Equipo_2!=null);
        }
        private boolean TerminoElSet(){
            boolean Retornar = false;
            if (Resultados[0][SetActual]>= 11 && Resultados[0][SetActual]-Resultados[1][SetActual]>=2){
                Retornar = true;
            }
            else if (Resultados[1][SetActual]>= 11 && Resultados[1][SetActual]-Resultados[0][SetActual]>=2){
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
        public void MenuPartido(Scanner Teclado) {
            if (Activo){
                int Opcion;
                boolean Continuar = true;
                while(Continuar){
                    System.out.print("\n "+Equipo_1.getJugador().getNombre()+" "+Equipo_1.getJugador().getApellido()+":");
                    System.out.print(" Sets ganados: "+SetsGanados_E1+". Puntos Ganados: "+PuntosGanados_E1+". Aces: "+Aces_E1+". Saques errados: "+SaquesErrados_E1+".");
                    System.out.print("\n "+Equipo_2.getJugador().getNombre()+" "+Equipo_2.getJugador().getApellido()+":");
                    System.out.print(" Sets ganados: "+SetsGanados_E2+". Puntos Ganados: "+PuntosGanados_E2+". Aces: "+Aces_E2+". Saques errados: "+SaquesErrados_E2+".");
                    System.out.print("\n");

                    System.out.print("\n Resultados:");

                    System.out.print("\n "+Equipo_1.getJugador().getNombre()+" "+Equipo_1.getJugador().getApellido()+":");
                    for (int i=0 ; i<SetActual+1 ; i++){
                        System.out.printf("|%d", Resultados[0][i]);
                    }
                    System.out.print("|");

                    System.out.print("\n "+Equipo_2.getJugador().getNombre()+" "+Equipo_1.getJugador().getApellido()+":");
                    for (int i=0 ; i<SetActual+1 ; i++){
                        System.out.printf("|%d", Resultados[1][i]);
                    }
                    System.out.print("|");

                    System.out.print("\n");
                    System.out.print("\n Elija una de las siguientes opciones.");
                    System.out.print("\n 01 - Punto");
                    System.out.print("\n 02 - Ace");
                    System.out.print("\n 03 - Saque errado");
                    System.out.print("\n 04 - Suspender partido");
                    System.out.print("\n");
                    Opcion = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion: ", false, false);
                    switch (Opcion) {
                        case 1 -> MenuPuntos(Teclado);
                        case 2 -> MenuAces(Teclado);
                        case 3 -> MenuSaquesErrados(Teclado);
                        case 4 -> Continuar = false;
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
        public void MenuPuntos(Scanner Teclado){
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
                    PuntosGanados_E1++;
                    Resultados[0][SetActual]++;
                    Equipo_1.getJugador().AumentarPuntosGanados_Goles();
                    Equipo_2.getJugador().AumentarPuntosPerdidos();
                    AumentarPuntaje();
                    Continuar = false;
                }
                else if (Swtch==2){
                    PuntosGanados_E2++;
                    Resultados[1][SetActual]++;
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
        public void MenuAces(Scanner Teclado){
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
                    PuntosGanados_E1++;
                    Resultados[0][SetActual]++;
                    Aces_E1++;
                    Equipo_1.getJugador().AumentarPuntosGanados_Goles();
                    Equipo_1.getJugador().AumentarAces();
                    Equipo_2.getJugador().AumentarPuntosPerdidos();
                    Equipo_2.getJugador().AumentarSaquesNoRecibidos();
                    AumentarPuntaje();
                    Continuar = false;
                }
                else if (Swtch==2){
                    PuntosGanados_E2++;
                    Resultados[1][SetActual]++;
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
                System.out.print("\n ¿De que equipo fue el saque errado?");
                System.out.print("\n");
                System.out.print("\n Ingrese -1 para volver al menu anterior.");
                System.out.print("\n");
                System.out.print("\n 01 - "+Equipo_1.getJugador().getNombre()+" "+Equipo_1.getJugador().getApellido()+".");
                System.out.print("\n 02 - "+Equipo_2.getJugador().getNombre()+" "+Equipo_2.getJugador().getApellido()+".");
                System.out.print("\n");
                Swtch = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion: ", false, true);
                if (Swtch==1){
                    PuntosGanados_E2++;
                    Resultados[1][SetActual]++;
                    SaquesErrados_E1++;
                    Equipo_1.getJugador().AumentarPuntosPerdidos();
                    Equipo_1.getJugador().AumentarSaqueIncorrecto();
                    Equipo_2.getJugador().AumentarPuntosGanados_Goles();
                    AumentarPuntaje();
                    Continuar = false;
                }
                else if (Swtch==2){
                    PuntosGanados_E1++;
                    Resultados[0][SetActual]++;
                    SaquesErrados_E2++;
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
            System.out.print("\n ---------------------------------------------------------------------------------");
            System.out.print("\n |Nombre de Equipo|-|Sets Ganados|-|Puntos Ganados|-|Aces Hechos|-|Saques Errados|");
            System.out.printf("\n |%16s|", Equipo_1.getJugador().getNombre()+" "+Equipo_1.getJugador().getApellido());
            System.out.printf("-|%12s|", SetsGanados_E1);
            System.out.printf("-|%14s|", PuntosGanados_E1);
            System.out.printf("-|%11s|", Aces_E1);
            System.out.printf("-|%14s|", SaquesErrados_E1);
            System.out.printf("\n |%16s|", Equipo_2.getJugador().getNombre()+" "+Equipo_1.getJugador().getApellido());
            System.out.printf("-|%12s|", SetsGanados_E2);
            System.out.printf("-|%14s|", PuntosGanados_E2);
            System.out.printf("-|%11s|", Aces_E2);
            System.out.printf("-|%14s|", SaquesErrados_E2);
            System.out.print("\n ---------------------------------------------------------------------------------");
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // RETORNADORES GETTERS SETTERS
        public Equipo_PingPong getEquipo_1() {
            return Equipo_1;
        }
        public Equipo_PingPong getEquipo_2() {
            return Equipo_2;
        }
        public int getPuntosGanados_E1() {
            return PuntosGanados_E1;
        }
        public int getPuntosGanados_E2() {
            return PuntosGanados_E2;
        }
        public int getAces_E1() {
            return Aces_E1;
        }
        public int getAces_E2() {
            return Aces_E2;
        }
        public int getSaquesErrados_E1() {
            return SaquesErrados_E1;
        }
        public int getSaquesErrados_E2() {
            return SaquesErrados_E2;
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
        public int getSetsGanados_E1() {
            return SetsGanados_E1;
        }
        public int getSetsGanados_E2() {
            return SetsGanados_E2;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDE
        @Override
        public String toString() {
            return "Partido_PingPong{" +
                    "Equipo_1=" + Equipo_1 +
                    ", Equipo_2=" + Equipo_2 +
                    ", SetsGanados_E1=" + SetsGanados_E1 +
                    ", SetsGanados_E2=" + SetsGanados_E2 +
                    ", PuntosGanados_E1=" + PuntosGanados_E1 +
                    ", PuntosGanados_E2=" + PuntosGanados_E2 +
                    ", Aces_E1=" + Aces_E1 +
                    ", Aces_E2=" + Aces_E2 +
                    ", SetActual=" + SetActual +
                    ", SaquesErrados_E1=" + SaquesErrados_E1 +
                    ", SaquesErrados_E2=" + SaquesErrados_E2 +
                    ", Resultados=" + Arrays.toString(Resultados) +
                    ", Activo=" + Activo +
                    ", Id_Partido=" + Id_Partido +
                    ", Arbitro=" + Arbitro +
                    ", Nro_Ronda=" + Nro_Ronda +
                    ", Ganador=" + Ganador +
                    '}';
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}