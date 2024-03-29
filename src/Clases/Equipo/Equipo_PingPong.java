package Clases.Equipo;
import Clases.Persona.Jugador_PingPong;
import Clases.Persona.Tecnico_Entrenador;
public class Equipo_PingPong extends Equipo{

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ATRIBUTOS
        private Jugador_PingPong Jugador;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // CONSTRUCTORES
        public Equipo_PingPong(Tecnico_Entrenador _Tecnico, Jugador_PingPong _Jugador){
            super(_Tecnico);
            Jugador = _Jugador;
        }
        public Equipo_PingPong(){
            super();
            Jugador = null;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // METODOS
        public void AgregarJugador(Jugador_PingPong _JugadorNuevo){
            Jugador = _JugadorNuevo;
        }
        public void AumentarPartidosGanados(){
            PartidosGanados++;
            Jugador.AumentarPartidosGanados();
        }
        public void AumentarPartidosPerdidos(){
            PartidosPerdidos++;
            Jugador.AumentarPartidosPerdidos();
        }
        public void AumentarTorneosGanados(){
            TorneosGanados++;
            Jugador.AumentarTorneosGanados();
        }
        public void AumentarTorneosPerdidos(){
            TorneosPerdidos++;
            Jugador.AumentarTorneosPerdidos();
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // VERIFICADORES
        public boolean EstaJugadorCargado(){
            return Jugador != null;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // RETORNADORES GETTERS SETTERS
        public Jugador_PingPong getJugador() {
        return Jugador;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDE
        @Override
        public String toString() {
            return "Equipo_PingPong{" +
                    "Jugador=" + Jugador +
                    ", Id_Equipo=" + Id_Equipo +
                    ", Entrenador=" + Entrenador +
                    ", PartidosGanados=" + PartidosGanados +
                    ", PartidosPerdidos=" + PartidosPerdidos +
                    ", TorneosGanados=" + TorneosGanados +
                    ", TorneosPerdidos=" + TorneosPerdidos +
                    '}';
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}