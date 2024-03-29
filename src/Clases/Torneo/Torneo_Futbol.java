package Clases.Torneo;
import Clases.Equipo.Equipo_Futbol;
import Clases.Generica.ListaDeDatos;
import Clases.Partido.Partido_Futbol;
import Clases.Persona.Arbitro;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import static Clases.IngresosPorTeclado.IngresoPorTeclado.*;
public class Torneo_Futbol extends Torneo{

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ATRIBUTOS
        private ArrayList<Partido_Futbol> Partidos;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // CONSTRUCTORES
        public Torneo_Futbol(String nombre, ArrayList<Equipo_Futbol> equipos, ListaDeDatos<Arbitro> Arbitros){
            super(nombre);
            Partidos = new ArrayList<>(15);
            SortearPartidos(equipos, Partidos, Arbitros);
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // METODOS
        // SELECCIONAR Y JUGAR PARTIDO
            private void SeleccionarPartido(Scanner Teclado){
                boolean Continuar = true;
                int IdEquipoSeleccionado;
                while (Continuar){
                    System.out.print("\n A continuacion, ingrese el Id de algun partido disponible.");
                    System.out.print("\n Ingrese -1 para volver al Menu anterior.");
                    MostrarPartidosDisponibles();
                    System.out.print("\n");
                    IdEquipoSeleccionado = IngresarNumero(Teclado, "\n Id: ", false, true);
                    if (IdEquipoSeleccionado!=-1){ // VERIFICAR QUE EXISTA Y ESTE DISPONIBLE
                        if (VerificarPartidoExisteYDisponible_Id(IdEquipoSeleccionado)){
                            int Index = RetornarPosicionPorId(IdEquipoSeleccionado);
                            Partidos.get(Index).MenuPartido(Teclado);
                            if (!Partidos.get(Index).isActivo()){ // SI EL PARTIDO FINALIZO
                                Partidos.get(Index).getArbitro().AumentarPartidosArbitros();
                                if (Partidos.get(Index).isGanador()){
                                    Partidos.get(Index).getEquipo_1().AumentarPartidosGanados();
                                    Partidos.get(Index).getEquipo_2().AumentarPartidosPerdidos();
                                    Partidos.get(Index).getEquipo_2().AumentarTorneosPerdidos();
                                }
                                else{
                                    Partidos.get(Index).getEquipo_2().AumentarPartidosGanados();
                                    Partidos.get(Index).getEquipo_1().AumentarPartidosPerdidos();
                                    Partidos.get(Index).getEquipo_1().AumentarTorneosPerdidos();
                                }
                                if (Partidos.get(Index).getNro_Ronda()==3){ // SI FUE LA FINAL
                                    Activo = false;
                                    if (Partidos.get(Index).isGanador()){
                                        Partidos.get(Index).getEquipo_1().AumentarTorneosGanados();
                                        Partidos.get(Index).getEquipo_2().AumentarTorneosPerdidos();
                                        Partidos.get(Index).getEquipo_2().AumentarTorneosPerdidos();
                                    }
                                    else{
                                        Partidos.get(Index).getEquipo_2().AumentarTorneosGanados();
                                        Partidos.get(Index).getEquipo_1().AumentarTorneosPerdidos();
                                        Partidos.get(Index).getEquipo_1().AumentarTorneosPerdidos();
                                    }
                                }
                                else{
                                    if (!Partidos.get(Partidos.size()-1).EstaElPartidoCompleto()){
                                        // COMPLETAR PARTIDO
                                        if (Partidos.get(Index).isGanador()){
                                            Partidos.get(Partidos.size()-1).AgregarEquipo(Partidos.get(Index).getEquipo_1());
                                        }
                                        else{
                                            Partidos.get(Partidos.size()-1).AgregarEquipo(Partidos.get(Index).getEquipo_2());
                                        }
                                    }
                                    else{
                                        Partido_Futbol Nuevo = new Partido_Futbol(Partidos.get(Index).getNro_Ronda()+1, Partidos.get(Index).getArbitro());
                                        if (Partidos.get(Index).isGanador()){
                                            Nuevo.AgregarEquipo(Partidos.get(Index).getEquipo_1());
                                        }
                                        else{
                                            Nuevo.AgregarEquipo(Partidos.get(Index).getEquipo_2());
                                        }
                                        Partidos.add(Nuevo);
                                    }
                                }
                            }
                            Continuar = false;
                        }
                        else{
                            System.out.print("\n Por favor, ingrese el Id de un partido disponible y existente.");
                        }
                    }
                    else{
                        Continuar = false;
                    }
                }
            }
            public void MostrarPartidosDisponibles(){
                System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\n |Id del Partido|-|--Activo--|-|Nro de Ronda|-|--------------------Arbitro--------------------|-|---Equipo 1---|-|---Equipo 2---|");
                System.out.print("\n |              |-|          |-|            |-|----Dni----|-|----Nombre----|-|----Apellido----|-|----Nombre----|-|----Nombre----|");
                for (Partido_Futbol Aux : Partidos){
                    if (Aux.isActivo() && Aux.EstaElPartidoCompleto()){
                        MostrarPartidoDisponible(Aux);
                    }
                }
                System.out.print("\n --------------------------------------------------------------------------------------------------------------------------------");
            }
            private void MostrarPartidoDisponible(Partido_Futbol Mostrar){
                System.out.printf("\n |%14s|", Mostrar.getId_Partido());
                System.out.printf("-|%10s|", Mostrar.EstaActivo());
                System.out.printf("-|%12s|", Mostrar.getNro_RondaString());
                System.out.printf("-|%11s|", Mostrar.getArbitro().getDni());
                System.out.printf("-|%14s|", Mostrar.getArbitro().getNombre());
                System.out.printf("-|%16s|", Mostrar.getArbitro().getApellido());
                System.out.printf("-|%14s|", Mostrar.getEquipo_1().getNombreEquipo());
                System.out.printf("-|%14s|", Mostrar.getEquipo_2().getNombreEquipo());
            }

        // MOSTRAR PARTIDOS JUGADOS
            public void MostrarPartidosJugados(){
                System.out.print("\n ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\n |Id del Partido|-|--Activo--|-|Nro de Ronda|-|--------------------Arbitro--------------------|-|----------Ganador----------|-|----------------------Equipo 1----------------------|-|----------------------Equipo 2----------------------|");
                System.out.print("\n |              |-|          |-|            |-|----Dni----|-|----Nombre----|-|----Apellido----|-|                           |-|----Nombre----|-|Goles|-|Amarillas|-|Rojas|-|Penales|-|----Nombre----|-|Goles|-|Amarillas|-|Rojas|-|Penales|");
                for (Partido_Futbol Aux : Partidos){
                    if (!Aux.isActivo()){
                        MostrarPartidoJugado(Aux);
                    }
                }
                System.out.print("\n ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
            private void MostrarPartidoJugado(Partido_Futbol Mostrar){
                System.out.printf("\n |%14s|", Mostrar.getId_Partido());
                System.out.printf("-|%10s|", Mostrar.EstaActivo());
                System.out.printf("-|%12s|", Mostrar.getNro_RondaString());
                System.out.printf("-|%11s|", Mostrar.getArbitro().getDni());
                System.out.printf("-|%14s|", Mostrar.getArbitro().getNombre());
                System.out.printf("-|%16s|", Mostrar.getArbitro().getApellido());
                System.out.printf("-|%27s|", Mostrar.GanadorString());
                System.out.printf("-|%14s|", Mostrar.getEquipo_1().getNombreEquipo());
                System.out.printf("-|%5s|", Mostrar.getGoles_E1());
                System.out.printf("-|%9s|", Mostrar.getAmarillas_E1());
                System.out.printf("-|%5s|", Mostrar.getRojas_E1());
                System.out.printf("-|%7s|", Mostrar.getPenales_E1());
                System.out.printf("-|%14s|", Mostrar.getEquipo_2().getNombreEquipo());
                System.out.printf("-|%5s|", Mostrar.getGoles_E2());
                System.out.printf("-|%9s|", Mostrar.getAmarillas_E2());
                System.out.printf("-|%5s|", Mostrar.getRojas_E2());
                System.out.printf("-|%7s|", Mostrar.getPenales_E2());
            }

        // MOSTRAR EQUIPOS PARTICIPANTES
            public void MostrarEquiposParticipantes(){
                System.out.print("\n ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\n |-Deporte-|-|Id de Equipo|-|-------Nombre del Equipo-------|-|------Nombre del Entrenador------|-|Tornes ganados|-|Torneos perdidos|-|Partidos ganador|-|Partidos perdidos|");
                for (int i=0 ; i<8 ; i++){
                    Mostrar_Equipo_Futbol(Partidos.get(i).getEquipo_1());
                    Mostrar_Equipo_Futbol(Partidos.get(i).getEquipo_2());
                }
                System.out.print("\n ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
            private void Mostrar_Equipo_Futbol(Equipo_Futbol Mostrar){
                System.out.print("\n |   Futbol|");
                System.out.printf("-|%12d|", Mostrar.getId_Equipo());
                System.out.printf("-|%31s|", Mostrar.getNombreEquipo());
                System.out.printf("-|%33s|", Mostrar.getEntrenador().getNombre()+" "+Mostrar.getEntrenador().getApellido());
                System.out.printf("-|%14s|", Mostrar.getTorneosGanados());
                System.out.printf("-|%16s|", Mostrar.getTorneosPerdidos());
                System.out.printf("-|%16s|", Mostrar.getPartidosGanados());
                System.out.printf("-|%17s|", Mostrar.getPartidosPerdidos());
            }

        // MOSTRAR TODOS LOS PARTIDOS
            public void MostrarTodosLosPartidos(){
                System.out.print("\n ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\n |Id del Partido|-|--Activo--|-|Nro de Ronda|-|--------------------Arbitro--------------------|-|----------Ganador----------|-|----------------------Equipo 1----------------------|-|----------------------Equipo 2----------------------|");
                System.out.print("\n |              |-|          |-|            |-|----Dni----|-|----Nombre----|-|----Apellido----|-|                           |-|----Nombre----|-|Goles|-|Amarillas|-|Rojas|-|Penales|-|----Nombre----|-|Goles|-|Amarillas|-|Rojas|-|Penales|");
                for (Partido_Futbol Aux : Partidos){
                    if (Aux.EstaElPartidoCompleto()){
                        MostrarPartidoJugado(Aux);
                    }
                }
                System.out.print("\n ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }

        // SORTEAR EQUIPOS
            private void SortearPartidos(ArrayList<Equipo_Futbol>equipos,ArrayList<Partido_Futbol>Partidos,ListaDeDatos<Arbitro> arbitros){
                Random rand = new Random();
                ArrayList<Arbitro> Aniadidos = new ArrayList<>();
                int arbitroSelected;
                for (int i=0 ; i<16 ; i=i+2){
                    arbitroSelected=rand.nextInt(arbitros.size());
                    if (!Aniadidos.contains(arbitros.get(arbitroSelected))){
                        Aniadidos.add(arbitros.get(arbitroSelected));
                        arbitros.get(arbitroSelected).AumentarTorneosArbitros();
                    }
                    Partido_Futbol NuevoPartido = new Partido_Futbol(equipos.get(i), equipos.get(i+1), arbitros.get(arbitroSelected), 0);
                    Partidos.add(NuevoPartido);
                }
            }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // VERIFICADORES
        public boolean ExisteEquipo(Equipo_Futbol Aux){
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
            for (Partido_Futbol Aux : Partidos){
                if (Aux.getArbitro().equals(ArbitroBuscado)){
                    Retornar = true;
                    break;
                }
            }
            return Retornar;
        }
        public boolean VerificarPartidoExisteYDisponible_Id(int IdBuscado){
            boolean Retornar = false;
            for (Partido_Futbol Aux : Partidos){
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
        public String GanadorString(){
            String Retornar="";
            if (!Activo){
                Retornar += Partidos.get(Partidos.size()-1).GanadorString();
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
                while (Continuar){
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
                            System.out.print("\n ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n  Partidos jugados:");
                            MostrarPartidosJugados();
                            System.out.print("\n ");
                            Teclado.nextLine();
                        }
                        case 3 -> {
                            System.out.print("\n ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n  Todos los partidos:");
                            MostrarTodosLosPartidos();
                            System.out.print("\n ");
                            Teclado.nextLine();
                        }
                        case 4 -> {
                            System.out.print("\n ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.print("\n  Participantes:");
                            MostrarEquiposParticipantes();
                            System.out.print("\n ");
                            Teclado.nextLine();
                        }
                        case 0 -> Continuar = false;
                        default -> {}
                    }
                    if (!Activo){
                        Continuar = false;
                    }
                }
            }
            if (!Activo){
                System.out.print("\n ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\n  El torneo ha finalizado. A continuacion, los partidos jugados!");
                MostrarPartidosJugados();
                System.out.print("\n ");
                Teclado.nextLine();
            }
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // RETORNADORES GETTERS SETTERS
        public ArrayList<Partido_Futbol> getPartidos() {
            return Partidos;
        }
        public int RetornarPosicionPorId(int IdBuscado){
            int Retornar=0;
            for (Partido_Futbol Aux : Partidos){
                if (Aux.getId_Partido()==IdBuscado){
                    Retornar = Partidos.indexOf(Aux);
                    break;
                }
            }
            return Retornar;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDE
        @Override
        public String toString() {
            return "Torneo_Futbol{" +
                    "Partidos=" + Partidos +
                    ", Id=" + Id +
                    ", Nombre='" + Nombre + '\'' +
                    ", Activo=" + Activo +
                    '}';
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}