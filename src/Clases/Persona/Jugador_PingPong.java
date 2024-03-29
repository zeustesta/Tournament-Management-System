package Clases.Persona;
import Interfaces.Juego_Paleta_Raqueta;

public class Jugador_PingPong extends Jugador implements Juego_Paleta_Raqueta {

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ATRIBUTOS
        private int PuntosPerdidos;
        private int Aces, SaqueErrado, SaqueNoRecibido;
        private int SetsGanados, SetsPerdidos;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // CONSTRUCTORES
        public Jugador_PingPong(Persona NuevaPersona){
            super(NuevaPersona);
            PuntosPerdidos=0;
            Aces=0;
            SaqueErrado=0;
            SaqueNoRecibido=0;
            SetsGanados=0;
            SetsPerdidos=0;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // METODOS

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDES
        @Override
        public void AumentarPuntosPerdidos(){
            this.PuntosPerdidos += 1;
        }
        @Override
        public void AumentarAces(){
            this.Aces += 1;
        }
        @Override
        public void AumentarSetsPerdidos(){
            this.SetsPerdidos += 1;
        }
        @Override
        public void AumentarSetsGanados(){
            this.SetsGanados += 1;
        }
        @Override
        public void AumentarSaquesNoRecibidos(){
            this.SaqueNoRecibido += 1;
        }
        @Override
        public void AumentarSaqueIncorrecto(){
            this.SaqueErrado += 1;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // RETORNADORES GETTERS SETTERS
        public int getPuntosPerdidos() {
            return PuntosPerdidos;
        }
        public int getAces() {
            return Aces;
        }
        public int getSaqueErrado() {
            return SaqueErrado;
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
            return "Jugador_PingPong{" +
                    "PuntosPerdidos=" + PuntosPerdidos +
                    ", Aces=" + Aces +
                    ", SaqueErrado=" + SaqueErrado +
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