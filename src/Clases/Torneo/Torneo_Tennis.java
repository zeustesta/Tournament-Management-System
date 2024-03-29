package Clases.Torneo;
import static Clases.IngresosPorTeclado.IngresoPorTeclado.*;
import Clases.Equipo.Equipo_Tennis;
import Clases.Generica.ListaDeDatos;
import Clases.Partido.Partido_Tennis;
import Clases.Persona.Arbitro;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Torneo_Tennis extends Torneo {

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ATRIBUTOS
        private ArrayList<Partido_Tennis> Partidos;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // CONSTRUCTORES
        public Torneo_Tennis(String nombre, ArrayList<Equipo_Tennis> equipos, ListaDeDatos<Arbitro> Arbitros) {
            super(nombre);
            Partidos = new ArrayList<>();
            SortearPartidos(equipos, Partidos, Arbitros);
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // METODOS

        // JUGAR UN PARTIDO
            private void SeleccionarPartido(Scanner Teclado){
                boolean Continuar = true;
                int IdEquipoSeleccionado;
                while (Continuar) {
                    System.out.print("\n A continuacion, ingrese el Id de algun partido disponible.");
                    System.out.print("\n Ingrese -1 para volver al Menu anterior.");
                    MostrarPartidosDisponibles();
                    System.out.print("\n");
                    IdEquipoSeleccionado = IngresarNumero(Teclado, "\n Id: ", false, true);
                    if (IdEquipoSeleccionado != -1) { // VERIFICAR QUE EXISTA Y ESTE DISPONIBLE
                        if (VerificarPartidoExisteYDisponible_Id(IdEquipoSeleccionado)){
                            int Index = RetornarPosicionPorId(IdEquipoSeleccionado);
                            Partidos.get(Index).MenuPartido(Teclado);
                            if (!Partidos.get(Index).isActivo()) { // SI EL PARTIDO FINALIZO
                                Partidos.get(Index).getArbitro().AumentarPartidosArbitros();
                                if (Partidos.get(Index).isGanador()) {
                                    Partidos.get(Index).getEquipo_1().AumentarPartidosGanados();
                                    Partidos.get(Index).getEquipo_2().AumentarPartidosPerdidos();
                                    Partidos.get(Index).getEquipo_2().AumentarTorneosPerdidos();
                                }
                                else {
                                    Partidos.get(Index).getEquipo_2().AumentarPartidosGanados();
                                    Partidos.get(Index).getEquipo_1().AumentarPartidosPerdidos();
                                    Partidos.get(Index).getEquipo_1().AumentarTorneosPerdidos();
                                }
                                if (Partidos.get(Index).getNro_Ronda() == 3) { // SI FUE LA FINAL
                                    Activo = false;
                                    if (Partidos.get(Index).isGanador()) {
                                        Partidos.get(Index).getEquipo_1().AumentarTorneosGanados();
                                        Partidos.get(Index).getEquipo_2().AumentarTorneosPerdidos();
                                    } else {
                                        Partidos.get(Index).getEquipo_2().AumentarTorneosGanados();
                                        Partidos.get(Index).getEquipo_1().AumentarTorneosPerdidos();
                                    }
                                }
                                else {
                                    if (!Partidos.get(Partidos.size() - 1).EstaElPartidoCompleto()) {
                                        // COMPLETAR PARTIDO
                                        if (Partidos.get(Index).isGanador()) {
                                            Partidos.get(Partidos.size() - 1).AgregarEquipo(Partidos.get(Index).getEquipo_1());
                                        } else {
                                            Partidos.get(Partidos.size() - 1).AgregarEquipo(Partidos.get(Index).getEquipo_2());
                                        }
                                    } else {
                                        Partido_Tennis Nuevo = new Partido_Tennis(Partidos.get(Index).getNro_Ronda() + 1, Partidos.get(Index).getArbitro());
                                        if (Partidos.get(Index).isGanador()) {
                                            Nuevo.AgregarEquipo(Partidos.get(Index).getEquipo_1());
                                        } else {
                                            Nuevo.AgregarEquipo(Partidos.get(Index).getEquipo_2());
                                        }
                                        Partidos.add(Nuevo);
                                    }
                                }
                            }
                            Continuar = false;
                        }
                        else {
                            System.out.print("\n Por favor, ingrese el Id de un partido disponible y existente.");
                        }
                    }
                    else {
                        Continuar = false;
                    }
                }
            }
            public void MostrarPartidosDisponibles(){
                System.out.print("\n ------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\n |Id del Partido|-|--Activo--|-|Nro de Ronda|-|--------------------Arbitro--------------------|-|-------Equipo 1-------|-|-------Equipo 2-------|");
                System.out.print("\n |              |-|          |-|            |-|----Dni----|-|----Nombre----|-|----Apellido----|-|--------Nombre--------|-|--------Nombre--------|");
                for (Partido_Tennis Aux : Partidos){
                    if (Aux.isActivo() && Aux.EstaElPartidoCompleto()){
                        MostrarPartidoDisponible(Aux);
                    }
                }
                System.out.print("\n ------------------------------------------------------------------------------------------------------------------------------------------------");
            }
            private void MostrarPartidoDisponible(Partido_Tennis Mostrar){
                System.out.printf("\n |%14s|", Mostrar.getId_Partido());
                System.out.printf("-|%10s|", Mostrar.EstaActivo());
                System.out.printf("-|%12s|", Mostrar.getNro_RondaString());
                System.out.printf("-|%11s|", Mostrar.getArbitro().getDni());
                System.out.printf("-|%14s|", Mostrar.getArbitro().getNombre());
                System.out.printf("-|%16s|", Mostrar.getArbitro().getApellido());
                System.out.printf("-|%22s|", Mostrar.getEquipo_1().getJugador().getNombre()+" "+Mostrar.getEquipo_1().getJugador().getApellido());
                System.out.printf("-|%22s|", Mostrar.getEquipo_2().getJugador().getNombre()+" "+Mostrar.getEquipo_2().getJugador().getApellido());
            }

        // MOSTRAR PARTIDOS JUGADOS
            public void MostrarPartidosJugados(){
                System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\n |Id del Partido|-|--Activo--|-|Nro de Ronda|-|--------------------Arbitro--------------------|-|----------Ganador----------|-|-------------------------Equipo 01-------------------------|-|-------------------------Equipo 02-------------------------|");
                System.out.print("\n |              |-|          |-|            |-|----Dni----|-|----Nombre----|-|----Apellido----|-|                           |-|----Nombre----|-|Sets|-|Games|-|Puntos|-|Aces|-|Doble falta|-|----Nombre----|-|Sets|-|Games|-|Puntos|-|Aces|-|Doble falta|");
                for (Partido_Tennis Aux : Partidos){
                    if (!Aux.isActivo()){
                        MostrarPartidoJugado(Aux);
                    }
                }
                System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
            private void MostrarPartidoJugado(Partido_Tennis Mostrar){
                System.out.printf("\n |%14s|", Mostrar.getId_Partido());
                System.out.printf("-|%10s|", Mostrar.EstaActivo());
                System.out.printf("-|%12s|", Mostrar.getNro_RondaString());
                System.out.printf("-|%11s|", Mostrar.getArbitro().getDni());
                System.out.printf("-|%14s|", Mostrar.getArbitro().getNombre());
                System.out.printf("-|%16s|", Mostrar.getArbitro().getApellido());
                System.out.printf("-|%27s|", Mostrar.GanadorString());
                System.out.printf("-|%14s|", Mostrar.getEquipo_1().getJugador().getNombre());
                System.out.printf("-|%4s|", Mostrar.getSetsGanados_E1());
                System.out.printf("-|%5s|", Mostrar.getGamesGanados_E1());
                System.out.printf("-|%6s|", Mostrar.getPuntos_E1());
                System.out.printf("-|%4s|", Mostrar.getAces_E1());
                System.out.printf("-|%11s|", Mostrar.getDobleFalta_E1());
                System.out.printf("-|%14s|", Mostrar.getEquipo_2().getJugador().getNombre());
                System.out.printf("-|%4s|", Mostrar.getSetsGanados_E2());
                System.out.printf("-|%5s|", Mostrar.getGamesGanados_E2());
                System.out.printf("-|%6s|", Mostrar.getPuntos_E2());
                System.out.printf("-|%4s|", Mostrar.getAces_E2());
                System.out.printf("-|%11s|", Mostrar.getDobleFalta_E2());
            }

        // MOSTRAR EQUIPOS PARTICIPANTES
            public void MostrarEquiposParticipantes(){
                System.out.print("\n ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\n |-Deporte-|-|Id de Equipo|-|-------Nombre del Jugador-------|-|------Nombre del Entrenador------|-|Tornes ganados|-|Torneos perdidos|-|Partidos ganador|-|Partidos perdidos|");
                for (int i=0 ; i<8 ; i++){
                    Mostrar_Equipo_Tennis(Partidos.get(i).getEquipo_1());
                    Mostrar_Equipo_Tennis(Partidos.get(i).getEquipo_2());
                }
                System.out.print("\n ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
            private void Mostrar_Equipo_Tennis(Equipo_Tennis Mostrar){
                System.out.print("\n |   Tennis|");
                System.out.printf("-|%12d|", Mostrar.getId_Equipo());
                System.out.printf("-|%32s|", Mostrar.getJugador().getNombre()+" "+Mostrar.getJugador().getApellido());
                System.out.printf("-|%33s|", Mostrar.getEntrenador().getNombre()+" "+Mostrar.getEntrenador().getApellido());
                System.out.printf("-|%14s|", Mostrar.getTorneosGanados());
                System.out.printf("-|%16s|", Mostrar.getTorneosPerdidos());
                System.out.printf("-|%16s|", Mostrar.getPartidosGanados());
                System.out.printf("-|%17s|", Mostrar.getPartidosPerdidos());
            }

        // MOSTRAR TODOS LOS PARTIDOS
            public void MostrarTodosLosPartidos(){
                System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\n |Id del Partido|-|--Activo--|-|Nro de Ronda|-|--------------------Arbitro--------------------|-|----------Ganador----------|-|-------------------------Equipo 01-------------------------|-|-------------------------Equipo 02-------------------------|");
                System.out.print("\n |              |-|          |-|            |-|----Dni----|-|----Nombre----|-|----Apellido----|-|                           |-|----Nombre----|-|Sets|-|Games|-|Puntos|-|Aces|-|Doble falta|-|----Nombre----|-|Sets|-|Games|-|Puntos|-|Aces|-|Doble falta|");
                for (Partido_Tennis Aux : Partidos){
                    if (Aux.EstaElPartidoCompleto()){
                        MostrarPartidoJugado(Aux);
                    }
                }
                System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }

        // SORTEAR EQUIPOS
            public void SortearPartidos(ArrayList<Equipo_Tennis>equipos,ArrayList<Partido_Tennis>Partidos,ListaDeDatos<Arbitro> arbitros){
                Random rand = new Random();
                ArrayList<Arbitro> Aniadidos = new ArrayList<>();
                int arbitroSelected;
                for (int i=0 ; i<16 ; i=i+2){
                    arbitroSelected=rand.nextInt(arbitros.size());
                    if (!Aniadidos.contains(arbitros.get(arbitroSelected))){
                        Aniadidos.add(arbitros.get(arbitroSelected));
                        arbitros.get(arbitroSelected).AumentarTorneosArbitros();
                    }
                    arbitroSelected=rand.nextInt(arbitros.size());
                    Partido_Tennis NuevoPartido = new Partido_Tennis(equipos.get(i), equipos.get(i+1), arbitros.get(arbitroSelected), 0);
                    Partidos.add(NuevoPartido);
                }
            }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // VERIFICADORES
    public boolean VerificarPartidoExisteYDisponible_Id(int IdBuscado){
        boolean Retornar = false;
        for (Partido_Tennis Aux : Partidos){
            if (Aux.getId_Partido()==IdBuscado){
                if (Aux.isActivo()){
                    if (Aux.EstaElPartidoCompleto()){
                        Retornar = true;
                    }
                }
            }
        }
        return Retornar;
    }
    public boolean ExisteEquipo(Equipo_Tennis Aux){
        boolean Retornar = false;
        for(int i=0 ; i<4 ; i++){
            if (Partidos.get(i).getEquipo_1().equals(Aux)){
                Retornar = true;
                break;
            }
            else if (Partidos.get(i).getEquipo_2().equals(Aux)){
                Retornar = true;
                break;
            }
        }
        return Retornar;
    }
    public boolean ExisteArbitro(Arbitro ArbitroBuscado){
        boolean Retornar = false;
        for (Partido_Tennis Aux : Partidos){
            if (Aux.getArbitro().equals(ArbitroBuscado)){
                Retornar = true;
                break;
            }
        }
        return Retornar;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDES
        @Override
        public void MenuTorneo(Scanner Teclado) {
            if (Activo){
                boolean Continuar = true;
                int Opcion;
                while (Continuar) {
                    System.out.print("\n Elija una de las siguientes opciones:");
                    System.out.print("\n");
                    System.out.print("\n 01 - Jugar partido");
                    System.out.print("\n 02 - Mostrar Partidos Jugados");
                    System.out.print("\n 03 - Mostrar todos los partidos.");
                    System.out.print("\n 04 - Mostrar los equipos participantes");
                    System.out.print("\n");
                    System.out.print("\n 00 -  Volver");
                    System.out.print("\n");
                    Opcion = IngresarNumero(Teclado, "\n Opcion: ", false, false);
                    switch (Opcion) {
                        case 1 -> SeleccionarPartido(Teclado);
                        case 2 -> {
                            System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n  Partidos jugados:");
                            MostrarPartidosJugados();
                            System.out.print("\n ");
                            Teclado.nextLine();
                        }
                        case 3 -> {
                            System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n  Todos los partidos:");
                            MostrarTodosLosPartidos();
                            System.out.print("\n ");
                            Teclado.nextLine();
                        }
                        case 4 -> {
                            System.out.print("\n ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n  Participantes:");
                            MostrarEquiposParticipantes();
                            System.out.print("\n ");
                            Teclado.nextLine();
                        }
                        case 0 -> Continuar = false;
                        default -> {
                        }
                    }
                    if (!Activo){
                        Continuar = false;
                    }
                }
            }
            if (!Activo){
                System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\n  El torneo ha finalizado. A continuacion, los partidos jugados!");
                MostrarPartidosJugados();
                System.out.print("\n ");
                Teclado.nextLine();
            }
        }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // RETORNADORES GETTERS SETTERS
        public int RetornarPosicionPorId(int IdBuscado){
            int Retornar=0;
            for (Partido_Tennis Aux : Partidos){
                if (Aux.getId_Partido()==IdBuscado){
                    Retornar = Partidos.indexOf(Aux);
                    break;
                }
            }
            return Retornar;
        }
        public String GanadorString(){
            String Retornar="";
            if (!Activo){
                Retornar += Partidos.get(Partidos.size()-1).GanadorString();
            }
            return Retornar;
        }
        public ArrayList<Partido_Tennis> getPartidos() {
            return Partidos;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDE
        @Override
        public String toString() {
            return "Torneo_Tennis{" +
                    "Partidos=" + Partidos +
                    ", Id=" + Id +
                    ", Nombre='" + Nombre + '\'' +
                    ", Activo=" + Activo +
                    '}';
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}