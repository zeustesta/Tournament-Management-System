package Clases.Persona;

public class Jugador_Futbol extends Jugador{
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ATRIBUTOS
        private int AmarillasRecibidas, RojasRecibidas;
        private String Posicion_Juego;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // CONSTRUCTORES
        public Jugador_Futbol(Persona NuevaPersona, String _Posicion_Juego){
            super(NuevaPersona);
            AmarillasRecibidas=0;
            RojasRecibidas=0;
            Posicion_Juego = _Posicion_Juego;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // METODOS
        public void AumentarAmarillasRecibidas(){
            this.AmarillasRecibidas += 1;
        }
        public void AumentarRojasRecibidas(){
            this.RojasRecibidas += 1;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // RETORNADORES GETTERS SETTERS
        public int getAmarillasRecibidas() {
            return AmarillasRecibidas;
        }
        public int getRojasRecibidas() {
            return RojasRecibidas;
        }
        public String getPosicion_Juego() {
            return Posicion_Juego;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDE
        @Override
        public String toString() {
            return "Jugador_Futbol{" +
                    "AmarillasRecibidas=" + AmarillasRecibidas +
                    ", RojasRecibidas=" + RojasRecibidas +
                    ", Posicion_Juego='" + Posicion_Juego + '\'' +
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