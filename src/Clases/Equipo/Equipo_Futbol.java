package Clases.Equipo;
import Clases.Persona.Jugador_Futbol;
import Clases.Persona.Tecnico_Entrenador;
import java.util.ArrayList;

public class Equipo_Futbol extends Equipo{

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ATRIBUTOS
        private String NombreEquipo;
        private ArrayList<Jugador_Futbol> Jugadores;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // CONSTRUCTORES
        public Equipo_Futbol(String Nombre, Tecnico_Entrenador _Entrenador){
            super(_Entrenador);
            Jugadores = new ArrayList<>();
            NombreEquipo = Nombre;
        }
        public Equipo_Futbol(Tecnico_Entrenador _Entrenador, String _Nombre){
            super(_Entrenador);
            Jugadores = new ArrayList<>();
            NombreEquipo = _Nombre;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // METODOS

        // AUMENTAR
            public void AumentarPartidosGanados(){
                PartidosGanados++;
                for (Jugador_Futbol Aux : Jugadores){
                    Aux.AumentarPartidosGanados();
                }
            }
            public void AumentarPartidosPerdidos(){
                PartidosPerdidos++;
                for (Jugador_Futbol Aux : Jugadores){
                    Aux.AumentarPartidosPerdidos();
                }
            }
            public void AumentarTorneosGanados(){
                TorneosGanados++;
                for (Jugador_Futbol Aux : Jugadores){
                    Aux.AumentarTorneosGanados();
                }
            }
            public void AumentarTorneosPerdidos(){
                TorneosGanados++;
                for (Jugador_Futbol Aux : Jugadores){
                    Aux.AumentarTorneosPerdidos();
                }
            }
            public void AumentarGolPorDni(String Dni){
                for (Jugador_Futbol Aux : Jugadores){
                    if (Aux.getDni().equals(Dni)){
                        Aux.AumentarPuntosGanados_Goles();
                        break;
                    }
                }
            }
            public void AumentarAmarillasPorDni(String Dni){
                for (Jugador_Futbol Aux : Jugadores){
                    if (Aux.getDni().equals(Dni)){
                        Aux.AumentarAmarillasRecibidas();
                        break;
                    }
                }
            }
            public void AumentarRojasPorDni(String Dni){
                for (Jugador_Futbol Aux : Jugadores){
                    if (Aux.getDni().equals(Dni)){
                        Aux.AumentarRojasRecibidas();
                        break;
                    }
                }
            }

        // AGREGAR
            public void AgregarJugador(Jugador_Futbol _JugadorNuevo){
                Jugadores.add(_JugadorNuevo);
            }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // VERIFICADORES

        // EXISTE DNI
            public boolean ExisteDni(String Dni){
                boolean Retornar = false;
                for (Jugador_Futbol Aux : Jugadores){
                    if (Aux.getDni().equals(Dni)){
                        Retornar = true;
                        break;
                    }
                }
                return Retornar;
            }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDES
        @Override
        public String toString() {
                return "Equipo_Futbol{" +
                        "NombreEquipo='" + NombreEquipo + '\'' +
                        ", Jugadores=" + Jugadores +
                        ", Id_Equipo=" + Id_Equipo +
                        ", Entrenador=" + Entrenador +
                        ", PartidosGanados=" + PartidosGanados +
                        ", PartidosPerdidos=" + PartidosPerdidos +
                        ", TorneosGanados=" + TorneosGanados +
                        ", TorneosPerdidos=" + TorneosPerdidos +
                        '}';
            }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // RETORNADORES GETTERS SETTERS
        public ArrayList<Jugador_Futbol> getJugadores() {
            return Jugadores;
        }
        public String getNombreEquipo() {
            return NombreEquipo;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
