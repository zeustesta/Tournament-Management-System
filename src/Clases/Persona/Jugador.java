package Clases.Persona;
public abstract class Jugador extends Persona{

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // ATRIBUTOS
        protected int PartidosGanados, PartidosPerdidos;
        protected int TorneosGanados, TorneosPerdidos;
        protected int PuntoGanados_Goles;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // CONSTRUCTORES
        public Jugador(Persona NuevaPersona){
            super(NuevaPersona);
            PartidosGanados=0;
            PartidosPerdidos=0;
            TorneosGanados=0;
            TorneosPerdidos=0;
            PuntoGanados_Goles=0;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // METODOS
        public void AumentarPartidosGanados(){
            this.PartidosGanados += 1;
        }
        public void AumentarPartidosPerdidos(){
            this.PartidosPerdidos += 1;
        }
        public void AumentarTorneosGanados(){
            this.TorneosGanados += 1;
        }
        public void AumentarTorneosPerdidos(){
            this.TorneosPerdidos += 1;
        }
        public void AumentarPuntosGanados_Goles(){
            this.PuntoGanados_Goles += 1;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // RETORNADORES GETTERS SETTERS
        public int getPartidosGanados() {
            return PartidosGanados;
        }
        public int getPartidosPerdidos() {
            return PartidosPerdidos;
        }
        public int getTorneosGanados() {
            return TorneosGanados;
        }
        public int getTorneosPerdidos() {
            return TorneosPerdidos;
        }
        public int getPuntoGanados_Goles() {
            return PuntoGanados_Goles;
        }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // OVERRIDE
        @Override
        public String toString() {
            return "Jugador{" +
                    "PartidosGanados=" + PartidosGanados +
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