package Clases.Equipo;
import Clases.Persona.Jugador_Tennis;
import Clases.Persona.Tecnico_Entrenador;

public class Equipo_Tennis extends Equipo{

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ATRIBUTOS
        private Jugador_Tennis Jugador;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // CONSTRUCTORES
        public Equipo_Tennis(Tecnico_Entrenador _Entrenador, Jugador_Tennis _Jugador){
            super(_Entrenador);
            Jugador = _Jugador;
        }
        public Equipo_Tennis(){
            super();
            Jugador=null;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // METODOS
        public void AgregarJugador(Jugador_Tennis _Jugador){
            Jugador = _Jugador;
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
        return Jugador!=null;
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // RETORNADORES GETTERS SETTERS
        public Jugador_Tennis getJugador() {
            return Jugador;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDE
        @Override
        public String toString() {
            return "Equipo_Tennis{" +
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
