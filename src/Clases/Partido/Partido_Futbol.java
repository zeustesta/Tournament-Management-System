package Clases.Partido;
import Clases.Equipo.Equipo_Futbol;
import Clases.IngresosPorTeclado.IngresoPorTeclado;
import Clases.Persona.Arbitro;
import Clases.Persona.Jugador_Futbol;
import java.util.Scanner;
public class Partido_Futbol extends Partido{
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ATRIBUTOS
        private Equipo_Futbol Equipo_1, Equipo_2;
        private int Goles_E1, Goles_E2;
        private int Penales_E1, Penales_E2;
        private int Amarillas_E1, Amarillas_E2;
        private int Rojas_E1, Rojas_E2;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // CONSTRUCTORES
        public Partido_Futbol(int _Nro_Ronda, Arbitro _Arbitro){
            super(_Nro_Ronda, _Arbitro);
            Goles_E1=0;
            Goles_E2=0;
            Penales_E1=0;
            Penales_E2=0;
            Amarillas_E1=0;
            Amarillas_E2=0;
            Rojas_E1=0;
            Rojas_E2=0;
            Equipo_1 = null;
            Equipo_2 = null;
        }

        public Partido_Futbol(Equipo_Futbol _Equipo_1, Equipo_Futbol _Equipo_2, Arbitro _Arbitro, int _Nro_ronda){
            super(_Nro_ronda, _Arbitro);
            Goles_E1=0;
            Goles_E2=0;
            Penales_E1=0;
            Penales_E2=0;
            Amarillas_E1=0;
            Amarillas_E2=0;
            Rojas_E1=0;
            Rojas_E2=0;
            Equipo_1 = _Equipo_1;
            Equipo_2 = _Equipo_2;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // METODOS

        public void AgregarEquipo(Equipo_Futbol Agregar){
            if (Equipo_1==null){
                Equipo_1 = Agregar;
            }
            else{
                Equipo_2 = Agregar;
            }
        }

        // AUMENTAR GOLES
            private void MenuGoles(Scanner Teclado){
                boolean Continuar = true;
                int Swtch;
                while (Continuar){

                    System.out.print("\n ¿De que equipo fue el gol?");
                    System.out.print("\n");
                    System.out.print("\n Ingrese -1 para volver al menu anterior.");
                    System.out.print("\n");
                    System.out.print("\n 01 - "+Equipo_1.getNombreEquipo()+".");
                    System.out.print("\n 02 - "+Equipo_2.getNombreEquipo()+".");
                    System.out.print("\n");
                    Swtch = IngresoPorTeclado.IngresarNumero(Teclado, "\n Equipo: ", false, true);
                    if (Swtch==1){
                        Goles_E1++;
                        ElegirJugador_Gol(Teclado, Equipo_1);
                        Continuar = false;
                    }
                    else if (Swtch==2){
                        Goles_E2++;
                        ElegirJugador_Gol(Teclado, Equipo_2);
                        Continuar = false;
                    }
                    else if (Swtch == -1){
                        Continuar = false;
                    }
                }
            }
            public void ElegirJugador_Gol(Scanner Teclado, Equipo_Futbol Aumentar){
                boolean Continuar = true;
                String Dni;
                while (Continuar){
                    System.out.printf("\n Ingrese el Dni del jugador del equipo %s que metio el gol.", Aumentar.getNombreEquipo());
                    System.out.print("\n");
                    for (Jugador_Futbol Aux : Aumentar.getJugadores()){
                        System.out.print("\n Dni: "+Aux.getDni()+". Nombre y Apellido: "+Aux.getNombre()+" "+Aux.getApellido());
                    }
                    System.out.print("\n");
                    Dni = IngresoPorTeclado.IngresarPalabraAlfanumerica(Teclado, "\n Dni: ", false, false);
                    if (Aumentar.ExisteDni(Dni)){
                        Aumentar.AumentarGolPorDni(Dni);
                        Continuar = false;
                    }
                    else{
                        System.out.print("\n Ingrese el Dni de algun jugador mostrado anteriormente!");
                    }
                }
            }

        // AUMENTAR AMARILLAS
            private void MenuAmarillas(Scanner Teclado){
                boolean Continuar = true;
                int Swtch;
                while (Continuar){

                    System.out.print("\n Ingrese -1 para volver al menu anterior.");
                    System.out.print("\n");
                    System.out.print("\n ¿De que equipo fue la amarilla?");
                    System.out.print("\n");
                    System.out.print("\n 01 - "+Equipo_1.getNombreEquipo()+".");
                    System.out.print("\n 02 - "+Equipo_2.getNombreEquipo()+".");
                    System.out.print("\n");
                    Swtch = IngresoPorTeclado.IngresarNumero(Teclado, "\n Equipo: ", false, true);
                    if (Swtch==1){
                        Amarillas_E1++;
                        ElegirJugador_Amarilla(Teclado, Equipo_1);
                        Continuar = false;
                    }
                    else if (Swtch==2){
                        Amarillas_E2++;
                        ElegirJugador_Amarilla(Teclado, Equipo_2);
                        Continuar = false;
                    }
                    else if (Swtch == -1){
                        Continuar = false;
                    }
                }
            }
            private void ElegirJugador_Amarilla(Scanner Teclado, Equipo_Futbol Aumentar){
                boolean Continuar = true;
                String Dni;
                while (Continuar){
                    System.out.printf("\n Ingrese el Dni del jugador del equipo %s que recibio la amarilla.", Aumentar.getNombreEquipo());
                    System.out.print("\n Ingrese -1 para volver.");
                    System.out.print("\n");
                    for (Jugador_Futbol Aux : Aumentar.getJugadores()){
                        System.out.print("\n Dni: "+Aux.getDni()+". Nombre y Apellido: "+Aux.getNombre()+" "+Aux.getApellido());
                    }
                    System.out.print("\n");
                    Dni = IngresoPorTeclado.IngresarPalabraAlfanumerica(Teclado, "\n Dni: ", false, true);
                    if(!Dni.equals("-1")){
                        if (Aumentar.ExisteDni(Dni)){
                            Aumentar.AumentarAmarillasPorDni(Dni);
                            Continuar = false;
                        }
                        else{
                            System.out.print("\n Ingrese el Dni de algun jugador mostrado anteriormente!");
                        }
                    }
                    else {
                        Continuar = false;
                    }
                }
            }

        // AUMENTAR ROJAS
            private void MenuRojas(Scanner Teclado){
                boolean Continuar = true;
                int Swtch;
                while (Continuar){

                    System.out.print("\n ¿De que equipo fue la roja?");
                    System.out.print("\n");
                    System.out.print("\n Ingrese -1 para volver al menu anterior.");
                    System.out.print("\n");
                    System.out.print("\n 01 - "+Equipo_1.getNombreEquipo()+".");
                    System.out.print("\n 02 - "+Equipo_2.getNombreEquipo()+".");
                    System.out.print("\n");
                    Swtch = IngresoPorTeclado.IngresarNumero(Teclado, "\n Equipo: ", false, true);
                    if (Swtch==1){
                        Rojas_E1++;
                        ElegirJugador_Roja(Teclado, Equipo_1);
                        Continuar = false;
                    }
                    else if (Swtch==2){
                        Rojas_E2++;
                        ElegirJugador_Roja(Teclado, Equipo_2);
                        Continuar = false;
                    }
                    else if (Swtch == -1){
                        Continuar = false;
                    }
                }
            }
            private void ElegirJugador_Roja(Scanner Teclado, Equipo_Futbol Aumentar){
                boolean Continuar = true;
                String Dni;
                while (Continuar){
                    System.out.printf("\n Ingrese el Dni del jugador del equipo %s que recibio la roja.", Aumentar.getNombreEquipo());
                    System.out.print("\n Ingrese -1 para volver.");
                    System.out.print("\n");
                    for (Jugador_Futbol Aux : Aumentar.getJugadores()){
                        System.out.print("\n Dni: "+Aux.getDni()+". Nombre y Apellido: "+Aux.getNombre()+" "+Aux.getApellido());
                    }
                    System.out.print("\n");
                    Dni = IngresoPorTeclado.IngresarPalabraAlfanumerica(Teclado, "\n Dni: ", false, true);
                    if(!Dni.equals("-1")){
                        if (Aumentar.ExisteDni(Dni)){
                            Aumentar.AumentarRojasPorDni(Dni);
                            Continuar = false;
                        }
                        else{
                            System.out.print("\n Ingrese el Dni de algun jugador mostrado anteriormente!");
                        }
                    }
                    else {
                        Continuar = false;
                    }
                }
            }

        // FINALIZAR PARTIDO
            private void FinalizarPartido(Scanner Teclado){
                if (Goles_E1 != Goles_E2){
                    setGanador(Goles_E1 > Goles_E2);
                    Activo = false;
                }
                else{
                    IrAPenales(Teclado);
                }
            }
            private void IrAPenales(Scanner Teclado){
                boolean Continuar = true;
                int Ingresado;
                int Errado_1=0, Errado_2=0;
                System.out.print("\n Se desempatara el partido al mejor de 5 penales.");
                System.out.print("\n Se deberan tirar penales, hasta que la suma de los hechos y errados de ambos equipos sea 5.");
                System.out.print("\n En caso de que la suma de ambos de cinco, y tengan la misma cantidad de goles, se seguira pateando.");
                System.out.print("\n Vaya ingresando los goles hechos y errados.");
                System.out.print("\n");
                while (Continuar){

                    // PRIMER EQUIPO
                    while(Continuar){
                        System.out.print("\n");
                        System.out.print("\n "+Equipo_1.getNombreEquipo()+": "+Penales_E1+"/"+(Penales_E1+Errado_1)+".");
                        System.out.print("\n "+Equipo_2.getNombreEquipo()+": "+Penales_E2+"/"+(Penales_E2+Errado_2)+".");
                        System.out.print("\n");
                        System.out.print("\n Ingrese una opcion.");
                        System.out.print("\n");
                        System.out.print("\n 1 - Gol de "+Equipo_1.getNombreEquipo());
                        System.out.print("\n 2 - Penal errado de "+Equipo_1.getNombreEquipo());
                        System.out.print("\n");
                        Ingresado = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion: ", false, false);
                        switch (Ingresado){
                            case 1 -> {
                                Penales_E1++;
                                Continuar = false;
                            }
                            case 2 -> {
                                Errado_1++;
                                Continuar = false;
                            }
                            default-> {}
                        }
                    }

                    // SEGUNDO EQUIPO
                    Continuar=true;
                    while (Continuar){
                        System.out.print("\n");
                        System.out.print("\n "+Equipo_1.getNombreEquipo()+": "+Penales_E1+"/"+(Penales_E1+Errado_1)+".");
                        System.out.print("\n "+Equipo_2.getNombreEquipo()+": "+Penales_E2+"/"+(Penales_E2+Errado_2)+".");
                        System.out.print("\n");
                        System.out.print("\n Ingrese una opcion.");
                        System.out.print("\n");
                        System.out.print("\n 1 - Gol de "+Equipo_2.getNombreEquipo());
                        System.out.print("\n 2 - Penal errado de "+Equipo_2.getNombreEquipo());
                        System.out.print("\n");
                        Ingresado = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion: ", false, false);
                        switch (Ingresado){
                            case 1 -> {
                                Penales_E2++;
                                Continuar = false;
                            }
                            case 2 -> {
                                Errado_2++;
                                Continuar = false;
                            }
                            default-> {}
                        }
                    }

                    // CHEQUEAR SI CONTINUAR
                    Continuar=true;
                    if (((Penales_E1+Errado_1)>=5) && ((Penales_E2+Errado_2)>=5) && Penales_E1!=Penales_E2){
                        setGanador(Goles_E1 > Goles_E2);
                        Activo = false;
                        Continuar = false;
                    }
                }
            }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // VERIFICADORES
        public boolean EstaElPartidoCompleto(){
            return (Equipo_1!=null && Equipo_2!=null);
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDES
        @Override
        public void MenuPartido(Scanner Teclado) {
            if (Activo){
                int Opcion;
                boolean Continuar = true;
                while(Continuar){
                    System.out.print("\n Equipo 1: Goles:"+Goles_E1+". Amarilla:"+Amarillas_E1+". Roja:"+Rojas_E1+".");
                    System.out.print("\n Equipo 2: Goles:"+Goles_E2+". Amarilla:"+Amarillas_E2+". Roja:"+Rojas_E2+".");
                    System.out.print("\n");
                    System.out.print("\n Elija una de las siguientes opciones.");
                    System.out.print("\n 01 - Gol");
                    System.out.print("\n 02 - Amarilla");
                    System.out.print("\n 03 - Roja");
                    System.out.print("\n 04 - Suspender partido");
                    System.out.print("\n 05 - Terminar partido");
                    System.out.print("\n");
                    Opcion = IngresoPorTeclado.IngresarNumero(Teclado, "\n Opcion: ", false, false);
                    switch (Opcion) {
                        case 1 -> MenuGoles(Teclado);
                        case 2 -> MenuAmarillas(Teclado);
                        case 3 -> MenuRojas(Teclado);
                        case 4 -> {
                            System.out.print("\n El partido esta suspendido hasta su continuacion!");
                            Continuar = false;
                        }
                        case 5 -> {
                            FinalizarPartido(Teclado);
                            Continuar = false;
                        }
                        default -> {}
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
        public void MostrarEstadisticas() {
            System.out.print("\n ---------------------------------------------------------------");
            System.out.print("\n |Nombre de Equipo|-|Goles Hechos|-|Amarillas|-|Rojas|-|Penales|");
            System.out.printf("\n |%16s|", Equipo_1.getNombreEquipo());
            System.out.printf("-|%12s|", Goles_E1);
            System.out.printf("-|%9s|", Amarillas_E1);
            System.out.printf("-|%5s|", Rojas_E1);
            System.out.printf("-|%7s|", Penales_E1);
            System.out.printf("\n |%16s|", Equipo_2.getNombreEquipo());
            System.out.printf("-|%12s|", Goles_E2);
            System.out.printf("-|%9s|", Amarillas_E2);
            System.out.printf("-|%5s|", Rojas_E2);
            System.out.printf("-|%7s|", Penales_E2);
            System.out.print("\n ---------------------------------------------------------------");
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // RETORNADORES GETTERS SETTERS
        public Equipo_Futbol getEquipo_1() {
            return Equipo_1;
        }
        public Equipo_Futbol getEquipo_2() {
            return Equipo_2;
        }
        public int getGoles_E1() {
            return Goles_E1;
        }
        public int getGoles_E2() {
            return Goles_E2;
        }
        public int getPenales_E1() {
            return Penales_E1;
        }
        public int getPenales_E2() {
            return Penales_E2;
        }
        public int getAmarillas_E1() {
            return Amarillas_E1;
        }
        public int getAmarillas_E2() {
            return Amarillas_E2;
        }
        public int getRojas_E1() {
            return Rojas_E1;
        }
        public int getRojas_E2() {
            return Rojas_E2;
        }
        public String GanadorString(){
            String Retornar="";
            if (!Activo){
                if (Ganador){
                    Retornar += Equipo_1.getNombreEquipo();
                }
                else{
                    Retornar += Equipo_2.getNombreEquipo();
                }
            }
            return Retornar;
            }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDE
        @Override
        public String toString() {
            return "Partido_Futbol{" +
                    "Equipo_1=" + Equipo_1 +
                    ", Equipo_2=" + Equipo_2 +
                    ", Goles_E1=" + Goles_E1 +
                    ", Goles_E2=" + Goles_E2 +
                    ", Penales_E1=" + Penales_E1 +
                    ", Penales_E2=" + Penales_E2 +
                    ", Amarillas_E1=" + Amarillas_E1 +
                    ", Amarillas_E2=" + Amarillas_E2 +
                    ", Rojas_E1=" + Rojas_E1 +
                    ", Rojas_E2=" + Rojas_E2 +
                    ", Activo=" + Activo +
                    ", Id_Partido=" + Id_Partido +
                    ", Arbitro=" + Arbitro +
                    ", Nro_Ronda=" + Nro_Ronda +
                    ", Ganador=" + Ganador +
                    '}';
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}