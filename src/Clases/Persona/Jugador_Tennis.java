package Clases.Persona;
import Interfaces.Juego_Paleta_Raqueta;
public class Jugador_Tennis extends Jugador implements Juego_Paleta_Raqueta {

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ATRIBUTOS
        private int PuntosPerdidos;
        private int GamesGanados, GamesPerdidos;
        private int Aces, DobleFalta;
        private int SaqueNoRecibido;
        private int SetsGanados, SetsPerdidos;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // CONSTRUCTORES
        public Jugador_Tennis(Persona NuevaPersona){
            super(NuevaPersona);
            PuntosPerdidos=0;
            GamesGanados=0;
            GamesPerdidos=0;
            Aces=0;
            DobleFalta=0;
            SaqueNoRecibido=0;
            SetsGanados=0;
            SetsPerdidos=0;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // METODOS
        public void AumentarGamesGanados() {
            GamesGanados += 1;
        }
        public void AumentarGamesPerdidos() {
            GamesPerdidos += 1;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDES
        @Override
        public void AumentarPuntosPerdidos() {
            PuntosPerdidos += 1;
        }
        @Override
        public void AumentarAces() {
            Aces += 1;
        }
        @Override
        public void AumentarSetsGanados(){
            SetsGanados += 1;
        }
        @Override
        public void AumentarSetsPerdidos(){
            SetsPerdidos += 1;
        }
        @Override
        public void AumentarSaquesNoRecibidos() {
            SaqueNoRecibido += 1;
        }
        @Override
        public void AumentarSaqueIncorrecto() {
            DobleFalta += 1;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // RETORNADORES GETTERS SETTERS
        public int getPuntosPerdidos() {
            return PuntosPerdidos;
        }
        public int getGamesGanados() {
            return GamesGanados;
        }
        public int getGamesPerdidos() {
            return GamesPerdidos;
        }
        public int getAces() {
            return Aces;
        }
        public int getDobleFalta() {
            return DobleFalta;
        }
        public int getSaqueNoRecibido() {
            return SaqueNoRecibido;
        }
        public int getSetsGanados() {
            return SetsGanados;
        }
        public int getSetsPerdidos() {
            return SetsPerdidos;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDE
        @Override
        public String toString() {
            return "Jugador_Tennis{" +
                    "PuntosPerdidos=" + PuntosPerdidos +
                    ", GamesGanados=" + GamesGanados +
                    ", GamesPerdidos=" + GamesPerdidos +
                    ", Aces=" + Aces +
                    ", DobleFalta=" + DobleFalta +
                    ", SaqueNoRecibido=" + SaqueNoRecibido +
                    ", SetsGanados=" + SetsGanados +
                    ", SetsPerdidos=" + SetsPerdidos +
                    ", PartidosGanados=" + PartidosGanados +
                    ", PartidosPerdidos=" + PartidosPerdidos +
                    ", TorneosGanados=" + TorneosGanados +
                    ", TorneosPerdidos=" + TorneosPerdidos +
                    ", PuntoGanados_Goles=" + PuntoGanados_Goles +
                    ", Nombre='" + Nombre + '\'' +
                    ", Apellido='" + Apellido + '\'' +
                    ", Dni='" + Dni + '\'' +
                    ", Nacionalidad='" + Nacionalidad + '\'' +
                    ", Fecha_Nacimiento=" + Fecha_Nacimiento +
                    '}';
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}